package Poker;

public enum Rank { 
	TWO(2, "Two"), 
	THREE(3, "Three"), 
	FOUR(4, "Four"), 
	FIVE(5, "Five"), 
	SIX(6, "Six"), 
	SEVEN(7, "Seven"), 
	EIGHT(8, "Eight"), 
	NINE(9, "Nine"), 
	TEN(10, "Ten"), 
	JACK(11, "Jack"), 
	QUEEN(12, "Queen"), 
	KING(13, "King"),
	ACE(14, "Ace");
	
	
	private int rank;
	private String name;
	
	Rank(int rank, String name){
		this.rank = rank;
		this.name= name;
	}
	
	public int getRankVal(){
		return this.rank;
	}
	public String getRankName(){
		return this.name;
	}
}
