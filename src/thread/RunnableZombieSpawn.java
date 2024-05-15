package thread;

import java.util.Random;

import grid.Lawn;
import organism.zombie.NormalZombie;

public class RunnableZombieSpawn implements Runnable {
    private int gametimer;
    private Lawn mainlawn;

    public RunnableZombieSpawn(int gametimer, Lawn mainlawn){
        this.gametimer = gametimer;
        this.mainlawn = mainlawn;
    }
    
    @Override
    public void run() {
        try {
                // !! Ini Logic untuk Zombie Spawn !! 
                // Random spawn zombie
                Random rand = new Random();
                int max = 10, min = 1;
                int zombieCount = 0;
                while(gametimer > 0){
                    if (ThreadManager.getRunnableGameTimer().getCurrentGameTime() < 180) {
                        for (int i = 0; i < 6; i++) {
                            System.out.print("Row" + i + ": ");
                            if (((rand.nextInt(max - min + 1) + min) > 3) && (zombieCount < 10)) {
                                System.out.println("Zombie spawned");
                                mainlawn.getLand().get(i).get(8).addZombie(new NormalZombie());
                                zombieCount++;
                            } else {
                                System.out.println("Zombie not spawned");
                            }
                        }
                        System.out.println("Zombie Count: " + zombieCount);
                        System.out.println();
                    }
                    Thread.sleep(1000);
                }
               
                
                
        } catch (InterruptedException e) {
            // TODO: handle exception
        }
    }
    
} 