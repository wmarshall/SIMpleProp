package com.elevendustries.simpleprop.instr;

import com.elevendustries.simpleprop.COG;

public class Addabs extends Instruction {
	
	public Addabs(COG cog) {
		super(cog);
		// TODO Auto-generated constructor stub
	} 

	protected void execute() {
		
		long result = (long) this.getDestination() & INT_MASK;
		result += (long) Math.abs(this.getSource()) & INT_MASK;
		
		this.writeResult((int)result);
		setZ(((int) result) == 0);
		setC((result & (1 + INT_MASK)) > INT_MASK);
	}
}