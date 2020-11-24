import java.util.ArrayList;

//use for cleaning
//use utfii to compare, if beyond value of number or characters then delete

public class wordprocess{

  public static void clean(ArrayList<String> txt){
    
    for (int i = txt.size()-1; i >= 0; i-- ){
      txt.set(i, txt.get(i).replaceAll("[^\\x00-\\x7F]", ""));
      System.out.println(txt.get(i));
    }

 } 

  


}