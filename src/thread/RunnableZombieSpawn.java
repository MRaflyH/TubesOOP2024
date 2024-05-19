package thread;

import java.util.*;
import gui.*;
import grid.Lawn;
import organism.zombie.*;

public class RunnableZombieSpawn implements Runnable {
    private int gametimer;
    private Lawn mainlawn;
    private static int zombieCount;
    private static int gargantuarCount;
    private static int max_zombies = 10;
    private static int probability = 3;
    private static boolean flag = false;

    private static Random flagRand = new Random();
    private static int maxStartFlag = 50, minStartFlag = 40;
    private static int startFlag = (flagRand.nextInt(maxStartFlag - minStartFlag + 1) + minStartFlag);
    private static int maxFlagDuration = 20, minFlagDuration = 10;
    private static int endFlag = startFlag - (flagRand.nextInt(maxFlagDuration - minFlagDuration + 1) + minFlagDuration);

    private static Random randSelector = new Random();
    int minSelection = 1, maxSelection = 6;

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
                gargantuarCount = 0;
                while(gametimer > 0){
                    if (ThreadManager.getRunnableGameTimer().getCurrentGameTime() <= 200 && ThreadManager.getRunnableGameTimer().getCurrentGameTime() >= 40) { // for debugging purposes ini diest 199 aja ya -Dama
                        RunnableZombieSpawn.setFlag((ThreadManager.getRunnableGameTimer().getCurrentGameTime() <= startFlag) && (ThreadManager.getRunnableGameTimer().getCurrentGameTime() >= endFlag));
                        for (int i = 0; i < 6; i++) {
                            if (((spawnRand.nextInt(maxSpawn - minSpawn + 1) + minSpawn) <= probability) && (zombieCount < max_zombies)) {
                                // int num = spawnRand.nextInt(0,5);
                                if (ThreadManager.getRunnableGameTimer().getCurrentGameTime() < 100) {
                                    minSelection = 7;
                                    maxSelection = 10;
                                }
                                Zombie spawn = selectZombie((randSelector.nextInt(maxSelection - minSelection + 1) + minSelection));
                                if (i == 2 || i == 3) {
                                    while (!spawn.getIsAquatic()){
                                        spawn = selectZombie((randSelector.nextInt(maxSelection - minSelection + 1) + minSelection));
                                    }
                                } else {
                                    while (spawn.getIsAquatic()){
                                        spawn = selectZombie((randSelector.nextInt(maxSelection - minSelection + 1) + minSelection));
                                    }
                                }
                                if (spawn instanceof Gargantuar) {
                                    if (gargantuarCount < (max_zombies / 2)){
                                        gargantuarCount++;
                                    } else {
                                        while (spawn instanceof Gargantuar) {
                                            spawn = selectZombie((randSelector.nextInt(maxSelection - minSelection + 1) + minSelection));
                                        }
                                    }
                                }
                                mainlawn.getLand().get(i).get(10).addZombie(spawn);
                                zombieCount++;
                                System.out.println("New " + spawn.getName() + " spawned at: (" + i + ", 10)");
                            } else {
                                // System.out.println("Zombie not spawned");
                            }
                        }
                        // System.out.println();
                        //System.out.println("Zombie Count: " + zombieCount);
                        // System.out.println("max_zombies: " + max_zombies);
                        // System.out.println("probability: " + probability);
                        // System.out.println("start flag at: " + startFlag);
                        // System.out.println("end flag at: " + endFlag);
                        // System.out.println("flag: " + flag);
                        // System.out.println();
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

    public Zombie selectZombie(int index){
        switch (index){
            case 1:
                return new DuckyTubeZombie();
            case 2:
                return new DolphinRiderZombie();
            case 3:
                return new NormalZombie();
            case 4:
                return new ConeheadZombie();
            case 5:
                return new BucketheadZombie();
            case 6:
                return new PoleVaultingZombie();
            case 7:
                return new NewspaperZombie();
            case 8:
                return new Gargantuar();
            case 9:
                return new JackInTheBoxZombie();
            case 10:
                return new SnorkelZombie();
            default:
                return new NormalZombie();
        }
    }
} 