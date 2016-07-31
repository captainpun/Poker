package Poker;
/*
 * This is the main class for the game, where the main while loop for the rounds will take place etc.
 *
 */
import java.util.ArrayList;


public class MainGame {
	static Table table;
	
	public static void main(String[] args){
		System.out.println("starting game: ");
		table = new Table();
		
		table.dealCommunityCard();
		table.dealCommunityCard();
		table.dealCommunityCard();
		table.dealCommunityCard();
		table.dealCommunityCard();
		table.dealCommunityCard();
		table.printCommunityCards();
	}
}

