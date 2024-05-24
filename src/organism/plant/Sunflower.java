package organism.plant;
import java.util.Random;

import sun.Sun;
import thread.*;
public class Sunflower extends Plant {
    private int remainingTime;
    private volatile boolean isSunflowerExist = true;
    public Sunflower() {
        super("Sunflower", 100, 0, 0, false, 50, 0, 10); 
        produceSun();
        for(Runnable run : ThreadManager.getInstance().getList()){
            if(run instanceof RunnableGenerateSun){
                remainingTime = ((RunnableGenerateSun)run).getCurrentSundrop();
            }
        }     
    }

    public void produceSun(){
        Thread t = new Thread(new Runnable(){
            
            @Override
            public void run() {
                try {
                    while(remainingTime > 0 && isSunflowerExist){
                        System.out.println("This is from sunflower");
                        System.out.println("remaining time: " + remainingTime);
                        if (ThreadManager.getInstance().getList().size() > 0) {
                            for(Runnable run : ThreadManager.getInstance().getList()){
                                if(run instanceof RunnableGenerateSun){
                                    remainingTime = ((RunnableGenerateSun)run).getCurrentSundrop();
                                }
                            }
                            Sun.getInstance().generateSun();
                            Thread.sleep(3000);
                            System.out.println("Sun produced");
                            // remainingTime -= 3;
                        } else {
                            remainingTime = 0;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Sunflower Masuk Exception");
                    // e.printStackTrace();
                    // remainingTime = 0;
                }
            }
            
        });
        t.start();
    }

    public void stopProduce(){
        isSunflowerExist = false;
    }

}
