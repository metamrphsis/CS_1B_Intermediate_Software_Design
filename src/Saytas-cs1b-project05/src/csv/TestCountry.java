package csv;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *  Tests the Country class, which holds Indicator data for each year in 
 *  chronological order.
 *
 *  @author Foothill College, Bita Mazloom, Selahittin Saytaş
 */
public class TestCountry
{	
	/**
	 * Displays the names of countries. 
	 * Each country index is output along with the country's index in the array.
	 * @param countries  array of Country objects
	 */
	protected static void displayCountryNames(Country [] countries)
	{
		String countryNames = "";
		int counter = 0;

		for (int i = 0; i < countries.length; i++)
		{
			// Concatenates the name of countries
			countryNames += " " + countries[i].getName();
			// uses a ternary operator to prettify the output
			countryNames += (counter+1) % 50 == 0 ? "\n" : ",";
			counter++;
		}
		System.out.println("\nCountry names:\n" + countryNames + "\n");
	}

	/**
	 * Display the entire data table of the country such that it is readable.
	 * Note: This requires that you experiment with the formatting of the output
	 * @param countries  array of Country objects
	 */
	protected static void displayCountries(Country [] countries)
	{
		StringBuilder countryInfo = new StringBuilder();
		int startingYear = countries[0].getStartYear();
		int numYears = countries[0].getEndYear() - startingYear + 1;

		for (int countryIndex = 0; countryIndex < countries.length; countryIndex++)
		{
			// appends year header
			if (countryIndex % 5 == 0)
			{
				countryInfo.append(String.format("\n%20s", "Country Name"));
				for(int yearIndex = 0; yearIndex < numYears; yearIndex++)
				{
					countryInfo.append(String.format("%18d  ", (startingYear + yearIndex)));
					//countryInfo.append(String.format("%20d  ", (startingYear + yearIndex)));
				}
				countryInfo.append("\n");
			}
			// appends indicator data
			countryInfo.append(countries[countryIndex].toString());
		}

		System.out.println("\nCountries:\n" + countryInfo + "\n");
	}

	/**
	 * Performs a linear search of the array of Country objects based on the name of the Country.
	 * @param requestedCountryName  Name of the Country object to search for
	 * @param countries	One-dimensional array of Country objects.
	 * @return	The Country object if found. Otherwise, null.
	 */
	private static Country findCountry(String requestedCountryName, Country[] countries) 
	{
		for (int i = 0; i < countries.length; i++)
		{
			if (countries[i] != null && countries[i].equals(requestedCountryName))
			{
				return countries[i];
			}
		}
		return null;
	}

    /**
     * Prints out the contents of a double array
     * @param countryName For the name of the country
     * @param requestedEnrollmentPeriod one-dimensional array of enrollment data
     */
	private static void displayEnrollmentPeriod(String countryName, Indicator[] requestedEnrollmentPeriod) 
	{
		System.out.printf("%20s ", countryName);
		for(int i = 0; i < requestedEnrollmentPeriod.length; i++)
		{
			if (requestedEnrollmentPeriod[i] == null)
			{
				System.out.printf("     ," + "   ");
				//System.out.printf("20s", ",");
			}
			else
			{
				System.out.printf("%s   ", requestedEnrollmentPeriod[i].toString());
				//System.out.printf("%20s", requestedEnrollmentPeriod[i].toString());
			}
		}
		System.out.println("");
	}

	/**
	 * Displays the menu option for different indicators.
	 * Note: Update the menu option if you add additional input files or categories.
	 */
	private static void printMenu()
	{
		System.out.print("Select an indicator to parse. ");
		System.out.println("Available indicators:\n");
		System.out.println("0. Invalid data (for debugging)");
		System.out.println("1. GDP per Capita (short for debugging)");
		System.out.println("2. School Enrollment (short for debugging)");
		System.out.println("3. GDP per Capita");
		System.out.println("4. School Enrollment");
	}
	
