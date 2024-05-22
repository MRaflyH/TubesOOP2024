package grid;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

import organism.plant.Plant;
import organism.plant.PlantCard;

public class Deck implements Serializable {
    private static final int MAX_PLANT = 6;
    private List<PlantCard> playablePlants;
    
    public Deck() {
        playablePlants = new ArrayList<>();
    }

    public List<PlantCard> getPlayablePlants() {
        return this.playablePlants;
    }

    public int getMaxPlants() {
        return MAX_PLANT;
    }

    public void addPlantToMap(int slot, Lawn lawn, int row, int column) {
        try {
            if (playablePlants.get(slot).getPlantingCooldown() == 0) {
                lawn.getLand().get(row).get(column).addPlant(playablePlants.get(slot).getClassPlant().getDeclaredConstructor().newInstance());
            }
            else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removePlantFromMap(Lawn lawn, int x, int y) {
        lawn.getLand().get(y).get(x).removePlant();
    }
}
