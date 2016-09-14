
/**
 * City data in json-format found at: 
 * https://gist.github.com/Miserlou/c5cd8364bf9b2420bb29
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

	public static final int NUM_CITIES = 10;
	private static ArrayList<City> path = new ArrayList<City>();
	private static Population population;
	private static ArrayList<Route> populationList = new ArrayList<Route>();
	private static double sumHaversine = 0.0;

	public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {

		int maxIter = 1000;
		int numPop = 1000;
		double crossoverRate = 80.0;
		double mutationRate = 15.0;

		// Read city.json file
		JSONParser parser = new JSONParser();
		JSONArray array = (JSONArray) parser.parse(new FileReader("./sourceFiles/cities.json"));
		for (Object obj : array) {
			JSONObject data = (JSONObject) obj;
			String cityName = (String) data.get("city");
			double cityLat = (Double) data.get("latitude");
			double cityLon = (Double) data.get("longitude");

			path.add(new City(cityLat, cityLon, cityName));
		}

		// Create population list to start off with
		populationList = new ArrayList<Route>();
		for (int i = 0; i < numPop; ++i) {
			populationList.add(new Route(path, true));
		}

		population = new Population(populationList, numPop, maxIter, crossoverRate, mutationRate, NUM_CITIES);
		population.runGA();
		System.out.println();
		population.printResult();

		calculateHaversine(population.getBestSolution());
		System.out.println();
		System.out.println("Haversine distance: " + sumHaversine);

	}

	private static double haversine(double lat1, double lat2, double lon1, double lon2) {
		double p = 0.017453292519943295;
		double a = 0.5 - Math.cos((lat2 - lat1) * p) / 2
				+ Math.cos(lat1 * p) * Math.cos(lat2 * p) * (1 - Math.cos((lon2 - lon1) * p)) / 2;
		return 12742 * Math.asin(Math.sqrt(a));
	}

	private static void calculateHaversine(ArrayList<City> path) {
		for (int i = 0; i < NUM_CITIES - 1; ++i) {
			sumHaversine += haversine(path.get(i).getLat(), path.get(i + 1).getLat(), path.get(i).getLon(),
					path.get(i + 1).getLon());
		}
	}

}