package com.elevendustries.simpleprop.instr;

import com.elevendustries.simpleprop.COG;

public class Ror extends Instruction {
	
	public Ror(COG cog) {
		super(cog);
		// TODO Auto-generated constructor stub
	}

	protected void execute() {
		
		setZ(this.getDestination() == 0);
		setC((this.getDestination() & 1) == 1);
		
		this.writeResult(java.lang.Integer.rotateRight(this.getDestination(), this.getSource() & 0x1F));
	}
}