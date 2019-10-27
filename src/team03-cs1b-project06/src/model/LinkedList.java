package model;

import java.util.Iterator;

/**
 * The container class LinkedList contains a collection of
 * generic Node objects
 * @param <T>
 *
 * @author Foothill College, Bita Mazloom, Selahittin Saytaş
 */
public class LinkedList<T> implements Iterable<T>
{
    private Node head;
    //private int size;
    public int size;

    /**
     * Constructor creates an empty list by initializing
     * head to null to indicate the list is empty and by
     * initilazing the size to zero since we have zero
     * elements in the list. Receives no argument
     */
    public LinkedList()
    {
        this.head = null;
        this.size = 0;
    }

    /**
     * The method adds new object to the end of the list.
     * Receives one generic object argument. Does not
     * return anything
     * @param countryToAdd  A generic object as parameter.
     *                      This is the new data to be added
     *                      to the list
     */
    public void add(T countryToAdd)
    {
        Node newNode = new Node(countryToAdd);
        Node current;

        if(this.isEmpty())
        {
            head = newNode;
        }
        else
        {
            current = head;
            Node previousNode = null;

            while(current.getNext() != null)
            {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    /**
     * The method checks in the LinkedList is empty
     * @return  True if the LinkedList is empty
     *          otherwise false
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
     * The method checks whether the list has
     * the data of interest. Receives one argument
     * @param tmpCountry    A generic object for the
     *                      object we want to search for
     * @return  A generic object if the object is found
     *          in the LinkedList. If the object is not found
     *          returns null.
     */
    public T contains(T tmpCountry)
    {
        Node<T> tempCountry = new Node<T>(tmpCountry);
        Node<T> walker = head;

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
     * The accessor method returns the generic object
     * at the requested index. Receives one argument
     * @param selectedIndex An int value for the requested year
     * @return  The specific generic object located at
     *          the requested index
     */
    public T getIndex(int selectedIndex)
    {
        Node<T> walker = head;
        Node<T> newCountry;
        int index = 0;

        if(this.isEmpty())
        {
            return null;
        }
        else if(selectedIndex < 0 || selectedIndex > size)
        {
            throw new IndexOutOfBoundsException();
        }

        while(walker != null && index <= selectedIndex)
        {
            if(index == selectedIndex)
            {
                return walker.getData();
            }
            walker = walker.getNext();
            index++;
        }
        return null;
    }

    /**
     * The method enables the users to add a generic
     * object to a chosen location. Receives two arguments
     * @param object    A generic object for the object we
     *                  want to insert in the list
     * @param indexOfLocation   An int value for the index
     *                          of location we want to add
     *                          our new element
     * @return  Does not return anything
     */
    public void insertAtIndex(T object, int indexOfLocation) // Signature might be T
    {
        Node<T> newNode = new Node<T>(object);
        Node<T> walker;
        int index = 0;

        if(this.isEmpty())
        {
            head = newNode;
        }

        if(indexOfLocation < 0)
        {
            throw new IndexOutOfBoundsException();
        }

        if(indexOfLocation > getSize())
        {
            //newNode = newNode.setNext(null);
            walker = head;
            while(walker.getNext() != null)
            {
                walker = walker.getNext();
            }
            walker.setNext(newNode);
            newNode.setNext(null);
            size++;
        }

        walker = head;

        while(walker.getNext() != null && index <= indexOfLocation)
        {
            if(index == indexOfLocation)
            {
                walker.setNext(newNode);
                newNode.setNext(null);
                size++;
            }
        }
    }

    // THIS MIGHT NOT BE THE RIGHT IMPLEMENTATION
    /**
     * The method traverses LinkedList of Indicators objects
     * @return  An object of type ListIterator
     */
    public Iterator<T> iterator()
    {
        return new ListIterator();
    }


    /**
     * The accessor method indicates the number of
     * elements in the list
     * @return  The instance variable size
     */
    public int getSize()
    {
        return size;
    }

    /**
     * The accessor method indicates the number of
     * elements in the list
     * @return  The instance variable size
     */
    public int size()
    {
        return size;
    }

    /**
     * The method returns a String object containing the text
     * representation every element in the list
     * @return A String object containing the text representation
     *         of every element in the linked list
     */
    public String toString()
    {
        StringBuilder output = new StringBuilder();
//        output.append("[");

        Node<T> walker = head;

        while(walker != null)
        {
            output.append(walker);

//            if(walker.getNext() != null)
//            {
//                output.append(", ");
//            }

            walker = walker.getNext();
        }

//        output.append("]");

        return output.toString();
    }










    /**
     * The purpose of this class is to contain the
     * data of interest. In other words, links the
     * different elements into a singly linked list
     * structure.
     */
    public class Node<T> //Node
    {
        private T data;
        private Node<T> next;
        //private Node next;

        /**
         * Constructor initializes one generic type
         * instance variable. Receives one argument.
         * @param data  A generic type for the data
         *              of the current instance
         */
        public Node(T data)
        {
            this.data = data;
            this.next = null;
        }

        /**
         * Constructor initializes both generic type
         * instance variables. Receives two arguments.
         * Connects the two nodes such that the current
         * next instance points to other Node object
         * @param data  A generic type for the data
         *              of the current instance
         * @param anotherNode   A Node object for another
         *                      node we want to connect to
         */
        public Node(T data, Node<T> anotherNode) //Node<T> anotherNode
        {
            this.data = data;
            setNext(anotherNode);
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
         * @return  A generic type data
         */
        public T getData()
        {
            return this.data;
        }

        /**
         * The accessor method returns a Node type for
         * the next object
         * @return A Node type for the next object
         */
        public Node<T> getNext() //Node getNext
        {
            return this.next;
        }

        /**
         * The mutator method updates the next instance
         * variable. Receives one argument of type generic
         * Node type and does not return anything.
         * @param anotherNode   A Node type of object
         */
        public void setNext(Node<T> anotherNode) //Node anotherNode - protected
        {
            this.next = anotherNode;
        }

        /**
         * The toString() method returns the String
         * representation of the data instance variable.
         * @return  The data instance variable
         */
        public String toString()
        {
            StringBuilder output = new StringBuilder("");
            output.append(this.data);
            //output.append(this.getNext());
            return output.toString();
        }
    }







    /**
     * The purpose of this class is to traverse the
     * collection of objects within the list. The class
     * implements the generic interface Iterator
     */
    private class ListIterator<T> implements Iterator<T> // or ListIterator without parameterized
    {
        private Node<T> current;

        /**
         * The constructor initializes the instance variable
         * current to the beginning of the list. Receives no
         * argument
         */
        public ListIterator()
        {
            this.current = head;
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
            {
                return false;
            }
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
            if(current == null)
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
}
