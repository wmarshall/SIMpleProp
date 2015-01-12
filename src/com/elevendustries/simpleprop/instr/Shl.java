package com.elevendustries.simpleprop.instr;

import com.elevendustries.simpleprop.COG;

public class Shl extends Instruction {
	
	public Shl(COG cog) {
		super(cog);
		// TODO Auto-generated constructor stub
	}

	protected void execute() {
		
		int workspace; 
		
		setC((this.getDestination() & 0x80000000) == 0x80000000);
		
		workspace = this.getDestination() << (this.getSource() & 0x1F);
		
		setZ(workspace == 0);
		this.writeResult(workspace);
	}
}