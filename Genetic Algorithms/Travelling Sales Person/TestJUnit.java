import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class TestJUnit {

	@Test
	public void testMyRandom() {

		MyRandom myRandom = new MyRandom();

		int rand1 = myRandom.randomInt(5);
		int rand2 = myRandom.randomInt(1, 5);
		double rand3 = myRandom.randomDouble();

		System.out.println("**************************************");
		System.out.println("1. Testing the MyRandom functonality:\n");
		System.out.println("0 <= RandomInt < 5: " + rand1);
		System.out.println("1 <= RandomInt < 5: " + rand2);
		System.out.println("0 < RandomDble < 1: " + rand3);
		System.out.println();

		for (int i = 0; i < 100; ++i) {
			int randTest = myRandom.randomInt(5);
			assertNotEquals(randTest, 5);
		}

		int high = 5;
		int low = 1;
		for (int i = 0; i < 100; ++i) {
			int randTest = myRandom.randomInt(1, 5);
			assertTrue("Error, random is too high", randTest < high);
			assertTrue("Error, random is too high", randTest >= low);
		}

		for (int i = 0; i < 100; ++i) {
			double randTest = myRandom.randomDouble();
			assertTrue("Error, random is too high", randTest < 1);
			assertTrue("Error, random is too high", randTest >= 0);
		}

	}

	@Test
	public void testRouteLength() {

		Route route1, route2;
		ArrayList<City> path1 = new ArrayList<City>();
		ArrayList<City> path2 = new ArrayList<City>();

		String[] names1 = { "F", "E", "C", "A", "B", "D" };
		double[] lat1 = { 10.6, 10.5, 10.3, 10.1, 10.2, 10.4 };
		double[] lon1 = { 20.6, 20.5, 20.3, 20.1, 20.2, 20.4 };
		for (int i = 0; i < names1.length; ++i) {
			double x1 = lat1[i];
			double y1 = lon1[i];
			path1.add(new City(x1, y1, names1[i]));
		}

		String[] names2 = { "D", "C", "B", "A", "F", "E" };
		double[] lat2 = { 10.4, 10.3, 10.2, 10.1, 10.6, 10.5 };
		double[] lon2 = { 20.4, 20.3, 20.2, 20.1, 20.6, 20.5 };
		for (int i = 0; i < names2.length; ++i) {
			double x2 = lat2[i];
			double y2 = lon2[i];
			path2.add(new City(x2, y2, names2[i]));
		}

		route1 = new Route(path1, false);
		route2 = new Route(path2, false);

		int lengthDNA = route1.getChromosome().size();
		assertEquals(lengthDNA, 6);

		route1.setChromosome(path1);
		route2.setChromosome(path2);

		ArrayList<City> routeList = new ArrayList<City>(route1.getChromosome());

		assertEquals(routeList, path1);
		assertEquals(routeList.get(0).getName(), "F");
		assertEquals(routeList.get(1).getName(), "E");
		assertEquals(routeList.get(2).getName(), "C");

		assertEquals(routeList.get(0).getLat(), 10.6, 0.01);
		assertEquals(routeList.get(1).getLat(), 10.5, 0.01);
		assertEquals(routeList.get(2).getLat(), 10.3, 0.01);

		assertEquals(routeList.get(0).getLon(), 20.6, 0.01);
		assertEquals(routeList.get(1).getLon(), 20.5, 0.01);
		assertEquals(routeList.get(2).getLon(), 20.3, 0.01);

		ArrayList<City> parentA, parentB;
		ArrayList<City> child = new ArrayList<City>();

		parentA = new ArrayList<City>(route1.getChromosome());
		parentB = new ArrayList<City>(route2.getChromosome());

		System.out.println("**************************************");
		System.out.println("2. Testing the crossover functonality:\n");
		// Print results
		System.out.print("ParentA: ");
		for (int i = 0; i < parentA.size(); ++i) {
			System.out.print(parentA.get(i).getName());
		}

		System.out.println();
		System.out.print("ParentB: ");
		for (int i = 0; i < parentB.size(); ++i) {
			System.out.print(parentB.get(i).getName());
		}

		ArrayList<Route> populationList = new ArrayList<Route>();
		for (int i = 0; i < 50; ++i) {
			populationList.add(new Route(path1, false));
		}

		RandomStrategy randomStrategy = new RandomStrategy(populationList, 5, 10, 80.0, 10.0, 10.0, 6);
		randomStrategy.crossover(parentA, parentB, child);

		// Print results
		System.out.println();
		System.out.print("Child: ");
		for (int i = 0; i < child.size(); ++i) {
			System.out.print(child.get(i).getName());
		}
	}
}