package Poker;

import java.util.ArrayList;

public class Deck {
	private ArrayList<Card> deck;
	
	Deck(){
		deck = new ArrayList();
		deck.add(new Card(KING, SPADE));
		}
	}
	
	public void printDeck(){
		for(int i = 0; i < deck.length; i++){
			deck[i].printCard();
		}
	}
	
}
