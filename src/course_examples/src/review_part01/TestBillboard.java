package review_part01;

public class TestBillboard
{
    public static void main(String args[])
    {
        Rectangle r;
        r = new Rectangle();
        r.printRect();
        //r.printBillBoard();
        BillBoard b;
        b = new BillBoard();
        b.printBillBoard();
        b.printRect();
        //b = new Rectangle();
        r = new BillBoard();
        r.printRect();
        //r.printBillBoard();
        ((BillBoard)r).printBillBoard();
    }
}