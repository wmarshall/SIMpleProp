package com.elevendustries.simpleprop.instr;

import com.elevendustries.simpleprop.COG;

public class Movs extends Instruction {
	
	public Movs(COG cog) {
		super(cog);
		// TODO Auto-generated constructor stub
	}

	protected void execute() {
		
		int result = (this.getDestination() & ((int) INT_MASK ^ SRC_MASK)) | (this.getSource() & SRC_MASK);
		
		setZ(result == 0);
		setC(false);
	}
}