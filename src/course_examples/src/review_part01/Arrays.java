package review_part01;

public class Arrays
{
    public static void main(String[] args)
    {
        double [][] firstQuarterDeposits = new double[4][];
        double [] jan = {0, 200, 250, 50};
        double [] feb = {25, 50, 300, 75};
        double [] march = {400, 175, 250, 225};
        double [] april = {125, 0, 200, 100};

        firstQuarterDeposits[0] = jan;
        firstQuarterDeposits[1] = feb;
        firstQuarterDeposits[2] = march;
        firstQuarterDeposits[3] = april;

        int month = 3;
        int week = 2;

        double deposit = firstQuarterDeposits[month][week];
        System.out.println("a) deposit month " + (month-1) + " week " + week + " is " + deposit);

        month = 2;
        System.out.println("b) deposit month " + (month-1) + " week " + week + " is " + firstQuarterDeposits[month][week-1]);

        week++;
        System.out.println("c) deposit month " + (month-1) + " week " + week + " is " + firstQuarterDeposits[month][week]);

        double total = firstQuarterDeposits[month-1][week-2] +
                firstQuarterDeposits[month][week];
        System.out.println("d) deposit total is " + total);
    }
}
