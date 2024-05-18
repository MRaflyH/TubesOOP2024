package organism.zombie;

public class JackInTheBoxZombie extends Zombie implements organism.ExplosionInterface {
    private boolean hasExploded = false;

    public JackInTheBoxZombie(){
        super("Jack In The Box Zombie", 175, 10000, 1, true, true);
    }

    public void explode(){
        hasExploded = true;
        this.loseHealth(this.getHealth());
    }

    public boolean hasExploded(){
            return hasExploded;
    }

}
