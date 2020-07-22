package com.exalt.Clinic.controller;

import java.util.List;

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
	ClinicService clinicService;

	@PostMapping(path = {"/",""})
	public Clinic creatClinic(@RequestBody Clinic clinic) {
		return clinicService.create(clinic);
	}

	@GetMapping(path = "/{id}")
	public Clinic getClinic(@PathVariable String id) {
		return clinicService.get(id);
	}

	@GetMapping(path = "/geo", params = { "longitude", "latitude", "distance" })
	public List<Clinic> getClinicGeo(@RequestParam("longitude") double longitude,
			@RequestParam("latitude") double latitude, @RequestParam("distance") double distance) {
		return clinicService.gettNear(longitude, latitude, distance);
	}

	@DeleteMapping(path = "/{id}")
	public String deleteClinic(@PathVariable String id) {
		return clinicService.delete(id);
	}

	@PutMapping(path = "/{id}")
	public Clinic updateClinic(@PathVariable String id, @RequestBody Clinic clinic) {
		return clinicService.update(clinic);
	}
}
