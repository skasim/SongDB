package com.sk.gui;

import com.sk.objects.Song;
import com.sk.utils.DBUtils;
import com.sk.utils.GUIUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

import static com.sk.SongDatabase.INPUT_FILE_PATH;

/**
 * Class implements the ActionListener interface and creates a ButtonsPanelListener object
 * that listens for actions in the buttons panel section of the GUI interface
 * @author Samra Kasim
 */
public class ButtonsPanelListener implements ActionListener
{
    private ButtonsPanel buttonsPanel;
    private TreeMap<String, Song> songTreeMap;
    private SongDescriptorsPanel songDescriptorsPanel;
    private SongSelectionPanel songSelectionPanel;

    /**
     * Constructor to create a new ButtonsPanel object
     * @param buttonsPanel: ButtonsPanel object representing the action buttons in the GUI interface
     * @param songTreeMap: a TreeMap with key of type String and value of type Song representing
     *                   all song object records
     * @param songDescriptorsPanel: a SongDescriptors object representing the descriptor JTextFields of
     *                            the GUI interface
     * @param songSelectionPanel: a SongSelection object representing the song selection combo box of the
     *                          GUI interface
     */
    public ButtonsPanelListener(ButtonsPanel buttonsPanel,
                                TreeMap<String, Song> songTreeMap,
                                SongDescriptorsPanel songDescriptorsPanel,
                                SongSelectionPanel songSelectionPanel)
    {
        this.buttonsPanel = buttonsPanel;
        this.songTreeMap = songTreeMap;
        this.songDescriptorsPanel = songDescriptorsPanel;
        this.songSelectionPanel = songSelectionPanel;
    }

    /**
     * Method to implement responses to actions performed in the buttons panel
     * @param e: ActionEvent object representing an action
     */
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == buttonsPanel.exit)
        {
            DBUtils.writeDB(songTreeMap,INPUT_FILE_PATH);
            System.exit(0);
        }
        else if (source == buttonsPanel.add)
        {
            GUIUtils.clearSongDescriptorFields(songDescriptorsPanel, songSelectionPanel, buttonsPanel);
        }
        else if (source == buttonsPanel.accept)
        {
            GUIUtils.acceptDescriptorFields(songDescriptorsPanel, songSelectionPanel, songTreeMap, buttonsPanel);
        }
        else if (source == buttonsPanel.cancel)
        {
            String selectedSong = songTreeMap.firstKey();
            songSelectionPanel.songBox.setSelectedItem(selectedSong);
            GUIUtils.enterSongRecord(selectedSong, songTreeMap, songDescriptorsPanel);
            GUIUtils.cancelAddDescriptorFields(buttonsPanel);
        }
        else if (source == buttonsPanel.edit)
        {
            GUIUtils.editDescriptorFields(songDescriptorsPanel, songSelectionPanel, buttonsPanel);
        }
        else if (source == buttonsPanel.delete)
        {
            GUIUtils.deleteDescriptorFields(songSelectionPanel, songTreeMap);
        }
    }
}
