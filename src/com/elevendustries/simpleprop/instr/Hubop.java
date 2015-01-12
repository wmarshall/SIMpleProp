package com.elevendustries.simpleprop.instr;

import com.elevendustries.simpleprop.COG;

public class Hubop extends Instruction {		// fuck everything about this instruction
	
	public final int cycles = 8;
	
	public Hubop(COG cog) {
		super(cog);
		// TODO Auto-generated constructor stub
	}

	protected void execute() {
		
		int result;
		
		if (!cog.hubAccess()) {
			count--;
			return;
		}

		switch (instr & 0b111) {
		
		case 0:		// CLKSET -- I don't really use a clock at the moment.
			break;
			
		case 1:		// COGID 			
			this.writeResult(cog.id);
			setZ((cog.id == 0));
			setC(false);
			break;
			
		case 2:		// COGINIT
			result = cog.hub.coginit(this.getDestination());
			setC(result > 7);
			setZ((result == 0));
			this.writeResult(result);

			if (result > 7) {
				this.writeResult(7);
			}
			
			break;
		
		case 3:		// COGSTOP
			result = cog.hub.cogstop(this.getDestination());
			setC(result == 7);
			setZ(result == 0);
			this.writeResult(result);
			break;
			
		case 4:		// LOCKNEW
			result = cog.hub.locknew();
			setC(result > 7);
			setZ(result == 0);
			this.writeResult(result);
			
			if (result > 7) {
				this.writeResult(7);
			}
			break;
			
		case 5:		// LOCKRET
			cog.hub.lockret(this.getDestination());
			this.writeResult(this.getDestination() & 0b111);
			setC((cog.hub.activeLockCount() == 7));
			setZ((this.getDestination() & 0b111) == 0);
			break;
			
		case 6:		// LOCKSET
			this.writeResult(this.getDestination() & 0b111);
			setC((cog.hub.lockset(this.getDestination()) == 1));
			setZ((this.getDestination() & 0b111) == 0);
			break;
			
		case 7:		// LOCKCLR
			this.writeResult(this.getDestination() & 0b111);
			setC((cog.hub.lockclr(this.getDestination()) == 1));
			setZ((this.getDestination() & 0b111) == 0);
			break;
		}
	}
}