	/**
	 * Creates an object of type CSVParser which parses a CSV file. If
	 * the format of the CSVParser is valid, creates a Country object
	 * from each parsed line. 
	 * Tests the Country class using with various date ranges. For each 
	 * test case, either prints that data for the overlapping years found 
	 * or catches an IllegalArgumentExceptions.
     * @param args An array of String arguments
	 */
	public static void main(String[] args) 
	{
		// NOTE: Make sure to use relative path instead of specifying the entire path
		//       such as (/Users/alicew/myworkspace/so_on_and_so_forth).

		// Example of invalid input file
		final String [] INVALID_INPUT= {"resources/childrenEnrolled_invalid.csv"};

		// Example of input file for GDP per capita:
		final String [] GDP_INPUT_SHORT = { "resources/gdpPerCapita_short_12years.csv"};

		// Example of input file for net school enrollment for:
		// [0] primary grade
		// [1] secondary grade
		final String [] ENROLLMENT_INPUT_SHORT = { "resources/childrenEnrolledInPrimary_short_12years.csv", 
				"resources/childrenEnrolledInSecondary_short_12years.csv"
		};

		// Example of input file for GDP per capita:
		final String [] GDP_INPUT = { "resources/gdpPerCapita.csv"};

		// Example of input file for net school enrollment for:
		// [0] primary grade
		// [1] secondary grade
		final String [] ENROLLMENT_INPUT = { "resources/childrenEnrolledInPrimary.csv", 
				"resources/childrenEnrolledInSecondary.csv"
		};

		// Prompts the user and asks for a selection.
		printMenu();
		Scanner input = new Scanner(System.in);
		String line = input.nextLine();
		int selection = Integer.parseInt(line);
		final String [] filenames;

		IndicatorType selectedIndicator;
		if (selection == 1 || selection == 3)
		{
			selectedIndicator = IndicatorType.GDP_PER_CAPITA;
			filenames = selection == 1 ? GDP_INPUT_SHORT : GDP_INPUT;
		}
		else if (selection == 2 || selection == 4)
		{
			// Note: alternatively we can set the selected indicator as SCHOOL_ENROLLMENT_SECONDARY
			selectedIndicator = IndicatorType.SCHOOL_ENROLLMENT_PRIMARY;
			filenames = selection == 2 ? ENROLLMENT_INPUT_SHORT : ENROLLMENT_INPUT;
		}
		else 
		{
			selectedIndicator = IndicatorType.INVALID;
			filenames = INVALID_INPUT;
		}

		// For debugging purposes
		System.out.println("The selected indicator is " + selectedIndicator);

		Country [] countries = null;

		for (int currentFileIndex = 0; currentFileIndex < filenames.length; currentFileIndex++)
		{
			// Parse the CSV data file BASED on the input filename.
			CSVParser parser;
			try 
			{
				parser = new CSVParser(filenames[currentFileIndex]);
				// Given the childrenEnrolledInPrimary_short_12years.csv file, the output is:
				// 			School Enrollment In Primary (% net) updated at 7/25/18
				//
				// Given the gdpPerCapita_short_12years.csv file, the output is:
				// 			GDP per capita (current US$) updated at 8/28/18

			} 
			catch (FileNotFoundException e) 
			{
				System.err.printf("File name %s not found.", filenames[currentFileIndex]);
				return;
			}
			catch (InvalidFileFormatException e)
			{
				System.err.printf("Invalid file format %s\n", e.getMessage());
				return;
			}

			// For debugging purposes
			System.out.println("\nParsing filename " + filenames[currentFileIndex]);

			IndicatorType indicatorType = parser.getIndicatorType();

			String [] countryNames = parser.getCountryNames();
			int [] yearLabels = parser.getYearLabels();
			double [][] parsedTable = parser.getParsedTable();		

			// Check if the array of countries has been initialized by a previous iteration.
			if (countries == null)
			{
				// An array of Country objects.
				// NOTE: Here, we are no longer using the two dimensional array from EnrollmentData class.
				//       Instead, each country will hold it's own data.
				//       So, we no longer need the EnrollmentData class.
				countries = new Country[countryNames.length];				 
			}

			// Note: We are moving two dimensional array of Indicator objects
			//       to a single dimensional array of Country objects where each
			//       Country objects stores a single dimensional array of Indicators.
			// Reference to a Country object
			Country foundCountry;

			// Go through each country name parsed from the CSV file.
			// If the country name has not bee seen before create a new object of
			// type Country to hold an array of Indicator objects.
			for (int countryIndex = 0; countryIndex < countries.length; countryIndex++)
			{
				int numberOfYears = yearLabels.length;   // alternatively numberOfYears = dataTable[countryIndex].length;

				foundCountry = findCountry(countryNames[countryIndex], countries);

				if (foundCountry == null)
				{
					foundCountry = new Country(countryNames[countryIndex], numberOfYears);
				}

				// Get the parsed array of doubles for the current Country object
				double [] dataForAllYears = parsedTable[countryIndex];

				// Go through each year of data read from the CSV file.
				for (int yearIndex = 0; yearIndex < dataForAllYears.length; yearIndex++)
				{
					// Refers to the current indicator.
					Indicator dataForOneYear = null;

					// Checks if a previous input file has already added the current year.
					// Note: Assume the CSV files are well formed with same number of years
					//       and that the years are consecutive.
					dataForOneYear = foundCountry.getIndicatorForYear(yearLabels[yearIndex]);

					// Recall that each CSV input file we read will provide us one data value.
					switch(indicatorType)
					{
					case GDP_PER_CAPITA:
						// if true then we have not seen this Country before
						if (dataForOneYear == null)
						{
							dataForOneYear = new GDPIndicator(yearLabels[yearIndex]);
						}
						// The overriden method of the abstact class requires an one dimensional array
						// Note: Curly braces specify that we are creating and initializing an array in place.
						double data[] = {dataForAllYears[yearIndex]};
						// Uses the overriden method to set the data
						((GDPIndicator)dataForOneYear).setData(data);
						break;
					case SCHOOL_ENROLLMENT_PRIMARY:
						if (dataForOneYear == null)
						{
							dataForOneYear = new SchoolEnrollmentIndicator(yearLabels[yearIndex]);
						}
						((SchoolEnrollmentIndicator)dataForOneYear).setPrimaryEnrollment(dataForAllYears[yearIndex]);
						break;
					case SCHOOL_ENROLLMENT_SECONDARY:
						if (dataForOneYear == null)
						{
							dataForOneYear = new SchoolEnrollmentIndicator(yearLabels[yearIndex]);
						}
						((SchoolEnrollmentIndicator)dataForOneYear).setSecondaryEnrollment(dataForAllYears[yearIndex]);
						break;
					default:
						break; 
					}
					// initialize the year
					foundCountry.setIndicatorForYear(yearLabels[yearIndex], dataForOneYear);	
				}

				// add the newly created country to the 1D array
				countries[countryIndex] = foundCountry;
			} // end of for loop traversing the array of Country objects
		} // end of the for loop parsing the CSV file(s)

		// Displays the name of each Country object
		displayCountryNames(countries);
		// Given the childrenEnrolledIn*_short_12years.csv file(s), the output is:
		// Countries:
		// 		 Argentina, Canada, China, Egypt, Arab Rep., India, Nepal, Syrian Arab Republic, United States,

		// Verbose display of the country information
		displayCountries(countries);

		// -------------------------------------------------------------------
		// Test cases that requests data between different start and end years 
		Indicator [] requestedEnrollmentPeriod;

		try
		{
			int countryNum = 1;
			String countryName = countries[countryNum].getName();
			int requestedStartYear = 2006;
			int requestedEndYear = 2014;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = countries[countryNum].getIndicatorForPeriod(requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}

		try
		{
			int countryNum = countries.length/2;
			String countryName = countries[countryNum].getName();
			int requestedStartYear = 1950;
			int requestedEndYear = 2000;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = countries[countryNum].getIndicatorForPeriod(requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}

		try
		{
			int countryNum = 1;
			String countryName = countries[countryNum].getName();
			int requestedStartYear = 2000;
			int requestedEndYear = 2030;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = countries[countryNum].getIndicatorForPeriod(requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}






		//_________________Test Cases (My contribution)_________________

		try
		{
			int countryNum = 3;
			String countryName = countries[countryNum].getName();
			int requestedStartYear = 2000;
			int requestedEndYear = 2007;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = countries[countryNum].getIndicatorForPeriod(requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}
		// OUTPUT for 1. GDP per Capita (Short for debugging):
		//		Requested enrollment period (2000 to 2007) for Egypt, Arab Rep.:
		//		Invalid request of start and end year 2000, 2007. Using valid subperiod for  is 2006 to 2007
		//		Egypt, Arab Rep.            [1375.20]              [1640.48]

		// OUTPUT for 2. School Enrollment (Short for debugging):
		//		Requested enrollment period (2000 to 2007) for Egypt, Arab Rep.:
		//		Invalid request of start and end year 2000, 2007. Using valid subperiod for  is 2006 to 2007
		//		Egypt, Arab Rep. [N/A, N/A]   [N/A, N/A]

		// OUTPUT for 3. GDP per Capita:
		//		Requested enrollment period (2000 to 2007) for Albania:
		//		Albania            [1175.79]              [1326.97]              [1453.64]              [1890.68]              [2416.59]              [2709.14]              [3005.01]              [3603.01]

		// OUTPUT for 4. School Enrollment:
		//		Requested enrollment period (2000 to 2007) for Albania:
		//		Albania [N/A, 67.02]   [N/A, 68.76]   [N/A, N/A]   [N/A, N/A]   [N/A, N/A]   [N/A, N/A]   [N/A, N/A]   [N/A, N/A]

		try
		{
			int countryNum = 5;
			String countryName = countries[countryNum].getName();
			int requestedStartYear = 1923;
			int requestedEndYear = 2017;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = countries[countryNum].getIndicatorForPeriod(requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}
		// OUTPUT for 1. GDP per Capita (Short for debugging):
		//		Requested enrollment period (1923 to 2017) for Nepal:
		//		Invalid request of start and end year 1923, 2017. Using valid subperiod for  is 2006 to 2017
		//		Nepal             [348.63]               [393.88]               [473.84]               [480.72]               [592.18]               [692.12]               [681.79]               [688.62]               [706.24]               [747.16]               [729.12]               [835.08]

		// OUTPUT for 2. School Enrollment (Short for debugging):
		//		Requested enrollment period (1923 to 2017) for Nepal:
		//		Invalid request of start and end year 1923, 2017. Using valid subperiod for  is 2006 to 2017
		//		Nepal [N/A, N/A]   [N/A, 44.76]   [N/A, 48.12]   [N/A, N/A]   [N/A, 50.49]   [N/A, 54.74]   [N/A, 57.69]   [N/A, 58.84]   [N/A, 59.69]   [N/A, 60.40]   [N/A, 54.37]   [N/A, 55.29]

		// OUTPUT for 3. GDP per Capita:
		//		Requested enrollment period (1923 to 2017) for Arab World:
		//		Invalid request of start and end year 1923, 2017. Using valid subperiod for  is 1960 to 2017
		//		Arab World                [N/A]                  [N/A]                  [N/A]                  [N/A]                  [N/A]                  [N/A]                  [N/A]                  [N/A]               [222.62]               [238.84]               [256.34]               [289.45]               [334.98]               [413.97]               [768.95]               [825.75]               [996.34]              [1114.05]              [1182.46]              [1549.75]              [2040.40]              [2037.30]              [1837.09]              [1669.55]              [1640.87]              [1571.56]              [1451.27]              [1525.23]              [1457.98]              [1486.36]              [1987.84]              [1904.62]              [2004.01]              [1974.28]              [1969.70]              [2052.14]              [2215.93]              [2299.15]              [2169.79]              [2313.79]              [2588.74]              [2495.37]              [2462.79]              [2721.62]              [3117.66]              [3745.79]              [4336.72]              [4937.60]              [6115.24]              [5158.25]              [5917.25]              [6854.84]              [7463.40]              [7508.57]              [7452.81]              [6413.38]              [6151.18]              [6251.14]

		// OUTPUT for 4. School Enrollment:
		//		Requested enrollment period (1923 to 2017) for Arab World:
		//		Invalid request of start and end year 1923, 2017. Using valid subperiod for  is 1960 to 2017
		//		Arab World [N/A, N/A]   [N/A, N/A]   [N/A, N/A]   [N/A, N/A]   [N/A, N/A]   [N/A, N/A]   [N/A, N/A]   [N/A, N/A]   [N/A, N/A]   [N/A, N/A]   [N/A, 21.14]   [N/A, 21.06]   [N/A, 21.54]   [N/A, 22.84]   [N/A, 23.31]   [N/A, 24.58]   [N/A, 26.03]   [N/A, 27.89]   [N/A, 29.68]   [N/A, 31.09]   [N/A, 32.19]   [N/A, 32.65]   [N/A, 34.11]   [N/A, 35.23]   [N/A, 36.47]   [N/A, 37.89]   [N/A, 39.66]   [N/A, 40.76]   [N/A, 41.25]   [N/A, 41.92]   [N/A, 44.38]   [N/A, 44.52]   [N/A, 43.58]   [N/A, 44.18]   [N/A, 45.97]   [N/A, 46.11]   [N/A, 46.25]   [N/A, 47.49]   [N/A, 48.92]   [N/A, 50.39]   [N/A, 51.67]   [N/A, 52.86]   [N/A, 53.65]   [N/A, 55.35]   [N/A, 57.15]   [N/A, 57.25]   [N/A, 57.17]   [N/A, 57.08]   [N/A, 58.30]   [N/A, 60.54]   [N/A, 61.02]   [N/A, 62.77]   [N/A, 62.91]   [N/A, 61.24]   [N/A, 61.57]   [N/A, 61.91]   [N/A, 62.31]   [N/A, N/A]

		try
		{
			int countryNum = 7;
			String countryName = countries[countryNum].getName();
			int requestedStartYear = 2001;
			int requestedEndYear = 2033;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = countries[countryNum].getIndicatorForPeriod(requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}
		// OUTPUT for 1. GDP per Capita (Short for debugging):
		//		Requested enrollment period (2001 to 2033) for United States:
		//		Invalid request of start and end year 2001, 2033. Using valid subperiod for  is 2006 to 2017
		//		United States           [46437.07]             [48061.54]             [48401.43]             [47001.56]             [48375.41]             [49793.71]             [51450.96]             [52782.09]             [54696.73]             [56443.82]             [57588.54]             [59531.66]

		// OUTPUT for 2. School Enrollment (Short for debugging):
		//		Requested enrollment period (2001 to 2033) for United States:
		//		Invalid request of start and end year 2001, 2033. Using valid subperiod for  is 2006 to 2017
		//		United States [N/A, 88.29]   [N/A, 88.39]   [N/A, 88.56]   [N/A, 87.94]   [N/A, 86.70]   [N/A, 87.63]   [N/A, 87.33]   [N/A, 88.12]   [N/A, 88.90]   [N/A, 90.83]   [N/A, N/A]   [N/A, N/A]

		// OUTPUT for 3. GDP per Capita:
		//		Requested enrollment period (2001 to 2033) for Argentina:
		//		Invalid request of start and end year 2001, 2033. Using valid subperiod for  is 2001 to 2017
		//		Argentina            [7170.69]              [2579.19]              [3330.44]              [4251.57]              [5076.88]              [5878.76]              [7193.62]              [8953.36]              [8161.31]             [10276.26]             [12726.91]             [12969.71]             [12976.64]             [12245.26]             [13698.29]             [12654.36]             [14401.97]

		// OUTPUT for 4. School Enrollment:
		//		Requested enrollment period (2001 to 2033) for Argentina:
		//		Invalid request of start and end year 2001, 2033. Using valid subperiod for  is 2001 to 2017
		//		Argentina [N/A, 79.45]   [N/A, 79.36]   [N/A, 79.17]   [N/A, 79.36]   [N/A, 78.98]   [N/A, 79.11]   [N/A, 79.20]   [N/A, 80.35]   [N/A, 82.41]   [N/A, 84.00]   [N/A, 85.22]   [N/A, 87.32]   [N/A, 88.16]   [N/A, 88.25]   [N/A, 88.55]   [N/A, N/A]   [N/A, N/A]

		// Extra Credit Portion
		System.out.println("\nTesting getIndicatorForYear() & setIndicatorForYear() methods:\n");
		Indicator testOutputSetIndicatorData = new GDPIndicator(2015, 17.33);
		Country testCountryData = new Country("India");
		//System.out.println(testCountryData.getName());
		String testCountryDataName = testCountryData.getName();

		testCountryData.setIndicatorForYear(2015, testOutputSetIndicatorData);
		System.out.println("The value of setIndicatorForYear for " + testCountryDataName + " is:" + testOutputSetIndicatorData);

		Indicator testOutputGetIndicatorData = testCountryData.getIndicatorForYear(2015);
		System.out.println("The value of getIndicatorForYear for " + testCountryDataName + " is:" + testOutputGetIndicatorData);

		System.out.println("\nDone with TestCountry.\n");
	}
}
