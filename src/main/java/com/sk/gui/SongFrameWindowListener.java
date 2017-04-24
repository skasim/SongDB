package com.sk.gui;

import com.sk.objects.Song;
import com.sk.utils.GUIUtils;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * Class implements the WindowListener interface and creates a new
 * SongFrameWindowListener object. The listener creates the response for a newly
 * opened JFrame window
 *
 * @author Samra Kasim
 */
public class SongFrameWindowListener implements WindowListener
{
    private JFrame songFrame;
    private TreeMap<String, Song> songTreeMap;
    private SongDescriptorsPanel songDescriptorsPanel;
    private ButtonsPanel buttonsPanel;

    /**
     * Constructor to create a new SongFrameWindowListener object
     * @param songFrame: JFrame object representing the main frame
     *                 of the GUI interface
     * @param songTreeMap: a TreeMap with key of type String and value of type Song representing
     *                   all song object records
     * @param songDescriptorsPanel: a SongDescriptors object representing the descriptor JTextFields of
     *                            the GUI interface
     * @param buttonsPanel: ButtonsPanel object representing the action buttons in the GUI interface
     */
    public SongFrameWindowListener(JFrame songFrame,
                                   TreeMap<String, Song> songTreeMap,
                                   SongDescriptorsPanel songDescriptorsPanel,
                                   ButtonsPanel buttonsPanel)
    {
        this.songFrame = songFrame;
        this.songTreeMap = songTreeMap;
        this.songDescriptorsPanel = songDescriptorsPanel;
        this.buttonsPanel = buttonsPanel;
    }

    /**
     * Method that implements actions when GUI interface window is first opened.
     * Used in this program to invoke an action that loads records into combo box as soon as
     * the GUI interface loads
     * @param e: a WindowEvent acton representing the window opening
     */
    public void windowOpened(WindowEvent e)
    {
        try
        {
            String selectedSong = songTreeMap.firstKey();
            GUIUtils.enterSongRecord(selectedSong, songTreeMap, songDescriptorsPanel);
        }
        catch (NoSuchElementException ex){
            System.out.println("First song in song database not found [" + ex.getMessage() +"] or database is empty");
        }
        buttonsPanel.accept.setEnabled(false);
        buttonsPanel.cancel.setEnabled(false);
    }

    public void windowClosing(WindowEvent e) {}

    public void windowClosed(WindowEvent e) {}

    public void windowIconified(WindowEvent e) {}

    public void windowDeiconified(WindowEvent e) {}

    public void windowActivated(WindowEvent e) {}

    public void windowDeactivated(WindowEvent e) {}
}
