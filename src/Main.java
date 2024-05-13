
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

public class Main {
    public static void main(String[] args) {
        new MyFrame();
        DolphinRiderZombie d = new DolphinRiderZombie();
        System.out.println(d.getHealth());
    }
}
