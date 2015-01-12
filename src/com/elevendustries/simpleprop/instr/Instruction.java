package com.elevendustries.simpleprop.instr;

import com.elevendustries.simpleprop.COG;

public abstract class Instruction {
	
	protected final static int SRC_OFFSET  = 0;
	protected final static int DEST_OFFSET = 9;
	protected final static int COND_OFFSET = 18;
	protected final static int IFLG_OFFSET = 22;
	protected final static int RFLG_OFFSET = 23;
	protected final static int CFLG_OFFSET = 24;
	protected final static int ZFLG_OFFSET = 25;
	protected final static int ISTR_OFFSET = 26;
	
	protected final static int SRC_MASK  = 0x1FF << SRC_OFFSET;
	protected final static int DEST_MASK = 0x1FF << DEST_OFFSET;
	protected final static int COND_MASK = 0xF   << COND_OFFSET;
	protected final static int IFLG_MASK = 0x1   << IFLG_OFFSET;
	protected final static int RFLG_MASK = 0x1   << RFLG_OFFSET;
	protected final static int CFLG_MASK = 0x1   << CFLG_OFFSET;
	protected final static int ZFLG_MASK = 0x1   << ZFLG_OFFSET;
	protected final static int ISTR_MASK = 0x3F  << ISTR_OFFSET;
	
	protected final static long INT_MASK = 0xFFFFFFFF;
	
	public final static int cycles = 4;	// Number of cycles that a particular instruction takes (min)
	
	public COG cog;
	public int count;
	public int instr;
	
	public Instruction(COG cog) {
		this.count = 0;
		this.cog = cog;
	}

	public boolean step() {	// method called at each clock cycle
		
		if (!this.testCondition()) {
			if (++count == Nop.cycles) {
				return true;
			} else {
				return false;
			}
		}
		
		if (++count == cycles) {
			this.execute();
		}
		
		if (count >= cycles) {
			return true;
		}
		
		return false;
		
	}
	
	// Following methods just deconstruct instruction
	
	public int getSrcField() {
		return (instr & SRC_MASK) >>> SRC_OFFSET;
	}
	
	public int getDestField() {
		return (instr & DEST_MASK) >>> DEST_OFFSET;
	}
	
	public int getCondition() {
		return (instr & COND_MASK) >>> COND_OFFSET;
	}
	
	public int getIFlag() {
		return (instr & IFLG_MASK) >>> IFLG_OFFSET;
	}
	
	public int getRFlag() {
		return (instr & RFLG_MASK) >>> RFLG_OFFSET;
	}
	
	public int getCFlag() {
		return (instr & CFLG_MASK) >>> CFLG_OFFSET;
	}
	
	public int getZFlag() {
		return (instr & ZFLG_MASK) >>> ZFLG_OFFSET;
	}
	
	public int getOpcode() {
		return (instr & ISTR_MASK) >>> ISTR_OFFSET;
	}
	
	public int getInstruction() {
		return this.instr;
	}
	
	public void setInstruction(int instr) {
		this.count = 0;
		this.instr = instr;
	}
	
	protected void writeResult(int data) {	// handles whether or not we write the result
		
		if (getRFlag() == 0) {
			return;
		}
		
		cog.cogram[getDestField()] = data;
		
	}
	
	protected int getSource() {
		
		int src = getSrcField();
		
		if (getIFlag() == 1) {
			return src;
		}
		
		return cog.cogram[src];
		
	}
	
	protected int getDestination() {
		return cog.cogram[getDestField()];
	}
	
	protected void setC(boolean data) {
		if (getCFlag() == 1) {
			cog.cflag = data ? 1 : 0;
		}
	}
	
	protected void setZ(boolean data) {
		if (getZFlag() == 1) {
			cog.zflag = data ? 1 : 0;
		}
	}
	
	protected boolean testCondition() {
		
		switch (this.getCondition()) {
		case 0:
			return false;
		case 1:
			return (cog.cflag == 0) && (cog.zflag == 0);
		case 2:
			return (cog.cflag == 0) && (cog.zflag == 1);
		case 3:
			return (cog.cflag == 0);
		case 4:
			return (cog.cflag == 1) && (cog.zflag == 0);
		case 5:
			return (cog.zflag == 0);
		case 6:
			return (cog.cflag != cog.zflag);
		case 7:
			return (cog.cflag == 0) | (cog.zflag == 0);
		case 8:
			return (cog.cflag == 1) && (cog.zflag == 1);
		case 9:
			return (cog.cflag == cog.zflag);
		case 10:
			return (cog.zflag == 1);
		case 11:
			return (cog.cflag == 0) | (cog.zflag == 1);
		case 12:
			return (cog.cflag == 1);
		case 13:
			return (cog.cflag == 1) | (cog.zflag == 0);
		case 14:
			return (cog.cflag == 1) | (cog.zflag == 1);
		case 15:
			return true;
		default:
			return false;
			
		}
	}
	
	
	protected abstract void execute();	// Code part to be filled by each instruction definition
	
}