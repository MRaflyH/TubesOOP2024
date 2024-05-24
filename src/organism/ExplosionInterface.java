package organism;

import grid.Lawn;

public interface ExplosionInterface {
    public void explode(Lawn lawn, int idxrow, int i);
    public boolean hasExploded();
}