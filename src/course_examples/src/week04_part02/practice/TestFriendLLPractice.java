package week04_part02.practice;

public class TestFriendLLPractice 
{

	public static void main(String[] args) 
	{	
		FriendLinkedListPractice myfriends = new FriendLinkedListPractice();
		
		myfriends.addInFront("alice");
		myfriends.addInFront("peter");
		myfriends.addInFront("rumple");		

		// PRACTICE: complete implementation of printFirstNode()
		myfriends.printFirstNode();
	
		// PRACTICE: complete implementation of printList()		
		System.out.println("\nList after adding " + myfriends.getSize());
		myfriends.printList();
		
		// PRACTICE: complete implementation of removeFirstNode()
		String forgottenFriend = myfriends.removeFirstNode();
		System.out.println("Name of forgotten friend is: " + forgottenFriend);
		System.out.println("\nList after removing first node ");
		myfriends.printList();
	}

}
