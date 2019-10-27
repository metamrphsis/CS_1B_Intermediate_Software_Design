/**
 * The class creates and holds the information for a single employee
 *
 * @author Foothill College, Bita Mazloom, Nitzan Zippel, Selahittin Saytaş
 */
public class Employee
{
	private String name;
	private double salary;
	private int yearsOfService;

	public Employee()
	{
		name = "null";
		salary = 0;
		yearsOfService = 0;
	}

	public Employee(String name, double newSalary)
	{
		this.name = name;
		this.salary = newSalary;
		yearsOfService = 0;
	}

	public Employee(String name, double newSalary, int numYearsEmployed)
	{
		this.name = name;
		this.salary = newSalary;
		this.yearsOfService = numYearsEmployed;
	}

	public double getSalary()
	{
		return this.salary;
	}
	
	public int getYearsEmployed()
	{
		return this.yearsOfService;
	}

	public String toString()
	{
		String result = name + ", $";
		result += String.format("%.2f", salary); 
		result += ", years served  " + this.yearsOfService;
		return result + "\n";
	}
}
