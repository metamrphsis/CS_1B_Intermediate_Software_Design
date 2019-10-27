package week06_part02.practice;

public class Node<T>
{
    // Attributes should be generic
    // Everywhere we say node we have to put a parameter
    // Instance of a particular data type use a parameter

    private T data;
    private Node<T> next; //Parameterized node

    public Node(T data)
    {
        this.data = data;
        this.next = null;
    }

    public Node(T data, Node<T> node)
    {
        this.data = data;
        this.next = node;
    }

    public boolean equals(Object other)
    {
        //Node<T> current = other;
        if(other.equals(this.data))
        {
            return true;
        }
        return false;
    }

    public boolean isEmpty()
    {
        if(this.next == null)
        {
            return true;
        }
        return false;
    }

    public void setNext(Node<T> node)
    {
        this.next = node;
    }

    public Node<T> getNext()
    {
        return this.next;
    }

    public T getData()
    {
        return this.data;
    }

    public String toString()
    {
        StringBuilder output = new StringBuilder();
        output.append(" " + this.data + " ");
        return output.toString();
    }
}
