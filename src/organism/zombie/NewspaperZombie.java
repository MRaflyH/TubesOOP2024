package organism.zombie;

public class NewspaperZombie extends Zombie {
    public NewspaperZombie(){
        super("Newspaper Zombie", 175, 100, 1, true, true);
    }

    public void rage(){
        this.attackSpeed *= 2;
    }
}
