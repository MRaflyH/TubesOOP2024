package organism.zombie;

import organism.Organism;
import tile.Tile;

public abstract class Zombie extends Organism {
    protected int moveCooldown;
    protected int currentSlow;
    protected boolean isVisible;
    private boolean hasBeenAttacked;

    public Zombie(String name, int health, int attackDamage, int attackSpeed, boolean isAquatic, boolean isVisible) {
        super(name, health, attackDamage, attackSpeed, isAquatic);
        this.moveCooldown = 10;
        this.currentSlow = 0;
        this.isVisible = isVisible;
        hasBeenAttacked = false;
    }

    public void setHasBeenAttacked(){
        hasBeenAttacked = true;
    }
    
    public boolean HasBeenAttacked() {
        return hasBeenAttacked;
    }
    public void move(Tile prev, Tile next) {
        prev.moveZombie(this, next);
        moveCooldown = 10;
        // isSlow() ? moveCooldown = 20 : moveCooldown = 10;
    }

    public int getMoveCooldown() {
        return moveCooldown;
    }

    public void setMoveCooldown(int moveCDValue) {
        if (moveCDValue < 0) moveCDValue = 0;
        moveCooldown = moveCDValue;
    }

    public int getcurrentSlow() {
        return currentSlow;
    }

    public void setCurrentSlow(int slowValue) {
        currentSlow = slowValue;
        moveCooldown *= 2;
    }

    public void reduceCurrentSlow(int reduceValue) {
        currentSlow -= reduceValue;
    }

    public boolean isSlow() {
        return (currentSlow > 0);
    }

    public boolean getIsVisible() {
        return isVisible;
    }
}
