package Poker;

import java.util.ArrayList;

public class Deck {
	private ArrayList<Card> deck;

	Deck(){
		deck = new ArrayList();
		
		for(Rank r: Rank.values()){
			for (Suit s: Suit.values()){
				deck.add(new Card(r, s));
			}
		}
	}

	public void printDeck(){
		for (Card c: deck){
			c.printCard();
		}
	}

}
