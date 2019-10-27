package model;

/**
 *  SchoolEnrollmentIndicator class extends the abstract class Indicator
 *  Processes and returns the String representation of the data categories
 *  net percentage of children in enrolled in primary school and net percentage
 *  of children enrolled in secondary school
 *
 *  @author Foothill College, Bita Mazloom, Selahittin Saytaş
 */
public class SchoolEnrollmentIndicator extends Indicator
{
    private double netPrimary;
    private double netSecondary;

    /**
     * Constructor initializes the year and sets the instance
     * variable netPrimary to inherited INVALID_DATA
     * Receives one argument
     * @param year  For the year of this instance
     */
    public SchoolEnrollmentIndicator(int year)
    {
        super(year);
        netPrimary = INVALID_DATA;
        netSecondary = INVALID_DATA;
    }

    /**
     * Constructor initializes the instance variables year
     * and netPrimary. Receives two arguments
     * @param year  For the year of this instance
     * @param netPrimary For the primary enrollment
     * @param netSecondary For the secondary enrollment
     */

    public SchoolEnrollmentIndicator(int year, double netPrimary, double netSecondary)
    {
        super(year);
        this.netPrimary = netPrimary;
        this.netSecondary = netSecondary;
    }

    /**
     * The mutator method initializes the netPrimary instance variable
     * and sets the first index to netPrimary. Receives one argument
     * @param data  A single dimensional array of type double[]
     */

    public void setData(double[] data)
    {
        this.netPrimary = data[0];
        this.netSecondary = data[1];
    }

    /**
     * The mutator method updates the primary enrollment
     * Receives one argument which is used to update netPrimary
     * Does not return anything
     * @param dataForAllYear    A double value which is used
     *                          to update netPrimary
     */
    public void setPrimaryEnrollment(double dataForAllYear)
    {
        this.netPrimary = dataForAllYear;
    }

    /**
     * The mutator method updates the secondary enrollment
     * Receives one argument which is used to update netSecondary
     * @param dataForAllYear    A double value which is used
     *                          to update netPrimary
     */

    public void setSecondaryEnrollment(double dataForAllYear)
    {
        this.netSecondary = dataForAllYear;
    }

    /**
     * The accessor method returns the instance variable
     * netPrimary. Does not receive arguments
     * @return  Instance variable netPrimary
     */
    public double getPrimaryEnrollment()
    {
        return this.netPrimary;
    }

    /**
     * The accessor method returns the instance variable
     * netSecondary. Does not receive arguments
     * @return  Instance variable netSecondary
     */

    public double getSecondaryEnrollment()
    {
        return this.netSecondary;
    }

    /**
     * The accessor method sets the first index to the instance
     * variable netPrimary and second index to the instance variable
     * netSecondary, and returns an array of double primitives.
     * Does not receive an argument
     * @return  An array of double primitives which sets
     *          the first index to the instance variable netPrimary
     *          the second index to the instance variable netSecondary
     */

    public double[] getData()
    {
        // returns array of double primitives. Set the the first index to the instance variable netPrimary.
        return new double[]{netPrimary, netSecondary};
    }

    /**
     * Overwrites the toString() method and returns a String
     * representation of the object
     * @return  String representation of the object
     */

    public String toString()
    {
        // Note: If the value is set to INVALID_DATA do not output -1 (negative one).
        // Instead output an empty String with parenthesis.
        String output = "";

        if(netPrimary == -1 || netSecondary == -1)
        {
            output += String.format("[%.02f, %.02f]", netPrimary, netSecondary).replace("-1.00", "N/A");
        }

//        if(netPrimary == -1)
//        {
//            output += String.format("%10s", "()");
//        }
//        else
//        {
//            output += String.format("%10s", "(" + String.format("%.2f", netPrimary) + ")");
//        }
//
//        if(netSecondary == -1)
//        {
//            output += String.format("%10s", "()");
//        }
//        else
//        {
//            output += String.format("%10s", "(" + String.format("%.2f", netSecondary) + ")");
//        }

        return output;
    }
}
