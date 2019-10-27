package week01_part01;

import java.util.Scanner;

public class Testing2DArrays
{
    public static void main(String [] args)
    {
        Scanner keyboard = new Scanner(System.in);
        double[][] subscriptionTable;
        subscriptionTable = new double[3][];

        // initializes each row with user requested size
        for(int subscriberNum = 0; subscriberNum < subscriptionTable.length; subscriberNum++) // The length of the row
        {
            // prompts the user
            System.out.printf("Enter the number of years you have data for country number %d: \n",subscriberNum + 1); // subscriberNum = 0 + 1
            int userInput = keyboard.nextInt();
            if(userInput > 10)
            {
                // initialize the current row based on user input
                subscriptionTable[subscriberNum] = new double[userInput];
            }
            // if the requested size is too small, sets the current row a default size of 10.
            else
            {
                System.out.printf("Increasing table size to 10 years for country number %d:\n", subscriberNum + 1);
                subscriptionTable[subscriberNum] = new double[10];
            }
        }
    }
}
