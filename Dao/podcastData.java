package Dao;

public class podcastData
{
    //attributes
    int id;
    String episodeName;
    String celebName;
    String duration;
    String url;
    String DOP;
    int podcastID;
    int episodes;
    String podcastName;

    //getter and setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEpisodeName() {
        return episodeName;
    }
    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }
    public String getCelebName() {
        return celebName;
    }
    public void setCelebName(String celebName) {
        this.celebName = celebName;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getDOP() {
        return DOP;
    }
    public void setDOP(String DOP) {
        this.DOP = DOP;
    }
    public int getPodcastID() {
        return podcastID;
    }
    public void setPodcastID(int podcastID) {
        this.podcastID = podcastID;
    }
    public int getEpisodes() {
        return episodes;
    }
    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }
    public String getPodcastName() {
        return podcastName;
    }
    public void setPodcastName(String podcastName) {
        this.podcastName = podcastName;
    }

    //constructor to retrieve values from DB from table podcastTable
    public podcastData(int podcastID, int episodes, String podcastName) {
        this.podcastID = podcastID;
        this.episodes = episodes;
        this.podcastName = podcastName;
    }

    //
    public podcastData(int id, String episodeName, String celebName, String duration, String url, String DOP, int podcastID, int episodes,
                       String podcastName)
    {
        this.id = id;
        this.episodeName = episodeName;
        this.celebName = celebName;
        this.duration = duration;
        this.url = url;
        this.DOP = DOP;
        this.podcastID = podcastID;
        this.episodes = episodes;
        this.podcastName = podcastName;
    }

    public podcastData() {
    }

    public void displayPodcastDetails()
    {
        System.out.format("%-10s %-20s %-20s %-20s %-20s %-20s\n",this.getId(),this.getEpisodeName(),this.getPodcastName(),this.getCelebName(),
                this.getDuration(),this.getDOP());
        System.out.println("------------------------------------------------------------------------------------------------------------");

    }

    //to display podcast menu
    public void displayPodcastMenu()
    {
        System.out.format("%-15s %-15s %10s\n",this.getPodcastID(),this.getPodcastName(),this.getEpisodes());
        System.out.println("------------------------------------------------------------------------------------------------------------");

    }

}
