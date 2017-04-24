package com.sk.objects;

/**
 * Class creates a new Song object
 *
 * @author Samra Kasim
 */
public class Song
{

    private String itemCode;
    private String songTitle;
    private String artist;
    private String album;
    private double price;
    private String description;

    /**
     * Empty constructor to create a new Song object
     */
    public Song(){}

    /**
     * Constructor to create a new Song object
     * @param itemCode: String representing the itemCode of a song
     * @param songTitle: String representing the songTitle
     * @param artist: String representing the artist's name
     * @param album: String representing the album name
     * @param price: double representing the price
     * @param description: String representing the description of the song
     */
    public Song(String itemCode, String songTitle, String artist, String album,
                double price, String description)
    {
        this.itemCode = itemCode;
        this.songTitle = songTitle;
        this.artist = artist;
        this.album = album;
        this.price = price;
        this.description = description;
    }
    // Getters and Setters
    public String getItemCode(){ return this.itemCode; }
    public String getSongTitle(){ return this.songTitle; }
    public String getArtist(){ return this.artist; }
    public String getAlbum(){ return this.album; }
    public double getPrice(){ return this.price; }
    public String getDescription(){ return this.description; }
    public void setItemCode(String itemCode){ this.itemCode = itemCode; }
    public void setSongTitle(String songTitle){ this.songTitle = songTitle; }
    public void setArtist(String artist){ this.artist = artist; }
    public void setAlbum(String album){ this.album = album; }
    public void setPrice(double price){ this.price = price; }
    public void setDescription(String description){ this.description = description; }

    /**
     * ToString method to represent the Song object as a String
     * @return String representing the Song object
     */
    public String toString()
    {
        return getItemCode()+"; "+getSongTitle()+"; "+getArtist()+"; "+getAlbum()+"; " +
                ""+getPrice()+"; "+getDescription();
    }
}
