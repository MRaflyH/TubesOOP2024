package grid;
import java.util.ArrayList;
import java.util.List;

import exception.InvalidInventoryException;
import organism.plant.*;

public class Inventory {
    private List<Class<? extends Plant>> allPlants;
    
    public Inventory() {
        allPlants = new ArrayList<>();
        allPlants.add(CherryBomb.class);
        allPlants.add(Chomper.class);
        allPlants.add(Lilypad.class);
        allPlants.add(Peashooter.class);
        allPlants.add(Repeater.class);
        allPlants.add(SnowPea.class);
        allPlants.add(Squash.class);
        allPlants.add(Sunflower.class);
        allPlants.add(TangleKelp.class);
        allPlants.add(Wallnut.class);
    }
    
    //harus diurus apakah akan add plant atau swap plant di main
    public void addPlant(Class<? extends Plant> cplant, Deck deck) throws InvalidInventoryException {
        if (!deck.getPlayablePlants().contains(cplant)) {
            if(deck.getPlayablePlants().size() < deck.getMaxPlants()){
                deck.getPlayablePlants().set(deck.getPlayablePlants().size(), cplant);
            } else {
                throw new InvalidInventoryException("Plants are full!");
            }
        }
        else {
            throw new InvalidInventoryException("Plant already in deck!");
        }
    }

    public void swapPlant(List<Class<? extends Plant>> arrayplant, int slot1, int slot2) throws InvalidInventoryException {
        if (arrayplant.get(slot1) != null && arrayplant.get(slot2) != null && slot1<6 && slot2<6) {
            if (slot1 != slot2) {
                Class<? extends Plant> temp = arrayplant.get(slot1);
                arrayplant.set(slot1, arrayplant.get(slot2));
                arrayplant.set(slot2, temp);
            }
            else {
                throw new InvalidInventoryException("Selected plants are same!");
            }
        }
        else {
            throw new InvalidInventoryException("Selected slot is empty!");
        }
    }

    public void removePlant(Deck deck, int slot) throws InvalidInventoryException {
        if (deck.getPlayablePlants().get(slot) != null) {
            deck.getPlayablePlants().set(slot, null);
        }
        else {
            throw new InvalidInventoryException("Deck is empty!");
        }
    }
}