package net.sourceforge.javapoker.server;

import java.util.*;

import net.sourceforge.javapoker.server.gamecomponents.*;

/**
 * Acts as a comparator, for sorting cards into numerical order
 * 
 * @author Martin Cavanagh
 */

public class CardSorter implements Comparator {
	public static final int ASC = 1;
	public static final int DESC = 2;

	private int order = ASC;
		
	public CardSorter (int order) {
		this.order = order;
	}
		
	public int compare(Object o1, Object o2) {
		Card card1 = (Card)o1;
		Card card2 = (Card)o2;
		
		int answer = 0;
		
		if (this.order == ASC) {
			answer = card1.getDenomination().getValue() - card2.getDenomination().getValue();
		}
		else {
			answer = card2.getDenomination().getValue() - card1.getDenomination().getValue();				
		}
		
		/*
		 * If the denomination was the same, sort based on suit, using the order
		 * Spades, Hearts, Diamonds, Clubs
		 */
		if (answer == 0) {
			
		}
		
		return answer;
	}

	public boolean equals(Object obj) {
	  return obj.equals(this);
	}
		
}
