package Poker;

import java.util.ArrayList;
import java.util.Arrays;

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
	private Card[] communityCards; //list of cards that everyone looks at to determine their handRank
	private ArrayList<Player> players; // number of players at the table (could be spectators)
	private Seat[] seats; // total seats at the table available.
	private ArrayList<Pot> potList; //list of all pots and which players are in the pots.
	private int dealerPos;


	public Table(){
		deck = new Deck(true);
		players = new ArrayList<Player>();
		communityCards = new Card[5];
		buildSeats();
	}

	/**
	 * note that the seat number is not the same as the position of the seat in the array
	 */
	private void buildSeats(){
		int seatNum = 1;
		seats = new Seat[10]; // 10 seats at the table
		for (int i = 0; i < seats.length; i++){
			seats[i] = new Seat(seatNum++);
		}
	}



	private void dealCard(Seat s){
		try{
			s.setHoleCard(deck.dealCard() );
		}catch(HoleCardsFullException e){
			System.out.println("Seat " + s.getSeatNum() + " already has " + e.getNumCards() + "cards in the hole");
			System.out.print("Cannot deal card: ");
			e.printCardToAdd();
			e.printStackTrace();
		}
	}

	/**
	 * go around the table twice to deal the cards.
	 * if seat is not free, it means a player is sitting there, therefore deal
	 */
	public void dealHoleCards(){
		for (Seat s: seats){
			if(!s.isFree()){
				dealCard(s);
			}
		}
		for (Seat s: seats){
			if(!s.isFree()){
				dealCard(s);
			}
		}		
	}

	private void setCommunityCard(Card c, int pos){
		if(communityCards[pos] == null){
			communityCards[pos] = c;
		}
		else{
			System.out.println("Card already placed in that spot");
		}
	}

	public void dealFlop(){
		deck.burnCard();
		for (int i = 0; i < 3; i++){
			setCommunityCard(deck.dealCard(), i);
		}
	}
	
	public void dealTurn(){
		deck.burnCard();
		setCommunityCard(deck.dealCard(), 3);
	}
	
	public void dealRiver(){
		deck.burnCard();
		setCommunityCard(deck.dealCard(), 4);
	}
	
	

	private void printPlayerHoleCards(){
		for(Seat s: seats){
			System.out.print(s +": " + s.getHoleCards()[0] + " and " + s.getHoleCards()[1]);
			System.out.println();
		}
	}

	private void printPlayerHoleCards(Boolean seatedOnly){
		if(seatedOnly){
			for(Seat s: seats){
				if(!s.isFree()){
					System.out.print(s +": " + s.getHoleCards()[0] + " and " + s.getHoleCards()[1]);
					System.out.println();
				}
			}
		} else{
			printPlayerHoleCards();
		}
	}

	public void printPlayerHoleCards(Boolean seatedOnly, Boolean seatAndPlayerName){
		if(!seatAndPlayerName && !seatedOnly){
			printPlayerHoleCards(false);
		} else if(!seatAndPlayerName && seatedOnly){
			printPlayerHoleCards(true);
		} else if (seatAndPlayerName && !seatedOnly){
			for(Seat s: seats){
				System.out.println("seat: " + s.getSeatNum() + " "+ s.getPlayer()+ " has " + s.getHoleCards()[0] + " and " + s.getHoleCards()[1]);
			}
		} else if(seatAndPlayerName && seatedOnly){
			for(Seat s: seats){
				if(!s.isFree()){
					System.out.println("seat: " + s.getSeatNum() + " "+ s.getPlayer()+ " has " + s.getHoleCards()[0] + " and " + s.getHoleCards()[1]);
				}
			}
		}
	}

	public void printCommunityCards(){
		System.out.println("Community Cards: ");
		for(Card c : communityCards){
			if(c != null) c.printCardName();
		}
	}

	public void addPlayer(Player p){
		players.add(p);
	}

	//not sure this works, try it out!
	public void removePlayer(Player rplayer){
		for(int i = 0; i < players.size(); i++){
			if (players.get(i).equals(rplayer) ){
				players.remove(i);
			}
		}
	}

	/**
	 * checks to see if a particular player is seated at the table
	 * @param p
	 * @return
	 */
	private boolean isPSeated(Player p){
		for(Seat s: seats){
			if (s.getPlayer() == p){
				return true;
			}
		}
		return false;
	}

	public void seatPlayer(Player p, Seat s){
		if(!isPSeated(p) && s.isFree()){
			s.addPlayer(p);
		}else{
			System.out.println("Cannot seat player");
		}
	}

	public Seat getNextFreeSeat(){
		for(Seat s: seats){
			if (s.isFree()){
				return s;
			}
		}
		System.out.println("All seats are full");
		return null;
	}

	public void seatPlayers(){
		for(Player p: players){
			seatPlayer(p, getNextFreeSeat() );
		}
	}

	public void printSeatedPlayers(){
		for (Seat s: seats){
			if(!s.isFree()){
				System.out.println("Seat " + s + ": " + s.getPlayer());
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
