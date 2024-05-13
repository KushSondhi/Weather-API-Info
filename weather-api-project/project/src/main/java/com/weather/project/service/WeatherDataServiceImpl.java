package com.weather.project.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.project.entity.LocationDetails;
import com.weather.project.entity.OpenWeatherAPIResponse;
import com.weather.project.entity.WeatherData;
import com.weather.project.repository.LocationRepository;
import com.weather.project.repository.WeatherDataRepository;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {

	int i = 0;

	@Autowired
	WeatherDataRepository weatherDataRepository;

	@Autowired
	LocationRepository locationRepository;

	@Override
	public WeatherData saveWeatherDetails(WeatherData weatherDetails) {

		return weatherDataRepository.save(weatherDetails);
	}

	@Bean
	public RestTemplate template(RestTemplateBuilder builder) {

		return builder.build();

	}

	private String OPEN_WEATHER_API = "3f52fd7fa4327e1fb2cc73e72e2b9743";

	@Override
	public WeatherData updateWeatherData(Long locationId) {

		LocationDetails loc = locationRepository.findById(locationId).get();
		WeatherData weatherData = weatherDataRepository.findById(locationId).get();

		if (Objects.nonNull(weatherData.getPlace()) && !"".equalsIgnoreCase((weatherData.getPlace()))) {

			weatherData.setPlace(weatherData.getPlace());
		}

		return weatherDataRepository.save(weatherData);
	}

	@Override
	public void deleteWeatherDetails(Long locationId) {

		weatherDataRepository.deleteById(locationId);

	}

	@Cacheable("locationCache")
	private LocationDetails checkExistingLocationData(Long pinCode, LocalDate date) throws IOException {

		LocationDetails location = locationRepository.findById(pinCode).orElse(generateLocationData(pinCode, date));
		return location;
	}

	private LocationDetails generateLocationData(Long pinCode, LocalDate date) throws IOException {

		String url = "http://api.openweathermap.org/geo/1.0/zip?zip=" + pinCode.toString()
				+ ",in&appid=".concat(OPEN_WEATHER_API);
		URL sUrl = new URL(url);

		HttpURLConnection conn = (HttpURLConnection) sUrl.openConnection();
		conn.setRequestMethod("GET");
		InputStream inputStream = conn.getInputStream();
		ObjectMapper mapper = new ObjectMapper();

		Map<String, Object> map = mapper.readValue(inputStream, Map.class);
		RestTemplate template = new RestTemplate();

		// System.out.println(map);

		ResponseEntity<OpenWeatherAPIResponse> response = template.getForEntity(sUrl.toString(),
				OpenWeatherAPIResponse.class);

		double latitude = 0;
		double longitude = 0;
		double zip = 0;
		String city;

		if (response.getStatusCode().is2xxSuccessful()) {

			latitude = (double) map.get("lat");
			longitude = (double) map.get("lon");
			city = (String) map.get("name");

			LocationDetails newLocation = new LocationDetails(pinCode, latitude, longitude, date, city);
			locationRepository.save(newLocation);
			inputStream.close();
			conn.disconnect();
			return newLocation;
		} else {
			System.out.print("Wrong Pincode or Date");
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public WeatherData showWeatherData(Long pinCode, LocalDate date) throws Exception {

		LocationDetails existingLocation = checkExistingLocationData(pinCode, date);

		WeatherData weatherData = weatherDataRepository.findById(existingLocation.getLocationId())
				.orElse(new WeatherData());

		if (weatherData.getPlace() == null) {
			System.out.println("New Weather Object");

			String weatherURL = "https://api.openweathermap.org/data/2.5/weather?lat=" + existingLocation.getLatitude()
					+ "&lon=" + existingLocation.getLongitude() + "&appid=" + OPEN_WEATHER_API;

			URL weatherdataURL = new URL(weatherURL);

			HttpURLConnection connection = (HttpURLConnection) weatherdataURL.openConnection();
			connection.setRequestMethod("GET");

			InputStream inputStream = connection.getInputStream();

			ObjectMapper mapper = new ObjectMapper();
			Map<Object, Object> weatherMap = mapper.readValue(inputStream, Map.class);
			Map<String, Object> main = (Map<String, Object>) weatherMap.get("main");
			Map<String, Object> wind = (Map<String, Object>) weatherMap.get("wind");
			Map<String, Object> sys = (Map<String, Object>) weatherMap.get("sys");

			System.out.println(weatherMap);

			weatherData.setLocation(existingLocation);
			if (!(main.getOrDefault("temp", null) == null))
				weatherData.setTemp(main.get("temp"));
			if (!(main.getOrDefault("temp_min", null) == null))
				weatherData.setTemp_min(main.get("temp_min"));
			if (!(main.getOrDefault("temp_max", null) == null))
				weatherData.setTemp_max(main.get("temp_max"));
			if (!(main.getOrDefault("pressure", null) == null))
				weatherData.setPressure(main.get("pressure"));
			if (!(main.getOrDefault("feels_like", null) == null))
				weatherData.setFeels_like(main.get("feels_like"));
			if (!(main.getOrDefault("humidity", null) == null))
				weatherData.setHumidity(main.get("humidity"));
			if (!(main.getOrDefault("seal_level", null) == null))
				weatherData.setSea_level(main.get("sea_level"));
			if (!(main.getOrDefault("grnd_level", null) == null))
				weatherData.setGround_level(main.get("grnd_level"));
			if (!(weatherMap.getOrDefault("visibility", null) == null))
				weatherData.setVisibility(weatherMap.get("visibility"));
			if (!(wind.getOrDefault("speed", null) == null))
				weatherData.setWindSpeed(wind.get("speed"));
			if (!(wind.getOrDefault("gust", null) == null))
				weatherData.setGust(wind.get("gust"));
			if (!(sys.getOrDefault("sunrise", null) == null))
				weatherData.setSunrise(sys.get("sunrise"));
			if (!(sys.getOrDefault("sunset", null) == null))
				weatherData.setSunset(sys.get("sunset"));
			if (!(weatherMap.getOrDefault("name", null) == null))
				weatherData.setPlace(weatherMap.get("name"));

			weatherDataRepository.save(weatherData);

		}
		return new WeatherData();
	}
}
