package com.sk.gui;

import javax.swing.*;
import java.awt.*;

/**
 * SongDescriptorsPanel extends JPanel and represents the labels and descriptors of a
 * the GUI interface
 *
 * @author Samra Kasim
 */
public class SongDescriptorsPanel extends JPanel
{

    private JLabel itemCodeLabel;
    private JLabel descriptionLabel;
    private JLabel artistLabel;
    private JLabel albumLabel;
    private JLabel priceLabel;
    public JTextField itemCodeField;
    public JTextField descriptionField;
    public JTextField artistField;
    public JTextField albumField;
    public JTextField priceField;

    /**
     * Constructor creates a new SongDescriptorsPanel object which represents the labels
     * and text fields descriptors of the song GUI interface
     */
    public SongDescriptorsPanel()
    {
        // Set flow layout
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Create labels
        this.itemCodeLabel = new JLabel("  Item Code:");
        this.descriptionLabel = new JLabel("Description:");
        this.artistLabel = new JLabel("         Artist: ");
        this.albumLabel = new JLabel("       Album: ");
        this.priceLabel = new JLabel("      Price:  ");

        // Create fields
        this.itemCodeField = new JTextField(5);
        this.descriptionField = new JTextField(40);
        this.artistField = new JTextField(30);
        this.albumField = new JTextField(30);
        this.priceField = new JTextField(5);

        // Add labels and fields to panel
        this.add(Box.createHorizontalStrut(900));
        this.add(artistLabel);
        this.add(artistField);
        this.add(Box.createHorizontalStrut(900));
        this.add(albumLabel);
        this.add(albumField);
        this.add(Box.createHorizontalStrut(900));
        this.add(descriptionLabel);
        this.add(descriptionField);
        this.add(Box.createHorizontalStrut(900));
        this.add(itemCodeLabel);
        this.add(itemCodeField);
        this.add(priceLabel);
        this.add(priceField);
    }

}
