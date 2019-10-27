package week05_part01.practice.deepViaClone;

public class MagazineSubscriptions implements Cloneable
{
	public static final int MAX_SIZE = 10;
	private String logo;
	private int totalSubscriptions;
	private Customer[] customers;

	public MagazineSubscriptions(String logo)
	{
		this.logo = logo;

		this.customers = new Customer[MAX_SIZE];
		for (int i = 0; i < customers.length; i++)
			this.customers[i] = null;

		this.totalSubscriptions = 0;
	}


	public void addCustomer(Customer subscriber)
	{
		this.customers[totalSubscriptions] = subscriber;
		this.totalSubscriptions++;
	}

	protected Object clone()
	{
		// TO COMPLETE
		int arraySize = this.customers.length;
		MagazineSubscriptions newMagazinesubscriptions = new MagazineSubscriptions(this.logo);
		newMagazinesubscriptions.totalSubscriptions = totalSubscriptions;
		//MagazineSubscriptions ms = (MagazineSubscriptions)newMagazinesubscriptions.clone();

		for(int i = 0; i < newMagazinesubscriptions.customers.length; i++)
		{
			newMagazinesubscriptions.customers[i] = (Customer)this.customers[i].clone();
		}

		return newMagazinesubscriptions;
	}


	protected void setCustomerFee(int index, double updatedFee) throws java.util.NoSuchElementException
	{
		// get the requested customer number
		Customer found = customers[index];

		// if customer does not exist, do nothing and return
		if (found == null)
			throw new java.util.NoSuchElementException();

		found.setFee(updatedFee);
	}

	public int getNumberOfSubscribers()
	{	return customers.length; }

	public String toString()
	{
		String details = "(" + String.format("%-4s, #subs: %-3d: ", this.logo, this.totalSubscriptions);

		for (int i = 0; i < customers.length; i++)
		{
			if (customers[i] ==  null)
				continue;

			details += customers[i];

			if (i < customers.length-2 && customers[i+1] != null)
				details += ",";
		}

		details += ")";

		return details;
	}

}