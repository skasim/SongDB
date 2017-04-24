package com.sk.gui;

import com.sk.objects.Song;
import com.sk.utils.GUIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;
import java.util.TreeMap;

/**
 * SongFrame class extends JFrame and creates a SongFrame object, which is the window of
 * the GUI interface
 *
 * @author Samra Kasim
 */
public class SongFrame extends JFrame
{
    /**
     *  Constructor to create a SongFrame object for the GUI interface
     * @param songTreeMap: a TreeMap with key of type String and value of type Song representing
     *                   all song object records
     */
    public SongFrame(TreeMap<String, Song> songTreeMap)
    {
        setTitle("Song Database");
        setBounds(280, 600, 600, 280);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUIUtils.centerFrame(this);

        // Create new panels
        JPanel songDescriptorsPanel = new SongDescriptorsPanel();
        JPanel songSelectionPanel = new SongSelectionPanel(songTreeMap,
                (SongDescriptorsPanel) songDescriptorsPanel);
        JPanel buttonsPanel = new ButtonsPanel(songTreeMap, (SongDescriptorsPanel) songDescriptorsPanel,
                (SongSelectionPanel) songSelectionPanel);

        //add to frame
        this.add(songSelectionPanel, BorderLayout.NORTH);
        this.add(songDescriptorsPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);

        //Add window listener to set values when the program begins
        WindowListener windowListener = new SongFrameWindowListener(this, songTreeMap,
                (SongDescriptorsPanel) songDescriptorsPanel, (ButtonsPanel) buttonsPanel);
        this.addWindowListener(windowListener);
    }
}
