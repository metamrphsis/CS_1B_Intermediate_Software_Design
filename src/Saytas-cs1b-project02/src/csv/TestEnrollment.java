package csv;

/**
 *  Tests the CSVParser class, which reads input from a CSV file. Uses
 *  the attributes stored in CSVParser object to fill the EnrollmentData class.
 *
 * @author Foothill College, Bita Mazloom, Selahittin Saytas
 */
public class TestEnrollment
{
	// Indicates that the data is invalid.
	public final static int MISSING_DATA = -1;

	/**
	 * Prints out the contents of a double array
	 * @param requestedEnrollmentPeriod   one-dimensional array of enrollment data.
	 */
	private static void displayEnrollmentPeriod(String countryName, int startingYearLabel, double[] requestedEnrollmentPeriod)
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
	 * Uses a CSVParser to parse a CSV file.
	 * Adds each parsed line to an instance of the EnrollmentData class.
	 */
	public static void main(String[] args)
	{
		// Part 1: Testing with partial data for a country --------------------
		System.out.println("Testing with childrenEnrolledInPrimary_short_12years.csv file:");

		// Note: Currently we are replacing invalid
		final String [] countryNames = {"Canada", "India", "United States"};
		final double [] partialPrimaryEnrollment01 = {MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,99.47058868,99.44992065,99.4045105,99.59809875,99.96224213};
		final double [] partialPrimaryEnrollment02 = {MISSING_DATA,91.30478668,91.02262878,90.76931763,91.03694916,90.4213562,91.58106232,92.25010681,MISSING_DATA,MISSING_DATA,MISSING_DATA};
		final double [] partialPrimaryEnrollment03 = {93.09568024,94.49015808,95.11688995,93.85852814,92.71695709,91.9122467,92.05748749,91.74311829,92.19660187,92.94197845,MISSING_DATA};

		// EnrollmentData class is to store information
		// about all countries via multiple array objects, including
		// a 2D array of doubles.
		EnrollmentData datatable;

		// hard-coded based on input file
		int startingYear = 2006;
		// the number of rows in our 2D array should be the same of the number of countries
		// we have data on.
		int numRows = countryNames.length;
		// the number of years is equal to the number of data
		int numColumns = partialPrimaryEnrollment01.length;

		// columns, and starting year.
		datatable = new EnrollmentData(numRows, numColumns, startingYear);

		datatable.addCountry(countryNames[0], partialPrimaryEnrollment01);
		datatable.addCountry(countryNames[1], partialPrimaryEnrollment02);
		datatable.addCountry(countryNames[2], partialPrimaryEnrollment03);

		// string representation of the data table. This string will contain
		// newlines and be terminated with one.
		System.out.println(datatable.toString());
		// the output is:
		/*countryTracker
        Country Name     2006     2007     2008     2009     2010     2011     2012     2013     2014     2015     2016
              Canada                                                          99.47    99.45    99.40    99.60    99.96
               India             91.30    91.02    90.77    91.04    90.42    91.58    92.25
       United States    93.10    94.49    95.12    93.86    92.72    91.91    92.06    91.74    92.20    92.94
		*/

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
		/*
		Requested enrollment period (2006 to 2014) for India:
	        Country Name
	               India      ,    91.30    91.02    90.77    91.04    90.42    91.58    92.25        ,
		*/

		try
		{
			countryName = countryNames[2];
			int requestedStartYear = 1960;
			int requestedEndYear = 2000;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = datatable.getEnrollmentInCountryForPeriod(countryName,requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedStartYear,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}
		/*
		Requested enrollment period (1960 to 2000) for United States:
		Invalid request of start and end year 1960, 2000. Valid period for United States is 2006 to 2016
		*/

		try
		{
			countryName = countryNames[0];
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
		/*
		Requested enrollment period (2000 to 2030) for Canada:
		Invalid request of start and end year 2000, 2030. Using valid subperiod for Canada is 2006 to 2016
        Country Name
              Canada      ,        ,        ,        ,        ,        ,    99.47    99.45    99.40    99.60    99.96
		 */

		// Part 2: Testing with all data for a country --------------------------------------------
		System.out.println("\n\nTesting with childrenEnrolledInPrimary.csv file:");
		final double [] primaryEnrollmentCountry01 = {MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				95.56710815,95.29425812,94.72662354,94.57524109,94.88964081,94.95413208,94.94940948,95.18415833,95.18572998,
				97.73861694,97.48632813,95.86266327,95.30892181,94.18283081,95.11089325,MISSING_DATA,98.43271637,99.70514679,
				99.91953278,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,MISSING_DATA,99.47058868,99.44992065,99.4045105,99.59809875,99.96224213,MISSING_DATA};
		final double [] primaryEnrollmentCountry02 = {MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,61.35797119,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				77.50028992,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,79.80341339,79.65550232,79.25736237,83.82756805,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,91.30478668,91.02262878,90.76931763,91.03694916,90.4213562,91.58106232,92.25010681,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,MISSING_DATA};
		final double [] primaryEnrollmentCountry03 = {MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,81.58202362,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,93.18254089,95.09078979,MISSING_DATA,MISSING_DATA,
				98.64874268,98.65077209,MISSING_DATA,96.71166229,96.07853699,97.03784943,95.92057037,MISSING_DATA,MISSING_DATA,
				94.83860016,94.74723053,94.76270294,92.86582947,92.60085297,91.33039093,91.78575134,93.09568024,94.49015808,
				95.11688995,93.85852814,92.71695709,91.9122467,92.05748749,91.74311829,92.19660187,92.94197845,MISSING_DATA,MISSING_DATA};

		// hard-coded based on input file
		startingYear = 1960;
		// Note: Below statement is redundant as the number of country names has not changed from part 1
		numRows = countryNames.length;
		// the number of years is equal to the number of data
		numColumns = primaryEnrollmentCountry01.length;

		// Reinitializes the reference to a new object of type EnrollmentData
		EnrollmentData datatablem = new EnrollmentData(numRows, numColumns, startingYear);

		// Add a country and its associated data to the table.
		datatablem.addCountry(countryNames[0], primaryEnrollmentCountry01);
		datatablem.addCountry(countryNames[1], primaryEnrollmentCountry02);
		datatablem.addCountry(countryNames[2], primaryEnrollmentCountry03);

		// Implicitly calls toString() method
		System.out.println(datatablem);
		// the output is:
		/*
        Country Name     1960     1961     1962     1963     1964     1965     1966     1967     1968     1969     1970     1971     1972     1973     1974     1975     1976     1977     1978     1979     1980     1981     1982     1983     1984     1985     1986     1987     1988     1989     1990     1991     1992     1993     1994     1995     1996     1997     1998     1999     2000     2001     2002     2003     2004     2005     2006     2007     2008     2009     2010     2011     2012     2013     2014     2015     2016     2017
              Canada                                                                                                                                                                                                          95.57    95.29    94.73    94.58    94.89    94.95    94.95    95.18    95.19    97.74    97.49    95.86    95.31    94.18    95.11             98.43    99.71    99.92                                                                                                       99.47    99.45    99.40    99.60    99.96
               India                                                                                                       61.36                                                                                                                                                                      77.50                                                                                     79.80    79.66    79.26    83.83                               91.30    91.02    90.77    91.04    90.42    91.58    92.25
       United States                                                                                                                                           81.58                                                                                              93.18    95.09                      98.65    98.65             96.71    96.08    97.04    95.92                      94.84    94.75    94.76    92.87    92.60    91.33    91.79    93.10    94.49    95.12    93.86    92.72    91.91    92.06    91.74    92.20    92.94
		*/

		try
		{
			countryName = countryNames[1];
			int requestedStartYear = 2006;
			int requestedEndYear = 2014;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = datatablem.getEnrollmentInCountryForPeriod(countryName,requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedStartYear,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}
		/*
		Requested enrollment period (2006 to 2014) for India:
		        Country Name
		               India      ,    91.30    91.02    90.77    91.04    90.42    91.58    92.25
		*/

		try
		{
			countryName = countryNames[2];
			int requestedStartYear = 1960;
			int requestedEndYear = 2000;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = datatablem.getEnrollmentInCountryForPeriod(countryName,requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedStartYear,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}
		/*
		Requested enrollment period (1960 to 2000) for United States:
		        Country Name
		       United States      ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,    81.58        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,    93.18    95.09        ,        ,    98.65    98.65        ,    96.71    96.08    97.04    95.92        ,        ,    94.84    94.75
		*/

		try
		{
			countryName = countryNames[0];
			int requestedStartYear = 2000;
			int requestedEndYear = 2030;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = datatablem.getEnrollmentInCountryForPeriod(countryName,requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedStartYear,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}
		/*
		Requested enrollment period (2000 to 2030) for Canada:
		Invalid request of start and end year 2000, 2030. Using valid subperiod for Canada is 2000 to 2017.
 		       Country Name
 		             Canada  99.92        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,    99.47    99.45    99.40    99.60    99.96        ,
		 */



		System.out.println("\n");
		// ------------- MY CONTRIBUTION -------------
		// PART 3: Testing with partial data for a country --------------------
		System.out.println("Testing with childrenEnrolledInSecondary_short_12years.csv file:");

		final double [] partialSecondaryEnrollment01 = {MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,92.30924988,91.76743317,91.35083771,92.85788727,99.65135193};
		final double [] partialSecondaryEnrollment02 = {MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,61.76811981,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA};
		final double [] partialSecondaryEnrollment03 = {88.28988647,88.39064789,88.55860138,87.94101715,86.69602203,87.63204193,87.32827759,88.12046814,88.90480042,90.83441925,MISSING_DATA,MISSING_DATA};

		EnrollmentData dataTable01;
		startingYear = 2006;
		numRows = countryNames.length;
		numColumns = partialSecondaryEnrollment01.length;
		dataTable01 = new EnrollmentData(numRows, numColumns, startingYear);

		dataTable01.addCountry(countryNames[0], partialSecondaryEnrollment01);
		dataTable01.addCountry(countryNames[1], partialSecondaryEnrollment02);
		dataTable01.addCountry(countryNames[2], partialSecondaryEnrollment03);

		System.out.println(dataTable01.toString());
		// The output is:
		/*countryTracker
		Country Name       2006       2007       2008       2009       2010       2011       2012       2013       2014       2015       2016       2017
              Canada                                                                                   92.31      91.77      91.35      92.86      99.65
               India                                                                                   61.77
       United States      88.29      88.39      88.56      87.94      86.70      87.63      87.33      88.12      88.90      90.83
		*/

		try
		{
			countryName = countryNames[1];
			int requestedStartYear = 2006;
			int requestedEndYear = 2014;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = dataTable01.getEnrollmentInCountryForPeriod(countryName,requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedStartYear,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}
		/*
		Requested enrollment period (2006 to 2014) for India:
        Country Name
               India      ,        ,        ,        ,        ,        ,        ,    61.77        ,
		*/

		try
		{
			countryName = countryNames[2];
			int requestedStartYear = 1960;
			int requestedEndYear = 2000;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = dataTable01.getEnrollmentInCountryForPeriod(countryName,requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedStartYear,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}
		/*
		Requested enrollment period (1960 to 2000) for United States:
		Invalid request of start and end year 1960, 2000. Valid period for United States is 2006 to 2017
		*/

		try
		{
			countryName = countryNames[0];
			int requestedStartYear = 2000;
			int requestedEndYear = 2030;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = dataTable01.getEnrollmentInCountryForPeriod(countryName,requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedStartYear,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}
		/*
		Requested enrollment period (2000 to 2030) for Canada:
		Invalid request of start and end year 2000, 2030. Using valid subperiod for Canada is 2006 to 2017
        Country Name
              Canada      ,        ,        ,        ,        ,        ,        ,    92.31    91.77    91.35    92.86    99.65
		 */


		// PART 4: Testing with all data for a country --------------------------------------------
		System.out.println("\n\nTesting with childrenEnrolledInSecondary.csv file:");
		final double [] secondaryEnrollmentCountry01 = {MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,MISSING_DATA,90.69728088,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,92.30924988,91.76743317,91.35083771,92.85788727,99.65135193};
		final double [] secondaryEnrollmentCountry02 = {MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				61.76811981,MISSING_DATA,MISSING_DATA,MISSING_DATA};
		final double [] secondaryEnrollmentCountry03 = {MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,88.41416168,MISSING_DATA,
				MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,MISSING_DATA,88.24775696,89.43807983,89.66252899,88.93643951,
				MISSING_DATA,MISSING_DATA,MISSING_DATA,87.00357819,86.88871765,87.39705658,85.5687027,88.69991302,89.30381775,
				88.80712891,88.28988647,88.39064789,88.55860138,87.94101715,86.69602203,87.63204193,87.32827759,88.12046814,88.90480042,90.83441925,
				MISSING_DATA};

		startingYear = 1960;
		numRows = countryNames.length;
		numColumns = secondaryEnrollmentCountry01.length;
		EnrollmentData dataTable02 = new EnrollmentData(numRows, numColumns, startingYear);

		dataTable02.addCountry(countryNames[0], secondaryEnrollmentCountry01);
		dataTable02.addCountry(countryNames[1], secondaryEnrollmentCountry02);
		dataTable02.addCountry(countryNames[2], secondaryEnrollmentCountry03);

		System.out.println(dataTable02);
		// The output is:
		/*
		Country Name       1960       1961       1962       1963       1964       1965       1966       1967       1968       1969       1970       1971       1972       1973       1974       1975       1976       1977       1978       1979       1980       1981       1982       1983       1984       1985       1986       1987       1988       1989       1990       1991       1992       1993       1994       1995       1996       1997       1998       1999       2000       2001       2002       2003       2004       2005       2006       2007       2008       2009       2010       2011       2012       2013       2014       2015       2016       2017
              Canada                                                                                                                                                                                                                                                                                                                                                                                            90.70                                                                                                                                                                                                            92.31      91.77      91.35      92.86      99.65
               India                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        61.77
       United States                                                                                                                                                                                                                                                                                                                          88.41                                                                        88.25      89.44      89.66      88.94                                       87.00      86.89      87.40      85.57      88.70      89.30      88.81      88.29      88.39      88.56      87.94      86.70      87.63      87.33      88.12      88.90
		*/

		try
		{
			countryName = countryNames[1];
			int requestedStartYear = 2006;
			int requestedEndYear = 2014;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = dataTable02.getEnrollmentInCountryForPeriod(countryName,requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedStartYear,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}
		/*
		Requested enrollment period (2006 to 2014) for India:
		        Country Name
		               India      ,        ,        ,        ,        ,        ,        ,        ,    61.77
		*/

		try
		{
			countryName = countryNames[2];
			int requestedStartYear = 1960;
			int requestedEndYear = 2000;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = dataTable02.getEnrollmentInCountryForPeriod(countryName,requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedStartYear,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}
		/*
		Requested enrollment period (1960 to 2000) for United States:
		        Country Name
		       United States      ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,    88.41        ,        ,        ,        ,        ,        ,    88.25    89.44    89.66    88.94        ,        ,
		*/

		try
		{
			countryName = countryNames[0];
			int requestedStartYear = 2000;
			int requestedEndYear = 2030;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = dataTable02.getEnrollmentInCountryForPeriod(countryName,requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedStartYear,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}
		/*
		Requested enrollment period (2000 to 2030) for Canada:
		Invalid request of start and end year 2000, 2030. Using valid subperiod for Canada is 2000 to 2017.
 		       Country Name
 		             Canada      ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,        ,    92.31    91.77    91.35    92.86    99.65
		 */

		System.out.println("\nDone with TestEnrollment.\n");
	}
}