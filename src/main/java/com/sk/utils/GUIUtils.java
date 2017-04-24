package com.sk.utils;

import com.sk.gui.ButtonsPanel;
import com.sk.gui.SongDescriptorsPanel;
import com.sk.gui.SongSelectionPanel;
import com.sk.objects.Song;

import java.awt.*;
import java.util.TreeMap;

/**
 * Utility class for methods that perform activities related to the GUI interface
 *
 * @author Samra Kasim
 */
public class GUIUtils
{

    /**
     * Method centers the frame
     * @param window: a Window object that represents the SongFrame object
     */
    public static void centerFrame(Window window)
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int xLoc = (dimension.width - window.getWidth())/2;
        int yLoc = (dimension.height - window.getHeight())/2;
        window.setLocation(xLoc, yLoc);
    }

    /**
     * Method fills in the descriptor fields for the song selected in the GUI interface
     * combo box
     * @param selectedSong: a String value representing the song selected in the combo box
     * @param songTreeMap: a TreeMap with key of type String and value of type Song representing
     *                   all song object records
     * @param songDescriptorsPanel: a SongDescriptors object representing the descriptor JTextFields of
     *                            the GUI interface
     */
    public static void enterSongRecord(String selectedSong,
                                       TreeMap<String, Song> songTreeMap,
                                       SongDescriptorsPanel songDescriptorsPanel)
    {
        Song song = songTreeMap.get(selectedSong);
        songDescriptorsPanel.artistField.setText(song.getArtist());
        songDescriptorsPanel.albumField.setText(song.getAlbum());
        songDescriptorsPanel.descriptionField.setText(song.getDescription());
        songDescriptorsPanel.itemCodeField.setText(song.getItemCode());
        songDescriptorsPanel.priceField.setText(String.valueOf(song.getPrice()));
        // Enable fields to be editable
        songDescriptorsPanel.artistField.setEditable(false);
        songDescriptorsPanel.albumField.setEditable(false);
        songDescriptorsPanel.descriptionField.setEditable(false);
        songDescriptorsPanel.itemCodeField.setEditable(false);
        songDescriptorsPanel.priceField.setEditable(false);
    }

    /**
     * Method to clear the fields in the Java GUI following the clicking of the add button
     * @param songDescriptorsPanel: a SongDescriptors object representing the descriptor JTextFields of
     *                            the GUI interface
     * @param songSelectionPanel: a SongSelection object representing the song selection combo box of the
     *                          GUI interface
     * @param buttonsPanel: ButtonsPanel object representing the action buttons in the GUI interface
     */
    public static void clearSongDescriptorFields(SongDescriptorsPanel songDescriptorsPanel,
                                                 SongSelectionPanel songSelectionPanel,
                                                 ButtonsPanel buttonsPanel)
    {
        songSelectionPanel.songBox.setEditable(true);
        songSelectionPanel.songBox.setSelectedItem("");
        songDescriptorsPanel.artistField.setText("");
        songDescriptorsPanel.albumField.setText("");
        songDescriptorsPanel.descriptionField.setText("");
        songDescriptorsPanel.itemCodeField.setText("");
        songDescriptorsPanel.priceField.setText(String.valueOf(""));
        // Enable fields to be editable
        songDescriptorsPanel.artistField.setEditable(true);
        songDescriptorsPanel.albumField.setEditable(true);
        songDescriptorsPanel.descriptionField.setEditable(true);
        songDescriptorsPanel.itemCodeField.setEditable(true);
        songDescriptorsPanel.priceField.setEditable(true);
        // Enable or disable buttons based on expected actions
        buttonsPanel.edit.setEnabled(false);
        buttonsPanel.delete.setEnabled(false);
        buttonsPanel.accept.setEnabled(true);
        buttonsPanel.cancel.setEnabled(true);
        buttonsPanel.add.setEnabled(false);
    }

    /**
     * Method to validate newly added song title and descriptor fields before adding them to the
     * song selection combo box and TreeMap.
     * @param songDescriptorsPanel: a SongDescriptors object representing the descriptor JTextFields of
     *                            the GUI interface
     * @param songSelectionPanel: a SongSelection object representing the song selection combo box of the
     *                          GUI interface
     * @param songTreeMap: a TreeMap with key of type String and value of type Song representing
     *                   all song object records
     * @param buttonsPanel: ButtonsPanel object representing the action buttons in the GUI interface
     */
    public static void acceptDescriptorFields(SongDescriptorsPanel songDescriptorsPanel,
                                              SongSelectionPanel songSelectionPanel,
                                              TreeMap<String, Song> songTreeMap,
                                              ButtonsPanel buttonsPanel)
    {
        double price = -99999;
        String priceStr = "";
        Song song = null;
        String songTitle = String.valueOf(songSelectionPanel.songBox.getSelectedItem());
        if (songTitle.equals(""))
        {
            songTitle = null;
        }
        String itemCode = songDescriptorsPanel.itemCodeField.getText();
        if (itemCode.equals(""))
        {
            itemCode = null;
        }
        String artist = songDescriptorsPanel.artistField.getText();
        if (artist.equals(""))
        {
            artist = null;
        }
        String album = songDescriptorsPanel.albumField.getText();
        if (album.equals(""))
        {
            album = "None";
            System.out.println("WARNING Album not provided for song [" + songTitle+ "]. Assuming song " +
                    "is a single and setting album to None");
        }
        try
        {
            priceStr = songDescriptorsPanel.priceField.getText();
            price = Double.parseDouble(priceStr);
        }
        catch(NumberFormatException ex)
        {
            System.out.println("ERROR Non-numeric price ["+ priceStr +"] given for [" + songTitle +"].");
        }
        String description = songDescriptorsPanel.descriptionField.getText();
        if (description.equals(""))
        {
            description = null;
        }

        if(songTitle!=null && itemCode != null && artist != null && price!=-99999 && description != null) {
            // If song is already in the map, replace descriptors so that a new record is not created
            if (songTreeMap.containsKey(songTitle))
            {
                song = songTreeMap.get(songTitle);
                song.setItemCode(itemCode);
                song.setAlbum(album);
                song.setArtist(artist);
                song.setPrice(price);
                song.setDescription(description);
            } else
            {
                song = new Song(itemCode, songTitle, artist, album, price, description);
                songSelectionPanel.songBox.addItem(songTitle);
                songTreeMap.put(songTitle, song);
            }
        } else
        {
            System.out.println("ERROR Record not created due to missing or invalid values: song title " +
                    "["+ songTitle +"], item code [" + itemCode +"], artist [" + artist +"], " +
                    "album ["+ album +"], price [" + priceStr +"], and description ["+description+"]");
        }
        // Enable or disable buttons based on expected actions
        buttonsPanel.add.setEnabled(true);
        buttonsPanel.edit.setEnabled(true);
        buttonsPanel.delete.setEnabled(true);
        buttonsPanel.accept.setEnabled(false);
        buttonsPanel.cancel.setEnabled(false);
        buttonsPanel.exit.setEnabled(true);
    }

    /**
     * Method enables or disables fields based on expected actions following selection of the add button
     * and returns the GUI interface to original state
     * @param buttonsPanel: ButtonsPanel object representing the action buttons in the GUI interface
     */
    public static void cancelAddDescriptorFields(ButtonsPanel buttonsPanel)
    {
        buttonsPanel.add.setEnabled(true);
        buttonsPanel.edit.setEnabled(true);
        buttonsPanel.delete.setEnabled(true);
        buttonsPanel.accept.setEnabled(false);
        buttonsPanel.cancel.setEnabled(false);
        buttonsPanel.exit.setEnabled(true);
    }

    /**
     * Method for editing the descriptor fields
     * @param songDescriptorsPanel: a SongDescriptors object representing the descriptor JTextFields of
     *                            the GUI interface
     * @param songSelectionPanel: a SongSelection object representing the song selection combo box of the
     *                          GUI interface
     * @param buttonsPanel: ButtonsPanel object representing the action buttons in the GUI interface
     */
    public static void editDescriptorFields(SongDescriptorsPanel songDescriptorsPanel,
                                            SongSelectionPanel songSelectionPanel,
                                            ButtonsPanel buttonsPanel)
    {
        // Make the combo box editable
        songSelectionPanel.songBox.setEditable(true);
        // Enable or disable buttons based on expected actions
        buttonsPanel.add.setEnabled(false);
        buttonsPanel.edit.setEnabled(false);
        buttonsPanel.delete.setEnabled(false);
        buttonsPanel.accept.setEnabled(true);
        buttonsPanel.cancel.setEnabled(true);
        buttonsPanel.exit.setEnabled(true);
        // Make fields editable
        songDescriptorsPanel.itemCodeField.setEditable(false);
        songDescriptorsPanel.descriptionField.setEditable(true);
        songDescriptorsPanel.artistField.setEditable(true);
        songDescriptorsPanel.albumField.setEditable(true);
        songDescriptorsPanel.priceField.setEditable(true);
    }

    /**
     * Method for deleting a record from the combo box and the TreeMap
     * @param songSelectionPanel: a SongSelection object representing the song selection combo box of the
     *                          GUI interface
     * @param songTreeMap: a TreeMap with key of type String and value of type Song representing
     *                   all song object records
     */
    public static void deleteDescriptorFields(SongSelectionPanel songSelectionPanel,
                                              TreeMap<String, Song> songTreeMap)
    {
        String songSelection = String.valueOf(songSelectionPanel.songBox.getSelectedItem());
        songTreeMap.remove(songSelection);
        songSelectionPanel.songBox.removeItem(songSelection);
    }
}
