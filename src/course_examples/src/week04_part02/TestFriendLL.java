package week04_part02;

public class TestFriendLL 
{
	public static void main(String[] args) 
	{
		// create an object to manage our list of nodes
		FriendLinkedList mylist = new FriendLinkedList();
		
		// add our friend's name to the list
		// add a friend whose name is "alice"
		// add a friend whose name is "rumple"
		// add a friend whose name is "swan"
		mylist.addInFront("alice");
		mylist.addInFront("rumple");
		mylist.addInFront("swan");

		System.out.println(mylist.toString());


		FriendNode example = mylist.getNodeAtIndex(0);
		System.out.println("Node at index 0 : " + example);

		example = mylist.getNodeAtIndex(1);
		System.out.println("Node at index 1 : " + example);

		example = mylist.getNodeAtIndex(2);
		System.out.println("Node at index 2 : " + example);

	}

}
