package place;

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
			return this.placeHTML;
		}
	}
	
	public PlaceLinkedList() {
		first = null;
	}
	
	public void addAtBeginning(HTMLComponent placeHTML, int score) {
		PlaceNode newBeginning = new PlaceNode(placeHTML, score, this.first);
		first = newBeginning;
	}
	
	public void addAtEnd(HTMLComponent placeHTML, int score) {
		if(this.first == null) {
			addAtBeginning(placeHTML, score);
			return;
		}
		PlaceNode current = this.first;
		while(current.next != null) {
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
			result += (current.getScore() + ", ");
			current = current.next;
		} // end while
		result += current.getScore() + "] first=[" + this.first.getScore() + "]";
		return result;
	}
	
	public HTMLComponent getHTMLAtIndex(int ind) {
		if(ind > this.length()) {
			return null;
		}
		
		PlaceNode current = this.first;
		for(int i = 0; i < ind; i++) {
			current = current.next;
		}
		return current.getHTML();
	}

	private int length() {
		PlaceNode current = this.first;
		int count = 0;
		while(current != null) {
			count++;
			current = current.next;
		}
		return count;
	}
	
	public void sort(PlaceLinkedList list) {
		final int n = this.length();
		if(n<=1) {
			return;
		}
		
		PlaceLinkedList lower = new PlaceLinkedList();
		PlaceLinkedList upper = new PlaceLinkedList();
		
		// Splitting the list in half
		PlaceNode current = this.first;
		for(int i=0; i<n/2; i++) {
			lower.addAtBeginning(current.getHTML(), current.getScore());
			current = current.next;
		}
		for(int i=0; i<(n-n/2); i++) {
			upper.addAtBeginning(current.getHTML(), current.getScore());
		}
		
		sort(lower);
		sort(upper);
		
		// Sort
		PlaceNode lowercurrent = lower.first;
		PlaceNode uppercurrent = upper.first;
		while(lowercurrent!=null) {
			if(lowercurrent.getScore()>uppercurrent.getScore()){
				
			}
		}
		

	}
}
