package visitor;

import java.util.ArrayList;
import grid.Lawn;
import tile.*;
import organism.plant.*;
import organism.zombie.*;

public class PlantVisitor extends Visitor implements Runnable{
    private ArrayList<Tile> row;
    
    public PlantVisitor(Lawn lawn, int idxrow) {
        super(lawn);
        this.row = lawn.getLand().get(idxrow);
    }
    public void run() {
        visit();
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
                    System.out.println("duar");
                    plant.attack();
                    for (Zombie zombie : zombieTile.getZombies()) {
                        zombie.loseHealth(plant.getAttackDamage());
                        if (zombie.isDead()) {
                            System.out.println("mati jombi "+zombie.getName());
                            zombieTile.removeZombie(zombie);
                            System.out.println(plant.getName()+" berhasil bunuh");
                            if (plant instanceof Squash) {
                                plant.loseHealth(100);
                                System.out.println("kamikaze");
                            }
                        }
                    }
                }
                if (plant.isDead()) {
                    System.out.println("mati taneman "+plant.getName());
                    row.get(i).removePlant();
                }
            }
        }
    }
}