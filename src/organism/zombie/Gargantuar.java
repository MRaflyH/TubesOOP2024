package organism.zombie;

public class Gargantuar extends Zombie{
    private boolean windUp = true;

    public Gargantuar(){
        super("Gargantuar", 1000, 200, 10, false, true);
    }

    @Override
    public int getAttackDamage(){
        if (windUp){
            attackCooldown += attackSpeed;
            windUp = false;
            return 0;
        } else {
            attackCooldown = attackSpeed;
            windUp = true;
            return attackDamage;
        }
    }
}
