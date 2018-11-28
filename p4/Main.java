import java.util.*; 
public class Main{



    
    public static void main(String[] args){
        int[] a = Sorting.quickSort(new int[]{1,5,7,3,3,4,7,4,5,2,9,45,21});
        for(int i = 0; i < a.length; i++){
            System.out.println(a[i]);
        }

        a = Sorting.heapSort(new int[]{1,5,7,3,3,4,7,4,5,2,9,45,21});
        for(int i = 0; i < a.length; i++){
            System.out.println(a[i]);
        }

        a = Sorting.mergeSort(new int[]{1,5,7,3,3,4,7,4,5,2,9,45,21});
        for(int i = 0; i < a.length; i++){
            System.out.println(a[i]);
        }
        


    }

}