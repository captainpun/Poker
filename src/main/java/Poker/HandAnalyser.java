package net.sourceforge.javapoker.server;

/**
 * @author Martin Cavanagh
 *
 * This class is for analysing the cards that a single player is holding, and
 * working out what their possible (and best) hand(s) is/are.
 * 
 * <p>Currently, it assumes that the following hands are valid for any variation
 * of poker, and that they are ranked in the following order (best first):
 *
 * <ul>
 * <li>Straight Flush - a straight with all cards in the same suit</li>
 * <li>4 of a kind - 4 cards of the same denomination (eg: 4 jacks)</li>
 * <li>Full house - 3 of one denomination and 2 of another (eg: 3 tens, 2 fives)</li>
 * <li>Flush - 5 cards in the same suit (eg: 2, 3, 6, 10 and king of clubs)</li>
 * <li>Straight - 5 cards of mixed suit but in consecutive order</li>
 * <li>3 of a kind - 3 cards of the same denomination (eg: 3 jacks)</li>
 * <li>2 Pair - 2 lots of 2 cards with the same denomination (eg: 2 jacks
 * and 2 fives)</li>
 * <li>Pair - 2 cards of the same denomination (eg: 2 jacks)</li>
 * <li>High Card - If they don't have any of the above, their best hand is
 * just their highest single card.</li>
 * </ul>
 * 
 */


/*
 * Notes
 * =====
 * 
 * Probably want to make this a separate Thread later.  For now, I need to see
 * how long it takes to run.
 * 
 */

import java.util.*;

import net.sourceforge.javapoker.server.gamecomponents.*;


public final class HandAnalyser {

	private HandAnalyser () {
	}
	
	/**
	 * Returns the best hand it can find.
	 * 
	 * @return AnalysisResult
	 */
	public static AnalysisResult analyse(Vector cards) {
		// Some optimisations to make searching for hands quicker
		sortNumerically(cards, CardSorter.DESC);

		// Change to 0 instead of 1 if we want to spot 'high cards'.
		if (cards.size() <= 1) {
			return new AnalysisResult(false);
		}

		
	
		/*
		 * Check for each hand in descending order (as long as there are enough
		 * cards to make it worth it).  As soon as you find a hand, return (as
		 * the first hand found should be the best one)
		 */

		// Straight flush
		if (cards.size() >= 5) {
			Vector cds = (Vector)cards.clone();
			AnalysisResult result = findStraightFlush(cds);
			if (result.found()) {
				return result;
			}	
		}

/*
		
		// Four of a kind
		if (cards.size() >= 4) {
			Vector cds = (Vector)cards.clone();
			AnalysisResult result = findFourOfAKind();
			if (result.found()) {
				return result;
			}
		}
		
		// Full house
		if (cards.size() >= 5) {
			AnalysisResult result = findFullHouse();
			if (result.found()) {
				return result;
			}
		}
*/		
		
		// Flush
		if (cards.size() >= 5) {
			Vector cds = (Vector)cards.clone();
			AnalysisResult result = findFlush(cds);
			if (result.found()) {
				return result;
			}
		}

		// Straight
		if (cards.size() >= 5) {
			Vector cds = (Vector)cards.clone();
			AnalysisResult result = findStraight(cds);
			if (result.found()) {
				return result;
			}
		}

		
		// 3 of a kind
		if (cards.size() >= 3) {
			Vector cds = (Vector)cards.clone();
			AnalysisResult result = findThreeOfAKind(cds);
			if (result.found()) {
				return result;
			}
		}
		
		// 2 pair
		if (cards.size() >= 4) {
			Vector cds = (Vector)cards.clone();
			AnalysisResult result = findTwoPair(cds);
			if (result.found()) {
				return result;
			}
		}
		
		
		// Pair
		if (cards.size() >= 2) {
			Vector cds = (Vector)cards.clone();
			AnalysisResult result = findPair(cds);
			if (result.found()) {
				return result;
			}
		}
		
		// If you haven't found a hand yet, you're not going to.
		return new AnalysisResult(false);
	}
	
	
	
