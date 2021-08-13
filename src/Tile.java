package src;

import src.Animals.Animal;
import src.Enums.Terrain;

/**
 * Represents a location for <code>Animal</code> objects and special <code>Terrain</code> enumerations.
 */
public class Tile{

    /* Class Variables */
    protected Animal animal;
    protected final Terrain TERRAIN;
    protected final Position LOCATION;

    /* Constructors */
    /**
     * Tile Constructor with specific terrain.
     * @param loc - Position to set
     * @param ter - Terrain to set
     */
    public Tile(Position loc, Terrain ter){
        this.animal = null;
        this.TERRAIN = ter;
        this.LOCATION = loc;
    }

    /**
     * Tile Constructor without terrain.
     * @param loc - Position to set
     */
    public Tile(Position loc){
        this.animal = null;
        this.TERRAIN = null;
        this.LOCATION = loc;
    }

    /* Getters */

    /**
     * Gets animal stored in tile.
     * @return animal field
     */
    public Animal getAnimal(){
        return this.animal;
    }

    /**
     * Gets terrain stored in tile.
     * @return TERRAIN field
     */
    public Terrain getTerrain(){
        return this.TERRAIN;
    }

    /**
     * Gets position of tile.
     * @return LOCATION field
     */
    public Position getLocation(){
        return this.LOCATION;
    }

    /* Setters */
    /**
     * Sets animal to tile.
     * @param anim - Animal to set
     */
    public void setAnimal(Animal anim){
        this.animal = anim;
    }

    /* Methods */
    /**
     * Checks if tile has an animal.
     * @return boolean value
     */
    public boolean hasAnimal(){
        if (this.animal != null)
            return true;
        //else
        return false;
    }


    /**
     * checks if tile has a river terrain.
     * @return boolean value
     */
    public boolean isRiver(){
        if (this.TERRAIN == Terrain.RIVER)
            return true;
        //else
        return false;
    }

    /**
     * checks if tile has a trap terrain.
     * @return boolean value
     */
    public boolean isTrap(){
        if (this.TERRAIN == Terrain.TRAP)
            return true;
        //else
        return false;
    }

    /**
     * checks if tile is one of the 2 dens.
     * @return boolean value
     */
    public boolean isDen(){
        if(this.TERRAIN == Terrain.DEN)
            return true;
        //else
        return false;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;

        Tile other = (Tile) obj;
        return other.LOCATION.equals(this.LOCATION);
    }   
}
