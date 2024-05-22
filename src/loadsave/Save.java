package loadsave;

import grid.*;
import organism.*;
import sun.*;
import thread.*;
import tile.*;
import gui.*;

import java.io.*;
import java.util.*;

public class Save {
    private static boolean hasSaved = false;

    public static void save(String fileName, Lawn mainlawn,
    ArrayList<Runnable> threads, Deck deck) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            System.out.println("Threads in manager (save): " + threads.size());
            out.writeObject(mainlawn);
            for (Runnable r : threads) {
                if (r instanceof RunnableZombieSpawn) {
                    out.writeObject(r);
                }
            }
            for (Runnable r : threads) {
                if (r instanceof RunnableGenerateSun) {
                    out.writeObject(r);
                }
            }
            for (Runnable r : threads) {
                if (r instanceof RunnableGameTimer) {
                    out.writeObject(r);
                }
            }
            out.writeObject(deck);
            System.out.println("Game saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        hasSaved = true;
    }

    public static void saveFrame(String fileName, MyFrame frame){
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(frame);
            System.out.println("Game saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean getHasSaved() {
        return hasSaved;
    }
}