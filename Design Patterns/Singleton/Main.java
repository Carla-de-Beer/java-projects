public class Main {

	public static void main(String[] args) {

		Singleton instance = Singleton.getInstance();

		System.out.println(instance.getVariable());

	}
}
