package src;

public class Tile {

    /**Class Variables*/
    private Animal animal;
    private final Terrain TERRAIN;
    private final Position LOCATION;

    /**Constructors*/
    public Tile(Position loc, Terrain ter){
        this.animal = null;
        this.TERRAIN = ter;
        this.LOCATION = loc;
    }

    public Tile(Position loc){
        this.animal = null;
        this.TERRAIN = null;
        this.LOCATION = loc;
    }

    /**Getters*/
    public Animal getAnimal(){
        return this.animal;
    }

    public Terrain getTerrain(){
        return this.TERRAIN;
    }

    public Position getLocation(){
        return this.LOCATION;
    }

    /**Setters*/
    public void setAnimal(Animal anim){
        this.animal = anim;
    }

    /**Methods*/
    public boolean hasAnimal(){
        if (this.animal != null)
            return true;
        //else
        return false;
    }

    public boolean isRiver(){
        if (this.TERRAIN == Terrain.RIVER)
            return true;
        //else
        return false;
    }

    public boolean isTrap(){
        if (this.TERRAIN == Terrain.TRAP)
            return true;
        //else
        return false;
    }

}
