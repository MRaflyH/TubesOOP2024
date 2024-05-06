package organism.plant;

public class Lilypad extends Plant {
    
    private Plant plant;

    public Lilypad() {
        super("Lilypad", 100, 0, 0, true, 25, 0, 10);
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

}
