package organism;

import java.io.*;

public abstract class Organism implements Serializable {
    protected String name;
    protected int health;
    protected int attackDamage;
    protected int attackSpeed;
    protected int attackCooldown;
    protected static boolean isAquatic;

    public Organism(String name, int health, int attackDamage, int attackSpeed, boolean isAquatic) {
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.attackCooldown = 0;
        Organism.isAquatic = isAquatic;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public int getAttackCooldown() {
        return attackCooldown;
    }

    public void setAttackCooldown(int attackCDValue) {
        if (attackCDValue < 0) attackCDValue = 0;
        attackCooldown = attackCDValue;
    }

    public static boolean getIsAquatic() {
        return isAquatic;
    }

    public boolean isDead() {
        return (health <= 0);
    }

    public void attack() {
        attackCooldown = attackSpeed;
    }

    public void loseHealth(int damageTaken) {
        health -= damageTaken;
    }

    public void addHealth(int healthAdded) {
        health += healthAdded;
    }

    public boolean updateState() {
        setAttackCooldown(attackCooldown - 1);

        if (attackCooldown == 0) {
            attack();
            return true;
        }

        return false;
    }
}
