package place;

import static org.junit.Assert.*;

import org.junit.Test;

import components.HTMLComponent;

//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;



public class TestPlaceLinkedList {
//	PlaceLinkedList list1;

	@Test
	public void testAddN01(){
		PlaceLinkedList list1 = new PlaceLinkedList();

		assertEquals("[]", list1.toString());
	}
	
	@Test
	public void testAddN02(){
		PlaceLinkedList list1 = new PlaceLinkedList();
		HTMLComponent HTMLPlaceHolder = new HTMLComponent(null, null, null);
		list1.addAtBeginning(HTMLPlaceHolder, 3);
		list1.addAtBeginning(HTMLPlaceHolder, 4);
		list1.addAtEnd(HTMLPlaceHolder, 10);
		list1.addAtBeginning(HTMLPlaceHolder, 13);
		assertEquals("[13, 4, 3, 10] first=[13]", list1.toString());
	}
	
	@Test
	public void testSortN01(){
		PlaceLinkedList list1 = new PlaceLinkedList();
		HTMLComponent HTMLPlaceHolder = new HTMLComponent(null, null, null);
		list1.addAtBeginning(HTMLPlaceHolder, 3);
		list1.addAtBeginning(HTMLPlaceHolder, 4);
		list1.addAtEnd(HTMLPlaceHolder, 10);
		list1.addAtBeginning(HTMLPlaceHolder, 13);
		System.out.println(list1);
		list1.sort();
		System.out.println(list1);
		assertEquals("[13, 10, 4, 3] first=[13]", list1.toString());
	}
}
