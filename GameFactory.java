package net.sourceforge.javapoker.server;

/**
 * @author Martin Cavanagh
 *
 */

import net.sourceforge.javapoker.server.gametypes.*;
import net.sourceforge.javapoker.server.exceptions.*;

import java.util.*;

public class GameFactory {
	public static final int TEXAS_HOLDEM = 1;
	public static final int SEVEN_CARD_STUD = 2;
	
	public static HashMap GAME_TYPE_NAMES;

	public GameFactory () {
		GAME_TYPE_NAMES = new HashMap();
		GAME_TYPE_NAMES.put(new Integer(TEXAS_HOLDEM), "Texas Holdem");
		GAME_TYPE_NAMES.put(new Integer(SEVEN_CARD_STUD), "Seven Card Stud");
	}

	public AbstractPokerGame createGame(GameOptionsHash options) throws InvalidGameTypeException {

		AbstractPokerGame game;
		
		Integer gametype = (Integer)options.get(GameOptionsHash.GAME_TYPE);

		switch (gametype.intValue()) {
			case TEXAS_HOLDEM:
				game = new TexasHoldem(options);
				break;
				
//			case SEVEN_CARD_STUD:
//				game = new SevenCardStud(options);
//				break;
			default:
				throw new InvalidGameTypeException("No valid game type was given");
		}
		
		
		return game;		
	}

}
