package com.weather.project.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.weather.project.entity.LocationDetails;

@Repository
public interface LocationRepository extends CrudRepository<LocationDetails, Long> {

	Optional<LocationDetails> findById(long locationId);

}
