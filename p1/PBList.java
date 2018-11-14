public abstract class PBList{

    //list for person obejects

    public abstract int size();

    public abstract void addPerson(int i, Person person);

    public abstract boolean addNumber(String ID, String phoneNum);

    public abstract boolean delete(int i);

    public abstract Person search(int i);

    public abstract void merge();

}