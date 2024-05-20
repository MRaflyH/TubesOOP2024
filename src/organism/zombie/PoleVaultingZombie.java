package organism.zombie;

import organism.plant.Lilypad;
import organism.plant.Squash;
import tile.Tile;

public class PoleVaultingZombie extends Zombie implements VaultingInterface {
    private boolean hasVaulted = false;

    public PoleVaultingZombie(){
        super("Pole Vaulting Zombie", 175, 100, 2, false, true);
    }

    public void vault(Tile current, Tile land){
        current.moveZombie(this, land);
        if (land.getPlant() instanceof Lilypad) {
            Lilypad l = (Lilypad) land.getPlant();
            if (!(l.getPlant() instanceof Squash)) land.removePlant();
        }
        else if (!(land.getPlant() instanceof Squash)) land.removePlant();
        hasVaulted = true;
        attack();
    }

    public boolean getHasVaulted(){
        return hasVaulted;
    }

}
