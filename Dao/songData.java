package Dao;

public class songData
{
    int songID;
    String song;
    String duration;
    String Genre;
    String url;
    String Album;
    String artist;

    public String getArtist() {return artist;}

    public int getSongID() {
        return songID;
    }

    public String getSong() {
        return song;
    }

    public String getUrl() {
        return url;
    }

    public String getDuration() {
        return duration;
    }

    public String getGenre() {
        return Genre;
    }

    public String getAlbum() {
        return Album;
    }

    public songData(int songID, String song, String duration, String genre, String url, String album,String artist) {
        this.songID=songID;
        this.song = song;
        this.duration = String.valueOf(duration);
        this.Genre = genre;
        this.url=url;
        this.Album = album;
        this.artist=artist;
    }
    public songData() {}

    public void display() {
        System.out.format("%-10s %-35s %-15s %-15s %-15s %-15s \n",this.getSongID(),this.getSong(),this.getDuration(),this.getGenre(),this.getAlbum(),this.getArtist());
        System.out.println("------------------------------------------------------------------------------------------------------------");
    }
}
