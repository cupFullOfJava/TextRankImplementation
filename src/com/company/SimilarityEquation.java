package com.company;


/**
 * Created by Jeremy Timothy Brown on 10/22/2016.
 * This class applies the similarity equation to a given two sentences
 * The formal statement of the similarity equation is as follows
    * The absolute value the total similar words (denoted Wk)
    * in sentence one (denoted S1) that are also in sentence two (denoted S2)
    * divided by the log of total words in S1 plus the log of total words in S2.
 * The division part is to normalize the similarity between the two sentences
 * to deal with sentences that have different lengths.
 */
public class SimilarityEquation {

    /**
     * Given two sentences, this function runs the Similarity Equation on them and returns the result.
     * @param s1 the first sentence assigned as a String
     * @param s2 the second sentence assigned as a String
     * @return simDubOne the similarity that sentence one has to sentence two assigned as a double
     */
    public static double runEquation(String s1, String s2){
        //variables used in this equation
        String[] arrS1 = s1.split(" ");         //the first sentence split into a string list
        String[] arrS2 = s2.split(" ");         //the second sentence split into a string list
        double normDivide = normalize(          //runs the normalize function on two sentence lengths
                        (double) arrS1.length,
                        (double) arrS2.length); //the lengths are cast to doubles when sent to the function
        double simCount = 0;                    //double variable used to count the total similar words sentence one shares with sentence two
        double similarity;                  //variable to be used to hold the normalized count
        //end variable declaration

        System.out.println("----------------------");           //makes printing out to the console prettier ;)
        System.out.println("Sentence One: "+s1);                //Prints out the first sentence
        System.out.println("Sentence Two: "+s2);                //Prints out the second sentence

        /**
         * loop to determine the total similar words sentence one has is sentence two.
         * For every word in sentence two and for every word in sentence one
         * Check to see if the the current word in S1 is the same as the word current word in S2
         * If it is add one to the count variable, simCount.
         * It the breaks the loop so that the S1 is not checked anymore after it is counted.
         */
        for(String s2word : arrS2) {
            for (String s1word : arrS1) {
                if (s1word.compareTo(s2word) == 0) {
                    simCount++;
                    break;
                }
            }
        }

        similarity = Math.abs(simCount)/normDivide;             //this normalizes the count
        System.out.println("The sim is "+similarity);           //prints out the similarity to the console
        System.out.println("----------------------");           //makes printing out to the console prettier ;)
        return similarity;                                      //obviously this returns the similarity that sentence one has to sentence two
    }

    /**
     * This function computes that number to normalize the count by.
     * @param s1L the length of sentence one
     * @param s2L the length of sentence two
     * @return the normalization double
     */
    private static double normalize(double s1L, double s2L){
        return Math.log(Math.abs(s1L)) + Math.log(Math.abs(s2L));
    }



}
