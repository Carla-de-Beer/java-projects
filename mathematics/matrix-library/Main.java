
public class Main {

	public static void main(String[] args) {
		System.out.println("--------------------------------------------");
		System.out.println("Matrix addition:");
		System.out.println("--------------------------------------------");

		int[][] m1 = { { 1, 3, 4 }, { 2, 4, 3 }, { 3, 4, 5 } };
		int[][] m2 = { { 1, 3, 4 }, { 2, 4, 3 }, { 1, 2, 4 } };

		System.out.println("Matrix m1:");
		printNestedArray(m1);
		System.out.println("Matrix m2:");
		printNestedArray(m2);
		System.out.println("Matrix m1 + m2 = m3:");
		printNestedArray(Matrix.addMatrices(m1, m2));

		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("Matrix multiplication:");
		System.out.println("--------------------------------------------");

		int[][] m3 = { { 1, 1, 1 }, { 2, 2, 2 }, { 3, 3, 3 } };
		int[][] m4 = { { 1, 1, 1 }, { 2, 2, 2 }, { 3, 3, 3 } };

		System.out.println("Matrix m3:");
		printNestedArray(m3);
		System.out.println("Matrix m4:");
		printNestedArray(m4);
		System.out.println("Matrix m3 + m4 = m5:");
		printNestedArray(Matrix.multMatrices(m3, m4));

		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("Matrix transpose:");
		System.out.println("--------------------------------------------");

		System.out.println("Matrix m3:");
		printNestedArray(m3);
		System.out.println("Matrix m3 tramspose:");
		Matrix.transposeMatrix(m3);
		printNestedArray(m3);

		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("Identity matrix check:");
		System.out.println("--------------------------------------------");

		int[][] m5 = { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };
		printNestedArray(m5);
		System.out.println(Matrix.isMatrixIdentity(m5));
		printNestedArray(m3);
		System.out.println(Matrix.isMatrixIdentity(m3));

		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("Print lower diaginal matrix:");
		System.out.println("--------------------------------------------");
		int[][] m6 = { { 1, 5, 3, 6 }, { 5, 7, 9, 8 }, { 9, 0, 5, 4 }, { 4, 1, 8, 7 } };
		printNestedArray(m6);
		Matrix.printLowerMatrix(m6);

		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("Print upper diaginal matrix:");
		System.out.println("--------------------------------------------");
		printNestedArray(m6);
		Matrix.printUpperMatrix(m6);
	}

	private static void printNestedArray(int[][] array) {
		for (int i = 0; i < array.length; ++i) {
			for (int j = 0; j < array[0].length; ++j) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}

}
