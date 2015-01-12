package com.elevendustries.simpleprop.instr;

import com.elevendustries.simpleprop.COG;

public class Rdwrbyte extends Instruction {
	
	public final int cycles = 8;

	public Rdwrbyte(COG cog) {
		super(cog);
		// TODO Auto-generated constructor stub
	}
	
	protected void execute() {
		
		if (cog.hubAccess()) {	// If our cog has hub access, then perform operation
			
			if (this.getRFlag() == 0) { // WRBYTE
				
				cog.hub.wrbyte(this.getSource(), this.getDestination());
				
				setZ(((this.getSource() & 0b11) != 0));
				setC(false);
				
			} else {		// RDBYTE
				
				writeResult(cog.hub.rdbyte(this.getSource()));
				
				setC((this.getDestination() == 0));
				setZ(false);
			}
			
		} else {	// otherwise we continue to wait
			count--;
		}
	}
}