import java.util.*; 
import java.io.*;
import java.lang.reflect.Method;
public class Testing1{

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


    public static double meanValue(long[] arrayOfSamples){
        double sum = 0;
        for(int i = 0; i < arrayOfSamples.length; i++){
            sum+=arrayOfSamples[i];
        }
        return (sum)/((double)(arrayOfSamples.length));
    }

    public static double varianceVal(long[] arrayOfSamples, double mean){
        double sum = 0;
        for(int i = 0; i < arrayOfSamples.length; i++){
            sum+=Math.pow(arrayOfSamples[i] - mean,2);
        }
        return (1.0/(double)(arrayOfSamples.length-1)) * sum;
    }

    public static int[] generateRandom(int length){
        int[] a = new int[length]; 
        for(int i = 0; i < length; i++) {
            a[i] = (int)(Math.random()*length + 1);
        }
        return a;
    }

    public static long getEfficiency(int type, int[] inputArray){
        int[] inputArray2 = inputArray.clone();
        int[] inputArray3 = inputArray.clone();
        long[] heapSortArray = new long[3];
              
        long start = System.nanoTime();

        if(type == 1)
            Sorting.heapSort(inputArray);
        else if(type == 2)
            Sorting.mergeSort(inputArray);
        else if(type == 3)
            Sorting.quickSort(inputArray);
        
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
        selectionSort(heapSortArray);
        return (heapSortArray[1]);
       
    }

    public static void testRandom(int type){
        long[] times1 = new long[10];
        long[] times2 = new long[10];
        long[] times3 = new long[10];
        for(int i = 0; i < 10; i++){
            times1[i] = getEfficiency(type, generateRandom(1000));
        }
        for(int i = 0; i < 10; i++){
            times2[i] = getEfficiency(type, generateRandom(10000));
        }
        for(int i = 0; i < 10; i++){
            times3[i] = getEfficiency(type, generateRandom(100000));
        }

        System.out.println(meanValue(times1));
        System.out.println(varianceVal(times1, meanValue(times1)));

        System.out.println(meanValue(times2));
        System.out.println(varianceVal(times2, meanValue(times2)));
        
        System.out.println(meanValue(times3));
        System.out.println(varianceVal(times3, meanValue(times3)));

    }
    

    public static void main(String args[]){
        int[] sorted1 = new int[1000];
        int[] sorted2 = new int[10000];
        int[] sorted3 = new int[100000];
        int[] sorted4 = new int[1000000];
        int[] reverse1 = new int[1000];
        int[] reverse2 = new int[10000];
        int[] reverse3 = new int[100000];
        int[] reverse4 = new int[1000000];
        


    
    

        

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
        System.out.println("heapsort reversed array ascending size");
        System.out.println(getEfficiency(1, reverse1));
        System.out.println(getEfficiency(1, reverse2));
        System.out.println(getEfficiency(1, reverse3));
        System.out.println(getEfficiency(1, reverse4));
        System.out.println("sorted array ascending size");
        System.out.println(getEfficiency(1, sorted1));
        System.out.println(getEfficiency(1, sorted2));
        System.out.println(getEfficiency(1, sorted3));
        System.out.println(getEfficiency(1, sorted4));
        System.out.println("random array ascendng size");
        testRandom(1);

        System.out.println("\nmergesort reversed array ascending size");
        System.out.println(getEfficiency(2, reverse1));
        System.out.println(getEfficiency(2, reverse2));
        System.out.println(getEfficiency(2, reverse3));
        System.out.println(getEfficiency(2, reverse4));
        System.out.println("sorted array ascending size");
        System.out.println(getEfficiency(2, sorted1));
        System.out.println(getEfficiency(2, sorted2));
        System.out.println(getEfficiency(2, sorted3));
        System.out.println(getEfficiency(2, sorted4));
        System.out.println("random array ascendng size");
        testRandom(2);

        System.out.println("\nquicksort reversed array ascending size");
        System.out.println(getEfficiency(3, reverse1));
        System.out.println(getEfficiency(3, reverse2));
        System.out.println(getEfficiency(3, reverse3));
        System.out.println(getEfficiency(3, reverse4));
        System.out.println("sorted array ascending size");
        System.out.println(getEfficiency(3, sorted1));
        System.out.println(getEfficiency(3, sorted2));
        System.out.println(getEfficiency(3, sorted3));
        System.out.println(getEfficiency(3, sorted4));
        System.out.println("random array ascendng size");
        testRandom(3);

    }
    
}