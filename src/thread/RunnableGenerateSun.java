package thread;

import java.util.Random;
import gui.*;
import sun.*;

public class RunnableGenerateSun implements Runnable {
    private int gametotal = 200;
    private MyFrame m;
   
    
    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            while(gametotal != 0){
                gametotal -= 1;
                Random rand = new Random();
                Thread.sleep(rand.nextInt(5000, 10000));
                Sun.generateSun();
                System.out.println(Sun.getTotalSun());

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }    
}
