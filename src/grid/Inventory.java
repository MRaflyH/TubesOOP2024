package grid;
import java.util.ArrayList;
import java.util.List;

import exception.InvalidInventoryException;
import organism.plant.*;

public class Inventory {
    private List<PlantCard> allPlants;
    
    public Inventory() {
        allPlants = new ArrayList<>();
        allPlants.add(new PlantCard(CherryBomb.class));
        allPlants.add(new PlantCard(Chomper.class));
        allPlants.add(new PlantCard(Lilypad.class));
        allPlants.add(new PlantCard(Peashooter.class));
        allPlants.add(new PlantCard(Repeater.class));
        allPlants.add(new PlantCard(SnowPea.class));
        allPlants.add(new PlantCard(Squash.class));
        allPlants.add(new PlantCard(Sunflower.class));
        allPlants.add(new PlantCard(TangleKelp.class));
        allPlants.add(new PlantCard(Wallnut.class));
    }
    
    //harus diurus apakah akan add plant atau swap plant di main
    public void addPlant(PlantCard plantcard, Deck deck) throws InvalidInventoryException {
        if (!deck.getPlayablePlants().contains(plantcard)) {
            if(deck.getPlayablePlants().size() < deck.getMaxPlants()){
                deck.getPlayablePlants().add(plantcard);
            } else {
                throw new InvalidInventoryException("Plants are full!");
            }
        }
        else {
            throw new InvalidInventoryException("Plant already in deck!");
        }
    }

    public void swapPlant(List<PlantCard> arrayplant, int slot1, int slot2) throws InvalidInventoryException {
        if (arrayplant.get(slot1) != null && arrayplant.get(slot2) != null && slot1<6 && slot2<6) {
            if (slot1 != slot2) {
                PlantCard temp = arrayplant.get(slot1);
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

    public List<PlantCard> getAllPlants() {
        return allPlants;
    }
}