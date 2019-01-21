// Carla de Beer
// Factory pattern, based on the example found at https://www.oodesign.com/factory-pattern.html.
// Created: January 2019

public class Main {

	public static void main(String[] args) {
		try {
			Class.forName("Square");
			System.out.println();
			Class.forName("Circle");
		} catch (ClassNotFoundException any) {
			any.printStackTrace();
		}
	}

}
