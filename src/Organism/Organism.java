package organism;

public abstract class Organism {
    private String name;
    private Integer health;
    private Integer attackDamage;
    private Integer attackSpeed;
    private Integer currentAttackCooldown;
    private Boolean isAquatic;

    public Organism(String name, Integer health, Integer attackDamage, Integer attackSpeed, Boolean isAquatic) {
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.currentAttackCooldown = 0;
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

    public Integer getCurrentAttackCooldown(){
        return currentAttackCooldown;
    }

    public void setCurrentAttackCooldown(Integer attackCDValue){
        currentAttackCooldown = attackCDValue;
    }

    public Boolean getIsAquatic() {
        return isAquatic;
    }

    public Boolean isDead(){
        return (health == 0);
    }

    public void attack(){
        
    }

    public void loseHealth(Integer damageTaken){
        health -= damageTaken;
    }

    public void updateState(){

    }
}