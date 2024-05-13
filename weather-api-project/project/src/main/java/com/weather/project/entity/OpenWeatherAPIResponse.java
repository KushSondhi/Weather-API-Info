package com.weather.project.entity;

public class OpenWeatherAPIResponse {
	
	private double lat;
	private double lon;
	private String name;
	private String country;
	private String zip;
	
	
	public double getLatitude() {
		return lat;
	}
	public void setLatitude(double latitude) {
		this.lat = latitude;
	}
	public double getLongitude() {
		return lon;
	}
	public void setLongitude(double longitude) {
		this.lon = longitude;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public OpenWeatherAPIResponse(double latitude, double longitude, String name, String country) {
		super();
		this.lat = latitude;
		this.lon = longitude;
		this.name = name;
		this.country = country;
	}
	
	public OpenWeatherAPIResponse() {
	}
	
	@Override
	public String toString() {
		
		return "GeocodingApiResponse [zip=" + zip + "name=" + name + "lat=" + lat
				+ "longitude= " + lon + "country=" + country +"]";
	}

}
