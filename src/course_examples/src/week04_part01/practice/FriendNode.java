package week04_part01.practice;

public class FriendNode
{
    private String name;
    private FriendNode next;

    public FriendNode(String name)
    {
        this.name = name;
        this.next = null;
    }

    public FriendNode(String name, FriendNode node)
    {
        this.name = name;
        this.next = node;
    }

    public FriendNode getNext()
    {
        return next;
    }

    public void setNext(FriendNode node)
    {
        this.next = node;
    }

    public String toString()
    {
        String output = "";
        output = this.name;
        return output;
    }
}
