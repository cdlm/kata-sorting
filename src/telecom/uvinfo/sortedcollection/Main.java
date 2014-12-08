package telecom.uvinfo.sortedcollection;

public class Main {

	public static void main(String[] args) {
		int[] dataSizes = { 10, 100, 1000, 10000 };

		System.out.printf("algorithm,data");
		for (int howMany : dataSizes)
			System.out.printf(",N=%d", howMany);

		System.out.printf("\nmergeSort,in order integers");
		for (int howMany : dataSizes) {
			int[] inOrderIntegers = Utils.inOrderIntegers(howMany);
			long nanos = benchMergeSort(inOrderIntegers, 100);
			System.out.printf(",%d", nanos);
		}

		System.out.printf("\nmergeSort,reversed integers");
		for (int howMany : dataSizes) {
			long nanos = benchMergeSort(
					Utils.reverse(Utils.inOrderIntegers(howMany)), 100);
			System.out.printf(",%d", nanos);
		}

		System.out.printf("\nmergeSort,fully shuffled");
		for (int howMany : dataSizes) {
			long nanos = benchMergeSort(
					Utils.shuffle(Utils.inOrderIntegers(howMany)), 100);
			System.out.printf(",%d", nanos);
		}

		System.out.printf("\nmergeSort,partly shuffled");
		for (int howMany : dataSizes) {
			long nanos = benchMergeSort(
					Utils.roughShuffle(Utils.inOrderIntegers(howMany), 100),
					100);
			System.out.printf(",%d", nanos);
		}

		System.out.printf("\ninsertSort,in order integers");
		for (int howMany : dataSizes) {
			int[] inOrderIntegers = Utils.inOrderIntegers(howMany);
			long nanos = benchInsertSort(inOrderIntegers, 100);
			System.out.printf(",%d", nanos);
		}

		System.out.printf("\ninsertSort,reversed integers");
		for (int howMany : dataSizes) {
			long nanos = benchInsertSort(
					Utils.reverse(Utils.inOrderIntegers(howMany)), 100);
			System.out.printf(",%d", nanos);
		}

		System.out.printf("\ninsertSort,fully shuffled");
		for (int howMany : dataSizes) {
			long nanos = benchInsertSort(
					Utils.shuffle(Utils.inOrderIntegers(howMany)), 100);
			System.out.printf(",%d", nanos);
		}

		System.out.printf("\ninsertSort,partly shuffled");
		for (int howMany : dataSizes) {
			long nanos = benchInsertSort(
					Utils.roughShuffle(Utils.inOrderIntegers(howMany), 100),
					100);
			System.out.printf(",%d", nanos);
		}
	}

	public static long benchMergeSort(int[] unsortedData, int runs) {
		long totalNanos = 0;

		int runsToDo = runs;
		while (runsToDo-- > 0) {
			SortedCollection guineapig = new SortedCollection(
					unsortedData.clone());
			long start = System.nanoTime();
			guineapig.mergeSort();
			totalNanos += System.nanoTime() - start;
		}
		return totalNanos / runs;
	}

	public static long benchInsertSort(int[] unsortedData, int runs) {
		long totalNanos = 0;

		int runsToDo = runs;
		while (runsToDo-- > 0) {
			SortedCollection guineapig = new SortedCollection(
					unsortedData.clone());
			long start = System.nanoTime();
			guineapig.insertSort();
			totalNanos += System.nanoTime() - start;
		}
		return totalNanos / runs;
	}
}
