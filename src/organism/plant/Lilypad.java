package organism.plant;

public class Lilypad extends Plant {
    
    private Plant plant;

    public Lilypad() {
        super("Lilypad", 100, 0, 0, true, 25, 0, 10);
    }

    public String getName(){
        if (hasPlant()) {
            return plant.getName();
        }
        else {
            return super.getName();
        }
    }

    public Integer getHealth() {
        if (hasPlant()) {
            return plant.getHealth();
        }
        else {
            return super.getHealth();
        }
    }

    public Integer getAttackDamage(){
        if (hasPlant()) {
            return plant.getAttackDamage();
        }
        else {
            return super.getAttackDamage();
        }
    }

    public Integer getAttackSpeed(){
        if (hasPlant()) {
            return plant.getAttackSpeed();
        }
        else {
            return super.getAttackSpeed();
        }
    }

    public Integer getAttackCooldown(){
        if (hasPlant()) {
            return plant.getAttackCooldown();
        }
        else {
            return super.getAttackCooldown();
        }
    }

    public void setAttackCooldown(Integer attackCDValue){
        if (hasPlant()) {
            plant.setAttackCooldown(attackCDValue);
        }
        else {
            super.setAttackCooldown(attackCDValue);
        }
    }

    public Boolean isDead() {
        if (hasPlant()) {
            return plant.isDead();
        }
        else {
            return super.isDead();
        }
    }

    public void attack(){
        if (hasPlant()) {
            plant.attack();
        }
        else {
            super.attack();
        }
    }

    public void loseHealth(Integer damageTaken) {
        if (hasPlant()) {
            plant.loseHealth(damageTaken);
        }
        else {
            super.loseHealth(damageTaken);
        }
    }

    public void addHealth(Integer healthAdded) {
        if (hasPlant()) {
            plant.addHealth(healthAdded);
        }
        else {
            super.addHealth(healthAdded);
        }
    }

    public void updateState() {
        if (hasPlant()) {
            plant.updateState();
        }
        else {
        }
    }

    public Integer getRange() {
        if (hasPlant() == true) {
            return plant.getRange();
        }
        else {
            return super.getRange();
        }
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        if (!hasPlant() && !plant.getIsAquatic()) {
            plant.addHealth(getHealth());
            this.plant = plant;
        }
        else {
            // exception
        }
    }

    public Boolean hasPlant() {
        return plant != null;
    }

}
