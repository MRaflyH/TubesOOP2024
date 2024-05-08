package tile;

import java.util.ArrayList;
import organism.plant.*;
import organism.zombie.*;

public abstract class Tile {
    private String name;
    private Plant plant;
    private ArrayList<Zombie> zombies;

    public Tile() {
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
        zombies.add(zombie);
    }

    public void removeZombie(Zombie zombie) {
        zombies.remove(zombie);
    }

    public void moveZombie(Zombie zombie, Tile tile) {
        this.removeZombie(zombie);
        tile.addZombie(zombie);
    }

    public boolean hasPlant() {
        return plant != null;
    }

    public boolean hasZombie() {
        return !zombies.isEmpty();
    }
}
