package Main;

import Dao.podcastData;
import Dao.songData;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class executionProcess {
    Scanner sc = new Scanner(System.in).useDelimiter("\n");

    //

    public void printSongData(List<songData> name) {
        System.out.format("%-10s %-34s  %-15s %-15s %-15s %-15s\n", "Track ID", "Song Name", "Duration", "Genre", "Album", "Artist Name");
        System.out.println("(●'◡'●)(●'◡'●)(●'◡'●)(●'◡'●)(●'◡'●)(●'◡'●)(●'◡'●)(●'◡'●)(●'◡'●)(●'◡'●)(●'◡'●)(●'◡'●)(●'◡'●)(●'◡'●'◡'●)");
        name.forEach(songData::display);
    }

    public List<songData> customSearchSongByName(List<songData> list, int searchType, String search) {
            return (searchType == 1) ? list.stream().filter(p -> p.getSong().startsWith(search.toUpperCase())).toList() : (searchType == 2) ?
                    list.stream().filter(p -> p.getSong().equalsIgnoreCase(search)).toList() : new ArrayList<>();
    }

    public List<songData> customSearchSongByAlbum(List<songData> list, int searchType, String search) {
        return (searchType == 1) ? list.stream().filter(p -> p.getAlbum().startsWith(search.toUpperCase())).toList() : (searchType == 2) ?
                list.stream().filter(p -> p.getAlbum().equalsIgnoreCase(search)).toList() : null;
    }

    public List<songData> customSearchSongByGenre(List<songData> list, int searchType, String search) {
        return (searchType == 1) ? list.stream().filter(p -> p.getGenre().startsWith(search.toUpperCase())).toList() : (searchType == 2) ?
                list.stream().filter(p -> p.getGenre().equalsIgnoreCase(search)).toList() : null;
    }

    public List<songData> customSearchSongByArtist(List<songData> list, int searchType, String search) {
        return (searchType == 1) ? list.stream().filter(p -> p.getArtist().startsWith(search.toUpperCase())).toList() : (searchType == 2) ?
                list.stream().filter(p -> p.getArtist().equalsIgnoreCase(search)).toList() : null;
    }

    public List<podcastData> customSearchPodcast(List<podcastData> list, int searchType, String search) {
        return (searchType == 1) ? list.stream().filter(p -> p.getEpisodeName().startsWith(search.toUpperCase())).toList() : (searchType == 2) ?
                list.stream().filter(p -> p.getEpisodeName().equalsIgnoreCase(search)).toList() : null;
    }

    public void printPodcastData(List<podcastData> name) {
        System.out.format("%-10s %-20s %-20s %-20s %-20s %-20s\n", "SNO", "Episode Name", "Podcast Name", "Celebrity Name", "Duration", "Date of Publish");
        name.forEach(podcastData::displayPodcastDetails);
    }

    public void printPodcastMenu(List<podcastData> name) {
        name.forEach(podcastData::displayPodcastMenu);
    }

    public List<podcastData> customSearchPodcastByName(List<podcastData> list, int searchType, String search) {
        return (searchType == 1) ? list.stream().filter(p -> p.getPodcastName().startsWith(search.toUpperCase())).toList() : (searchType == 2) ?
                list.stream().filter(p -> p.getPodcastName().equalsIgnoreCase(search)).toList() : null;
    }

    public List<podcastData> customSearchPodcastByEpisodeName(List<podcastData> list, int searchType, String search) {
        return (searchType == 1) ? list.stream().filter(p -> p.getEpisodeName().startsWith(search.toUpperCase())).toList() : (searchType == 2) ?
                list.stream().filter(p -> p.getEpisodeName().equalsIgnoreCase(search)).toList() : null;
    }

    public List<podcastData> customSearchPodcastByNumberOfEpisodes(List<podcastData> list, int search) {
        return list.stream().filter(p -> p.getEpisodes() == search).toList();
    }

    public List<podcastData> customSearchPodcastByCelebrityName(List<podcastData> list, int searchType, String search) {
        return (searchType == 1) ? list.stream().filter(p -> p.getCelebName().startsWith(search.toUpperCase())).toList() : (searchType == 2) ?
                list.stream().filter(p -> p.getEpisodeName().equalsIgnoreCase(search)).toList() : null;
    }

    public List<songData> playListData(List<songData>list,int id){
      return list.stream().filter(p->p.getSongID()==id).toList();
    }

    public String playSong(List<songData> list, int trackID) {
        return list.stream().filter(p -> p.getSongID()==trackID).toList().get(0).getUrl();
    }

    public int choose() {
        System.out.println("Please Select: ");
        return sc.nextInt();
    }

    public void displayMenuSongs() {
        System.out.println("""
                1️⃣ search song by name
                2️⃣ search song by album
                3️⃣ search song  by artist
                4️⃣ search song by genre
                """);
    }

    public void displayMenuPodcast() {
        System.out.println("""
                1️⃣ search podcast by name
                2️⃣ search podcast by episode name
                3️⃣ search podcast by number of episodes
                4️⃣ search podcast by celebrity name
                """);
    }

    public void displayMain() {
        System.out.println("""
                1️⃣ search songs
                2️⃣ search podcast
                3️⃣ login/sign up
                """);
    }

    public int cSearchType() {
        System.out.println("""
                1️⃣ search by Alphabet
                2️⃣ search by Name
                3️⃣ Add Song to your playlist""");
        return choose();
    }

    //search by name for search parameter
    String cSearch(){
        System.out.println("Enter name or alphabet");
       sc.nextLine();
        return sc.nextLine();
    }

    public void displayLogin() {
        System.out.println("""
                1️⃣ login
                2️⃣ sign up""");
    }

    public void displayMenuForLoggedIn() {
        System.out.println("""
                1️⃣ search songs
                2️⃣ search podcast
                3️⃣ create playlist
                4️⃣ view playlist
                5️⃣ play media player
                0️⃣ exit
                """);
    }
    public int addToPlaylist(){
        System.out.println("""
                1️⃣ add song to playlist
                2️⃣ exit menu""");
        return choose();
    }

    //used to create
    public String createNewPlayListName(){
        System.out.println("Enter playlist Name");
        return sc.next();
    }

    //function to select the playlist in which song has to be entered
    public String selectPlaylist(){
        System.out.println("select playlist Name");
        sc.nextLine();
        return sc.next();
    }

    //display to view the available playlist for the user
    public int availablePlaylist(){
        System.out.println("""
                            1️⃣ view available playlist
                            """);
        return choose();
    }

    //to signup new account input data should be taken using this
    public String userName(){
        System.out.println("Enter Name");
       return sc.next();
    }
    public String password(){
        System.out.println("Enter Password");
        return  sc.next();
    }
}
