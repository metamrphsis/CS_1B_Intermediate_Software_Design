package csv;

/**
 *  GDPIndicator class extends the abstract class Indicator
 *  Processes and returns the String representation of the
 *  GDP per capita data
 * @author Foothill College, Bita Mazloom, Selahittin Saytaş
 */
public class GDPIndicator extends Indicator
{
    private double gdpPerCapita;

    /**
     * Constructor initializes the year using a call to the
     * super constructor and initializes the instance variable
     * gdpPerCapita to the inherited INVALID_DATA
     * Receives one argument
     * @param year  For the year of this instance
     */
    public GDPIndicator(int year)
    {
        super(year);
        this.gdpPerCapita = INVALID_DATA;
    }

    /**
     * Constructor initializes the instance variables year using a call
     * to the super constructor and initializes the instance variable
     * gdpPerCapita by receiving a double argument gdpPerCapita
     * Receives two arguments
     * @param year  For the year of this instance
     * @param gdpPerCapita  For the gdp per capita
     */
    public GDPIndicator(int year, double gdpPerCapita)
    {
        super(year);
        this.gdpPerCapita = gdpPerCapita;
    }

    /**
     * Mutator method initializes the gdpPerCapita instance variable
     * Receives one argument
     * @param data  A single dimensional array of type double[]
     */
    public void setData(double[] data)
    {
        this.gdpPerCapita = data[0];
    }

    /**
     * Accessor method returns an array of double primitives
     * Does not receives an argument
     * @return  An single dimensional array of double[] primitives
     */
    public double[] getData()
    {
        return new double[]{gdpPerCapita}; // Creates and returns a new single dimensional array
    }

    /**
     * Overwrites toString() method and returns
     * a String representation of the object
     * @return  A String representation of the object
     */
    public String toString()
    {
        // Note: If the value is set to INVALID_DATA do not output -1 (negative one).
        // Instead output an empty String with parenthesis.
        String output = "";
        output += String.format("%10s", "(" + String.format("%.2f", gdpPerCapita) + ")");
        if(gdpPerCapita == -1)
        {
            output = String.format("%10s", "()");
        }
        return output;
    }

    /**
     * The mutator method sets the primary enrollment data
     * and takes one argument of type double
     * @param gdpPerCapita  For the gdpPerCapita double
     */
    public void setPrimaryEnrollment(double gdpPerCapita)
    {
        this.gdpPerCapita = gdpPerCapita;
    }
}
