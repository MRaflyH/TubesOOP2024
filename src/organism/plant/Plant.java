package organism.plant;

import organism.Organism;

public abstract class Plant extends Organism {
    private Integer cost;
    private Integer range;
    private Integer plantingSpeed;
    private Integer plantingCooldown;

    public Plant(String name, Integer health, Integer attackDamage, Integer attackSpeed, Boolean isAquatic, Integer cost, Integer range, Integer plantingSpeed) {
        super(name, health, attackDamage, attackSpeed, isAquatic);
        this.cost = cost;
        this.range = range;
        this.plantingSpeed = plantingSpeed;
        plantingCooldown = plantingSpeed;
    }

    public Integer getCost() {
        return cost;
    }

    public Integer getRange() {
        return range;
    }

    public Integer getPlantingSpeed() {
        return plantingSpeed;
    }

    public Integer getPlantingCooldown() {
        return plantingCooldown;
    }

    public void setPlantingCooldown(Integer plantingCooldown) {
        this.plantingCooldown = plantingCooldown;
    }
}
