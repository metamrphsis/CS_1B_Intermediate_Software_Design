import java.util.Iterator;

/**
 * The container class MyLinkedList contains and maintains a singly
 * linked list of objects of a generic type Node objects
 *
 * @author Foothill College, Bita Mazloom, Nitzan Zippel, Selahittin Saytaş
 */
public class MyLinkedList<T> implements Iterable<T>
{
	private Node head;			
	private int size;







	/**
	 * The Node class contains the data of interest.
	 * In other words, it links the different elements
	 * into a singly linked list structure
	 */
	public class Node
	{
	   private T data;
	   private Node next;

		/**
		 * Constructor initializes one generic type
		 * instance variable. Receives one argument.
		 * @param newData	A generic type for the data
		 *                  of the current instance
		 */
	   public Node(T newData)
	   {
		  this.data = newData;
	      this.next = null;
	   }

		/**
		 * Constructor initializes both generic type
		 * instance variables and connects the two
		 * nodes such that the current next instance
		 * points to other Node object. Receives two
		 * arguments.
		 * @param newData	A generic type for the data
		 *                  of the current instance
		 * @param nextNode	A Node object for another
		 *                  node we want to connect to
		 */
	   public Node(T newData, Node nextNode)
	   {
		    this.data = newData;
		    this.next = nextNode;
	   }

		/**
		 * The equals method returns true if the
		 * argument matches the data of an instance
		 * @param other  String for the data of the country
		 *               we are searching for
		 * @return  True if the name of the instance
		 *          matches the argument
		 */
		public boolean equals(Object other)
		{
			if(other.equals(this.getData()))
			{
				return true;
			}
			return false;
		}

		/**
		 * The accessor method returns a generic type data
		 * Does not receive an argument
		 * @return	A generic type data
		 */
	   public T getData()
	   {
		   return this.data;
	   }

		/**
		 * The accessor method returns a Node type
		 * for the next object
		 * @return	A Node type for the next object
		 */
	   public Node getNext()
	   {
		   return this.next;
	   }

		/**
		 * The mutator method updates the next instance
		 * variable. Receives one argument of type generic
		 * Node type and does not return anything.
		 * @param nextNode	A Node type of object
		 */
		protected void setNext(Node nextNode)
	   {
		   this.next = nextNode;
	   }

		/**
		 * The toString() method returns the String
		 * representation of the data instance variable.
		 * @return  The data instance variable
		 */
	   public String toString()
	   {
	      return this.data.toString();
	   }
	}







	/**
	 * The purpose of this class is to traverse the
	 * collection of objects within the list. The class
	 * implements the generic interface Iterator
	 */
	private class MyListIterator implements Iterator<T>
	{
		/**
		 * An object of type Node
		 */
		private Node current;

		/**
		 * The constructor initializes the instance variable
		 * current to the beginning of the list. Receives no
		 * argument
		 */
		public MyListIterator()
		{
			current = head;
		}

		/**
		 * The method does not receive any arguments and returns
		 * a boolean value. If the list has another element in this
		 * method should return true
		 * @return  A boolean value if the list has an element in it
		 */
		public boolean hasNext() 
		{
			// check if the next node is valid
			// if node is invalid, return false
			if(current == null)
				return false;
			// otherwise we haven't reached the end of the list
			return true;
		}

		/**
		 * The method does not receive any arguments and saves and
		 * returns the data that current object is pointing to. Then
		 * moves current object to the next available reference
		 * @return  The data that current object is pointing to
		 */
		public T next() 
		{	
			if (current == null)
			{
				throw new java.util.NoSuchElementException();
			}

			// if we're here, then we're looking at a valid current node
			// so grab the data portion, to give to the caller
			T data = current.getData();
			// move in preparation for the next time
			current = current.getNext();
			return data;  
		}

		/**
		 * The method does not receive any arguments and does not return anything.
		 * A call to this method throws an UnsupportedOperationException()
		 * to indicate that the operation is not supported
		 */
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}







	/**
	 * Constructor creates an empty list by initializing
	 * head to null to indicate the list is empty and by
	 * initilazing the size to zero since we have zero
	 * elements in the list. Receives no argument
	 */
	public MyLinkedList()
	{
		this.head = null;
		this.size = 0;
	}

	/**
	 * Check's if head is pointing to any node
	 * @return true if head is a valid element. Otherwise, false.
	 */
	public boolean isEmpty()
	{
		if (this.head == null)	// alternatively, check this.length == 0;
			return true;
		return false;
	}

	/**
	 * Adds an element to the front of the list.
	 * @param element  Element to be added.
	 */
	public void addToFront(T element)
	{
		Node newNode = new Node(element);
		if (this.isEmpty() == false)
		{
			newNode.setNext(head);
		}

		head = newNode;
		this.size++;
	}

	/**
	 * The Itreator method traverses LinkedList of Indicators objects
	 * and creaters a new object of MyListIterator
	 * @return  An object of type ListIterator
	 */
	@Override
	public Iterator<T> iterator()
	{
		return new MyListIterator();
	}

	/**
	 * 	Returns a string containing every element in List by
	 * 	calling the toString() method of each element.
	 */
	public void printList()
	{
		Node current = head;
		while(current != null)
		{
			System.out.print(" -> " + current);
			current = current.getNext();
		}
		System.out.println("null");
	}
}
