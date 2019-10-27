package csv;

/**
 * Class stores a Country object as the data and a reference to the next CountryNode
 * The class enables us to link the data in a singly linked list structure.
 *
 * @author Foothill College, Bita Mazloom, Selahittin Saytaş
 */
public class CountryNode
{
    private Country data;
    private CountryNode next;

    /**
     * Constructor initializes one instance variable
     * Receives one argument
     * @param data  For the data of the current instance
     */
    public CountryNode(Country data)
    {
        this.data = data;
        this.next = null;
    }

    /**
     * Constructor initializes both instance variables
     * Receives two arguments and connects two nodes
     * such that the current next instance variable
     * points to the other node object
     * @param data  For the data of the current instance
     * @param node  A CountryNode object for another node
     *              we want to connect to.
     */
    public CountryNode(Country data, CountryNode node)
    {
        this.data = data;
        setNext(node);
    }

    /**
     * The equals method returns true if the
     * argument matches the name of an instance
     * @param other A String for the name of the country
     *              we are searching for
     * @return  True if the name of the instance
     *          matches the argument
     */
    public boolean equals(Object other)
    {
        if(other instanceof CountryNode)
        {
            CountryNode current = (CountryNode)other;
            if(current.data.getName().equals(this.data.getName()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * The accessor method returns a Country type data
     * Does not receive any arguments
     * @return  A country type data
     */
    public Country getData()
    {
        return this.data;
    }

    /**
     * The accessor method returns a CountryNode type for the
     * next object. Does not receive any arguments
     * @return  A CountryNode type for the next object
     */
    public CountryNode getNext()
    {
        return this.next;
    }

    /**
     * The mutator method receives one argument of type
     * CountryNode and does not return anything
     * @param node  An argument of type CountryNode
     *              to update the next instance variable
     */
    public void setNext(CountryNode node)
    {
        this.next = node;
    }

    /**
     * The method returns the String representation
     * of the data instance variable
     * @return  A String object representing the
     *          data instance variable
     */
    public String toString()
    {
        String output = "";
        output += this.data;
        return output;
    }
}
