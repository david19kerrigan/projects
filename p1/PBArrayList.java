public class PBArrayList extends PBList{
    Person[] array;
    //declare and give value to the array
    public PBArrayList(){
         array = new Person[100];

    }

    
    //go through and find the first null element this is the size
    //O(n) since there is one loop that goes through every element
    public int size(){
        for(int i = 0; i < array.length; i++){
            if(array[i] == null){
                return i;
            }
        }
        return array.length;
    }

    //O(n^2) since we go through two for loops at most.  each one is the length of the list so n*n = n^2
    public void addPerson(int i, Person person){
        //make this -5 so if we can't find the lastElement we know as the index can never be negative
        int lastElement = -5;
        //then find the last element in the list
        for(int j = 0; j <= this.size(); j++) {

            if(array[j] == null){

                lastElement = j-1;
 
            }
            
        }
        //add to an empty array
        if(array[0] == null){
            array[0] = person;
            return;
        }

        //the array is full so we make a new one with more length and copy everything over
        //then add to the end
        if(lastElement == -5){
                
            Person[] newArray = new Person[array.length+100];
                
            for(int j = 0; j < array.length; j++){
                
                newArray[j] = array[j];
                
            }
            newArray[array.length] = person;
            this.array = newArray;
            return;
        }

        if(i < lastElement){

            if(lastElement != -5){
                //start at last element and go down shifting everything
                for(int j = lastElement; j > i; j--){
                    //i want to insert the element at int i - 1 and shift array[i] upwards to the right
                    array[j+1] = array[j];

                }
                array[i] = person;
            }

        }
        //if i is greater than the size of the list just add it to the end
        if(i >= lastElement && lastElement >= 0){
            array[lastElement+1] = person;
        }
    }

    //O(n) for the one while loop.  It may go through every list element
    public boolean addNumber(String ID, String phoneNum){
        int i = 0;
        //iterate thru the array and find the matching iD
        while(!array[i].getID().equals(ID)){
            //if we reach the end its not here
            if(i == array.length -1){
                return false;
            }
            //if we hit null its not here
            if(array[i] == null){
                return false;
            }
            i++;
        }
        
        array[i].addNum(phoneNum);
        return true;
    }

    //we can through up to two for loops.  n is the length and each will go up to the length so n*n = n^2
    public boolean delete(int i){
        int lastElement = -5;
        //find the last element
        for(int j = 0; j <= this.size(); j++){
                
            if(array[j] == null){

                lastElement = j-1;
               
            }
            
        }
        //if i is out of range we cannot delete anything
        if(i > lastElement+1){
            return false;
        }

        //i want to delete the element at position [i-1] in the array
        //i dont even need to delete it all i need to do is shift elements over
        //i want to shift every element from array[i] to array[lastElement] inclusive down by one 
        //i want to do this by starting from array[i] and then working my way upwards

        for(int j = i-1; j <= lastElement; j++){
            array[j] = array[j+1];

        }

        return true;

    }

    //O(n) for the single for loop iterating through each element for length n.
    public Person search(int i){
        int lastElement = -5;
        //find the last element
        for(int j = 0; j < array.length; j++){
                
            if(array[j] == null){

                lastElement = j-1;

            }
            
        }
        //if we're looking for something out of range return null
        if(i - 1 > lastElement){
            return null;
        }
        //otherwise simply index the array
        return array[i-1];
    }

    //O(n^2) since we enter two for loops each running for the length of the list n. n*n = n^2
    public void merge(){
        int lastElement = -5;
        //find the last elemtent.  
        for(int i = 0; i <= this.size(); i++){
                
            if(array[i] == null){

                lastElement = i-1;
                

            }
            
        }
        //go through each element
        for(int i = 0;i <= lastElement; i++){
            //and for each go through every other element
            for(int j = i+1; j <= lastElement; j++){
                //if the two id's are equal merge the lists using the function we made in person.java
                if(array[i].getID().equals(array[j].getID())){
                    array[i].merge(array[j]);
                    //make sure to remove the redundant element
                    this.delete(j+1);
                }
            }
        }
    }


    

}