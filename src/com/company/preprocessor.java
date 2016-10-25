package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.text.BreakIterator;
import java.util.Locale;


/**
 * Created by Jeremy Timothy Brown 10/21/2016
 * This class is designed to break an article into sentences.
 */
public class preprocessor {

    /**
     * Every instance in american english that might cause a sentence to break in the wrong spot.
     * Is this every instance?  Maybe...but it is close imo.  SOOOOOO yeah.  Deal with it. ;)
     */
    private static final String[] ABBREV_SPACE =
            {"Mr. ", "Mrs. ", "Ms. ", "Sr. ", "Jr. ", "Prof. ", "Mx. ", "St. ",
                    "A. ", "B. ", "C. ", "D. ", "E. ", "F. ", "G. ", "H. ", "I. ", "J. ", "K. ", "L. ", "M. ", "N. ", "O. ",
                    "P. ", "Q. ", "R. ", "S. ", "T. ", "U. ", "V. ", "W. ", "Y. ", "X. ", "Z. ",
                    ".a. ", ".b. ", ".c. ", ".d. ", ".e. ", ".f. ", ".g. ", ".h. ", ".i. ", ".j. ", ".k. ",
                    ".l. ", ".m. ", ".n. ", ".o. ", ".p. ", ".q. ", ".r. ", ".s. ", ".t. ", ".u. ", ".v. ", ".w. ", ".y. ", ".x. ", ".z. "
            };

    /**
     * Used to remove white space in most abbreviations that are used in American text.
     * This should make it so that the format of every sentence will be processed correctly.
     */
    private static final String[] ABBREV_REPLACE =
            {"Mr.", "Mrs.", "Ms.", "Sr.", "Jr.", "Prof.", "Mx.", "St.",
                    "A.", "B.", "C.", "D.", "E.", "F.", "G.", "H.", "I.", "J.", "K.", "L.", "M.", "N.", "O.",
                    "P.", "Q.", "R.", "S.", "T.", "U.", "V.", "W.", "Y.", "X.", "Z.",
                    ".a.", ".b.", ".c.", ".d.", ".e.", ".f.", ".g.", ".h.", ".i.", ".j.", ".k.",
                    ".l.", ".m.", ".n.", ".o.", ".p.", ".q.", ".r.", ".s.", ".t.", ".u.", ".v.", ".w.", ".y.", ".x.", ".z."
            };

    /**
     * This function takes file, processes it into an array list, and returns that list.
     * @param fname the name of the file to be processed.
     * @return arrList the text file broken up into an array of strings
     * @throws IOException deals with any I/O exceptions.  such as the file not existing.  user input error lolz.
     */
    public static ArrayList<String> preprocessFile(String fName) throws IOException {
        ArrayList<String> arrList = new ArrayList<>();                                  //Array list to contain every sentence in the given text file
        StringBuilder sb = new StringBuilder();                                         //Use string builder to create a string to contain every line in the text file
        BufferedReader br = new BufferedReader(new FileReader(fName+".txt"));           //Readers to read in the text file.  FileReader is basic.  BuffReader deals with larger files.  or something like that...

        String line;                                                                    //String to use as a holder for every line
        while ((line = br.readLine()) != null) {                                        //Run through the text file and assign each value to the variable line until it hits null
            for (int j = 0; j < ABBREV_SPACE.length; j++) {                             //Run through every abbreviate I could think up
                if(line.contains(ABBREV_SPACE[j])){                                     //If the line contains an abbreviation
                    line = line.replace(ABBREV_SPACE[j], ABBREV_REPLACE[j]);            //Replace the whitespace in the abbreviation
                }
            }
            sb.append(line).append(" ");                                                //Append each line to the string builder.  Also gets rid of any newlines.
        }

        String sbString = sb.toString();                                                //Turn the string builder into a usable string
        BreakIterator boundary = BreakIterator.getSentenceInstance(Locale.US);          //Sentence Breaker to find sentence breaks
        boundary.setText(sbString);                                                     //Set the breaker to the string that contains all of the text

        int start = boundary.first();                                                   //Set the boundary to start at the beginning of the string
        for (int end = boundary.next(); end != BreakIterator.DONE; start = end, end = boundary.next()) {
            arrList.add(sb.substring(start, end));                                      //for every sentence in the string builder, append it to the array list
        }
        return arrList;
    }
}