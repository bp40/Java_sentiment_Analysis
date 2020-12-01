import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.*; 
import java.io.IOException;  
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document; 
import org.jsoup.nodes.Element;

class Main {

  public static ArrayList<String> urls = new ArrayList<String>();
  public static ArrayList<String> text = new ArrayList<String>();
  public static HashMap completeCount = new HashMap();
      
  public static void main(String[] args){
    System.out.println("\033[H\033[2J"); //clears the screen
    System.out.println("Program is starting..."); //text baseline debug
    
    crawler.crawl("https://forums.macrumors.com/forums/macos-big-sur-11-0.226/","https://forums.macrumors.com", "div.structItem-title a[href]" ,urls);
    
    for (int i = 0; i < urls.size(); i++){ 
      crawler.extractWords(urls.get(i), "div.bbWrapper",text);
    }
    
    wordprocess.countWords(wordprocess.clean(crawler.getMessages()), completeCount);

    System.out.println("\033[0;32m" + "\n\nCOMPLETE : The program ran til the end, but did it work?"); //text baseline debug
  }
  
}