package review_part02;

public class SimpleList
{
    private ListNode first;

    private int length;

    public SimpleList ()
    {
        first = null;
        this.length = 0;
    }

    public void insertAtBeginning(Object d)
    {
        ListNode temp;
        temp = new ListNode(d, first);
        first = temp;
    }
    // other methods…

    public boolean isEmpty()
    {
        if(this.first == null)
        {
            return true;
        }
        return false;
    }

    public void addFirstNode(String node)
    {
        if(this.isEmpty())
        {
            ListNode current = new ListNode(node);
            first = current;
            this.length++;
        }
    }

    public void addAtFront(String node)
    {
        ListNode current = new ListNode(node);
        if(this.isEmpty())
        {
            this.first = current;
            this.length++;
            return;
        }
        current.setNext(first);
        first = current;
        this.length++;
    }

    public void addAtEnd(String node)
    {
        ListNode current = new ListNode(node);
        if(this.first == null)
        {
            current.setNext(node);
        }
    }
}
