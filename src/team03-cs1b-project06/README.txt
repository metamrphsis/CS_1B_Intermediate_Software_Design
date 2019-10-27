project folder:
team-03-cs1b-project06/


Brief description of submitted files:

src/csv/
        - CSVParser.java
            - CSVParser class reads input from a CSV file one line at a time and sets various attributes
              Uses the attributes stored in CSVParser object to fill the EnrollmentData class

        - InvalidFileFormatException.java
            - InvalidFileFormatException class extends java.io.IOException
              and throws an object of this type when a class wants to indicate
              that the format of the file is not valid

        - TestGenericList.java
            - Tests the parameterized LinkedList class, which holds a singly linked list
              of Node objects.

src/model/
        - Country.java
            - Country class holds an array of Indicator objects

        - DataModel.java
            - Provides access to CSV data

        - GDPIndicator.java
            - GDPIndicator class extends the abstract class Indicator
              Processes and returns the String representation of the
              GDP per capita data

        - Indicator.java
            - Indicator abstract class manages different data we read from
              one or more input files by taking advantage of inheritance to
              store different indicators in the same data structure

        - IndicatorType.java
            - IndicatorType enum class

        - LinkedList.java
            - The container class LinkedList contains a collection of
              generic Node objects.

            - LinkedList class
                - Constructor creates an empty list by initializing
                  head to null to indicate the list is empty and by
                  initilazing the size to zero since we have zero
                  elements in the list. Receives no argument

            - Node class
                - The Node class contains the data of interest.
                  In other words, it links the different elements
                  into a singly linked list structure

            - ListIterator class
                - The purpose of this class is to traverse the
                  collection of objects within the list. The class
                  implements the generic interface Iterator

        - SchoolEnrollmentIndicator.java
            - SchoolEnrollmentIndicator class extends the abstract class Indicator
              Processes and returns the String representation of the data categories
              net percentage of children in enrolled in primary school and net percentage
              of children enrolled in secondary school

        - FemalePopIndicator.java
                    - FemalePopIndicator class extends the abstract class Indicator
                      Processes and returns the String representation of the
                      Female population data

src/view/
        - ChartGraph.java
            - Instantiates a JavaFX application which creates a model of the data.
              Uses the model to instantiate an object of type  javafx.scene.chart.LineChart
              via the GraphView class. Then sets up the scene with basic modification to
              the stage.

        - CountrySelector.java
            - Randomly builds a LinkedList of Country objects to be used in the graph

        - GraphView.java
            - The GraphView class extends the parameterized LineChart
              from javafx.scene package. This class will create a data
              series for each Country object

resources/
    - Seven CSV (Comma Separated Value) file
        - childrenEnrolled_invalid.csv
        - childrenEnrolledInPrimary.csv
        - childrenEnrolledInPrimary_short_12years.csv
        - childrenEnrolledInSecondary.csv
        - childrenEnrolledInSecondary_short_12years.csv
        - gdpPerCapita.csv
        - gdpPerCapita_short_12years.csv
        Extra Credit:
        - female_population.csv
        - female_population_short.csv
        - female_population_rawData

    - First four rows contain the source of the data, Indicator type whether school enrollment
    - data or GDP per capita data, last updated year, and the number of countries.
    - Fifth row contains the country name title in the first column and the years in the rest of the columns
    - Rest of the rows contain the name of the countries and the enrollment data

    RUN.txt
    - Console output of TestCountry.java, TestCountryList.java, Country.java, CountryList.java,
      CountryNode.java, InvalidFileFormatException.java, CSVParser.java, IndicatorType.java,
      Indicator.java, GDPIndicator.java, and SchoolEnrollmentIndicator.java files

README.txt
- Description of submitted files