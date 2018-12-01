public class Sorting{

    
    public static int[] mergeSort(int[] a){
        return mergeSort(a, 0, a.length -1);
    }

    //helper method to combine two arrays taken from a given array
    public static void merge(int[] a,int left, int mid, int right){
        //find the lengths of the two arrays
        int leftLength = mid - left + 1;
        int rightLength = right - mid;

        //two temp arrays to store the arrays taken from the main array
        int[] L = new int[leftLength];
        int[] R = new int[rightLength];

        //transfer the left and right arrays
        for(int i = 0; i < leftLength; i++){
            L[i] = a[left + i];
        }
        for(int i = 0; i < rightLength; i++){
            R[i] = a[mid + 1 + i];
        }
        int i = 0;
        int j = 0;
        int k = left;
        
        //now iterate through each array and add the greater number back to the main array
        while(i < leftLength && j < rightLength){

            if(L[i] <= R[j]){
                a[k++] = L[i++];               
            }
            else{
                a[k++] = R[j++];
            }
            
        }

        //add any remaining integers
        while(i < leftLength)
            a[k++] = L[i++];

        while(j < rightLength)
            a[k++] = R[j++];

    }

    //this method takes an array and a left and right bound
    public static int[] mergeSort(int[] a, int left, int right){
        //this statement is true until the array is sorted
        if(left < right){
            int mid = (left+right)/2;
            //merge sort both halves
            mergeSort(a, left, mid);
            mergeSort(a, mid+1, right);
            //merge them
            merge(a, left, mid, right);
        }
        //now that we're sorted return the array
        return a;

    }

    //keep track of the number of items in an array where not all indexes are filled
    //this is for heapsort
    private static int numItems;

    //remove top value from heap
    public static int removeMax(int[] a){
        //we want to scratch this number
        int toRemove = a[0];
        //so now the top value is taken from the very last element
        a[0] = a[numItems-1];
        
        //decrease numItems 
        numItems--;
        //now just sift down the top value to place the real max at the top
        siftDown(a, 0);

        return toRemove;
    }

    //build a heap in place using the algorithm from class
    public static void buildHeap(int[] a){           
        //sift down each node starting down -> up save for the leaf nodes 
        for(int i = (numItems -2)/2; i >= 0; i--)
        siftDown(a, i);        
    }

    //sift down a node in a heap a with index i
    //algorithm from class
    public static void siftDown(int[] a, int i) { // Input: the node to sift
        //value to sift down
        Integer toSift = a[i];
        //index of value to sift down
        int parent = i;
        //index of left child from parent node
        int child = 2 * parent + 1;
        //while the child node is in range of our heap
        while (child < numItems) {// If the right child is bigger than the left one, use the right child instead.
            //if child+1 (the right child) is in range of our heap and its value is greater than the left child
            if (child +1 < numItems  && a[child] < a[child+1])
                //then we use the right child instead of the left child
                child = child + 1; 
            //if we are at a value that is greater than the child's value
            if (toSift >= a[child])
                break; //exit the loop the sift is complete
            //go down a level via the child with the greater value
            a[parent] = a[child];
            a[child] = toSift;
            //readjust parnet and child values to represent this shift down
            parent = child;
            child = 2 * parent + 1;
        }
        //assign the resulting index to the initial value wanted to sift
        a[parent] = toSift;
    }

    //the actual heapsort method
    public static int[] heapSort(int[] a){
        //the num of items starts at the length of the array
        numItems = a.length;
        //build hte heap first
        buildHeap(a);
        
        int[] heap = a; 
        
        int[] answer = new int[a.length];
        //just remove the top value and append to the end of the array repeatedly
        //the result is sorted
        for(int i = a.length - 1; i >= 0; i--){
            answer[i] = removeMax(heap);
        }
        return answer;
    }

    public static int[] quickSort(int[] a){
        QSort(a, 0, a.length - 1);
        return a;
    }

    //The Qsort method takes a start and end value to partition
    public static void QSort(int[] a, int start, int end){
        //if start index surpasses end index the sort is completed
        if(start>=end){return;}
        //split is the value of the index right below the sorted element
        int split = partition(a, start, end);
        //sort to the left and right of the sorted index
        QSort(a, start, split);
        QSort(a, split+1, end);
        
    }

    //takes an array and a start and end index
    //this function performs one iteration of QSort and returns the single sorted element (its index specifically)
    static int partition(int[] a, int start, int end){
        //we use the middle element
        int pivot = a[(start+end)/2];
        //change these values because of the do-while blocks
        //i and j are incremented before we do any actions with them
        int i = start-1;
        int j = end + 1;
        //infinite loop only exited with a return
        while(true){
            //find a value which is lower than the pivot value
            do{
                i++;
            } while (a[i] < pivot);

            //find a value which is greater than the pivot value
            do{
                j--;
            } while (a[j] > pivot);

            //if they are on the correct sides of pivot
            //i and j have not surpassed the pivot
            if(i < j){
                //swap them
                int toSwap1 = a[i];
                int toSwap2 = a[j];
                a[j] = toSwap1;
                a[i] = toSwap2;

            }
            //once i and j have surpassed the pivot return the index right marking the end of the left partition
            else{return j;}
        }
    }


}