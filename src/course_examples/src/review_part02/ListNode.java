package review_part02;

public class ListNode
{
    private String node;
    private ListNode next;

    public ListNode(String node)
    {
        this.node = node;
        this.next = null;
    }

    public ListNode(String node, String anotherNode)
    {
        this.node = node;
        this.next = anotherNode;
    }

    public ListNode(String node, ListNode anotherNode)
    {
        this.node = node;
        this.next = anotherNode;
    }

    public boolean equals(Object other)
    {
        if(other instanceof ListNode)
        {
            ListNode current = (ListNode)other;
            if(current.node.equals(this.node))
            {
                return true;
            }
        }
        return false;
    }

    public void setNext(ListNode node)
    {
        this.next = node;
    }
}
