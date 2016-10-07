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


public class TwoPairResult extends AnalysisResult {
	private static final String resultKey = "two-pair";
	private PairResult firstpair;
	private PairResult secondpair;
		
	public TwoPairResult(boolean found, Vector cards, String description, PairResult p1, PairResult p2) {
		super(found, cards, description);
		this.firstpair = p1;
		this.secondpair = p2;
	}
		
	public PairResult getFirstPair() {
		return firstpair;
	}
		
	public PairResult getSecondPair() {
		return secondpair;
	}
}
