package organism.plant;
import java.util.Random;

import sun.Sun;
import thread.RunnableGameTimer;
public class Sunflower extends Plant {
    
    public Sunflower() {
        super("Sunflower", 100, 0, 0, false, 50, 0, 10);
        System.out.println("Sunflower Planted!");
    }

    public void produceSun(){
        Thread t = new Thread(new Runnable(){
            int remainingTime = RunnableGameTimer.getCurrentGameTime();
            @Override
            public void run() {
                try {
                    while(remainingTime > 0){
                        Sun.generateSun();
                        Thread.sleep(3000);
                        System.out.println("Sun produced");
                        remainingTime -= 3;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
        });
        t.start();
    }

}
