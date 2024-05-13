package thread;

import java.util.Random;
import gui.*;
import sun.*;

public class RunnableGenerateSun implements Runnable {
    private int sundrop = 100;
   
    
    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            while(sundrop > 0){
                Random rand = new Random();
                int berkurang = rand.nextInt(5000, 10000);
                Thread.sleep(berkurang);
                Sun.generateSun();
                System.out.println(Sun.getTotalSun());
                System.out.println(sundrop);
                sundrop -= (berkurang/1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }    
}
