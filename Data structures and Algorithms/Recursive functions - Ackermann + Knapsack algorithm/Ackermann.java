// Carla de Beer
// Ackermann function
// Date created: 17.02.2014

public class Ackermann {

	public int ackermann(int n, int m) {
		if (n == 0)
			return m + 1;
		else if (m == 0 && n > 0) {
			return ackermann(n - 1, 1);

		} else {
			return ackermann((n - 1), ackermann(n, m - 1));
		}
	}

}