	/**
	 * List the cards which the found hand is made up of
	 * 
	 * @param hand
	 * @return String
	 */
	private static String concatShortNames(Vector hand) {
		String string = "";
		Iterator i = hand.iterator();
		
		while (i.hasNext()) {
			Card c = (Card) i.next();
			string = string + c.getShortName() + " ";
		}
		
		return string;
	}
	
	
	/**
	 * Searches for a pair in <code>cards</code>
	 * 
	 * @return AnalysisResult
	 */
	private static PairResult findPair(Vector cds) {
		boolean found = false;
		PairResult a;
		String description = "";

		// Add cards to this vector if they are part of a found hand
		Vector handcards = new Vector();
		
		
		Iterator i = cds.iterator();
		
		// Loop over each card
		while (i.hasNext()) {
			Card c = (Card) i.next();
			
			found = false;
			
			// Loop over each card again, comparing it to the one in the outer loop
			Iterator k = cds.iterator();
			while (k.hasNext()) {
				Card d = (Card)k.next();
				
				// Skip the current card if it's the same one as the one we're comparing it to
				if (d.equals(c)) {
					continue;
				}
				
				// If 2 denominations match, you've got a pair
				if (d.getDenomination().getValue() == c.getDenomination().getValue()) {
					handcards.add(c);
					handcards.add(d);
					found = true;
					description = "a pair of " + c.getDenomination().getPluralLongName().toLowerCase();
					break;
				}
			}
			
			// Can stop looping if you've found a pair already
			if (found == true) {
				break;
			}
		}

		
		a = new PairResult(found, handcards, description);
		return a;		
	}
	
	
	/**
	 * Looks for the hand 'Two Pair' by finding one pair, then another with the
	 * remaining cards.
	 * 
	 * @param cds
	 * @return AnalysisResult
	 */
	private static TwoPairResult findTwoPair(Vector cds) {
		boolean found = false;
		TwoPairResult a;
		String description = "";
		PairResult firstpair = null;
		PairResult secondpair = null;
		
		// Add cards to this vector if they are part of a found hand
		Vector handcards = new Vector();
		
		
		firstpair = findPair(cds);
		if (firstpair.found()) {
			Iterator i = firstpair.getHand().iterator();
			
			/* 
			 * Remove the cards which made up the first pair, and then pass the
			 * remaining cards to findPair() again
			 */
			while (i.hasNext()) {
				Card n = (Card) i.next();
				
				handcards.add(n);
				cds.remove(n);
			}
			
			secondpair = findPair(cds);
			
			if (secondpair.found()) {
				found = true;
				Iterator k = secondpair.getHand().iterator();
				while (k.hasNext()) {
					handcards.add(k.next());
				}
				
				description = "two pair - ";
				Card onefromfirstpair = (Card)firstpair.getHand().firstElement();
				Card onefromsecondpair = (Card)secondpair.getHand().firstElement();
				
				description += onefromfirstpair.getDenomination().getPluralLongName().toLowerCase();
				description += " and ";
				description += onefromsecondpair.getDenomination().getPluralLongName().toLowerCase();
			}
			
			
		}
		
		
		a = new TwoPairResult(found, handcards, description, firstpair, secondpair);
		return a;	
	}
	
	
	/**
	 * Looks for the hand 'Three of a kind' by finding a pair, and then one more
	 * card of the same denomination.
	 * 
	 * @param cds
	 * @return AnalysisResult
	 */
	private static ThreeOfAKindResult findThreeOfAKind(Vector cds) {
		boolean found = false;
		ThreeOfAKindResult a;
		String description = "";

		// Add cards to this vector if they are part of a found hand
		Vector handcards = new Vector();
		
		// First, find a pair
		PairResult apair = findPair(cds);
		if (apair.found()) {
			
			/* 
			 * Remove the cards which made up the first pair, and then pass the
			 * remaining cards to findPair() again
			 */
			handcards.addAll(apair.getHand());
			cds.removeAll(apair.getHand());

		
			// If we've found a pair, search for one more card of the same suit
			Card onefrompair = (Card) handcards.firstElement();
			Iterator k = cds.iterator();
			while (k.hasNext()) {
				Card onemore = (Card) k.next();
				
				if (onemore.getDenomination().getValue() == onefrompair.getDenomination().getValue()) {
					handcards.add(onemore);
					found = true;
					description = "three of a kind - " + onemore.getDenomination().getPluralLongName().toLowerCase();
					break;
				}

			}
		}


		a = new ThreeOfAKindResult(found, handcards, description);
		return a;	
	}
	
	
	
