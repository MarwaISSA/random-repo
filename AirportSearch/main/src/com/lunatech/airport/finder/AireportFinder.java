package com.lunatech.airport.finder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class AireportFinder {

	private static final String COUNTRIES_CSV = "/countries.csv";
	private static final String AIRPORTS_CSV = "/airports.csv";
	private static final String RUNWAYS_CSV = "/runways.csv";

	/**
	 * This method searchs runways by airportId
	 * 
	 * @param airportId
	 * @return
	 * @throws IOException
	 */
	public List<Runway> getRunwaysByAirport(String airportId)
			throws IOException {
		CSVReader reader = null;
		List<Runway> runwaysList = new ArrayList<Runway>();
		try {
			reader = createCSVReader(RUNWAYS_CSV);
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				if ((nextLine[1] != null && nextLine[1].equals(airportId))) {
					Runway runway = mapRunway(nextLine);
					runwaysList.add(runway);
				}
			}
		} finally {
			closeCSVReader(reader);
		}
		return runwaysList;

	}

	/**
	 * Creates the runway object with data from csv file
	 * 
	 * @param nextLine
	 * @return
	 */
	private Runway mapRunway(String[] nextLine) {
		Runway runway = new Runway();
		runway.setId(nextLine[0]);
		runway.setLenght(nextLine[2]);
		runway.setWidth(nextLine[3]);
		return runway;
	}

	/**
	 * This method searchs airports by countryId
	 * 
	 * @param countryId
	 * @return
	 * @throws IOException
	 */
	public List<Airport> getAirportsByCountry(String countryId)
			throws IOException {
		CSVReader reader = null;
		List<Airport> airportsList = new ArrayList<Airport>();
		try {
			reader = createCSVReader(AIRPORTS_CSV);
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				if ((nextLine[8] != null && nextLine[8].equals(countryId))) {
					Airport airport = mapAirport(nextLine);
					airportsList.add(airport);
				}
			}
		} finally {
			closeCSVReader(reader);
		}
		return airportsList;

	}

	/**
	 * Creates the airport object with data from csv file
	 * 
	 * @param nextLine
	 * @return
	 */
	private Airport mapAirport(String[] nextLine) {
		Airport airport = new Airport();
		airport.setId(nextLine[0]);
		airport.setIdent(nextLine[1]);
		airport.setType(nextLine[2]);
		airport.setName(nextLine[3]);
		return airport;
	}

	/**
	 * This method searchs the specified country in the csv file
	 * 
	 * @param country
	 *            could the countryId or country name
	 * @return the countryId in the csv file and null if not
	 * @throws IOException
	 */
	public String findCountryId(String country) throws IOException {
		CSVReader reader = null;
		String countryIdFound = null;
		try {
			reader = createCSVReader(COUNTRIES_CSV);
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				if ((nextLine[1] != null && nextLine[1].equals(country))
						|| (nextLine[2] != null && nextLine[2]
								.equalsIgnoreCase(country))) {
					countryIdFound = nextLine[1];
					break;
				}
			}
		} finally {
			closeCSVReader(reader);
		}
		return countryIdFound;
	}

	/**
	 * Close CSV reader
	 * 
	 * @param reader
	 */
	private void closeCSVReader(CSVReader reader) {
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Creates the CSV reader
	 * 
	 * @param fileName
	 * @return
	 */
	private CSVReader createCSVReader(String fileName) {
		InputStream is = AireportFinder.class.getResourceAsStream(fileName);
		InputStreamReader inputStreamReader = new InputStreamReader(is);
		CSVReader reader = new CSVReader(inputStreamReader);
		return reader;
	}

}
