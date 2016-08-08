import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Trader class: calculates and evaluates the fitness of each trader based on
 * the number of stocks traded, and the return on investment. Note: The Trader
 * class contains RenkoData as an inner class so that the Renko Data obtained is
 * accessible only via the trader (encapsulation), and not via any other class.
 * This helps prevent potential duplication of methods and resulting erroneous
 * data.
 * 
 * @author cadebe
 * 
 */
public class Trader {

	private static final int COUNT = 32;
	private String filePath;
	private String chromosome;
	private double money;
	private int numShares;
	private double fitness;
	private RenkoData renko_data;
	private ArrayList<Integer> renkoData = new ArrayList<Integer>();
	private ArrayList<Integer> renkoDataWithDays = new ArrayList<Integer>();
	private ArrayList<String> stringRenkoData = new ArrayList<String>();
	private ArrayList<Integer> highPriceList = new ArrayList<Integer>();
	private ArrayList<Integer> lowPriceList = new ArrayList<Integer>();
	private ArrayList<Double> averagePriceList = new ArrayList<Double>();
	private static final double SST_PERCENTAGE = 0.0025;
	private static final double BF_PERCENTAGE = 0.0005;
	private static final double BF_MIN = 70.0;
	private static final double STRATE = 11.58;
	private static final double IPL_PERCENTAGE = 0.000002;
	private static final double VAT_PERCENTAGE = 0.14;

	/**
	 * Parameterised constructor for Trader; takes the filepath of the input
	 * file as argument.
	 * 
	 * @param filePath_
	 *            String containing the filepath to the input file
	 * @throws IOException
	 */
	public Trader(String filePath_) throws IOException {

		try {
			filePath = filePath_;
			renko_data = new RenkoData(filePath);
			renko_data.createRenko();
			renkoData = renko_data.getRenkoDataList();
			renkoDataWithDays = renko_data.getRenkoDataListWithDays();
			chromosome = calculateChromosome();
			money = 100000.00;
			numShares = 0;
			fitness = 0.0;
			// for (int i = 0; i < renkoDataWithDays.size(); ++i)
			// System.out.print(renkoDataWithDays.get(i));
		} catch (Exception e) {
			System.out.println("Could not generate Renko data.");
		}
	}

	/**
	 * Method that creates the Trader chromosome: a 32-character string where
	 * each character is either a B, S or H character (for buy, sell or hold).
	 * 
	 * @return
	 */
	private String calculateChromosome() {
		String BHS = "";
		for (int i = 0; i < COUNT; ++i) {
			Random r = new Random();
			int randVal = r.nextInt(3 - 0);

			if (randVal == 0)
				BHS += 'B';
			else if (randVal == 1)
				BHS += 'S';
			else if (randVal == 2)
				BHS += 'H';
		}

		// System.out.println("Trader Chromosome: " + BHS);
		return BHS;
	}

	/**
	 * Method that calculates the fitness of each trader.
	 * 
	 * @return fitness double value
	 */
	public double calcFitness() throws IOException {

		decideBHS();

		fitness = money
				+ (numShares * averagePriceList
						.get(averagePriceList.size() - 1));
		return fitness;

	}

