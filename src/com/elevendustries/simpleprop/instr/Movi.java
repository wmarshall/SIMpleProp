package com.elevendustries.simpleprop.instr;

import com.elevendustries.simpleprop.COG;

public class Movi extends Instruction {
	
	public Movi(COG cog) {
		super(cog);
		// TODO Auto-generated constructor stub
	}

	protected void execute() {
		
		int result = (this.getDestination() & ((int) INT_MASK ^ RFLG_MASK)) | ((this.getSource() & SRC_MASK) << RFLG_MASK);
		
		setZ(result == 0);
		setC(false);
	}
}