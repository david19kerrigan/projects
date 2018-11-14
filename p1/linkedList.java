//build linkedlist for use in PBLL
public class linkedList{

    //as usual size and head of list
    private node head;
    private int size;

    
    public void add(int i, node n){
        //if empty add is simple
        if(head == null){
            head = n;
            size++;
            return;
        }
        node current = head;
        int j = i - 1;
        //otherwise find position "i" in the list
        while(j > 0){

            if(current.getNext() != null){
                current = current.getNext();
            }
            j--;
            
        }
        //and now rearrange the pointers to insert i and keep the order intact
        n.setNext(current.getNext());
        current.setNext(n);

        
        size++;
    }

    public int getSize(){
        return size;
    }

    public void decreaseSize(){
        size--;
    }

    public node getHead(){
        return head;
    }
    



}