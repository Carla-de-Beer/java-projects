
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
	private static ArrayList<Route> populationList = new ArrayList<Route>();

	public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {

		int maxIter = 50;
		int numPop = 850;
		double crossoverRate = 80.0;
		double mutationRate = 15.0;
		double generationGap = 3.0;
		int tournamentSize = 10;

		// Read city.json file
		JSONParser parser = new JSONParser();
		JSONArray array = (JSONArray) parser.parse(new FileReader("./sourceFiles/cities.json"));
		for (Object obj : array) {
			JSONObject data = (JSONObject) obj;
			String cityName = (String) data.get("city");
			double cityLon = (Double) data.get("longitude");
			double cityLat = (Double) data.get("latitude");

			path.add(new City(cityLon, cityLat, cityName));
		}

		// Create population list to start off with
		populationList = new ArrayList<Route>();
		for (int i = 0; i < numPop; ++i) {
			populationList.add(new Route(path, true));
		}

		// RANDOM SELECTION STRATEGY
		Genetics GA = new Genetics(new RandomStrategy(populationList, numPop, maxIter, crossoverRate, mutationRate,
				generationGap, NUM_CITIES));

		// RANK SELECTION STRATEGY
		// Genetics GA = new Genetics(new RankStrategy(populationList, numPop,
		// maxIter, crossoverRate, mutationRate,
		// generationGap, NUM_CITIES));

		// TOURNAMENT SELECTION STRATEGY
		// Genetics GA = new Genetics(new TournamentStrategy(populationList,
		// numPop, maxIter, crossoverRate, mutationRate,
		// generationGap, NUM_CITIES, tournamentSize));

		// ROULETTE SELECTION STRATEGY
		// Genetics GA = new Genetics(new RouletteStrategy(populationList,
		// numPop, maxIter, crossoverRate, mutationRate,
		// generationGap, NUM_CITIES));

		GA.start();
	}
}