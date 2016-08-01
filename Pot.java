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
	
	Pot(int amount, ArrayList<Seat> seats){
		this.amount = amount;
		
		for (Seat s: seats){
			SeatsInPot.add(s);
		}
	}
	
	// need to be able to remove a player from the pot if they fold
	public void removeSeat(Seat s){
		for (Seat r: SeatsInPot){
			if (s == r){
				SeatsInPot.remove(r);
				return;
			}
		}
	}

}
