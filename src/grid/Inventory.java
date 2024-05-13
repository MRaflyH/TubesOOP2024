package grid;
import java.util.ArrayList;
import java.util.List;

import organism.plant.CherryBomb;
import organism.plant.Chomper;
import organism.plant.Lilypad;
import organism.plant.Peashooter;
import organism.plant.Plant;
import organism.plant.Repeater;
import organism.plant.SnowPea;
import organism.plant.Squash;
import organism.plant.Sunflower;
import organism.plant.TangleKelp;
import organism.plant.Wallnut;

public class Inventory {
    private List<Plant> allPlants;
    
    public Inventory() {
        allPlants = new ArrayList<>();
        allPlants.add(new CherryBomb());
        allPlants.add(new Chomper());
        allPlants.add(new Lilypad());
        allPlants.add(new Peashooter());
        allPlants.add(new Repeater());
        allPlants.add(new SnowPea());
        allPlants.add(new Squash());
        allPlants.add(new Sunflower());
        allPlants.add(new TangleKelp());
        allPlants.add(new Wallnut());
    }
    
    public void addPlant(Plant plant, Deck deck) {
        if (allPlants.contains(plant)) {
            deck.addPlantToDeck(plant);
            allPlants.remove(plant);
        }
        
    }
}
