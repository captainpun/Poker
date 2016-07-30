package Poker;
import java.util.ArrayList;
/**
 * assigns the amount of money that can be won for a given pot.
 * seatsInPot will determine which seats are elligible for winning a pot, since the seats hold the information on the cards.
 * @author edmon.moren
 *
 */
public class Pot {
	private ArrayList<Seat> SeatsInPot;
	private int amount;
	
	Pot(int amount, Player[] players){
		this.amount = amount;
		
		for (Player p: players){
			playersInPot.add(p);
		}
	}
	
	// need to be able to remove a player from the pot if they fold
	public void removePlayer(Player p){
		for (Player r: playersInPot){
			if (p == r){
				playersInPot.remove(r);
				return;
			}
		}
	}

}
