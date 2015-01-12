package com.elevendustries.simpleprop.instr;

import com.elevendustries.simpleprop.COG;

public class Rev extends Instruction {
	
	public Rev(COG cog) {
		super(cog);
		// TODO Auto-generated constructor stub
	}

	protected void execute() {
		
		int dest = this.getDestination(), 
				src = this.getSource(), 
				workspace = 0,
				mask = 1, 
				i;
		
		setC((dest & 1) == 1);
		
		for (i=0; i<src; i++) {
			mask |= (mask << 1);
		}
		
		workspace = mask & dest;
		dest &= (0xFFFFFFFF ^ mask);
		
		java.lang.Integer.reverse(workspace);
		
		dest |= workspace;
		
		this.writeResult(dest);
		setZ(dest == 0);
	}
}