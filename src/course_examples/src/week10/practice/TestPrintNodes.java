package week10.practice;

public class TestPrintNodes
{
    public static void main(String[] args)
    {
        Node<String> one = new Node<>("one");
        Node<String> two = new Node<>("two");
        Node<String> three = new Node<>("three");

        one.setNext(two);
        two.setNext(three);

        // print nodes iteratively
        one.printNodes();

        // print nodes recursively
        one.printNodesRecursive();
    }
}