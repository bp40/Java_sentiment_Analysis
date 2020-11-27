import java.util.ArrayList;
import java.util.Collections;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

class crawler {
  
    public static ArrayList < String > messages = new ArrayList < String > ();

    public static void crawl(String url, String rootUrl, String htmlTag, ArrayList < String > destinationArray) {

        try {

            Document doc = Jsoup.connect(url).get();

            for (Element link: doc.select(htmlTag)) { // get links from href attribute in <a> tags
                destinationArray.add(rootUrl + link.attr("href"));
            }

        } catch (Exception except) {
            except.printStackTrace();
        }

    }
//extract words into an arraylist called messages and would also remove punctuation in the mean time(will need to come up with a way to remove stop words as well)
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

        } catch (Exception except) {
            except.printStackTrace();
        } finally {
            messages.removeAll(Collections.singleton(null));
        }
    }

    public static ArrayList < String > getMessages() {
        return messages;
    }



}