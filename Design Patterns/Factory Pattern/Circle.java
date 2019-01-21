
public class Circle implements Shape {
	private static String ID = "T-001";
	private int radius;

	Circle(int radius) {
		this.radius = radius;
	}

	static {
		ShapeFactory.registerProduct(ID, new Circle(25));
		System.out.println("Register Triangle with ID: " + ID + ".");
		ShapeFactory.printMessage(ID);
		ShapeFactory.calculateArea(ID);
		ShapeFactory.calculateCircumference(ID);
	}

	public Circle createProduct() {
		return new Circle(this.radius);
	}

	public void printMessage() {
		System.out.println("Circle radius: " + radius + "mm.");
	}

	public void calculateArea() {
		double area = Math.PI * radius * radius;
		System.out.println("Circle area: " + area + "mm^2.");
	}

	public void calculateCircumference() {
		double circumference = 2 * Math.PI * radius;
		System.out.println("Square circumference: " + circumference + "mm.");
	}
}