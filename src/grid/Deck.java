package grid;
import java.util.ArrayList;
import java.util.List;

import organism.plant.Plant;

public class Deck {
    private static final int MAX_PLANT = 6;
    private List<Plant> playablePlants;
    
    public Deck() {
        playablePlants = new ArrayList<>();
        for (int i = 0; i < MAX_PLANT; i++) {
            playablePlants.add(null);
        }
    }

    public List<Plant> getPlayablePlants() {
        return this.playablePlants;
    }

    public int getMaxPlants() {
        return MAX_PLANT;
    }

    public void addPlantToMap(int slot, Lawn lawn, int x, int y) {
        if (slot < MAX_PLANT && playablePlants.get(slot).getPlantingCooldown()==0) {
            lawn.getLand().get(y).get(x).addPlant(playablePlants.get(slot));
            playablePlants.get(slot).setPlantingCooldown(playablePlants.get(slot).getPlantingSpeed());
            
        }
        else {
            // exception
        }
    }

    public void removePlantFromMap(Lawn lawn, int x, int y) {
        lawn.getLand().get(y).get(x).removePlant();
    }
}
