package com.exalt.Clinic;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClientException;

import com.exalt.Clinic.model.Address;
import com.exalt.Clinic.model.Clinic;
import com.exalt.Clinic.repository.ClinicRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@ActiveProfiles("dev")
public class ClinicTest {
	@Autowired
	private TestRestTemplate testRestTemplate;
	@Autowired
	ClinicRepository clinicRepository;
	@LocalServerPort
	private int port;

	@BeforeEach
	void setupBefor() throws RestClientException, URISyntaxException {

		Clinic clinic = new Clinic();
		clinic.setName("testCreatBefor");
		Address address = new Address();
		address.setBuildingNumber(231);
		address.setCity("testCityBefor");
		address.setCountry("testCountryBefor");
		address.setId("testIDBefor");
		address.setStreet("streetTestBefor");
		address.setTimeZone("timezoonTestBefor");
		address.setPostalCode(3123);
		address.setZipCode(1312);
		GeoJsonPoint position = new GeoJsonPoint(10, 10);
		address.setPosition(position);
		Clinic clinicTemp = create(clinic);
		// clinicRepository.save(clinic);
		System.out.println(clinicTemp.getId());

	}

//	@AfterEach
//	void setupAfter() {
//		clinicRepository.deleteAll();
//	}
	@Test
	void test() {
		System.out.println(clinicRepository.getId().get(0).getId());
	}

	@Test
	void creatTest() throws RestClientException, URISyntaxException {
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
		GeoJsonPoint position = new GeoJsonPoint(10, 10);
		address.setPosition(position);
		Clinic clinicTemp = create(clinic);
		assertTrue(clinicRepository.existsById(clinicTemp.getId()));

	}

	private Clinic get() throws RestClientException, URISyntaxException {
		return testRestTemplate.getForEntity(new URI("http://localhost:" + port + "/api/v1/clinic/"), Clinic.class)
				.getBody();
	}

	private Clinic create(Clinic clinic) throws RestClientException, URISyntaxException {
		return testRestTemplate
				.postForEntity(new URI("http://localhost:" + port + "/api/v1/clinic/"), clinic, Clinic.class).getBody();
	}

}
