package telecom.uvinfo.sorting;

import java.util.Random;

public class IntegerSequence {

	private int[] storage, tmp; // tmp is used only during mergeSort()
	private int size;

	public IntegerSequence(int capacity) {
		storage = new int[capacity];
		size = 0;
	}

	public int size() {
		return size;
	}

	public int capacity() {
		return storage.length;
	}

	public void addElement(int element) {
		storage[size++] = element;
	}

	public int getElement(int index) {
		return storage[index];
	}

	public void addIncreasingIntegers(int howMany) {
		for (int i = 1; i <= howMany; i++)
			this.addElement(i);
	}

	public void addDecreasingIntegers(int howMany) {
		for (int i = howMany; i >= 1; i--)
			this.addElement(i);
	}

	/**
	 * Mélange les éléments (selon l'<a
	 * href="http://en.wikipedia.org/wiki/Fisher-Yates_shuffle" >algorithme
	 * Fisher-Yates</a>)
	 * 
	 * @param seed
	 *            Graine d'initialisation du générateur pseudo-aléatoire, pour
	 *            pouvoir reproduire le mélange à l'identique.
	 */
	public void shuffle(long seed) {
		Random random = new Random(seed);
		for (int index = storage.length - 1; index > 0; index--) {
			int other = random.nextInt(index + 1); // other takes a value in
													// 0..index
			this.swap(index, other);
		}
	}

	/**
	 * Mélange <i>partiellement</i> les éléments, en conservant donc
	 * approximativement leur ordre initial.
	 * 
	 * @param seed
	 *            Graine du générateur pseudo-aléatoire.
	 * @param maxDistance
	 *            Distance maximale des échanges d'éléments ; valeurs possibles
	 *            entre 0 (mélange identité) et la taille des données (mélange
	 *            de Fisher-Yates).
	 */
	public void localizedShuffle(long seed, int maxDistance) {
		Random random = new Random(seed);
		for (int index = storage.length - 1; index > 0; index--) {
			int pickDistance = Math.min(index, maxDistance);
			int other = index - random.nextInt(pickDistance + 1);
			this.swap(index, other);
		}
	}

	/**
	 * Échange les éléments stockés aux indices donnés.
	 * 
	 * @param index
	 * @param other
	 */
	public void swap(int index, int other) {
		int tmp = storage[other];
		storage[other] = storage[index];
		storage[index] = tmp;
	}

	/**
	 * Détermine si la séquence est triée.
	 * 
	 * @return true si les éléments sont en ordre croissant.
	 */
	public boolean isSorted() {
		if (size == 0)
			return true;

		int prev = storage[0];
		for (int x : storage) {
			if (x < prev)
				return false;
			prev = x;
		}

		return true;
	}

	public void mergeSort() {
		tmp = new int[storage.length];
		mergeSort(0, size - 1);
		tmp = null; // tmp is now wasted space, throw it away
	}

	public void insertSort() {
		insertSort(size);
	}

	public void mergeSort(int fromIndex, int toIndex) {
		if (fromIndex < toIndex) {
			int midIndex = (fromIndex + toIndex) / 2;
			mergeSort(fromIndex, midIndex);
			mergeSort(midIndex + 1, toIndex);
			merge(fromIndex, midIndex, toIndex);
		}
	}

	protected void merge(int fromIndex, int midIndex, int toIndex) {
		int i1 = fromIndex, i2 = midIndex + 1;

		// merge first into tmp
		for (int indexMerged = fromIndex; indexMerged <= toIndex; indexMerged++) {
			if (i1 <= midIndex && (i2 > toIndex || storage[i1] <= storage[i2])) {
				tmp[indexMerged] = storage[i1];
				i1++;
			} else {
				tmp[indexMerged] = storage[i2];
				i2++;
			}
		}

		// then copy back to storage
		for (int indexMerged = fromIndex; indexMerged <= toIndex; indexMerged++)
			storage[indexMerged] = tmp[indexMerged];
	}

	public void insertSort(int n) {
		int j, p;
		int tp;
		for (p = 1; p < n; p++) {
			tp = storage[p];
			for (j = p; j > 0 && storage[j - 1] > tp; j--) {
				storage[j] = storage[j - 1];
			}
			storage[j] = tp;
		}
	}

	public IntegerSequence copy() {
		IntegerSequence result = new IntegerSequence(size);
		for (int index = 0; index < size; index++)
			result.addElement(storage[index]);
		return result;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer(2 * size + 1);
		sb.append("[");
		for (int index = 0; index < size; index++) {
			sb.append(storage[index]);
			if (index < size - 1)
				sb.append(",");
		}
		sb.append("]");
		return sb.toString();
	}

}