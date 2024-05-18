package organism.plant;

import organism.Organism;

public abstract class Plant extends Organism {
    private int cost;
    private int range;
    private int plantingSpeed;
    private int plantingCooldown;

    public Plant(String name, int health, int attackDamage, int attackSpeed, boolean isAquatic, int cost, int range, int plantingSpeed) {
        super(name, health, attackDamage, attackSpeed, isAquatic);
        this.cost = cost;
        this.range = range;
        this.plantingSpeed = plantingSpeed;
        plantingCooldown = plantingSpeed;
    }

    public int getCost() {
        return cost;
    }

    public int getRange() {
        return range;
    }

    public int getPlantingSpeed() {
        return plantingSpeed;
    }

    public int getPlantingCooldown() {
        return plantingCooldown;
    }

    public void setPlantingCooldown(int plantingCooldown) {
        this.plantingCooldown = plantingCooldown;
    }
}
