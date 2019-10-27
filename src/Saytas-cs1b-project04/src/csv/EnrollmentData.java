package csv;

/**
 *  EnrollmentData class where one object represents a table
 *  containing all data for different countries and returns it
 * @author Foothill College, Bita Mazloom, Selahittin Saytaş
 */
public class EnrollmentData
{
    private String [] countryNames;
    private int [] yearLabels;
    private Indicator[][] table;
    private int startingYear;
    private int countryTracker;

    /**
     * Constructs an object and initializes the instance variables
     * @param numRows   The number of countries
     * @param numColumns    The number of years of enrollment for a given country
     * @param startingYear  The starting year for the data for all countries
     */
    public EnrollmentData(int numRows, int numColumns, int startingYear)
    {
        this.countryNames = new String[numRows];
        this.yearLabels = new int[numColumns];
        this.table = new Indicator[numRows][numColumns];
        this.startingYear = startingYear;
        countryTracker = 0;

        // Initializing the years in yearLabels[] 1D array
        for(int i = 0; i < numColumns; i++)
        {
            yearLabels[i] = startingYear + i;
        }
    }

    /**
     * Adds country name to instance variable countryNames[] array and adds
     * the one dimensional array of enrollment data to table[][] array
     * @param countryName   The name of the country
     * @param partialPrimaryEnrollment  One dimensional array of Indicator[] for the
     *                                  enrollment data of a specific country
     */
    public void addCountry(String countryName, Indicator[] partialPrimaryEnrollment)
    {
        try
        {
            countryNames[countryTracker] = countryName;
            for(int j = 0; j < partialPrimaryEnrollment.length; j++)
            {
                table[countryTracker][j] = partialPrimaryEnrollment[j];
            }
            countryTracker++;
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println(e);
        }
    }

    /**
     * Determines if the requested year range is valid. If not, first display a message
     * specifying which request is invalid. Then adjust the start and end year to the
     * overlap between the requested and valid start and end years. Then return a one
     * dimensional array of double for the enrollment data of the given country name
     * between the requested start year and end year (inclusive).
     * @param countryName   The country name to search for
     * @param requestedStartYear    The requested starting year
     * @param requestedEndYear      The requested ending year
     * @return  A one dimensional array of double[] for the enrollment data of the given
     *          country name between the requested start and end year (inclusive)
     * @throws IllegalArgumentException In case, a valid period does not exist between
     *                                  the requested start and end years
     */
    public Indicator[] getEnrollmentInCountryForPeriod(String countryName, int requestedStartYear, int requestedEndYear) throws IllegalArgumentException
    {
        Indicator[] output;

        int countryIndex = -1;
        int validStartingYear = 0;
        int validEndingYear = 0;
        int endYear = yearLabels[yearLabels.length - 1];
        int startingPos;
        int endingPos;

        // Checking for a matching countryName then initializing the countryIndex
        for(int i = 0; i < countryNames.length; i++)
        {
            if(countryNames[i].equals(countryName))
            {
                countryIndex = i;
                break;
            }
        }

        if(((requestedStartYear < startingYear) && (requestedEndYear < startingYear)) || ((requestedStartYear > endYear) &&  (requestedEndYear > endYear)))
        {
            throw new IllegalArgumentException("Invalid request of start and end year " + requestedStartYear + ", " + requestedEndYear + ". Valid period for " + countryNames[countryIndex] + " is " + startingYear + " to " + endYear);
        }
        else if((requestedStartYear < startingYear) && (requestedEndYear > endYear))
        {
            validStartingYear = startingYear;
            validEndingYear = endYear;
            System.out.println("Invalid request of start and end year " + requestedStartYear + ", " + requestedEndYear + ". Using valid subperiod for " + countryNames[countryIndex] + " is " + validStartingYear + " to " + validEndingYear);
        }
        else if((requestedStartYear < startingYear) && (requestedEndYear <= endYear))
        {
            validStartingYear = startingYear;
            validEndingYear = requestedEndYear;
            System.out.println("Invalid request of start and end year " + requestedStartYear + ", " + requestedEndYear + ". Using valid subperiod for " + countryNames[countryIndex] + " is " + validStartingYear + " to " + validEndingYear);
        }
        else if(requestedStartYear >= startingYear && requestedEndYear > endYear)
        {
            validStartingYear = requestedStartYear;
            validEndingYear = endYear;
            System.out.println("Invalid request of start and end year " + requestedStartYear + ", " + requestedEndYear + ". Using valid subperiod for " + countryNames[countryIndex] + " is " + validStartingYear + " to " + validEndingYear);
        }
        else
        {
            validStartingYear = requestedStartYear;
            validEndingYear = requestedEndYear;
        }


        startingPos = validStartingYear - startingYear;
        endingPos = validEndingYear - startingYear;
        output = new Indicator[endingPos - startingPos + 1];

        for(int i = startingPos, j = 0; i <= endingPos; i++, j++)
        {
            output[j] = table[countryIndex][i]; //.getData()[0];
        }
        return output;
    }

    /**
     * String representation of the enrollment data stored in 2D table[][] array
     * The order of the String:
     * country name title, year labels as the column headers, country names
     * as the row labels, and the corresponding data
     * @return  result which prints out a data table[][] 2D array with country names
     *          and years with the data in the corresponding year
     */
    public String toString()
    {
        System.out.println("\n");
        String result = "";
        String tempVal;
        String message = "";
        String nameOfCountry = "Country Name";

        nameOfCountry = String.format("%20s ", nameOfCountry);
        result += message + "\n";
        result += nameOfCountry;

        for(int i = 0; i < yearLabels.length; i++)
        {
            result += String.format("%10s ", yearLabels[i]);
        }
        result += "\n";

        System.out.println("\n");
        for(int i = 0; i < countryNames.length; i++)
        {
            String nameOfTheCountry = String.format("%20s ", countryNames[i]);
            result += nameOfTheCountry;

            try
            {
                for(int j = 0; j < table[i].length; j++)
                {
                    if(table[i][j].equals(-1)) // (table[i][j] == -1)
                    {
                        tempVal = String.format("%10s ", "  ");
                        result += tempVal;
                    }
                    else
                    {
                        result += String.format("%10s ", this.table[i][j].toString());
                    }
                }
            }
            catch(NullPointerException e)
            {
                //System.out.println(e);
            }
            result += "\n";
        }
        return result;
    }
}
