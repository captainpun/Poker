package Poker;
/*
 * This is the main class for the game, where the main while loop for the rounds will take place etc.
 *
 */

public class MainGame {
	static Table table;
	
	public static void main(String[] args){
		table = new Table();
		
		
		table.addPlayer(new Player("Edmon", 10000));
		table.addPlayer(new Player("Chi", 10000));
		table.addPlayer(new Player("Ryan", 10000));
		table.addPlayer(new Player("Padraig", 10000));
		
		
		table.seatPlayers();
		//table.printSeatedPlayers();
		table.dealHoleCards();
		
		table.printPlayerHoleCards(true, true);
		System.out.println("*****");
		
		table.dealFlop();
		table.printCommunityCards();
		System.out.println("*****");
		table.dealTurn();
		table.printCommunityCards();
		System.out.println("*****");
		table.dealRiver();
		table.printCommunityCards();
		System.out.println("*****");
		
	}
}

