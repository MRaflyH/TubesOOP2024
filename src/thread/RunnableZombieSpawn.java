package thread;

import java.util.*;
import gui.*;
import grid.Lawn;
import organism.zombie.*;

public class RunnableZombieSpawn implements Runnable {
    private int gametimer;
    private Lawn mainlawn;
    private static int zombieCount;
    private static int max_zombies = 10;
    private static int probability = 3;
    private static boolean flag = false;

    private static Random flagRand = new Random();
    private static int maxStartFlag = 50, minStartFlag = 40;
    private static int startFlag = (flagRand.nextInt(maxStartFlag - minStartFlag + 1) + minStartFlag);
    private static int maxFlagDuration = 20, minFlagDuration = 10;
    private static int endFlag = startFlag - (flagRand.nextInt(maxFlagDuration - minFlagDuration + 1) + minFlagDuration);

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
                Random spawnRand = new Random();
                int maxSpawn = 10, minSpawn = 1;
                zombieCount = 0;
                while(gametimer > 0){
                    if (ThreadManager.getRunnableGameTimer().getCurrentGameTime() <= 180 && ThreadManager.getRunnableGameTimer().getCurrentGameTime() >= 40) { // for debugging purposes ini diest 199 aja ya -Dama
                        RunnableZombieSpawn.setFlag((ThreadManager.getRunnableGameTimer().getCurrentGameTime() <= startFlag) && (ThreadManager.getRunnableGameTimer().getCurrentGameTime() >= endFlag));
                        for (int i = 0; i < 6; i++) {
                            //System.out.print("Row" + i + ": ");
                            if (((spawnRand.nextInt(maxSpawn - minSpawn + 1) + minSpawn) <= probability) && (zombieCount < max_zombies)) {
                                // System.out.println("Zombie spawned");
                                // int num = spawnRand.nextInt(0,5);
                                mainlawn.getLand().get(i).get(10).addZombie(new NormalZombie());
                                zombieCount++;
                            } else {
                                // System.out.println("Zombie not spawned");
                            }
                        }
                        System.out.println();
                        //System.out.println("Zombie Count: " + zombieCount);
                        System.out.println("max_zombies: " + max_zombies);
                        System.out.println("probability: " + probability);
                        System.out.println("start flag at: " + startFlag);
                        System.out.println("end flag at: " + endFlag);
                        System.out.println("flag: " + flag);
                        System.out.println();
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

    public static void setFlag(boolean setFlag){
        if (setFlag == true){
            RunnableZombieSpawn.max_zombies = 25;
            RunnableZombieSpawn.probability = 5;
            RunnableZombieSpawn.flag = setFlag;
        } else {
            RunnableZombieSpawn.max_zombies = 10;
            RunnableZombieSpawn.probability = 3;
            RunnableZombieSpawn.flag = setFlag;
        }
        
    }
} 