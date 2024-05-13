package organism.zombie;

import tile.Tile;

public interface VaultingInterface {
    public void vault(Tile current, Tile vaulted, Tile land);
    public Boolean getHasVaulted();
}