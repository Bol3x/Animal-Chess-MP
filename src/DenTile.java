package src;

import src.Enums.Color;
import src.Enums.Terrain;

/**
 * Subclass of Tile that represents a den, containing a color enum to represent the player's color.
 */
public class DenTile extends Tile{
    private final Color COLOR;

    /* Constructor */
    /**
     * Den Tile constructor with specified color assigned.
     * @param loc - position DenTile is located at
     * @param clr - assigned color
     */
    public DenTile(Position loc, Color clr){
        super(loc, Terrain.DEN);
        COLOR = clr;
    }

    /**
     * Gets color of DenTile.
     * @return color enum of den.
     */
    public Color getColor(){
        return COLOR;
    }

}
