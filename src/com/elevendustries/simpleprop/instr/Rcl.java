package com.elevendustries.simpleprop.instr;

import com.elevendustries.simpleprop.COG;

public class Rcl extends Instruction {
	
	public Rcl(COG cog) {
		super(cog);
		// TODO Auto-generated constructor stub
	}

	protected void execute() {
		
		int workspace, i, mask;
		
		mask = (cog.cflag == 1) ? 1 : 0;
		workspace = this.getDestination();
		
		setC((this.getDestination() & 0x80000000) == 0x80000000);
		
		for (i=0; i<(this.getSource() & 0x1F); i++) {
			workspace <<= 1;
			workspace |= mask;
		}
		
		setZ(workspace == 0);
		this.writeResult(workspace);
	}
}