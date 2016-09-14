import java.io.IOException;

/**
 * HillClimber class: implementation of a basic hill climbing algorithm to solve
 * the trading problem.
 * 
 * @author cadebe
 * 
 */
public class HillClimber {

	private String filePath = "";
	private String solution = "";
	private Trader trader = null;
	private int tally = 0; // keeps track of the number of steps
	private final int STOP = 32;

	/**
	 * Parameterised constructor for Trader; takes the filepath of the input
	 * file as argument.
	 * 
	 * @param filePath_
	 *            String containing the filepath to the input file
	 * @throws IOException
	 */
	public HillClimber(String filePath) throws IOException {
		this.filePath = filePath;
		this.trader = new Trader(this.filePath);
		this.solution = this.trader.getChromosome();
		tally = 0;
	}

	/**
	 * Hill climbing algorithm - iterates through the input solution and
	 * systematically replaces each of the character values with another,
	 * calculates the revised fitness value and compares that with the fitness
	 * value of the input string to check which is better
	 * 
	 * @return String value that outputs the optimised solution; or reports on
	 *         lack of being able to find such solution
	 * @throws IOException
	 */
	public String hillClimb() throws IOException {

		double t_fit = trader.calcFitness();
		System.out.println("Initial solution: " + trader.getChromosome());
		System.out.println("Initial fitness value: " + t_fit);

		int count = 0;

		for (int i = 0; i < solution.length(); ++i) {

			if (count > STOP) {
				System.out.println("No better solution found within " + STOP
						+ " steps.");
				return "No better solution found within 31 steps.";
			}

			if (solution.charAt(i) == 'B') {

				StringBuilder s = new StringBuilder(solution);
				s.setCharAt(i, 'S');

				Trader traderS = new Trader(filePath);

				traderS.setChromosome(s.toString());
				double s_fit = traderS.calcFitness();
				System.out.println(s_fit);
				count++;

				if (s_fit > t_fit) {
					System.out.println("Optimised solution found: "
							+ traderS.getChromosome());
					System.out.println("Optimised fitness value: " + s_fit);
					System.out.println("Number of steps required: " + count);
					tally = count;
					return traderS.getChromosome();
				}

				StringBuilder h = new StringBuilder(solution);
				h.setCharAt(i, 'H');

				Trader traderH = new Trader(filePath);

				traderH.setChromosome(h.toString());
				double h_fit = traderH.calcFitness();
				System.out.println(h_fit);
				count++;

				if (h_fit > t_fit) {
					System.out.println("Optimised solution found: "
							+ traderH.getChromosome());
					System.out.println("Optimised fitness value: " + h_fit);
					System.out.println("Number of steps required: " + count);
					tally = count;
					return traderH.getChromosome();
				}

			} else if (solution.charAt(i) == 'S') {
				StringBuilder b = new StringBuilder(solution);
				b.setCharAt(i, 'B');

				Trader traderB = new Trader(filePath);

				traderB.setChromosome(b.toString());
				double b_fit = traderB.calcFitness();
				System.out.println(b_fit);
				count++;

				if (b_fit > t_fit) {
					System.out.println("Optimised solution found: "
							+ traderB.getChromosome());
					System.out.println("Optimised fitness value: " + b_fit);
					System.out.println("Number of steps required: " + count);
					tally = count;
					return traderB.getChromosome();
				}

				StringBuilder h = new StringBuilder(solution);
				h.setCharAt(i, 'H');

				Trader traderH = new Trader(filePath);

				traderH.setChromosome(h.toString());
				double h_fit = traderH.calcFitness();
				System.out.println(h_fit);
				count++;

				if (h_fit > t_fit) {
					System.out.println("Optimised solution found: "
							+ traderH.getChromosome());
					System.out.println("Optimised fitness value: " + h_fit);
					System.out.println("Number of steps required: " + count);
					tally = count;
					return traderH.getChromosome();
				}

			} else if (solution.charAt(i) == 'H') {
				StringBuilder b = new StringBuilder(solution);
				b.setCharAt(i, 'B');

				Trader traderB = new Trader(filePath);

				traderB.setChromosome(b.toString());
				double b_fit = traderB.calcFitness();
				System.out.println(b_fit);
				count++;

				if (b_fit > t_fit) {
					System.out.println("Optimised solution found: "
							+ traderB.getChromosome());
					System.out.println("Optimised fitness value: " + b_fit);
					System.out.println("Number of steps required: " + count);
					tally = count;
					return traderB.getChromosome();
				}

				StringBuilder h = new StringBuilder(solution);
				h.setCharAt(i, 'H');

				Trader traderH = new Trader(filePath);

				traderH.setChromosome(h.toString());
				double h_fit = traderH.calcFitness();
				System.out.println(h_fit);
				count++;

				if (h_fit > t_fit) {
					System.out.println("Optimised solution found: "
							+ traderH.getChromosome());
					System.out.println("Optimised fitness value: " + h_fit);
					System.out.println("Number of steps required: " + count);
					tally = count;
					return traderH.getChromosome();
				}
			}
		}

		System.out.println("No better solution found.");
		return "No better solution found.";
	}

	/**
	 * Getter method that gets the size of the tally (i.e. step size), for
	 * external use.
	 * 
	 * @return tally integer value
	 */
	public final int getTally() {
		return tally;
	}
}
