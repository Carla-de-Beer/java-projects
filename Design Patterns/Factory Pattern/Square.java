
public class Square implements Shape {
	private static String ID = "S-001";
	private int sideLength;

	Square(int sideLength) {
		this.sideLength = sideLength;
	}

	static {
		ShapeFactory.registerProduct(ID, new Square(15));
		System.out.println("Register Square with ID: " + ID + ".");
		ShapeFactory.printMessage(ID);
		ShapeFactory.calculateArea(ID);
		ShapeFactory.calculateCircumference(ID);
	}

	public Square createProduct() {
		return new Square(this.sideLength);
	}

	public void printMessage() {
		System.out.println("Square sidelength: " + sideLength + "mm.");
	}

	public void calculateArea() {
		int area = sideLength * sideLength;
		System.out.println("Square area: " + area + "mm^2.");
	}

	public void calculateCircumference() {
		int circumference = 4 * sideLength;
		System.out.println("Square circumference: " + circumference + "mm.");
	}

}
