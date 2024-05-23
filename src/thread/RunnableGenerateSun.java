package thread;

import java.util.Random;
import sun.*;
import java.io.*;

public class RunnableGenerateSun implements Runnable, Serializable {
    private int sundrop;
    
    public RunnableGenerateSun(int sundrop){
        this.sundrop = sundrop;
    }
    @Override
    public void run() {
            while(sundrop > 0){
                try {
                Random rand = new Random();
                int berkurang = rand.nextInt(5000, 10000);
                Thread.sleep(berkurang);
                Sun.getInstance().generateSun();
                sundrop -= (berkurang/1000);
                
            }
            
        catch (InterruptedException e) {
            e.printStackTrace();
        } 
    }  }  

    public int getCurrentSundrop(){
        return sundrop;
    }

    public void endCurrentSundrop() {
        sundrop = 0;
    }
}
