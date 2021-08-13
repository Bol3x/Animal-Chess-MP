package src;

import src.Enums.Color;
import src.Enums.Terrain;

public class DenTile extends Tile{
    private final Color COLOR;
    public DenTile(Position loc, Color clr){
        super(loc, Terrain.DEN);
        COLOR = clr;
    }

    public Color getColor(){
        return COLOR;
    }

}
