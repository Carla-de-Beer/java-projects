package useCases;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ReadTLEFile implements IReadTLEFile {

	private ReadTLEFileRequest request;

	// ArrayLists to contain data extracted
	private ArrayList<String> nameList = new ArrayList<String>();
	private ArrayList<String> satIDList = new ArrayList<String>();
	// Maximum number of letters and digits allowed in a TLE entry
	private static final int MAX_SIZE = 69;
	
	public ReadTLEFile(){		
	}

/**
 * Setter method for DI.
 * @param request Request object to be set
 */
	public void setRequest(ReadTLEFileRequest request){
		 this.request = request;
	} 

	/**
	 * Implements the readFile method from the IReadTLEFile interface. Checks to
	 * see if: file can be found, file format is correct, file not empty,
	 * correct file data provided, otherwise throws exceptions.
	 */
	public ReadTLEFileResult readFile()
			throws IOException, FileEmptyException,
			FileIncorrectFormatException, FileIncorrectDataException {

		// If file format is incorrect ...
		if (!request.getFilePath().endsWith(".txt")) {
			throw new FileIncorrectFormatException(
					"Incorrect file format provided.");
		}

		BufferedReader br = new BufferedReader(new FileReader(
				request.getFilePath()));
		try {
			String line = br.readLine();
			int counter = 0;

			while (line != null) {

				if (counter % 3 == 0) {
					//System.out.println(line);
					nameList.add(line);
				} else if (counter % 1 == 0 || counter % 2 == 0) {
					String check = line;

					if (check.length() > MAX_SIZE) {
						// Check if too many characters
						// Exception
						throw new FileIncorrectDataException(
								"Incorrect input data provided.");
					} else if (check == null || check.isEmpty()) {
						// Check is list is empty
						throw new FileIncorrectDataException(
								"Empty data field detected.");
					} else if (counter % 2 == 0) {
						// Get NORAD_ID
						satIDList.add(line.substring(2, 7));
					}

					// Checks to see if no illegal characters have been provided
					Pattern p = Pattern.compile("^[A-Z,.+\\s\\d-]+$");
					// String s = "34aa";
					boolean hasSpecialChar = p.matcher(line).find();
					if (!hasSpecialChar)
						throw new FileIncorrectDataException(
								"Incorrect input data provided.");
				}
				counter++;
				line = br.readLine();
			}
			// System.out.println("Number of satellites in file: "
			// + nameList.size());
			// System.out.println("Number of satelliteIDs in file: "
			// + satIDList.size());

			 for (String i : nameList) {
			 System.out.println(i);
			 }

			ReadTLEFileResult result = new ReadTLEFileResult();
			result.setResult(nameList);
			return result;

		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw e;
		} finally {
			br.close();
		}
	}
}
