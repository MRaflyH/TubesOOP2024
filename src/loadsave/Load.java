package loadsave;

import grid.*;
import organism.*;
import sun.*;
import thread.*;
import tile.*;
import gui.*;

import java.io.*;

public class Load {

    // private static boolean hasLoaded = false;

    public static class LoadHolder {
        public static Lawn lawn = null;
        public static ThreadManager threadManager = null;
        public static RunnableZombieSpawn zomSpawn = null;
        public static RunnableGenerateSun genSun = null;
        public static RunnableGameTimer gameTimer = null;
        public static Deck gameDeck = null;
        public static Sun gameSun = null;

        // public static MyFrame myFrame = null;
    }

    public static boolean load(String fileName) {
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            Load.LoadHolder.lawn = (Lawn) in.readObject();
            // Load.LoadHolder.threadManager = (ThreadManager) in.readObject();
            Load.LoadHolder.zomSpawn = (RunnableZombieSpawn) in.readObject();
            Load.LoadHolder.genSun = (RunnableGenerateSun) in.readObject();
            Load.LoadHolder.gameTimer = (RunnableGameTimer) in.readObject();
            Load.LoadHolder.gameDeck = (Deck) in.readObject();
            Load.LoadHolder.gameSun = (Sun) in.readObject();
            // System.out.println("Lawn object deserialized: " + Load.LoadHolder.lawn);
            // System.out.println("RunnableZombieSpawn object deserialized: " + Load.LoadHolder.zomSpawn);
            // System.out.println("RunnableGenerateSun object deserialized: " + Load.LoadHolder.genSun);
            // System.out.println("RunnableGameTimer object deserialized: " + Load.LoadHolder.gameTimer);
            System.out.println("Load Success");
            return true;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Load Failed");
            return false;
        }
    }

    // public static boolean loadFrame(String fileName) {
    //     try (FileInputStream fileIn = new FileInputStream(fileName);
    //          ObjectInputStream in = new ObjectInputStream(fileIn)) {
    //         Load.LoadHolder.myFrame = (MyFrame) in.readObject();
    //         hasLoaded = true;
    //         return true;
    //     } catch (IOException | ClassNotFoundException e) {
    //         e.printStackTrace();
    //         return false;
    //     }
    // }

    // public static boolean getHasLoaded(){
    //     return hasLoaded;
    // }

}