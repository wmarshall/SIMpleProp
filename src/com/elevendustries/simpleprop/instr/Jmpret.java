package com.elevendustries.simpleprop.instr;

import com.elevendustries.simpleprop.COG;

public class Jmpret extends Instruction {
	
	public Jmpret(COG cog) {
		super(cog);
		// TODO Auto-generated constructor stub
	}

	protected void execute() {
		
		cog.cogram[this.getDestination()] = (cog.cogram[this.getDestination()] & (int) INT_MASK ^ SRC_MASK) | (cog.progcounter - 1); // subtract 1 since we queue two instructions
		cog.progcounter = this.getSource();
		cog.advanceCount();
		
		setC(true);
		setZ(this.getDestination() == 0);
	}
}