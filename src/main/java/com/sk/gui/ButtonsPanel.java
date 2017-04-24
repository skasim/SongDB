package com.sk.gui;

import com.sk.objects.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.TreeMap;

/**
 * ButtonsPanel class extends JPanel and represents the action buttons in the GUI interface
 *
 * @author Samra Kasim
 */
public class ButtonsPanel extends JPanel
{
    public JButton add;
    public JButton edit;
    public JButton delete;
    public JButton accept;
    public JButton cancel;
    public JButton exit;
    private TreeMap<String, Song> songTreeMap;
    private SongDescriptorsPanel songDescriptorsPanel;
    private SongSelectionPanel songSelectionPanel;

    /**
     * Constructor to create a new ButtonsPanel object
     * @param songTreeMap: a TreeMap with key of type String and value of type Song representing
     *                   all song object records
     * @param songDescriptorsPanel: a SongDescriptors object representing the descriptor JTextFields of
     *                            the GUI interface
     * @param songSelectionPanel: a SongSelection object representing the song selection combo box of the
     *                          GUI interface
     */
    public ButtonsPanel(TreeMap<String, Song> songTreeMap, SongDescriptorsPanel songDescriptorsPanel,
                        SongSelectionPanel songSelectionPanel)
    {
        this.songTreeMap = songTreeMap;
        this.songDescriptorsPanel = songDescriptorsPanel;
        this.songSelectionPanel = songSelectionPanel;
        // Set flow layout
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Create buttons
        this.add = new JButton("Add");
        this.edit = new JButton("Edit");
        this.delete = new JButton("Delete");
        this.accept = new JButton("Accept");
        this.cancel = new JButton("Cancel");
        this.exit = new JButton("Exit");

        // Add buttons to panel
        this.add(add);
        this.add(edit);
        this.add(delete);
        this.add(accept);
        this.add(cancel);
        this.add(exit);

        // Instantiate and add action listeners
        ActionListener listener = new ButtonsPanelListener(this, songTreeMap,
                songDescriptorsPanel, songSelectionPanel);
        exit.addActionListener(listener);
        add.addActionListener(listener);
        accept.addActionListener(listener);
        cancel.addActionListener(listener);
        edit.addActionListener(listener);
        delete.addActionListener(listener);
    }
}
