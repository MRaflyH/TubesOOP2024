package organism.plant;

import java.io.*;

public class PlantCard implements Serializable {
    private Class<? extends Plant> cplant;
    private int plantingCooldown = 0;
    private int plantingSpeed;
    private boolean is_aquatic;
    private int cost;

    public PlantCard(Class<? extends Plant> cplant) {
        this.cplant = cplant;
        if (cplant == CherryBomb.class) {
            plantingSpeed = 20;
            plantingCooldown = plantingSpeed;
            cost = 150;
            is_aquatic = false;
            
        } else if (cplant == Chomper.class) {
            plantingSpeed = 15;    
            cost = 150;  
            is_aquatic = false;
        } else if (cplant == Lilypad.class) {
            plantingSpeed = 10;      
            cost = 25;
            is_aquatic = true;
        } else if (cplant == Peashooter.class) {
            plantingSpeed = 1;    
            cost = 100;
            is_aquatic = false;
        } else if (cplant == Repeater.class) {
            plantingSpeed = 10;
            cost = 200;
            is_aquatic = false;
        } else if (cplant == SnowPea.class) {
            plantingSpeed = 10;  
            cost = 175; 
            is_aquatic = false;
        } else if (cplant == Squash.class) {
            plantingSpeed = 20;   
            cost = 50;   
            is_aquatic = false;
        } else if (cplant == Sunflower.class) {
            plantingSpeed = 5;     
            cost = 50;
            is_aquatic = false;
        } else if (cplant == TangleKelp.class) {
            plantingSpeed = 15;
            cost = 25;
            is_aquatic = true;
        } else if (cplant == Wallnut.class) {
            plantingSpeed = 20; 
            cost = 50;
            is_aquatic = false;
        }
    }

    public int getPlantingCooldown() {
        return plantingCooldown;
    }

    public void setPlantingCooldown(int plantingCooldown) {
        this.plantingCooldown = plantingCooldown;
    }

    public int getPlantingSpeed() {
        return plantingSpeed;
    }
    
    public int getCost(){
        return cost;
    }
    
    public boolean getIsAquatic() {
        return is_aquatic;
    }

    public void updatePlantingCooldown() {
        if (getPlantingCooldown() > 0) setPlantingCooldown(getPlantingCooldown() - 1);
    }

    public void afterPlant() {
        setPlantingCooldown(getPlantingSpeed());
    }

    public Class<? extends Plant> getClassPlant() {
        return cplant;
    }
}
