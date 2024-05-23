package grid;
import java.util.ArrayList;
import java.util.List;

import exception.InvalidDeployException;
import java.io.Serializable;
import java.io.*;

import organism.plant.PlantCard;

public class Deck implements Serializable {
    private static final int MAX_PLANT = 6;
    private List<PlantCard> playablePlants;
    
    public Deck() {
        playablePlants = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            playablePlants.add(null);
        }
    }

    public List<PlantCard> getPlayablePlants() {
        return this.playablePlants;
    }

    public int getMaxPlants() {
        return MAX_PLANT;
    }

    public void addPlantToMap(int slot, Lawn lawn, int x, int y) throws InvalidDeployException {
        try {
            if(y != 0 || y != 10){
                if (playablePlants.get(slot).getPlantingCooldown() == playablePlants.get(slot).getPlantingSpeed()) {
                    if(playablePlants.get(slot).getIsAquatic()){
                        if(x == 2 || x == 3){
                            lawn.getLand().get(x).get(y)
                                    .addPlant(playablePlants.get(slot).getClassPlant().getDeclaredConstructor()
                                            .newInstance());
                        } else {
                            throw new InvalidDeployException("Plant aquatic tidak dapat ditanam pada tile yang dipilih!");
                        }
                    } else if(!playablePlants.get(slot).getIsAquatic() && x != 2 && x != 3){
                        lawn.getLand().get(x).get(y)
                                .addPlant(playablePlants.get(slot).getClassPlant().getDeclaredConstructor()
                                        .newInstance());
                    } else {
                       if(x == 2 || x == 3 && lawn.TileHasLilypad(x, y)){
                            lawn.getLand().get(x).get(y)
                                   .addPlant(playablePlants.get(slot).getClassPlant().getDeclaredConstructor()
                                           .newInstance());
                       } else{
                        throw new InvalidDeployException("Plant tidak dapat ditanam! Belum ada lilypad!");
                       }
                    }
                } else {
                throw new InvalidDeployException("Plant masih dalam cooldown!");
                }
            } else {
                throw new InvalidDeployException("Plant tidak dapat ditanam di tile yang dipilih!");
            }
               
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removePlantFromMap(Lawn lawn, int row, int column) {
        lawn.getLand().get(row).get(column).removePlant();
    }
}
