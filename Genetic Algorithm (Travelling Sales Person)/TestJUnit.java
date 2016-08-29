import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class TestJUnit {

	ArrayList<City> path = new ArrayList<>();
	RandomStrategy randomStrategy;
	Route route;

	@Test
	public void testRouteLength() {

		ArrayList<City> path = new ArrayList<>();
		String[] names = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
				"S", "T" };
		double[] lat = { 10.05, 27.03, 81.87, 33.67, 82.98, 24.34, 77.35, 73.82, 12.36, 63.25, 23.83, 65.83, 23.46,
				67.21, 48.39, 24.89, 74.35, 25.87, 36.78, 76.37 };
		double[] lon = { 67.33, 73.23, 83.48, 72.62, 26.78, 87.88, 65.78, 89.52, 31.24, 56.87, 18.94, 45.89, 34.76,
				63.67, 34.82, 76.51, 78.35, 23.78, 38.23, 54.27 };
		for (int i = 0; i < names.length; ++i) {
			double x = lat[i];
			double y = lon[i];
			path.add(new City(x, y, names[i]));
		}

		route = new Route(path);

		int lengthDNA = route.getChromosome().size();
		assertEquals(lengthDNA, 20);

		route.setChromosome(path);
		assertEquals(route.getChromosome(), path);
		assertEquals(route.getChromosome().get(0).getName(), "A");
		assertEquals(route.getChromosome().get(1).getName(), "B");
		assertEquals(route.getChromosome().get(2).getName(), "C");
		assertEquals(route.getChromosome().get(19).getName(), "T");

		assertEquals(route.getChromosome().get(0).getLat(), 10.05, 0.01);
		assertEquals(route.getChromosome().get(1).getLat(), 27.03, 0.01);
		assertEquals(route.getChromosome().get(2).getLat(), 81.87, 0.01);
		assertEquals(route.getChromosome().get(19).getLat(), 76.37, 0.01);

		assertEquals(route.getChromosome().get(0).getLon(), 67.33, 0.01);
		assertEquals(route.getChromosome().get(1).getLon(), 73.23, 0.01);
		assertEquals(route.getChromosome().get(2).getLon(), 83.48, 0.01);
		assertEquals(route.getChromosome().get(19).getLon(), 54.27, 0.01);
	}

}