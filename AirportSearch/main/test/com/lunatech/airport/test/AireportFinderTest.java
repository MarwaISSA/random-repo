/**
 * 
 */
package com.lunatech.airport.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;
import java.util.List;
import org.junit.Test;

import com.lunatech.airport.finder.AireportFinder;
import com.lunatech.airport.finder.Airport;
import com.lunatech.airport.finder.Runway;

/**
 * @author Marwa
 *
 */
public class AireportFinderTest {

	private AireportFinder aireportFinder = new AireportFinder();

	/**
	 * Test method for {@link com.lunatech.airport.test.AireportFinder#getRunwaysByAirport(java.lang.String)}.
	 */
	@Test
	public void testGetRunwaysByAirport() {
		try {
			List<Runway> runways = aireportFinder.getRunwaysByAirport("27100");
			assertNotNull(runways);
			assertThat(runways.iterator().next().getId(), equalTo("235263"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Test method for {@link com.lunatech.airport.test.AireportFinder#getAirportsByCountry(java.lang.String)}.
	 */
	@Test
	public void testGetAirportsByCountry() {
		try {
			List<Airport> airports = aireportFinder.getAirportsByCountry("CC");
			assertNotNull(airports);
			assertThat(airports.iterator().next().getId(), equalTo("27100"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Test method for {@link com.lunatech.airport.test.AireportFinder#findCountryId(java.lang.String)}.
	 */
	@Test
	public void testFindCountryId() {
		try {
			String countryId = aireportFinder.findCountryId("test");
			assertNull(countryId);
			countryId = aireportFinder.findCountryId("CC");
			assertTrue(countryId.equals("CC"));
			countryId = aireportFinder.findCountryId("Cocos (Keeling) Islands");
			assertTrue("CC".equals(countryId));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
