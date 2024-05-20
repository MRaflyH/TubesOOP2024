package loadsave;

import grid.*;
import organism.*;
import sun.*;
import thread.*;
import tile.*;

import java.io.*;

public class Load {
    public static boolean load(String fileName, LoadHolder holder) {
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            holder.lawn = (Lawn) in.readObject();
            // holder.threadManager = (ThreadManager) in.readObject();
            holder.zomSpawn = (RunnableZombieSpawn) in.readObject();
            holder.genSun = (RunnableGenerateSun) in.readObject();
            holder.gameTimer = (RunnableGameTimer) in.readObject();
            System.out.println("Lawn object deserialized: " + holder.lawn);
            System.out.println("RunnableZombieSpawn object deserialized: " + holder.zomSpawn);
            System.out.println("RunnableGenerateSun object deserialized: " + holder.genSun);
            System.out.println("RunnableGameTimer object deserialized: " + holder.gameTimer);
            return true;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static class LoadHolder {
        public static Lawn lawn = null;
        public static ThreadManager threadManager = null;
        public static RunnableZombieSpawn zomSpawn = null;
        public static RunnableGenerateSun genSun = null;
        public static RunnableGameTimer gameTimer = null;
    }
}