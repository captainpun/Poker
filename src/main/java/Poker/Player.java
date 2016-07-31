package Poker;

public class Player {

	private int money;
	private boolean isTurn;

	private Card[] holeCards = new Card[2];

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
