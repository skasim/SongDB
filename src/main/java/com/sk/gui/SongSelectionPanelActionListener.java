package com.sk.gui;

import com.sk.objects.Song;
import com.sk.utils.GUIUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

/**
 * Class implements ActionListener interface and creates a SongSelectionPanelActionListener
 * object that listens for actions in the song selection panel of the GUI interface
 *
 * @author Samra Kasim
 */
public class SongSelectionPanelActionListener implements ActionListener
{

    private SongSelectionPanel songSelectionPanel;
    private SongDescriptorsPanel songDescriptorsPanel;
    private TreeMap<String, Song> songTreeMap;

    /**
     * Constructor creates a new SongSelectionPanelActionListener object
     * @param songSelectionPanel: a SongSelection object representing the song selection combo box of the
     *                          GUI interface
     * @param songDescriptorsPanel: a SongDescriptors object representing the descriptor JTextFields of
     *                            the GUI interface
     * @param songTreeMap: a TreeMap with key of type String and value of type Song representing
     *                   all song object records
     */
    public SongSelectionPanelActionListener(SongSelectionPanel songSelectionPanel,
                                            SongDescriptorsPanel songDescriptorsPanel,
                                            TreeMap<String, Song> songTreeMap)
    {
        this.songSelectionPanel = songSelectionPanel;
        this.songDescriptorsPanel = songDescriptorsPanel;
        this.songTreeMap = songTreeMap;
    }

    /**
     * Method to implement responses to actions performed in the song selection panel
     * @param e: ActionEvent object representing an action
     */
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        String selectedSong = String.valueOf(songSelectionPanel.songBox.getSelectedItem());
        if (source == songSelectionPanel.songBox)
        {
            try
            {
                GUIUtils.enterSongRecord(selectedSong, songTreeMap, songDescriptorsPanel);
            }
            catch (NullPointerException ex)
            {
                ex.getMessage();
            }
        }
    }
}
