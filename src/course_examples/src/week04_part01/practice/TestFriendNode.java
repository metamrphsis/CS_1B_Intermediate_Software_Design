package week04_part01.practice;

import javax.swing.plaf.synth.SynthLookAndFeel;

public class TestFriendNode
{
    public static void main(String[] args)
    {
        FriendNode node01 = new FriendNode("Happy");
        FriendNode node02 = new FriendNode("Sleepy");
        FriendNode node03 = new FriendNode("Grumpy");

        node01.setNext(node02);
        node02.setNext(node03);
        node03.setNext(null);

        System.out.println(node01);
        System.out.println(node02);
        System.out.println(node03);

        //System.out.println(((node01.next).next).next);

        System.out.println("Testing with loop:");
        while(true) //(pointer != null)
        {
            System.out.println(node01.toString());
            node01 = node01.getNext();
            if(node01 == null)
            {
                break;
            }
        }
    }
}
