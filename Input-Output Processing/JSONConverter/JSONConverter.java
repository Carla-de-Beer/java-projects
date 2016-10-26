
/**
 * Class that converts data in JSON format to a txt format. The JSON-simple library needs to be installed.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONConverter {

	private static ArrayList<Double> latList = new ArrayList<>();
	private static ArrayList<Double> lonList = new ArrayList<>();
	private static ArrayList<String> nameList = new ArrayList<>();

	public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {

		// Reading the city data in from a .json file found at:
		// https://gist.github.com/Miserlou/c5cd8364bf9b2420bb29
		JSONParser parser = new JSONParser();
		JSONArray a = (JSONArray) parser.parse(new FileReader("./sourceFiles/cities.json"));

		for (Object o : a) {
			JSONObject data = (JSONObject) o;

			double cityLat = (Double) data.get("latitude");
			double cityLon = (Double) data.get("longitude");
			String cityName = (String) data.get("city");

			latList.add(cityLat);
			lonList.add(cityLon);
			nameList.add(cityName);
		}

		File file = new File("cities.txt");
		file.createNewFile();
		FileWriter writer = new FileWriter(file);

		for (int i = 0; i < 36; ++i) {
			String latString = String.valueOf(latList.get(i));
			String lonString = String.valueOf(lonList.get(i));

			writer.write(latString);
			writer.write(", ");
			writer.write(lonString);
			writer.write(", ");
			writer.write(nameList.get(i));
			writer.write("\n");
		}

		writer.flush();
		writer.close();
	}
}
