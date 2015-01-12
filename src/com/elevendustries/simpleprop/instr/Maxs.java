package com.elevendustries.simpleprop.instr;

import com.elevendustries.simpleprop.COG;

public class Maxs extends Instruction {
	
	public Maxs(COG cog) {
		super(cog);
		// TODO Auto-generated constructor stub
	}
	
	protected void execute() {
		
		int src = getSource(), dest = getDestination();
		
		setC(dest > src);
		setZ(src == 0);
		this.writeResult((dest > src) ? dest : src);
	}
}