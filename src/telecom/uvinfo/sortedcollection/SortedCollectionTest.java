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
		assertEquals(0, c.size());
	}

	@Test
	public void testMergeSortOne() {
		c.addElement(42);
		assertEquals(1, c.size());
		assertEquals(42, c.getElement(0));

		c.mergeSort();
		assertEquals(1, c.size());
		assertEquals(42, c.getElement(0));
	}

	@Test
	public void testMergeSortTwo() {
		c.addElement(51);
		c.addElement(42);
		assertEquals(2, c.size());
		assertEquals(51, c.getElement(0));
		assertEquals(42, c.getElement(1));

		c.mergeSort();
		assertEquals(2, c.size());
		assertEquals(42, c.getElement(0));
		assertEquals(51, c.getElement(1));
	}

	@Test
	public void testMergeSortAlreadySorted() {
		c = new SortedCollection(inOrderIntegers(1000));
		assertEquals(1000, c.size());
		assertTrue(c.isSorted());

		c.mergeSort();
		assertEquals(1000, c.size());
		assertTrue(c.isSorted());
	}

	@Test
	public void testMergeSortReversed() {
		c = new SortedCollection(reverse(inOrderIntegers(1000)));
		assertEquals(1000, c.size());
		assertFalse(c.isSorted()); // unless we are REALLY unlucky

		c.mergeSort();
		assertEquals(1000, c.size());
		assertTrue(c.isSorted());
	}

	@Test
	public void testMergeSortShuffled() {
		c = new SortedCollection(shuffle(inOrderIntegers(1000)));
		assertEquals(1000, c.size());
		assertFalse(c.isSorted());

		c.mergeSort();
		assertEquals(1000, c.size());
		assertTrue(c.isSorted());
	}

	@Test
	public void testMergeSortRoughShuffled() {
		c = new SortedCollection(roughShuffle(inOrderIntegers(1000), 20));
		assertEquals(1000, c.size());
		assertFalse(c.isSorted());

		c.mergeSort();
		assertEquals(1000, c.size());
		assertTrue(c.isSorted());
	}

	@Test
	public void testInsertSortEmpty() {
		c.mergeSort();
		assertEquals(0, c.size());
	}

	@Test
	public void testInsertSortOne() {
		c.addElement(42);
		assertEquals(1, c.size());
		assertEquals(42, c.getElement(0));

		c.insertSort();
		assertEquals(1, c.size());
		assertEquals(42, c.getElement(0));
	}

	@Test
	public void testInsertSortTwo() {
		c.addElement(51);
		c.addElement(42);
		assertEquals(2, c.size());
		assertEquals(51, c.getElement(0));
		assertEquals(42, c.getElement(1));

		c.insertSort();
		assertEquals(2, c.size());
		assertEquals(42, c.getElement(0));
		assertEquals(51, c.getElement(1));
	}

	@Test
	public void testInsertSortAlreadySorted() {
		c = new SortedCollection(inOrderIntegers(1000));
		assertEquals(1000, c.size());
		assertTrue(c.isSorted());

		c.insertSort();
		assertEquals(1000, c.size());
		assertTrue(c.isSorted());
	}

	@Test
	public void testInsertSortReversed() {
		c = new SortedCollection(reverse(inOrderIntegers(1000)));
		assertEquals(1000, c.size());
		assertFalse(c.isSorted());

		c.insertSort();
		assertEquals(1000, c.size());
		assertTrue(c.isSorted());
	}

	@Test
	public void testInsertSortShuffled() {
		c = new SortedCollection(shuffle(inOrderIntegers(1000)));
		assertEquals(1000, c.size());
		assertFalse(c.isSorted());
		
		c.insertSort();
		assertEquals(1000, c.size());
		assertTrue(c.isSorted());
	}

	@Test
	public void testInsertSortRoughShuffled() {
		c = new SortedCollection(roughShuffle(inOrderIntegers(1000), 20));
		assertEquals(1000, c.size());
		assertFalse(c.isSorted());
		
		c.insertSort();
		assertEquals(1000, c.size());
		assertTrue(c.isSorted());
	}
}
