package com.elevendustries.simpleprop.instr;

import com.elevendustries.simpleprop.COG;

public class Rdwrlong extends Instruction {
	
	public final int cycles = 8;

	public Rdwrlong(COG cog) {
		super(cog);
		// TODO Auto-generated constructor stub
	}
	
	protected void execute() {
		
		if (cog.hubAccess()) {	// If our cog has hub access, then perform operation
			
			if (this.getRFlag() == 0) { // WRLONG
				
				cog.hub.wrlong(this.getSource(), this.getDestination());
				
				setZ(false);
				setC(false);
				
			} else {		// RDLONG
				
				writeResult(cog.hub.rdlong(this.getSource()));
				
				setC((this.getDestination() == 0));
				setZ(false);
			}
			
		} else {	// otherwise we continue to wait
			count--;
		}
	}
}