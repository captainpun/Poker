package Poker;

public class Card implements Comparable<Card>{
	
	private final Rank RANK;
	private final Suit SUIT;
	
	Card(Rank rank, Suit suit){
		this.RANK = rank;
		this.SUIT = suit;
	}
	
	public void printCardName(){
		System.out.println(RANK.getRankName() + " of " + SUIT.getSuitName());
	}
	public void printCardVal(){
		System.out.println(RANK.getRankVal() + "; " + SUIT.getSuitVal() );
	}
	
	public int getCardRank(){
		return RANK.getRankVal();
	}
	public int getCardSuit(){
		return SUIT.getSuitVal();
	}
	
	public String toString(){
		return RANK.getRankName() + " of " + SUIT.getSuitName();
	}
	
	@Override
	public int compareTo(Card other) {
		return Integer.compare(this.RANK.getRankVal(), other.RANK.getRankVal());
	}
}
