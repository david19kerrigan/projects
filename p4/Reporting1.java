import java.util.*; 
import java.io.*;
import java.lang.reflect.Method;
public class Reporting1{
    //basic selection sort for sorting small arrays of timestamps
    //these timestamps are all in nanoseconds so long is fitting
    public static void selectionSort(long[] a){
        for(int i = 0; i < 3; i++){
            long max = a[i];
            int maxIndex = i;
            for(int j = i; j < 3; j++){
                if(a[j] > a[i])
                    max = a[j];
                    maxIndex = j;
            }
            
            long temp2 = a[i];
            a[i] = max;
            a[maxIndex] = temp2;
        }
    }

    //get the mean value of an array of longs
    public static double meanValue(long[] arrayOfSamples){
        //easy formula here
        //total sum / num of elements
        double sum = 0;
        for(int i = 0; i < arrayOfSamples.length; i++){
            sum+=arrayOfSamples[i];
        }
        return (sum)/((double)(arrayOfSamples.length));
    }

    //get variance value of long array with a given mean
    public static double varianceVal(long[] arrayOfSamples, double mean){
        //follow the formula for variance using a loop
        double sum = 0;
        for(int i = 0; i < arrayOfSamples.length; i++){
            sum+=Math.pow(arrayOfSamples[i] - mean,2);
        }
        return (1.0/(double)(arrayOfSamples.length-1)) * sum;
    }

    //make a random array with a given length
    public static int[] generateRandom(int length){
        int[] a = new int[length]; 
        for(int i = 0; i < length; i++) {
            a[i] = (int)(Math.random()*length + 1);
        }
        return a;
    }
    
    //this method takes a sorting type and an input array
    //returns the time it takes to run the sorting method on the given array
    public static long getEfficiency(int type, int[] inputA){
        //clone the input array so we dont modify it
        int[] inputArray = inputA.clone();
        int[] inputArray2 = inputA.clone();
        int[] inputArray3 = inputA.clone();
        long[] heapSortArray = new long[3];
            
        //record starting time
        long start = System.nanoTime();

        if(type == 1)
            Sorting.heapSort(inputArray);
        else if(type == 2)
            Sorting.mergeSort(inputArray);
        else if(type == 3)
            Sorting.quickSort(inputArray);

        //boom we have the timestamp for whichever sorting method we used        
        heapSortArray[0] = System.nanoTime()-start;
        start = System.nanoTime();

        if(type == 1)
            Sorting.heapSort(inputArray2);
        else if(type == 2)
            Sorting.mergeSort(inputArray2);
        else if(type == 3)
            Sorting.quickSort(inputArray2);

        heapSortArray[1] = System.nanoTime()-start;
        start = System.nanoTime();

        if(type == 1)
            Sorting.heapSort(inputArray3);
        else if(type == 2)
            Sorting.mergeSort(inputArray3);
        else if(type == 3)
            Sorting.quickSort(inputArray3);

        heapSortArray[2] = System.nanoTime()-start;
        //we have three entries for the timestamps and now we just sort and get the middle value
        //the middle value is the median in a sorted array 
        selectionSort(heapSortArray);
        return (heapSortArray[1]);
       
    }

    //for part 3 in testing random arrays
    public static void testRandom(int type){
        //we have three arrays for the three sizes
        //fourth and largest size is included in comment but takes ~10 seconds to sort if included
        //the arrays are a list of ten sorting times
        long[] times1 = new long[10];
        long[] times2 = new long[10];
        long[] times3 = new long[10];
        //comment this out for the large array
        long[] times4 = new long[10];

        //fill each array with the correct sorting time
        for(int i = 0; i < 10; i++){
            times1[i] = getEfficiency(type, generateRandom(1000));
        }
        for(int i = 0; i < 10; i++){
            times2[i] = getEfficiency(type, generateRandom(10000));
        }
        for(int i = 0; i < 10; i++){
            times3[i] = getEfficiency(type, generateRandom(100000));
        }
        //comment out for large array
        for(int i = 0; i < 10; i++){
            times4[i] = getEfficiency(type, generateRandom(1000000));
        }

        //now calculate the means and variances and report
        System.out.println("random array size 1000 mean: " + meanValue(times1));
        System.out.println("random arrya size 1000 variance: " + varianceVal(times1, meanValue(times1)));

        System.out.println("random array size 10000 mean: " + meanValue(times2));
        System.out.println("random arrya size 10000 variance: " + varianceVal(times2, meanValue(times2)));
        
        System.out.println("random array size 100000 mean: " + (meanValue(times3)));
        System.out.println("random arrya size 100000 variance: " + varianceVal(times3, meanValue(times3)));

        //comment out for large array
        System.out.println("random array size 1000000 mean: " + meanValue(times4));
        System.out.println("random arrya size 1000000 variance: " + varianceVal(times4, meanValue(times4)));

    }
    

