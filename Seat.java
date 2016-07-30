
package net.sourceforge.javapoker.server;

/**
 * @author Martin Cavanagh
 *
 */

import net.sourceforge.javapoker.server.exceptions.*;

public class Seat {
	private boolean empty;
	private Player occupant;
	
	public Seat() {
		empty = true;
	}
	
	public synchronized void setOccupant(Player p) {
		occupant = p;
		empty = false;
	}
	
	public boolean isEmpty() {
		return empty;
	}
	
	public synchronized Player getOccupant() throws EmptySeatException {
		if (empty) {
			throw new EmptySeatException("awef");
		}
		else {
			return occupant;
		}
	}

	public void makeAvailable() {
		empty = true;
	}

}
