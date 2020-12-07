import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;

//use for cleaning
public class wordprocess {
  public static ArrayList < String > positiveWordsClean = new ArrayList < String > ();
  public static ArrayList < String > negativeWordsClean = new ArrayList < String > ();

  //takes a ArrayList<String> 
  //replaces all characters not specified([^\\x41-\\x7A]) with blank String
  public static ArrayList < String > clean(ArrayList < String > txt) {
    for (int i = txt.size() - 1; i >= 0; i--) {
      txt.set(i, txt.get(i).replaceAll("[^\\x41-\\x7A]", "")); //cleans weird characters
    }

    try {

      File stopWords = new File("stopwords.txt");
      Iterator < String > it = txt.iterator();

      //cleans stopwords
      while (it.hasNext()) {
        String i = it.next();
        Scanner fileScan = new Scanner(stopWords);
        fileScan.useDelimiter(",");
        while (fileScan.hasNext()) {
          if (i.equalsIgnoreCase(fileScan.next()) || i.contains("\\") || i.contains("_") || i.contains("[")) {
            it.remove();
            break;
          }
        }
        fileScan.close();
      }

      it = txt.iterator(); //resetting the iterator
      //cleaning empty spaces caused by the removal of non ASCII characters
      while (it.hasNext()) {
        String i = it.next();

        if (i.isBlank() || i.isEmpty()) {
          it.remove();
        }
      }
    } catch(Exception e) {
      e.printStackTrace();
    } finally {
      //System.out.println(txt);
    }
    return txt;
  }

  //comparing words from crawler after being clean to txt files to be stored into arraylists and be later implemented with countword
  public static void lexiconDivider(ArrayList < String > txt) {
    try {
      File positivewords = new File("positivewords.txt");
      File negativewords = new File("negativewords.txt");
      Iterator < String > it = txt.iterator();
      while (it.hasNext()) {
        String i = it.next();
        Scanner posScan = new Scanner(positivewords);
        Scanner negScan = new Scanner(negativewords);
        while (posScan.hasNext()) {
          if (i.equalsIgnoreCase(posScan.next())) {
            positiveWordsClean.add(i);
            break;
          }
        }
        while (negScan.hasNext()) {
          if (i.equalsIgnoreCase(negScan.next())) {
            negativeWordsClean.add(i);
            break;
          }
        }
        posScan.close();
        negScan.close();
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  public static void countWords(ArrayList < String > txt, HashMap complete, String name) {

    String frequentWord = "";
    String Word;
    int total = 0;
    int maxValue = 0;
    int uniqueCount = 0;

    Collections.sort(txt);

    for (String word: txt) {
      int frequency = Collections.frequency(txt, word);
      if (!complete.containsKey(word)) {
        complete.put(word, frequency);
        uniqueCount++;
      }
    }

    Iterator mapIterate = complete.entrySet().iterator(); //iterator for hashmap entries
    while (mapIterate.hasNext()) { //while there are more entries
      Map.Entry pair = (Map.Entry) mapIterate.next(); //set pair to the next entry 
      System.out.println(pair.getKey() + " = " + pair.getValue()); //prints the key and value of that entry
      int value = (Integer) pair.getValue();
      Word = pair.getKey().toString();
      if (maxValue < value) {
        frequentWord = Word;
        maxValue = value;
      }
      total += value;
    }

    if (name.equals("Negative")) {
      Main.negTotal = total;
      Main.negWordMost = frequentWord;
    } else if (name.equals("Positive")) {
      Main.posTotal = total;
      Main.posWordMost = frequentWord;
    }

    System.out.println("Total " + name + " words : " + total);
    System.out.println("Total " + name + " unique words : " + uniqueCount);
    System.out.println("==========================================================");
  }

}