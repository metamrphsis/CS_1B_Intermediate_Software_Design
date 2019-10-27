project folder:
Saytas-cs1b-project05/


Brief description of submitted files:

src/csv/TestCountry.java
    - Tests the Country class, which holds Indicator data for each year in
      chronological order

src/csv/TestCountryList.java
    - Tests the CountryList class, which holds a linked list of CountryNode objects

src/csv/Country.java
    - Country class holds an array of Indicator objects

src/csv/CountryList.java
    - CountryList class manages a singly linked list of CountryNode objects

src/csv/CountryNode.java
    - CountryNode class stores a Country object as the data and a reference to the next
      CountryNode. The class enables us to link the data in a singly linked list structure

src/csv/InvalidFileFormatException.java
    - InvalidFileFormatException class extends java.io.IOException
      and throws an object of this type when a class wants to indicate
      that the format of the file is not valid

src/csv/CSVParser.java
    - CSVParser class reads input from a CSV file one line at a time and sets various attributes
      Uses the attributes stored in CSVParser object to fill the EnrollmentData class

src/csv/IndicatorType.java
    - IndicatorType enum class contains constant attributes for different data,
      GDP_PER_CAPITA, SCHOOL_ENROLLMENT_PRIMARY, and SCHOOL_ENROLLMENT_SECONDARY, namely

src/csv/Indicator.java
    - Indicator abstract class manages different data we read from one or more input files
      by taking advantage of inheritance to stores different indicators in the same data structure

src/csv/GDPIndicator.java
    - GDPIndicator class extends the abstract class Indicator and processes
      and returns the String representation of the GDP per capita data

src/csv/SchoolEnrollmentIndicator.java
    - SchoolEnrollmentIndicator class extends the abstract class Indicator and processes
      and returns the String representation of the data categories: Net percentage of
      children in enrolled in primary school and net percentage of children enrolled
      in secondary school

resources/
    - Seven CSV (Comma Separated Value) file
        - childrenEnrolled_invalid.csv
        - childrenEnrolledInPrimary.csv
        - childrenEnrolledInPrimary_short_12years.csv
        - childrenEnrolledInSecondary.csv
        - childrenEnrolledInSecondary_short_12years.csv
        - gdpPerCapita.csv
        - gdpPerCapita_short_12years.csv
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
