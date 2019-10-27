package review_part02;

import java.awt.Point;

public class TestLinkedList
{
    public static void main(String[] args)
    {
        SimpleList myList;
        myList = new SimpleList();
        myList.addAtEnd(new Point (3,4));
        myList.addAtEnd(new Point (5,6));
        myList.addAtFront(new Point (1,2));
        System.out.println(myList);
    }
}
