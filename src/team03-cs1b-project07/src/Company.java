import java.util.Iterator;
import java.util.Random;

/**
 * The class creates a list of employees by using Employee and MyLinkedList
 * classes and finds the highest paid and longest served employees from the list
 *
 * @author Foothill College, Bita Mazloom, Nitzan Zippel, Selahittin Saytaş
 */
public class Company
{

	private static final double MINSALARY = 1000;
	private static final double MAXSALARY = 10000;
	private MyLinkedList<Employee> employees;

	/**
	 * The constructor initializes the instance variable
	 * employees which is a type of MyLinkedList
	 * @param employees	A MyLinkedList type
	 */
	public Company(MyLinkedList<Employee> employees)
	{
		this.employees = employees;
	}

	/**
	 * The method takes no arguments and finds the longest
	 * serving employee in a company and returns that employee
	 * @return	An Employee object of longest served employee from
	 * 			the list of employees
	 */
	public Employee findLongestServingEmployee()
	{
	    Iterator<Employee> testItr = employees.iterator();
		Employee longestServingEmployee = testItr.next();
		while (testItr.hasNext())
		{
		    Employee employeeToCompare = testItr.next();
		    if (longestServingEmployee.getYearsEmployed() < employeeToCompare.getYearsEmployed() )
		    {
		        longestServingEmployee = employeeToCompare;
		    }

		}
		
		return longestServingEmployee;
	}

	/**
	 * finds the highest Earning employee in a company and returns that employee.
	 * @return highestpaid        The highest paid employee from the list of employees
	 */
	public Employee findHighestEarningEmployee()
	{
		EmployeeSalaryComparator comparingEmployees = new EmployeeSalaryComparator();
		Iterator<Employee> testItr = employees.iterator();
		Employee highestpaid = testItr.next();

		while(testItr.hasNext())
		{
			Employee testEmp = testItr.next();
			if(comparingEmployees.compare(highestpaid, testEmp) == -1)
			{
				highestpaid = testEmp;
			}
		}
		return highestpaid;
	}

	/**
	 * The main method builds a list of employees and
	 * outputs the information regarding them
	 * @param args A array of String arguments
	 */
	public static void main(String[] args) 
	{
		String [] names = {"alice", "bob", "dan", "cindy", "janice", "sam", "george", "ryan", "tom"};
		
		Random randomGenerator = new Random();
		
		MyLinkedList<Employee> allWorkers = new MyLinkedList<Employee>();

		for(int i = 0; i < names.length; i++)
		{
			// arbitrarily limited to 20 years, let's say that's how long the company has been around
			int numYears = randomGenerator.nextInt(20) + 1;
			double salary = randomGenerator.nextDouble()*(MAXSALARY - MINSALARY) + MINSALARY;
			int nPos = randomGenerator.nextInt(names.length);
			
			Employee worker = new Employee(names[nPos], salary, numYears);
			allWorkers.addToFront(worker);
		}
		
		
		// for debugging
		allWorkers.printList();
		
		Company myStartUp = new Company(allWorkers);
		
		// TO COMPLETE
		Employee toReward = myStartUp.findLongestServingEmployee();
		System.out.println("\nLongest serving employee: " + toReward);
		
		// TO COMPLETE
		Employee highestPaid = myStartUp.findHighestEarningEmployee();
		System.out.println("Highest paid employee: " + highestPaid);







		// TEST CASES:
		System.out.println("\nTest Cases:\n");

		// Creating a new list of employees
		String[] employeeNames = {"Emma", "Etienne", "Octavia", "Hugo", "Nicole", "Sela", "Irene"};
		MyLinkedList<Employee> someEmployees = new MyLinkedList<>();

		for(int i = 0; i < employeeNames.length; i++)
		{
			// arbitrarily limited to 33 years, let's say that's how long the company has been around
			int numYears = randomGenerator.nextInt(33) + 1;
			double salary = randomGenerator.nextDouble()*(MAXSALARY - MINSALARY) + MINSALARY;
			int nPos = randomGenerator.nextInt(employeeNames.length);

			Employee someEmployee = new Employee(employeeNames[nPos], salary, numYears);
			someEmployees.addToFront(someEmployee);
		}

		someEmployees.printList();

		Company testCompany = new Company(someEmployees);

		// Test case 1:
		Employee testCaseOneLongestServing = testCompany.findLongestServingEmployee();
		System.out.println("\nTest Case 1:\nThe longest serving employee : " + testCaseOneLongestServing);
		Employee testCaseOneHighestPaid = testCompany.findHighestEarningEmployee();
		System.out.println("The highest paid employee : " + testCaseOneHighestPaid);

		// Test case 2:
		Employee testCaseTwoLongestServing = testCompany.findLongestServingEmployee();
		System.out.println("Test Case 2:\nThe longest serving employee : " + testCaseTwoLongestServing);
		Employee testCaseTwoHighestPaid = testCompany.findHighestEarningEmployee();
		System.out.println("The highest paid employee : " + testCaseTwoHighestPaid);
	}

}
