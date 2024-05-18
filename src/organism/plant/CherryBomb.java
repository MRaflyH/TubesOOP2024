package organism.plant;

public class CherryBomb extends Plant implements organism.ExplosionInterface {
    private boolean hasExploded = false;

    public CherryBomb(){
        super("Cherry Bomb", 100, 50000, 10, false, 150, 0, 20);
    }

    public boolean hasExploded(){
        return hasExploded;
    }

    public void explode(){
        hasExploded = true;
        this.loseHealth(this.getHealth());
    }
}
