package com.elevendustries.simpleprop.instr;

import com.elevendustries.simpleprop.COG;

public class Movd extends Instruction {
	
	public Movd(COG cog) {
		super(cog);
		// TODO Auto-generated constructor stub
	}

	protected void execute() {
		
		int result = (this.getDestination() & ((int) INT_MASK ^ DEST_MASK)) | ((this.getSource() & SRC_MASK) << DEST_MASK);
		
		setZ(result == 0);
		setC(false);
	}
}