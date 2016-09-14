import java.io.IOException;

/**
 * Genetic class: implementation of a genetic algorithm to solve the trading
 * optimisation problem.
 * 
 * @author cadebe
 * 
 */
public class Genetics {

	private Strategy strategyChoice;

	/**
	 * Parameterised constructor for Genetics; takes the Strategy type as
	 * argument.
	 * 
	 */
	public Genetics(Strategy strategyChoice) throws IOException {
		this.strategyChoice = strategyChoice;
	}

	/**
	 * Method that implements the genetic algorithm.
	 * 
	 * @throws IOException
	 */
	public void start() throws IOException {
		strategyChoice.printStartInfo();
		strategyChoice.runGA();
	}

	public double getAverage() {
		return strategyChoice.getAverage();
	}

	public String getBestSolution() {
		return strategyChoice.getBestSolution();
	}

	public double getBestFitness() {
		return strategyChoice.getBestFitness();
	}
}
