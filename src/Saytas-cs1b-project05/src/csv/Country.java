package csv;

/**
 * Country class holds an array of Indicator objects
 *
 * @author Foothill College, Bita Mazloom, Selahittin Saytaş
 */

public class Country
{
    private String name;
    private Indicator[] indicators;

    /**
     * Constructs the class by initializing the
     * instance variables
     * @param name  For the name of the current instance
     * @param numberOfYears For the number of years used to
     *                      initialize one dimensional array
     *                      of indicators
     */
    public Country(String name, int numberOfYears)
    {
        this.name = name;
        indicators = new Indicator[numberOfYears];
        for(int i = 0; i < indicators.length; i++)
        {
            indicators[i] = null;
        }
    }

    /**
     * Constructor initializes the instance
     * variable name which is used for object
     * comparison
     * @param name For the name of the current instance
     */
    public Country(String name)
    {
        this.name = name;
        indicators = new Indicator[7];
    }

    /**
     * The equals method returns true if the
     * argument matches the name of an instance
     * @param other A String for the name of the country
     *              we are searching for
     * @return  True if the name of the instance
     *          matches the argument
     */
    public boolean equals(Object other)
    {
        if(other instanceof Country)
        {
            Country current = (Country)other;
            if(current.name.equals(this.name))
            {
                return true;
            }
        }
//        if(other instanceof String)
//        {
//            String current = other;
//            if(current.name.equals(this.name))
//            {
//                return true;
//            }
//        }
        return false;
    }

    /**
     * The accessor method returns the name
     * of the current instance. Does not take
     * any argument
     * @return  The name of the current instance
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * The accessor method returns an int for the
     * first year that we have indicator data for
     * Does not receive any argument
     * @return  An int for the first year that we
     *          have indicator data for
     */
    public int getStartYear()
    {
        int startYear = indicators[0].getYear(); //indicators[0].year
        return startYear;
        //return indicators[0];
    }

    /**
     * The accessor method returns an int for the
     * last year that we have indicator data for
     * Does not receive any argument
     * @return  An int for the last year that we
     *          have indicator data for
     */
    public int getEndYear()
    {
        //int endYear = indicators[indicators.length - 1].year;
        int endYear = getStartYear() + indicators.length -1;
        //int endYear = startYear + indicators.length;
        return endYear;
    }

    /**
     * The accessor method returns the data if the
     * requested year is valid. Otherwise, it will
     * throw an IllegalArgumentException
     * @param requestedYear An int for the requested year
     * @return  An object of type indicator if the
     *          requested year exists in the indicators
     *          instance variable
     */
    public Indicator getIndicatorForYear(int requestedYear)
    {
        if(indicators[0] == null)
        {
            return null;
        }

        int startYear = getStartYear();
        //int endYear = startYear + indicators.length - 1;
        int endYear = startYear + indicators.length;

        if(requestedYear < startYear || requestedYear > endYear)
        {
            throw new IllegalArgumentException();
        }

        int moveToIndex = requestedYear - startYear;
        return indicators[moveToIndex];
    }

    /**
     * The mutator method updates the array indicators with
     * new data. This method will add the data for the
     * requested year. Throws and IllegalArgumentException if
     * the year does not exist.
     * @param requestedYear An int for the requested year
     * @param dataForOneYear    An object of type Indicators
     */
    public void setIndicatorForYear(int requestedYear, Indicator dataForOneYear)
    {
        if(indicators[0] == null)
        {
            indicators[0] = dataForOneYear;
            return;
        }

        int startYear = getStartYear();
        //int endYear = startYear + indicators.length - 1;
        int endYear = startYear + indicators.length;

        if(requestedYear < startYear || requestedYear > endYear)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            // Adding the data to the corresponding index
            int index = requestedYear - startYear;
            indicators[index] = dataForOneYear;
        }
    }

    /**
     * The accessor method determines if the requested year
     * range is valid. If not, the method adjusts the start
     * and end year to overlap between the requested and valid
     * start and end years. Then returns a one dimensional array
     * of type Indicator for the data between the requested start
     * and end year (inclusive). Throws an IllegalArgumentException
     * if both requested start year and end year are out of range, or
     * the requested start and end year are in inverted order
     * @param requestedStartYear For the requested starting year (inclusive)
     * @param requestedEndYear  For the requested ending year (inclusive)
     * @return  The data for the requested years between start and
     *          end year (inclusive)
     */
    public Indicator[] getIndicatorForPeriod(int requestedStartYear, int requestedEndYear)
    {
        if (indicators == null && indicators[0] == null) {
            return null;
        }

        Indicator[] output;

        int validStartingYear = 0;
        int validEndingYear = 0;
        int startingYear = indicators[0].year;

        int startYear = getStartYear();
        int endYear = startYear + indicators.length - 1;
        //int endYear = indicators[indicators.length - 1].year;
        int startingPos;
        int endingPos;

        if(((requestedStartYear < startingYear) && (requestedEndYear < startingYear)) || ((requestedStartYear > endYear) &&  (requestedEndYear > endYear)))
        {
            throw new IllegalArgumentException("Invalid request of start and end year " + requestedStartYear + ", " + requestedEndYear + ". Valid period for " + " is " + startingYear + " to " + endYear);
        }
        else if((requestedStartYear < startingYear) && (requestedEndYear > endYear))
        {
            validStartingYear = startingYear;
            validEndingYear = endYear;
            System.out.println("Invalid request of start and end year " + requestedStartYear + ", " + requestedEndYear + ". Using valid subperiod for " + " is " + validStartingYear + " to " + validEndingYear);
        }
        else if((requestedStartYear < startingYear) && (requestedEndYear <= endYear))
        {
            validStartingYear = startingYear;
            validEndingYear = requestedEndYear;
            System.out.println("Invalid request of start and end year " + requestedStartYear + ", " + requestedEndYear + ". Using valid subperiod for " + " is " + validStartingYear + " to " + validEndingYear);
        }
        else if(requestedStartYear >= startingYear && requestedEndYear > endYear)
        {
            validStartingYear = requestedStartYear;
            validEndingYear = endYear;
            System.out.println("Invalid request of start and end year " + requestedStartYear + ", " + requestedEndYear + ". Using valid subperiod for " + " is " + validStartingYear + " to " + validEndingYear);
        }
        else
        {
            validStartingYear = requestedStartYear;
            validEndingYear = requestedEndYear;
        }

        startingPos = validStartingYear - startingYear;
        endingPos = validEndingYear - startingYear;
        int size = endingPos - startingPos + 1;
        output = new Indicator[size];

        for(int i = 0, j = startingPos; i < size; i++)
        {
            output[i] = indicators[j];
            j++;
        }

        return output;
    }

    /**
     * The method's return value includes the name of Country
     * instance and the data stored in one dimensional
     * indicators array
     * @return A String object representing the instance
     */
    public String toString()
    {
        String output = "";
        String nameOfCountry = String.format("%-20s", this.name);
        output += nameOfCountry;

        for(int i = 0; i < indicators.length; i++)
        {
            output += String.format("%20s", indicators[i]);
        }

        return output + "\n";
    }
}
