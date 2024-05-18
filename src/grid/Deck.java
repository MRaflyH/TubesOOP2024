package grid;
import java.util.ArrayList;
import java.util.List;

import organism.plant.Plant;
import tile.Tile;

public class Deck {
    private static final int MAX_PLANT = 6;
    private List<Class<? extends Plant>> playablePlants;
    
    public Deck() {
        playablePlants = new ArrayList<>();
    }

<<<<<<< Updated upstream
    public List<Plant> getPlants() {
=======
    public List<Class<? extends Plant>> getPlayablePlants() {
>>>>>>> Stashed changes
        return this.playablePlants;
    }

    public void addPlantToMap(Plant plant, Tile tile) {
        if (playablePlants.contains(plant) && plant.getPlantingCooldown()==0) {
            tile.addPlant(plant);
        }
        else {
            // exception
        }
    }

    public void removePlantFromMap(Tile tile) {
        if (tile.hasPlant()) {
            tile.removePlant();
        }
        else {
            // exception
        }
    }

    public void addPlantToDeck(Plant plant, int slot) {
        if (playablePlants.get(slot) == null) {
            playablePlants.add(slot, plant);
        }
        
    }

    public void removePlantDeck(Plant plant, int slot) {
        if () {
            
        }
    }
}
