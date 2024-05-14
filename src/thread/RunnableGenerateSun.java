package thread;

import java.util.Random;
import gui.*;
import sun.*;

public class RunnableGenerateSun implements Runnable {
    private static int sundrop;
    
    public RunnableGenerateSun(int sundrop){
        this.sundrop = sundrop;
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        
            while(sundrop > 0){
                try {
                Random rand = new Random();
                int berkurang = rand.nextInt(5000, 10000);
                Thread.sleep(berkurang);
                Sun.generateSun();
                sundrop -= (berkurang/1000);
                
            }
            
        catch (InterruptedException e) {
            e.printStackTrace();
        } 
    }  }  

    public static int getCurrentSundrop(){
        return sundrop;
    }

    public static void endCurrentSundrop() {
        sundrop = 0;
    }
}
