package com.company;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created By Jeremy Timothy Brown.
 * This is an implementation of the TextRank concept for automatically summarizing a given body of text.
 * The documentation used for my implementation can be found in TextRank.PDF which is provided in this folder.
 */
public class Main {

    //global variables
    private static ArrayList<String> arrList = new ArrayList<>();       //to hold original list
    private static ArrayList<String> origList = new ArrayList<>();      //to also hold the original list
    private static ArrayList<Double> simNums = new ArrayList<>();       //to hold the similarity doubles
    private static ArrayList<String> sortedList = new ArrayList<>();    //to hold the sorted list
    private static Scanner scan = new Scanner(System.in);               //scanner for user input

    public static void main(String[] args) throws Exception {
        preprocessor p = new preprocessor();                //creates an instance of the preprocessor class
        SimilarityEquation se = new SimilarityEquation();   //creates an instance of the SimilarityEquation class
        double sims;

        String fname = getFileName();                   //runs getFileName and assigns in a string variable
        System.out.println("Parsing file into list of sentences...\n");
        arrList = p.preprocessFile(fname);              //Sends the file to be processed
        origList = p.preprocessFile(fname);             //since arrList is to be modified later, another list must be created to hold the original processed list


        /**
         * for loop to run through the sentences, select two, and then send off to the similarity equation function
         * once a double is returned, it is added to the simNums list
         * The if statement deals with the last sentence in the array.  this is to cover if a body of text has an odd length of sentences.
         */
        System.out.println("Comparing Sentences...");
        for(int i = 0; i < arrList.size(); i++){
            sims = se.runEquation(arrList.get(i), arrList.get(i + 1));
            simNums.add(sims);
            if(simNums.size() == arrList.size()-1){
                sims = se.runEquation(arrList.get(i+1), arrList.get(i));
                simNums.add(sims);
                break;
            }
        }

        int sumlen = summaryLength(arrList.size());
        SortSentences ss = new SortSentences();
        sortedList = ss.sort(arrList, simNums, origList, sumlen);

        /**
         * for loop that writes the sorted list to a file
         * it also sorts the chosen sentences into the correct order for output
         */
        PrintWriter pw = new PrintWriter(fname+"__summary.txt", "UTF-8");
        System.out.println("\nSentences to write to summary text file");
        System.out.println("----------------------");
        for(String origS : origList) {
            for (String sortS : sortedList) {
                if (origS.compareTo(sortS) == 0) {
                    System.out.println(origS);
                    pw.println(origS);
                    break;
                }
            }
        }
        pw.close();
        System.out.println("----------------------");
        System.out.println("Writing to file "+fname+"__summary.txt...............");
        System.out.println("DONE!");
    }

    /**
     * This function asks the user to enter a file name that corresponds to a textFile in the root folder
     * @return name of file
     */
    private static String getFileName(){
        System.out.println("Enter name of text file: ");
        String fname = scan.next();
        return fname;
    }

    /**
     * This function asks the user to enter a length that they want the summary to be.
     * If the number they enter is greater then or equal to the size of the original body of text, it will not accept the answer.
     * The user rMUST enter a number smaller.
     * @param orSize size of the original list
     * @return the desired length of the summary provided by the user
     */
    private static int summaryLength(int orSize){
        int len;
        System.out.println("\nEnter summary length less than "+orSize+":");
        String lenS = scan.next();
        len = Integer.parseInt(lenS);
        while(len >= orSize){
            System.out.println("You must enter a length that is less than the original size of the text");
            System.out.println("Enter summary length less than "+orSize+":");
            lenS = scan.next();
            len = Integer.parseInt(lenS);
        }
        scan.close();
        return len;
    }
}
