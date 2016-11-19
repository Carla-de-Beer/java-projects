package classifier;

/**
 * Class that creates an object containing the input parameters for the Bayes
 * class. The input parameters are obtained from the FileHandler class.
 * 
 * @author cadebe Created: November 2016
 *
 */

public class FileHandlerOutput {

	private String[] tokensCatA;
	private String[] tokensCatB;
	private String[] tokensCatX;

	public FileHandlerOutput(String[] tokensCatA, String[] tokensCatB, String[] tokensCatX) {
		this.tokensCatA = new String[tokensCatA.length];
		this.tokensCatB = new String[tokensCatB.length];
		this.tokensCatX = new String[tokensCatX.length];

		copyArray(this.tokensCatA, tokensCatA);
		copyArray(this.tokensCatB, tokensCatB);
		copyArray(this.tokensCatX, tokensCatX);
	}

	private void copyArray(String[] self, String[] other) {
		for (int i = 0; i < other.length; ++i) {
			self[i] = other[i];
		}
	}

	public String[] getTokensCatA() {
		return tokensCatA;
	}

	public String[] getTokensCatB() {
		return tokensCatB;
	}

	public String[] getTokensCatX() {
		return tokensCatX;
	}
}
