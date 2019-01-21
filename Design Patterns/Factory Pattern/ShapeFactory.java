import java.util.HashMap;

public class ShapeFactory {
	private static HashMap<String, Shape> registeredProducts = new HashMap<String, Shape>();

	public static void registerProduct(String productID, Shape p) {
		registeredProducts.put(productID, p);
	}

	public static Shape createProduct(String productID) {
		return ((Shape) registeredProducts.get(productID)).createProduct();
	}

	public static void printMessage(String productID) {
		registeredProducts.get(productID).printMessage();
	}

	public static void calculateArea(String productID) {
		registeredProducts.get(productID).calculateArea();
	}

	public static void calculateCircumference(String productID) {
		registeredProducts.get(productID).calculateCircumference();
	}

}
