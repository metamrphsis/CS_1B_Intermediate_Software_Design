Practice Problems
========================

Learning Objectives
-------------------
- Creating an `ArrayList` of objects
- Implementing the `Comparator` interface

<br><br>

Problem 1
---------
**Given the class `Employee` and sample `main()` method. Circle the lines that result in compilation error and make corrections.**

```java	
public class Employee implements Comparable 
{
    private String name;
    private int yearsOfService;

    public Employee(String name, int numYearsEmployed) 
    {
        this.name = name;
        this.yearsOfService = numYearsEmployed;
    }


    public int compareTo(Employee other) 
    {
        // this instance has worked fewer years than the other person
        if(this.yearsOfService < other.yearsOfService)
            return -1;

        // this instance has worked more years than the other person
        if(this.yearsOfService > other.yearsOfService)
            return 1;

        // if both employees started in the same year
        return 0;
    }

    public String toString() 
    {    return this.name + ", years served  " + this.yearsOfService + "\n";  }
}


    // somewhere in main
    // create a list of employees
    ArrayList<> employees = new ArrayList<Employee>();
    employees.add( Employee("rumple", 14) );
    employees.add( Employee("hook", 10) );
    employees.add( Employee("bell", 12) );
    employees.add( Employee("swan", 10) );
    
    // adds some more employees...
    
    // later get a specific element in our list
    Employee thirdEntry = employees.get(2);
    
    // check if the thirdEntry has more years served than the last element in the list
    if (thirdEntry.compareTo("swan")) 
    {
        System.out.println("swan is 3rd in line.");
    }
```

<br><br>

Note: Problem 1 and Problem 2 are not related.

<br><br>

Problem 2
---------
**Given the class `Employee` and `TestComparatorOnEmployees`, define the class `EmployeeNameComparator` which implements `Comparator`.**

Recall that the compare method returns:
- -1 if first employee’s name is less than second employees, or
- 1 if first employee’s name is greater than seconds, or 
- 0 if both name’s are equal.

For future, when asked to implement a `Comparator`, assume the return values are similar.

```java
public class Employee
{
    private String name;
    private double salary;
    private int yearsOfService;
    
    // other content
}
```

and a sample main in test class:
```java
    Employee alice = new Employee("alice", 1000, 5);
    Employee bob = new Employee("bob", 1400, 3);
    
    EmployeeNameComparator ceName = new EmployeeNameComparator ();
    result = ceName.compare(alice, bob);
    System.out.println("Comparing names: " + result);
```

Define the `EmployeeNameComparator` class below: 

<br><br>
