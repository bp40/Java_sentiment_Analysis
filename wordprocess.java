import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

//use for cleaning
public class wordprocess{
  public static void clean(ArrayList<String> txt){
    for (int i = txt.size()-1; i >= 0; i-- ){
      txt.set(i, txt.get(i).replaceAll("[^\\x41-\\x7A]", ""));
    }

    try{

      File stopWords = new File("stopwords.txt");
      Iterator<String> it = txt.iterator();

      while(it.hasNext()){
        String i = it.next();
        Scanner fileScan = new Scanner(stopWords);
        fileScan.useDelimiter(",");
        while(fileScan.hasNext()){
          if(i.equalsIgnoreCase(fileScan.next())){
           it.remove();
           break;
          }
        }
        fileScan.close();
      }
      
      it = txt.iterator();

      while(it.hasNext()){
        String i = it.next();
        
        if(i.isBlank() || i.isEmpty()){
           it.remove();
        }    
      }
    } catch(Exception e){
      e.printStackTrace();
    } finally {
      System.out.println(txt);
    }

    

    /*
    for(String words : txt){
      if (words != null&&words != ""&& words != "\n" && words !="\r\n"&&words!= "\r"){
        System.out.println(words); 
      }
      */
    }
 } 

  


