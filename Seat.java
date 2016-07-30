package Poker;
/**
 * I decided to create a seat class for a few reasons:
 * 
 * - There will always be a fixed amounts of seats
 * 
 * - People can select their sear (where they sit at the table)
 * 
 * - It will be easier to implement turns / who is the dealer, 
 * who is the big blind etc since this has more to do with the order of the seating rather than Player attributes
 * 
 * - also, technically, money bet does not go into the pots / side pots until the betting is over for the player. 
 * As such, we should deduct money from a player each time they bet and place it to the seat. The table logic will determine the pots / side pots.
 * 
 * @author edmon.moren
 *
 */
public class Seat {

	private Player p;
	private final int SEATNUM;
	private Card[] holeCards = new Card[2];
	private boolean isAllIn;

	public Seat(int seatNum){
		this.SEATNUM = seatNum;
		p = null; // when creating the seat, set to null;
	}

	public boolean isFree(){
		return p == null;
	}

	public int getSearNum(){
		return this.SEATNUM;
	}

	public void addPlayer(Player p){
		if(this.p == null){
			this.p = p;
		} else{
			System.out.println("There is already a player at this seat");
		}
	}

	public void removePlayer(){
		this.p = null;
	}

	public void getHoleCard(Card c) throws HoleCardsFullException{
		if (holeCards[0] == null){
			holeCards[0] = c;
		}else if (holeCards[1] == null){
			holeCards[1] = c;
		}else{
			throw new HoleCardsFullException(holeCards.length, c);
		}
	}


}
class HoleCardsFullException extends Exception{

	private int numCards;
	private Card cardToAdd;
	public HoleCardsFullException(int numCards, Card c){
		this.numCards = numCards;
		this.cardToAdd = c;
	}

	public int getNumCards(){
		return numCards;
	}

	public void printCardToAdd(){
		cardToAdd.printCardName();
	}
}
