package place;

import java.util.Collections;
import java.util.Comparator;


import components.HTMLComponent;

public class PlaceLinkedList {
	PlaceNode first;

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
			System.out.println("got to getHTML");
			return this.placeHTML;
		}
	}

	public PlaceLinkedList() {
		this.first = null;
	}

	public void addAtBeginning(HTMLComponent placeHTML, int score) {
		this.first = new PlaceNode(placeHTML, score, this.first);
		System.out.println("got to Ccc");
		return;
	}

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
	
	public HTMLComponent getHTMLAtIndex(int ind) {
		if (ind > this.length()) {
			return null;
		}

		PlaceNode current = this.first;
		for (int i = 0; i < ind; i++) {
			current = current.next;
		}
		System.out.println(current);
		System.out.println(current.getHTML());
		return current.getHTML();
	}

	public int length() {
		PlaceNode current = this.first;
		int count = 0;
		while (current != null) {
			count++;
			current = current.next;
		}
		return count;
	}
	
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

	 public void sort() {
			System.out.println("got to s");
		 this.first = sortHelper(this.first);
			System.out.println("got to sa");
		 return;
	 }

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
