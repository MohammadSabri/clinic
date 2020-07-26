package com.exalt.Clinic.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exalt.Clinic.model.Clinic;
import com.exalt.Clinic.service.ClinicService;

@RestController
@RequestMapping("/api/v1/clinic")
public class ClinicController {
	@Autowired
	private ClinicService clinicService;

	/**
	 * Post new clinic controller with API /api/v1/clinic/ and /api/v1/clinic
	 * 
	 * @param clinic
	 * @return
	 */
	@PostMapping(path = { "/", "" })
	public Clinic creatClinic(@RequestBody @Valid Clinic clinic) {
		return clinicService.create(clinic);
	}

	/**
	 * get the clinics by their id once a time
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/{id}")
	public Clinic getClinic(@PathVariable String id) {
		return clinicService.get(id);
	}

	/**
	 * get the near by location of the clinic by providing you location by the
	 * coordinate of longitude, latitude and the distance to search within sphere
	 * shape as the distance in meters
	 * 
	 * @param longitude
	 * @param latitude
	 * @param distance
	 * @return
	 */
	@GetMapping(path = "/geo", params = { "longitude", "latitude", "distance" })
	public List<Clinic> getClinicGeo(@RequestParam("longitude") double longitude,
			@RequestParam("latitude") double latitude, @RequestParam("distance") double distance) {
		return clinicService.gettNear(longitude, latitude, distance);
	}

	/**
	 * Delete the clinic from the DB by giving clinic Id
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(path = "/{id}")
	public String deleteClinic(@PathVariable String id) {
		return clinicService.delete(id);
	}

	/**
	 * Update clinic by its id
	 * 
	 * @param id
	 * @param clinic
	 * @return
	 */
	@PutMapping(path = "/{id}")
	public Clinic updateClinic(@PathVariable String id, @RequestBody @Valid Clinic clinic) {
		return clinicService.update(id, clinic);
	}
}
