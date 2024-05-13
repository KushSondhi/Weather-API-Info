package com.weather.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "Weather_Info")
public class WeatherData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long weatherId;

	@ManyToOne
	private LocationDetails location;

	@Column(name = "Temperature")
	private double temp;

	private double feels_like;

	@Column(name = "MIN_TEMP")
	private double temp_min;

	@Column(name = "MAX_TEMP")
	private double temp_max;

	private int humidity;

	private double pressure;

	private double sea_level;

	private long ground_level;

	private double visibility;

	private double windSpeed;

	private double gust;

	private long sunrise;

	private long sunset;

	private String place;

	public Long getWeatherId() {
		return weatherId;
	}

	public void setWeatherId(Long weatherId) {
		this.weatherId = weatherId;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(Object place) {
		this.place = (String) place;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(Object humidity) {
		this.humidity = (int) humidity;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(Object object) {
		this.pressure = (int) object;
	}

	public LocationDetails getLocation() {
		return location;
	}

	public void setLocation(LocationDetails locationId) {
		this.location = locationId;
	}

	public double getTemp_min() {
		return temp_min;
	}

	public void setTemp_min(Object object) {
		this.temp_min = (double) object;
	}

	public double getTemp_max() {
		return temp_max;
	}

	public void setTemp_max(Object temp_max) {
		this.temp_max = (double) temp_max;
	}

	public double getVisibility() {
		return visibility;
	}

	public void setVisibility(Object object) {
		this.visibility = (int) object;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(Object object) {
		this.temp = (double) object;
	}

	public double getFeels_like() {
		return feels_like;
	}

	public void setFeels_like(Object object) {
		this.feels_like = (double) object;
	}

	public double getSea_level() {
		return sea_level;
	}

	public void setSea_level(Object sea_level) {
		this.sea_level = (int) sea_level;
	}

	public long getGround_level() {
		return ground_level;
	}

	public void setGround_level(Object ground_level) {
		this.ground_level = (int) ground_level;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(Object windSpeed) {
		this.windSpeed = (double) windSpeed;
	}

	public double getGust() {
		return gust;
	}

	public void setGust(Object gust) {
		this.gust = (double) gust;
	}

	public long getSunrise() {
		return sunrise;
	}

	public void setSunrise(Object sunrise) {
		this.sunrise = (int) sunrise;
	}

	public long getSunset() {
		return sunset;
	}

	public void setSunset(Object sunset) {
		this.sunset = (int) sunset;
	}

	public WeatherData(Long weatherId, LocationDetails location, double temp, double feels_like, double temp_min, double temp_max,
			int humidity, double pressure, double sea_level, long ground_level, double visibility, double windSpeed,
			double gust, long sunrise, long sunset, String place) {
		super();
		this.weatherId = weatherId;
		this.location = location;
		this.temp = temp;
		this.feels_like = feels_like;
		this.temp_min = temp_min;
		this.temp_max = temp_max;
		this.humidity = humidity;
		this.pressure = pressure;
		this.sea_level = sea_level;
		this.ground_level = ground_level;
		this.visibility = visibility;
		this.windSpeed = windSpeed;
		this.gust = gust;
		this.sunrise = sunrise;
		this.sunset = sunset;
		this.place = place;
	}

	public WeatherData() {

		super();
	}

}
