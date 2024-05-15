package grid;
import java.util.ArrayList;

import organism.plant.Plant;
import tile.*;

public class Lawn {
    private ArrayList<ArrayList<Tile>> land;

    public Lawn() {
        land = new ArrayList<ArrayList<Tile>>();
        for (int i = 0; i < 6; i++) {
            land.add(new ArrayList<Tile>());
            land.get(i).add(new HomeTile());
        }
        for (int i = 0; i < 9; i++) {
            land.get(0).add(new GrassTile());
            land.get(1).add(new GrassTile());
            land.get(2).add(new PoolTile());
            land.get(3).add(new PoolTile());
            land.get(4).add(new GrassTile());
            land.get(5).add(new GrassTile());
        }
        for (int i = 0; i < 6; i++) {
            land.get(i).add(new SpawnTile());
        }
    }

    public ArrayList<ArrayList<Tile>> getLand() {
        return land;
    }
}
