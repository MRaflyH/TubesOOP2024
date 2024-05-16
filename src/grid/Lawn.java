package grid;
import java.util.ArrayList;

import organism.plant.Plant;
import tile.GrassTile;
import tile.HomeTile;
import tile.PoolTile;
import tile.SpawnTile;
import tile.Tile;
import organism.zombie.*;

public class Lawn {
    private ArrayList<ArrayList<Tile>> land;
    
    public Lawn() {
        land = new ArrayList<ArrayList<Tile>>();
        for (int i = 0; i < 6; i++) {
            land.add(new ArrayList<Tile>());
            for(int j = 0; j < 11; j++){
                if(j == 0){
                    land.get(i).add(new HomeTile());
                } else if(i != 2 && i != 3 && j > 0 && j < 10){
                    land.get(i).add(new GrassTile());
                } else if(j == 10){
                    land.get(i).add(new SpawnTile());
                } else {
                    land.get(i).add(new PoolTile());
                }

            }

        }
    }

    public ArrayList<ArrayList<Tile>> getLand() {
        return land;
    }   
}
