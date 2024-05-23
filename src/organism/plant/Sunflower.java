package organism.plant;
import java.util.Random;

import sun.Sun;
import thread.*;
public class Sunflower extends Plant {
    private int remainingTime;
    private volatile boolean isSunflowerExist = true;
    public Sunflower() {
        super("Sunflower", 100, 0, 0, false, 50, 0, 10);
        for(Runnable run : ThreadManager.getInstance().getList()){
            if(run instanceof RunnableGenerateSun){
                remainingTime = ((RunnableGenerateSun)run).getCurrentSundrop();
            }
        }  
        produceSun();     
}

    public void produceSun(){
        Thread t = new Thread(new Runnable(){
            
            @Override
            public void run() {
                try {
                    while(remainingTime > 0 && isSunflowerExist){
                        Sun.getInstance().generateSun();
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

    public void stopProduce(){
        isSunflowerExist = false;
    }

}
