package organism.zombie;

import tile.Tile;

public class DolphinRiderZombie extends Zombie implements VaultingInterface {
    private Boolean hasVaulted = false;

    public DolphinRiderZombie(){
        super("Dolphin Rider Zombie", 175, 100, 1, true, true);
    }

    public void vault(Tile current, Tile vaulted, Tile land){
        current.moveZombie(this, land);
        vaulted.removePlant();
        hasVaulted = true;
    }

    public Boolean getHasVaulted(){
        return hasVaulted;
    }
}
