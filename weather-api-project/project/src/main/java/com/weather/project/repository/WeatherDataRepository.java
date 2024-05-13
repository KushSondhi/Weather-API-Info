package com.weather.project.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.weather.project.entity.WeatherData;

@Repository
public interface WeatherDataRepository extends CrudRepository<WeatherData, Long> {

	// Optional<WeatherData> findByPinCodeAndDate(Long pinCode, LocalDate date);

	Optional<WeatherData> findById(long locationId);

}
