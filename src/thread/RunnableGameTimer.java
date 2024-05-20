package thread;

import java.util.Random;
import java.util.Timer;
import java.io.*;

public class RunnableGameTimer implements Runnable, Serializable{
    public int gametimer;
   

    public RunnableGameTimer(int gametime){
        gametimer = gametime;
    }
    
    @Override
    public void run() {
            while(gametimer > 0){
                try {
                Thread.sleep(1000);
                gametimer -= 1;
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                } 
        }
    }
    public int getCurrentGameTime(){
        return gametimer;
    }

    public void endCurrentGameTime() {
        gametimer = 0;
    }
    
}
