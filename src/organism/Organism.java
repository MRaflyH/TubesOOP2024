package organism;

public abstract class Organism {
    protected String name;
    protected Integer health;
    protected Integer attackDamage;
    protected Integer attackSpeed;
    protected Integer attackCooldown;
    protected Boolean isAquatic;

    public Organism(String name, Integer health, Integer attackDamage, Integer attackSpeed, Boolean isAquatic) {
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.attackCooldown = 0;
        this.isAquatic = isAquatic;
    }

    public String getName() {
        return name;
    }

    public Integer getHealth() {
        return health;
    }

    public Integer getAttackDamage() {
        return attackDamage;
    }

    public Integer getAttackSpeed() {
        return attackSpeed;
    }

    public Integer getAttackCooldown() {
        return attackCooldown;
    }

    public void setAttackCooldown(Integer attackCDValue) {
        if (attackCDValue < 0) attackCDValue = 0;
        attackCooldown = attackCDValue;
    }

    public Boolean getIsAquatic() {
        return isAquatic;
    }

    public Boolean isDead() {
        return (health == 0);
    }

    public void attack() {
        attackCooldown = attackSpeed;
    }

    public void loseHealth(Integer damageTaken) {
        health -= damageTaken;
    }

    public void addHealth(Integer healthAdded) {
        health += healthAdded;
    }

    public void updateState() {
        setAttackCooldown(attackCooldown - 1);

        if (attackCooldown == 0) {
            attack();
        }
    }
}
