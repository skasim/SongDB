package com.sk.gui;

import com.sk.objects.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class extends JPanel and creates a new SongSelectionPanel object representing the song
 * selection combo box
 *
 * @author Samra Kasim
 */
public class SongSelectionPanel extends JPanel
{

    private JLabel songBoxLabel;
    public JComboBox songBox;

    /**
     * Constructor creates a new SongSelectionPanel object
     * @param songTreeMap: a TreeMap with key of type String and value of type Song representing
     *                   all song object records
     * @param songDescriptorsPanel: a SongDescriptors object representing the descriptor JTextFields of
     *                            the GUI interface
     */
    public SongSelectionPanel(TreeMap<String, Song> songTreeMap, SongDescriptorsPanel songDescriptorsPanel)
    {
        // Create Label
        songBoxLabel = new JLabel("Select Song: ");
        // Create Combo Box
        songBox = new JComboBox();
        songBox.setPreferredSize(new Dimension(400, 30));
        // Set flow layout
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        // Parse through tree map
        for (Map.Entry song : songTreeMap.entrySet())
        {
            songBox.addItem(song.getKey());
        }
        // Add to panel
        this.add(songBoxLabel);
        this.add(songBox);

        // Add to action listener
        ActionListener listener = new SongSelectionPanelActionListener
                (
                this,
                songDescriptorsPanel,
                songTreeMap);
        songBox.addActionListener(listener);
    }
}
