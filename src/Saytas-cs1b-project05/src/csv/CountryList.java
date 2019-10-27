package csv;

/**
 * CountryList class manages a singly linked list of
 * CountryNode objects
 *
 * @author Foothill College, Bita Mazloom, Selahittin Saytaş
 */
public class CountryList
{
    private CountryNode head;
    private int size;

    /**
     * Constructor creates an empty list by initializing head to null
     * to indicate the list is empty and initializing size to 0 since
     * by default we have zero elements in the list. Receives no
     * arguments
     */
    public CountryList()
    {
        this.head = null;
        size = 0;
    }

    /**
     * The method adds the new object to the end of the list
     * Receives one argument a Country object as a parameter
     * That is the new data to be added to the list
     * @param node  A Country object as parameter
     */
    public void add(Country node)
    {
        CountryNode newNode = new CountryNode(node);
        CountryNode current;

        if(this.isEmpty())
        {
            head = newNode;
        }
        else
        {
            current = head;
            CountryNode previousNode = null;

            while(current.getNext() != null)
            {
                current = current.getNext();
            }

            current.setNext(newNode);
        }
        size++;
    }

    /**
     * The method checks in the CountryList is empty
     * @return True if the CountryList is empty
     *         otherwise false
     */
    public boolean isEmpty()
    {
        if(this.head == null)
        {
            return true;
        }
        return false;
    }

    /**
     * The method checks whether the list has the data
     * of interest. Receives one argument
     * @param tmpCountry    A County object for the
     *                      object we want to search for
     * @return  A Country object if the object is found in
     *          the linked list. If the object is not found
     *          return null
     */
    public Country contains(Country tmpCountry)
    {
        CountryNode tempCountry = new CountryNode(tmpCountry);
        CountryNode walker = head;

        while(!this.isEmpty())
        {
            if(walker.equals(tempCountry))
            {
                return walker.getData();
            }

            walker = walker.getNext();

            if(walker == null)
            {
                break;
            }
        }
        return null;
    }

    /**
     * The method returns the data at the requested index
     * Receives one argument which is an int value for the
     * requested index. If the request is out of bounds then
     * throws an IndexOutOfBoundsException
     * @param selectedIndex An int value for the requested index
     * @return  The specific Country object located at the
     *          requested index
     */
    public Country getIndex(int selectedIndex)
    {
        CountryNode walker = head;
        Country newCountry;
        int i = 0;

        if(this.isEmpty())
        {
            return null;
        }
        else if(selectedIndex < 0 || selectedIndex > size)
        {
            throw new IndexOutOfBoundsException();
        }

        while(walker != null && i <= selectedIndex)
        {
            if(i == selectedIndex)
            {
                return walker.getData();
            }
            walker = walker.getNext();
            i++;
        }
        return null;
    }

    /**
     * The method returns the instance variable size to indicate
     * the number of elements in the list.
     * @return  Instance variable size
     */
    public int size()
    {
        return this.size;
    }

    /**
     * The method returns a String containing information about
     * every element in the list
     * @return A String object representing information about
     *         every element in the linked list
     */
    public String toString()
    {
        StringBuilder output = new StringBuilder();
        output.append("[");

        CountryNode walker = head;

        while(walker != null)
        {
            output.append(walker);

            if(walker.getNext() != null)
            {
                output.append(", ");
            }

            walker = walker.getNext();
        }

        output.append("]");

        return output.toString();
    }
}
