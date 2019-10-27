package model;

/**
 *  Indicator abstract class manages different data we read from
 *  one or more input files by taking advantage of inheritance to
 *  store different indicators in the same data structure
 *
 *  @author Foothill College, Bita Mazloom, Selahittin Saytaş
 */
public abstract class Indicator
{
    public int year; // Stores year of the indicator data
    public final int INVALID_DATA = -1;

    /**
     * Constuctor initializes the year instance variable
     * takes one argument for the year of the current data
     * @param year  The year of the current data
     */
    public Indicator(int year)
    {
        this.year = year;
    }

    /**
     * Takes no argument and returns the instance variable year
     * @return  Instance variable year
     */
    public int getYear()
    {
        return this.year;
    }

    /**
     * Abstract method will be implemented by the child class to
     * initialize one or more instance variables
     * @param data  A single dimensional array of double[] values
     */
    // Note: that we are restricting the values used in the data to be of type double.
    public abstract void setData(double[] data);

    /**
     * Receives no arguments and returns a single dimensional
     * double[] array
     * @return  A single dimensional double[] array
     */
    public abstract double[] getData();

    /**
     * Receives no argument and returns a String object
     * representing the instance
     * @return  A String object
     */
    public abstract String toString();

    /**
     * The mutator method sets the primary enrollment data
     * @param dataForAllYear An array of type double[]
     */
    public abstract void setPrimaryEnrollment(double dataForAllYear);

    public abstract void setSecondaryEnrollment(double dataForAllYear);
}
