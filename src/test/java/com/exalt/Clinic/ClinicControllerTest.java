package com.exalt.Clinic;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClientException;

import com.exalt.Clinic.model.Address;
import com.exalt.Clinic.model.Clinic;
import com.exalt.Clinic.repository.ClinicRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@ActiveProfiles("dev")
public class ClinicControllerTest {
	@Autowired
	RestTemplateBuilder restTemplateBuilder;
	private TestRestTemplate testRestTemplate;
	@Autowired
	private ClinicRepository clinicRepository;
	@LocalServerPort
	private int port;

	@PostConstruct
	public void initialize() {
		RestTemplateBuilder builder = restTemplateBuilder.rootUri("http://localhost:" + port + "/api/v1/clinic");
		this.testRestTemplate = new TestRestTemplate(builder);

	}

	@BeforeEach
	public void setupBefor() throws RestClientException, URISyntaxException {
		Clinic clinic = new Clinic();
		clinic.setName("testCreatBefor");
		Address address = new Address();
		address.setBuildingNumber(123);
		address.setCity("testCityBefor");
		address.setCountry("testCountryBefor");
		address.setId("testIDBefor");
		address.setStreet("streetTestBefor");
		address.setTimeZone("timezoonTestBefor");
		address.setPostalCode(1234);
		address.setZipCode(4321);
		GeoJsonPoint position = new GeoJsonPoint(10, 10);
		address.setPosition(position);
		clinic.setAddress(address);
		create(clinic);
	}

	@AfterEach
	void setupAfter() {
		clinicRepository.deleteAll();
	}

	@Test
	@DisplayName(value = "testClinicCreate_AddNewClinic_successfull")
	public void creatTest() throws RestClientException, URISyntaxException {
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
		GeoJsonPoint position = new GeoJsonPoint(30, 30);
		address.setPosition(position);
		clinic.setAddress(address);
		Clinic clinicTemp = create(clinic);
		assertAll(

				() -> assertEquals(clinicTemp.getId(), getLastId()),
				() -> assertEquals(clinicTemp.getName(), "testCreat"),
				() -> assertEquals(clinicTemp.getAddress().getBuildingNumber(), 231),
				() -> assertEquals(clinicTemp.getAddress().getCity(), "testCity"),
				() -> assertEquals(clinicTemp.getAddress().getCountry(), "testCountry"),
				() -> assertEquals(clinicTemp.getAddress().getId(), "testID"),
				() -> assertEquals(clinicTemp.getAddress().getStreet(), "streetTest"),
				() -> assertEquals(clinicTemp.getAddress().getTimeZone(), "timezoonTest"),
				() -> assertEquals(clinicTemp.getAddress().getPostalCode(), 3123),
				() -> assertEquals(clinicTemp.getAddress().getZipCode(), 1312),
				() -> assertEquals(clinicTemp.getAddress().getPosition().getCoordinates().get(0), 30),
				() -> assertEquals(clinicTemp.getAddress().getPosition().getCoordinates().get(1), 30)

		);

		assertTrue(clinicRepository.existsById(clinicTemp.getId()));
	}

	@Test
	@DisplayName(value = "testGetClinic_getClinicById_successfull")
	public void getTest() throws RestClientException, URISyntaxException {
		Clinic clinic = get(getLastId());
		assertAll(

				() -> assertEquals(clinic.getId(), getLastId()), () -> assertEquals(clinic.getName(), "testCreatBefor"),
				() -> assertEquals(clinic.getAddress().getBuildingNumber(), 123),
				() -> assertEquals(clinic.getAddress().getCity(), "testCityBefor"),
				() -> assertEquals(clinic.getAddress().getCountry(), "testCountryBefor"),
				() -> assertEquals(clinic.getAddress().getId(), "testIDBefor"),
				() -> assertEquals(clinic.getAddress().getStreet(), "streetTestBefor"),
				() -> assertEquals(clinic.getAddress().getTimeZone(), "timezoonTestBefor"),
				() -> assertEquals(clinic.getAddress().getPostalCode(), 1234),
				() -> assertEquals(clinic.getAddress().getZipCode(), 4321),
				() -> assertEquals(clinic.getAddress().getPosition().getCoordinates().get(0), 10),
				() -> assertEquals(clinic.getAddress().getPosition().getCoordinates().get(1), 10)

		);
	}

