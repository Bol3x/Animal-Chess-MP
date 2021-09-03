package src.model.Animals;

import java.awt.image.BufferedImage;

import src.model.Player;
import src.model.Tile;
import src.model.Enums.AnimalName;

/**
 * Class for animal pieces to be used in the <code>GameBoard</code> class.
 * Each animal object will have their respective player color faction, rank, species name, tile, and state within the game, 
 * and will be stored within the <code>Tile</code> and <code>Player</code> classes.
 */
public class Animal implements Comparable<Animal> {

    /* Class Variables */
    protected final Player PLAYER_FACTION;
    protected final int RANK;
    protected final AnimalName SPECIES;
    protected final BufferedImage IMG;
    protected final BufferedImage DISABLED_IMG;
    protected Tile tile;

    /* Constructor */
    /**
     * Constructor for Animal object
     * @param Faction - player it belongs to
     * @param Rank - rank of animal
     * @param strSpecies - name of animal
     * @param pos - initial tile
     */
    public Animal(Player Faction, int Rank, AnimalName species, BufferedImage img, BufferedImage disabledImage, Tile pos){
        PLAYER_FACTION = Faction;
        RANK = Rank;

        IMG = img;
        DISABLED_IMG = disabledImage;

        SPECIES = species;
        tile = pos;
    }
    
    /**Getters*/
    /**
     * Gets player the animal belongs to.
     * @return PLAYER_FACTION field
     */
    public Player getFaction(){
        return this.PLAYER_FACTION;
    }
    
    /**
     * Gets animal's rank.
     * @return RANK field
     */
    public int getRank(){
        return this.RANK;
    }

    /**
     * Gets animal's name.
     * @return SPECIES field
     */
    public AnimalName getSpecies(){
        return this.SPECIES;
    }

    /**
     * Gets animal's current tile.
     * @return tile field
     */
    public Tile getTile(){
        return tile;
    }

    /**
     * Gets image of animal
     */
    public BufferedImage getImage(){
        return IMG;
    }

    /**
     * Gets disabled image of animal (when tile is disabled)
     */
    public BufferedImage getDisabledImage(){
        return DISABLED_IMG;
    }

    /**Setters*/

    /**
     * Sets a new tile for the animal
     * @param newTile - new tile to update pos with
     */
    public void setTile(Tile newTile){
        this.tile = newTile;
    }

    /**Methods*/

    /**
     * check if animal's rank is higher than opponent's
     * @param opponentAnimal - opponent to compare rank to
     * @return boolean value
     */
    public boolean isHigherOrEqualRank(Animal opponentAnimal){
        return (this.RANK >= opponentAnimal.RANK);
    }

    /**
     * check if animal and other are in opposing teams
     * @param otherAnimal - other animal to compare factions with
     * @return boolean value
     */
    public boolean isOpposingFaction(Animal otherAnimal){
        return (this.PLAYER_FACTION != otherAnimal.PLAYER_FACTION);
    }

    /**
     * Checks if this animal can capture other if not null.
     * @param other - other animal to be captured
     * @return true if captured/empty, false if not capturable
     * 
     * @version 1.3 - moved method to <code>Animal</code> class for specific instructions.
    */
    public boolean capture(Animal other){
        if (other != null){
            /*
            if other animal is opposing faction AND
            other animal is lower rank OR is trapped
            */
            if (this.isOpposingFaction(other)
            && (this.isHigherOrEqualRank(other) || other.getTile().isTrap()) ){
                return true;
            }
            //else, can't capture
            return false;
        }
        return true;
    }

    /**
     * Checks if the animal is a jumper (rank 6 or 7)
     * @return true if is a jumper, false if not
     */
    public boolean canJump(){
        return RANK == 6 || RANK == 7;
    }

    /**
     * Comparable method override to sort animals by rank.
    */
    @Override
    public int compareTo(Animal other){
        if (this.RANK > other.RANK)
            return 1;
        if (this.RANK == other.RANK)
            return 0;
        return -1;
    }

}
