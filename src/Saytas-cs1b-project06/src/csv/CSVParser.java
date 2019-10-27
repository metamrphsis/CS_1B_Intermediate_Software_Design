package csv;

import model.IndicatorType;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *  CSVParser class reads input from a CSV file one line at a time and sets various attributes
 *  Uses the attributes stored in CSVParser object to fill the EnrollmentData class
 *
 *  @author Foothill College, Bita Mazloom, Selahittin Saytaş
 */

public class CSVParser
{
    private String[] countryNames;
    private int[] yearLabels;
    private double[][] dataTable;
    private int countryTracker;

    private IndicatorType indicatorType;

    /**
     * Constructor parses the file and fills the instance variables
     * countryNames[], yearLabels[], and dataTable[][] arrays
     * @param inputFile     The path of the file
     * @throws FileNotFoundException    In case the file path cannot be found
     * @throws InvalidFileFormatException   In case the format of the file is invalid
     */
    public CSVParser(String inputFile) throws FileNotFoundException, InvalidFileFormatException
    {
        // looking for the dot/. separator
        int indexOfDot = inputFile.indexOf(".");

        // looking for the extension after the dot/. separator e.g. .csv in this case
        String extension = inputFile.substring(indexOfDot + 1);
        File fileName;
        Scanner input;
        countryTracker = 0;
        int numberOfColumns = 0;
        int count = 0;
        int tokenLength = 0;
        fileName = new File(inputFile);
        String[] linesOfText;

        try
        {
            input = new Scanner(fileName);

            // Reads the data file line by line
            String netPercentage = "";
            String date = "";
            while(input.hasNextLine())
            {
                String line = input.nextLine();
                String[] tokens = line.split(",", -1);
                if(count == 1)
                {
                    netPercentage = tokens[1];
                    if(tokens[1].equals("School Enrollment In Primary (% net)"))
                    {
                        indicatorType = IndicatorType.SCHOOL_ENROLLMENT_PRIMARY;
                    }
                    else if(tokens[1].equals("GDP per capita (current US$)"))
                    {
                        indicatorType = IndicatorType.GDP_PER_CAPITA;
                    }
                    else if(tokens[1].equals("School Enrollment In Secondary (% net)"))
                    {
                        indicatorType = IndicatorType.SCHOOL_ENROLLMENT_SECONDARY;
                    }
                }
                if(count == 2)
                {
                    date = tokens[1];
                }
                if(count == 3)
                {
                    if(!tokens[0].equals("Number of Countries"))
                    {
                        String temp = "Invalid file format at " + inputFile;
                        throw new InvalidFileFormatException(temp, "Expecting number of countries at line number 4");
                    }
                    System.out.println("\n\t\tGiven the " + inputFile + " file, the output is: ");
                    System.out.println("\t\t\t" + netPercentage + " updated at " + date);
                    int numberOfCountries = Integer.parseInt(tokens[1]);
                    int tokensLength = tokens.length - 1;
                    countryNames = new String[numberOfCountries];
                }
                else if(count == 4)
                {
                    if(tokens.length < 3)
                    {
                        String temp = "Invalid file format at line number 5" + inputFile;
                        throw new InvalidFileFormatException(temp, "missing start or end year");
                    }

                    int startingYear = Integer.parseInt(tokens[1]);
                    int endingYear = Integer.parseInt(tokens[tokens.length - 1]);
                    numberOfColumns = endingYear - startingYear + 1;
                    yearLabels = new int[numberOfColumns];
                    dataTable = new double[countryNames.length][numberOfColumns];

                    // Adds years for the data into yearLabels[] array
                    addYearLabels(startingYear, numberOfColumns);
                }
                else if(count > 4)
                {
                    try
                    {
                        if(line.startsWith("\""))
                        {
                            // dividing the line with delimiter " (double quote)
                            tokens = line.split("\"", -1);

                            // Position 1 is countryName
                            String countryName = tokens[1];

                            // Position 2 is the corresponding data
                            String data = tokens[2].substring(1);

                            // Storing the substring of data into temp[] array
                            String[] temp = data.split(",", -1);
                            tokens = new String[temp.length + 1];
                            tokens[0] = countryName;
                            for(int i = 0; i < temp.length; i++)
                            {
                                tokens[i + 1] = temp[i];
                            }
                        }

                    }
                    catch(StringIndexOutOfBoundsException e) {

                        System.out.println(e);
                    }

                    // Adds country names to the countryNames[] array
                    addCountryNames(tokens[0]);

                    if(tokens.length != numberOfColumns + 1)
                    {
                        String temp = "Invalid file format at " + inputFile;
                        throw new InvalidFileFormatException(temp, "Number of columns less in line number " + count);
                    }

                    // Takes a String[] array and an int data type for index position
                    // as a parameter and adds the data into dataTable[][] 2D array
                    addRowParsedTable(tokens, countryTracker);
                    countryTracker++;
                }
                count++;
            }
            input.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File " + fileName + " not found!");
            //throw new InvalidFormatException();
        }
    }

    /**
     * Adds the country names into the countryNames[] String array
     * @param countryName   Takes countryName String as an argument
     */
    public void addCountryNames(String countryName)
    {
        String output = "";

        for(int i = 0; i < countryNames.length; i++)
        {
            String tempCountryNames; //= this.countryNames[countryTracker] = countryName;
            this.countryNames[countryTracker] = countryName;
            tempCountryNames = this.countryNames[countryTracker];
            output += String.format("%10s ", tempCountryNames);
        }
    }

    // This method can be a get method by printing out all the values
    /**
     * Adds the years into the yearLabels[] int array
     * @param startingYear  Takes int startingYear as argument
     * @param numberOfIteration Takes int numberOfIteration as argument
     */
    public void addYearLabels(int startingYear, int numberOfIteration)
    {
        int counter = 0;
        String output = "";
        for(int i = startingYear; i < startingYear + numberOfIteration; i++)
        {
            yearLabels[counter] = i;
            counter++;
        }
    }

    /**
     * Adds the country names index to the rows and the data into
     * the corresponding columns in the dataTable[][] 2D double array
     * @param tokens    Takes tokens[] array as an argument which is the data
     * @param index     Takes index int as an argument which is country index
     */
    public void addRowParsedTable(String[] tokens, int index)
    {
        // try-catch block proves the problem
        try
        {
            // First position is country index
            // The rest of the position is corresponding data index
            for(int i = 0, j=1; i < dataTable[0].length; i++, j++)
            {
                if(tokens[j].equals(""))
                {
                    dataTable[index][i] = -1;
                }
                else
                {
                    dataTable[index][i] = Double.parseDouble(tokens[j]);
                }
            }
        }
        catch(NumberFormatException e)
        {
            //System.out.println(e);
        }
    }

    /**
     * Accessor method returns countryNames[] array
     * @return  The countryNames[] array
     */
    public String[] getCountryNames()
    {
        return countryNames;
    }

    /**
     * Accessor method returns yearLabels[] array
     * @return  The yearLabels[] array
     */
    public int[] getYearLabels()
    {
        return yearLabels;
    }

    /**
     * Accessor method returns dataTable[][] array
     * @return  The dataTable[][] array
     */
    public double[][] getParsedTable()
    {
        return dataTable;
    }

    /**
     * Accessor method returns the number of columns in 2D table
     * @return  The length of yearLabels[] array
     */
    public int getNumberOfYears()
    {
        return yearLabels.length;
    }

    /**
     * Returns the indicator type set when parsed the input
     * file in the constructor. Receives no argument
     * @return  The indicator type set
     */
    public IndicatorType getIndicatorType()
    {
        return indicatorType;
    }
}
