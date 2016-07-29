package Poker;

public class MainGame {
	
	public static void main(String[] args){
		Deck deck = new Deck();
		deck.shuffleDeck();
		/*
		 * uncomment if you want to sort the deck some some reason
		 * I just created the method to make sure my shuffle wasn't randomly
		 * messing around with the cards in the deck
		 * 
		 */
		 //deck.sortDeck();
		 
		deck.printDeck(true);
	}
}

