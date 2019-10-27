project folder:
team03-cs1b-project07/

Brief description of submitted files:

src/csv/
        - Company.java
            - The class creates a list of employees by using Employee and MyLinkedList
              classes and finds the highest paid and longest served employees from the list

        - Employee.java
            - The class creates and holds the information for a single employee

        - EmployeeSalaryComparator.java
            - The functor class has no attributes and implements the the generic Interface
              Comparator which is the parameterized type of Employee
              and compare() method takes two objects of type Employee that compares the
              getSalary() for two different objects of type Employee and returns an int

        - EmployeeYearsComparator.java
            - The functor class has no attributes and implements the the generic Interface
              Comparator which is the parameterized type of Employee
              and compare() method takes two objects of type Employee that compares the
              yearsOfService() served for two different objects of type Employee and returns an int

        - MyLinkedList.java
            - The container class MyLinkedList contains and maintains a singly
              linked list of objects of a generic type Node objects
              And has Node, MyListIterator and MyLinkedList as nested classes

                - Node
                    - The Node class contains the data of interest.
                      In other words, it links the different elements
                      into a singly linked list structure

                - MyListIterator
                    -The purpose of this class is to traverse the
                      collection of objects within the list. The class
                      implements the generic interface Iterator

                - MyLinkedList class
                    - Constructor creates an empty list by initializing
                      head to null to indicate the list is empty and by
                      initilazing the size to zero since we have zero
                      elements in the list. Receives no argument

resources/

    RUN.txt
    - Console output

README.txt
- Description of submitted files