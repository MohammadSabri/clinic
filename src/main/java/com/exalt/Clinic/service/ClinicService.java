package com.exalt.Clinic.service;

import java.util.List;

import com.exalt.Clinic.model.Clinic;

public interface ClinicService {
	Clinic create(Clinic clinic);

	Clinic get(String id);

	List<Clinic> gettNear(double longitude, double latitude, double distance);

	Clinic update(String id, Clinic clinic);

	String delete(String id);
	
	boolean existClinic (String id);
	
	String lastId();

}
