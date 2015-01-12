package com.elevendustries.simpleprop.instr;

import com.elevendustries.simpleprop.COG;

public class Shr extends Instruction {
	
	public Shr(COG cog) {
		super(cog);
		// TODO Auto-generated constructor stub
	}

	protected void execute() {
		
		int workspace; 
		
		setC((this.getDestination() & 1) == 1);
		
		workspace = this.getDestination() >>> (this.getSource() & 0x1F);
		
		setZ(workspace == 0);
		this.writeResult(workspace);
	}
}