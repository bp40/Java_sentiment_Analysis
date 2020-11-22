import java.util.ArrayList;
import java.io.IOException;  
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document; 
import org.jsoup.nodes.Element;

class Main {

  static ArrayList<String> urls = new ArrayList<String>();
  static ArrayList<String> text = new ArrayList<String>();

  public static void main(String[] args){
    
    crawler.crawl("https://forums.macrumors.com/forums/macos-big-sur-11-0.226/","https://forums.macrumors.com", "div.structItem-title a[href]" ,urls);
    
    for (int i = 0; i < urls.size(); i++){ 
      crawler.scrape(urls.get(i), "div.bbWrapper",text);
    }
    

  }
}