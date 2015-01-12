package com.elevendustries.simpleprop.instr;

import com.elevendustries.simpleprop.COG;

public class Andn extends Instruction {
	
	public Andn(COG cog) {
		super(cog);
		// TODO Auto-generated constructor stub
	}

	protected void execute() {
		
		int result = this.getDestination() & (this.getSource() ^ (int) INT_MASK);
		
		this.writeResult(result);
		setZ(result == 0);
		
		// calculate parity
		for (int i=0; i<32; i++) {
			result = (result >>> 1) ^ (result & 1);
		}
		
		setC(result == 1);
	}
}