package thread;

import java.util.*;
import gui.*;
import grid.*;
import tile.*;
import organism.Organism;
import organism.zombie.*;
import java.io.*;

public class RunnableZombieSpawn implements Runnable, Serializable {
    private int gametimer;
    private Lawn mainlawn;
    private int zombieCount;
    private int gargantuarCount;
    private int max_zombies = 10;
    private int probability = 3;
    private boolean flag = false;

    private Random flagRand = new Random();
    private int maxStartFlag = 50, minStartFlag = 40;
    private int startFlag = (flagRand.nextInt(maxStartFlag - minStartFlag + 1) + minStartFlag);
    private int maxFlagDuration = 20, minFlagDuration = 10;
    private int endFlag = startFlag - (flagRand.nextInt(maxFlagDuration - minFlagDuration + 1) + minFlagDuration);

    private Random randSelector = new Random();
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
                // zombieCount = 0;

                gargantuarCount = 0;
                
                while(gametimer > 0){
                    int currentZombies = 0;
                    int currentGargantuar = 0;
                    for (ArrayList<Tile> alt : mainlawn.getLand()){
                        for (Tile t : alt){
                            if (t.hasZombie()) {
                                // Zombie z = t.getZombie();
                                ArrayList<Zombie> alz = t.getZombies();
                                for (Zombie z : alz){
                                    if (z instanceof Gargantuar) {
                                        currentGargantuar++;
                                    }
                                }
                                currentZombies += alz.size();
                            }
                        }
                    }
                    zombieCount = currentZombies;
                    gargantuarCount = currentGargantuar;
                    
                    if (ThreadManager.getInstance().getRunnableGameTimer().getCurrentGameTime() <= 200 && ThreadManager.getInstance().getRunnableGameTimer().getCurrentGameTime() >= 40) { // for debugging purposes ini diest 199 aja ya -Dama
                        setFlag((ThreadManager.getInstance().getRunnableGameTimer().getCurrentGameTime() <= startFlag) && (ThreadManager.getInstance().getRunnableGameTimer().getCurrentGameTime() >= endFlag));
                        int spawned = 0;
                        for (int i = 0; i < 6; i++) {
                            if (((spawnRand.nextInt(maxSpawn - minSpawn + 1) + minSpawn) <= probability) && (zombieCount+spawned < max_zombies)) {
                                // int num = spawnRand.nextInt(0,5);
                                if (ThreadManager.getInstance().getRunnableGameTimer().getCurrentGameTime() < 100) {
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
                                    if (!(gargantuarCount < (max_zombies / 2))){
                                        while (spawn instanceof Gargantuar) {
                                            spawn = selectZombie((randSelector.nextInt(maxSelection - minSelection + 1) + minSelection));
                                        }
                                    }
                                }
                                mainlawn.getLand().get(i).get(10).addZombie(spawn);
                                spawned += 1;
                                // zombieCount++;
                                System.out.println("New " + spawn.getName() + " spawned at: (" + i + ", 10)");
                            } else {
                                // System.out.println("Zombie not spawned");
                            }
                        }
                        // System.out.println();
                        System.out.println("Zombie Count: " + zombieCount);
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

    public void setFlag(boolean setFlag){
        if (setFlag == true){
            max_zombies = 25;
            probability = 5;
            flag = setFlag;
        } else {
            max_zombies = 10;
            probability = 3;
            flag = setFlag;
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

    public void endCurrentZombieSpawn() {
        gametimer = 0;
        mainlawn = null;
    }
} 