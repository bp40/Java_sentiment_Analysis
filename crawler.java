import java.util.ArrayList;
import java.io.IOException;  
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document; 
import org.jsoup.nodes.Element;

class crawler {

  public static void crawl(String url, String rootUrl , String htmlTag, ArrayList<String> destinationArray) {

    try {

      Document doc = Jsoup.connect(url).get();

      for (Element link : doc.select(htmlTag)) { // get links from href attribute in <a> tags
        destinationArray.add(rootUrl + link.attr("href"));
      }

    } catch (Exception except) {
      except.printStackTrace();
    }
    
  }

  public static void scrape(String url, String htmlTag, ArrayList<String> destinationArray) { 

    try {

    Document doc = Jsoup.connect(url).get();

    for (Element link : doc.select(htmlTag)) {
      System.out.println(link.text());
    }

    } catch (Exception except) {
      except.printStackTrace();
    }

  }

}