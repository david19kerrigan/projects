import java.io.*;
public class HashTable{
    private class Entry{
        private String key;
        private int count;
        private boolean removed;


        private Entry(String key, int e){
            this.key = key;
            count = e;
            removed = false;

        }

        private String getKey(){
            return key;
        }
        private void incCount(){
            count++;
        }

        private int getCount(){
            return count;
        }

    }

    private Entry[][] table;
    private int size;
    private int numItems;
    private String name;

    public HashTable(String s){
        this.size = 2;
        table = new Entry[size][1];
        numItems = 0;
        name = s;
    }

    public void insert(String key){
        int h = Math.abs(key.hashCode()) % size;
        if((double)numItems/(double)size >= .75){
            Entry[][] temp = table;
            Entry[][] newTable = new Entry[temp.length * 2][1];
            for(int i = 0; i < temp.length; i++){
                newTable[i] = temp[i];
            }
            table = newTable;
            size = size * 2;
            rehash();
        }
        if(table[h][table[h].length-1] == null){
            for(int i = 0; i < table[h].length; i++){
                if(table[h][i] == null){
                    table[h][i] = new Entry(key, 1);
                    numItems++;
                    break;
                }
                else if(table[h][i].getKey().equals(key)){
                    table[h][i].incCount();
                    break;
                }
                
            }
        }
        else{
            Entry[] temp = table[h];
            Entry[] newArray = new Entry[temp.length * 2];
            for(int i = 0; i < temp.length; i++){
                newArray[i] = temp[i];
            }
            table[h] = newArray;
            for(int i = 0; i < table[h].length; i++){
                if(table[h][i] == null){
                    table[h][i] = new Entry(key, 1);
                    numItems++;
                    break;
                }
                else if(table[h][i].getKey().equals(key)){
                    table[h][i].incCount();
                    break;
                }
                
            }
            
        }
    }

    public void printAll() throws IOException{
        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table[i].length; j++){
                if(table[i][j] != null){
                    System.out.println(table[i][j].getKey() + ", "+ table[i][j].getCount());
                }
                
            }
        }
        
        

        BufferedWriter writer = new BufferedWriter(new FileWriter(name));
        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table[i].length; j++){
                if(table[i][j] != null){
                    
                    writer.write("("+table[i][j].getKey() + ", "+ table[i][j].getCount()+") ");
                }
                
            }
            
        }
        
        writer.close();
                    
        System.out.println("size: "+size);
        System.out.println(table.length);
        int nonEmptySize = 0;;
        
        for(int i = 0; i < table.length; i++){
            if(table[i][0] != null){nonEmptySize++;}
            
        }
        
        BufferedWriter writer0 = new BufferedWriter(new FileWriter("properties.txt"));
        
        writer0.write("non-empty size: "+nonEmptySize);
        writer0.newLine();
        writer0.write("num of items: " + numItems);
        writer0.newLine();
        writer0.write("load factor: "+ (double)numItems/(double)size);
        writer0.newLine();
        writer0.write("collision length: " + (double)numItems/(double)nonEmptySize);
        writer0.close();

        System.out.println("non-empty size: "+nonEmptySize);
        System.out.println("num of items: " + numItems);
        System.out.println("load factor: "+ (double)numItems/(double)size);
        System.out.println("collision length: " + (double)numItems/(double)nonEmptySize);
    }
    
    public void rehash(){
        Entry[][] newTable = new Entry[table.length][1];
        for(int i = 0; i < table.length/2; i++){
            for(int j = 0; j < table[i].length && table[i][j] != null; j++){
                int h = Math.abs(table[i][j].getKey().hashCode()) % size;
                if(newTable[h][newTable[h].length-1] == null){
                    for(int k = 0; k < newTable[h].length; k++){
                        if(newTable[h][k] == null){
                            newTable[h][k] = table[i][j];
                            
                            break;
                        }
                        
                        
                    }
                }
                else{
                    Entry[] temp = newTable[h];
                    Entry[] newArray = new Entry[temp.length * 2];
                    for(int k = 0; k < temp.length; k++){
                        newArray[k] = temp[k];
                    }
                    newTable[h] = newArray;
                    for(int k = 0; k < table[k].length; k++){
                        if(newTable[h][k] == null){
                            newTable[h][k] = table[i][j];
                            
                            break;
                        }
                    }
                        
                        
                }
            }
            
        }
    }

}