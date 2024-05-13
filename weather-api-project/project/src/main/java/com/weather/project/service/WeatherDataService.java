package com.weather.project.service;

import java.time.LocalDate;

import com.weather.project.entity.WeatherData;

public interface WeatherDataService {

	WeatherData saveWeatherDetails(WeatherData weatherData);

	WeatherData updateWeatherData(Long LocationId);

	void deleteWeatherDetails(Long LocationId);

	WeatherData showWeatherData(Long pinCode, LocalDate date) throws Exception;

}
