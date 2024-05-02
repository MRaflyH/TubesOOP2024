package organism.zombie;

import organism.Organism;

public abstract class Zombie extends Organism {
    private Integer currentMoveCooldown;
    private Integer currentSlow;
    private Boolean isSeen;

    public Zombie(String name, Integer health, Integer attackDamage, Integer attackSpeed, Boolean isAquatic, Boolean isSeen) {
        super(name, health, attackDamage, attackSpeed, isAquatic);
        this.currentMoveCooldown = 5;
        this.currentSlow = 0;
        this.isSeen = isSeen;
    }

    public void move(){

    }

    public Integer getCurrentMoveCooldown(){
        return currentMoveCooldown;
    }

    public void setCurrentMoveCooldown(Integer moveCDValue){
        currentMoveCooldown = moveCDValue;
    }

    public Integer getcurrentSlow(){
        return currentSlow;
    }

    public void setCurrentSlow(Integer slowValue){
        currentSlow = slowValue;
    }

    public void reduceCurrentSlow(Integer reduceValue){
        currentSlow -= reduceValue;
    }

    public Boolean isSlow(){
        return (currentSlow > 0);
    }

    public Boolean getIsSeen(){
        return isSeen;
    }
}