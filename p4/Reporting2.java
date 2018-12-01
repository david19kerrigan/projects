import java.io.*;
import java.util.*;
public class Reporting2{

    //method to test an array given in a file
    public static void test(String inputFile) throws Exception{
        //read from a file
        BufferedReader br = new BufferedReader(new FileReader(inputFile)); 
        int length = 0;
        String st; 
        ArrayList<Integer> b = new ArrayList<Integer>();
        
        //read each line and increment length and add the corresponding number to the arraylist
        while((st = br.readLine()) != null){    
            length++;
            b.add(Integer.parseInt(st));
        }
        
        //conver to array and then to a primitive array
        Integer[] array2 = b.toArray(new Integer[length]);
        int[] array = Arrays.stream(array2).mapToInt(Integer::intValue).toArray();
        
        //store the heapsort timestamps using heapsort's efficiency
        long[] heapSort = new long[3];
        heapSort[0] = Reporting1.getEfficiency(1, array.clone());
        heapSort[1] = Reporting1.getEfficiency(1, array.clone());
        heapSort[2] = Reporting1.getEfficiency(1, array.clone());
        //sort 
        Reporting1.selectionSort(heapSort);
        //now the middle value is the median
        System.out.println("heap sort: " + heapSort[1]);

        //now do the same for quick and merge sort
        long[] quickSort = new long[3];
        quickSort[0] = Reporting1.getEfficiency(3, array.clone());
        quickSort[1] = Reporting1.getEfficiency(3, array.clone());
        quickSort[2] = Reporting1.getEfficiency(3, array.clone());
        Reporting1.selectionSort(heapSort);
        System.out.println("quick sort: " + quickSort[1]);

        long[] mergeSort = new long[3];
        mergeSort[0] = Reporting1.getEfficiency(2, array.clone());
        mergeSort[1] = Reporting1.getEfficiency(2, array.clone());
        mergeSort[2] = Reporting1.getEfficiency(2, array.clone());
        Reporting1.selectionSort(heapSort);
        System.out.println("merge sort: " + mergeSort[1]);

        //start a writer to write to the heapsort file
        BufferedWriter writer = new BufferedWriter(new FileWriter("dwk55HS.txt"));
        //write the heapsort median efficency
        writer.write("HSdwk55 = " + heapSort[1]);
        int[] a = array.clone();
        
        //now write the sorted array to the file
        a = Sorting.heapSort(a);
        for(int i = 0; i < a.length; i++){
            writer.newLine();
            writer.write(Integer.toString(a[i]));
            
            
        }
        writer.close();

        //and do the same for quick and merge sort
        writer = new BufferedWriter(new FileWriter("dwk55QS.txt"));
        writer.write("QSdwk55 = " + quickSort[1]);
        a = array.clone();
        
        a = Sorting.quickSort(a);
        for(int i = 0; i < a.length; i++){
            writer.newLine();
            writer.write(Integer.toString(a[i]));
            
            
        }
        writer.close();

        writer = new BufferedWriter(new FileWriter("dwk55MS.txt"));
        writer.write("MSdwk55 = " + mergeSort[1]);
        a = array.clone();
        
        a = Sorting.mergeSort(a);
        for(int i = 0; i < a.length; i++){
            writer.newLine();
            writer.write(Integer.toString(a[i]));
            
            
        }
        writer.close();

    }

    public static void main(String args[]) throws Exception{
        //the user enters the input file in cmd
        test(args[0]);
    }

}