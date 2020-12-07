import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap; 
import java.io.IOException;  
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document; 
import org.jsoup.nodes.Element;

class Main {

  public static ArrayList<String> urls = new ArrayList<String>();
  public static ArrayList<String> text = new ArrayList<String>();
  public static HashMap completeNeg = new HashMap();
  public static HashMap completePos = new HashMap();
  public static int posTotal;
  public static int negTotal;
  public static String posWordMost;
  public static String negWordMost;
      
  public static void main(String[] args){
    System.out.println("\033[H\033[2J"); //clears the screen
    System.out.println("Program is starting..."); //text baseline debug
    long startTime = System.nanoTime();
    
    crawler.crawl("https://forums.macrumors.com/forums/macos-big-sur-11-0.226/","https://forums.macrumors.com", "div.structItem-title a[href]" ,urls);
    
    for (int i = 0; i < urls.size(); i++){ 
      crawler.extractWords(urls.get(i), "div.bbWrapper",text);
    }
    
    //wordprocess.countWords(wordprocess.clean(crawler.getMessages()), completeCount);
    //clean method is to remove punctuations and unwanted characters, parameters expected arraylist of words from crawler.extractWords method
    //call lexiconDivider method to seperate into positiveWordsClean or negativeWordsClean arraylist
    wordprocess.lexiconDivider(wordprocess.clean(crawler.getMessages()));
    wordprocess.countWords(wordprocess.positiveWordsClean, completePos, "Positive");
    wordprocess.countWords(wordprocess.negativeWordsClean, completeNeg, "Negative");

//compare posTotal and negTotal form countwords method to show which trend the opinions were going
//poTotal and negTotal has already been counted in countWords method in wordprocess
    if(posTotal > negTotal){
      System.out.println("Sentiment on the MacOS Big Sur is likely more positive than negative");
      System.out.println("Total Positive : " + posTotal + " Total Negative : " + negTotal);
      System.out.println("The most common positive word is " + posWordMost);
    } else if(posTotal < negTotal){
      System.out.println("Sentiment on the MacOS Big Sur is likely more negative than positive");
      System.out.println("Total Positive : " + posTotal + " Total Negative : " + negTotal);
      System.out.println("The most common negative word is " + negWordMost);
    } else if(posTotal == negTotal){
      System.out.println("Sentiment on the MacOS Big Sur is likely neutral");
      System.out.println(posTotal);
      System.out.println(negTotal);
      System.out.println(posWordMost);
      System.out.println(negWordMost);
    } else {
      System.out.println("Sum ting wong");
      System.out.println("Total Positive : " + posTotal + " | Total Negative : " + negTotal);
      System.out.println(posTotal);
      System.out.println(negTotal);
      System.out.println(posWordMost);
      System.out.println(negWordMost);
    }

    long endTime = System.nanoTime();
    long totalTime = endTime - startTime;
    
    System.out.println("\033[0;32m" + "\n\nCOMPLETE : The program ran til the end, but did it work? (took " + (totalTime / 1000000000) + " seconds)"); //text baseline debug
  }
  
}