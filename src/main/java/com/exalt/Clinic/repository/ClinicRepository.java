package com.exalt.Clinic.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.exalt.Clinic.model.Clinic;

@Repository
public interface ClinicRepository extends MongoRepository<Clinic, String> {
	@Query("{  \"address.position\": {$nearSphere: {$geometry: {type: \"Point\",coordinates: [?0, ?1]},$maxDistance: ?2}}}")
	List<Clinic> findGeo(double longitude, double latitude, double distance);

	@Query(sort = "{_id:-1}", value = "{}")
	List<Clinic> getId();

	@Query(sort = "{_id:-1}", value = "{}")
	String getIddd();
}
