import java.util.Comparator;

/**
 * The functor class has no attributes and implements the the generic Interface
 * Comparator which is the parameterized type of Employee
 * and compare() method takes two objects of type Employee that compares the
 * yearsOfService() served for two different objects of type Employee and returns an int
 *
 * @author Foothill College, Bita Mazloom, Nitzan Zippel, Selahittin Saytaş
 */
public class EmployeeYearsComparator implements Comparator<Employee>
{
	public int compare(Employee first, Employee second) 
	{
		if (first.getYearsEmployed() < second.getYearsEmployed())
			return -1;
		if (first.getYearsEmployed() > second.getYearsEmployed())
			return 1;
		return 0;
	}

}
