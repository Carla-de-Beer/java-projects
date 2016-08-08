// Carla de Beer
// 95151835
// COS 314: Project 2
// 20 April 2015
// Genetic Algorithm, and Hill Climber optimiser, to determine the best stock market trader 
// based on historic trading data from a set of four companies.

import java.io.IOException;
import java.util.Scanner;

/**
 * Main output class for the project. The main method offers three options for
 * testing: OPTION 1: sets up the Renko data for the file and tests the traders
 * on this data. OPTION 2: runs the hill climbing algorithm. OPTION 3: runs the
 * genetic algorithm. Input parameters are provided by the user.
 * 
 * @author cadebe
 * 
 */
public class Main {

	public static void main(String[] args) throws IOException {

		try {

			System.out.println("COS 314 PROJECT 2\n");
			System.out.println("There are 3 options to choose from.");

			Scanner scanner = new Scanner(System.in);
			Scanner scannerString = new Scanner(System.in);

			System.out
					.println("\nBefore choosing an option, first enter the dataset file path,\neg. \"agl.dat\":");

			String filePath = scanner.nextLine();

			if (filePath.equals("")) {
				System.out
						.println("We detected an empty filepath. \nThe filepath is defaulting to \"agl.dat\".");
				System.out.println();
				filePath = "agl.dat";
			}

			// ------------------------- Trader test

			System.out
					.println("OPTION 1: Generate the Renko data and test the traders? Y/N");
			char isRenko = scanner.next().charAt(0);

			if (isRenko == 'Y' || isRenko == 'y') {
				System.out
						.println("Enter an integer value for the number of tests required:");
				int num = scanner.nextInt();

				Trader trader1 = new Trader(filePath);
				System.out.println();
				trader1.printRenkoDataInfo();

				for (int i = 0; i < num; ++i) {
					Trader trader = new Trader(filePath);

					try {
						trader.calcFitness();
					} catch (Exception e) {
						System.out.println("Could not calculate fitness.");
					}
					trader.printTradeResult(i + 1);
					System.out.println();
				}

				System.out.println();
				System.out
						.println("Run the program again to try options 2 and 3.");

			} else if (isRenko == 'N' || isRenko == 'n') {

				// ------------------------- Hill climber test

				System.out.println("OPTION 2: Test the hill climber? Y/N");
				char isHillClimb = scanner.next().charAt(0);

				if (isHillClimb == 'Y' || isHillClimb == 'y') {
					System.out
							.println("Enter an integer value for the number of tests required:");
					int num = scanner.nextInt();

					System.out.println();
					for (int i = 0; i < num; ++i) {
						HillClimber hillClimber = new HillClimber(filePath);
						try {
							hillClimber.hillClimb();
						} catch (Exception e) {
							System.out
									.println("Hill climbing could not be executed.");
						}
						System.out.println();
					}

					System.out.println();
					System.out
							.println("Run the program again to try option 3.");

				} else if (isHillClimb == 'N' || isHillClimb == 'n') {

					// ------------------------- Genetic algorithm test

					System.out
							.println("OPTION 3: Test the genetic algorithm? Y/N");
					char isGenetic = scanner.next().charAt(0);

					if (isGenetic == 'Y' || isGenetic == 'y') {

						System.out.println("Choose a selection strategy: ");
						System.out
								.println("Enter either \"random\", \"roulette\", \"rank\", or \"tournament\": ");
						String strategy = scannerString.nextLine();

						System.out.println(strategy);

						if (!strategy.equals("random")
								&& !strategy.equals("rank")
								&& !strategy.equals("roulette")
								&& !strategy.equals("tournament")) {
							System.out
									.println("An incorrect strategy field was detected. \nThe strategy is defaulting to \"random\".");
							strategy = "random";
						}

						if (strategy.equals("")) {
							System.out
									.println("An empty strategy field was detected. \nThe strategy is defaulting to \"random\".");
							System.out.println();
							strategy = "random";
						}

						System.out.println("Enter the population size: ");
						int numPop = scanner.nextInt();

						System.out.println("Enter the number of generations: ");
						int numPGen = scanner.nextInt();

						System.out
								.println("Enter the cross-over rate as a double value \n(eg. 80.0 for 80%): ");
						double crossRate = scanner.nextDouble();

						System.out
								.println("Enter the number of mutation rate as a double value \n(eg. 10.0 for 10%): ");
						double muteRate = scanner.nextDouble();

						int tourSize = 0;
						if (strategy.equals("tournament")) {
							System.out
									.println("Enter the tournament size as an integer: ");
							tourSize = scanner.nextInt();
							if (tourSize < 3) {
								tourSize = 3;
								System.out
										.println("The tournament size entered is less than 3. It has been set to 3.");
							}
						}
						System.out
								.println("Enter the generation gap as an integer value \n(eg. 1.0 for a 1% generation gap): ");
						double genGap = scanner.nextDouble();
						System.out.println();

						Genetics GA = null;

						if (strategy.equals("random")) {
							GA = new Genetics(new RandomStrategy(filePath,
									numPop, numPGen, crossRate, muteRate,
									genGap));

						} else if (strategy.equals("rank")) {
							GA = new Genetics(new RankStrategy(filePath,
									numPop, numPGen, crossRate, muteRate,
									genGap));
						} else if (strategy.equals("roulette")) {
							GA = new Genetics(new RouletteStrategy(filePath,
									numPop, numPGen, crossRate, muteRate,
									genGap));
						} else if (strategy.equals("tournament")) {
							GA = new Genetics(new TournamentStrategy(filePath,
									numPop, numPGen, crossRate, muteRate,
									tourSize, genGap));
						}
						GA.start();
						// try {
						// GA.runGA();
						// } catch (Exception e) {
						// System.out.println("The GA could not be executed.");
						// }
					}

					if (isGenetic == 'N' || isGenetic == 'n') {
						System.out
								.println("You have reached the end of the menu options.");
						System.out
								.println("Run the program again to restart the selection process.");
						scanner.close();
						scannerString.close();
					}
				}
			}
		} catch (IOException e) {
			System.out.println("The file path could not be detected.");
		}

	}
}