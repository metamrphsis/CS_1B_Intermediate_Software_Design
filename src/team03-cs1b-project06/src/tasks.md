Tasks for Project 6 and Team 3
============================================

Part 1
---------

Task x:
    - Added to toString() methods in SchoolEnrollmentIndicator and LinkedList to allow both netPrimary and netSecondary
    to be displayed properly and edits to the formatting.
    - Added test cases to the RUN.txt

	- Mary Markart
	- Projected completion date: March 13, 2019
	- Completion date: March 13, 2019

Task: Defining generic LinkedList and generic Node and ListIterator classes as
       nested in the LinkedList class:<br><br>
    - Created the parameterized container class LinkedList that contains a collection of generic Node
      objects and defined the nested ListIterator class to enable the user to iterate over
      the elements. Updated the constructor and equals method (checks for the String object type) 
      in the Country class and added addIndicator() method<br><br>
    - Documented the files

	- Selahittin Saytaş
	- Projected completion date: March 09, 2019
	- Completion date: March 09, 2019

<br><br>

Part 2
---------
Task: Updating the IndicatorType class and Writing GraphView class:<br><br>
    - Created an instance variable label type of String to provide user a description of
    the constant and added corresponding fields for each constant. Created an accessor
    method getLabel() which returns the instance variable label to ease setting the
    instance variables xAxis and yAxis in the GraphView class.<br><br>
    -Wrote a constructor for the GraphView class that creates a data series
    for each Country object. The constructor takes an argument of DataModel object and
    initializes the axis for the LineChart. To create a series of years with corresponding
    data I created seriesFromCountry() method that populates the data in the XYChart as an
    ordered pairs in series. Then I wrote the update() method. The method gets an ordered pair
    data from the seriesFromCountry() method and adds the value of the data to our XYChart
    as on ordered pair in series. And lastly, Nitzan and I created the LinkedList<Country> type of createRandomListOfCountries()
    method. The method takes an array of country objects as an argument and asks user to make a choice to display
    their data of interest and how many data they want to display. Then the method creates and object
    of CountrySelector class and creates a random list of Country Objects by calling the method and 
    returns it<br><br>
    - Documented the files

	- Selahittin Saytaş
	- Projected completion date: March 18, 2019
	- Completion date: March 18, 2019


<br><br>

Extra Credit (if applicable)
-----------------------

Task 1:
    Added:
        - data files for world female population
        - IndicatorType FEMALE_POPULATION
        - CSVParser to parse the file for this IndicatorType
    Edited:
        - TestGenericList to allow for the new files added and test cases.
        - DataModel to allow for the IndicatorType FEMALE_POPULATION
        - ChartGraph to allow for the IndicatorType FEMALE_POPULATION
        - added a switch statement in ChartGraph to get the title for the graph from the different IndicatorTypes

	- Mary Markart
	- Projected Completion Date: March 17, 2019
	- Completion date: March 17, 2019
	
Task 1:	
    Added:
        - Class FemalePopIndicator:  stores the female population per year
    Edited:
        - GraphView  To use the class CountrySelector
        

	- Nitzan Zippel
	- Projected Completion Date: March 17, 2019
	- Completion date: March 17, 2019


<br><br>

Extra Credit Discussion (if applicable)
-----------------------

<br><br>
