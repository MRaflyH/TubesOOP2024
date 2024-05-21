
import javax.swing.SwingUtilities;

import gui.*;
import sun.Sun;
import thread.RunnableGenerateSun;
import exception.*;
import grid.*;
import gui.*;
import organism.*;
import organism.zombie.DolphinRiderZombie;
import tile.*;
import loadsave.*;

public class Main {
    public static void main(String[] args) {
        MyFrame m;

        if (Load.loadFrame("testSaveFrame.ser")) {
            m = Load.LoadHolder.myFrame;
            System.out.println("Loaded frame");
        } else {
            m = new MyFrame();
        }

        if (Save.getHasSaved()){
            Save.saveFrame("testSaveFrame.ser", m);
            System.out.println("Saved frame");
        }
    }
}
