package csv;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 *  Tests the CountryList class, which holds a linked list of CountryNode objects
 *  and tests the CountryList class object by creating a linked list of CountryNode
 *  objects which stores a Country object as the data.
 *
 *  @author Foothill College, Bita Mazloom, Selahittin Saytaş
 */
public class TestCountryList
{	
	// Sets the Scanner object to the beginning of the input stream
	// Since we will be reading user input in more than one place.
	// To avoid incorrectly closing/reopening closing the stream,
	// the reference to the stream is set as a class attribute.
	public static Scanner keyboard = new Scanner(System.in);

	/**
	 * Builds a linked list of nodes where the data is a Country object.
	 * The size of the list depends on the user input. The countries added
	 * to the list are randomly selected.
	 * @param allCountries      An array of Country objects
	 * @return	selectedCountries list
	 */
	private static CountryList createRandomListOfCountries(Country [] allCountries)
	{
		int requestedSize;
		do
		{
			// Prompts the user for the number of elements they want to add to the list.
			System.out.println("How many countries do you want to add to the list?");
			requestedSize = Integer.parseInt( keyboard.nextLine() );
		} while (requestedSize < 1);

		// Used to generate random numbers
		Random random = new Random();

		CountryList selectedCountries = new CountryList();

		for (int i = 0; i < requestedSize; i++)
		{
			// Selects a random index of the Country data array
			int selectedIndex = random.nextInt(allCountries.length);

			Country countryToAdd = allCountries[selectedIndex];
			System.out.printf("Adding country with name %s to the end of the list.\n", countryToAdd.getName());
			selectedCountries.add(countryToAdd);
		}

		System.out.println(selectedCountries.toString());
		
		System.out.println("Done with creating random linked list of countries.\n");
		return selectedCountries;
	}

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
			//countryNames += (counter + 1);
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
		int numYears = countries[0].getEndYear()-startingYear;

