package grid;
import java.util.ArrayList;
import java.util.List;

import organism.plant.Plant;
import tile.Tile;

public class Deck {
    private static final int MAX_PLANT = 6;
    private List<Plant> playablePlants;
    
    public Deck() {
        playablePlants = new ArrayList<>();
    }

    public List<Plant> getPlayablePlants() {
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
}
