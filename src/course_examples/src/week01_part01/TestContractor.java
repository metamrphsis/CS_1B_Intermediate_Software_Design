package week01_part01;

public class TestContractor 
{
	public static void main(String[] args) 
	{
		// Declare a variable of type Contractor.
		Contractor contractor01;

		// Initialize contractor01 by calling the default constructor.
		contractor01 = new Contractor();

		// Set the name of the contractor
		contractor01.setName("alice");

		// Set the hours the contractor has worked using a mutator method.
		int[] hours01 = {7, 8, 8, 8, 6};
		contractor01.setHours(hours01);

		// Display the text representation of the contractor01 object.
		System.out.println("contractor 01: " +  contractor01);


		// Declares and sets up a second object of type Contractor.
		int[] hours02 = {7, 5, 8, 8, 0};
		Contractor contractor02 = new Contractor("peter", hours02);
		System.out.println("contractor 02: " + contractor02.toString());
	}

}
