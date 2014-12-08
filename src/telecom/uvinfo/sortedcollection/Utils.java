package telecom.uvinfo.sortedcollection;

import java.util.Random;

public class Utils {

	/**
	 * @param howMany
	 *            taille du tableau à créer
	 * @return un tableau d'entiers en ordre croissant
	 */
	public static int[] inOrderIntegers(int howMany) {
		int[] result = new int[howMany];
		for (int i = 0; i < howMany; i++)
			result[i] = i;
		return result;
	}

	/**
	 * @param data
	 *            tableau à renverser
	 * @return une copie du tableau <code>data</code>, dans laquelle l'ordre des
	 *         éléments a été inversé
	 */
	public static int[] reverse(int[] data) {
		int[] reversed = data.clone();
		for (int index = 0; index <= reversed.length / 2; index++) {
			swap(reversed, index, reversed.length - index - 1);
		}
		return reversed;
	}

	/**
	 * @param data
	 *            tableau à mélanger
	 * @return une copie du tableau <code>data</code>, dans laquelle les
	 *         éléments ont été mélangés (selon l'<a
	 *         href="http://en.wikipedia.org/wiki/Fisher-Yates_shuffle"
	 *         >algorithme Fisher-Yates</a>)
	 */
	public static int[] shuffle(int[] data) {
		int[] shuffled = data.clone();
		Random random = new Random();
		for (int index = shuffled.length - 1; index > 0; index--) {
			int other = random.nextInt(index + 1); // dans 0..index
			swap(shuffled, index, other);
		}
		return shuffled;
	}

	/**
	 * @param data
	 *            tableau à mélanger
	 * @param maxDistance
	 *            distance maximale d'échange entre deux éléments du tableau
	 * @return une copie du tableau <code>data</code>, dans laquelle les
	 *         éléments ont été mélangés
	 */
	public static int[] roughShuffle(int[] data, int maxDistance) {
		int[] shuffled = data.clone();
		Random random = new Random();
		for (int index = shuffled.length - 1; index > 0; index--) {
			int pickDistance = Math.min(index, maxDistance);
			int other = index - random.nextInt(pickDistance + 1);
			swap(shuffled, index, other);
		}
		return shuffled;
	}

	protected static void swap(int[] result, int index, int other) {
		int tmp = result[other];
		result[other] = result[index];
		result[index] = tmp;
	}

}
