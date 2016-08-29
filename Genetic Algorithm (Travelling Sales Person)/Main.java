import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

	public static final int NUM_CITIES = 20;
	private static ArrayList<City> path = new ArrayList<>();
	private static Route route;
	private static RandomStrategy randomStrategy;

	public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {

		int maxIter = 1000;
		int numPop = 10;
		double crossoverRate = 80.0;
		double mutationRate = 5.0;
		double percentageGap = 5.0;

		// Reading the city data in from a .json file found at:
		// https://gist.github.com/Miserlou/c5cd8364bf9b2420bb29
		JSONParser parser = new JSONParser();
		JSONArray a = (JSONArray) parser.parse(new FileReader("./cityFiles/cities.json"));

		for (Object o : a) {
			JSONObject data = (JSONObject) o;

			String cityName = (String) data.get("city");
			double cityLat = (Double) data.get("latitude");
			double cityLon = (Double) data.get("longitude");

			path.add(new City(cityLat, cityLon, cityName));
		}

		// for (City c : path) {
		// System.out.println(c.getName() + ": " + c.getLat() + ", " +
		// c.getLon());
		// }

		route = new Route(path);
		randomStrategy = new RandomStrategy(path, numPop, maxIter, crossoverRate, mutationRate, percentageGap);
		randomStrategy.printStartInfo();

		randomStrategy.runGA();

		System.out.print("Optimal route: ");
		for (int i = 0; i < route.chromosome.size(); ++i) {
			System.out.print(randomStrategy.getOptimalRoute().getChromosome().get(i).getName());
			if (i < route.chromosome.size() - 1) {
				System.out.print("->");
			}
		}
	}
}
