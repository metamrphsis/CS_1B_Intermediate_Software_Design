package csv;

import java.io.FileNotFoundException;

/**
 *  Tests the CSVParser class, which reads input from a CSV file. Uses
 *  the attributes stored in CSVParser object to fill the EnrollmentData class.
 *
 * @author Foothill College, Bita Mazloom, Selahittin Saytas
 */
public class TestParser
{
	/**
	 * Prints out the contents of a double array
	 * @param requestedEnrollmentPeriod   one-dimensional array of enrollment data.
	 */
	private static void displayEnrollmentPeriod(String countryName, int startigYearLabel, double[] requestedEnrollmentPeriod) 
	{
		System.out.printf("%20s ","Country Name");
		System.out.printf("\n%20s ", countryName);
		for(int i = 0; i < requestedEnrollmentPeriod.length; i++)
		{
			if (requestedEnrollmentPeriod[i] == 0)
				System.out.printf("%6d   ", 0);
			else if (requestedEnrollmentPeriod[i] == -1)
				System.out.printf("     ," + "   ");
			else
				System.out.printf("%6.2f   ", requestedEnrollmentPeriod[i]);
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
		final String FILENAME = "resources/childrenEnrolledInPrimary_short_12years.csv";
        //final String FILENAME = "resources/childrenEnrolledInSecondary_short_12years.csv";
		//final String FILENAME = "resources/childrenEnrolled_invalid.csv";

		CSVParser parser;
		try 
		{
			parser = new CSVParser(FILENAME);
			// Given the childrenEnrolledInPrimary_short_12years.csv file, the output is:
			// 			School Enrollment In Primary (% net) updated at 7/25/18

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

		String [] countryNames = parser.getCountryNames();
		int [] yearLabels = parser.getYearLabels();
		double [][] parsedTable = parser.getParsedTable();		

		// Reuse your implementation of EnrollmentData class to stores the 
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
		for(int countryIndex = 0; countryIndex < countryNames.length; countryIndex++)
		{
			double [] countryData = parsedTable[countryIndex];
			datatable.addCountry(countryNames[countryIndex], countryData);					
		}

		// Display the string representation of the data table.
		System.out.println(datatable.toString());
		// Given the childrenEnrolledInPrimary_short_12years.csv file, the output is:
        //		School Enrollment In Primary (% net) updated at 7/25/18
        //        Country Name     2006     2007     2008     2009     2010     2011     2012     2013     2014     2015     2016     2017   
        //         Afghanistan                                                                                                               
        //              Canada                                                          99.47    99.45    99.40    99.60    99.96            
        //               China                                                                                                               
        //    Egypt, Arab Rep.    90.28    95.34             95.98    97.16    93.18    96.87             95.83             97.45            
        //               India             91.30    91.02    90.77    91.04    90.42    91.58    92.25                                       
        //               Nepal                                                 98.26    99.39             94.13    96.53    96.62    94.70   
        //Syrian Arab Republic    89.91    91.00    91.74    92.78    92.94                      63.24                                       
        //       United States    93.10    94.49    95.12    93.86    92.72    91.91    92.06    91.74    92.20    92.94                     

		double [] requestedEnrollmentPeriod;
		String countryName;

		try
		{
			countryName = countryNames[1];
			int requestedStartYear = 2006;
			int requestedEndYear = 2014;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = datatable.getEnrollmentInCountryForPeriod(countryName,requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedStartYear,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}
		// Given the childrenEnrolledInPrimary_short_12years.csv file, the output is:
		// 		Requested enrollment period (2006 to 2014) for Canada:
		//         Country Name  
		//               Canada      ,        ,        ,        ,        ,        ,    99.47    99.45    99.40   
 

		try
		{
			countryName = countryNames[2];
			int requestedStartYear = 1950;
			int requestedEndYear = 2000;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = datatable.getEnrollmentInCountryForPeriod(countryName,requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedStartYear,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}
		// Given the childrenEnrolledInPrimary_short_12years.csv file, the output is:
		// 		Requested enrollment period (1950 to 2000) for China:
		// 			Invalid request of start and end year 1950, 2000. Valid period for China is 2006 to 2017

		try
		{
			countryName = countryNames[4];
			int requestedStartYear = 2000;
			int requestedEndYear = 2030;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = datatable.getEnrollmentInCountryForPeriod(countryName,requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedStartYear,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}
		// Given the childrenEnrolledInPrimary_short_12years.csv file, the output is: 
		//		Requested enrollment period (2000 to 2030) for India:
		//			Invalid request of start and end year 2000, 2030. Using valid subperiod for India is 2006 to 2017
		//			        Country Name 
		//			               India      ,    91.30    91.02    90.77    91.04    90.42    91.58    92.25        ,        ,        ,        ,



        // New countries
        try
        {
            countryName = countryNames[3];
            int requestedStartYear = 2006;
            int requestedEndYear = 2014;
            System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
            requestedEnrollmentPeriod = datatable.getEnrollmentInCountryForPeriod(countryName,requestedStartYear,requestedEndYear);
            displayEnrollmentPeriod(countryName,requestedStartYear,requestedEnrollmentPeriod);
        }
        catch (IllegalArgumentException exc)
        {
            System.out.println(exc.getMessage());
        }
//        Requested enrollment period (2006 to 2014) for Egypt, Arab Rep.:
//          Country Name
//              Egypt, Arab Rep.  90.28    95.34        ,    95.98    97.16    93.18    96.87        ,    95.83

        try
        {
            countryName = countryNames[5];
            int requestedStartYear = 1950;
            int requestedEndYear = 2000;
            System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
            requestedEnrollmentPeriod = datatable.getEnrollmentInCountryForPeriod(countryName,requestedStartYear,requestedEndYear);
            displayEnrollmentPeriod(countryName,requestedStartYear,requestedEnrollmentPeriod);
        }
        catch (IllegalArgumentException exc)
        {
            System.out.println(exc.getMessage());
        }
//        Requested enrollment period (1950 to 2000) for Nepal:
//          Invalid request of start and end year 1950, 2000. Valid period for Nepal is 2006 to 2017

        try
        {
            countryName = countryNames[7];
            int requestedStartYear = 2000;
            int requestedEndYear = 2030;
            System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
            requestedEnrollmentPeriod = datatable.getEnrollmentInCountryForPeriod(countryName,requestedStartYear,requestedEndYear);
            displayEnrollmentPeriod(countryName,requestedStartYear,requestedEnrollmentPeriod);
        }
        catch (IllegalArgumentException exc)
        {
            System.out.println(exc.getMessage());
        }
//        Requested enrollment period (2000 to 2030) for United States:
//          Invalid request of start and end year 2000, 2030. Using valid subperiod for United States is 2006 to 2017
//              Country Name
//                  United States  93.10    94.49    95.12    93.86    92.72    91.91    92.06    91.74    92.20    92.94        ,        ,

        System.out.println("\nDone with TestParser.\n");
	}
}
