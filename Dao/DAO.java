package Dao;
import Connection.connectionSetup;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class DAO {
    Connection con;
    PreparedStatement ps;
    static int id;
    static String name;

    //usersTable
    public void usersAccess(String name, String password) throws SQLException, IOException, ClassNotFoundException {
        con = connectionSetup.getConnection();
        ps = con.prepareStatement("insert into users(name,password) values(?,MD5(?))");
        ps.setString(1, name);
        ps.setString(2, password);
        ps.execute();
        ps.close();
        con.close();
    }

    //authenticate
    public boolean login(int id,String password) throws SQLException, IOException, ClassNotFoundException {
        con = connectionSetup.getConnection();
        ps=con.prepareStatement("Select Exists(SELECT*from USERS WHERE password=MD5(?))");
        ps.setString(1,password);
       ResultSet rs= ps.executeQuery();
       rs.next();

       return rs.getInt(1) != 0;
    }

    //getting data from database of songTable
    public List<songData> storeSongTable(songData st) throws SQLException, IOException, ClassNotFoundException {
        con = connectionSetup.getConnection();
         ps = con.prepareStatement("select songID,song,Duration,Genre,ArtistName,url,Album from songTable " +
                "inner join artist on songTable.a_id=artist.artistID");
        ResultSet rs = ps.executeQuery();

        List<songData> list = new ArrayList<>();
        while (rs.next()) {
            songData insert = new songData(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(6), rs.getString(7),rs.getString(5));
            list.add(insert);
        }
        return list;
    }

    //getting data from database of podcast
    public List<podcastData> podcastDataTable(podcastData pd) throws SQLException, IOException, ClassNotFoundException {
        con=connectionSetup.getConnection();
        ps=con.prepareStatement("select id,episode_name,celebrity_name,duration,url,Dop,podcastId,numOfEpisodes,podcast_name"
                + " from podCastDetails inner join podcastTable using (podcastId)");
        ResultSet rs=ps.executeQuery();
        List<podcastData> list=new ArrayList<>();
        while (rs.next()){
            podcastData insert=new podcastData(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
                    rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getString(9));
            list.add(insert);
        }
        return list;
    }
    public List<podcastData> podcastDataList(podcastData pd) throws SQLException {
      ps=con.prepareStatement("select*from podcastTable");
        ResultSet rs=ps.executeQuery();
        List<podcastData> list=new ArrayList<>();
        while (rs.next()){
            podcastData insert=new podcastData(rs.getInt(1),rs.getInt(2),rs.getString(3));
            list.add(insert);
        }
        return list;
    }

    public void createPlaylist(String playListName,int id) throws SQLException {
       ps=con.prepareStatement("insert into playlistTable (playlistName,id) value(?,?)");
       ps.setString(1,playListName);
       ps.setInt(2,id);
        ps.execute();
        ps.close();

       ps=con.prepareStatement("create table IF not exists "+playListName+" (TrackID int not null,TrackName varchar(50) not null,TrackDuration time, " +
               "Genre varchar(20),Url tinytext,Album varchar(20),Artist varchar(20),Constraint unq UNIQUE(TrackName))");
        ps.execute();
        ps.close();
        con.close();

    }

    public void insertIntoPlaylist(List<songData> list,String playlistName) throws SQLException, IOException, ClassNotFoundException {
        con=connectionSetup.getConnection();
         ps=con.prepareStatement("insert into " +playlistName+ "( TrackID,TrackName,TrackDuration,Genre,url,Album,artist) values (?,?,?,?,?,?,?)");
        System.out.println(list.stream().toList().get(0).getSongID());
        ps.setInt(1,list.stream().toList().get(0).getSongID());
        ps.setString(2,list.stream().toList().get(0).getSong());
        ps.setString(3,list.stream().toList().get(0).getDuration());
        ps.setString(4,list.stream().toList().get(0).getGenre());
        ps.setString(5,list.stream().toList().get(0).getUrl());
        ps.setString(6,list.stream().toList().get(0).getAlbum());
        ps.setString(7,list.stream().toList().get(0).getArtist());
        System.out.println("song added");
        ps.execute();

    }

    //to check available playlist for the user
    public List<String> viewPlayListTable(int id) throws SQLException, IOException, ClassNotFoundException {
        con=connectionSetup.getConnection();
        ps=con.prepareStatement("select playListName from playListTable inner join users using(id) where users.id="+id+"");
        ResultSet rs= ps.executeQuery();
        List<String> list=new ArrayList<>();
        while ((rs.next())){
            list.add(rs.getString(1));
        }
     return list;
    }

    //to bring the songs or podcast present in the user defined playlist
    public List<songData> viewUserPlaylist(String name) throws SQLException, IOException, ClassNotFoundException {
        con=connectionSetup.getConnection();
        ps=con.prepareStatement("select*from "+name+" ");
        ResultSet rs=ps.executeQuery();
        List<songData> list=new LinkedList<>();
        while(rs.next()){
            songData insert=new songData(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
                    rs.getString(5),rs.getString(6),rs.getString(7));
            list.add(insert);
        }
        return list;
    }
}

