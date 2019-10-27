package model;

/**
 *  IndicatorType enum class
 *
 *  @author Foothill College, Bita Mazloom, Selahittin Saytaş
 */
public enum IndicatorType
{
    INVALID("Invalid Data"),
    GDP_PER_CAPITA("GDP per capita (current US$)"),
    SCHOOL_ENROLLMENT_PRIMARY("School Enrollment In Primary (% net)"),
    SCHOOL_ENROLLMENT_SECONDARY("School Enrollment In Secondary (% net)"),
    SCHOOL_ENROLLMENT("School Enrollment In Primary and Secondary");

    private String label = "";

    /**
     * Constructs the instance variable label which is a
     * type of String. The caller will use to get a description
     * of the constant
     * @param label A String type of instance variable
     */
    IndicatorType(String label)
    {
        this.label = label;
    }

    /**
     * The accessor method returns the instance variable
     * label when it is called to set the xAxis and yAxis
     * instance variables of the GraphView class
     * Does not receive an argument
     * @return  The instance variable label
     */
    public String getLabel()
    {
        return label;
    }
}
