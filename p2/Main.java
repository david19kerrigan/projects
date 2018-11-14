import java.io.*;
public class Main{
    
    public static String huffmanCoder(String inputFileName, String outputFileName) throws Exception{
        //s represents the character and the correpsonding index in a represents it's frequency
        String s = "ABCDEFGHIJKLMNOPQRSTUYVWXYZabcdefghijklmnopqrstuvwxyz";
        int[] a = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int savings = 0;
        FileReader fr = new FileReader(inputFileName);
        int i; 
        //increment the charater's count in array "a"
        while ((i=fr.read()) != -1){
            
            for(int j = 0; j < 52; j++){
                if(((char) i) == s.charAt(j)){
                    a[j] += 1;
                }
            } 
        }
        //and print the character and its frequency
        for(int j = 0; j < 52; j++){
            for(int k = 0; k < 52; k++){

            }
            System.out.print(s.charAt(j));
            System.out.print(" : " + a[j]+"\n");
        }
        //call all the tree methods we need
        huffmanTree tree = new huffmanTree();
        tree.addAllNodes(s, a);
        tree.buildTree();
        tree.printEncoding();
        String[] table = tree.storeEncoding();
        
        FileReader reader = new FileReader(inputFileName);
        int z; 
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
        //write the encoded text to the file
        while ((z=reader.read()) != -1){
               
            int d = 0;
            //find the appropriate character
            while(table[d].charAt(0) != (char) z && table[d+2] != null){
                d += 2;
            }
            //we store the frequency for each character directly after it in the array
            //each character is 2 indeces apart 
            if(table[d].charAt(0) == (char) z){
                //8 - length of the encoding is the saving for the individual write
                //note the saving can be negative for an individual write
                savings+= (8-table[d+1].length());
                writer.write(table[d+1]);
            }
            else{
                //if we didnt find the character in our table just write the character we read from the file
                //we dont save any space
                writer.write((char) z);
            }
            
        }
        writer.close();
     
        System.out.println("savings (bits): " + savings);
        //print height depth and balance
        tree.printData();
        return "a";
    }
    
    public static void main(String[] args) throws Exception{
        huffmanCoder("in.txt", "out.txt");
    }

}