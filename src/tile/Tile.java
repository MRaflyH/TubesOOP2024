package tile;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;

import organism.plant.*;
import organism.zombie.*;

public abstract class Tile implements Serializable {
    private String name;
    private Plant plant;
    private ArrayList<Zombie> zombies;

    public Tile(String name) {
        this.name = name;
        plant = null;
        zombies = new ArrayList<Zombie>();
    }

    public void addPlant(Plant plant) {
        if (!hasPlant()) {
            this.plant = plant;
        }
        else {
            // exception
        }
    }

    public void removePlant() {
        if (hasPlant()) {
            plant = null;
        }
        else {
        }
    }

    public void addZombie(Zombie zombie) {
        if (!zombies.contains(zombie)) {
            zombies.add(zombie);
        }
        else {
            // exception
        }    
    }

    public void removeZombie(Zombie zombie) {
        if (zombies.contains(zombie)) {
            zombies.remove(zombie);
        }
        else {
            // exception
        }
    }

    public void moveZombie(Zombie zombie, Tile tile) {
        // Timer spawn = new Timer();
        // TimerTask spawnTask = new TimerTask(){

        //     @Override
        //     public void run() {
        //         // TODO Auto-generated method stub
        //         tile.addZombie(zombie);
        //         removeZombie(zombie);
        //     }
            
        // };
        // spawn.schedule(spawnTask, 0, 5000);

        // ini aku comment out karena ga make sense?? -dama

        tile.addZombie(zombie);
        this.removeZombie(zombie);
       
    }

    public boolean hasPlant() {
        return plant != null;
    }

    public boolean hasZombie() {
        return !zombies.isEmpty();
    }

    public String getName() {
        return name;
    }

    public Plant getPlant() {
        return plant;
    }

    public ArrayList<Zombie> getZombies() {
        return zombies;
    }
}
