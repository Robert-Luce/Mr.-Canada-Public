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
			result += (current.getScore() + ", ");
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
		return current.getHTML();
	}

	private int length() {
		PlaceNode current = this.first;
		int count = 0;
		while (current != null) {
			count++;
			current = current.next;
		}
		return count;
	}

	public PlaceNode sortedMerge(PlaceNode a, PlaceNode b) {
		PlaceNode result = null;

		// Base cases
		if (a == null)
			return b;
		if (b == null)
			return a;

		// Pick either a or b, and recur
		if (a.getScore() > b.getScore()) {
			result = a;
			result.next = sortedMerge(a.next, b);
		} else {
			result = b;
			result.next = sortedMerge(a, b.next);
		}

		return result;
	}

	public PlaceNode mergeSort(PlaceNode h) {

		// Base case : if head is null
		if (h == null || h.next == null) {
			return h;
		}

		// get the middle of the list
		PlaceNode middle = getMiddle(h);
		PlaceNode nextofmiddle = middle.next;

		// set the next of middle node to null
		middle.next = null;

		// Apply mergeSort on left list
		PlaceNode left = mergeSort(h);

		// Apply mergeSort on right list
		PlaceNode right = mergeSort(nextofmiddle);

		// Merge the left and right lists
		PlaceNode sortedlist = sortedMerge(left, right);

		return sortedlist;
	}

	// Utility function to get the middle of the linked list
	public static PlaceNode getMiddle(PlaceNode head) {
		if (head == null)
			return head;

		PlaceNode slow = head, fast = head;

		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		return slow;
	}

//	public void sort(PlaceLinkedList list) {
//		final int n = this.length();
//		if(n<=1) {
//			return;
//		}
//		
//		PlaceLinkedList lower = new PlaceLinkedList();
//		PlaceLinkedList upper = new PlaceLinkedList();
//		
//		// Splitting the list in half
//		PlaceNode current = this.first;
//		for(int i=0; i<n/2; i++) {
//			lower.addAtBeginning(current.getHTML(), current.getScore());
//			current = current.next;
//		}
//		for(int i=0; i<(n-n/2); i++) {
//			upper.addAtBeginning(current.getHTML(), current.getScore());
//		}
//		
//		sort(lower);
//		sort(upper);

//		Collections.sort(this, new Comparator<PlaceNode>() {
//			@Override
//			public int compare(PlaceNode place1, PlaceNode place2) {
//				return place2.getScore()-place1.getScore();
//			}
//		});

	// Sort
//		PlaceNode lowercurrent = lower.first;
//		PlaceNode uppercurrent = upper.first;
//		while(lowercurrent!=null) {
//			if(lowercurrent.getScore()>uppercurrent.getScore()){
//				
//			}
//		}
}
