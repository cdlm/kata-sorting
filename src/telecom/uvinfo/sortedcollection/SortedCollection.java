package telecom.uvinfo.sortedcollection;


public class SortedCollection {

	int[] storage, tmp;
	int size;

	public SortedCollection(int storageSize) {
		storage = new int[storageSize];
		size = 0;
	}

	public SortedCollection(int[] values) {
		storage = values;
		size = values.length;
	}

	public void addElement(int element) {
		storage[size++] = element;
	}

	public int getElement(int index) {
		return storage[index];
	}

	public void mergeSort() {
		tmp = new int[storage.length];
		mergeSort(0, size - 1);
		tmp = null; // tmp is wasted space, throw it away
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

	public int[] getContent() {
		int[] result = new int[size];
		for (int i = 0; i < size; i++)
			result[i] = storage[i];
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

}