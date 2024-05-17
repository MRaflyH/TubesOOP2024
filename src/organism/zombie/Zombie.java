package organism.zombie;

import organism.Organism;
import tile.Tile;

public abstract class Zombie extends Organism {
    protected Integer moveCooldown;
    protected Integer currentSlow;
    protected Boolean isVisible;

    public Zombie(String name, Integer health, Integer attackDamage, Integer attackSpeed, Boolean isAquatic, Boolean isVisible) {
        super(name, health, attackDamage, attackSpeed, isAquatic);
        this.moveCooldown = 5;
        this.currentSlow = 0;
        this.isVisible = isVisible;
    }

    //public void move(Tile prev, Tile next) {
        // ini sementara dibuat gini, asumsinya:
        // Method ini dipanggil dari main dan
        // tile zombie saat ini dan tile zombie selanjutnya disediakan dari main
   //     prev.moveZombie(this, next);
   //     moveCooldown = 5;
   // }

    public Integer getMoveCooldown() {
        return moveCooldown;
    }

    public void setMoveCooldown(Integer moveCDValue) {
        moveCooldown = moveCDValue;
    }

    public Integer getcurrentSlow() {
        return currentSlow;
    }

    public void setCurrentSlow(Integer slowValue) {
        currentSlow = slowValue;
    }

    public void reduceCurrentSlow(Integer reduceValue) {
        currentSlow -= reduceValue;
    }

    public Boolean isSlow() {
        return (currentSlow > 0);
    }

    public Boolean getIsVisible() {
        return isVisible;
    }
}
