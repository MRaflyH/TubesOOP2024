package organism.zombie;

import tile.Tile;

public interface VaultingInterface {
    public void vault(Tile current, Tile land);
    public boolean getHasVaulted();
}