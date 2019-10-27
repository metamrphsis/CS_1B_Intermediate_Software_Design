package review_part01;

public class BillBoard extends Rectangle
{
    private String message = "hello";
    
    public void printBillBoard()
    {
        System.out.println(message);
        super.printRect();
    }
}
