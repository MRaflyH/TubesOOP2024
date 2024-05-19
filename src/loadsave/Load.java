package loadsave;

import grid.*;
import organism.*;
import sun.*;
import thread.*;
import tile.*;

import java.io.*;

public class Load {
    public static boolean load(String fileName, LawnHolder holdlawn) {
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            holdlawn.lawn = (Lawn) in.readObject();
            System.out.println("Lawn object deserialized: " + holdlawn.lawn);
            return true;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static class LawnHolder {
        public static Lawn lawn = null;
    }
}