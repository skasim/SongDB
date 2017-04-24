package com.sk;

import com.sk.gui.SongFrame;
import com.sk.objects.Song;
import com.sk.utils.DBUtils;

import javax.swing.*;
import java.util.TreeMap;

/**
 * This program creates a database of songs that can be manipulated using a
 * JAVA GUI interface
 *
 * @author Samra Kasim
 */
public class SongDatabase
{
    public static String INPUT_FILE_PATH;

    /**
     * Program entry-point
     * @param args: String array representing the arguments passed in when the program is run.
     *            In this case, a filepath to the database records file.
     */
    public static void main(String[] args)
    {
        // Instantiate TreeMap for Song objects
        TreeMap<String, Song> songTreeMap = new TreeMap<String, Song>();

        // Check to see if filepath to the database is provided as a run-time parameter and set it
        try
        {
            INPUT_FILE_PATH = args[0];
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Filepath to database not provided.");
            INPUT_FILE_PATH = null;
        }
        // If filepath is not provided, ask for a filepath or q to quit program
        while(INPUT_FILE_PATH==null || !DBUtils.checkDBFileExists(INPUT_FILE_PATH))
        {
            System.out.println("Provided database does not exist. Provide correct database filepath or enter [n] " +
                    "to create a new database or enter [q] to quit program: ");
            String consoleInput = DBUtils.getStringInput();
            // If q is selected exit program
            if (consoleInput.equals("q"))
            {
                System.exit(0);
            }
            // If n is selected create a new database with the filepath provided
            else if (consoleInput.equals("n"))
            {
                System.out.println("Provide filepath to where new database text file " +
                        "should be created (e.g., /path/to/file.txt): ");
                INPUT_FILE_PATH = DBUtils.getStringInput();
                DBUtils.writeDB(songTreeMap, INPUT_FILE_PATH);
            }
            // Otherwise the input filepath is the one provided in the console
            else
            {
                INPUT_FILE_PATH = consoleInput;
            }
        }
        // Read the database file
        songTreeMap = DBUtils.readDB(songTreeMap, INPUT_FILE_PATH);

        // Instantiate Song Frame
        JFrame frame = new SongFrame(songTreeMap);
        frame.setVisible(true);
    }

}
