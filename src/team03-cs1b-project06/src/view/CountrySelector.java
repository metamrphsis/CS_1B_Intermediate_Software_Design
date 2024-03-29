package view;

import model.Country;
import model.LinkedList;

import java.util.Random;

/**
 * Randomly builds a LinkedList of Country objects to be used in the graph
 * @author Foothill College, Bita Mazloom, Selahittin Saytaş
 */
public class CountrySelector 
{
	private LinkedList<Country> selectedCountries;

	/**
	 * Builds a linked list of random countries
	 * @param allCountries      An array of Country objects
	 * @param requestedSize	The requested number of elements.
	 */
	public CountrySelector(Country[] allCountries, int requestedSize)
	{
		// Build the list out of a random selection of countries.
		Random random = new Random();

		// A singly linked list of country data.
		selectedCountries = new LinkedList<Country>();
		for (int i = 0; i < requestedSize; i++)
		{
			int selectedIndex = random.nextInt(allCountries.length);
			selectedCountries.add(allCountries[selectedIndex]);
		}
	}

	/**
	 * Accessor method for randomly selected list of countries.
	 * @return LinkedList of Country objects.
	 */
	public LinkedList<Country> selectCountries()
	{
		return this.selectedCountries;
	}
}
