package com.elevendustries.simpleprop.instr;

import com.elevendustries.simpleprop.COG;

public class Muxnc extends Instruction {
	
	public Muxnc(COG cog) {
		super(cog);
		// TODO Auto-generated constructor stub
	}
	
	protected void execute() {
		
		int result = (cog.cflag == 0) ? this.getDestination() | this.getSource() : this.getDestination() & ((int) INT_MASK ^ this.getSource());
		
		this.writeResult(result);
		setZ(result == 0);
		
		// calculate parity
		for (int i=0; i<32; i++) {
			result = (result >>> 1) ^ (result & 1);
		}
		
		setC(result == 1);
	}
}