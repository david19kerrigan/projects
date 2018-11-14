public class huffmanTree{
    huffmanNode root;
    
    //list of letters and their corresponding frequencies
    String letters;
    int[] frequency;
    //list of nodes that at the end should just have the root
    huffmanNode[] nodes;
    //encoding table
    String[] table;


    boolean balanced;
    int leaves;
    int height;

    public huffmanTree(){
        //length of alph x 2 
        //table should be long enough to have null space so 52*2 + 2
        nodes = new huffmanNode[52];
        table = new String[106];
    }
    
    //add nodes and sort 
    //takes in the list of letters and their frequencies for us to add from outside the class
    public void addAllNodes(String s, int[] a){
        //transcribe each letter and its frequency ONLY if it's frequency is not zero
        //this accomplishes in such a way that the sequence is consecutive and without null elements between non-null elements
        for(int i = 0; i < 52; i++){
            if(a[i] != 0){
                
                boolean done = false;
                for(int j = 0; j < 52; j++){
                    
                    if(nodes[j] == null && done == false){
                        nodes[j] = new huffmanNode(s.charAt(i), a[i]);
                        done = true;
                        
                    }
                }
            }
        }   
        //count the length of our list of characters
        int count = 0;
        for(int i = 0; i < 52; i++){
            if(nodes[i] != null){
                count++;
            }
        }
        
        //make a new list of nodes that is the length of the non-null elements
        huffmanNode[] newNodes = new huffmanNode[count];
        //now "nodes" is rid of the empty space at the end of the array
        for(int i = 0; i < count; i++){
            newNodes[i] = nodes[i];
        }

        nodes = newNodes;

        //sort the list of nodes by lowest first
        //nested while loop rearranges the list into sorted portion first followed by unsorted portion
        for(int i = 0; i < count; i++){
            int min = i;
            for(int j = i+1; j < count; j++){
                if(nodes[j].getFreq() < nodes[min].getFreq()){
                    min = j;
                }
            }
            huffmanNode newMin = nodes[min];
            nodes[min] = nodes[i];
            nodes[i] = newMin;
            
            
        }

        
        

    }

    public void buildTree(){
        //if two nodes combine them and we're done
        if(nodes.length == 2){
            
            nodes = new huffmanNode[]{huffmanNode.merge(nodes[0],nodes[1])};
            
            return;
        }
        
        //new list of nodes must be one element shorter
        huffmanNode[] newNode = new huffmanNode[nodes.length-1];

        //we have to merge the first two elements (as they are the smallest in frequency)        
        huffmanNode merged = huffmanNode.merge(nodes[0],nodes[1]);
        
        //delete the first two elements of the list
        for(int i = 0; i < newNode.length-1; i++){
            newNode[i] = nodes[i+2];    
        }
        
        newNode[newNode.length-2] = nodes[nodes.length-1];

        //find where to put the new merged element
        //start from the end and stop when the frequency of the merged element is greater than the current
        //element
        //otherwise shift the node up one spot
        boolean inserted = false;
        for(int i = newNode.length-2; i >= 0 && inserted == false; i--){
            
            if(merged.getFreq() > newNode[i].getFreq()){
                newNode[i+1] = merged;
                inserted = true;
            }
            else{
                
                newNode[i+1] = newNode[i];
            }       
        }
        //apply our changes to the node list
        nodes = newNode;
        //and recur
        buildTree();

    }

    public void printEncoding(){
        printEncoding(nodes[0], "");
    }

    //recur through our tree where left is 0 and right is 1
    //print each leaf with it's "code" -- the combo of 0's and 1's referring to the leafs position
    //also print the leaf's character
    public void printEncoding(huffmanNode root, String code){
        if(root.getLeft() != null){
            printEncoding(root.getLeft(), code+"0");
        }
        if(root.getRight() != null){
            
            printEncoding(root.getRight(), code+"1");
        }
        else{
            System.out.println(code+root.getChar());
        }
    }

    
    public String[] storeEncoding(){
        storeEncoding(nodes[0], "");
        return table;
    }

    //does the same as printencoding but stores the results in a table where the characters are stored
    //in multiples of index 2 and the corresponding frequency is directly after
    public void storeEncoding(huffmanNode root, String code){
        if(root.getLeft() != null){
            storeEncoding(root.getLeft(), code+"0");
        }
        if(root.getRight() != null){
            
            storeEncoding(root.getRight(), code+"1");
        }
        else{
            int i = 0;
            while(table[i] !=null){
                i++;
            }
            table[i] = ""+root.getChar();
            table[i+1] = code;
            leaves++;
            
        }
    }

    public void calcHeight(){
        height = calcHeight(nodes[0]);
    }

    //simple method recurring through left and right branch and compare recursively
    public int calcHeight(huffmanNode root){
        int leftHeight = 0;
        int rightHeight = 0;
        if(root.getLeft()!=null)
            leftHeight = calcHeight(root.getLeft());
        if(root.getRight()!=null)
            rightHeight = calcHeight(root.getRight());
        if(leftHeight > rightHeight){
            return leftHeight+1;
        }
        else{
        return rightHeight+1;
        }
    }

    public void isBalanced(){
        balanced = isBalanced(nodes[0]);
    }

    //calculate the balance factor
    public boolean isBalanced(huffmanNode root){
        int leftHeight;
        int rightHeight;
        boolean b;
        if(root == null){
            return true;
        }

        leftHeight = calcHeight(root.getLeft());
        rightHeight = calcHeight(root.getRight());
        
        //if the heights are not too different continue to the left and right and evaluate those too
        if(Math.abs(leftHeight - rightHeight) <=1){
            if(isBalanced(root.getLeft()) && isBalanced(root.getRight())){
                //if all the heights were within +-1 height on either side then we are balanced
                return true;
            }
        }

        //if the heights were not within +-1 of each other the tree is not balanced
        return false;

    }

    public void printData(){
        calcHeight();
        isBalanced();
        System.out.println("balanced? "+balanced);
        System.out.println("height? "+ height);
        System.out.println("leaf nodes? "+leaves);
    }

}