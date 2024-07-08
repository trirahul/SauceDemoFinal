package com.sauce.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

	@DataProvider(name = "loginData")
	public static Iterator<Object[]> provideData() throws IOException {
		// List to store the records read from the CSV file
		List<Object[]> records = new ArrayList<>();

		// Path to the CSV file containing test data
		String csvFile = "src/test/resources/testdata/testdata.csv";
		String line;
		BufferedReader br = null;

		// Separator used in the CSV file
		String csvSplitBy = ",";

		try {
			// Initialize BufferedReader
			br = new BufferedReader(new FileReader(csvFile));

			// Skip the header line
			br.readLine();

			// Read each line from the CSV file
			while ((line = br.readLine()) != null) {
				// Split the line by the separator to extract the values
				String[] data = line.split(csvSplitBy);

				// Trim the values to remove any leading or trailing spaces
				String username = data[0].trim();
				String password = data[1].trim();
				String expectedTitle = data[2].trim();

				// Add the extracted values as a new record in the list
				records.add(new Object[] { username, password, expectedTitle });
			}
		} catch (IOException e) {
			// Print the stack trace if an exception occurs
			e.printStackTrace();
			throw e;
		} finally {
			// Close the BufferedReader in the finally block to ensure it gets closed
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// Return an iterator over the list of records
		return records.iterator();
	}
}
