package organism.plant;

import organism.Organism;

public abstract class Plant extends Organism {
    private Integer cost;
    private Integer range;
    public static Integer plantingSpeed;
    private static Integer plantingCooldown;

    public Plant(String name, Integer health, Integer attackDamage, Integer attackSpeed, Boolean isAquatic, Integer cost, Integer range) {
        super(name, health, attackDamage, attackSpeed, isAquatic);
        this.cost = cost;
        this.range = range;
        plantingSpeed = 0;
        plantingCooldown = plantingSpeed;
    }

    public Integer getCost() {
        return cost;
    }

    public Integer getRange() {
        return range;
    }

    public Integer getPlantingCooldown() {
        return plantingCooldown;
    }

    public void setPlantingCooldown(Integer plantingCooldown) {
        this.plantingCooldown = plantingCooldown;
    }
}
