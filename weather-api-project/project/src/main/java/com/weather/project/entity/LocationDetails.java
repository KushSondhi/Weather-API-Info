package com.weather.project.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Location")
public class LocationDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long LocationId;

	@Column(nullable = false)
	private Long pinCode;

	@Column(nullable = false)
	private double latitude;

	@Column
	private double longitude;

	@Column(name = "Date")
	private LocalDate date;

	private String city;

	public Long getLocationId() {
		return LocationId;
	}

	public void setLocationId(Long locationId) {
		LocationId = locationId;
	}

	public Long getPinCode() {
		return pinCode;
	}

	public void setPinCode(Long pinCode) {
		this.pinCode = pinCode;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public LocationDetails(Long pinCode, double latitude, double longitude, LocalDate date, String city) {
		super();
		this.pinCode = pinCode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.date = date;
		this.city = city;
	}

}
