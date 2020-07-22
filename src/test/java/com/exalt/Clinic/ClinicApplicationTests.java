package com.exalt.Clinic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.exalt.Clinic.model.Address;
import com.exalt.Clinic.model.Clinic;
import com.exalt.Clinic.repository.ClinicRepository;
import com.mongodb.client.MongoClient;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;



@ActiveProfiles("dev")
@ExtendWith(SpringExtension.class)
@DataMongoTest

public class ClinicApplicationTests {
//    private MongodExecutable mongodExe;
//    private MongodProcess mongod;
//    private MongoClient mongo;
//    @AfterEach
//    public void afterEach() throws Exception {
//        if (this.mongod != null) {
//            this.mongod.stop();
//            this.mongodExe.stop();
//        }
//    }


	@Autowired 
	ClinicRepository clinicRepository; 
	@Test
	@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
	public void creatTest() {
		Clinic clinic = new Clinic();
		clinic.setName("testCreat");
		Address address = new Address();
		address.setBuildingNumber(231);
		address.setCity("testCity");
		address.setCountry("testCountry");
		address.setId("testID");
		address.setStreet("streetTest");
		address.setTimeZone("timezoonTest");
		address.setPostalCode(3123);
		address.setZipCode(1312);
		GeoJsonPoint position =new GeoJsonPoint(10, 10);
		address.setPosition(position);
		
		clinicRepository.save(clinic);
		Clinic clinic2 = clinicRepository.findAll().get(0);
		System.out.println(clinicRepository.findAll().size());

		System.out.println(clinic2.getName());
	}

}