	/**
	 * Looks for the hand 'Straight' by starting at the highest card, then
	 * checking the next to see if it's only 1 away in terms of numerical value
	 * 
	 * @param cds
	 * @return AnalysisResult
	 */
	private static AnalysisResult findStraight(Vector cds) {
		boolean found = false;
		AnalysisResult a;
		String description = "";

		// Add cards to this vector if they are part of a found hand
		Vector handcards = new Vector();
		

		/* If you've got 7 cards in total (for example), and you've already
		 * looped through the cards three times, you know you can't find a
		 * straight, because you have to have 5 consecutively numbered ones
		 * in a row.
		 */
		 
		for (int i = 0; i <= (cds.size() - 5); i++) {
			handcards.clear();
			
			Card c = (Card) cds.get(i);
			handcards.add(c);
			
			int totalinorder = 1;
			for (int k = 1; k < 5; k++) {
				Card d = (Card)cds.get(i + k);
				
				// If the next card is in consecutive order from the last, keep going
				if ((c.getDenomination().getValue() - k) == d.getDenomination().getValue()) {
					handcards.add(d);
					totalinorder++;

					if (totalinorder == 5) {
						found = true;
						Card lowcard = c;
						Card highcard = (Card) handcards.lastElement();
						description = "straight - ";
						description += lowcard.getDenomination().getLongName();
						description += " to ";
						description += highcard.getDenomination().getLongName();
					}

				}
				else {
					break;
				}
			}
			
			if (found == true) {
				break;
			}
		}
		
		
		a = new AnalysisResult(found, handcards, description);
		return a;
	}
	
	
	
	
	/**
	 * Looks for the hand 'Flush', by creating a separate Vector of cards for
	 * each suit, and seeing if any of them are over 5 cards in size.
	 * 
	 * @param cds
	 * @return AnalysisResult
	 */
	private static AnalysisResult findFlush(Vector cds) {
		boolean found = false;
		AnalysisResult a;
		String description = "";

		// Add cards to this vector if they are part of a found hand
		Vector handcards = new Vector();
		
		
		/*
		 * Having a vector for each suit makes checking flushes much easier. Eg:
		 * you can just check the size of each Vector.  If none of them are 5,
		 * you don't have a flush.
		 */
		Vector spades = new Vector();
		Vector hearts = new Vector();
		Vector diamonds = new Vector();
		Vector clubs = new Vector();
		
		Iterator i = cds.iterator();
		
		while (i.hasNext()) {
			Card c = (Card) i.next();
			
			if (c.getSuit().getName().compareTo("spade") == 0)   { spades.add(c); }
			if (c.getSuit().getName().compareTo("heart") == 0)   { hearts.add(c); }
			if (c.getSuit().getName().compareTo("diamond") == 0) { diamonds.add(c); }
			if (c.getSuit().getName().compareTo("club") == 0)    { clubs.add(c); }			
		}
		
		Vector[] suitgroups = {spades, hearts, diamonds, clubs}; 

		for (int s = 0; s < suitgroups.length; s++) {
			Vector v = suitgroups[s];
			if (v.size() >= 5) {
				sortNumerically(v, CardSorter.DESC);
				
				// Incase they have more than 5 in the same suit, get the highest 5
				Card highcard = (Card) v.firstElement();
				for (int h = 0; h < 5; h++) {
					handcards.add(v.elementAt(h));
				}
				
				found = true;
				description = "flush (" + highcard.getSuit().getPlural() + ")";
				description += ", " + highcard.getDenomination().getLongName() + " high";
			}
		}
		
		a = new AnalysisResult(found, handcards, description);
		return a;
	}
	
	
	
	
	/**
	 * Searches for a straight flush in <code>cards</code>
	 * 
	 * @return AnalysisResult
	 */
	private static AnalysisResult findStraightFlush (Vector cds) {
		boolean found = false;
		AnalysisResult a;
		String description = "";

		// Add cards to this vector if they are part of a found hand
		Vector handcards = new Vector();
		
		AnalysisResult straight = findStraight(cds);
		sortNumerically(cds, CardSorter.DESC);  // Incase they became unsorted while finding a straight
		Card lowcard = (Card)cds.lastElement();
		Card highcard = (Card)cds.firstElement();
		
		
		/*
		 * FIXME
		 * 
		 * If it finds a straight, it passes the cards which made up the straight
		 * to the findFlush() method.  If findFlush() is not successful,
		 * it is assumed that there is no straight flush.  Technically,
		 * you could have another straight flush elsewhere in the hand (granted,
		 * you'd need 10 or more cards in your hand for this to be possible, but
		 * it might need checking for, depending on the version of poker being
		 * played).
		 * 
		 */
		
		if (straight.found()) {
			AnalysisResult flush = findFlush(straight.getHand());
			
			if (flush.found()) {
				found = true;
				description = "Straight flush - ";
				description += lowcard.getDenomination().getLongName();
				description += " to ";
				description += highcard.getDenomination().getLongName();
				handcards = flush.getHand();
			}
		}
		
		
		a = new AnalysisResult(found, handcards, description);

		return a;
	}
	
	
	
	/**
	 * Sorts the given Vector of cards by the numerical value of their
	 * denominations.
	 * 
	 * @param cards The cards to sort
	 * @param order Specifies whether to sort in ascending or descending order
	 */
	private static void sortNumerically(Vector cards, int order) {
		Collections.sort(cards, new CardSorter(order));
	}
	
	
	/**
	 * Sorts the given Vector of hands based on their relative
	 * strengths.
	 * 
	 * @param hands
	 * @see HandSorter
	 */
	public static void sortHands (Vector hands) {
		Collections.sort(hands, new HandSorter());
	}
		
}


