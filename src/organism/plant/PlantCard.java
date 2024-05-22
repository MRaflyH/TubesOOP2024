package organism.plant;

import java.io.*;

public class PlantCard implements Serializable {
    private Class<? extends Plant> cplant;
    private int plantingCooldown = 0;
    private int plantingSpeed;
    private int cost;

    public PlantCard(Class<? extends Plant> cplant) {
        this.cplant = cplant;
        if (cplant == CherryBomb.class) {
            plantingSpeed = 20;
            cost = 150;    
        } else if (cplant == Chomper.class) {
            plantingSpeed = 15;    
            cost = 150;  
        } else if (cplant == Lilypad.class) {
            plantingSpeed = 10;      
            cost = 25;
        } else if (cplant == Peashooter.class) {
            plantingSpeed = 1;    
            cost = 100;
        } else if (cplant == Repeater.class) {
            plantingSpeed = 10;
            cost = 200;
        } else if (cplant == SnowPea.class) {
            plantingSpeed = 10;  
            cost = 175; 
        } else if (cplant == Squash.class) {
            plantingSpeed = 20;   
            cost = 50;   
        } else if (cplant == Sunflower.class) {
            plantingSpeed = 5;     
            cost = 50;
        } else if (cplant == TangleKelp.class) {
            plantingSpeed = 15;
            cost = 25;
        } else if (cplant == Wallnut.class) {
            plantingSpeed = 20; 
            cost = 50;
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
