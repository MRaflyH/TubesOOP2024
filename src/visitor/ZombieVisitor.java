package visitor;

import java.util.*;
import grid.Lawn;
import tile.*;
import organism.zombie.*;

public class ZombieVisitor extends Visitor{
    private ArrayList<Tile> row;
    
    public ZombieVisitor(Lawn lawn, int row) {
        super(lawn);
        this.row = lawn.getLand().get(row);
    }

    public void visit() {
        int totalDamage;

        for (int i = 10; i > -1; i--) {
            if (row.get(i).hasZombie()) {
                totalDamage = 0;

                if (row.get(i).hasPlant()) {
                    for (Zombie z : row.get(i).getZombies()) {
                        // if(z.updateState()) totalDamage += z.getAttackDamage();
                    }
                    
                    row.get(i).getPlant().loseHealth(totalDamage);
                    
                    if (row.get(i).getPlant().isDead()) row.get(i).removePlant();
                }
                else {
                    for (Zombie z : row.get(i).getZombies()) {
                        z.setMoveCooldown(z.getMoveCooldown() - 1);
                        if (z.getMoveCooldown() <= 0) z.move(row.get(i), row.get(i-1));
                    }
                }
            }
        }
    }
}
