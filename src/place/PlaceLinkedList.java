package place;

import java.util.Collections;
import java.util.Comparator;


import components.HTMLComponent;
/**
 * @author leonemm, walindqg
 */
public class PlaceLinkedList {
	private PlaceNode first;

	/**
	 * @author walindqg
	 */
	private class PlaceNode {
		private HTMLComponent placeHTML;
		private int score;
		public PlaceNode next;

		public PlaceNode(HTMLComponent placeHTML, int score, PlaceNode next) {
			this.placeHTML = placeHTML;
			this.score = score;
			this.next = next;
		}

		public int getScore() {
			return this.score;
		}

		public HTMLComponent getHTML() {
			return this.placeHTML;
		}
	}

	/**
	 * ensures: constructs the PlaceLinkedList
	 */
	public PlaceLinkedList() {
		this.first = null;
	}
	
	/**
	 * ensures: adds a node at the beginning of the list
	 * @param placeHTML - the placeHTML to be put in the new node
	 * @param score - the value to be put in the new node
	 */
	public void addAtBeginning(HTMLComponent placeHTML, int score) {
		this.first = new PlaceNode(placeHTML, score, this.first);
		return;
	}
	
	/**
	 * ensures: adds a node at the end of the list
	 * @param placeHTML
	 * @param score
	 */
	public void addAtEnd(HTMLComponent placeHTML, int score) {
		if (this.first == null) {
			addAtBeginning(placeHTML, score);
			return;
		}
		PlaceNode current = this.first;
		while (current.next != null) {
			current = current.next;
		}
		current.next = new PlaceNode(placeHTML, score, null);
	}

	/**
	 * ensures: the values of the list are returned as a string
	 * @return - a string containing the values of the list in order
	 */
	public String toString() {
		if (this.first == null) {
			return "[]";
		}
		String result = "[";
		PlaceNode current = this.first;
		while (current.next != null) {
			result += current.getScore() + ", ";
			current = current.next;
		} // end while
		result += current.getScore() + "] first=[" + this.first.getScore() + "]";
		return result;
	}
	
	/**
	 * ensures: allows other classes to access this list's HTMLs based on index
	 * @param ind - desired index
	 * @return - HTML at desired index
	 */
	public HTMLComponent getHTMLAtIndex(int ind) {
		if (ind > this.length()) {
			return null;
		}

		PlaceNode current = this.first;
		for (int i = 0; i < ind; i++) {
			current = current.next;
		}
		return current.getHTML();
	}

	/**
	 * ensures: returns the length of this list
	 * @return - length of this list
	 */
	public int length() {
		PlaceNode current = this.first;
		int count = 0;
		while (current != null) {
			count++;
			current = current.next;
		}
		return count;
	}
	
	/**
	 * purpose: helper function for sortHelper()
	 * @param first - PlaceNode (first node in a list)
	 * @return - middle node of the list 
	 */
    private PlaceNode getMiddle(PlaceNode first) {
        if (first == null)
            return first;
        PlaceNode current = first, current2 = first;
        while (current2.next != null && current2.next.next != null) {
            current = current.next;
            current2 = current2.next.next;
        }
        return current;
    }

    /**
     * ensures: sorts this list in descending order by score
     */
	 public void sort() {
		 this.first = sortHelper(this.first);
		 return;
	 }

	 /**
	  * 
	  * @param first - PlaceNode (first node in a list)
	  * @return - recursive merge of lower and upper half of the list using merge()
	  */
	 private PlaceNode sortHelper(PlaceNode first) {
    	// if list has <= 1 node returns without splitting
        if (first == null || first.next == null) {
	            return first;
        }
	        
        // splits list in half
        PlaceNode middle = getMiddle(first);
        PlaceNode middleNext = middle.next;
        middle.next = null;
	        
        // sorts each half
        PlaceNode lower = sortHelper(first); 
        PlaceNode upper = sortHelper(middleNext); 
	        
        return merge(lower, upper); // combines sorted halves
	    }
	    
	/**
	 * 
	 * @param lower - first node of the lower half of the list 
	 * @param upper - first node of the upper half of the list
	 * @return - combined lower and upper list (in descending order by score)
	 */
    private PlaceNode merge(PlaceNode lower, PlaceNode upper) {
    	if (lower == null) {
            return upper;
        } else if (upper == null) {
            return lower;
        }
	        
        PlaceNode combined = null;
        if (lower.score >= upper.score) {
        	combined = lower;
        	combined.next = merge(lower.next, upper);
        } else {
        	combined = upper;
        	combined.next = merge(lower, upper.next);
        }
        return combined;
    }
    
}
