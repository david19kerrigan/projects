import java.io.*;
public class Main{

    public static void wordCount(String inputFileName, String outputFileName) throws Exception{
        HashTable table = new HashTable(outputFileName);
        BufferedReader br = new BufferedReader(new FileReader(inputFileName)); 
  
        String st; 
        while((st = br.readLine()) != null){
            String[] a = st.split("[, ?.@]+");
            for(int i = 0; i < a.length; i++){
                System.out.println(a[i]);
                table.insert(a[i]);
            }
            

        }
        table.printAll();

        
    }

    public static void main(String args[]) throws Exception{

        wordCount("toyFile.txt", "out.txt");
        

        

    }

}