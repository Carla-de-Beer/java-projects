
public class Matrix {

	public static int[][] addMatrices(int[][] m1, int[][] m2) {
		int[][] result = new int[m1.length][m2.length];

		for (int i = 0; i < m1.length; ++i) {
			for (int j = 0; j < m2[0].length; ++j) {
				result[i][j] = m1[i][j] + m2[i][j];
			}
		}

		return result;
	}

	public static int[][] multMatrices(int[][] m1, int[][] m2) {
		int[][] result = new int[m1.length][m2.length];

		for (int i = 0; i < m1.length; ++i) {
			for (int j = 0; j < m2[0].length; ++j) {
				for (int k = 0; k < m2.length; ++k) {
					result[i][j] += m1[i][k] * m2[k][i];
				}
			}

		}

		return result;
	}

	public static void transposeMatrix(int[][] m) {
		int temp = 0;
		for (int i = 0; i < m.length; ++i) {
			for (int j = i; j < m[0].length; ++j) {
				temp = m[i][j];
				m[i][j] = m[j][i];
				m[j][i] = temp;
			}
		}
	}

	public static boolean isMatrixIdentity(int[][] m) {
		for (int i = 0; i < m.length; ++i) {
			for (int j = i; j < m[0].length; ++j) {
				if (i == j && m[i][j] != 1) {
					return false;
				} else if (i != j && m[i][j] != 0) {
					return false;
				}
			}
		}
		return true;

	}

	public static void printLowerMatrix(int[][] m) {
		for (int i = 0; i < m.length; ++i) {
			for (int j = 0; j < m[0].length; ++j) {
				if (j < m[0].length - (i + 1)) {
					System.out.print(" ");
				} else
					System.out.print(m[i][j]);
			}
			System.out.println();
		}
	}

	public static void printUpperMatrix(int[][] m) {
		for (int i = 0; i < m.length; ++i) {
			for (int j = 0; j < m[0].length; ++j) {
				if (j < m.length - i) {
					System.out.print(m[i][j]);
				} else
					System.out.print(" ");
			}
			System.out.println();
		}
	}
}
