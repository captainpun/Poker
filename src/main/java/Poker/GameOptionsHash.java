package net.sourceforge.javapoker.server;

import java.util.HashMap;

/**
 * @author Martin Cavanagh
 *
 */
public class GameOptionsHash extends HashMap {

	public static final String WAITING_ALLOWED = "waiting-allowed";
	public static final String ACES = "ace-value";
	public static final String GAME_TYPE = "game-type";
	public static final String LOW_BET = "low-bet";
	public static final String HIGH_BET = "high-bet";
	public static final String NUM_SEATS = "num-seats";

	public GameOptionsHash() {
		super();
	}
}
