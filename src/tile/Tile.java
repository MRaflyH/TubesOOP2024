package tile;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import organism.plant.*;
import organism.zombie.*;

public abstract class Tile {
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
            // exception
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

    public void moveZombie(Zombie arrayList, Tile tile) {
        Timer spawn = new Timer();
        TimerTask spawnTask = new TimerTask(){

            @Override
            public void run() {
                // TODO Auto-generated method stub
                removeZombie(arrayList);
                tile.addZombie(arrayList);
            }
            
        };
        spawn.schedule(spawnTask, 0, 5000);
       
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
