package organism.plant;

import organism.Organism;

public abstract class Plant extends Organism {
    private Integer cost;
    private Integer range;
    private Integer cooldown;
    private Integer currentCooldown;

    public Plant(String name, Integer health, Integer attackDamage, Integer attackSpeed, Boolean isAquatic, Integer cost, Integer range, Integer cooldown) {
        super(name, health, attackDamage, attackSpeed, isAquatic);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
        currentCooldown = cooldown;
    }

    public Integer getCost() {
        return cost;
    }

    public Integer getRange() {
        return range;
    }

    public Integer getCooldown() {
        return cooldown;
    }

    public Integer getCurrentCooldown() {
        return currentCooldown;
    }

}
