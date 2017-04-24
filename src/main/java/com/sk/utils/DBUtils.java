package com.sk.utils;

import com.sk.objects.Song;

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Utility class for methods that perform database related activities
 *
 * @author Samra Kasim
 */
public class DBUtils
{
    /**
     * Method checks to see if a file exists and returns a boolean response
     * @param filepath: a String representing the input filepath
     * @return fileExists: a boolean response representing whether a file exists
     */
    public static boolean checkDBFileExists(String filepath)
    {
        File file;
        boolean fileExists = false;
        try {
            file = new File(filepath);
            fileExists = file.exists();
        }catch(NullPointerException e)
        {
            System.out.println("Provided input filepath is null [" + e.getMessage() +"]");
        }
        return fileExists;
    }

    /**
     * Method reads the database file and returns a TreeMap object
     * @param songTreeMap: a TreeMap with key of type String and value of type Song representing
     *                   all song object records
     * @param inputFilePath: a String representing the input filepath
     * @return songTreeMap: a TreeMap with key of type String and value of type Song representing
     *                   all song object records
     */
    public static TreeMap<String, Song> readDB(TreeMap<String, Song> songTreeMap, String inputFilePath)
    {
        BufferedReader reader = null;
        String record;

        try
        {
            reader = new BufferedReader(new FileReader(inputFilePath));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Input file not found ["+ e.getMessage() +"]");
        }

        try
        {
            /* While loop converts every line in the input file into a song object and adds it
               TreeMap */
            while ((record = reader.readLine()) != null)
            {
                String[] recordArray = record.split("; ");
                String itemCode = recordArray[0];
                String songTitle = recordArray[1];
                String artist = recordArray[2];
                String album = recordArray[3];
                double price = Double.valueOf(recordArray[4]);
                String description = recordArray[5];

                Song song = new Song(itemCode, songTitle, artist, album, price, description);
                songTreeMap.put(songTitle, song);
            }
        }
        catch (IOException e)
        {
            System.out.println("Error reading from input file ["+ e.getMessage()+"]");
        }
        try
        {
            reader.close();
        }
        catch (IOException e)
        {
            System.out.println("Error closing input file [" + e.getMessage() +"]");
        }
        return songTreeMap;
    }

    /**
     * Method writes song records to the output file path
     * @param songTreeMap: a TreeMap with key of type String and value of type Song representing
     *                   all song object records
     * @param outputFilePath: a String representing the input filepath
     */
    public static void writeDB(TreeMap<String, Song> songTreeMap, String outputFilePath)
    {
        BufferedWriter writer = null;
        try
        {
            writer = new BufferedWriter(new FileWriter(outputFilePath));
        }
        catch (IOException e)
        {
            System.out.println("Error creating output file [" + e.getMessage() + "]");
        }
        // For loop parses through the TreeMap and writes one record at a time
        for (Map.Entry song : songTreeMap.entrySet())
        {
            try
            {
                if (writer != null)
                {
                    writer.write(String.valueOf(song.getValue() + "\n"));
                }
            }
            catch (IOException e)
            {
                System.out.println("Error writing to output file [" + e.getMessage() + "]");
            }
        }
        try
        {
            if (writer != null)
            {
                writer.close();
            }
        }
        catch (IOException e)
        {
            System.out.println("Error closing output file [" + e.getMessage() + "]");
        }
    }
    /**
     * Method returns the input value entered into the console as a string
     * @return value: String input value entered by user
     */
    public static String getStringInput()
    {
        String value;
        Scanner input = new Scanner(System.in);
        value = input.nextLine();
        return value;
    }
}
