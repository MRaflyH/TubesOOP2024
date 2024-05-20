package visitor;

import java.util.ArrayList;
import grid.Lawn;
import tile.*;

public class PlantVisitor extends Visitor{
    private ArrayList<Tile> row;
    
    public PlantVisitor(Lawn lawn, int row) {
        super(lawn);
        this.row = lawn.getLand().get(row);
    }

    public void visit() {
        for (Tile t : row) {
            
        }
    }

}
