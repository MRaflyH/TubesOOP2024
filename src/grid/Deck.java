package grid;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

import organism.plant.Plant;

public class Deck implements Serializable {
    private static final int MAX_PLANT = 6;
    private List<Class<? extends Plant>> playablePlants;
    
    public Deck() {
        playablePlants = new ArrayList<>();
    }

    public List<Class<? extends Plant>> getPlayablePlants() {
        return this.playablePlants;
    }

    public int getMaxPlants() {
        return MAX_PLANT;
    }

    public void addPlantToMap(int slot, Lawn lawn, int x, int y) {
        try {
            if (playablePlants.get(slot).getMethod("getPlantingCooldown").invoke(null).equals(0)) {
                lawn.getLand().get(y).get(x).addPlant(playablePlants.get(slot).getDeclaredConstructor().newInstance());
                playablePlants.get(slot).getMethod("setPlantingCooldown", int.class).invoke(null,(Integer)playablePlants.get(slot).getMethod("getPlantingSpeed").invoke(null));
            }
            else {
                // exception
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void removePlantFromMap(Lawn lawn, int x, int y) {
        lawn.getLand().get(y).get(x).removePlant();
    }
}
