import java.io.IOException;  
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document; 
import org.jsoup.nodes.Element;

class Main {
  public static void main(String[] args) throws IOException{
    
    final String mainUrl = "https://forums.macrumors.com/forums/macos-big-sur-11-0.226/";

    try {

      final Document doc = Jsoup.connect(mainUrl).get();

      for (Element links : doc.select("div.structItem-title a[href]")) {
        System.out.println("https://forums.macrumors.com/" + links.attr("href"));
      }

    } catch (Exception except) {
      System.out.println(except);
      except.printStackTrace();
    }

  }
}