import java.util.Comparator;

/**
 * The functor class has no attributes and implements the generic Interface
 * Comparator which is the parameterized type of Employee
 * and compare() method takes two objects of type Employee that compares the
 * getSalary() for two different objects of type Employee and returns an int
 *
 * @author Foothill College, Bita Mazloom, Nitzan Zippel, Selahittin Saytaş
 */
public class EmployeeSalaryComparator implements Comparator<Employee>
{
    public int compare(Employee firstEmployee, Employee secondEmployee)
    {
        if(firstEmployee.getSalary() < secondEmployee.getSalary())
        {
            return -1;
        }

        if(firstEmployee.getSalary() > secondEmployee.getSalary())
        {
            return 1;
        }

        return 0;
    }
}
