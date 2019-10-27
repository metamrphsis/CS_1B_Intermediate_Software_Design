package week10.practice;

public class ProblemOne
{
    public int semifactorial(int n) // Can be static function/method
    {
        int number;
        // Base case
        if(n == 0 || n == 1)
        {
            return 1;
        }

        number = n * semifactorial(n - 2);
        return number;
    }

    public static void main(String[] args)
    {
        ProblemOne p1 = new ProblemOne();
        int evenNumber = p1.semifactorial(6);
        int oddNumber = p1.semifactorial(5);

        System.out.println(evenNumber);
        System.out.println(oddNumber);
    }
}