	/**
	 * Method that decides whether to buy, sell or hold, and if buying or
	 * selling, calculates the impact of the trade on money and numShares
	 * 
	 * @throws IOException
	 */
	private void decideBHS() throws IOException {

		stringRenkoData.clear();
		averagePriceList.clear();

		String renkoString = "";
		String result = "";

		// Stringify Renko Data
		for (int i = 0; i < renkoData.size(); ++i) {
			stringRenkoData.add(Integer.toString(renkoData.get(i)));
		}

		// Create giant String object with all the Renko Data
		for (int i = 0; i < stringRenkoData.size(); ++i) {
			renkoString += stringRenkoData.get(i);
		}

		// System.out.println();
		// System.out.println(renkoString);

		try {
			calcAveragePrice();
		} catch (Exception e) {
			System.out
					.println("File not found. \nCheck file name or directory location and try again.");
		}

		// Grab 5-bit substrings from giant String
		for (int i = 0; i < renkoString.length() - 5; ++i) {
			result = renkoString.substring(i, i + 5);
			// System.out.println(result);

			// Get index value in lookup table
			int index = getLookUpTableValue(result);

			// Get decision value: B, S, H

			char decide = chromosome.charAt(index);
			// System.out.println("decide: " + decide);

			// BUY shares - if you can afford to
			if (decide == 'B' && money > 0) {

				int count = 0;
				for (int j = 0; j < renkoDataWithDays.size() - 1; ++j) {

					if (renkoDataWithDays.get(j) != -9) {
						count++;

						if (count == 5) {

							// On the fist trading day after 5 bricks
							// check how much you can buy
							int mod = (int) Math.floor(money
									/ averagePriceList.get(j + 1));
							// System.out.println(mod);

							if (mod > 0) {

								// Calculate fees
								double tradeAmount = mod
										* averagePriceList.get(j);
								double SST = tradeAmount * SST_PERCENTAGE;
								double brokerageFee = tradeAmount
										* BF_PERCENTAGE;
								if (brokerageFee < BF_MIN)
									brokerageFee = BF_MIN;
								double IPL = tradeAmount * IPL_PERCENTAGE;
								double VAT = VAT_PERCENTAGE
										* (SST + brokerageFee + STRATE + IPL);
								double fees = SST + brokerageFee + STRATE + IPL
										+ VAT;

								tradeAmount += fees;

								// System.out.println("Fees: " + fees);
								// System.out.println("Trade Amount:" +
								// tradeAmount);

								// Double-check to see if the trade amount with
								// fees is still affordable
								if (tradeAmount > money) {

									if (mod > 1) {
										mod -= 1;

										tradeAmount = mod
												* averagePriceList.get(j);
										SST = tradeAmount * SST_PERCENTAGE;
										brokerageFee = tradeAmount
												* BF_PERCENTAGE;
										if (brokerageFee < BF_MIN)
											brokerageFee = BF_MIN;
										IPL = tradeAmount * IPL_PERCENTAGE;
										VAT = VAT_PERCENTAGE
												* (SST + brokerageFee + STRATE + IPL);
										fees = SST + brokerageFee + STRATE
												+ IPL + VAT;

										tradeAmount += fees;
									}
								}

								numShares += mod;
								money -= tradeAmount;

								// System.out.println("Fees: " + fees);
								// System.out.println("Trade Amount:"
								// + tradeAmount);

								// System.out.println("Money left: " + money);
								// System.out.println("Number of shares: "
								// + numShares);
							}
						}
					}
				}

			} // end-if BUY
				// SELL shares
			else if (decide == 'S' && numShares > 0) {

				int count = 0;
				for (int j = 0; j < renkoDataWithDays.size() - 1; ++j) {

					if (renkoDataWithDays.get(j) != -9) {
						count++;

						if (count == 5) {

							double tradeAmount = numShares
									* averagePriceList.get(j);

							double brokerageFee = tradeAmount * BF_PERCENTAGE;
							if (brokerageFee < BF_MIN)
								brokerageFee = BF_MIN;
							double IPL = tradeAmount * IPL_PERCENTAGE;
							double VAT = VAT_PERCENTAGE
									* (brokerageFee + STRATE + IPL);
							double fees = brokerageFee + STRATE + IPL + VAT;

							money += (tradeAmount - fees);
							numShares = 0;

							// System.out.println("Fees: " + fees);
							// System.out.println("Trade Amount:" +
							// tradeAmount);

							// System.out.println("Money left: " + money);
							// System.out
							// .println("Number of shares: " + numShares);
						}
					}
				}
			} // end-if SELL

		} // i-loop
	}

