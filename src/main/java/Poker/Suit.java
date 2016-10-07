package Poker;

public enum Suit{
	HEART(1, "Hearts"),
	CLUB(2, "Clubs"),
	SPADE(3, "Spades"),
	DIAMOND(4, "Diamonds");
	
	private int suit;
	private String name;
	private Suit(int suit, String n){
		this.suit = suit;
		this.name = n;
	}
	
	public int getSuitVal(){
		return this.suit;
	}
	public String getSuitName(){
		return this.name;
	}
}