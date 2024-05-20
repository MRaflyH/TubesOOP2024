package loadsave;

import grid.*;
import organism.*;
import sun.*;
import thread.*;
import tile.*;

import java.io.*;
import java.util.*;

public class Save {
    public static void save(String fileName, Lawn mainlawn,
    ArrayList<Runnable> threads) {
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
            // out.writeObject(zSpawn);
            System.out.println("Game saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}