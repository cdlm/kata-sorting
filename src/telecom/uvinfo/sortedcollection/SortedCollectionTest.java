package telecom.uvinfo.sortedcollection;

import static telecom.uvinfo.sortedcollection.Utils.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SortedCollectionTest {
	SortedCollection c;

	@Before
	public void setUp() {
		c = new SortedCollection(10);
	}

	@Test
	public void testMergeSortEmpty() {
		c.mergeSort();
		assertEquals("[]", c.toString());
	}

	@Test
	public void testMergeSortOne() {
		c.addElement(42);
		c.mergeSort();
		
		assertEquals("[42]", c.toString());
	}

	@Test
	public void testMergeSortTwo() {
		c.addElement(51);
		c.addElement(42);
		c.mergeSort();
		
		assertEquals("[42,51]", c.toString());
	}

	@Test
	public void testMergeSortAlreadySorted() {
		c = new SortedCollection(inOrderIntegers(1000));
		c.mergeSort();

		assertTrue(c.isSorted());
	}

	@Test
	public void testMergeSortReversed() {
		c = new SortedCollection(reverse(inOrderIntegers(1000)));
		c.mergeSort();

		assertTrue(c.isSorted());
	}
	
	@Test
	public void testMergeSortShuffled() {
		c = new SortedCollection(shuffle(inOrderIntegers(1000)));
		c.mergeSort();

		assertTrue(c.isSorted());
	}
	
	@Test
	public void testMergeSortRoughShuffled() {
		c = new SortedCollection(roughShuffle(inOrderIntegers(1000), 20));
		c.mergeSort();

		assertTrue(c.isSorted());
	}
}
