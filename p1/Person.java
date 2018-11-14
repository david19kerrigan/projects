import java.util.LinkedList;
public class Person{

    //variable for ID and the list of phone numbers associated with this person
    private String ID;
    private LinkedList<String> phoneNums;

    //declare them
    public Person(String ID){
        phoneNums = new LinkedList<String>();
        this.ID = ID;
    }

    //i can input either ID or ID and a phone number
    public Person(String ID, String number){
        
        phoneNums = new LinkedList<String>();
        this.addNum(number);
        this.ID = ID;
    }

    public String getID(){
        return ID;
    }

    public void addNum(String num){
        phoneNums.add(num);
    }

    //return that linkedlist variable, useful in the next method
    public LinkedList<String> getList(){
        return phoneNums;
    }

    //merge our list with another person's list of numbers
    public void merge(Person p){
        
        phoneNums.addAll(p.getList());
    }

}