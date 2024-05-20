package organism.zombie;

public class SnorkelZombie extends Zombie {
    public SnorkelZombie(){
        super("Snorkel Zombie", 175, 100, 2, true, true);
    }

    public void dive(){
        isVisible = false;
    }

    public void emerge(){
        isVisible = true;
    }
}
