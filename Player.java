/*
 * Created on 04-Mar-2003
 *
 */
package net.sourceforge.javapoker.server;

/**
 * @author Martin Cavanagh
 *
 */

import java.util.*;
import net.sourceforge.javapoker.server.gamecomponents.*;

/*
 * Need some clarification on what a 'Player' is.  Is this one person in a
 * given game, or one person connected to the server (and possibly involved
 * in multiple games)?
 */

public class Player {
	public final int ACTIVE = 1;
	public final int INACTIVE = 2;
	public final int WAITINGLIST = 3;
	
	private Vector cards;
	private int status;

	public Vector getCards() {
		return cards;
	}

}
