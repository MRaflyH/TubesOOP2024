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

    public void addPlantToMap(int slot, Lawn lawn, int x, int y) {
        if (playablePlants.get(slot) != null && playablePlants.get(slot).getPlantingCooldown()==0) {
            lawn.getLand().
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
