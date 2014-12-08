package telecom.uvinfo.sortedcollection;

public class TriTest {

	public static void main(String[] args) {

		int tailleMax = 10000;
		SortedCollection c1 = new SortedCollection(tailleMax);

		int taille = 900;
		for (int i = 0; i < taille; i++)
			c1.addElement(Math.abs(new java.util.Random().nextInt()) % taille);
		SortedCollection c2 = new SortedCollection(tailleMax);
		// recopie pour comparer le tri du même tableau d'entiers
		for (int i = 0; i < taille; i++)
			c2.addElement(c1.getElement(i));

		System.out.println("Taille du tableau " + taille + " entiers");
		System.out.println("c1 " + c1.toString());
		long start = System.currentTimeMillis();
		c1.mergeSort();
		System.out.println("duree du tri fusion :"
				+ (System.currentTimeMillis() - start) + " mms");
		System.out.println("c1 " + c1.toString());

		System.out.println("c2 " + c2.toString());
		start = System.currentTimeMillis();
		c2.insertSort(taille);
		System.out.println("duree du tri par insertion :"
				+ (System.currentTimeMillis() - start) + " mms");
		System.out.println("c2 " + c2.toString());

	}

}