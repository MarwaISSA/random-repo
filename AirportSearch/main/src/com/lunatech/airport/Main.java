package com.lunatech.airport;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.lunatech.airport.finder.AireportFinder;
import com.lunatech.airport.finder.Airport;
import com.lunatech.airport.finder.Runway;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the country id/name :");
		String country = sc.nextLine();
		sc.close();
		if (country == null || country.trim().isEmpty()) {
			System.err.println("The country id/name is mandatory");
			System.exit(1);
		}

		try {
			displayAireportsRunways(country);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void displayAireportsRunways(String country)
			throws IOException {
		AireportFinder aireportFinder = new AireportFinder();
		String countryIdfound = aireportFinder.findCountryId(country.trim());
		if (countryIdfound == null) {
			System.err.println("The country doesn't exist");
			System.exit(1);
		}
		List<Airport> airportsByCountry = aireportFinder
				.getAirportsByCountry(countryIdfound);
		System.out
				.println("*****************************************************************");
		System.out.println("The list of airports of the country "
				+ countryIdfound + " is : ");
		for (Airport airport : airportsByCountry) {
			System.out.println(airport);
		}
		System.out
				.println("*****************************************************************");

		System.out.println("The list of runways by airport is : ");
		for (Airport airport : airportsByCountry) {
			List<Runway> runwaysByAirport = aireportFinder
					.getRunwaysByAirport(airport.getId());
			for (Runway runway : runwaysByAirport) {
				System.out.println("| " + airport.getName() + " | " + runway
						+ " |");
			}

		}
		System.out
				.println("*****************************************************************");
	}
}
