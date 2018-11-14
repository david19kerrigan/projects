import java.util.LinkedList;
public class main{
    
    //O(n) where n is the siz of the list.  Ignore second for loop as mn simplifies to n since n is larger than m.
    public static int prefixCountArrayList(PBArrayList list, String prefix){
        int count = 0;
        //first loop iterates through the list each element one at a time
        for(int i = 0; i < list.size(); i++){
            LinkedList<String> numbers = list.search(i+1).getList();
            //now i go through the element (person)'s phone numbers and see if the beginning matches the prefix
            for(int j = 0; j < numbers.size(); j++){
                if(numbers.get(j).substring(0,3).equals(prefix)){
                    count++;
                }
            }
        }
        return count;
    }

    //also O(n)
    public static int prefixCountLinkedList(PBLinkedList list, String prefix){
        int count = 0;
        //first loop iterates through the list each element one at a time
        for(int i = 0; i < list.size(); i++){
            LinkedList<String> numbers = list.search(i).getList();
            //now i go through the element (person)'s phone numbers and see if the beginning matches the prefix
            for(int j = 0; j < numbers.size(); j++){
                if(numbers.get(i).substring(0,3).equals(prefix)){
                    count++;
                }
            }
        }
        return count;
    }

    
    public static void main(String[] args) {
        //test arraylist
        PBArrayList one = new PBArrayList();

        //testing size() and addPerson()
        System.out.println(one.size()); //zero
        one.addPerson(1,new Person("001"));    
        System.out.println(one.size()); //one
        one.addPerson(2,new Person("002"));
        System.out.println(one.size()); //two


        //testing addNumber() and search()
        one.addNumber("001", "3476336336");
        System.out.println(one.search(1).getList()); //return the number i just put in;

        //testing delete()
        one.delete(2);
        System.out.println(one.size());//one

        //testing merge()
        one.addPerson(3, new Person("001","347123456789"));
        one.merge();
        System.out.println(one.size());//one
        System.out.println(one.search(1).getList());//this will have the regular number and 1-9 number

        System.out.println(prefixCountArrayList(one, "347"));//this should be two








        System.out.println("\n\n\n\n\n\n\n");

        //testing linkedlist

        PBLinkedList list = new PBLinkedList();

        //testing size() and addPerson()
        System.out.println("zero " + list.size()); //zero
        list.addPerson(1,new Person("001"));    
        System.out.println("one " + list.size()); //one
        list.addPerson(2,new Person("002"));
        System.out.println("two " + list.size()); //two


        //testing addNumber() and search()

        list.addNumber("001", "3476336336");
        System.out.println("001 " + list.search(1).getID());//this should be 001
        System.out.println(list.search(1).getList()); //return the number i just put in;
        

        //testing delete()
        list.delete(2);
        System.out.println("one " + list.size());//one
        System.out.println(list.search(1).getID());//001
        System.out.println("null " + list.search(2)); // null

        //testing merge()
        list.addPerson(3, new Person("001","347123456789"));
        list.merge();
        System.out.println(list.size());//one
        System.out.println(list.search(1).getList());//this will have the regular number and 1-9 number

        System.out.println(prefixCountLinkedList(list, "347"));//this should be two
        
    }

    
}