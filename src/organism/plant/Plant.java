package organism.plant;

import organism.Organism;

public abstract class Plant extends Organism {
    private int cost;
    private int range;
    private static int plantingSpeed;
    private static int plantingCooldown;

    public Plant(String name, int health, int attackDamage, int attackSpeed, boolean isAquatic, int cost, int range, int plantingSpeed) {
        super(name, health, attackDamage, attackSpeed, isAquatic);
        this.cost = cost;
        this.range = range;
        Plant.plantingSpeed = plantingSpeed;
        Plant.plantingCooldown = plantingSpeed;
    }

    public int getCost() {
        return cost;
    }

    public int getRange() {
        return range;
    }

    public static int getPlantingCooldown() {
        return plantingCooldown;
    }

    public static int getPlantingSpeed() {
        return plantingSpeed;
    }

    public static void setPlantingCooldown(int plantingCooldown) {
        Plant.plantingCooldown = plantingCooldown;
    }
}
