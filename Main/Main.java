package Main;

import Dao.DAO;
import Dao.podcastData;
import Dao.songData;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    static DAO access = new DAO();

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        try (Scanner sc = new Scanner(System.in).useDelimiter("\n")) {
            songData st = new songData();
            podcastData pd = new podcastData();
            executionProcess ep = new executionProcess();
            List<songData> L1 = access.storeSongTable(st);
            List<podcastData> L2 = access.podcastDataTable(pd);

            ep.displayMain();
            System.out.println("Enter your choice");
            switch (ep.choose()) {
                case 1 -> {
                    ep.displayMenuSongs();
                    switch (ep.choose()) {
                        case 1 ->{
                            try {
                                List<songData>list=ep.customSearchSongByName(L1, ep.cSearchType(), sc.nextLine());
                                if(list.isEmpty())
                                    throw new RuntimeException();
                                else ep.printSongData(list);
                            }catch (RuntimeException e){
                                System.out.println("No Data Found ☹️");
                            }
                        }

                        case 2 -> {
                            try{
                                List<songData>list=ep.customSearchSongByAlbum(L1, ep.cSearchType(), sc.nextLine());
                                if(list.isEmpty())
                                    throw new RuntimeException();
                                else ep.printSongData(list);
                            }catch (RuntimeException e){
                                System.out.println("No Data Found ☹️");
                            }
                        }

                        case 3 -> {
                            try {
                                List<songData> list = ep.customSearchSongByArtist(L1, ep.cSearchType(), sc.nextLine());
                                if (list.isEmpty())
                                    throw new RuntimeException();
                                else ep.printSongData(list);
                            }catch(RuntimeException e){
                                System.out.println("No Data Found ☹️");
                            }
                        }

                        case 4 -> {
                            try{
                                List<songData>list=ep.customSearchSongByGenre(L1, ep.cSearchType(), sc.nextLine());
                                if(list.isEmpty())
                                    throw new RuntimeException();
                                else ep.printSongData(list);
                            }catch (RuntimeException e){
                                System.out.println("No Data found ☹️");
                            }
                        }
                    }
                }
                case 2 -> {
                    ep.displayMenuPodcast();
                    switch (ep.choose()) {
                        case 1 -> {
                            try{
                                List<podcastData>list=ep.customSearchPodcastByName(L2, ep.cSearchType(), sc.nextLine());
                                if(list.isEmpty())
                                    throw new RuntimeException();
                                else ep.printPodcastData(list);
                            }catch (RuntimeException e){
                                System.out.println("No Data Found ☹️");
                            }
                        }
                        case 2 -> {
                            try{
                                List<podcastData>list=ep.customSearchPodcastByEpisodeName(L2, ep.cSearchType(), sc.nextLine());
                                if(list.isEmpty())
                                    throw new RuntimeException();
                                else ep.printPodcastData(list);
                            }catch (RuntimeException e){
                                System.out.println("No Data Found ☹️");
                            }
                        }
                        case 3 -> {
                            try{
                                List<podcastData>list=ep.customSearchPodcastByNumberOfEpisodes(L2, sc.nextInt());
                                if(list.isEmpty())
                                    throw new RuntimeException();
                                else ep.printPodcastData(list);
                            }catch (RuntimeException e){
                                System.out.println("No Data Found ☹️");
                            }
                        }
                        case 4 -> {
                            try{
                                List<podcastData>list=ep.customSearchPodcastByCelebrityName(L2, ep.cSearchType(), sc.nextLine());
                                if(list.isEmpty())
                                    throw new RuntimeException();
                                else ep.printPodcastData(list);
                            }catch (RuntimeException e){
                                System.out.println("No Data Found ☹️");
                            }
                        }
                    }
                }
                case 3 -> {
                    ep.displayLogin();
                    int o = ep.choose();
                    int v;
                    if (o == 1) {
                        try {
                            System.out.println("insert Id");
                            v = sc.nextInt();
                            System.out.println("enter password");
                            String p = sc.next();
                            if (!access.login(v, p))
                                throw new RuntimeException();
                        } catch (Exception e) {
                            System.out.println("invalid details ☹️");
                            return;
                        }

                        System.out.println("welcome to Jukebox");
                        while(true) {
                            boolean el = true;
                            ep.displayMenuForLoggedIn();
                            switch (ep.choose()) {
                                case 0 -> el = false;
                                case 1 -> {
                                    ep.displayMenuSongs();
                                    switch (ep.choose()) {
                                        case 1 -> {
                                            try {
                                                List<songData> list = ep.customSearchSongByName(L1, ep.cSearchType(), ep.cSearch());
                                                if (list.isEmpty())
                                                    throw new RuntimeException();
                                                else
                                                    ep.printSongData(list);
                                            } catch (RuntimeException e) {
                                                System.out.println("data not found ☹️");
                                                return;
                                            }

                                            if (ep.addToPlaylist() == 1) {
                                                System.out.println("select Track ID to add in your playlist");
                                                int tid = ep.choose();
                                                List<songData> newList = ep.playListData(L1, tid);
                                                if (ep.availablePlaylist() == 1) {
                                                    List<String> avPlaylist = access.viewPlayListTable(v);
                                                    avPlaylist.forEach(System.out::println);
                                                    access.insertIntoPlaylist(newList, ep.selectPlaylist());
                                                }
                                            }
                                        }

                                        case 2 -> {
                                            try {
                                                List<songData> list = ep.customSearchSongByAlbum(L1, ep.cSearchType(), ep.cSearch());
                                                if (list.isEmpty())
                                                    throw new RuntimeException();
                                                else
                                                    ep.printSongData(list);
                                            } catch (RuntimeException e) {
                                                System.out.println("No data found ☹️");
                                                return;
                                            }

                                            if (ep.addToPlaylist() == 1) {
                                                System.out.println("select Track ID to add in your playlist");
                                                int tid = ep.choose();
                                                List<songData> newList = ep.playListData(L1, tid);
                                                if (ep.availablePlaylist() == 1) {
                                                    List<String> avPlaylist = access.viewPlayListTable(v);
                                                    avPlaylist.forEach(System.out::println);
                                                    access.insertIntoPlaylist(newList, ep.selectPlaylist());
                                                }
                                            }
                                        }

                                        case 3 -> {
                                            try {
                                                List<songData> list = ep.customSearchSongByArtist(L1, ep.cSearchType(), ep.cSearch());
                                                if (list.isEmpty())
                                                    throw new RuntimeException();
                                                else
                                                    ep.printSongData(list);
                                            } catch (RuntimeException e) {
                                                System.out.println("data not found");
                                            }

                                            if (ep.addToPlaylist() == 1) {
                                                System.out.println("select Track ID to add in your playlist");
                                                int tid = ep.choose();
                                                List<songData> newList = ep.playListData(L1, tid);
                                                if (ep.availablePlaylist() == 1) {
                                                    List<String> avPlaylist = access.viewPlayListTable(v);
                                                    avPlaylist.forEach(System.out::println);
                                                    access.insertIntoPlaylist(newList, ep.selectPlaylist());
                                                }
                                            }
                                        }

                                        case 4 -> {
                                            try {
                                                List<songData> list = ep.customSearchSongByGenre(L1, ep.cSearchType(), ep.cSearch());
                                                if (list.isEmpty())
                                                    throw new RuntimeException();
                                                else
                                                    ep.printSongData(list);
                                            } catch (RuntimeException e) {
                                                System.out.println("no data found");
                                            }
                                        }
                                    }
                                }

                                case 2 -> {
                                    ep.displayMenuPodcast();
                                    switch (ep.choose()) {
                                        case 1 -> {
                                            try {
                                                List<podcastData> list = ep.customSearchPodcastByName(L2, ep.cSearchType(), ep.cSearch());
                                                if (list.isEmpty())
                                                    throw new RuntimeException();
                                                else
                                                    ep.printPodcastData(list);
                                            } catch (RuntimeException e) {
                                                System.out.println("No data found");
                                            }
                                        }

                                        case 2 -> {
                                            try {
                                                List<podcastData> list = ep.customSearchPodcastByEpisodeName(L2, ep.cSearchType(), ep.cSearch());
                                                if (list.isEmpty())
                                                    throw new RuntimeException();
                                                else
                                                    ep.printPodcastData(list);
                                            } catch (RuntimeException e) {
                                                System.out.println("No data found");
                                            }
                                        }

                                        case 3 -> {
                                            try {
                                                List<podcastData> list = ep.customSearchPodcastByNumberOfEpisodes(L2, sc.nextInt());
                                                if (list.isEmpty())
                                                    throw new RuntimeException();
                                                else
                                                    ep.printPodcastData(list);
                                            } catch (RuntimeException e) {
                                                System.out.println("No data Found");
                                            }
                                        }
                                        case 4 -> {
                                            try {
                                                List<podcastData> list = ep.customSearchPodcastByCelebrityName(L2, ep.cSearchType(), ep.cSearch());
                                                if (list.isEmpty())
                                                    throw new RuntimeException();
                                                else
                                                    ep.printPodcastData(list);
                                            } catch (RuntimeException e) {
                                                System.out.println("No Data Found");
                                            }
                                        }
                                    }
                                }

                                case 3 -> {
                                    try {
                                        access.createPlaylist(ep.createNewPlayListName(), v);
                                        throw new SQLException();
                                    } catch (SQLException e) {
                                        System.out.println("please select other name ◀");
                                    }
                                }

                                case 4 -> {
                                    if (ep.availablePlaylist() == 1) {
                                        List<String> avPlaylist = access.viewPlayListTable(v);
                                        avPlaylist.forEach(System.out::println);
                                    }
                                }

                                case 5 -> {

                                        List<String> avPlaylist = access.viewPlayListTable(v);
                                        avPlaylist.forEach(System.out::println);
                                        System.out.println("Enter PlayList Name to Play the songs");
                                        List<songData> list = access.viewUserPlaylist(sc.next());
                                        ep.printSongData(list);
                                        System.out.println("Insert Track ID to play");
                                        int tID = sc.nextInt();
                                        try {
                                            SimpleAudioPlayer SAP = new SimpleAudioPlayer(ep.playSong(list, tID));
                                            SAP.play();
                                            SAP.playerOptionsDisplay();
                                            switch (ep.choose()) {
                                                case 1 -> SAP.pause();
                                                case 2 -> SAP.resumeAudio(ep.playSong(list, tID));
                                                case 3 -> SAP.restart(ep.playSong(list, tID));
                                                case 4 -> SAP.stop();
                                                case 5 -> SAP.forward();
                                                case 6 -> SAP.reverse();
                                                case 7 -> {
                                                    Collections.shuffle(list);
                                                    ep.printSongData(list);
                                                }
                                            }
                                        } catch (Exception ex) {
                                            System.out.println("Error with playing sound.");
                                            ex.printStackTrace();
                                        }
                                }
                            }
                            if (!el) break;
                        }
                    }
                    if (o == 2) access.usersAccess(ep.userName(), ep.password());
                }
            }
        }
    }
}
