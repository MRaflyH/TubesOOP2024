package tile;

import java.util.ArrayList;
import java.util.Iterator;

import java.io.Serializable;

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
        Iterator<Zombie> iterzombie = zombies.iterator();
        while (iterzombie.hasNext()) {
            Zombie z = iterzombie.next();
            if (z.equals(zombie)) {
                iterzombie.remove();
            }
        }
    }

    public void removeAllZombie() {
        zombies.clear();
    }

    public void moveZombie(Zombie zombie, Tile tile) {

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
