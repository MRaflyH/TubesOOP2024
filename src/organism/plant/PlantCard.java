package organism.plant;

public class PlantCard {
    Plant plant;

    public PlantCard(Plant plant) {
        this.plant = plant;
    }

    public void updatePlantingCooldown() {
        if (plant.getPlantingCooldown() > 0) plant.setPlantingCooldown(plant.getPlantingCooldown() - 1);
    }

    public void plant() {
        if (plant.getPlantingCooldown() == 0) plant.setPlantingCooldown(plant.getPlantingSpeed());
        else; // exception
    }
}
