package week04_part01.solution;

public class Problem01 
{
	public static void main(String[] args) 
	{	
		// create a node for each of our friends ----------------------
		FriendNode happy = new FriendNode("happy");
		FriendNode sleepy = new FriendNode("sleepy");
		FriendNode grumpy = new FriendNode("grumpy");

		// link the nodes together ------------------------------------
		happy.setNext(sleepy);
		sleepy.setNext(grumpy);
		grumpy.setNext(null);

		// print the nodes using separate statements ------------------
		System.out.println("First version: ");
		System.out.println(happy);
		System.out.println(sleepy);
		System.out.println(grumpy);

		System.out.println("\nSecond version: ");
		// Since we want to avoid changing where the happy reference is pointing to,
		// we create a new refernce called walker, which *starts out* by pointing to happy.
		FriendNode walker;

		// point walker to happy
		walker = happy;

		// ----------------------------------------------------------------
		// There are multiple ways to print the linked nodes
		// Below shows three different approaches.
		// ----------------------------------------------------------------
		
		// option 1 ----------------------------------------------------------------
		// while walker has reached the end (i.e. no node)
		System.out.println("\nOPTION 1: ");
		while(walker != null )
		{
			// do printing at each node
			System.out.println(walker);
			// move walker to the next node
			walker = walker.getNext();
		}

		// reset point walker to happy
		walker = happy;

		// option 2 ----------------------------------------------------------------
		// check to see if walker is *not* at the end of the list
		System.out.println("\nOPTION 2: ");
		while(walker.getNext() != null)
		{
			// if not, then print the current node
			System.out.println(walker);
			// move walker to the next node
			walker = walker.getNext();
		}

		// QUESTION: Will the above loop ever print the last element?
		// Suggestion: Draw the list and trace it out by hand.

		// Since we still haven't printed the last element,
		// then we need one *extra* statement to make sure to we print the last element
		// which walker is now pointing to:
		System.out.println(walker);



		// QUESTION: How would you rewrite the condition of the previous while loop,
		// so that we avoid that extra println(walker) statement?
		System.out.println("\nThird version: ");

		// reset walker to point to happy 
		walker = happy;
		// check to see if walker is *not* null
		while(walker != null)
		{
			// walker is not null, so it is safe to dereference walker
			System.out.println(walker.toString());
			// move walker to the next node
			walker = walker.getNext();
		}

		// reset walker to point to happy
		walker = happy;

		System.out.println("\nOPTION 3: ");
		// option 3 ----------------------------------------------------------------
		// while forever
		while(true)
		{
			System.out.println(walker);
			if(walker.isLastNode())
			{
				// exit the loop
				break;
			}
			// move walker to the next node
			walker = walker.getNext();
		}
		System.out.println("\nDone.");
	}
}
