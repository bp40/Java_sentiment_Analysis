import java.util.ArrayList;
import java.util.Iterator;

//use for cleaning
public class wordprocess{
  public static void clean(ArrayList<String> txt){
    for (int i = txt.size()-1; i >= 0; i-- ){
      txt.set(i, txt.get(i).replaceAll("[^\\x41-\\x7A]", ""));
    }

    Iterator<String> it = txt.iterator();
    while(it.hasNext()){
      String i = it.next();
      

      if(i.isBlank() || i.isEmpty()){
        it.remove();
      }
    }

    System.out.println(txt);

    /*
    for(String words : txt){
      if (words != null&&words != ""&& words != "\n" && words !="\r\n"&&words!= "\r"){
        System.out.println(words); 
      }
      */
    }
 } 

  


