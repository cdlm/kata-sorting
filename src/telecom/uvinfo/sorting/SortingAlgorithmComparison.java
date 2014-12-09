package telecom.uvinfo.sorting;

public class SortingAlgorithmComparison {

	public static void main(String[] args) {
		int[] dataSizes = { 10, 100, 1000, 10000, 100000 };

		System.out.printf("algorithm,data");
		for (int howMany : dataSizes)
			System.out.printf(",N=%d", howMany);

		System.out.printf("\nmergeSort,sorted integers");
		for (int howMany : dataSizes) {
			IntegerSequence seq = new IntegerSequence(howMany);
			seq.addIncreasingIntegers(howMany);
			long nanos = benchMergeSort(seq, 100);
			System.out.printf(",%d", nanos);
		}

		System.out.printf("\ninsertSort,sorted integers");
		for (int howMany : dataSizes) {
			IntegerSequence seq = new IntegerSequence(howMany);
			seq.addIncreasingIntegers(howMany);
			long nanos = benchInsertSort(seq, 100);
			System.out.printf(",%d", nanos);
		}

		System.out.printf("\nmergeSort,partly shuffled");
		for (int howMany : dataSizes) {
			long nanos = 0;
			for (int seed = 0; seed < 10; seed++) {
				IntegerSequence seq = new IntegerSequence(howMany);
				seq.addIncreasingIntegers(howMany);
				seq.localizedShuffle(seed, 100);
				nanos += benchMergeSort(seq, 10);
			}
			System.out.printf(",%d", nanos / 10);
		}
		
		System.out.printf("\ninsertSort,partly shuffled");
		for (int howMany : dataSizes) {
			long nanos = 0;
			for (int seed = 0; seed < 10; seed++) {
				IntegerSequence seq = new IntegerSequence(howMany);
				seq.addIncreasingIntegers(howMany);
				seq.localizedShuffle(seed, 100);
				nanos += benchInsertSort(seq, 10);
			}
			System.out.printf(",%d", nanos / 10);
		}

		System.out.printf("\nmergeSort,fully shuffled");
		for (int howMany : dataSizes) {
			long nanos = 0;
			for (int seed = 0; seed < 10; seed++) { // try different (but predictable) seeds
				IntegerSequence seq = new IntegerSequence(howMany);
				seq.addIncreasingIntegers(howMany);
				seq.shuffle(seed);
				nanos += benchMergeSort(seq, 10);
			}
			System.out.printf(",%d", nanos / 10);
		}
		
		System.out.printf("\ninsertSort,fully shuffled");
		for (int howMany : dataSizes) {
			long nanos = 0;
			for (int seed = 0; seed < 10; seed++) { // try different (but predictable) seeds
				IntegerSequence seq = new IntegerSequence(howMany);
				seq.addIncreasingIntegers(howMany);
				seq.shuffle(seed);
				nanos += benchInsertSort(seq, 10);
			}
			System.out.printf(",%d", nanos / 10);
		}

		System.out.printf("\nmergeSort,reversed integers");
		for (int howMany : dataSizes) {
			IntegerSequence seq = new IntegerSequence(howMany);
			seq.addDecreasingIntegers(howMany);
			long nanos = benchMergeSort(seq, 100);
			System.out.printf(",%d", nanos);
		}

		System.out.printf("\ninsertSort,reversed integers");
		for (int howMany : dataSizes) {
			IntegerSequence seq = new IntegerSequence(howMany);
			seq.addDecreasingIntegers(howMany);
			long nanos = benchInsertSort(seq, 100);
			System.out.printf(",%d", nanos);
		}

	}

	public static long benchMergeSort(IntegerSequence original, int runs) {
		long totalNanos = 0;
		int runsToDo = runs;
		while (runsToDo-- > 0) {
			IntegerSequence guineapig = original.copy();
			long start = System.nanoTime();
			guineapig.mergeSort();
			totalNanos += System.nanoTime() - start;
		}
		return totalNanos / runs;
	}

	public static long benchInsertSort(IntegerSequence original, int runs) {
		long totalNanos = 0;
		int runsToDo = runs;
		while (runsToDo-- > 0) {
			IntegerSequence guineapig = original.copy();
			long start = System.nanoTime();
			guineapig.insertSort();
			totalNanos += System.nanoTime() - start;
		}
		return totalNanos / runs;
	}
}
