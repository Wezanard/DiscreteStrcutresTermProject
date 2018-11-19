package discrete.term.project;
import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.text.*;
import java.util.*;
public class DiscreteTermProject
{       
    //Instance Variables
    static FileOutputStream fd;
    static PrintWriter pw;
    static String DiscreteTermFile = ".\\DiscreteLog.txt";
    static final boolean APPENDING = true;
    
    //Add Date Information
    static DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    static Date date = new Date();
    static boolean error = false;
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        Logger();
        System.out.println ("Size of array?");
        int size = scan.nextInt();
        int [] array = new int[size];
        Log("Size of Array is " + size);
        System.out.println("Sort how many times?");
        int times = scan.nextInt();
        Log("Iteration is " + times);
        printMenu();
        System.out.println("Type in a choice ");
        int choice = scan.nextInt();
        dispatch (choice, array, size, scan, times);
    }
    public static void printMenu()
    {
        System.out.println("--MENU--");
        Log("--Menu--");
        //System.out.println("0: Exit");
        //Log("0: Exit");
        System.out.println("1: Slection Sort Ascending Sequence.");
        Log("1: Slection Sort Ascending Sequence.");
        System.out.println("2: Bubble Sort Ascending Sequence.");
        Log("2: Bubble Sort Descending Sequence.");
    }
    public static void dispatch(int choice, int[] array, int size, Scanner scan, int times)
   {       
        double Final = 0;
        double division=times;
        switch(choice)
        {
            case 1:
                Log("SelectionAscendingSorting");
                do
                {
                    fillRandom(array, size);
                    logArray(array, size);
                    double start = System.currentTimeMillis();
                    sortSelectionAscending(array, size);
                    double end = System.currentTimeMillis();
                    logArray(array, size);
                    double result=end-start;        
                    Final=Final+result;
                    times--;
                    Log("Array sorted in " + result + " Milleseconds");
                    
                }    
                while(times!=0);
                double Average=Final/division;
                Log("Sorted all in " + Final + " Milleseconds");
                Log("Average time to sort " + Average + " Milleseconds");                       
                break;
            case 2:
                Log("BubbleAscendingSorting");
                do
                {
                    fillRandom(array, size);
                    logArray(array, size);
                    double start = System.currentTimeMillis();
                    sortBubbleAscending(array, size);
                    double end = System.currentTimeMillis();
                    logArray(array, size);
                    double result=end-start;
                    Final=Final+result;
                    times--;
                    Log("Array sorted in " + result + " Milleseconds");
                }
                while(times!=0);
                Average=Final/division;
                Log("Sorted all in " + Final + " Milleseconds");
                Log("Average time to sort " + Average + " Milleseconds");
                break;	     
        }
    }
    public static void sortSelectionAscending(int[]list, int size)
    {
        int min, temp;
        for (int index=0; index<size-1; index++)
        {
            min = index;
            for(int scan = index + 1; scan<size; scan++)
                if(list[scan]<list[min])
                min = scan;
                temp = list[min];
                list[min] = list[index];
                list[index] = temp;
        }
        Log("Array Sorted in Ascending Order!");
    }
    public static void sortBubbleAscending(int[]x, int size)
    {
        boolean sorted;
        int temp;
        int numpairs = size-1;
        do
        {
            sorted = true;
            for(int i = 0; i<numpairs; ++i)
            {
                if (x[i]>x[i+1])
                {
                    temp = x[i];
                    x[i] = x[i+1];
                    x[i+1] = temp;
                    sorted = false;
                }
            }   
            numpairs--;
        }
        while(sorted==false);
        Log("Array Sorted in Ascending Order!");
      
    }
    public static void fillRandom(int[] blarg, int size)
    {
        Random number = new Random();
        for(int i=0; i<size; i++)
        blarg[i]=number.nextInt(100)+1;
        Log("Array Filled!");
    }
    public static void logArray(int[]y, int size)
    {
        
        for(int i=0; i<size; ++i)
        Log(i + "--" + y[i]);
    }
    public static void Logger()
    {
        try
        {
            File f = new File(DiscreteTermFile);
            if(!f.exists())
                f.createNewFile();
        }
        catch (Exception e)
        {
            System.out.println("Permission?");
        }
    }
    static void Log(String s)
    {
        try
        {
            fd = new FileOutputStream(DiscreteTermFile, APPENDING);
            pw = new PrintWriter(fd);
            if (error == true)
            {
                pw.println(dateFormat.format(date) + " - Error: " + s + "\n");
                //pw.println(dateFormat.format(date));
            }
            else
            {
                pw.println(dateFormat.format(date) + " - "+ s + "\n");
                //pw.println(dateFormat.format(date));
            }
            
        }
        catch (Exception e)
        {
            System.out.println("File Not Found");
        }
        finally
        {
            pw.close();
        }
    }
    static void Debug(String s)
    {
        try
        {
            fd = new FileOutputStream(DiscreteTermFile, APPENDING);
            pw = new PrintWriter(fd);
            pw.println("Debug Event: " + s + "\n");
            pw.println(dateFormat.format(date));
            
        }
        catch (Exception e)
        {
            System.out.println("File Not Found");
        }
        finally
        {
            pw.close();
        }
    }
    public static void Error(Exception s)
    {
        error = true;
        Log(s.getMessage());
        error = false;
    }
}
