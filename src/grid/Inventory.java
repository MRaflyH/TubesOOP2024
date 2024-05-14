package grid;
import java.util.ArrayList;
import java.util.Iterator;
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
    
    //harus diurus apakah akan add plant atau swap plant
    public void addPlant(Plant plant, Deck deck, int slot) {
        if (!deck.getPlayablePlants().contains(plant)) {
            deck.getPlayablePlants().add(slot, plant);
        }
        else {
            //exception
        }
    }

    public void swapPlant(Plant plant, Deck deck, int slotDeck) {
        if (!deck.getPlayablePlants().get(slotDeck).equals(plant) && deck.getPlayablePlants().get(slotDeck) != null) {
            Iterator<Plant> iterInv = allPlants.iterator();
            int count = 0, slotInv = 0;
            while (iterInv.hasNext()) {
                Plant temp = iterInv.next();
                count++;
                if (temp.equals(plant)) {
                    iterInv.remove();
                    slotInv = count;
                }
            }
            Plant plantswap = deck.getPlayablePlants().get(slotDeck);
            deck.getPlayablePlants().remove(slotDeck);
            deck.getPlayablePlants().add(slotDeck, plant);
            allPlants.add(slotInv, plantswap);
        }
        
    }

    public void removePlant(Deck deck, int slot) {
        if (deck.getPlayablePlants().get(slot) != null) {
            deck.getPlayablePlants().remove(slot);
        }
        else {
            //exception
        }
    }
}
