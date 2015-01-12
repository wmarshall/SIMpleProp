package com.elevendustries.simpleprop.instr;

import com.elevendustries.simpleprop.COG;

public class Min extends Instruction {

	public Min(COG cog) {
		super(cog);
		// TODO Auto-generated constructor stub
	}
	
	protected void execute() {
		
		long dest = ((long) this.getDestination()) & INT_MASK;
		long src  = ((long) this.getDestination()) & INT_MASK;
		
		setC(dest < src);
		setZ(src == 0);
		this.writeResult((dest < src) ? (int) dest : (int) src);
	}
}