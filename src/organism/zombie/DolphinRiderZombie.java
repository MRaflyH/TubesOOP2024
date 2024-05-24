package organism.zombie;

import organism.plant.Lilypad;
import organism.plant.Squash;
import organism.plant.TangleKelp;
import tile.Tile;

public class DolphinRiderZombie extends Zombie implements VaultingInterface {
    private boolean hasVaulted = false;

    public DolphinRiderZombie(){
        super("Dolphin Rider Zombie", 175, 100, 2, true, true);
    }

    public void vault(Tile current, Tile land){
        current.moveZombie(this, land);
        if (land.getPlant() instanceof Lilypad) {
            Lilypad l = (Lilypad) land.getPlant();
            if (!(l.getPlant() instanceof Squash)) land.removePlant();
            else {
                land.removePlant();
                land.removeZombie(this);
            }
        }
        else {
            if (!(land.getPlant() instanceof Squash) || !(land.getPlant() instanceof TangleKelp)) land.removePlant();
            else {
                land.removePlant();
                land.removeZombie(this);
            }
        }
        hasVaulted = true;
        attack();
    }

    public boolean getHasVaulted(){
        return hasVaulted;
    }
}
