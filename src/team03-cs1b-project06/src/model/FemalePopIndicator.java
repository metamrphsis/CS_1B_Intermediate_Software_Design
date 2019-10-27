package model;

public class FemalePopIndicator extends Indicator {
    private double femalePopulation;

    /**
     * Constructor initializes the year using a call to the
     * super constructor and initializes the instance variable
     * femalePopulation to the inherited INVALID_DATA
     * Receives one argument
     * @param year  For the year of this instance
     */
    public FemalePopIndicator(int year)
    {
        super(year);
        this.femalePopulation = INVALID_DATA;
    }

    /**
     * Constructor initializes the instance variables year using a call
     * to the super constructor and initializes the instance variable
     * femalePopulation by receiving a double argument femalePopInput
     * Receives two arguments
     * @param year  For the year of this instance
     * @param femalePopInput  For the gdp per capita
     */
    public FemalePopIndicator(int year, double femalePopInput)
    {
        super(year);
        this.femalePopulation = femalePopInput;
    }

    /**
     * Mutator method initializes the femalePopulation instance variable
     * Receives one argument
     * @param data  A single dimensional array of type double[]
     */
    public void setData(double[] data)
    {
        this.femalePopulation = data[0];
    }

    /**
     * Accessor method returns an array of double primitives
     * Does not receives an argument
     * @return  An single dimensional array of double[] primitives
     */
    public double[] getData()
    {
        return new double[]{femalePopulation}; // Creates and returns a new single dimensional array
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

        output += String.format("%20s", "[" + String.format("%.2f", femalePopulation) + "]");
        if(femalePopulation == -1)
        {
            output = String.format("%20s", "[N/A]"); //[N/A]
        }
        return output;
    }
    /**
     * The mutator method sets the primary enrollment data
     * and takes one argument of type double
     * @param femalePopInput For the femalePopulation double
     */

    @Override
    public void setPrimaryEnrollment(double femalePopInput) {
        this.femalePopulation = femalePopInput;
        
    }

    @Override
    public void setSecondaryEnrollment(double dataForAllYear) {
        // TODO Auto-generated method stub
    }

}
