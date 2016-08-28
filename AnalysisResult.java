/*
 * Created on 04-Mar-2003
 *
 */
package net.sourceforge.javapoker.server;

import java.util.*;

/**
 * @author Martin Cavanagh
 *
 * Represents a pair
 * 
 */

public class AnalysisResult {
		private boolean found;
		private Vector hand;
		private String description;
		private String resultKey;
		
		public boolean found() {
			return found;
		}
		
		public Vector getHand() {
			return hand;
		}
		
		public String getDescription() {
			return description;
		}
		
		public String getResultKey() {
			return resultKey;
		}
		
		/**
		 * Creates a new <code>AnalysisResult</code> with the specified hand and
		 * description.
		 * 
		 * @param found Whether a hand was found or not
		 * @param hand The cards which make up that hand
		 * @param description A descriptive name for the hand (eg: "Full house -
		 * Threes full of Kings")
		 * 
		 */
		public AnalysisResult (boolean found, Vector hand, String description) {
			this.found = found;
			this.hand = hand;
			this.description = description;
			this.resultKey = "";
		}
		
		/**
		 * Any of the hand-finding methods can call this version of the
		 * constructor with <code>false</code> if they simply don't find a hand.
		 * 
		 * @param found Whether a hand was found or not.  Should generally be
		 * <code>false</code> if they call this version of the constructor.
		 */
		
		public AnalysisResult (boolean found) {
			this(found, new Vector(), "");
		}
		
	}
