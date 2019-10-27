package review_part01;

public class ReviewException
{
    public static int foo(int x) throws Exception
    {
        System.out.println ("foo started with " + x);
        int temp = bar(x);
        System.out.println ("foo returning " + temp);
        return temp;
    }

    public static int bar(int y) throws Exception
    {
        System.out.println ("bar started with " + y);
        if(y > 0)
            throw new Exception ("just a test");
        System.out.println ("when is this executed?");
        return y;
    }

    public static void main(String args[])
    {
        int x;
        try
        {
            x = foo(10);
        }
        catch(Exception e)
        {
            System.out.println ("Caught an exception: " + e);
            x = 99;
        }
        System.out.println("x = " + x);
    }
}
