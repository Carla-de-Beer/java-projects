/**
 * Class that creates City objects. These objects hold the city coordinates and
 * city name. Used by the Route class, where a rout consists of a number of city
 * objects.
 * 
 * @author cadebe
 *
 */

public class City {

	private double lat, lon;
	private String name;

	public City(double lat, double lon, String name) {
		this.lat = lat;
		this.lon = lon;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public double getLon() {
		return lon;
	}

	public double getLat() {
		return lat;
	}

}
