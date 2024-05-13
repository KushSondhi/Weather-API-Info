package com.weather.project.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.weather.project.entity.WeatherData;
import com.weather.project.service.WeatherDataService;

@RestController
public class WeatherController {

	@Autowired
	WeatherDataService weatherDataService;

	@PostMapping
	public WeatherData saveWeatherInfo(@RequestBody WeatherData weatherData) {

		return weatherDataService.saveWeatherDetails(weatherData);
	}

	@GetMapping("/location/{pin}/{date}")
	public Map<String, Object> fetchWeatherInfo(@PathVariable("pin") Long pinCode,
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") String date) throws Exception {

		Map<String, Object> weatherData = new HashMap<String, Object>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate localDate = LocalDate.parse(date, formatter);

		System.out.println("Date : " + localDate.getClass());
		System.out.println(pinCode);
		WeatherData weather = weatherDataService.showWeatherData(pinCode, localDate);

		// temp.put("location Id", loc.getLocationId());

		return weatherData;
	}

	@PutMapping("/updateWeatherData/{id}")
	public WeatherData updateLocationOrWeatherInfo(@PathVariable("id") Long locationId) {

		return weatherDataService.updateWeatherData(locationId);

	}

	@DeleteMapping("/locations/{id}")
	public String deleteWeatherInfo(@PathVariable("id") Long locationId) {

		weatherDataService.deleteWeatherDetails(locationId);
		return "Deleted Successfully!";
	}

}
