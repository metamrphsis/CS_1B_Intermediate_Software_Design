package week08_part01.practice;

import java.util.Comparator;

public class EmployeeNameComparator implements Comparator<Employee>
{
    public int compare(Employee nameFirst, Employee nameSecond)
    {
        if(nameFirst.getName().compareTo(nameSecond.getName()) < 0)
        {
            return -1;
        }

        if(nameFirst.getName().compareTo(nameSecond.getName()) > 0)
        {
            return 1;
        }

        return 0;
    }
}
