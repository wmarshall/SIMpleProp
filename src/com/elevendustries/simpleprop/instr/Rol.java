package com.elevendustries.simpleprop.instr;

import com.elevendustries.simpleprop.COG;

public class Rol extends Instruction {
	
	public Rol(COG cog) {
		super(cog);
		// TODO Auto-generated constructor stub
	}

	protected void execute() {
		
		setZ(this.getDestination() == 0);
		setC((this.getDestination() & 0x80000000) == 0x80000000);
		
		this.writeResult(java.lang.Integer.rotateLeft(this.getDestination(), this.getSource() & 0x1F));
	}
}