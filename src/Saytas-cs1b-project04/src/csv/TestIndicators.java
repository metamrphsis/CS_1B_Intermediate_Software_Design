package csv;

import java.io.FileNotFoundException;

/**
 *  Tests the CSVParser class, which reads input from a CSV file. Uses
 *  the attributes stored in CSVParser object to fill the EnrollmentData class.
 *
 * @author Foothill College, Bita Mazloom, Selahittin Saytaş
 */
public class TestIndicators
{	
	/**
	 * Prints out the contents of a double array
     * @param countryName   Name of the country
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
	 * Creates an object of type CSVParser which parses a CSV file. If
	 * the format of the CSVParser is valid, adds each parsed line to 
	 * an instance of the EnrollmentData class.
	 * Creates an object of type EnrollmentData to hold the data for 
	 * data of individual countries read from the file.
	 * Tests the EnrollmentData class using with various country names 
	 * and date ranges. For each test case, either prints that data for
	 * the overlapping years found or catches an IllegalArgumentExceptions.
	 */
	public static void main(String[] args) 
	{
		// NOTE: Make sure to use relative path instead of specifying the entire path
		//       such as (/Users/alicew/myworkspace/so_on_and_so_forth).
		
		// Examples of input files
		//final String FILENAME = "resources/childrenEnrolledInPrimary.csv";
		//final String FILENAME = "resources/childrenEnrolledInSecondary.csv";
		//final String FILENAME = "resources/childrenEnrolledInPrimary_short_12years.csv";
        //final String FILENAME = "resources/childrenEnrolledInSecondary_short_12years.csv";
		//final String FILENAME = "resources/childrenEnrolled_invalid.csv";
		// Example of input file for GDP per capita:
		final String FILENAME = "resources/gdpPerCapita_short_12years.csv";
		//final String FILENAME = "resources/gdpPerCapita.csv";

		CSVParser parser;
		try 
		{
			parser = new CSVParser(FILENAME);
			// Given the childrenEnrolledInPrimary_short_12years.csv file, the output is:
			// 			School Enrollment In Primary (% net) updated at 7/25/18
	        //
			// Given the gdpPerCapita_short_12years.csv file, the output is:
			// 			GDP per capita (current US$) updated at 8/28/18
		} 
		catch (FileNotFoundException e) 
		{
			System.err.printf("File name %s not found.", FILENAME);
			return;
		}
		catch (InvalidFileFormatException e)
		{
			System.err.printf("Invalid file format %s\n", e.getMessage());
			return;
		}

		IndicatorType indicatorType = parser.getIndicatorType();

		String [] countryNames = parser.getCountryNames();
		int [] yearLabels = parser.getYearLabels();
		double [][] parsedTable = parser.getParsedTable();		

		// Reuse your implementation of EnrollmentData class to store the
		// 2D array of data for all countries.
		EnrollmentData datatable;
		
		// The number of rows in our 2D array should be the same of the number of countries
		// we have data on.
		int numRows = parsedTable.length;
		
		// The number of years is equal to the number of data
		int numColumns = parser.getNumberOfYears();
		
		// The first year read from the input file.
		int startingYear = yearLabels[0];

		// Reuse your implementation of EnrollmentData class to initialize an 
		// instance which will hold a 2D array. The row represents a country 
		// and the column represent the data for a year. 
		datatable = new EnrollmentData(numRows, numColumns, startingYear);

		// From the array that stores parsed information,
		// add one country at a time to an object of type EnrollmentData.
		for (int countryIndex = 0; countryIndex < countryNames.length; countryIndex++)
		{
			// Get the parsed array of doubles for the current Country object
			double [] dataForAllYears = parsedTable[countryIndex];
			Indicator [] indicatorsForOneCountry = new Indicator[yearLabels.length];
			
			// Go through each year of data read from the CSV file.
			for (int yearIndex = 0; yearIndex < dataForAllYears.length; yearIndex++)
			{
				int year = yearLabels[yearIndex];

				Indicator dataForOneYear = null;

				switch(indicatorType)
				{
					case GDP_PER_CAPITA:
						dataForOneYear = new GDPIndicator(yearLabels[yearIndex]);
						// The overridden method of the abstract class requires one dimensional array
						// Note: Curly braces specify that we are creating and initializing an array in place.
						double data[] = {dataForAllYears[yearIndex]};
						// Uses the overridden method to set the data
						((GDPIndicator)dataForOneYear).setData(data);
						break;
					case SCHOOL_ENROLLMENT_PRIMARY:
						dataForOneYear = new SchoolEnrollmentIndicator(yearLabels[yearIndex]);
						((SchoolEnrollmentIndicator)dataForOneYear).setPrimaryEnrollment(dataForAllYears[yearIndex]);
						break;
					// Note: I added this case in order to display the childrenEnrolledInSecondary data
					case SCHOOL_ENROLLMENT_SECONDARY:
						dataForOneYear = new SchoolEnrollmentIndicator(yearLabels[yearIndex]);
						((SchoolEnrollmentIndicator)dataForOneYear).setPrimaryEnrollment(dataForAllYears[yearIndex]);
						break;
					default:
						break;
				}
				indicatorsForOneCountry[yearIndex] = dataForOneYear;
			}
			datatable.addCountry(countryNames[countryIndex], indicatorsForOneCountry);					
		}

		// Display the string representation of the data table.
		System.out.println(datatable.toString());
		// Given the childrenEnrolledInPrimary_short_12years.csv file, the output is:
		//         Country Name           2006           2007           2008           2009           2010           2011           2012           2013           2014           2015           2016           2017   
		//         Afghanistan                ()             ()             ()             ()             ()             ()             ()             ()             ()             ()             ()             ()
		//              Canada                ()             ()             ()             ()             ()             ()        (99.47)        (99.45)        (99.40)        (99.60)        (99.96)             ()
		//               China                ()             ()             ()             ()             ()             ()             ()             ()             ()             ()             ()             ()
		//    Egypt, Arab Rep.           (90.28)        (95.34)             ()        (95.98)        (97.16)        (93.18)        (96.87)             ()        (95.83)             ()        (97.45)             ()
		//               India                ()        (91.30)        (91.02)        (90.77)        (91.04)        (90.42)        (91.58)        (92.25)             ()             ()             ()             ()
		//               Nepal                ()             ()             ()             ()             ()        (98.26)        (99.39)             ()        (94.13)        (96.53)        (96.62)        (94.70)
		// Syrian Arab Republic           (89.91)        (91.00)        (91.74)        (92.78)        (92.94)             ()             ()        (63.24)             ()             ()             ()             ()
		//       United States           (93.10)        (94.49)        (95.12)        (93.86)        (92.72)        (91.91)        (92.06)        (91.74)        (92.20)        (92.94)             ()             ()
        //
		// Given the gdpPerCapita_short_12years.csv file, the output is:
		//         Country Name           2006           2007           2008           2009           2010           2011           2012           2013           2014           2015           2016           2017   
		//         Argentina         (5878.76)      (7193.62)      (8953.36)      (8161.31)     (10276.26)     (12726.91)     (12969.71)     (12976.64)     (12245.26)     (13698.29)     (12654.36)     (14401.97)
		//            Canada        (40386.70)     (44544.53)     (46596.34)     (40773.45)     (47447.48)     (52082.21)     (52496.69)     (52418.32)     (50633.21)     (43525.37)     (42348.95)     (45032.12)
		//             China         (2099.23)      (2695.37)      (3471.25)      (3838.43)      (4560.51)      (5633.80)      (6337.88)      (7077.77)      (7683.50)      (8069.21)      (8117.27)      (8826.99)
		//  Egypt, Arab Rep.         (1375.20)      (1640.48)      (2011.25)      (2291.67)      (2602.48)      (2747.48)      (3181.44)      (3213.39)      (3327.75)      (3547.71)      (3479.28)      (2412.73)
		//             India          (792.03)      (1018.17)       (991.48)      (1090.32)      (1345.77)      (1461.67)      (1446.99)      (1452.20)      (1576.00)      (1606.04)      (1717.47)      (1939.61)
		//             Nepal          (348.63)       (393.88)       (473.84)       (480.72)       (592.18)       (692.12)       (681.79)       (688.62)       (706.24)       (747.16)       (729.12)       (835.08)
		// Syrian Arab Republic         (1762.25)      (2058.04)             ()             ()             ()             ()             ()             ()             ()             ()             ()             ()
		//     United States        (46437.07)     (48061.54)     (48401.43)     (47001.56)     (48375.41)     (49793.71)     (51450.96)     (52782.09)     (54696.73)     (56443.82)     (57588.54)     (59531.66)

		Indicator [] requestedEnrollmentPeriod;
		String countryName;

		try
		{
			countryName = countryNames[1];
			int requestedStartYear = 2006;
			int requestedEndYear = 2014;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = datatable.getEnrollmentInCountryForPeriod(countryName,requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}
		// Given the childrenEnrolledInPrimary_short_12years.csv file, the output is:
		// 		Requested enrollment period (2006 to 2014) for Canada:
		//        Canada ()   ()   ()   ()   ()   ()   (99.47)   (99.45)   (99.40)   
        //
		// Given the gdpPerCapita_short_12years.csv file, the output is:
		// 		Requested enrollment period (2006 to 2014) for Canada:
		// 	      Canada (40386.70)   (44544.53)   (46596.34)   (40773.45)   (47447.48)   (52082.21)   (52496.69)   (52418.32)   (50633.21)   
		
		try
		{
			countryName = countryNames[2];
			int requestedStartYear = 1950;
			int requestedEndYear = 2000;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = datatable.getEnrollmentInCountryForPeriod(countryName,requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}
		// Given the childrenEnrolledInPrimary_short_12years.csv file, the output is:
		// 		Requested enrollment period (1950 to 2000) for China:
		// 			Invalid request of start and end year 1950, 2000. Valid period for China is 2006 to 2017
        //
		// Given the gdpPerCapita_short_12years.csv file, the output is:
		// 		Requested enrollment period (1950 to 2000) for China:
		// 			Invalid request of start and end year 1950, 2000. Valid period for China is 2006 to 2017
			
		try
		{
			countryName = countryNames[4];
			int requestedStartYear = 2000;
			int requestedEndYear = 2030;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = datatable.getEnrollmentInCountryForPeriod(countryName,requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}
		// Given the childrenEnrolledInPrimary_short_12years.csv file, the output is: 
		// 		Requested enrollment period (2000 to 2030) for India:
		// 			Invalid request of start and end year 2000, 2030. Using valid subperiod for India is 2006 to 2017
		// 			        Country Name 
		// 			               India ()   (91.30)   (91.02)   (90.77)   (91.04)   (90.42)   (91.58)   (92.25)   ()   ()   ()   ()   
        //
		// Given the gdpPerCapita_short_12years.csv file, the output is:
		// 		Requested enrollment period (2000 to 2030) for India:
		// 			Invalid request of start and end year 2000, 2030. Using valid subperiod for India is 2006 to 2017
		// 				India   (792.03)    (1018.17)     (991.48)    (1090.32)    (1345.77)    (1461.67)    (1446.99)    (1452.20)    (1576.00)    (1606.04)






		//_________________Test Cases (My contribution)_________________
		try
		{
			countryName = countryNames[3];
			int requestedStartYear = 2006;
			int requestedEndYear = 2014;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = datatable.getEnrollmentInCountryForPeriod(countryName,requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}
		// Given the childrenEnrolledInPrimary_short_12years.csv file, the output is:
		// 		Requested enrollment period (2006 to 2014) for Egypt, Arab Rep.:
		// 			Egypt, Arab Rep.    (90.28)      (95.34)           ()      (95.98)      (97.16)      (93.18)      (96.87)           ()      (95.83)
		//
		// Given the gdpPerCapita_short_12years.csv file, the output is:
		// 		Requested enrollment period (2006 to 2014) for Egypt, Arab Rep.:
		// 			Egypt, Arab Rep.  (1375.20)    (1640.48)    (2011.25)    (2291.67)    (2602.48)    (2747.48)    (3181.44)    (3213.39)    (3327.75)

		try
		{
			countryName = countryNames[5];
			int requestedStartYear = 1950;
			int requestedEndYear = 2000;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = datatable.getEnrollmentInCountryForPeriod(countryName,requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}
		// Given the childrenEnrolledInPrimary_short_12years.csv file, the output is:
		// 		Requested enrollment period (1950 to 2000) for Nepal:
		// 			Invalid request of start and end year 1950, 2000. Valid period for Nepal is 2006 to 2017
		//
		// Given the gdpPerCapita_short_12years.csv file, the output is:
		// 		Requested enrollment period (1950 to 2000) for Nepal:
		// 			Invalid request of start and end year 1950, 2000. Valid period for Nepal is 2006 to 2017


		try
		{
			countryName = countryNames[7];
			int requestedStartYear = 2000;
			int requestedEndYear = 2030;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = datatable.getEnrollmentInCountryForPeriod(countryName,requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}
		// Given the childrenEnrolledInPrimary_short_12years.csv file, the output is:
		// 		Requested enrollment period (2000 to 2030) for United States:
		// 			Invalid request of start and end year 2000, 2030. Using valid subperiod for United States is 2006 to 2017
		// 				United States    (93.10)      (94.49)      (95.12)      (93.86)      (92.72)      (91.91)      (92.06)      (91.74)      (92.20)      (92.94)           ()           ()
		//
		// Given the gdpPerCapita_short_12years.csv file, the output is:
		// 		Requested enrollment period (2000 to 2030) for United States:
		// 			Invalid request of start and end year 2000, 2030. Using valid subperiod for United States is 2006 to 2017
		// 				United States (46437.07)   (48061.54)   (48401.43)   (47001.56)   (48375.41)   (49793.71)   (51450.96)   (52782.09)   (54696.73)   (56443.82)   (57588.54)   (59531.66)


		System.out.println("\nDone with TestIndicators.\n");
	}
}
