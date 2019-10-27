project folder:
Saytas-cs1b-project03/


Brief description of submitted files:

src/csv/TestParser.java
    - Tests the CSVParser class, which reads input from a CSV file. Uses
      the attributes stored in CSVParser object to fill the EnrollmentData class

src/csv/EnrollmentData.java
    - EnrollmentData class stores information about about all countries
      via multiple array objects, including a 2D array of doubles
    - Also contains toString() method for String representation of data table

src/csv/InvalidFileFormatException.java
    - InvalidFileFormatException class  extends java.io.IOException
      and throws an object of this type when a class wants to indicate
      that the format of the file is not valid

src/csv/CSVParser.java
    - CSVParser class reads input from a CSV file one line at a time and set various attributes
      Uses the attributes stored in CSVParser object to fill the EnrollmentData class

resources/
    - Five CSV (Comma Separated Value) file
        - childrenEnrolled_invalid.csv
        - childrenEnrolledInPrimary.csv
        - childrenEnrolledInPrimary_short_12years.csv
        - childrenEnrolledInSecondary.csv
        - childrenEnrolledInSecondary_short_12years.csv
    - First four rows contain the source of the data, net percentage, last updated year, and the number of countries.
    - Fifth row contains the country name title in the first column and the years in the rest of the columns
    - Rest of the rows contain the name of the countries and the enrollment data

    RUN.txt
    - Console output of TestParser.java, EnrollmentData.java, InvalidFileFormatException.java,
      and CSVParser.java files

README.txt
- Description of submitted files