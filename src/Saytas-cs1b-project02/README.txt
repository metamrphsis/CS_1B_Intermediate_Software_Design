project folder:
Saytas-cs1b-project02/


Brief description of submitted files:

src/csv/TestEnrollment.java
    - Main application contains hard coded data and prints out 2D array, and uses
      attributes to fill the EnrollmentData class

src/csv/EnrollmentData.java
    - EnrollmentData class stores information about about all countries
      via multiple array objects, including a 2D array of doubles
    - Also contains toString() method for String representation of data table

resources/
    - Four CSV (Comma Separated Value) file.
        - childrenEnrolledInPrimary.csv
        - childrenEnrolledInPrimary_short_12years.csv
        - childrenEnrolledInSecondary.csv
        - childrenEnrolledInSecondary_short_12years.csv
    - First four rows contain the source of the data, net percentage, last updated year, and the number of countries.
    - Fifth row contains the country name title in the first column and the years in the rest of the column
    - Rest of the rows contain the name of the countries and the enrollment data

    RUN.txt
    - Console output of TestEnrollment.java and EnrollmentData.java

README.txt
    - Description of submitted files