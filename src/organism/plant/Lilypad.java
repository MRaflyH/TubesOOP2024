package organism.plant;

public class Lilypad extends Plant {
    
    private Plant plant;

    public Lilypad() {
        super("Lilypad", 100, 0, 0, true, 25, 0, 10);
    }

    public Integer getRange() {
        if (hasPlant() == true) {
            return plant.getRange();
        }
        else {
            return super.getRange();
        }
    }

    public Integer getCooldown() {
        if (hasPlant() == true) {
            return plant.getCooldown();
        }
        else {
            return super.getCooldown();
        }
    }

    public Integer getCurrentCooldown() {
        if (hasPlant() == true) {
            return plant.getCurrentCooldown();
        }
        else {
            return super.getCurrentCooldown();
        }
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public Boolean hasPlant() {
        return plant != null;
    }

}
