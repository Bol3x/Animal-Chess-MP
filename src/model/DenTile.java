package src.model;

import src.model.Enums.AvailableColor;
import src.model.Enums.Terrain;

/**
 * Subclass of Tile that represents a den, containing a color enum to represent the player's color.
 */
public class DenTile extends Tile{
    private final AvailableColor COLOR;

    /* Constructor */
    /**
     * Den Tile constructor with specified color assigned.
     * @param loc - position DenTile is located at
     * @param clr - assigned color
     */
    public DenTile(Position loc, AvailableColor clr){
        super(loc, Terrain.DEN);
        COLOR = clr;
    }

    /**
     * Gets color of DenTile.
     * @return color enum of den.
     */
    public AvailableColor getColor(){
        return COLOR;
    }

}
