package organism.plant;

public class PlantCard {
    Plant plant;

    public PlantCard(Plant plant) {
        this.plant = plant;
    }

    public void updatePlantingCooldown() {
        if (Plant.getPlantingCooldown() > 0) Plant.setPlantingCooldown(Plant.getPlantingCooldown() - 1);
    }

    public void plant() {
        if (Plant.getPlantingCooldown() == 0) Plant.setPlantingCooldown(Plant.getPlantingSpeed());
        else; // exception
    }
}
