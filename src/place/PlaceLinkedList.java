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
		PlaceNode current = this.first;
		String result = "";
		while(current != null) {
			result += current.getScore();
			result += "->";
			current = current.next;
		}
		result += "null";
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
	
	
	
}
