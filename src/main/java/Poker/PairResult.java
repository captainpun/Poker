/*
 * Created on 04-Mar-2003
 *
 */
package net.sourceforge.javapoker.server;

/**
 * @author Martin Cavanagh
 *
 */

import java.util.*;

import net.sourceforge.javapoker.server.gamecomponents.*;


public class PairResult extends AnalysisResult {
	private static final String resultKey = "pair";
	private Denomination denom;
	
	public PairResult(boolean found, Vector cards, String description) {
		super(found, cards, description);
		Card anycard = (Card) cards.firstElement();
		this.denom = anycard.getDenomination();
	}

	public Denomination getDenomination() {
		return denom;
	}				
}
