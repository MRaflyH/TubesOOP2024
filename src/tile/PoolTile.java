package tile;

import organism.plant.*;

public class PoolTile extends Tile {
    
    public PoolTile() {
        super("Pool Tile");
    }

    public void addPlant(Plant plant) {
        if (!hasPlant()) {
            if (plant.getIsAquatic()) {
                super.addPlant(plant);
            }
            else {
                // exception
            }
        }
        else {
            if (getPlant().getName() == "Lilypad") {
                Lilypad lilypad = (Lilypad) getPlant();
                lilypad.setPlant(plant);
            }
            else {
                // exception
            }
        }
    }
}
