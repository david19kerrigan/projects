public class Sorting{

    public static int[] mergeSort(int[]a ){
        return mergeSort(a, 0, a.length -1);
    }

    public static void merge(int[] a,int left, int mid, int right){
        int leftLength = mid - left + 1;
        int rightLength = right - mid;

        int[] L = new int[leftLength];
        int[] R = new int[rightLength];

        for(int i = 0; i < leftLength; i++){
            L[i] = a[left + i];
        }
        for(int i = 0; i < rightLength; i++){
            R[i] = a[mid + 1 + i];
        }
        int i = 0;
        int j = 0;
        int k = left;
        
        while(i < leftLength && j < rightLength){

            if(L[i] <= R[j]){
                a[k++] = L[i++];               
            }
            else{
                a[k++] = R[j++];
            }
            
        }

        while(i < leftLength)
            a[k++] = L[i++];

        while(j < rightLength)
            a[k++] = R[j++];

    }

    public static int[] mergeSort(int[] a, int left, int right){
        if(left < right){
            int mid = (left+right)/2;

            mergeSort(a, left, mid);
            mergeSort(a, mid+1, right);

            merge(a, left, mid, right);
        }
        return a;

    }

    private static int numItems;

    public static int removeMax(int[] a){
        int toRemove = a[0];
        a[0] = a[numItems-1];
        
        numItems--;
        siftDown(a, 0);
        return toRemove;
    }

    public static void buildHeap(int[] a){            
        for(int i = (numItems -2)/2; i >= 0; i--)
        siftDown(a, i);        
    }

    public static void siftDown(int[] a, int i) { // Input: the node to sift
        Integer toSift = a[i];
        int parent = i;
        int child = 2 * parent + 1; // Child to compare with; start with left child
        while (child < numItems) {// If the right child is bigger than the left one, use the right child instead.
            if (child +1 < numItems  && // if the right child exists
            a[child] < a[child+1])
            child = child + 1; // take the right child
            if (toSift >= a[child])
            break; // we’re done
            // Sift down one level in the tree.
            a[parent] = a[child];
            a[child] = toSift;
            parent = child;
            child = 2 * parent + 1;
        }
        a[parent] = toSift;
    }

    public static int[] heapSort(int[] a){
        numItems = a.length;
 
        buildHeap(a);
  
        int[] heap = a; 
        
        int[] answer = new int[a.length];
        for(int i = a.length - 1; i >= 0; i--){
            answer[i] = removeMax(heap);
        }
        return answer;
    }

    public static int[] quickSort(int[] a){
        QSort(a, 0, a.length - 1);
        return a;
    }
    
    public static void QSort(int[] a, int start, int end){
        if(start>=end){return;}
        int split = partition(a, start, end);
        QSort(a, start, split);
        QSort(a, split+1, end);
        
    }

    static int partition(int[] a, int start, int end){
        int pivot = a[(start+end)/2];
        int i = start-1;
        int j = end + 1;
        while(true){
            do{
                i++;
            } while (a[i] < pivot);
            do{
                j--;
            } while (a[j] > pivot);

            if(i < j){
                int toSwap1 = a[i];
                int toSwap2 = a[j];
                a[j] = toSwap1;
                a[i] = toSwap2;

            }
            else{return j;}
        }
    }


}