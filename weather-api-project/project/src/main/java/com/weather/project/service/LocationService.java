package com.weather.project.service;

import com.weather.project.entity.LocationDetails;

public interface LocationService {
	
	LocationDetails saveLocationDetails();
	LocationDetails updateLocationDetails();

}
