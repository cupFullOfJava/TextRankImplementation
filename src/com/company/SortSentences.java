package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Jeremy Timothy Brown on 10/22/2016.
 * This class sorts a sentence list given a list of similarity scores that directly correlates with the first list
 */
public class SortSentences {


    private static ArrayList<String> sortListCorOrder = new ArrayList<>();

    /**
     *
     * @param sentences the list of sentences
     * @param scores the list of similarity scores
     * @param summaryLen the user given length of the summary
     * @return sortedList a computed list of strings that hold the scored sentences
     * @throws Exception deals with some exception...
     */
    public static ArrayList<String> sort(ArrayList<String> sentences, ArrayList<Double> scores, ArrayList<String> originalList, int summaryLen) throws Exception{
        ArrayList<String> sortedList = new ArrayList<>();       //array list to hold the sorted sentences
        double curMax = 0;                                      //double to hold the current max of the score list
        int curPos = 0;                                         //integer to hold the current position of the current max of the score list

        /**
         * for loop based on the user given length that they want the summary to be
         * It walks through every score in the scores list,
            * finds the max and assigns that to curMax
            * and also assigns the current position of the max to curPos
         * Once this is done.
            * The max score is removed from the list.
            * The sentence with the max score is added to the sorted list.
            * The sentence is then removed from the sentences list, and the current max is set back to zero.
         * This is repeated until the user given length is reached.
         */
        for(int i = 0; i < summaryLen; i++){
            for(double score : scores){
                if(score > curMax){
                    curMax = score;
                    curPos = scores.indexOf(score);
                }
            }
            scores.remove(curPos);
            sortedList.add(sentences.get(curPos));
            sentences.remove(curPos);
            curMax = 0;
        }

        return sortedList;          //return the sorted list in the correct order
    }

}
