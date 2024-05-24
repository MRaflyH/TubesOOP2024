package visitor;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import grid.Lawn;
import tile.*;
import organism.plant.*;
import organism.zombie.*;
import thread.*;

public class PlantVisitor extends Visitor implements Runnable{
    private ArrayList<Tile> row;
    private int idxrow;
    private Lawn lawn;
    
    public PlantVisitor(Lawn lawn, int idxrow) {
        super(lawn);
        this.lawn = lawn;
        this.row = lawn.getLand().get(idxrow);
        this.idxrow = idxrow;
    }
    public void run() {
        if (ThreadManager.getInstance().getList().size() > 0) {
            try {
                visit();
            }
            catch (ConcurrentModificationException e) {
                e.getMessage();
            }
        }
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
                if (!(plant instanceof CherryBomb)) {
                    if (zombieTile.hasZombie() && 
                    plant.getAttackCooldown() <= 0 &&
                    (plant.getRange() == -1 || plant.getRange() >= distance)) {
                        // attack dan kill zombie
                        System.out.println("duar");
                        plant.attack();
                        for (Zombie zombie : zombieTile.getZombies()) {
                            zombie.loseHealth(plant.getAttackDamage());
                            zombie.setHasBeenAttacked();
                            if (zombie.isDead()) {
                                System.out.println("mati jombi "+zombie.getName());
                                zombieTile.removeZombie(zombie);
                                System.out.println(plant.getName()+" berhasil bunuh");
                                if (plant instanceof Squash || plant instanceof TangleKelp) {
                                    plant.loseHealth(100);
                                    System.out.println("kamikaze");
                                }
                            }
                        }
                    }
                }
                else {
                    if (plant.getAttackCooldown() <= 0) {
                        if (idxrow == 0) {
                            if (lawn.getLand().get(idxrow+1).get(i).hasZombie()
                            || lawn.getLand().get(idxrow+1).get(i+1).hasZombie()
                            || lawn.getLand().get(idxrow+1).get(i-1).hasZombie()
                            || row.get(i+1).hasZombie() || row.get(i-1).hasZombie() || row.get(i).hasZombie()) {
                                // attack dan kill zombie
                                System.out.println("duar");
                                lawn.getLand().get(idxrow+1).get(i).removeAllZombie();
                                lawn.getLand().get(idxrow+1).get(i+1).removeAllZombie();
                                lawn.getLand().get(idxrow+1).get(i-1).removeAllZombie();
                                row.get(i+1).removeAllZombie();
                                row.get(i-1).removeAllZombie();
                                row.get(i).removeAllZombie();
                                plant.loseHealth(100);
                                System.out.println("kamikaze");
                            }
                        } 
                        else if (idxrow == 5) {
                            if (lawn.getLand().get(idxrow-1).get(i).hasZombie()
                            || lawn.getLand().get(idxrow-1).get(i+1).hasZombie()
                            || lawn.getLand().get(idxrow-1).get(i-1).hasZombie()
                            || row.get(i+1).hasZombie() || row.get(i-1).hasZombie() || row.get(i).hasZombie()) {
                                // attack dan kill zombie
                                System.out.println("duar");
                                lawn.getLand().get(idxrow-1).get(i).removeAllZombie();
                                lawn.getLand().get(idxrow-1).get(i+1).removeAllZombie();
                                lawn.getLand().get(idxrow-1).get(i-1).removeAllZombie();
                                row.get(i+1).removeAllZombie();
                                row.get(i-1).removeAllZombie();
                                row.get(i).removeAllZombie();
                                plant.loseHealth(100);
                                System.out.println("kamikaze");
                            }
                        } 
                        else {
                            if (lawn.getLand().get(idxrow-1).get(i).hasZombie() || lawn.getLand().get(idxrow+1).get(i).hasZombie()
                            || lawn.getLand().get(idxrow-1).get(i+1).hasZombie() || lawn.getLand().get(idxrow+1).get(i+1).hasZombie()
                            || lawn.getLand().get(idxrow-1).get(i-1).hasZombie() || lawn.getLand().get(idxrow+1).get(i-1).hasZombie()
                            || row.get(i+1).hasZombie() || row.get(i-1).hasZombie() || row.get(i).hasZombie()) {
                                // attack dan kill zombie
                                System.out.println("duar");
                                lawn.getLand().get(idxrow-1).get(i).removeAllZombie();
                                lawn.getLand().get(idxrow+1).get(i).removeAllZombie();
                                lawn.getLand().get(idxrow-1).get(i+1).removeAllZombie();
                                lawn.getLand().get(idxrow+1).get(i+1).removeAllZombie();
                                lawn.getLand().get(idxrow-1).get(i-1).removeAllZombie();
                                lawn.getLand().get(idxrow+1).get(i-1).removeAllZombie();
                                row.get(i+1).removeAllZombie();
                                row.get(i-1).removeAllZombie();
                                row.get(i).removeAllZombie();
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