		for (int countryIndex = 0; countryIndex < countries.length; countryIndex++)
		{
			// appends year header
			if (countryIndex % 5 == 0)
			{
				countryInfo.append(String.format("\n%20s", "Country Name"));
				for(int yearIndex = 0; yearIndex < numYears; yearIndex++)
					countryInfo.append(String.format("%18d  ", (startingYear+yearIndex)));
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
	 * @param countryName	For the name of the country
	 * @param requestedEnrollmentPeriod   one-dimensional array of enrollment data.
	 */
	private static void displayEnrollmentPeriod(String countryName, Indicator[] requestedEnrollmentPeriod) 
	{
		System.out.printf("%20s ", countryName);
		for(int i = 0; i < requestedEnrollmentPeriod.length; i++)
		{
			if (requestedEnrollmentPeriod[i] == null)
				System.out.printf("     ," + "   ");
			else
				System.out.printf("%s   ", requestedEnrollmentPeriod[i].toString());
		}
		System.out.println("");
	}

	/**
	 * Displays the menu option for different indicators.
	 * Note: Update the menu option if you add additional input files or categories.
	 * @return	An int type of selection
	 */
	private static int printMenu()
	{	
		int selection = -1;
		while(true)
		{
			String userRequest = "";
			try
			{
				System.out.println("Select an indicator to parse. Enter a number between 0 to 4.");
				System.out.println("Available indicators are:");
				System.out.println("0. Invalid data (for debugging)");
				System.out.println("1. GDP per Capita (short for debugging)");
				System.out.println("2. School Enrollment (short for debugging)");
				System.out.println("3. GDP per Capita");
				System.out.println("4. School Enrollment");
				userRequest = keyboard.nextLine();
				selection = Integer.parseInt(userRequest);
				if (selection >= 0 && selection <=4)
					break;
			}
			catch (NumberFormatException ne) 
			{
				System.out.printf("Invalid input %s. ", userRequest);
			}
		}
		return selection;
	}

	/**
	 * Tests the contains() method of the CountryList class. 
	 * @param selectedCountryList  list of countries to search
	 */
	private static void testSearchInList(CountryList selectedCountryList) 
	{		
		// Check if the name of a country is in the list.
		// If the country is found, print the details.
		// Otherwise output not found.
		System.out.println("\nWhat is the name of the country you want to search for?");
		String countryNameToFind = keyboard.nextLine();

		Country tmpCountry = new Country(countryNameToFind);

		Country foundCountry = selectedCountryList.contains(tmpCountry);
		if (foundCountry != null)
		{
			System.out.println("Country " + countryNameToFind + " found:\n" + foundCountry);
		}
		else
		{
			System.out.println("Country " + countryNameToFind + " not found.");	
		}
		System.out.println("Done with searching for a country name.\n");
	}

	/**
	 * Tests the getIndicatorForPeriod() method of the Country class.
	 * @param selectedCountryList  list of countries to search
	 */
	private static void testFindingIndicatorDataInList(CountryList selectedCountryList) 
	{
		System.out.printf("\nWhat is the index of the country you want data on? (Enter a index between 0 and %d)\n", 
				selectedCountryList.size()-1);
		System.out.println("Available countries are:");
		System.out.println(selectedCountryList.toString());
		
		String userRequest = "";
		int selectedIndex = -1;
		
		try 
		{
			userRequest = keyboard.nextLine();
			selectedIndex = Integer.parseInt(userRequest);
			Country foundCountry = selectedCountryList.getIndex(selectedIndex);

			System.out.printf("\nWhat period are you interested in? Available years are from %d to %d.\n", 
					foundCountry.getStartYear(), foundCountry.getEndYear());
			System.out.println("Enter [starting year],[end year].");
			String period = keyboard.nextLine();
			String [] tokens = period.split(",");
			int requestedStartYear = Integer.parseInt(tokens[0]);
			int requestedEndYear = Integer.parseInt(tokens[1]);

			// Stores data between requested start year and end year
			Indicator [] requestedEnrollmentPeriod;
			requestedEnrollmentPeriod = foundCountry.getIndicatorForPeriod(requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(foundCountry.getName(),requestedEnrollmentPeriod);
		}
		catch (NumberFormatException ne) 
		{
			System.out.printf("Invalid input %s. ", userRequest);
		}
		catch (IndexOutOfBoundsException exc)
		{
			System.err.printf("ERROR: Requested index %d is out of range. ", selectedIndex);
			System.err.printf("Valid element positions are (index >= 0 && index < %d).", selectedCountryList.size());
		}
	}

	/**
	 * Creates an object of type CSVParser which parses a CSV file. If
	 * the format of the CSVParser is valid, creates a Country object
	 * from each parsed line. 
	 * Creates a CountryList object. Tests the linked list by adding 
	 * random Country object(s) to the list. Tests the created list by:
	 * - searching the list for a Country by name and 
	 * - by searching for available Indicator data by a year range.
	 * @param args	An array of String arguments
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
		int selection = printMenu();
		final String [] filenames;

		// Determines the input file pathname.
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

		for(int currentFileIndex = 0; currentFileIndex < filenames.length; currentFileIndex++)
		{
			// Parse the CSV data file *based* on the input filename.
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

			// Provides the indicator read from the input file.
			IndicatorType indicatorType = parser.getIndicatorType();

			// NOTE: You can assume that the format of all CSV input files are the same with the same:
			//       Number of countries
			//       Name of countries
			//       Year labels
			String [] countryNames = parser.getCountryNames();
			int [] yearLabels = parser.getYearLabels();
			double [][] parsedTable = parser.getParsedTable();		

			// Check if the array of countries has been initialized by a previous iteration.
			if (countries == null)
			{
				// An array of Country objects.
				// NOTE: Here we are no longer using the two dimensional array from EnrollmentData class.
				//       Instead, each country will hold it's own data.
				//       So, we no longer need the EnrollmentData class.
				countries = new Country[countryNames.length];				 
			}

			// Note: We are moving away from the two dimensional array of Indicator objects
			//       to a single dimensional array of Country objects where each
			//       Country object stores a single dimensional array of Indicators.
			// Refers to a Country object
			Country foundCountry;

			// Go through each country name parsed from the CSV file.
			// If the country name has not bee seen before, create a new object of
			// type Country to hold an array of Indicator objects.
			for (int countryIndex = 0; countryIndex < countries.length; countryIndex++)
			{
				int numberOfYears = yearLabels.length;   // alternatively numberOfYears = dataTable[countryIndex].length;

				// Checks if the Country has been seen before.
				// Note: The findCountry() method will return a non-null object if a previous
				//       iteration of the outer for-loop (i.e. using the counter currentFileIndex)
				//       has parsed data for the current country name.
				foundCountry = findCountry(countryNames[countryIndex], countries);

				if(foundCountry == null)
				{
					foundCountry = new Country(countryNames[countryIndex], numberOfYears);
				}

				// Get the parsed array of doubles for the current Country object
				double [] dataForAllYears = parsedTable[countryIndex];

				// Go through each year of data read from the CSV file.
				for (int yearIndex = 0; yearIndex < dataForAllYears.length; yearIndex++)
				{
					// Refers to the current indicator
					Indicator dataForOneYear = null;

					// Checks if a previous input file has already added the current year.
					// Note: Assume the CSV files are well formed with same number of years
					//       and that the years are consecutive.
					dataForOneYear = foundCountry.getIndicatorForYear(yearLabels[yearIndex]);

					// Recall that each CSV input file we read will provide us one data value.
					switch(indicatorType)
					{
					case GDP_PER_CAPITA:
						// When true then we have not seen this Country before.
						if (dataForOneYear == null)
						{
							dataForOneYear = new GDPIndicator(yearLabels[yearIndex]);
						}
						// The overridden method of the abstract class requires a one dimensional array.
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
					// Initializes the year.
					foundCountry.setIndicatorForYear(yearLabels[yearIndex], dataForOneYear);	
				}

				// Add the newly created country to the one dimensional array.
				countries[countryIndex] = foundCountry;
			} // End of for loop traversing the array of Country objects
		} // End of the for loop parsing the CSV file(s)

		// Output the names of the countries added to the list
		displayCountryNames(countries);
		// Example:
		// Names of countries in list:
		// 0 United States, 1 Argentina, 2 Egypt, Arab Rep., 
		
		// The following test methods assume we are given the childrenEnrolledIn*_short_12years.csv file(s).
		// Also, assume the user selects to add 3 countries to the list. 
		// The following serve as examples. Yours may look different from below. 

		// Tests the CountryList class by adding a random number of entries
		// from the array of Country objects.
		CountryList selectedCountryList = createRandomListOfCountries(countries);
		// Example:
		// How many countries do you want to add to the list?
		// 3
		// Adding country with name United States to the end of the list.
		// Adding country with name Argentina to the end of the list.
		// Adding country with name Egypt, Arab Rep. to the end of the list.

		// Tests searching the LinkedList
		testSearchInList(selectedCountryList);
		// Index         Country Name              2006                2007                2008                2009                2010                2011                2012                2013                2014                2015                2016  
		//     0        United States             (,88.29)            (,88.39)            (,88.56)            (,87.94)            (,86.70)            (,87.63)            (,87.33)            (,88.12)            (,88.90)            (,90.83)                  ()                  ()  
		//     1            Argentina             (,79.11)            (,79.20)            (,80.35)            (,82.41)            (,84.00)            (,85.22)            (,87.32)            (,88.16)            (,88.25)            (,88.55)                  ()                  ()  
		//     2     Egypt, Arab Rep.                   ()                  ()                  ()                  ()                  ()                  ()                  ()                  ()            (,79.09)                  ()            (,81.49)                  ()  
		// What is the name of the country you want to search for?
		// Egypt, Arab Rep.
		// Country Egypt, Arab Rep. found:
		//     Egypt, Arab Rep.                   ()                  ()                  ()                  ()                  ()                  ()                  ()                  ()            (,79.09)                  ()            (,81.49)                  ()  

		// Tests retrieving indicator data in the list.
		testFindingIndicatorDataInList(selectedCountryList);
		// Note: Omitting table output. 
		// What is the index of the country you want data on? (Enter a index between 0 and 2)
		// 0
		//
		// What period are you interested in? (Enter [starting year],[end year].)
		// 2010,2017
		//         Country Name            2010             2011             2012             2013             2014             2015             2016             2017  
		//        United States        (,86.70)         (,87.63)         (,87.33)         (,88.12)         (,88.90)         (,90.83)               ()               ()







		//_________________TEST CASES (MY CONTRIBUTION)_________________

		// TEST CASE 1 (Size of list is 7 - Finds a country name that does exist in the list):

		//displayCountryNames(countries);
		//_________________OUTPUT for 1. GDP per Capita (Short for debugging):_________________
		//		The selected indicator is GDP_PER_CAPITA
		//
		//		Given the resources/gdpPerCapita_short_12years.csv file, the output is:
		//		GDP per capita (current US$) updated at 8/28/18
		//
		//		Parsing filename resources/gdpPerCapita_short_12years.csv
		//
		//		Country names:
		//		Argentina, Canada, China, Egypt, Arab Rep., India, Nepal, Syrian Arab Republic, United States,



		//_________________OUTPUT for 2. School Enrollment (Short for debugging):_________________
		//		The selected indicator is SCHOOL_ENROLLMENT_PRIMARY
		//
		//		Given the resources/childrenEnrolledInPrimary_short_12years.csv file, the output is:
		//		School Enrollment In Primary (% net) updated at 7/25/18
		//
		//		Parsing filename resources/childrenEnrolledInPrimary_short_12years.csv
		//
		//		Given the resources/childrenEnrolledInSecondary_short_12years.csv file, the output is:
		//		School Enrollment In Secondary (% net) updated at 6/28/18
		//
		//		Parsing filename resources/childrenEnrolledInSecondary_short_12years.csv
		//
		//		Country names:
		//		Afghanistan, Canada, China, Egypt, Arab Rep., India, Nepal, Syrian Arab Republic, United States,


		//_________________OUTPUT for 3. GDP per Capita:_________________
		//		The selected indicator is GDP_PER_CAPITA
		//
		//		Given the resources/gdpPerCapita.csv file, the output is:
		//		GDP per capita (current US$) updated at 8/28/18
		//
		//		Parsing filename resources/gdpPerCapita.csv
		//
		//		Country names:
		//		Aruba, Afghanistan, Angola, Albania, Andorra, Arab World, United Arab Emirates, Argentina, Armenia, American Samoa, Antigua and Barbuda, Australia, Austria, Azerbaijan, Burundi, Belgium, Benin, Burkina Faso, Bangladesh, Bulgaria, Bahrain, Bahamas, The, Bosnia and Herzegovina, Belarus, Belize, Bermuda, Bolivia, Brazil, Barbados, Brunei Darussalam, Bhutan, Botswana, Central African Republic, Canada, Central Europe and the Baltics, Switzerland, Channel Islands, Chile, China, Cote d'Ivoire, Cameroon, Congo, Dem. Rep., Congo, Rep., Colombia, Comoros, Cabo Verde, Costa Rica, Caribbean small states, Cuba, Curacao
		//		Cayman Islands, Cyprus, Czech Republic, Germany, Djibouti, Dominica, Denmark, Dominican Republic, Algeria, East Asia & Pacific (excluding high income), Early-demographic dividend, East Asia & Pacific, Europe & Central Asia (excluding high income), Europe & Central Asia, Ecuador, Egypt, Arab Rep., Euro area, Eritrea, Spain, Estonia, Ethiopia, European Union, Fragile and conflict affected situations, Finland, Fiji, France, Faroe Islands, Micronesia, Fed. Sts., Gabon, United Kingdom, Georgia, Ghana, Gibraltar, Guinea, Gambia, The, Guinea-Bissau, Equatorial Guinea, Greece, Grenada, Greenland, Guatemala, Guam, Guyana, High income, Hong Kong SAR, China, Honduras, Heavily indebted poor countries (HIPC), Croatia, Haiti, Hungary
		//		IBRD only, IDA & IBRD total, IDA total, IDA blend, Indonesia, IDA only, Isle of Man, India, Not classified, Ireland, Iran, Islamic Rep., Iraq, Iceland, Israel, Italy, Jamaica, Jordan, Japan, Kazakhstan, Kenya, Kyrgyz Republic, Cambodia, Kiribati, St. Kitts and Nevis, Korea, Rep., Kuwait, Latin America & Caribbean (excluding high income), Lao PDR, Lebanon, Liberia, Libya, St. Lucia, Latin America & Caribbean, Least developed countries: UN classification, Low income, Liechtenstein, Sri Lanka, Lower middle income, Low & middle income, Lesotho, Late-demographic dividend, Lithuania, Luxembourg, Latvia, Macao SAR, China, St. Martin (French part), Morocco, Monaco, Moldova, Madagascar
		//		Maldives, Middle East & North Africa, Mexico, Marshall Islands, Middle income, Macedonia, FYR, Mali, Malta, Myanmar, Middle East & North Africa (excluding high income), Montenegro, Mongolia, Northern Mariana Islands, Mozambique, Mauritania, Mauritius, Malawi, Malaysia, North America, Namibia, New Caledonia, Niger, Nigeria, Nicaragua, Netherlands, Norway, Nepal, Nauru, New Zealand, OECD members, Oman, Other small states, Pakistan, Panama, Peru, Philippines, Palau, Papua New Guinea, Poland, Pre-demographic dividend, Puerto Rico, Korea, Dem. People's Rep., Portugal, Paraguay, West Bank and Gaza, Pacific island small states, Post-demographic dividend, French Polynesia, Qatar, Romania
		//		Russian Federation, Rwanda, South Asia, Saudi Arabia, Sudan, Senegal, Singapore, Solomon Islands, Sierra Leone, El Salvador, San Marino, Somalia, Serbia, Sub-Saharan Africa (excluding high income), South Sudan, Sub-Saharan Africa, Small states, Sao Tome and Principe, Suriname, Slovak Republic, Slovenia, Sweden, Swaziland, Sint Maarten (Dutch part), Seychelles, Syrian Arab Republic, Turks and Caicos Islands, Chad, East Asia & Pacific (IDA & IBRD countries), Europe & Central Asia (IDA & IBRD countries), Togo, Thailand, Tajikistan, Turkmenistan, Latin America & the Caribbean (IDA & IBRD countries), Timor-Leste, Middle East & North Africa (IDA & IBRD countries), Tonga, South Asia (IDA & IBRD), Sub-Saharan Africa (IDA & IBRD countries), Trinidad and Tobago, Tunisia, Turkey, Tuvalu, Tanzania, Uganda, Ukraine, Upper middle income, Uruguay, United States
		//		Uzbekistan, St. Vincent and the Grenadines, Venezuela, RB, British Virgin Islands, Virgin Islands (U.S.), Vietnam, Vanuatu, World, Samoa, Kosovo, Yemen, Rep., South Africa, Zambia, Zimbabwe,


		//_________________OUTPUT for 4. School Enrollment:_________________
		//		The selected indicator is SCHOOL_ENROLLMENT_PRIMARY
		//
		//		Given the resources/childrenEnrolledInPrimary.csv file, the output is:
		//		School Enrollment In Primary (% net) updated at 7/25/18
		//
		//		Parsing filename resources/childrenEnrolledInPrimary.csv
		//
		//		Given the resources/childrenEnrolledInSecondary.csv file, the output is:
		//		School Enrollment In Secondary (% net) updated at 6/28/18
		//
		//		Parsing filename resources/childrenEnrolledInSecondary.csv
		//
		//		Country names:
		//		Aruba, Afghanistan, Angola, Albania, Andorra, Arab World, United Arab Emirates, Argentina, Armenia, American Samoa, Antigua and Barbuda, Australia, Austria, Azerbaijan, Burundi, Belgium, Benin, Burkina Faso, Bangladesh, Bulgaria, Bahrain, Bahamas, The, Bosnia and Herzegovina, Belarus, Belize, Bermuda, Bolivia, Brazil, Barbados, Brunei Darussalam, Bhutan, Botswana, Central African Republic, Canada, Central Europe and the Baltics, Switzerland, Channel Islands, Chile, China, Cote d'Ivoire, Cameroon, Congo, Dem. Rep., Congo, Rep., Colombia, Comoros, Cabo Verde, Costa Rica, Caribbean small states, Cuba, Curacao
		//		Cayman Islands, Cyprus, Czech Republic, Germany, Djibouti, Dominica, Denmark, Dominican Republic, Algeria, East Asia & Pacific (excluding high income), Early-demographic dividend, East Asia & Pacific, Europe & Central Asia (excluding high income), Europe & Central Asia, Ecuador, Egypt, Arab Rep., Euro area, Eritrea, Spain, Estonia, Ethiopia, European Union, Fragile and conflict affected situations, Finland, Fiji, France, Faroe Islands, Micronesia, Fed. Sts., Gabon, United Kingdom, Georgia, Ghana, Gibraltar, Guinea, Gambia, The, Guinea-Bissau, Equatorial Guinea, Greece, Grenada, Greenland, Guatemala, Guam, Guyana, High income, Hong Kong SAR, China, Honduras, Heavily indebted poor countries (HIPC), Croatia, Haiti, Hungary
		//		IBRD only, IDA & IBRD total, IDA total, IDA blend, Indonesia, IDA only, Isle of Man, India, Not classified, Ireland, Iran, Islamic Rep., Iraq, Iceland, Israel, Italy, Jamaica, Jordan, Japan, Kazakhstan, Kenya, Kyrgyz Republic, Cambodia, Kiribati, St. Kitts and Nevis, Korea, Rep., Kuwait, Latin America & Caribbean (excluding high income), Lao PDR, Lebanon, Liberia, Libya, St. Lucia, Latin America & Caribbean, Least developed countries: UN classification, Low income, Liechtenstein, Sri Lanka, Lower middle income, Low & middle income, Lesotho, Late-demographic dividend, Lithuania, Luxembourg, Latvia, Macao SAR, China, St. Martin (French part), Morocco, Monaco, Moldova, Madagascar
		//		Maldives, Middle East & North Africa, Mexico, Marshall Islands, Middle income, Macedonia, FYR, Mali, Malta, Myanmar, Middle East & North Africa (excluding high income), Montenegro, Mongolia, Northern Mariana Islands, Mozambique, Mauritania, Mauritius, Malawi, Malaysia, North America, Namibia, New Caledonia, Niger, Nigeria, Nicaragua, Netherlands, Norway, Nepal, Nauru, New Zealand, OECD members, Oman, Other small states, Pakistan, Panama, Peru, Philippines, Palau, Papua New Guinea, Poland, Pre-demographic dividend, Puerto Rico, Korea, Dem. People's Rep., Portugal, Paraguay, West Bank and Gaza, Pacific island small states, Post-demographic dividend, French Polynesia, Qatar, Romania
		//		Russian Federation, Rwanda, South Asia, Saudi Arabia, Sudan, Senegal, Singapore, Solomon Islands, Sierra Leone, El Salvador, San Marino, Somalia, Serbia, Sub-Saharan Africa (excluding high income), South Sudan, Sub-Saharan Africa, Small states, Sao Tome and Principe, Suriname, Slovak Republic, Slovenia, Sweden, Swaziland, Sint Maarten (Dutch part), Seychelles, Syrian Arab Republic, Turks and Caicos Islands, Chad, East Asia & Pacific (IDA & IBRD countries), Europe & Central Asia (IDA & IBRD countries), Togo, Thailand, Tajikistan, Turkmenistan, Latin America & the Caribbean (IDA & IBRD countries), Timor-Leste, Middle East & North Africa (IDA & IBRD countries), Tonga, South Asia (IDA & IBRD), Sub-Saharan Africa (IDA & IBRD countries), Trinidad and Tobago, Tunisia, Turkey, Tuvalu, Tanzania, Uganda, Ukraine, Upper middle income, Uruguay, United States
		//		Uzbekistan, St. Vincent and the Grenadines, Venezuela, RB, British Virgin Islands, Virgin Islands (U.S.), Vietnam, Vanuatu, World, Samoa, Kosovo, Yemen, Rep., South Africa, Zambia, Zimbabwe,



		//selectedCountryList = createRandomListOfCountries(countries);
		//_________________OUTPUT for 1. GDP per Capita (Short for debugging):_________________
		//		How many countries do you want to add to the list?
		//		7
		//		Adding country with name Canada to the end of the list.
		//		Adding country with name Argentina to the end of the list.
		//		Adding country with name Argentina to the end of the list.
		//		Adding country with name Egypt, Arab Rep. to the end of the list.
		//		Adding country with name Canada to the end of the list.
		//		Adding country with name India to the end of the list.
		//		Adding country with name India to the end of the list.



		//_________________OUTPUT for 2. School Enrollment (Short for debugging):_________________
		//		How many countries do you want to add to the list?
		//		7
		//		Adding country with name Syrian Arab Republic to the end of the list.
		//		Adding country with name Syrian Arab Republic to the end of the list.
		//		Adding country with name United States to the end of the list.
		//		Adding country with name Canada to the end of the list.
		//		Adding country with name Egypt, Arab Rep. to the end of the list.
		//		Adding country with name Canada to the end of the list.
		//		Adding country with name United States to the end of the list.

		//_________________OUTPUT for 3. GDP per Capita:_________________
		//		How many countries do you want to add to the list?
		//		7
		//		Adding country with name Armenia to the end of the list.
		//		Adding country with name Heavily indebted poor countries (HIPC) to the end of the list.
		//		Adding country with name West Bank and Gaza to the end of the list.
		//		Adding country with name Bermuda to the end of the list.
		//		Adding country with name Ukraine to the end of the list.
		//		Adding country with name Pacific island small states to the end of the list.
		//		Adding country with name Maldives to the end of the list.



		//_________________OUTPUT for 4. School Enrollment:_________________
		//		How many countries do you want to add to the list?
		//		7
		//		Adding country with name South Sudan to the end of the list.
		//		Adding country with name Madagascar to the end of the list.
		//		Adding country with name New Caledonia to the end of the list.
		//		Adding country with name East Asia & Pacific (excluding high income) to the end of the list.
		//		Adding country with name Israel to the end of the list.
		//		Adding country with name Mauritius to the end of the list.
		//		Adding country with name Algeria to the end of the list.



		//testSearchInList(selectedCountryList);
		//_________________OUTPUT for 1. GDP per Capita (Short for debugging):_________________
		//		[Canada                        [40386.70]          [44544.53]          [46596.34]          [40773.45]          [47447.48]          [52082.21]          [52496.69]          [52418.32]          [50633.21]          [43525.37]          [42348.95]          [45032.12]
		//		, Argentina                      [5878.76]           [7193.62]           [8953.36]           [8161.31]          [10276.26]          [12726.91]          [12969.71]          [12976.64]          [12245.26]          [13698.29]          [12654.36]          [14401.97]
		//		, Argentina                      [5878.76]           [7193.62]           [8953.36]           [8161.31]          [10276.26]          [12726.91]          [12969.71]          [12976.64]          [12245.26]          [13698.29]          [12654.36]          [14401.97]
		//		, Egypt, Arab Rep.               [1375.20]           [1640.48]           [2011.25]           [2291.67]           [2602.48]           [2747.48]           [3181.44]           [3213.39]           [3327.75]           [3547.71]           [3479.28]           [2412.73]
		//		, Canada                        [40386.70]          [44544.53]          [46596.34]          [40773.45]          [47447.48]          [52082.21]          [52496.69]          [52418.32]          [50633.21]          [43525.37]          [42348.95]          [45032.12]
		//		, India                           [792.03]           [1018.17]            [991.48]           [1090.32]           [1345.77]           [1461.67]           [1446.99]           [1452.20]           [1576.00]           [1606.04]           [1717.47]           [1939.61]
		//		, India                           [792.03]           [1018.17]            [991.48]           [1090.32]           [1345.77]           [1461.67]           [1446.99]           [1452.20]           [1576.00]           [1606.04]           [1717.47]           [1939.61]
		//		]
		//		Done with creating random linked list of countries.
		//
		//
		//		What is the name of the country you want to search for?
		//		Canada
		//		Country Canada found:
		//		Canada                        [40386.70]          [44544.53]          [46596.34]          [40773.45]          [47447.48]          [52082.21]          [52496.69]          [52418.32]          [50633.21]          [43525.37]          [42348.95]          [45032.12]
		//
		//		Done with searching for a country name.



		//_________________OUTPUT for 2. School Enrollment (Short for debugging):_________________
		//		[Syrian Arab Republic        [N/A, 63.83]        [N/A, 65.49]        [N/A, 66.18]        [N/A, 66.30]        [N/A, 66.94]        [N/A, 68.81]        [N/A, 70.62]        [N/A, 45.52]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, Syrian Arab Republic        [N/A, 63.83]        [N/A, 65.49]        [N/A, 66.18]        [N/A, 66.30]        [N/A, 66.94]        [N/A, 68.81]        [N/A, 70.62]        [N/A, 45.52]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, United States               [N/A, 88.29]        [N/A, 88.39]        [N/A, 88.56]        [N/A, 87.94]        [N/A, 86.70]        [N/A, 87.63]        [N/A, 87.33]        [N/A, 88.12]        [N/A, 88.90]        [N/A, 90.83]          [N/A, N/A]          [N/A, N/A]
		//		, Canada                        [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 92.31]        [N/A, 91.77]        [N/A, 91.35]        [N/A, 92.86]        [N/A, 99.65]          [N/A, N/A]
		//		, Egypt, Arab Rep.              [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 79.09]          [N/A, N/A]        [N/A, 81.49]          [N/A, N/A]
		//		, Canada                        [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 92.31]        [N/A, 91.77]        [N/A, 91.35]        [N/A, 92.86]        [N/A, 99.65]          [N/A, N/A]
		//		, United States               [N/A, 88.29]        [N/A, 88.39]        [N/A, 88.56]        [N/A, 87.94]        [N/A, 86.70]        [N/A, 87.63]        [N/A, 87.33]        [N/A, 88.12]        [N/A, 88.90]        [N/A, 90.83]          [N/A, N/A]          [N/A, N/A]
		//		]
		//		Done with creating random linked list of countries.
		//
		//
		//		What is the name of the country you want to search for?
		//		Canada
		//		Country Canada found:
		//		Canada                        [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 92.31]        [N/A, 91.77]        [N/A, 91.35]        [N/A, 92.86]        [N/A, 99.65]          [N/A, N/A]
		//
		//		Done with searching for a country name.



		//_________________OUTPUT for 3. GDP per Capita:_________________
		//		[Armenia                            [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]            [637.86]            [590.12]            [369.63]            [357.20]            [400.52]            [456.38]            [504.06]            [523.28]            [609.17]            [597.43]            [622.74]            [694.43]            [783.26]            [930.17]           [1191.96]           [1643.76]           [2158.00]           [3138.81]           [4010.03]           [2993.83]           [3218.38]           [3526.98]           [3684.80]           [3843.59]           [3994.71]           [3617.94]           [3605.74]           [3936.80]
		//		, Heavily indebted poor countries (HIPC)            [107.73]            [107.95]            [114.90]            [135.97]            [117.53]            [133.06]            [141.59]            [134.73]            [137.30]            [149.49]            [152.68]            [160.39]            [169.22]            [198.47]            [237.10]            [261.95]            [270.45]            [311.22]            [346.26]            [381.69]            [387.54]            [376.39]            [365.93]            [334.39]            [323.81]            [332.90]            [372.14]            [396.50]            [388.18]            [367.92]            [364.19]            [365.13]            [322.34]            [323.29]            [278.35]            [307.37]            [312.48]            [319.35]            [320.02]            [311.20]            [328.17]            [303.26]            [320.13]            [354.25]            [396.58]            [444.57]            [518.76]            [601.13]            [706.70]            [691.36]            [744.28]            [808.59]            [846.80]            [894.22]            [922.95]            [883.41]            [875.87]            [952.51]
		//		, West Bank and Gaza                 [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]           [1201.58]           [1326.56]           [1317.47]           [1389.16]           [1465.05]           [1499.49]           [1476.17]           [1335.55]           [1156.22]           [1257.70]           [1337.57]           [1455.19]           [1441.46]           [1575.56]           [1855.46]           [1963.20]           [2338.72]           [2664.95]           [2787.17]           [2992.20]           [2960.78]           [2865.81]           [2949.69]           [3094.73]
		//		, Bermuda                        [1902.40]           [1961.54]           [2020.39]           [2020.27]           [2199.73]           [2282.22]           [2630.85]           [2982.75]           [2830.19]           [3053.70]           [3387.27]           [3866.30]           [4343.17]           [5009.29]           [5853.93]           [6509.43]           [7261.28]           [8370.79]           [8876.87]           [9613.38]          [11218.22]          [13425.98]          [14166.17]          [15902.02]          [17469.83]          [18269.54]          [20450.66]          [22411.80]          [24253.18]          [25517.92]          [26841.52]          [27700.31]          [28669.68]          [30900.69]          [31476.06]          [33989.72]          [44826.79]          [48478.88]          [51371.74]          [54245.46]          [56284.17]          [58883.96]          [62583.10]          [66111.73]          [70359.32]          [75882.03]          [83912.70]          [90849.59]          [93605.75]          [88463.31]          [88207.33]          [85973.16]          [85458.46]          [85748.07]               [N/A]               [N/A]               [N/A]               [N/A]
		//		, Ukraine                            [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]           [1249.45]           [1449.97]           [1597.54]           [1569.74]               [N/A]           [1378.64]           [1257.35]           [1011.99]            [935.99]            [872.71]            [991.23]            [835.26]            [635.77]            [635.71]            [780.74]            [879.48]           [1048.52]           [1367.35]           [1828.72]           [2303.02]           [3068.61]           [3891.04]           [2545.48]           [2965.14]           [3569.76]           [3855.42]           [4029.72]           [3104.66]           [2124.66]           [2185.73]           [2639.82]
		//		, Pacific island small states               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]            [883.44]            [867.99]            [887.47]           [1008.44]           [1209.83]           [1354.84]           [1365.29]           [1302.55]           [1218.89]           [1308.16]           [1215.57]           [1299.24]           [1259.71]           [1304.23]           [1345.27]           [1429.12]           [1494.99]           [1617.03]           [1681.47]           [1907.15]           [2025.05]           [2139.73]           [2106.02]           [1777.03]           [1920.09]           [1753.43]           [1697.68]           [1767.09]           [2048.07]           [2339.12]           [2540.14]           [2638.50]           [2858.26]           [3040.96]           [2643.08]           [2896.75]           [3401.06]           [3586.75]           [3670.62]           [3776.84]           [3640.27]           [3778.83]           [4004.38]
		//		, Maldives                           [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]            [268.10]            [273.17]            [282.04]            [327.92]            [598.53]            [670.52]            [722.67]            [695.48]            [803.35]            [875.07]            [963.60]           [1064.04]           [1206.03]           [1329.41]           [1432.52]           [1570.32]           [1736.74]           [1923.09]           [2006.26]           [2146.72]           [2226.72]           [3031.70]           [3047.59]           [3481.44]           [3952.12]           [3648.78]           [4811.67]           [5559.51]           [6583.45]           [6615.76]           [7100.41]           [7395.69]           [7473.20]           [8291.49]           [9056.65]           [9575.77]           [9871.91]          [10535.79]
		//		]
		//		Done with creating random linked list of countries.
		//
		//
		//		What is the name of the country you want to search for?
		//		Ukraine
		//		Country Ukraine found:
		//		Ukraine                            [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]           [1249.45]           [1449.97]           [1597.54]           [1569.74]               [N/A]           [1378.64]           [1257.35]           [1011.99]            [935.99]            [872.71]            [991.23]            [835.26]            [635.77]            [635.71]            [780.74]            [879.48]           [1048.52]           [1367.35]           [1828.72]           [2303.02]           [3068.61]           [3891.04]           [2545.48]           [2965.14]           [3569.76]           [3855.42]           [4029.72]           [3104.66]           [2124.66]           [2185.73]           [2639.82]
		//
		//		Done with searching for a country name.



		//_________________OUTPUT for 4. School Enrollment:_________________
		//		[South Sudan                   [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]         [N/A, 4.76]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]         [N/A, 4.92]          [N/A, N/A]          [N/A, N/A]
		//		, Madagascar                    [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 17.30]          [N/A, N/A]        [N/A, 21.81]        [N/A, 23.30]        [N/A, 24.94]          [N/A, N/A]        [N/A, 29.94]        [N/A, 30.99]          [N/A, N/A]        [N/A, 31.02]        [N/A, 30.29]        [N/A, 30.15]          [N/A, N/A]
		//		, New Caledonia                 [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, East Asia & Pacific (excluding high income)          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 60.50]        [N/A, 61.80]        [N/A, 62.55]        [N/A, 63.43]        [N/A, 63.36]        [N/A, 64.57]        [N/A, 65.83]        [N/A, 66.57]        [N/A, 67.28]        [N/A, 69.09]        [N/A, 69.00]        [N/A, 70.21]        [N/A, 71.99]        [N/A, 73.91]        [N/A, 74.34]        [N/A, 75.38]        [N/A, 78.49]        [N/A, 78.78]        [N/A, 77.85]          [N/A, N/A]
		//		, Israel                        [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 96.05]        [N/A, 97.91]        [N/A, 98.93]        [N/A, 99.61]        [N/A, 99.66]        [N/A, 99.91]        [N/A, 99.77]        [N/A, 99.74]        [N/A, 99.38]        [N/A, 98.69]        [N/A, 98.40]        [N/A, 98.04]        [N/A, 98.05]        [N/A, 97.38]        [N/A, 97.79]        [N/A, 98.38]        [N/A, 98.84]        [N/A, 98.58]          [N/A, N/A]
		//		, Mauritius                     [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 42.17]        [N/A, 41.37]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 64.43]        [N/A, 66.43]        [N/A, 68.24]        [N/A, 70.42]        [N/A, 73.49]          [N/A, N/A]        [N/A, 79.38]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 85.35]        [N/A, 84.30]          [N/A, N/A]
		//		, Algeria                       [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]         [N/A, 9.28]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 38.78]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 52.25]        [N/A, 52.76]        [N/A, 52.61]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		]
		//		Done with creating random linked list of countries.
		//
		//
		//		What is the name of the country you want to search for?
		//		Israel
		//		Country Israel found:
		//		Israel                        [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 96.05]        [N/A, 97.91]        [N/A, 98.93]        [N/A, 99.61]        [N/A, 99.66]        [N/A, 99.91]        [N/A, 99.77]        [N/A, 99.74]        [N/A, 99.38]        [N/A, 98.69]        [N/A, 98.40]        [N/A, 98.04]        [N/A, 98.05]        [N/A, 97.38]        [N/A, 97.79]        [N/A, 98.38]        [N/A, 98.84]        [N/A, 98.58]          [N/A, N/A]
		//
		//		Done with searching for a country name.



		//testFindingIndicatorDataInList(selectedCountryList);
		//_________________OUTPUT for 1. GDP per Capita (Short for debugging):_________________
		//		What is the index of the country you want data on? (Enter a index between 0 and 6)
		//		Available countries are:
		//		[Canada                        [40386.70]          [44544.53]          [46596.34]          [40773.45]          [47447.48]          [52082.21]          [52496.69]          [52418.32]          [50633.21]          [43525.37]          [42348.95]          [45032.12]
		//		, Argentina                      [5878.76]           [7193.62]           [8953.36]           [8161.31]          [10276.26]          [12726.91]          [12969.71]          [12976.64]          [12245.26]          [13698.29]          [12654.36]          [14401.97]
		//		, Argentina                      [5878.76]           [7193.62]           [8953.36]           [8161.31]          [10276.26]          [12726.91]          [12969.71]          [12976.64]          [12245.26]          [13698.29]          [12654.36]          [14401.97]
		//		, Egypt, Arab Rep.               [1375.20]           [1640.48]           [2011.25]           [2291.67]           [2602.48]           [2747.48]           [3181.44]           [3213.39]           [3327.75]           [3547.71]           [3479.28]           [2412.73]
		//		, Canada                        [40386.70]          [44544.53]          [46596.34]          [40773.45]          [47447.48]          [52082.21]          [52496.69]          [52418.32]          [50633.21]          [43525.37]          [42348.95]          [45032.12]
		//		, India                           [792.03]           [1018.17]            [991.48]           [1090.32]           [1345.77]           [1461.67]           [1446.99]           [1452.20]           [1576.00]           [1606.04]           [1717.47]           [1939.61]
		//		, India                           [792.03]           [1018.17]            [991.48]           [1090.32]           [1345.77]           [1461.67]           [1446.99]           [1452.20]           [1576.00]           [1606.04]           [1717.47]           [1939.61]
		//		]
		//		3
		//
		//		What period are you interested in? Available years are from 2006 to 2017.
		//		Enter [starting year],[end year].
		//		2007,2017
		//		Egypt, Arab Rep.            [1640.48]              [2011.25]              [2291.67]              [2602.48]              [2747.48]              [3181.44]              [3213.39]              [3327.75]              [3547.71]              [3479.28]              [2412.73]
		//
		//		Done with TestCountryList.



		//_________________OUTPUT for 2. School Enrollment (Short for debugging):_________________
		//		What is the index of the country you want data on? (Enter a index between 0 and 6)
		//		Available countries are:
		//		[Syrian Arab Republic        [N/A, 63.83]        [N/A, 65.49]        [N/A, 66.18]        [N/A, 66.30]        [N/A, 66.94]        [N/A, 68.81]        [N/A, 70.62]        [N/A, 45.52]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, Syrian Arab Republic        [N/A, 63.83]        [N/A, 65.49]        [N/A, 66.18]        [N/A, 66.30]        [N/A, 66.94]        [N/A, 68.81]        [N/A, 70.62]        [N/A, 45.52]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, United States               [N/A, 88.29]        [N/A, 88.39]        [N/A, 88.56]        [N/A, 87.94]        [N/A, 86.70]        [N/A, 87.63]        [N/A, 87.33]        [N/A, 88.12]        [N/A, 88.90]        [N/A, 90.83]          [N/A, N/A]          [N/A, N/A]
		//		, Canada                        [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 92.31]        [N/A, 91.77]        [N/A, 91.35]        [N/A, 92.86]        [N/A, 99.65]          [N/A, N/A]
		//		, Egypt, Arab Rep.              [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 79.09]          [N/A, N/A]        [N/A, 81.49]          [N/A, N/A]
		//		, Canada                        [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 92.31]        [N/A, 91.77]        [N/A, 91.35]        [N/A, 92.86]        [N/A, 99.65]          [N/A, N/A]
		//		, United States               [N/A, 88.29]        [N/A, 88.39]        [N/A, 88.56]        [N/A, 87.94]        [N/A, 86.70]        [N/A, 87.63]        [N/A, 87.33]        [N/A, 88.12]        [N/A, 88.90]        [N/A, 90.83]          [N/A, N/A]          [N/A, N/A]
		//		]
		//		3
		//
		//		What period are you interested in? Available years are from 2006 to 2017.
		//		Enter [starting year],[end year].
		//		2007,2017
		//		Canada [N/A, N/A]   [N/A, N/A]   [N/A, N/A]   [N/A, N/A]   [N/A, N/A]   [N/A, 92.31]   [N/A, 91.77]   [N/A, 91.35]   [N/A, 92.86]   [N/A, 99.65]   [N/A, N/A]
		//
		//		Done with TestCountryList.



		//_________________OUTPUT for 3. GDP per Capita:_________________
		//		What is the index of the country you want data on? (Enter a index between 0 and 6)
		//		Available countries are:
		//		[Armenia                            [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]            [637.86]            [590.12]            [369.63]            [357.20]            [400.52]            [456.38]            [504.06]            [523.28]            [609.17]            [597.43]            [622.74]            [694.43]            [783.26]            [930.17]           [1191.96]           [1643.76]           [2158.00]           [3138.81]           [4010.03]           [2993.83]           [3218.38]           [3526.98]           [3684.80]           [3843.59]           [3994.71]           [3617.94]           [3605.74]           [3936.80]
		//		, Heavily indebted poor countries (HIPC)            [107.73]            [107.95]            [114.90]            [135.97]            [117.53]            [133.06]            [141.59]            [134.73]            [137.30]            [149.49]            [152.68]            [160.39]            [169.22]            [198.47]            [237.10]            [261.95]            [270.45]            [311.22]            [346.26]            [381.69]            [387.54]            [376.39]            [365.93]            [334.39]            [323.81]            [332.90]            [372.14]            [396.50]            [388.18]            [367.92]            [364.19]            [365.13]            [322.34]            [323.29]            [278.35]            [307.37]            [312.48]            [319.35]            [320.02]            [311.20]            [328.17]            [303.26]            [320.13]            [354.25]            [396.58]            [444.57]            [518.76]            [601.13]            [706.70]            [691.36]            [744.28]            [808.59]            [846.80]            [894.22]            [922.95]            [883.41]            [875.87]            [952.51]
		//		, West Bank and Gaza                 [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]           [1201.58]           [1326.56]           [1317.47]           [1389.16]           [1465.05]           [1499.49]           [1476.17]           [1335.55]           [1156.22]           [1257.70]           [1337.57]           [1455.19]           [1441.46]           [1575.56]           [1855.46]           [1963.20]           [2338.72]           [2664.95]           [2787.17]           [2992.20]           [2960.78]           [2865.81]           [2949.69]           [3094.73]
		//		, Bermuda                        [1902.40]           [1961.54]           [2020.39]           [2020.27]           [2199.73]           [2282.22]           [2630.85]           [2982.75]           [2830.19]           [3053.70]           [3387.27]           [3866.30]           [4343.17]           [5009.29]           [5853.93]           [6509.43]           [7261.28]           [8370.79]           [8876.87]           [9613.38]          [11218.22]          [13425.98]          [14166.17]          [15902.02]          [17469.83]          [18269.54]          [20450.66]          [22411.80]          [24253.18]          [25517.92]          [26841.52]          [27700.31]          [28669.68]          [30900.69]          [31476.06]          [33989.72]          [44826.79]          [48478.88]          [51371.74]          [54245.46]          [56284.17]          [58883.96]          [62583.10]          [66111.73]          [70359.32]          [75882.03]          [83912.70]          [90849.59]          [93605.75]          [88463.31]          [88207.33]          [85973.16]          [85458.46]          [85748.07]               [N/A]               [N/A]               [N/A]               [N/A]
		//		, Ukraine                            [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]           [1249.45]           [1449.97]           [1597.54]           [1569.74]               [N/A]           [1378.64]           [1257.35]           [1011.99]            [935.99]            [872.71]            [991.23]            [835.26]            [635.77]            [635.71]            [780.74]            [879.48]           [1048.52]           [1367.35]           [1828.72]           [2303.02]           [3068.61]           [3891.04]           [2545.48]           [2965.14]           [3569.76]           [3855.42]           [4029.72]           [3104.66]           [2124.66]           [2185.73]           [2639.82]
		//		, Pacific island small states               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]            [883.44]            [867.99]            [887.47]           [1008.44]           [1209.83]           [1354.84]           [1365.29]           [1302.55]           [1218.89]           [1308.16]           [1215.57]           [1299.24]           [1259.71]           [1304.23]           [1345.27]           [1429.12]           [1494.99]           [1617.03]           [1681.47]           [1907.15]           [2025.05]           [2139.73]           [2106.02]           [1777.03]           [1920.09]           [1753.43]           [1697.68]           [1767.09]           [2048.07]           [2339.12]           [2540.14]           [2638.50]           [2858.26]           [3040.96]           [2643.08]           [2896.75]           [3401.06]           [3586.75]           [3670.62]           [3776.84]           [3640.27]           [3778.83]           [4004.38]
		//		, Maldives                           [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]            [268.10]            [273.17]            [282.04]            [327.92]            [598.53]            [670.52]            [722.67]            [695.48]            [803.35]            [875.07]            [963.60]           [1064.04]           [1206.03]           [1329.41]           [1432.52]           [1570.32]           [1736.74]           [1923.09]           [2006.26]           [2146.72]           [2226.72]           [3031.70]           [3047.59]           [3481.44]           [3952.12]           [3648.78]           [4811.67]           [5559.51]           [6583.45]           [6615.76]           [7100.41]           [7395.69]           [7473.20]           [8291.49]           [9056.65]           [9575.77]           [9871.91]          [10535.79]
		//		]
		//		3
		//
		//		What period are you interested in? Available years are from 1960 to 2017.
		//		Enter [starting year],[end year].
		//		2007,2017
		//		Bermuda           [90849.59]             [93605.75]             [88463.31]             [88207.33]             [85973.16]             [85458.46]             [85748.07]                  [N/A]                  [N/A]                  [N/A]                  [N/A]
		//
		//		Done with TestCountryList.



		//_________________OUTPUT for 4. School Enrollment:_________________
		//		What is the index of the country you want data on? (Enter a index between 0 and 6)
		//		Available countries are:
		//		[South Sudan                   [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]         [N/A, 4.76]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]         [N/A, 4.92]          [N/A, N/A]          [N/A, N/A]
		//		, Madagascar                    [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 17.30]          [N/A, N/A]        [N/A, 21.81]        [N/A, 23.30]        [N/A, 24.94]          [N/A, N/A]        [N/A, 29.94]        [N/A, 30.99]          [N/A, N/A]        [N/A, 31.02]        [N/A, 30.29]        [N/A, 30.15]          [N/A, N/A]
		//		, New Caledonia                 [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, East Asia & Pacific (excluding high income)          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 60.50]        [N/A, 61.80]        [N/A, 62.55]        [N/A, 63.43]        [N/A, 63.36]        [N/A, 64.57]        [N/A, 65.83]        [N/A, 66.57]        [N/A, 67.28]        [N/A, 69.09]        [N/A, 69.00]        [N/A, 70.21]        [N/A, 71.99]        [N/A, 73.91]        [N/A, 74.34]        [N/A, 75.38]        [N/A, 78.49]        [N/A, 78.78]        [N/A, 77.85]          [N/A, N/A]
		//		, Israel                        [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 96.05]        [N/A, 97.91]        [N/A, 98.93]        [N/A, 99.61]        [N/A, 99.66]        [N/A, 99.91]        [N/A, 99.77]        [N/A, 99.74]        [N/A, 99.38]        [N/A, 98.69]        [N/A, 98.40]        [N/A, 98.04]        [N/A, 98.05]        [N/A, 97.38]        [N/A, 97.79]        [N/A, 98.38]        [N/A, 98.84]        [N/A, 98.58]          [N/A, N/A]
		//		, Mauritius                     [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 42.17]        [N/A, 41.37]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 64.43]        [N/A, 66.43]        [N/A, 68.24]        [N/A, 70.42]        [N/A, 73.49]          [N/A, N/A]        [N/A, 79.38]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 85.35]        [N/A, 84.30]          [N/A, N/A]
		//		, Algeria                       [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]         [N/A, 9.28]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 38.78]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 52.25]        [N/A, 52.76]        [N/A, 52.61]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		]
		//		3
		//
		//		What period are you interested in? Available years are from 1960 to 2017.
		//		Enter [starting year],[end year].
		//		2007,2017
		//		East Asia & Pacific (excluding high income) [N/A, 69.09]   [N/A, 69.00]   [N/A, 70.21]   [N/A, 71.99]   [N/A, 73.91]   [N/A, 74.34]   [N/A, 75.38]   [N/A, 78.49]   [N/A, 78.78]   [N/A, 77.85]   [N/A, N/A]
		//
		//		Done with TestCountryList.










		// TEST CASE 2 (Size of list is 10 - Handles searching for a country name that does not exist in the list):

		//displayCountryNames(countries);
		//_________________OUTPUT for 1. GDP per Capita (Short for debugging):_________________
		//		The selected indicator is GDP_PER_CAPITA
		//
		//		Given the resources/gdpPerCapita_short_12years.csv file, the output is:
		//		GDP per capita (current US$) updated at 8/28/18
		//
		//		Parsing filename resources/gdpPerCapita_short_12years.csv
		//
		//		Country names:
		//		Argentina, Canada, China, Egypt, Arab Rep., India, Nepal, Syrian Arab Republic, United States,



		//_________________OUTPUT for 2. School Enrollment (Short for debugging):_________________
		//		The selected indicator is SCHOOL_ENROLLMENT_PRIMARY
		//
		//		Given the resources/childrenEnrolledInPrimary_short_12years.csv file, the output is:
		//		School Enrollment In Primary (% net) updated at 7/25/18
		//
		//		Parsing filename resources/childrenEnrolledInPrimary_short_12years.csv
		//
		//		Given the resources/childrenEnrolledInSecondary_short_12years.csv file, the output is:
		//		School Enrollment In Secondary (% net) updated at 6/28/18
		//
		//		Parsing filename resources/childrenEnrolledInSecondary_short_12years.csv
		//
		//		Country names:
		//		Afghanistan, Canada, China, Egypt, Arab Rep., India, Nepal, Syrian Arab Republic, United States,



		//_________________OUTPUT for 3. GDP per Capita:_________________
		//		The selected indicator is GDP_PER_CAPITA
		//
		//		Given the resources/gdpPerCapita.csv file, the output is:
		//		GDP per capita (current US$) updated at 8/28/18
		//
		//		Parsing filename resources/gdpPerCapita.csv
		//
		//		Country names:
		//		Aruba, Afghanistan, Angola, Albania, Andorra, Arab World, United Arab Emirates, Argentina, Armenia, American Samoa, Antigua and Barbuda, Australia, Austria, Azerbaijan, Burundi, Belgium, Benin, Burkina Faso, Bangladesh, Bulgaria, Bahrain, Bahamas, The, Bosnia and Herzegovina, Belarus, Belize, Bermuda, Bolivia, Brazil, Barbados, Brunei Darussalam, Bhutan, Botswana, Central African Republic, Canada, Central Europe and the Baltics, Switzerland, Channel Islands, Chile, China, Cote d'Ivoire, Cameroon, Congo, Dem. Rep., Congo, Rep., Colombia, Comoros, Cabo Verde, Costa Rica, Caribbean small states, Cuba, Curacao
		//		Cayman Islands, Cyprus, Czech Republic, Germany, Djibouti, Dominica, Denmark, Dominican Republic, Algeria, East Asia & Pacific (excluding high income), Early-demographic dividend, East Asia & Pacific, Europe & Central Asia (excluding high income), Europe & Central Asia, Ecuador, Egypt, Arab Rep., Euro area, Eritrea, Spain, Estonia, Ethiopia, European Union, Fragile and conflict affected situations, Finland, Fiji, France, Faroe Islands, Micronesia, Fed. Sts., Gabon, United Kingdom, Georgia, Ghana, Gibraltar, Guinea, Gambia, The, Guinea-Bissau, Equatorial Guinea, Greece, Grenada, Greenland, Guatemala, Guam, Guyana, High income, Hong Kong SAR, China, Honduras, Heavily indebted poor countries (HIPC), Croatia, Haiti, Hungary
		//		IBRD only, IDA & IBRD total, IDA total, IDA blend, Indonesia, IDA only, Isle of Man, India, Not classified, Ireland, Iran, Islamic Rep., Iraq, Iceland, Israel, Italy, Jamaica, Jordan, Japan, Kazakhstan, Kenya, Kyrgyz Republic, Cambodia, Kiribati, St. Kitts and Nevis, Korea, Rep., Kuwait, Latin America & Caribbean (excluding high income), Lao PDR, Lebanon, Liberia, Libya, St. Lucia, Latin America & Caribbean, Least developed countries: UN classification, Low income, Liechtenstein, Sri Lanka, Lower middle income, Low & middle income, Lesotho, Late-demographic dividend, Lithuania, Luxembourg, Latvia, Macao SAR, China, St. Martin (French part), Morocco, Monaco, Moldova, Madagascar
		//		Maldives, Middle East & North Africa, Mexico, Marshall Islands, Middle income, Macedonia, FYR, Mali, Malta, Myanmar, Middle East & North Africa (excluding high income), Montenegro, Mongolia, Northern Mariana Islands, Mozambique, Mauritania, Mauritius, Malawi, Malaysia, North America, Namibia, New Caledonia, Niger, Nigeria, Nicaragua, Netherlands, Norway, Nepal, Nauru, New Zealand, OECD members, Oman, Other small states, Pakistan, Panama, Peru, Philippines, Palau, Papua New Guinea, Poland, Pre-demographic dividend, Puerto Rico, Korea, Dem. People's Rep., Portugal, Paraguay, West Bank and Gaza, Pacific island small states, Post-demographic dividend, French Polynesia, Qatar, Romania
		//		Russian Federation, Rwanda, South Asia, Saudi Arabia, Sudan, Senegal, Singapore, Solomon Islands, Sierra Leone, El Salvador, San Marino, Somalia, Serbia, Sub-Saharan Africa (excluding high income), South Sudan, Sub-Saharan Africa, Small states, Sao Tome and Principe, Suriname, Slovak Republic, Slovenia, Sweden, Swaziland, Sint Maarten (Dutch part), Seychelles, Syrian Arab Republic, Turks and Caicos Islands, Chad, East Asia & Pacific (IDA & IBRD countries), Europe & Central Asia (IDA & IBRD countries), Togo, Thailand, Tajikistan, Turkmenistan, Latin America & the Caribbean (IDA & IBRD countries), Timor-Leste, Middle East & North Africa (IDA & IBRD countries), Tonga, South Asia (IDA & IBRD), Sub-Saharan Africa (IDA & IBRD countries), Trinidad and Tobago, Tunisia, Turkey, Tuvalu, Tanzania, Uganda, Ukraine, Upper middle income, Uruguay, United States
		//		Uzbekistan, St. Vincent and the Grenadines, Venezuela, RB, British Virgin Islands, Virgin Islands (U.S.), Vietnam, Vanuatu, World, Samoa, Kosovo, Yemen, Rep., South Africa, Zambia, Zimbabwe,



		//_________________OUTPUT for 4. School Enrollment:_________________
		//		The selected indicator is SCHOOL_ENROLLMENT_PRIMARY
		//
		//		Given the resources/childrenEnrolledInPrimary.csv file, the output is:
		//		School Enrollment In Primary (% net) updated at 7/25/18
		//
		//		Parsing filename resources/childrenEnrolledInPrimary.csv
		//
		//		Given the resources/childrenEnrolledInSecondary.csv file, the output is:
		//		School Enrollment In Secondary (% net) updated at 6/28/18
		//
		//		Parsing filename resources/childrenEnrolledInSecondary.csv
		//
		//		Country names:
		//		Aruba, Afghanistan, Angola, Albania, Andorra, Arab World, United Arab Emirates, Argentina, Armenia, American Samoa, Antigua and Barbuda, Australia, Austria, Azerbaijan, Burundi, Belgium, Benin, Burkina Faso, Bangladesh, Bulgaria, Bahrain, Bahamas, The, Bosnia and Herzegovina, Belarus, Belize, Bermuda, Bolivia, Brazil, Barbados, Brunei Darussalam, Bhutan, Botswana, Central African Republic, Canada, Central Europe and the Baltics, Switzerland, Channel Islands, Chile, China, Cote d'Ivoire, Cameroon, Congo, Dem. Rep., Congo, Rep., Colombia, Comoros, Cabo Verde, Costa Rica, Caribbean small states, Cuba, Curacao
		//		Cayman Islands, Cyprus, Czech Republic, Germany, Djibouti, Dominica, Denmark, Dominican Republic, Algeria, East Asia & Pacific (excluding high income), Early-demographic dividend, East Asia & Pacific, Europe & Central Asia (excluding high income), Europe & Central Asia, Ecuador, Egypt, Arab Rep., Euro area, Eritrea, Spain, Estonia, Ethiopia, European Union, Fragile and conflict affected situations, Finland, Fiji, France, Faroe Islands, Micronesia, Fed. Sts., Gabon, United Kingdom, Georgia, Ghana, Gibraltar, Guinea, Gambia, The, Guinea-Bissau, Equatorial Guinea, Greece, Grenada, Greenland, Guatemala, Guam, Guyana, High income, Hong Kong SAR, China, Honduras, Heavily indebted poor countries (HIPC), Croatia, Haiti, Hungary
		//		IBRD only, IDA & IBRD total, IDA total, IDA blend, Indonesia, IDA only, Isle of Man, India, Not classified, Ireland, Iran, Islamic Rep., Iraq, Iceland, Israel, Italy, Jamaica, Jordan, Japan, Kazakhstan, Kenya, Kyrgyz Republic, Cambodia, Kiribati, St. Kitts and Nevis, Korea, Rep., Kuwait, Latin America & Caribbean (excluding high income), Lao PDR, Lebanon, Liberia, Libya, St. Lucia, Latin America & Caribbean, Least developed countries: UN classification, Low income, Liechtenstein, Sri Lanka, Lower middle income, Low & middle income, Lesotho, Late-demographic dividend, Lithuania, Luxembourg, Latvia, Macao SAR, China, St. Martin (French part), Morocco, Monaco, Moldova, Madagascar
		//		Maldives, Middle East & North Africa, Mexico, Marshall Islands, Middle income, Macedonia, FYR, Mali, Malta, Myanmar, Middle East & North Africa (excluding high income), Montenegro, Mongolia, Northern Mariana Islands, Mozambique, Mauritania, Mauritius, Malawi, Malaysia, North America, Namibia, New Caledonia, Niger, Nigeria, Nicaragua, Netherlands, Norway, Nepal, Nauru, New Zealand, OECD members, Oman, Other small states, Pakistan, Panama, Peru, Philippines, Palau, Papua New Guinea, Poland, Pre-demographic dividend, Puerto Rico, Korea, Dem. People's Rep., Portugal, Paraguay, West Bank and Gaza, Pacific island small states, Post-demographic dividend, French Polynesia, Qatar, Romania
		//		Russian Federation, Rwanda, South Asia, Saudi Arabia, Sudan, Senegal, Singapore, Solomon Islands, Sierra Leone, El Salvador, San Marino, Somalia, Serbia, Sub-Saharan Africa (excluding high income), South Sudan, Sub-Saharan Africa, Small states, Sao Tome and Principe, Suriname, Slovak Republic, Slovenia, Sweden, Swaziland, Sint Maarten (Dutch part), Seychelles, Syrian Arab Republic, Turks and Caicos Islands, Chad, East Asia & Pacific (IDA & IBRD countries), Europe & Central Asia (IDA & IBRD countries), Togo, Thailand, Tajikistan, Turkmenistan, Latin America & the Caribbean (IDA & IBRD countries), Timor-Leste, Middle East & North Africa (IDA & IBRD countries), Tonga, South Asia (IDA & IBRD), Sub-Saharan Africa (IDA & IBRD countries), Trinidad and Tobago, Tunisia, Turkey, Tuvalu, Tanzania, Uganda, Ukraine, Upper middle income, Uruguay, United States
		//		Uzbekistan, St. Vincent and the Grenadines, Venezuela, RB, British Virgin Islands, Virgin Islands (U.S.), Vietnam, Vanuatu, World, Samoa, Kosovo, Yemen, Rep., South Africa, Zambia, Zimbabwe,



		//selectedCountryList = createRandomListOfCountries(countries);
		//_________________OUTPUT for 1. GDP per Capita (Short for debugging):_________________
		//		How many countries do you want to add to the list?
		//		10
		//		Adding country with name India to the end of the list.
		//		Adding country with name Argentina to the end of the list.
		//		Adding country with name Egypt, Arab Rep. to the end of the list.
		//		Adding country with name Argentina to the end of the list.
		//		Adding country with name United States to the end of the list.
		//		Adding country with name Nepal to the end of the list.
		//		Adding country with name Nepal to the end of the list.
		//		Adding country with name India to the end of the list.
		//		Adding country with name Nepal to the end of the list.
		//		Adding country with name Syrian Arab Republic to the end of the list.



		//_________________OUTPUT for 2. School Enrollment (Short for debugging):_________________
		//		How many countries do you want to add to the list?
		//		10
		//		Adding country with name Egypt, Arab Rep. to the end of the list.
		//		Adding country with name Afghanistan to the end of the list.
		//		Adding country with name China to the end of the list.
		//		Adding country with name China to the end of the list.
		//		Adding country with name Syrian Arab Republic to the end of the list.
		//		Adding country with name India to the end of the list.
		//		Adding country with name Afghanistan to the end of the list.
		//		Adding country with name China to the end of the list.
		//		Adding country with name Syrian Arab Republic to the end of the list.
		//		Adding country with name Nepal to the end of the list.



		//_________________OUTPUT for 3. GDP per Capita:_________________
		//		How many countries do you want to add to the list?
		//		10
		//		Adding country with name Azerbaijan to the end of the list.
		//		Adding country with name Euro area to the end of the list.
		//		Adding country with name Colombia to the end of the list.
		//		Adding country with name Small states to the end of the list.
		//		Adding country with name Kiribati to the end of the list.
		//		Adding country with name Hong Kong SAR, China to the end of the list.
		//		Adding country with name Fiji to the end of the list.
		//		Adding country with name Ghana to the end of the list.
		//		Adding country with name Belize to the end of the list.
		//		Adding country with name Korea, Rep. to the end of the list.



		//_________________OUTPUT for 4. School Enrollment:_________________
		//		How many countries do you want to add to the list?
		//		10
		//		Adding country with name Seychelles to the end of the list.
		//		Adding country with name Ireland to the end of the list.
		//		Adding country with name IDA & IBRD total to the end of the list.
		//		Adding country with name Montenegro to the end of the list.
		//		Adding country with name Syrian Arab Republic to the end of the list.
		//		Adding country with name Belize to the end of the list.
		//		Adding country with name West Bank and Gaza to the end of the list.
		//		Adding country with name Comoros to the end of the list.
		//		Adding country with name St. Kitts and Nevis to the end of the list.
		//		Adding country with name Kenya to the end of the list.



		//testSearchInList(selectedCountryList);
		//_________________OUTPUT for 1. GDP per Capita (Short for debugging):_________________
		//		[India                           [792.03]           [1018.17]            [991.48]           [1090.32]           [1345.77]           [1461.67]           [1446.99]           [1452.20]           [1576.00]           [1606.04]           [1717.47]           [1939.61]
		//		, Argentina                      [5878.76]           [7193.62]           [8953.36]           [8161.31]          [10276.26]          [12726.91]          [12969.71]          [12976.64]          [12245.26]          [13698.29]          [12654.36]          [14401.97]
		//		, Egypt, Arab Rep.               [1375.20]           [1640.48]           [2011.25]           [2291.67]           [2602.48]           [2747.48]           [3181.44]           [3213.39]           [3327.75]           [3547.71]           [3479.28]           [2412.73]
		//		, Argentina                      [5878.76]           [7193.62]           [8953.36]           [8161.31]          [10276.26]          [12726.91]          [12969.71]          [12976.64]          [12245.26]          [13698.29]          [12654.36]          [14401.97]
		//		, United States                 [46437.07]          [48061.54]          [48401.43]          [47001.56]          [48375.41]          [49793.71]          [51450.96]          [52782.09]          [54696.73]          [56443.82]          [57588.54]          [59531.66]
		//		, Nepal                           [348.63]            [393.88]            [473.84]            [480.72]            [592.18]            [692.12]            [681.79]            [688.62]            [706.24]            [747.16]            [729.12]            [835.08]
		//		, Nepal                           [348.63]            [393.88]            [473.84]            [480.72]            [592.18]            [692.12]            [681.79]            [688.62]            [706.24]            [747.16]            [729.12]            [835.08]
		//		, India                           [792.03]           [1018.17]            [991.48]           [1090.32]           [1345.77]           [1461.67]           [1446.99]           [1452.20]           [1576.00]           [1606.04]           [1717.47]           [1939.61]
		//		, Nepal                           [348.63]            [393.88]            [473.84]            [480.72]            [592.18]            [692.12]            [681.79]            [688.62]            [706.24]            [747.16]            [729.12]            [835.08]
		//		, Syrian Arab Republic           [1762.25]           [2058.04]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]
		//		]
		//		Done with creating random linked list of countries.
		//
		//
		//		What is the name of the country you want to search for?
		//		China
		//		Country China not found.
		//		Done with searching for a country name.



		//_________________OUTPUT for 2. School Enrollment (Short for debugging):_________________
		//		[Egypt, Arab Rep.              [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 79.09]          [N/A, N/A]        [N/A, 81.49]          [N/A, N/A]
		//		, Afghanistan                   [N/A, N/A]        [N/A, 27.38]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 47.50]        [N/A, 47.31]        [N/A, 47.37]          [N/A, N/A]        [N/A, 49.56]
		//		, China                         [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, China                         [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, Syrian Arab Republic        [N/A, 63.83]        [N/A, 65.49]        [N/A, 66.18]        [N/A, 66.30]        [N/A, 66.94]        [N/A, 68.81]        [N/A, 70.62]        [N/A, 45.52]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, India                         [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 61.77]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, Afghanistan                   [N/A, N/A]        [N/A, 27.38]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 47.50]        [N/A, 47.31]        [N/A, 47.37]          [N/A, N/A]        [N/A, 49.56]
		//		, China                         [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, Syrian Arab Republic        [N/A, 63.83]        [N/A, 65.49]        [N/A, 66.18]        [N/A, 66.30]        [N/A, 66.94]        [N/A, 68.81]        [N/A, 70.62]        [N/A, 45.52]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, Nepal                         [N/A, N/A]        [N/A, 44.76]        [N/A, 48.12]          [N/A, N/A]        [N/A, 50.49]        [N/A, 54.74]        [N/A, 57.69]        [N/A, 58.84]        [N/A, 59.69]        [N/A, 60.40]        [N/A, 54.37]        [N/A, 55.29]
		//		]
		//		Done with creating random linked list of countries.
		//
		//
		//		What is the name of the country you want to search for?
		//		Bhutan
		//		Country Bhutan not found.
		//		Done with searching for a country name.



		//_________________OUTPUT for 3. GDP per Capita:_________________
		//		[Azerbaijan                         [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]           [1237.32]           [1209.24]            [676.15]            [530.09]            [436.19]            [397.20]            [409.22]            [505.56]            [561.91]            [573.89]            [655.10]            [703.68]            [763.07]            [883.64]           [1045.02]           [1578.40]           [2473.08]           [3851.44]           [5574.60]           [4950.29]           [5842.81]           [7189.69]           [7496.29]           [7875.76]           [7891.31]           [5500.31]           [3880.74]           [4131.62]
		//		, Euro area                       [924.96]           [1008.90]           [1109.26]           [1232.78]           [1358.66]           [1469.36]           [1589.19]           [1714.27]           [1825.77]           [2003.62]           [2230.24]           [2513.53]           [3015.52]           [3889.04]           [4384.38]           [5057.39]           [5254.30]           [5953.06]           [7261.30]           [8761.46]           [9772.04]           [8461.20]           [8169.83]           [7954.18]           [7616.97]           [7810.54]          [10933.62]          [13484.84]          [14780.09]          [15031.69]          [18829.02]          [19502.78]          [21416.10]          [19533.59]          [20572.18]          [23683.30]          [23910.79]          [21809.82]          [22364.32]          [22206.93]          [20170.93]          [20424.50]          [22114.79]          [27138.11]          [30958.29]          [31966.28]          [33783.09]          [38700.71]          [42220.04]          [38480.92]          [37605.15]          [40610.73]          [37604.60]          [39111.44]          [39823.54]          [34362.69]          [35038.55]          [36869.05]
		//		, Colombia                        [245.20]            [268.10]            [283.92]            [268.32]            [322.47]            [302.45]            [276.49]            [281.97]            [283.11]            [298.05]            [326.29]            [345.85]            [374.62]            [435.73]            [510.99]            [529.09]            [605.82]            [751.63]            [877.80]           [1030.50]           [1204.16]           [1282.36]           [1342.47]           [1304.60]           [1260.40]           [1125.20]           [1103.34]           [1125.26]           [1189.14]           [1175.93]           [1175.15]           [1181.08]           [1385.87]           [1541.71]           [2218.78]           [2470.68]           [2553.55]           [2759.95]           [2509.14]           [2164.43]           [2472.20]           [2395.86]           [2355.73]           [2246.26]           [2740.25]           [3386.03]           [3709.08]           [4674.22]           [5433.72]           [5148.42]           [6250.66]           [7227.74]           [7884.98]           [8030.59]           [7913.38]           [6044.53]           [5756.86]           [6301.59]
		//		, Small states                       [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]            [459.21]            [507.21]            [587.64]            [718.65]           [1097.64]           [1225.75]           [1374.64]           [1533.95]           [1621.38]           [1976.06]           [2567.71]           [2600.72]           [2536.46]           [2403.18]           [2333.13]           [2199.21]           [2111.13]           [2361.89]           [2551.52]           [2604.95]           [2955.90]           [2961.66]           [3102.19]           [3009.15]           [3120.40]           [3482.69]           [3654.55]           [3864.63]           [3798.07]           [4015.80]           [4388.43]           [4335.23]           [4569.60]           [5361.86]           [6354.72]           [7460.24]           [8513.86]           [9982.37]          [11787.05]           [9860.50]          [11095.12]          [13173.54]          [13492.41]          [13704.98]          [13812.85]          [11605.70]          [11129.11]          [11933.40]
		//		, Kiribati                           [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]            [279.32]            [293.68]            [358.48]            [591.57]           [1574.79]            [998.42]            [734.40]            [682.06]            [784.05]            [728.48]            [652.45]            [687.97]            [665.99]            [612.58]            [657.15]            [501.93]            [489.72]            [499.68]            [621.90]            [580.30]            [549.76]            [644.71]            [638.47]            [619.65]            [715.17]            [724.79]            [842.96]            [842.28]            [801.16]            [832.05]            [796.79]            [734.95]            [826.59]           [1015.04]           [1130.60]           [1214.56]           [1151.56]           [1357.63]           [1413.30]           [1297.29]           [1493.16]           [1692.61]           [1763.82]           [1724.36]           [1619.34]           [1504.72]           [1587.06]           [1685.17]
		//		, Hong Kong SAR, China            [429.44]            [436.75]            [487.82]            [565.73]            [629.59]            [676.81]            [685.93]            [723.24]            [714.48]            [825.52]            [960.03]           [1106.47]           [1384.74]           [1893.18]           [2144.61]           [2252.11]           [2850.01]           [3429.42]           [3923.94]           [4569.45]           [5700.41]           [5991.32]           [6133.78]           [5595.24]           [6208.23]           [6542.93]           [7435.03]           [9071.33]          [10609.75]          [12097.78]          [13485.54]          [15465.86]          [17976.43]          [20395.52]          [22502.58]          [23497.49]          [24818.15]          [27330.03]          [25808.97]          [25091.67]          [25756.66]          [25230.22]          [24665.89]          [23977.02]          [24928.10]          [26649.75]          [28224.22]          [30594.02]          [31515.66]          [30697.34]          [32550.00]          [35142.49]          [36730.88]          [38403.78]          [40315.29]          [42431.89]          [43737.04]          [46193.61]
		//		, Fiji                            [285.54]            [287.33]            [291.54]            [296.77]            [310.87]            [317.07]            [316.18]            [333.31]            [334.65]            [357.46]            [422.41]            [466.04]            [583.35]            [768.74]            [987.98]           [1186.74]           [1182.18]           [1202.71]           [1360.87]           [1640.68]           [1893.05]           [1898.59]           [1786.92]           [1638.64]           [1681.97]           [1603.59]           [1795.61]           [1632.07]           [1535.41]           [1632.14]           [1834.99]           [1881.57]           [2057.41]           [2166.05]           [2383.92]           [2540.75]           [2714.25]           [2641.06]           [2070.17]           [2408.17]           [2076.01]           [2038.89]           [2259.06]           [2835.97]           [3332.92]           [3658.63]           [3749.94]           [4078.82]           [4177.66]           [3369.41]           [3651.97]           [4353.12]           [4546.74]           [4763.07]           [5061.40]           [4889.46]           [5197.51]           [5589.39]
		//		, Ghana                           [182.98]            [189.71]            [195.12]            [210.97]            [230.43]            [266.32]            [269.46]            [216.84]            [202.76]            [233.65]            [257.65]            [273.82]            [232.54]            [263.69]            [301.37]            [285.83]            [275.88]            [313.00]            [353.71]            [381.04]            [411.52]            [379.80]            [351.32]            [341.09]            [358.40]            [354.22]            [437.08]            [376.43]            [375.18]            [368.96]            [402.59]            [438.61]            [414.77]            [375.32]            [333.40]            [385.74]            [403.53]            [391.36]            [414.77]            [417.77]            [263.11]            [273.66]            [309.48]            [373.28]            [423.19]            [498.17]            [922.95]           [1090.69]           [1224.40]           [1086.77]           [1312.61]           [1574.98]           [1629.80]           [1814.49]           [1449.66]           [1353.68]           [1517.50]           [1641.49]
		//		, Belize                          [304.92]            [316.40]            [327.13]            [336.94]            [351.16]            [377.59]            [406.10]            [420.43]            [386.95]            [396.65]            [435.69]            [474.44]            [519.56]            [605.93]            [786.07]            [885.98]            [717.04]            [858.83]            [980.82]           [1074.27]           [1350.98]           [1307.21]           [1183.17]           [1212.92]           [1315.27]           [1268.18]           [1343.71]           [1586.45]           [1758.94]           [1979.35]           [2202.32]           [2326.85]           [2666.98]           [2833.06]           [2880.21]           [2996.38]           [3001.67]           [2952.60]           [2991.92]           [3065.49]           [3364.42]           [3419.28]           [3556.56]           [3679.91]           [3831.54]           [3933.33]           [4187.38]           [4324.88]           [4470.22]           [4258.79]           [4344.15]           [4517.14]           [4673.79]           [4685.25]           [4844.98]           [4950.26]           [4960.18]           [4905.51]
		//		, Korea, Rep.                     [158.24]             [93.82]            [106.13]            [146.29]            [123.59]            [108.70]            [133.45]            [161.12]            [198.37]            [243.33]            [279.13]            [300.76]            [323.60]            [405.88]            [561.57]            [615.20]            [830.70]           [1050.90]           [1398.48]           [1773.53]           [1704.47]           [1870.34]           [1977.64]           [2180.49]           [2390.67]           [2457.33]           [2803.37]           [3510.99]           [4686.14]           [5736.90]           [6516.31]           [7523.48]           [8001.54]           [8740.95]          [10205.81]          [12332.98]          [13137.91]          [12131.87]           [8085.32]          [10409.33]          [11947.58]          [11252.91]          [12782.53]          [14209.39]          [15907.67]          [18639.52]          [20888.38]          [23060.71]          [20430.64]          [18291.92]          [22086.95]          [24079.79]          [24358.78]          [25890.02]          [27811.37]          [27105.08]          [27608.25]          [29742.84]
		//		]
		//		Done with creating random linked list of countries.
		//
		//
		//		What is the name of the country you want to search for?
		//		Thailand
		//		Country Thailand not found.
		//		Done with searching for a country name.



		//_________________OUTPUT for 4. School Enrollment:_________________
		//		[Seychelles                    [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 44.91]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 78.91]        [N/A, 82.24]        [N/A, 82.16]        [N/A, 83.61]          [N/A, N/A]        [N/A, 89.68]        [N/A, 66.52]        [N/A, 65.30]        [N/A, 71.17]        [N/A, 70.55]        [N/A, 74.80]        [N/A, 74.77]        [N/A, 81.56]          [N/A, N/A]        [N/A, 79.03]        [N/A, 76.29]        [N/A, 76.16]        [N/A, 74.64]        [N/A, 44.32]          [N/A, N/A]        [N/A, 79.51]        [N/A, 82.43]        [N/A, 88.58]          [N/A, N/A]          [N/A, N/A]
		//		, Ireland                       [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 62.92]        [N/A, 64.71]        [N/A, 68.90]        [N/A, 71.78]        [N/A, 73.79]        [N/A, 74.77]        [N/A, 75.34]        [N/A, 75.88]          [N/A, N/A]        [N/A, 78.14]          [N/A, N/A]        [N/A, 82.55]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 75.91]        [N/A, 77.48]          [N/A, N/A]          [N/A, N/A]        [N/A, 81.18]        [N/A, 81.04]        [N/A, 80.33]        [N/A, 86.85]        [N/A, 85.62]        [N/A, 85.34]        [N/A, 84.92]        [N/A, 86.79]        [N/A, 88.86]        [N/A, 91.53]        [N/A, 93.24]        [N/A, 94.56]        [N/A, 96.17]        [N/A, 97.44]        [N/A, 98.92]        [N/A, 99.33]          [N/A, N/A]        [N/A, 99.76]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, IDA & IBRD total              [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 49.50]        [N/A, 49.81]        [N/A, 50.84]        [N/A, 51.54]        [N/A, 52.16]        [N/A, 53.00]        [N/A, 53.90]        [N/A, 54.62]        [N/A, 55.21]        [N/A, 56.26]        [N/A, 57.07]        [N/A, 57.40]        [N/A, 58.75]        [N/A, 59.92]        [N/A, 60.55]        [N/A, 61.42]        [N/A, 62.32]        [N/A, 62.56]        [N/A, 62.39]          [N/A, N/A]
		//		, Montenegro                    [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 90.04]          [N/A, N/A]
		//		, Syrian Arab Republic          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 26.20]          [N/A, N/A]        [N/A, 35.45]        [N/A, 35.64]        [N/A, 36.10]        [N/A, 38.51]        [N/A, 40.64]        [N/A, 41.45]        [N/A, 42.96]        [N/A, 42.39]        [N/A, 40.03]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 38.14]        [N/A, 37.54]        [N/A, 37.48]        [N/A, 38.52]        [N/A, 38.50]        [N/A, 39.33]        [N/A, 40.97]        [N/A, 41.03]        [N/A, 57.40]        [N/A, 60.89]        [N/A, 64.00]        [N/A, 63.83]        [N/A, 65.49]        [N/A, 66.18]        [N/A, 66.30]        [N/A, 66.94]        [N/A, 68.81]        [N/A, 70.62]        [N/A, 45.52]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, Belize                        [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 55.27]        [N/A, 58.58]        [N/A, 60.15]          [N/A, N/A]        [N/A, 68.08]          [N/A, N/A]        [N/A, 65.05]        [N/A, 64.78]        [N/A, 64.22]        [N/A, 63.42]        [N/A, 63.75]        [N/A, 63.69]        [N/A, 65.41]        [N/A, 67.51]        [N/A, 69.21]        [N/A, 69.33]        [N/A, 69.52]        [N/A, 72.22]          [N/A, N/A]
		//		, West Bank and Gaza            [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 73.20]        [N/A, 74.65]        [N/A, 75.40]        [N/A, 78.09]        [N/A, 80.88]        [N/A, 84.54]        [N/A, 85.64]        [N/A, 86.72]        [N/A, 86.53]        [N/A, 86.39]        [N/A, 85.10]        [N/A, 83.19]        [N/A, 81.05]        [N/A, 81.00]        [N/A, 80.47]        [N/A, 80.29]        [N/A, 81.17]        [N/A, 82.09]          [N/A, N/A]
		//		, Comoros                       [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 44.42]        [N/A, 44.77]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, St. Kitts and Nevis           [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, Kenya                         [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 33.40]          [N/A, N/A]        [N/A, 34.76]        [N/A, 36.33]        [N/A, 39.10]        [N/A, 40.85]        [N/A, 41.80]        [N/A, 43.95]        [N/A, 48.67]        [N/A, 48.33]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		]
		//		Done with creating random linked list of countries.
		//
		//
		//		What is the name of the country you want to search for?
		//		Iceland
		//		Country Iceland not found.
		//		Done with searching for a country name.



		//testFindingIndicatorDataInList(selectedCountryList);
		//_________________OUTPUT for 1. GDP per Capita (Short for debugging):_________________
		//		What is the index of the country you want data on? (Enter a index between 0 and 9)
		//		Available countries are:
		//		[India                           [792.03]           [1018.17]            [991.48]           [1090.32]           [1345.77]           [1461.67]           [1446.99]           [1452.20]           [1576.00]           [1606.04]           [1717.47]           [1939.61]
		//		, Argentina                      [5878.76]           [7193.62]           [8953.36]           [8161.31]          [10276.26]          [12726.91]          [12969.71]          [12976.64]          [12245.26]          [13698.29]          [12654.36]          [14401.97]
		//		, Egypt, Arab Rep.               [1375.20]           [1640.48]           [2011.25]           [2291.67]           [2602.48]           [2747.48]           [3181.44]           [3213.39]           [3327.75]           [3547.71]           [3479.28]           [2412.73]
		//		, Argentina                      [5878.76]           [7193.62]           [8953.36]           [8161.31]          [10276.26]          [12726.91]          [12969.71]          [12976.64]          [12245.26]          [13698.29]          [12654.36]          [14401.97]
		//		, United States                 [46437.07]          [48061.54]          [48401.43]          [47001.56]          [48375.41]          [49793.71]          [51450.96]          [52782.09]          [54696.73]          [56443.82]          [57588.54]          [59531.66]
		//		, Nepal                           [348.63]            [393.88]            [473.84]            [480.72]            [592.18]            [692.12]            [681.79]            [688.62]            [706.24]            [747.16]            [729.12]            [835.08]
		//		, Nepal                           [348.63]            [393.88]            [473.84]            [480.72]            [592.18]            [692.12]            [681.79]            [688.62]            [706.24]            [747.16]            [729.12]            [835.08]
		//		, India                           [792.03]           [1018.17]            [991.48]           [1090.32]           [1345.77]           [1461.67]           [1446.99]           [1452.20]           [1576.00]           [1606.04]           [1717.47]           [1939.61]
		//		, Nepal                           [348.63]            [393.88]            [473.84]            [480.72]            [592.18]            [692.12]            [681.79]            [688.62]            [706.24]            [747.16]            [729.12]            [835.08]
		//		, Syrian Arab Republic           [1762.25]           [2058.04]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]
		//		]
		//		7
		//
		//		What period are you interested in? Available years are from 2006 to 2017.
		//		Enter [starting year],[end year].
		//		2010,2017
		//		India            [1345.77]              [1461.67]              [1446.99]              [1452.20]              [1576.00]              [1606.04]              [1717.47]              [1939.61]
		//
		//		Done with TestCountryList.



		//_________________OUTPUT for 2. School Enrollment (Short for debugging):_________________
		//		What is the index of the country you want data on? (Enter a index between 0 and 9)
		//		Available countries are:
		//		[Egypt, Arab Rep.              [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 79.09]          [N/A, N/A]        [N/A, 81.49]          [N/A, N/A]
		//		, Afghanistan                   [N/A, N/A]        [N/A, 27.38]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 47.50]        [N/A, 47.31]        [N/A, 47.37]          [N/A, N/A]        [N/A, 49.56]
		//		, China                         [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, China                         [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, Syrian Arab Republic        [N/A, 63.83]        [N/A, 65.49]        [N/A, 66.18]        [N/A, 66.30]        [N/A, 66.94]        [N/A, 68.81]        [N/A, 70.62]        [N/A, 45.52]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, India                         [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 61.77]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, Afghanistan                   [N/A, N/A]        [N/A, 27.38]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 47.50]        [N/A, 47.31]        [N/A, 47.37]          [N/A, N/A]        [N/A, 49.56]
		//		, China                         [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, Syrian Arab Republic        [N/A, 63.83]        [N/A, 65.49]        [N/A, 66.18]        [N/A, 66.30]        [N/A, 66.94]        [N/A, 68.81]        [N/A, 70.62]        [N/A, 45.52]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, Nepal                         [N/A, N/A]        [N/A, 44.76]        [N/A, 48.12]          [N/A, N/A]        [N/A, 50.49]        [N/A, 54.74]        [N/A, 57.69]        [N/A, 58.84]        [N/A, 59.69]        [N/A, 60.40]        [N/A, 54.37]        [N/A, 55.29]
		//		]
		//		7
		//
		//		What period are you interested in? Available years are from 2006 to 2017.
		//		Enter [starting year],[end year].
		//		2010,2017
		//		China [N/A, N/A]   [N/A, N/A]   [N/A, N/A]   [N/A, N/A]   [N/A, N/A]   [N/A, N/A]   [N/A, N/A]   [N/A, N/A]
		//
		//		Done with TestCountryList.



		//_________________OUTPUT for 3. GDP per Capita:_________________
		//		What is the index of the country you want data on? (Enter a index between 0 and 9)
		//		Available countries are:
		//		[Azerbaijan                         [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]           [1237.32]           [1209.24]            [676.15]            [530.09]            [436.19]            [397.20]            [409.22]            [505.56]            [561.91]            [573.89]            [655.10]            [703.68]            [763.07]            [883.64]           [1045.02]           [1578.40]           [2473.08]           [3851.44]           [5574.60]           [4950.29]           [5842.81]           [7189.69]           [7496.29]           [7875.76]           [7891.31]           [5500.31]           [3880.74]           [4131.62]
		//		, Euro area                       [924.96]           [1008.90]           [1109.26]           [1232.78]           [1358.66]           [1469.36]           [1589.19]           [1714.27]           [1825.77]           [2003.62]           [2230.24]           [2513.53]           [3015.52]           [3889.04]           [4384.38]           [5057.39]           [5254.30]           [5953.06]           [7261.30]           [8761.46]           [9772.04]           [8461.20]           [8169.83]           [7954.18]           [7616.97]           [7810.54]          [10933.62]          [13484.84]          [14780.09]          [15031.69]          [18829.02]          [19502.78]          [21416.10]          [19533.59]          [20572.18]          [23683.30]          [23910.79]          [21809.82]          [22364.32]          [22206.93]          [20170.93]          [20424.50]          [22114.79]          [27138.11]          [30958.29]          [31966.28]          [33783.09]          [38700.71]          [42220.04]          [38480.92]          [37605.15]          [40610.73]          [37604.60]          [39111.44]          [39823.54]          [34362.69]          [35038.55]          [36869.05]
		//		, Colombia                        [245.20]            [268.10]            [283.92]            [268.32]            [322.47]            [302.45]            [276.49]            [281.97]            [283.11]            [298.05]            [326.29]            [345.85]            [374.62]            [435.73]            [510.99]            [529.09]            [605.82]            [751.63]            [877.80]           [1030.50]           [1204.16]           [1282.36]           [1342.47]           [1304.60]           [1260.40]           [1125.20]           [1103.34]           [1125.26]           [1189.14]           [1175.93]           [1175.15]           [1181.08]           [1385.87]           [1541.71]           [2218.78]           [2470.68]           [2553.55]           [2759.95]           [2509.14]           [2164.43]           [2472.20]           [2395.86]           [2355.73]           [2246.26]           [2740.25]           [3386.03]           [3709.08]           [4674.22]           [5433.72]           [5148.42]           [6250.66]           [7227.74]           [7884.98]           [8030.59]           [7913.38]           [6044.53]           [5756.86]           [6301.59]
		//		, Small states                       [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]            [459.21]            [507.21]            [587.64]            [718.65]           [1097.64]           [1225.75]           [1374.64]           [1533.95]           [1621.38]           [1976.06]           [2567.71]           [2600.72]           [2536.46]           [2403.18]           [2333.13]           [2199.21]           [2111.13]           [2361.89]           [2551.52]           [2604.95]           [2955.90]           [2961.66]           [3102.19]           [3009.15]           [3120.40]           [3482.69]           [3654.55]           [3864.63]           [3798.07]           [4015.80]           [4388.43]           [4335.23]           [4569.60]           [5361.86]           [6354.72]           [7460.24]           [8513.86]           [9982.37]          [11787.05]           [9860.50]          [11095.12]          [13173.54]          [13492.41]          [13704.98]          [13812.85]          [11605.70]          [11129.11]          [11933.40]
		//		, Kiribati                           [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]               [N/A]            [279.32]            [293.68]            [358.48]            [591.57]           [1574.79]            [998.42]            [734.40]            [682.06]            [784.05]            [728.48]            [652.45]            [687.97]            [665.99]            [612.58]            [657.15]            [501.93]            [489.72]            [499.68]            [621.90]            [580.30]            [549.76]            [644.71]            [638.47]            [619.65]            [715.17]            [724.79]            [842.96]            [842.28]            [801.16]            [832.05]            [796.79]            [734.95]            [826.59]           [1015.04]           [1130.60]           [1214.56]           [1151.56]           [1357.63]           [1413.30]           [1297.29]           [1493.16]           [1692.61]           [1763.82]           [1724.36]           [1619.34]           [1504.72]           [1587.06]           [1685.17]
		//		, Hong Kong SAR, China            [429.44]            [436.75]            [487.82]            [565.73]            [629.59]            [676.81]            [685.93]            [723.24]            [714.48]            [825.52]            [960.03]           [1106.47]           [1384.74]           [1893.18]           [2144.61]           [2252.11]           [2850.01]           [3429.42]           [3923.94]           [4569.45]           [5700.41]           [5991.32]           [6133.78]           [5595.24]           [6208.23]           [6542.93]           [7435.03]           [9071.33]          [10609.75]          [12097.78]          [13485.54]          [15465.86]          [17976.43]          [20395.52]          [22502.58]          [23497.49]          [24818.15]          [27330.03]          [25808.97]          [25091.67]          [25756.66]          [25230.22]          [24665.89]          [23977.02]          [24928.10]          [26649.75]          [28224.22]          [30594.02]          [31515.66]          [30697.34]          [32550.00]          [35142.49]          [36730.88]          [38403.78]          [40315.29]          [42431.89]          [43737.04]          [46193.61]
		//		, Fiji                            [285.54]            [287.33]            [291.54]            [296.77]            [310.87]            [317.07]            [316.18]            [333.31]            [334.65]            [357.46]            [422.41]            [466.04]            [583.35]            [768.74]            [987.98]           [1186.74]           [1182.18]           [1202.71]           [1360.87]           [1640.68]           [1893.05]           [1898.59]           [1786.92]           [1638.64]           [1681.97]           [1603.59]           [1795.61]           [1632.07]           [1535.41]           [1632.14]           [1834.99]           [1881.57]           [2057.41]           [2166.05]           [2383.92]           [2540.75]           [2714.25]           [2641.06]           [2070.17]           [2408.17]           [2076.01]           [2038.89]           [2259.06]           [2835.97]           [3332.92]           [3658.63]           [3749.94]           [4078.82]           [4177.66]           [3369.41]           [3651.97]           [4353.12]           [4546.74]           [4763.07]           [5061.40]           [4889.46]           [5197.51]           [5589.39]
		//		, Ghana                           [182.98]            [189.71]            [195.12]            [210.97]            [230.43]            [266.32]            [269.46]            [216.84]            [202.76]            [233.65]            [257.65]            [273.82]            [232.54]            [263.69]            [301.37]            [285.83]            [275.88]            [313.00]            [353.71]            [381.04]            [411.52]            [379.80]            [351.32]            [341.09]            [358.40]            [354.22]            [437.08]            [376.43]            [375.18]            [368.96]            [402.59]            [438.61]            [414.77]            [375.32]            [333.40]            [385.74]            [403.53]            [391.36]            [414.77]            [417.77]            [263.11]            [273.66]            [309.48]            [373.28]            [423.19]            [498.17]            [922.95]           [1090.69]           [1224.40]           [1086.77]           [1312.61]           [1574.98]           [1629.80]           [1814.49]           [1449.66]           [1353.68]           [1517.50]           [1641.49]
		//		, Belize                          [304.92]            [316.40]            [327.13]            [336.94]            [351.16]            [377.59]            [406.10]            [420.43]            [386.95]            [396.65]            [435.69]            [474.44]            [519.56]            [605.93]            [786.07]            [885.98]            [717.04]            [858.83]            [980.82]           [1074.27]           [1350.98]           [1307.21]           [1183.17]           [1212.92]           [1315.27]           [1268.18]           [1343.71]           [1586.45]           [1758.94]           [1979.35]           [2202.32]           [2326.85]           [2666.98]           [2833.06]           [2880.21]           [2996.38]           [3001.67]           [2952.60]           [2991.92]           [3065.49]           [3364.42]           [3419.28]           [3556.56]           [3679.91]           [3831.54]           [3933.33]           [4187.38]           [4324.88]           [4470.22]           [4258.79]           [4344.15]           [4517.14]           [4673.79]           [4685.25]           [4844.98]           [4950.26]           [4960.18]           [4905.51]
		//		, Korea, Rep.                     [158.24]             [93.82]            [106.13]            [146.29]            [123.59]            [108.70]            [133.45]            [161.12]            [198.37]            [243.33]            [279.13]            [300.76]            [323.60]            [405.88]            [561.57]            [615.20]            [830.70]           [1050.90]           [1398.48]           [1773.53]           [1704.47]           [1870.34]           [1977.64]           [2180.49]           [2390.67]           [2457.33]           [2803.37]           [3510.99]           [4686.14]           [5736.90]           [6516.31]           [7523.48]           [8001.54]           [8740.95]          [10205.81]          [12332.98]          [13137.91]          [12131.87]           [8085.32]          [10409.33]          [11947.58]          [11252.91]          [12782.53]          [14209.39]          [15907.67]          [18639.52]          [20888.38]          [23060.71]          [20430.64]          [18291.92]          [22086.95]          [24079.79]          [24358.78]          [25890.02]          [27811.37]          [27105.08]          [27608.25]          [29742.84]
		//		]
		//		7
		//
		//		What period are you interested in? Available years are from 1960 to 2017.
		//		Enter [starting year],[end year].
		//		2010,2017
		//		Ghana            [1312.61]              [1574.98]              [1629.80]              [1814.49]              [1449.66]              [1353.68]              [1517.50]              [1641.49]
		//
		//		Done with TestCountryList.



		//_________________OUTPUT for 4. School Enrollment:_________________
		//		What is the index of the country you want data on? (Enter a index between 0 and 9)
		//		Available countries are:
		//		[Seychelles                    [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 44.91]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 78.91]        [N/A, 82.24]        [N/A, 82.16]        [N/A, 83.61]          [N/A, N/A]        [N/A, 89.68]        [N/A, 66.52]        [N/A, 65.30]        [N/A, 71.17]        [N/A, 70.55]        [N/A, 74.80]        [N/A, 74.77]        [N/A, 81.56]          [N/A, N/A]        [N/A, 79.03]        [N/A, 76.29]        [N/A, 76.16]        [N/A, 74.64]        [N/A, 44.32]          [N/A, N/A]        [N/A, 79.51]        [N/A, 82.43]        [N/A, 88.58]          [N/A, N/A]          [N/A, N/A]
		//		, Ireland                       [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 62.92]        [N/A, 64.71]        [N/A, 68.90]        [N/A, 71.78]        [N/A, 73.79]        [N/A, 74.77]        [N/A, 75.34]        [N/A, 75.88]          [N/A, N/A]        [N/A, 78.14]          [N/A, N/A]        [N/A, 82.55]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 75.91]        [N/A, 77.48]          [N/A, N/A]          [N/A, N/A]        [N/A, 81.18]        [N/A, 81.04]        [N/A, 80.33]        [N/A, 86.85]        [N/A, 85.62]        [N/A, 85.34]        [N/A, 84.92]        [N/A, 86.79]        [N/A, 88.86]        [N/A, 91.53]        [N/A, 93.24]        [N/A, 94.56]        [N/A, 96.17]        [N/A, 97.44]        [N/A, 98.92]        [N/A, 99.33]          [N/A, N/A]        [N/A, 99.76]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, IDA & IBRD total              [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 49.50]        [N/A, 49.81]        [N/A, 50.84]        [N/A, 51.54]        [N/A, 52.16]        [N/A, 53.00]        [N/A, 53.90]        [N/A, 54.62]        [N/A, 55.21]        [N/A, 56.26]        [N/A, 57.07]        [N/A, 57.40]        [N/A, 58.75]        [N/A, 59.92]        [N/A, 60.55]        [N/A, 61.42]        [N/A, 62.32]        [N/A, 62.56]        [N/A, 62.39]          [N/A, N/A]
		//		, Montenegro                    [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 90.04]          [N/A, N/A]
		//		, Syrian Arab Republic          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 26.20]          [N/A, N/A]        [N/A, 35.45]        [N/A, 35.64]        [N/A, 36.10]        [N/A, 38.51]        [N/A, 40.64]        [N/A, 41.45]        [N/A, 42.96]        [N/A, 42.39]        [N/A, 40.03]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 38.14]        [N/A, 37.54]        [N/A, 37.48]        [N/A, 38.52]        [N/A, 38.50]        [N/A, 39.33]        [N/A, 40.97]        [N/A, 41.03]        [N/A, 57.40]        [N/A, 60.89]        [N/A, 64.00]        [N/A, 63.83]        [N/A, 65.49]        [N/A, 66.18]        [N/A, 66.30]        [N/A, 66.94]        [N/A, 68.81]        [N/A, 70.62]        [N/A, 45.52]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, Belize                        [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 55.27]        [N/A, 58.58]        [N/A, 60.15]          [N/A, N/A]        [N/A, 68.08]          [N/A, N/A]        [N/A, 65.05]        [N/A, 64.78]        [N/A, 64.22]        [N/A, 63.42]        [N/A, 63.75]        [N/A, 63.69]        [N/A, 65.41]        [N/A, 67.51]        [N/A, 69.21]        [N/A, 69.33]        [N/A, 69.52]        [N/A, 72.22]          [N/A, N/A]
		//		, West Bank and Gaza            [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 73.20]        [N/A, 74.65]        [N/A, 75.40]        [N/A, 78.09]        [N/A, 80.88]        [N/A, 84.54]        [N/A, 85.64]        [N/A, 86.72]        [N/A, 86.53]        [N/A, 86.39]        [N/A, 85.10]        [N/A, 83.19]        [N/A, 81.05]        [N/A, 81.00]        [N/A, 80.47]        [N/A, 80.29]        [N/A, 81.17]        [N/A, 82.09]          [N/A, N/A]
		//		, Comoros                       [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 44.42]        [N/A, 44.77]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, St. Kitts and Nevis           [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		, Kenya                         [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]        [N/A, 33.40]          [N/A, N/A]        [N/A, 34.76]        [N/A, 36.33]        [N/A, 39.10]        [N/A, 40.85]        [N/A, 41.80]        [N/A, 43.95]        [N/A, 48.67]        [N/A, 48.33]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]          [N/A, N/A]
		//		]
		//		7
		//
		//		What period are you interested in? Available years are from 1960 to 2017.
		//		Enter [starting year],[end year].
		//		2010,2017
		//		Comoros [N/A, N/A]   [N/A, N/A]   [N/A, N/A]   [N/A, 44.42]   [N/A, 44.77]   [N/A, N/A]   [N/A, N/A]   [N/A, N/A]
		//
		//		Done with TestCountryList.

		System.out.println("\nDone with TestCountryList.\n");
	}
}