	/**
	 * Method that calculates the average price values for each day, based on
	 * the mean of the daily high and low price values for the data set.
	 * 
	 * @throws IOException
	 */
	private void calcAveragePrice() throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(filePath));

		highPriceList.clear();
		lowPriceList.clear();
		averagePriceList.clear();

		try {
			String line = br.readLine();

			while (line != null) {
				try {
					String[] parts = line.split("\t");

					// ------------ Create highPriceList

					highPriceList.add(Integer.parseInt(parts[2]));
					// System.out.println(h);

					// ------------ Create lowPriceList

					lowPriceList.add(Integer.parseInt(parts[3]));

					// System.out.println(l);
					// System.out.println(lowPriceList.size());

					// ------------ Create avergaePriceList

				} catch (Exception e) {

				}

				line = br.readLine();
			}

			double ave = 0;

			for (int i = 0; i < highPriceList.size(); ++i) {
				ave = (highPriceList.get(i) + lowPriceList.get(i)) / 2.0;
				averagePriceList.add(ave);
			}

			// for (int i = 0; i < averagePriceList.size(); ++i) {
			// System.out.println(averagePriceList.get(i));
			// }
			// System.out.println(averagePriceList.size());

		} finally {
			br.close();
		}

	}

	/**
	 * Method to print out vital Renko data information to the console/terminal
	 * (strictly speaking this method belongs to the RenkoData class; however
	 * RenkoData is called via Trader, and not on its own, to prevent
	 * duplication of method calls and erroneous data).
	 * 
	 * @throws IOException
	 */
	public void printRenkoDataInfo() throws IOException {
		System.out.println("DATA SET FOR \"" + filePath + "\":");
		System.out.println("Brick size: " + renko_data.calcBrickSize());
		System.out.println("Renko Data size: " + renkoData.size());
		System.out.println("Renko Data:");

		for (int i = 0; i < renkoData.size(); ++i) {
			System.out.print(renkoData.get(i));
		}
		System.out.println();
		System.out.println();
	}

	/**
	 * Method to print out trader's trading result information (to be called
	 * AFTER decideBHS() and calcFitness() have been called so as to provide the
	 * required result information).
	 */
	public void printTradeResult(int i) {
		System.out.println("TRADING RESULT: " + i);
		System.out.println("Trader Chromosome: " + chromosome);
		System.out.println("Money left: " + money);
		System.out.println("Number of shares: " + numShares);
		System.out.println("Trader fitness: " + fitness);
	}

	/**
	 * Method that acts as lookup table for the 5-bit Renko brick data string
	 * values.
	 * 
	 * @param renko
	 *            String containing the Renko data
	 * @return integer value corresponding to the position in the 32-character
	 *         string chromosome
	 */
	private int getLookUpTableValue(String renko) {

		switch (renko) {
		case "00000":
			return 0;
		case "00001":
			return 1;
		case "00010":
			return 2;
		case "00011":
			return 3;
		case "00100":
			return 4;
		case "00101":
			return 5;
		case "00110":
			return 6;
		case "00111":
			return 7;
		case "01000":
			return 8;
		case "01001":
			return 9;
		case "01010":
			return 10;
		case "01011":
			return 11;
		case "01100":
			return 12;
		case "01101":
			return 13;
		case "01110":
			return 14;
		case "01111":
			return 15;
		case "10000":
			return 16;
		case "10001":
			return 17;
		case "10010":
			return 18;
		case "10011":
			return 19;
		case "10100":
			return 20;
		case "10101":
			return 21;
		case "10110":
			return 22;
		case "10111":
			return 23;
		case "11000":
			return 24;
		case "11001":
			return 25;
		case "11010":
			return 26;
		case "11011":
			return 27;
		case "11100":
			return 28;
		case "11101":
			return 29;
		case "11110":
			return 30;
		case "11111":
			return 31;
		default:
			return -1;
		}
	}

	/**
	 * Getter method that gets the size of the Renko Data, for external use.
	 * 
	 * @return String object containing chromosome information
	 */
	public final String getChromosome() {
		return chromosome;
	}

	public void setChromosome(String chromosome_) {
		chromosome = chromosome_;
	}

	/**
	 * RenkoData inner class: calculates the binary string to represent the
	 * Renko Data for the period of trading; used by the Trader class
	 * 
	 * @author cadebe
	 * 
	 */
	public class RenkoData {

		private String filePath = "";
		private ArrayList<Integer> closingPriceList = new ArrayList<Integer>();
		private ArrayList<Integer> renkoDataList = new ArrayList<Integer>();
		private ArrayList<Integer> renkoDataListWithDays = new ArrayList<Integer>();

		/**
		 * Parameterised constructor for RenkoData; takes the filepath of the
		 * input file as argument.
		 * 
		 * @param filePath_
		 *            String containing the filepath to the input file
		 */
		public RenkoData(String filePath_) {
			filePath = filePath_;
		}

		/**
		 * Method that calculates the brick size for the data set. The brick
		 * size is determined by the difference between the maximum and minimum
		 * values of the closing price, multiplied by 0.1%.
		 * 
		 * @return Integer value to return the brick size value
		 * @throws IOException
		 */
		public int calcBrickSize() throws IOException {

			closingPriceList.clear();

			int max = 0;
			int min = 0;

			try {
				BufferedReader br = new BufferedReader(new FileReader(filePath));
				try {

					String line = br.readLine();

					while (line != null) {
						try {
							String[] parts = line.split("\t");

							closingPriceList.add(Integer.parseInt(parts[1]));
							// System.out.println(s);

						} catch (Exception e) {

						}

						max = Collections.max(closingPriceList);
						min = Collections.min(closingPriceList);

						line = br.readLine();
					}

					// System.out.println("Closing price max: " + max
					// + "\nClosing price min:  " + min);
					// System.out.println(closingPriceList.size());

				} finally {
					br.close();
				}
			} catch (IOException ex) {
				System.out
						.println("File not found. \nCheck file name or directory location and try again.");
			}

			double res = Math.ceil((max - min) * 0.01);

			return (int) res;
		}

		/**
		 * Method that creates the Renko Data, represented by a binary string,
		 * and contained within an ArrayList.
		 * 
		 * @return renkoDataList ArrayList that contains the Renko Data
		 * @throws IOException
		 */
		public ArrayList<Integer> createRenko() throws IOException {

			closingPriceList.clear();
			renkoDataListWithDays.clear();
			renkoDataList.clear();

			int res = calcBrickSize();
			int balance = closingPriceList.get(0);

			for (int i = 1; i < closingPriceList.size(); ++i) {

				if (closingPriceList.get(i) - balance < 0
						&& Math.abs(closingPriceList.get(i) - balance) >= res) {
					renkoDataList.add(1);
					renkoDataListWithDays.add(1);
					balance -= res;
				} else if (closingPriceList.get(i) - balance > 0
						&& Math.abs(closingPriceList.get(i) - balance) >= res) {
					renkoDataList.add(0);
					renkoDataListWithDays.add(0);
					balance += res;
				} else {
					renkoDataListWithDays.add(-9);
					// where -9 means that no brick movement happened on
					// that
					// day
				}
			}

			return renkoDataList;
		}

		/**
		 * Getter method that gets the Renko data list, for external use.
		 * 
		 * @return renkoDataList ArrayList containing the Renko Data
		 */
		public final ArrayList<Integer> getRenkoDataList() {
			return renkoDataList;
		}

		/**
		 * Getter method that gets the Renko data list, with reference to the
		 * number of days, for external use.
		 * 
		 * @return renkoDataListWithDays ArrayList containing the Renko data
		 *         with reference to the days
		 */
		public final ArrayList<Integer> getRenkoDataListWithDays() {
			return renkoDataListWithDays;
		}

		/**
		 * Getter method that gets the size of the Renko Data, for external use.
		 * 
		 * @return integer value that represents the size of the Renko Data list
		 */
		public final int getRenkoDataSize() {
			return renkoDataList.size();
		}
	} // end Renko Data class
} // end Trader class