    public static void main(String args[]){
        //make the sorted and reverse arrays;
        int[] sorted1 = new int[1000];
        int[] sorted2 = new int[10000];
        int[] sorted3 = new int[100000];
        int[] sorted4 = new int[1000000];
        int[] reverse1 = new int[1000];
        int[] reverse2 = new int[10000];
        int[] reverse3 = new int[100000];
        int[] reverse4 = new int[1000000];
        
        //fill them with loops
        for(int i = 0; i < sorted1.length; i++){
            sorted1[i] = i+1;
        }
        for(int i = 0; i < sorted2.length; i++){
            sorted2[i] = i+1;
        }
        for(int i = 0; i < sorted3.length; i++){
            sorted3[i] = i+1;
        }
        for(int i = 0; i < sorted4.length; i++){
            sorted4[i] = i+1;
        }
        for(int i = reverse1.length-1; i >= 0; i--){
            reverse1[i] = i+1;
        }
        for(int i = reverse2.length-1; i >= 0; i--){
            reverse2[i] = i+1;
        }
        for(int i = reverse3.length-1; i >= 0; i--){
            reverse3[i] = i+1;
        }
        for(int i = reverse4.length-1; i >= 0; i--){
            reverse4[i] = i+1;
        }

        //now just print out the results using the methods we created
        System.out.println("heapsort");
        System.out.println("reversed array size 1000: " + getEfficiency(1, reverse1));
        System.out.println("reversed array size 10000: " +getEfficiency(1, reverse2));
        System.out.println("reversed array size 100000: " +getEfficiency(1, reverse3));
        System.out.println("reversed array size 1000000: " +getEfficiency(1, reverse4));
        System.out.println("sorted array size 1000: " + getEfficiency(1, sorted1));
        System.out.println("sorted array size 10000: " + getEfficiency(1, sorted2));
        System.out.println("sorted array size 100000: " + getEfficiency(1, sorted3));
        System.out.println("sorted array size 1000000: " + getEfficiency(1, sorted4));
        testRandom(1);

        System.out.println("\nmergesort");
        System.out.println("reversed array size 1000: " + getEfficiency(2, reverse1));
        System.out.println("reversed array size 10000: " +getEfficiency(2, reverse2));
        System.out.println("reversed array size 100000: " +getEfficiency(2, reverse3));
        System.out.println("reversed array size 1000000: " +getEfficiency(2, reverse4));
        System.out.println("sorted array size 1000: " + getEfficiency(2, sorted1));
        System.out.println("sorted array size 10000: " + getEfficiency(2, sorted2));
        System.out.println("sorted array size 100000: " + getEfficiency(2, sorted3));
        System.out.println("sorted array size 1000000: " + getEfficiency(2, sorted4));
        testRandom(2);

        System.out.println("\nquicksort");
        System.out.println("reversed array size 1000: " + getEfficiency(3, reverse1));
        System.out.println("reversed array size 10000: " +getEfficiency(3, reverse2));
        System.out.println("reversed array size 100000: " +getEfficiency(3, reverse3));
        System.out.println("reversed array size 1000000: " +getEfficiency(3, reverse4));
        System.out.println("sorted array size 1000: " + getEfficiency(3, sorted1));
        System.out.println("sorted array size 10000: " + getEfficiency(3, sorted2));
        System.out.println("sorted array size 100000: " + getEfficiency(3, sorted3));
        System.out.println("sorted array size 1000000: " + getEfficiency(3, sorted4));
        testRandom(3);

    }
    
}