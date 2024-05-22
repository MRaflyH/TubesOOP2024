package visitor;

import java.util.ArrayList;
import grid.Lawn;
import tile.*;
import organism.plant.*;
import organism.zombie.*;

public class PlantVisitor extends Visitor{
    private ArrayList<Tile> row;
    
    public PlantVisitor(Lawn lawn, int row) {
        super(lawn);
        this.row = lawn.getLand().get(row);
    }

    public void visit() {
        int distance = 0;
        Tile zombieTile = row.get(10);
        Plant plant;

        for (int i = 10; i >= 0; i--) {
            // menyimpan tile dengan zombie terkiri
            if (row.get(i).hasZombie()) {
                zombieTile = row.get(i);
                distance = 0;
            }
            else distance++;

            // check jika ada plant
            if (row.get(i).hasPlant()) {
                plant = row.get(i).getPlant();
                plant.updateState();

                // check jika bisa attack
                if (zombieTile.hasZombie() && 
                plant.getAttackCooldown() <= 0 &&
                (plant.getRange() == -1 || plant.getRange() >= distance)) {

                    // attack dan kill zombie
                    plant.attack();
                    for (Zombie zombie : zombieTile.getZombies()) {
                        zombie.loseHealth(plant.getAttackDamage());
                        if (zombie.isDead()) zombieTile.removeZombie(zombie);
                    }
                }
            }
        }
    }
}
