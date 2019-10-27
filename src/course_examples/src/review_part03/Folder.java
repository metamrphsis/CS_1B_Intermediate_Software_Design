package review_part03;

public class Folder implements Cloneable
{
    private String name;
    private int[] fileIDs;
    private int permissions;

    // Constructor
    public Folder(String name, int[] fileIDs)
    {
        this.name = name;
        this.fileIDs = fileIDs;
        this.permissions = 0744;
    }

    // Copy Constructor - Deep Copy
    public Folder(Folder folder, int[] fileIDs)
    {
        this.name = folder.name;
        this.permissions = folder.permissions;
        int arraySize = folder.fileIDs.length;
        this.fileIDs = new int[arraySize];

        for(int i = 0; i < fileIDs.length; i++)
        {
            try
            {
                //fileIDs[i] = new int(folder.fileIDs[i]);
                fileIDs[i] = folder.fileIDs[i];
            }
            catch(NullPointerException e)
            {}
        }
    }


//    public Folder(Folder folder)
//    {
//        int arraySize = folder.fileIDs.length;
//        fileIDs = new int[arraySize];
//        for(int i = 0; i < arraySize; i++)
//        {
//            fileIDs[i] = folder.fileIDs[i]; //new int(folder.fileIDs[i]);
//        }
//        return fileIDs;
//    }

    public String toString()
    {
        String result = name + ": " + permissions + "\t";
        for(int i = 0; i < fileIDs.length; i++)
            result += fileIDs[i] + "\t";
        result += "\n";
        return result;
    }

    public static void main(String[] args)
    {
        int[] docs = {111, 112, 115, 201};

        Folder myDocuments = new Folder("Documents", docs);

        System.out.println(myDocuments.toString());
        System.out.println("Done!");
    }
}
