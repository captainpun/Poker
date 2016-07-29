package Poker;

public class Card {
	
	public static Rank rank;
	public static Suit suit;
	
	Card(Rank rank, Suit suit){
		this.rank = rank;
		this.suit = suit;
	}
	
	
	public static void main(String[] args){
		System.out.println("Card Values: ");
		for (Rank r: Rank.values() ){
			System.out.println(r.rank + ": " + r.value);
		}
		System.out.println("************");
		System.out.println("Card Suits: ");
		for (Suit s: Suit.values() ){
			System.out.println(s.suit);
		}
	}
	
	public void printCard(){
		System.out.println(this.rank + " of " + this.suit);
	}
}
