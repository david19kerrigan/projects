public class PBLinkedList extends PBList{
    //runs on our homemade linkedlist
    linkedList list = new linkedList();
    
    public int size(){
        return list.getSize();

    }
    
    //send the operation to the method in linkedlist
    //O(n) linear since there is one while loop that can go thru all elements in the list
    public void addPerson(int i, Person p){
        node n = new node();
        n.setContent(p);
        list.add(i, n);
    }

    //O(n) since there is one while loop that can go through every element of the list
    public boolean addNumber(String ID, String phoneNum){
        node current = list.getHead();
        
        //go through the list
        while(current.getNext()!=null){
            //find the matching ID
            if(current.getContent().getID().equals(ID)){
                //add the number
                current.getContent().addNum(phoneNum);
                return true;
            }
            //iterate
            current = current.getNext();
        }
        return false;
    }

    //O(n) one for loop that goes through the list
    public boolean delete(int i){
        node current = list.getHead();  
        node prev = list.getHead();
        //find position i by increasing j and calling next() until j is = to i  
        for(int j = 1; j < i; j++){
            
            if(j == 0){
                prev = list.getHead();
            }
            //keep track of last element
            else{
                prev = current;
            }
            if(current == null){
                return false;
            }
            //iterate
            current = current.getNext();
        }
        list.decreaseSize();
        //skip over the element we want to delete
        //the prev element points to the elements next 
        prev.setNext(current.getNext());
        return true;
        
        
    }
    //O(n) since there is one for loop that goes through all elements in the list
    public Person search(int i){
        node current = list.getHead();
        
        if(i > list.getSize()){
            return null;
        }
        //go through the list until we find position i
        for(int j = 0; j < i - 1; j++){
            current = current.getNext();
        }
        return current.getContent();
    }

    //O(n^2) becaues we have two for loops.  Each may iterate through the length of the list.  if n is length the result is n*n or n^2
    public void merge(){
        //if its empty we can do nothing
        if(list.getHead().getNext()==null){
            return;
        }
        node current = list.getHead();
        node iterator = current.getNext();
        //find each element
        for(int i = 0; i < list.getSize(); i++){
            //and for each element check every other element
            for(int j =  i + 1; j < list.getSize(); j++){
                //if the id is equal simply call the merge method i made in person
                if(current.getContent().getID().equals(iterator.getContent().getID())){
                    current.getContent().merge(iterator.getContent());
                    //make sure to remove the redundant element
                    this.delete(j);
                }
                iterator = iterator.getNext();
            }
            //iterate the current node and reset the iterator node
            current = current.getNext();
            iterator = current.getNext();
        }
    }
}