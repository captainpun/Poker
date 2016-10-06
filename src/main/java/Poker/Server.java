package net.sourceforge.javapoker.server;

/**
 * @author Martin Cavanagh
 *
 */

import net.sourceforge.javapoker.server.console.*;
import net.sourceforge.javapoker.server.networking.*;

import net.sourceforge.javapoker.server.gamecomponents.*;
import net.sourceforge.javapoker.server.gametypes.*;
import net.sourceforge.javapoker.server.exceptions.*;

import java.util.*;

public class Server {

	public static void main(String[] args) {
		
		System.out.println("Running Poker Server...");
		
		ServerLogger.init();
		ServerLogger.addEntry("###### Poker Server starting up ######");
		
		ServerConsole console = new ServerConsole();
		ConnectionListener ears = new ConnectionListener();
		
		console.addConsoleCommandListener(ears);

		new Thread(console).start();		
		new Thread(ears).start();
		
		GameFactory gamefactory = new GameFactory();
		
		GameOptionsHash options = new GameOptionsHash();
		options.put(GameOptionsHash.GAME_TYPE, new Integer(GameFactory.TEXAS_HOLDEM));
		options.put(GameOptionsHash.ACES,      new Integer(Deck.ACES_BOTH));
		options.put(GameOptionsHash.LOW_BET,   new Float(2.0f));
		options.put(GameOptionsHash.HIGH_BET,  new Float(4.0f));
		options.put(GameOptionsHash.NUM_SEATS, new Integer(8));

		AbstractPokerGame game;
		
		try {
			game = gamefactory.createGame(options);
		}
		catch (InvalidGameTypeException e) {
			System.out.println(e.getMessage());
			game = null;
		}
		
		new Thread(game).start();
		
		/* TESTING */
		
/*
		Deck deck = new Deck();
		Vector hand = new Vector();
		
		hand.add(deck.dealCard("5H"));
		hand.add(deck.dealCard("3D"));
		hand.add(deck.dealCard("4H"));
		hand.add(deck.dealCard("7S"));
		hand.add(deck.dealCard("6C"));

		Iterator i = hand.iterator();
		System.out.println("Hand to analyse is...");
		while (i.hasNext()) {
			Card c = (Card) i.next();
			System.out.println(c.getLongName() + " (" + c.getShortName() + ")");
		}


		long t = System.currentTimeMillis();
		
		AnalysisResult result = HandAnalyser.analyse(hand);
		
		long timetaken = System.currentTimeMillis() - t;
		
		System.out.println("Analysis took " + timetaken + " ms");
		
		if (result.found()) {
			System.out.println("Found " + result.getDescription());
		}
		else {
			System.out.println("No hand found");
		}
*/
		

	}
}