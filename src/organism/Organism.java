package organism;

public abstract class Organism {
    private String name;
    private Integer health;
    private Integer attackDamage;
    private Integer attackSpeed;
    private Integer attackCooldown;
    private Boolean isAquatic;

    public Organism(String name, Integer health, Integer attackDamage, Integer attackSpeed, Boolean isAquatic) {
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.attackCooldown = 0;
        this.isAquatic = isAquatic;
    }

    public String getName(){
        return name;
    }

    public Integer getHealth(){
        return health;
    }

    public Integer getAttackDamage(){
        return attackDamage;
    }

    public Integer getAttackSpeed(){
        return attackSpeed;
    }

    public Integer getAttackCooldown(){
        return attackCooldown;
    }

    public void setAttackCooldown(Integer attackCDValue){
        attackCooldown = attackCDValue;
    }

    public Boolean getIsAquatic() {
        return isAquatic;
    }

    public Boolean isDead(){
        return (health == 0);
    }

    public void attack(){
        attackCooldown = attackSpeed;
    }

    public void loseHealth(Integer damageTaken){
        health -= damageTaken;
    }

    public void updateState(){
        attackCooldown -= 1;

        if (attackCooldown == 0) {
            attack();
        }
    }
}