	@Test
	@DisplayName(value = "testClinicUpdate_updateClinicById_successfull")
	public void updateTest() throws RestClientException, URISyntaxException {
		Clinic clinic = get(getLastId());
		clinic.setName("updateTestName");
		Address address = new Address();
		address.setBuildingNumber(231);
		address.setCity("updatetestCity");
		address.setCountry("updatetestCountry");
		address.setId("updatetestID");
		address.setStreet("updatestreetTest");
		address.setTimeZone("UpdatetimezoonTest");
		address.setPostalCode(1111);
		address.setZipCode(1111);
		GeoJsonPoint position = new GeoJsonPoint(22, 22);
		address.setPosition(position);
		clinic.setAddress(address);
		Clinic clinicTemp = update(getLastId(), clinic);

		assertAll(

				() -> assertEquals(clinicTemp.getId(), getLastId()),
				() -> assertEquals(clinicTemp.getName(), clinic.getName()),
				() -> assertEquals(clinicTemp.getAddress().getBuildingNumber(),
						clinic.getAddress().getBuildingNumber()),
				() -> assertEquals(clinicTemp.getAddress().getCity(), clinic.getAddress().getCity()),
				() -> assertEquals(clinicTemp.getAddress().getCountry(), clinic.getAddress().getCountry()),
				() -> assertEquals(clinicTemp.getAddress().getId(), clinic.getAddress().getId()),
				() -> assertEquals(clinicTemp.getAddress().getStreet(), clinic.getAddress().getStreet()),
				() -> assertEquals(clinicTemp.getAddress().getTimeZone(), clinic.getAddress().getTimeZone()),
				() -> assertEquals(clinicTemp.getAddress().getPostalCode(), clinic.getAddress().getPostalCode()),
				() -> assertEquals(clinicTemp.getAddress().getZipCode(), clinic.getAddress().getZipCode()),
				() -> assertEquals(clinicTemp.getAddress().getPosition().getCoordinates().get(0),
						clinic.getAddress().getPosition().getCoordinates().get(0)),
				() -> assertEquals(clinicTemp.getAddress().getPosition().getCoordinates().get(1),
						clinic.getAddress().getPosition().getCoordinates().get(1))

		);

	}

	@Test
	@DisplayName(value = "testClinicDelete_DeleteClinic_successfull")
	public void deleteTest() throws RestClientException, URISyntaxException {
		String result = delete(getLastId());
		assertEquals("Deleted successsfuly", result);
	}

	@Test
	@DisplayName(value = "testGeoLocation_ClinicGeoLocationTesting_successfull")
	public void getGeoTest() throws RestClientException, URISyntaxException {

		creatTenClinic();
		assertAll(

				() -> assertEquals(2, getGeo(10, 10, 1000).size()), () -> assertEquals(4, getGeo(10, 10, 22000).size()),
				() -> assertEquals(9, getGeo(10, 10, 77000).size())

		);

	}

	/**
	 * get clinic by specific id
	 * 
	 * @param id
	 * @return
	 * @throws RestClientException
	 * @throws URISyntaxException
	 */
	private Clinic get(String id) throws RestClientException, URISyntaxException {

		return testRestTemplate.getForEntity("/" + id, Clinic.class).getBody();
	}

	/**
	 * create new clinic
	 * 
	 * @param clinic
	 * @return
	 * @throws RestClientException
	 * @throws URISyntaxException
	 */
	private Clinic create(Clinic clinic) throws RestClientException, URISyntaxException {
		return testRestTemplate.postForEntity("/", clinic, Clinic.class).getBody();
	}

	/**
	 * delete clinic by given id
	 * 
	 * @param id
	 * @return
	 * @throws RestClientException
	 * @throws URISyntaxException
	 */
	private String delete(String id) throws RestClientException, URISyntaxException {
		return testRestTemplate.exchange(new RequestEntity<>(HttpMethod.DELETE, new URI("/" + id)), String.class)
				.getBody();
	}

	/**
	 * update clinic information
	 * 
	 * @param id
	 * @param clinic
	 * @return
	 * @throws RestClientException
	 * @throws URISyntaxException
	 */
	private Clinic update(String id, Clinic clinic) throws RestClientException, URISyntaxException {
		return testRestTemplate.exchange(new RequestEntity<>(clinic, HttpMethod.PUT, new URI("/" + id)), Clinic.class)
				.getBody();

	}

	/**
	 * Get list of all clinic by give location by longitude and latitude and the
	 * distance to search within
	 * 
	 * @param longitude
	 * @param latitude
	 * @param distance
	 * @return
	 * @throws RestClientException
	 * @throws URISyntaxException
	 */
	private List<Clinic> getGeo(double longitude, double latitude, double distance)
			throws RestClientException, URISyntaxException {
		return testRestTemplate
				.exchange("/geo?longitude=" + longitude + "&latitude=" + latitude + "&distance=" + distance,
						HttpMethod.GET, null, new ParameterizedTypeReference<List<Clinic>>() {
						})
				.getBody();
	}

	/**
	 * create 10 clinic with there location to use them in nearBy location testing
	 * as each clinic has 0.1 degree between each other which represent 11Km
	 * 
	 * @throws RestClientException
	 * @throws URISyntaxException
	 */
	private void creatTenClinic() throws RestClientException, URISyntaxException {
		Clinic clinic = new Clinic();
		Address address = new Address();
		double x = 10;
		double y = 10;
		for (int i = 0; i < 10; i++) {

			GeoJsonPoint position = new GeoJsonPoint(x, y);
			clinic.setName("testCreatBefor" + i);
			address.setBuildingNumber(123);
			address.setCity("testCityBefor" + i);
			address.setCountry("testCountryBefor" + i);
			address.setId("testIDBefor" + i);
			address.setStreet("streetTestBefor" + i);
			address.setTimeZone("timezoonTestBefor" + i);
			address.setPostalCode(1234);
			address.setZipCode(4321);
			address.setPosition(position);
			clinic.setAddress(address);
			create(clinic);
			x += 0.1;
		}
	}

	/**
	 * return the id for the last add field as the ObjectId consist of 12 byte as
	 * the query return for example {"_id": {"$oid": "5f192bf907e6670835518364"}} so
	 * take from 18 to 42 which represent the 12 bit of the Id object
	 * 
	 * @return
	 */
	private String getLastId() {
		return clinicRepository.getLastId().get(0).substring(18, 42);
	}

}
