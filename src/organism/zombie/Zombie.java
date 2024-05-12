package organism.zombie;

import organism.Organism;

public abstract class Zombie extends Organism {
    private Integer moveCooldown;
    private Integer currentSlow;
    private Boolean isVisible;

    public Zombie(String name, Integer health, Integer attackDamage, Integer attackSpeed, Boolean isAquatic, Boolean isVisible) {
        super(name, health, attackDamage, attackSpeed, isAquatic);
        this.moveCooldown = 5;
        this.currentSlow = 0;
        this.isVisible = isVisible;
    }

    public void move() {
        moveCooldown = 5;
    }

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
