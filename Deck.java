package Poker;

import java.util.ArrayList;

public class Deck {
	private ArrayList<Card> deck;

	Deck(){
		deck = new ArrayList<Card>();
		createDeck();
	}
	
	Deck(boolean toShuffle){
		this();
		if(toShuffle){
			shuffleDeck();
		}
	}

	public void createDeck(){
		for(Rank r: Rank.values()){
			for (Suit s: Suit.values()){
				//System.out.println(r.getRankName() + " of " + s.getSuitName());
				deck.add(new Card(r, s) );	
			}

		}
	}


	public void printDeck(){
		for (Card card: deck){
			card.printCardName();
		}
	}
	//overloaded the method in case we wanted to get the number form of the cards
	public void printDeck(boolean printNames){
		if (printNames){
			printDeck();
		} else{
			for (Card card: deck){
				card.printCardVal();
			}
		}
	}
	
	public int getSize(){
		return deck.size();
	}

	public Card dealCard(){
		return deck.remove(deck.size()-1);
	}
	
	public void burnCard(){
		deck.remove(deck.size()-1);
	}
	
	public void shuffleDeck(){
		//iterate 3 times the size of the deck
		for (int i = 0; i < deck.size() * 3; i++){
			int firstPos = (int)(Math.random() * deck.size());
			int secondPos = (int)(Math.random() * deck.size());

			Card secondCard = deck.get(secondPos);

			//swap the cards:
			Card tempCard = deck.get(firstPos);
			deck.set(firstPos, deck.get(secondPos));
			deck.set(secondPos, tempCard);
		}
	}
	public void sortDeck(){
		deck.sort(null);
	}
	

	
	public void pNumDeckCards(){
		System.out.println(deck.size() + " cards in the deck");
	}
	
}

