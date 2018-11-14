public class huffmanNode{

    private Character inChar;
    private int frequency;
    private huffmanNode left;
    private huffmanNode right;

    public huffmanNode(Character a, int f){
        inChar = a;
        frequency = f;
    }

    public int getFreq(){
        return frequency;
    }

    public Character getChar(){
        return inChar;
    }

    public void setLeft(huffmanNode l){
        left = l;
    }
    
    public void setRight(huffmanNode r){
        right = r;
    }

    public huffmanNode getLeft(){
        return left;
    }

    public huffmanNode getRight(){
        return right;
    }

    //a static method called on two nodes
    public static huffmanNode merge(huffmanNode first, huffmanNode other){
        //create the parents with null and sum of frequencies
        huffmanNode parent = new huffmanNode(Character.MIN_VALUE, other.getFreq()+first.getFreq());
        //make sure to get the lower freq on the left and higher on the right
        if(first.getFreq() < other.getFreq()){
            parent.setLeft(first);
            parent.setRight(other);
        }
        else{
            parent.setRight(first);
            parent.setLeft(other);
        }
        //give a reference to parent node
        return parent;
    }
}