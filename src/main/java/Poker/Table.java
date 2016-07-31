package Poker;

import java.util.ArrayList;

/**
 * Table is the class that will hold the community cards, and the list of players
 * 
 *can deal cards to a player, or deal community cards
 *
 * @author edmon.moren
 *
 */
public class Table {

	private Deck deck;
	private Card[] communityCards;
	private ArrayList<Player> players;


	public Table(){
		deck = new Deck(true);
		communityCards = new Card[5];
		players = new ArrayList<Player>();
	}


	public void getCommunityCard(Card c) throws CommunityCardsFullException{
		int pos = 0;
		while(pos < 5){
			if (communityCards[pos] != null){
				pos++;
			} else{
				//if the position in the array is empty, put the new card there.
				// exit the function
				communityCards[pos] = c;
				return;
			}
		}
		//if the function leaves the while loop, it means that the array holding the community cards is full
		// throw an exception
		throw new CommunityCardsFullException(pos, c);
	}
	
	public void dealHoleCard(Player p){
		try{
			p.getHoleCard(deck.dealCard() );
		}catch(HoleCardsFullException e){
			System.out.println("Player already has " + e.getNumCards() + "cards in the hole");
			System.out.print("Cannot deal card: ");
			e.printCardToAdd();
			e.printStackTrace();
		}
	}

	public void dealCommunityCard(){
		try {
			this.getCommunityCard(deck.dealCard() );
		} catch (CommunityCardsFullException e){
			System.out.println("Table already has " + e.getNumCards() + " community cards");
			System.out.print("Cannot deal community card: ");
			e.printCard();
			e.printStackTrace();
		}
	}
	
	public void printCommunityCards(){
		System.out.println("Community Cards: ");
		for(Card c : communityCards){
			if(c != null) c.printCardName();
		}
	}
	
	public void addPlayer(Player p){
		if(players.size() < 10){
			players.add(p);
		}else{
			System.out.println("there are too many players at the table");
		}
	}
	
	public void removePlayer(Player rplayer){
		for(int i = 0; i < players.size(); i++){
			if (players.get(i).equals(rplayer) ){
				players.remove(i);
			}
		}
	}
}

@SuppressWarnings("serial")
class CommunityCardsFullException extends Exception{
	private int numCards;
	private Card card;

	public CommunityCardsFullException(int numCards, Card c){
		this.numCards = numCards;
		this.card = c;
	}

	public int getNumCards(){
		return numCards;
	}
	public void printCard(){
		card.printCardName();
	}


}
