package thread;

import java.util.Random;
import java.util.Timer;

public class RunnableGameTimer implements Runnable{
    public static int gametimer;
   

    public RunnableGameTimer(int gametime){
        gametimer = gametime;
    }
    
    @Override
    public void run() {
            while(gametimer > 0){
                try {
                Thread.sleep(1000);
                System.out.println("Game tersisa: " + gametimer);
                gametimer -= 1;
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                } 
        }
    }
    public static int getCurrentGameTime(){
        return gametimer;
    }

    public static void endCurrentGameTime() {
        gametimer = 0;
    }
    
}
