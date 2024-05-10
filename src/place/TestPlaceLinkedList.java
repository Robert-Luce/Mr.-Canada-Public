package place;

import static org.junit.Assert.*;

import org.junit.Test;

import components.HTMLComponent;

//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;


/**
 * @author leonemm
 * ensures: tests that class PlaceLinkedList and its methods function as intended
 */
public class TestPlaceLinkedList {

	@Test
	public void testPlaceLinkedListN01(){
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
	public void testSortN03(){
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
	
	@Test
	public void testSortN04(){
		PlaceLinkedList list2 = new PlaceLinkedList();

		list2.sort();
		assertEquals("[]", list2.toString());
	}
	
	@Test
	public void testIdiotProofN05(){
		PlaceLinkedList list1 = new PlaceLinkedList();
		HTMLComponent HTMLPlaceHolder = new HTMLComponent(null, null, null);
		list1.addAtBeginning(HTMLPlaceHolder, 3);
		list1.addAtBeginning(HTMLPlaceHolder, 4);
		list1.addAtEnd(HTMLPlaceHolder, 10);
		list1.addAtBeginning(HTMLPlaceHolder, 13);
		System.out.println(list1);
		for(int i=0; i<10; i++) {
			list1.sort();
		}
		assertEquals("[13, 10, 4, 3] first=[13]", list1.toString());
	}
}
