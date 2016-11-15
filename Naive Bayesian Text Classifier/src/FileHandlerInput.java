
/**
 * Class that creates an object containing the input parameters for the
 * FileHandler class. The input parameters are declared inside the Client.
 * 
 * @author cadebe Created: November 2016
 *
 */

public class FileHandlerInput {

	private String pathA;
	private String pathB;
	private String pathX;
	private String categoryA;
	private String categoryB;
	private char A, B;

	public FileHandlerInput() {
	}

	public void setPathA(String pathA) {
		this.pathA = pathA;
	}

	public final String getPathA() {
		return pathA;
	}

	public void setPathB(String pathB) {
		this.pathB = pathB;
	}

	public final String getPathB() {
		return pathB;
	}

	public void setPathX(String pathX) {
		this.pathX = pathX;
	}

	public final String getPathX() {
		return pathX;
	}

	public void setCategoryA(String categoryA) {
		this.categoryA = categoryA;
	}

	public final String getCategoryA() {
		return categoryA;
	}

	public void setCategoryB(String categoryB) {
		this.categoryB = categoryB;
	}

	public final String getCategoryB() {
		return categoryB;
	}

	public void setCharA(char A) {
		this.A = A;
	}

	public final char getCharA() {
		return A;
	}

	public void setCharB(char B) {
		this.B = B;
	}

	public final char getCharB() {
		return B;
	}
}
