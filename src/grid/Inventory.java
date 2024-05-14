package grid;
import java.util.ArrayList;
import java.util.List;

import organism.plant.*;

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
    
    public void addPlant(Plant plant, Deck deck, int slot) {
        if (allPlants.contains(plant)) {
            deck.addPlantToDeck(plant, slot);
            allPlants.remove(plant);
        }
        else {
            //exception
        }
    }

    public void removePlant(Plant plant, Deck deck, int slot) {
        if (deck.getPlants().contains(plant)) {
            deck.removePlantDeck(plant, slot);
            allPlants.add(plant);
        }
        else {
            //exception
        }
    }
}
