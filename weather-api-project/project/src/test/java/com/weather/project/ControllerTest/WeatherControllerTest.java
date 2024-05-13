package com.weather.project.ControllerTest;

import static org.mockito.ArgumentMatchers.anyLong;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.weather.project.controller.WeatherController;
import com.weather.project.entity.WeatherData;
import com.weather.project.repository.WeatherDataRepository;
import com.weather.project.service.WeatherDataService;

@ExtendWith(MockitoExtension.class)
public class WeatherControllerTest {

	@InjectMocks
	WeatherController weatherController;

	@Mock
	WeatherDataRepository weatherDataRepository;

	@Mock
	WeatherDataService weatherDataService;

	@Test
	public void Test_Controller() throws Exception {

		weatherController = new WeatherController();
		LocalDate date;
//		Mockito.when(weatherDataService.showWeatherData(anyLong(), null)).thenReturn(new WeatherData());
//		weatherController.fetchWeatherInfo(anyLong(), "2024-01-04");
	}

}
