package week01_part01;

public class Contractor
{
	private String name;
	private int[] hoursWorked;
	private int totalHours;
	
	public Contractor()
	{
		name = "null";
		totalHours = 0;
	}

	public Contractor(String name, int[] hoursWorked)
	{
		this.name = name;
		this.totalHours = 0;
		setHours(hoursWorked);
	}

	public void setName(String newName)
	{
		this.name = newName;
	}
	
	
	public void setHours(int[] paramArray)
	{
		// Instantiate the array hoursWorked to the correct size
		hoursWorked = new int[paramArray.length];

		// Copy values from parameter array to hoursWorked
		for(int index = 0; index < paramArray.length; index++) 
		{
			hoursWorked[index] = paramArray[index];
			totalHours += paramArray[index];  // or hoursWorked[index]
		}
	}
	
		
	public String toString()
	{
		String result = "";

		// A. Concatenate the name of the current instance
		result += name;

		// B. Concatenate the total hours
		result += " has worked " + totalHours + " total hours.";

		// C. Concatenate the contents of hoursWorked in the current instance
		//    by traversing the array.
		result += " Worked hours are: ";
		for(int i = 0; i < hoursWorked.length; i++)
		{
			result += hoursWorked[i];
			if(i != hoursWorked.length-1)
				result += ",";
		}
		// Question: What would happen if instead of the for-loop in C we
		//           directly concatenated the reference hoursWorked?
		// result += hoursWorked; // This would give the reference (memory) address

		return result;
	}
}
