package Poker;

public enum Suit{
	HEART("Hearts"),
	CLUB("Clubs"),
	SPADE("Spades"),
	DIAMOND("Diamonds");
	
	private String suit;
	private Suit(String suit){
		this.suit = suit;
	}
}