package week05_part01.practice.deepViaClone;

public class Customer implements Cloneable
{
	private static int count = 1000;
	private String name;
	private int id;
	private double fee;

	public Customer(String customerName, double fee)
	{
		id = count++;
		this.name = customerName;
		this.fee = fee;
	}

	protected Object clone() //throws CloneNotSupportedException
	{
		// TO COMPLETE
		this.count--;
		Customer newCustomer = new Customer(this.name, this.fee);
		//newCustomer = (Customer)namclone();
        return newCustomer;
		//return null;
	}

	public void setFee(double updatedFee)
	{	fee = updatedFee;  }


	public String toString()
	{
		String info = "";
		info += String.format("#%-4d", id);
		info += String.format(" %6s", name);
		info += String.format(" %.2f", fee);
		return info;
	}
}