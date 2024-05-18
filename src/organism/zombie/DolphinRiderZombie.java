package organism.zombie;

import tile.Tile;

public class DolphinRiderZombie extends Zombie implements VaultingInterface {
    private boolean hasVaulted = false;

    public DolphinRiderZombie(){
        super("Dolphin Rider Zombie", 175, 100, 1, true, true);
    }

    public void vault(Tile current, Tile vaulted, Tile land){
        current.moveZombie(this, land);
        vaulted.removePlant();
        hasVaulted = true;
        moveCooldown = 10;
    }

    public boolean getHasVaulted(){
        return hasVaulted;
    }
}
