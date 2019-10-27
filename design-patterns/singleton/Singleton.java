public class Singleton {

	private static Singleton instance = null;
	private int variable;

	private Singleton() {
		this.variable = 90;
	}

	public static Singleton getInstance() {
		if (instance == null) {
			return new Singleton();
		}
		return instance;
	}

	public int getVariable() {
		return variable;
	}

}
