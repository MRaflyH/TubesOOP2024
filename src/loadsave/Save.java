package loadsave;

import grid.*;
import organism.*;
import sun.*;
import thread.*;
import tile.*;

import java.io.*;

public class Save {
    public static void save(String fileName, Lawn mainlawn) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(mainlawn);
            // out.writeObject(deck);
            System.out.println("Game saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}