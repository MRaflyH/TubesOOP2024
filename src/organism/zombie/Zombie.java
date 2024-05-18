package organism.zombie;

import organism.Organism;
import tile.Tile;

public abstract class Zombie extends Organism {
    protected int moveCooldown;
    protected int currentSlow;
    protected boolean isVisible;

    public Zombie(String name, int health, int attackDamage, int attackSpeed, boolean isAquatic, boolean isVisible) {
        super(name, health, attackDamage, attackSpeed, isAquatic);
        this.moveCooldown = 10;
        this.currentSlow = 0;
        this.isVisible = isVisible;
    }

    public void move(Tile prev, Tile next) {
        prev.moveZombie(this, next);
        moveCooldown = 10;
    }

    public int getMoveCooldown() {
        return moveCooldown;
    }

    public void setMoveCooldown(int moveCDValue) {
        moveCooldown = moveCDValue;
    }

    public int getcurrentSlow() {
        return currentSlow;
    }

    public void setCurrentSlow(int slowValue) {
        currentSlow = slowValue;
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
