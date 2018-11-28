public class Reporting2{

    public static void test(String inputFile){
        //HashTable table = new HashTable(outputFileName);
        BufferedReader br = new BufferedReader(new FileReader(inputFile)); 
        
        String st; 
        while((st = br.readLine()) != null){
            String[] a = st.split(",");
            for(int i = 0; i < a.length; i++){
                System.out.println(a[i]);
                
            }
            

        }
    }

    public static void main(String args[]){
        test(args[0]);
    }

}