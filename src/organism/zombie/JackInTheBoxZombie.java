package organism.zombie;

import grid.Lawn;

public class JackInTheBoxZombie extends Zombie implements organism.ExplosionInterface {
    private boolean hasExploded = false;

    public JackInTheBoxZombie(){
        super("Jack In The Box Zombie", 175, 10000, 1, false, true);
    }

    public void explode(Lawn lawn, int idxrow, int i) {
        if (idxrow == 0 && i<10) {
            if (i>1) {
                if (lawn.getLand().get(idxrow+1).get(i).hasPlant()
                || lawn.getLand().get(idxrow+1).get(i+1).hasPlant()
                || lawn.getLand().get(idxrow+1).get(i-1).hasPlant()
                || lawn.getLand().get(idxrow).get(i+1).hasPlant() || lawn.getLand().get(idxrow).get(i-1).hasPlant() || lawn.getLand().get(idxrow).get(i).hasPlant()) {
                    lawn.getLand().get(idxrow+1).get(i).removePlant();
                    lawn.getLand().get(idxrow+1).get(i+1).removePlant();
                    lawn.getLand().get(idxrow+1).get(i-1).removePlant();
                    lawn.getLand().get(idxrow).get(i+1).removePlant();
                    lawn.getLand().get(idxrow).get(i-1).removePlant();
                    lawn.getLand().get(idxrow).get(i).removePlant();
                    hasExploded = true;
                    this.loseHealth(this.getHealth());
                }
            }
            else {
                if (lawn.getLand().get(idxrow+1).get(i).hasPlant()
                || lawn.getLand().get(idxrow+1).get(i+1).hasPlant()
                || lawn.getLand().get(idxrow).get(i+1).hasPlant() || lawn.getLand().get(idxrow).get(i).hasPlant()) {
                    lawn.getLand().get(idxrow+1).get(i).removePlant();
                    lawn.getLand().get(idxrow+1).get(i+1).removePlant();
                    lawn.getLand().get(idxrow).get(i+1).removePlant();
                    lawn.getLand().get(idxrow).get(i).removePlant();
                    hasExploded = true;
                    this.loseHealth(this.getHealth());
                }
            }
        } 
        else if (idxrow == 5 && i<10) {
            if (i>1){
                if (lawn.getLand().get(idxrow-1).get(i).hasPlant()
                || lawn.getLand().get(idxrow-1).get(i+1).hasPlant()
                || lawn.getLand().get(idxrow-1).get(i-1).hasPlant()
                || lawn.getLand().get(idxrow).get(i+1).hasPlant() || lawn.getLand().get(idxrow).get(i-1).hasPlant() || lawn.getLand().get(idxrow).get(i).hasPlant()) {
                    lawn.getLand().get(idxrow-1).get(i).removePlant();
                    lawn.getLand().get(idxrow-1).get(i+1).removePlant();
                    lawn.getLand().get(idxrow-1).get(i-1).removePlant();
                    lawn.getLand().get(idxrow).get(i+1).removePlant();
                    lawn.getLand().get(idxrow).get(i-1).removePlant();
                    lawn.getLand().get(idxrow).get(i).removePlant();
                    hasExploded = true;
                    this.loseHealth(this.getHealth());
                }
            }
            else {
                if (lawn.getLand().get(idxrow-1).get(i).hasPlant()
                || lawn.getLand().get(idxrow-1).get(i+1).hasPlant()
                || lawn.getLand().get(idxrow).get(i+1).hasPlant() || lawn.getLand().get(idxrow).get(i).hasPlant()) {
                    lawn.getLand().get(idxrow-1).get(i).removePlant();
                    lawn.getLand().get(idxrow-1).get(i+1).removePlant();
                    lawn.getLand().get(idxrow).get(i+1).removePlant();
                    lawn.getLand().get(idxrow).get(i).removePlant();
                    hasExploded = true;
                    this.loseHealth(this.getHealth());
                }
            }
        } 
        else if (i<10) {
            if (i>1) {
                if (lawn.getLand().get(idxrow-1).get(i).hasPlant() || lawn.getLand().get(idxrow+1).get(i).hasPlant()
                || lawn.getLand().get(idxrow-1).get(i+1).hasPlant() || lawn.getLand().get(idxrow+1).get(i+1).hasPlant()
                || lawn.getLand().get(idxrow-1).get(i-1).hasPlant() || lawn.getLand().get(idxrow+1).get(i-1).hasPlant()
                || lawn.getLand().get(idxrow).get(i+1).hasPlant() || lawn.getLand().get(idxrow).get(i-1).hasPlant() || lawn.getLand().get(idxrow).get(i).hasPlant()) {
                    lawn.getLand().get(idxrow-1).get(i).removePlant();
                    lawn.getLand().get(idxrow+1).get(i).removePlant();
                    lawn.getLand().get(idxrow-1).get(i+1).removePlant();
                    lawn.getLand().get(idxrow+1).get(i+1).removePlant();
                    lawn.getLand().get(idxrow-1).get(i-1).removePlant();
                    lawn.getLand().get(idxrow+1).get(i-1).removePlant();
                    lawn.getLand().get(idxrow).get(i+1).removePlant();
                    lawn.getLand().get(idxrow).get(i-1).removePlant();
                    lawn.getLand().get(idxrow).get(i).removePlant();
                    hasExploded = true;
                    this.loseHealth(this.getHealth());
                }
            }
            else {
                if (lawn.getLand().get(idxrow-1).get(i).hasPlant() || lawn.getLand().get(idxrow+1).get(i).hasPlant()
                || lawn.getLand().get(idxrow-1).get(i+1).hasPlant() || lawn.getLand().get(idxrow+1).get(i+1).hasPlant()
                || lawn.getLand().get(idxrow).get(i+1).hasPlant() || lawn.getLand().get(idxrow).get(i).hasPlant()) {
                    lawn.getLand().get(idxrow-1).get(i).removePlant();
                    lawn.getLand().get(idxrow+1).get(i).removePlant();
                    lawn.getLand().get(idxrow-1).get(i+1).removePlant();
                    lawn.getLand().get(idxrow+1).get(i+1).removePlant();
                    lawn.getLand().get(idxrow).get(i+1).removePlant();
                    lawn.getLand().get(idxrow).get(i).removePlant();
                    hasExploded = true;
                    this.loseHealth(this.getHealth());
                }
            }
        }
    }

    public boolean hasExploded(){
        return hasExploded;
    }

}
