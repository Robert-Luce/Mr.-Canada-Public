package place;

import static org.junit.Assert.*;

import org.junit.Test;

import components.HTMLComponent;

//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;



public class TestPlaceLinkedList {

	private static int testsPassed;
	private static int numberOfTests;

//	@BeforeClass
//	public static void oneTimeSetUp() {
//		testsPassed = 0;
//		numberOfTests = 0;
//	} // oneTimeSetUp
//
//	@AfterClass
//	public static void oneTimeTearDown() {
//		String className = TestDoubleList.class.getSimpleName();
//		RunAllTests.outputResults(testsPassed, numberOfTests, className);
//	} // oneTimeTearDown

	// --------------------------------------------
	// JUnit Tests
	// --------------------------------------------

	@Test
	public void testAddN01(){
		PlaceLinkedList list1 = new PlaceLinkedList();

		numberOfTests++;
		assertEquals("[]", list1.toString());
		testsPassed++;
	}
	
	@Test
	public void testAddN02(){
		PlaceLinkedList list1 = new PlaceLinkedList();
		HTMLComponent HTMLPlaceHolder = new HTMLComponent(null, null, null);
		list1.addAtBeginning(HTMLPlaceHolder, 3);
		list1.addAtBeginning(HTMLPlaceHolder, 4);
		list1.addAtEnd(HTMLPlaceHolder, 10);
		list1.addAtBeginning(HTMLPlaceHolder, 13);
		numberOfTests++;
		assertEquals("[13, 4, 3, 10] first=[13]", list1.toString());
		testsPassed++;
	}
}

