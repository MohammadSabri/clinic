package com.exalt.Clinic.model;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Field;

public class Address {
	private String id;
	private String country;
	@Field(name = "postal_code")
	private int postalCode;
	@Field(name = "zip_code")
	private int zipCode;
	private String city;
	private String street;
	@Field(name = "time_zone")
	private String timeZone;
	@Field(name = "building_number")
	private int buildingNumber;
	@GeoSpatialIndexed(name = "address.position", type = GeoSpatialIndexType.GEO_2DSPHERE)
	private GeoJsonPoint position;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public int getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(int buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public GeoJsonPoint getPosition() {
		return position;
	}

	public void setPosition(GeoJsonPoint position) {
		this.position = position;
	}

}
