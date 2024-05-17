package thread;

import java.util.*;
import gui.*;
import grid.Lawn;
import organism.zombie.*;

public class RunnableZombieSpawn implements Runnable {
    private int gametimer;
    private Lawn mainlawn;
    private static int zombieCount;
    public RunnableZombieSpawn(int gametimer, Lawn mainlawn){
        this.gametimer = gametimer;
        this.mainlawn = mainlawn;
        // zombieQueue = -1;
    }
    
    @Override
    public void run() {
        try {
                // !! Ini Logic untuk Zombie Spawn !! 
                // Random spawn zombie
                Random rand = new Random();
                int max = 10, min = 1;
                zombieCount = 0;
                while(gametimer > 0){
                    if (ThreadManager.getRunnableGameTimer().getCurrentGameTime() < 180 && ThreadManager
                            .getRunnableGameTimer().getCurrentGameTime() > 40) { // for debugging purposes ini diest 199 aja ya -Dama
                        for (int i = 0; i < 6; i++) {
                            //System.out.print("Row" + i + ": ");
                            if (((rand.nextInt(max - min + 1) + min) <= 3) && (zombieCount < 10)) {
                                // System.out.println("Zombie spawned");
                                // int num = rand.nextInt(0,5);
                                mainlawn.getLand().get(i).get(10).addZombie(new NormalZombie());
                                zombieCount++;
                            } else {
                                // System.out.println("Zombie not spawned");
                            }
                        }
                        //System.out.println("Zombie Count: " + zombieCount);
                        //System.out.println();
                    }
                    Thread.sleep(1000);
                }
               
                
                
        } catch (InterruptedException e) {
            // TODO: handle exception
        }
    }

    // public int getZombieQueue(){
    //     return zombieQueue;
    // }

    // public void setZombieQueue(int index) {
    //     zombieQueue = index;
    // }

    public int getZombieCount(){
        return zombieCount;
    }
} 