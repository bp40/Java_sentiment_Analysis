import java.util.ArrayList;
import java.util.Collections;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

class crawler {

  public static ArrayList < String > messages = new ArrayList < String > ();

  //Accessor
  public static ArrayList < String > getMessages() {
    return messages;
  }

  //takes in the url of the discussion forum, the root url, the tag in which the discussion link is in, an ArrayList<String>
  //finds different posts in the forum and saving their respective links in the ArrayList
  public static void crawl(String url, String rootUrl, String htmlTag, ArrayList < String > destinationArray) {

    try {

      Document doc = Jsoup.connect(url).get();

      for (Element link: doc.select(htmlTag)) { // get links from href attribute in <a> tags
        destinationArray.add(rootUrl + link.attr("href"));
      }

    } catch(Exception except) {
      except.printStackTrace();
    }

  }
  //Takes in the url of the post, the html tag of the post text and a ArrayList<String>
  //extract words from link into an arraylist called messages and would also remove punctuation in the mean time
  public static void extractWords(String url, String htmlTag, ArrayList < String > destinationArray) {

    try {

      Document doc = Jsoup.connect(url).get();

      for (Element link: doc.select(htmlTag)) {
        String text = link.text();
        String[] arrSplit = text.split("[ ]");

        for (int x = 0; x < arrSplit.length; x++) {
          messages.add(arrSplit[x].toLowerCase());
        }
      }

    } catch(Exception except) {
      except.printStackTrace();
    } finally {
      messages.removeAll(Collections.singleton(null));
    }
  }
}