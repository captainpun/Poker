package net.sourceforge.javapoker.server;

/**
 * For comparing entire hands to one another, to see which is best.
 * 
 * @author Martin Cavanagh
 */

import java.util.*;

import net.sourceforge.javapoker.server.gamecomponents.*;

public class HandSorter implements Comparator {
	private static HashMap handStrength;
		
	public HandSorter () {
		handStrength = new HashMap();
		handStrength.put("straight-flush", new Integer(90));
		handStrength.put("four-of-a-kind", new Integer(80));
		handStrength.put("full-house", new Integer(70));
		handStrength.put("flush", new Integer(60));
		handStrength.put("straight", new Integer(50));
		handStrength.put("three-of-a-kind", new Integer(40));
		handStrength.put("two-pair", new Integer(30));
		handStrength.put("pair", new Integer(20));
		handStrength.put("high-card", new Integer(10));

	}
		
		
	public int compare(Object o1, Object o2) {
		int answer = 0;
			
		AnalysisResult r1 = (AnalysisResult)o1;
		AnalysisResult r2 = (AnalysisResult)o2;
			
		Integer strength1 = (Integer)handStrength.get(r1.getResultKey());
		Integer strength2 = (Integer)handStrength.get(r2.getResultKey());
			
		// We want to sort them into highest-first order
		answer = strength1.intValue() - strength2.intValue();
			
		// If the hands were the same, sort based on hand-specific criteria
		if (answer == 0) {
				
		}
			
		return answer;
	}

	public boolean equals(Object obj) {
	  return obj.equals(this);
	}
		
}