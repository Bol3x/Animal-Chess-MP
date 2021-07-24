package src;

/**
 * Class for animal pieces to be used in the <code>GameBoard> class.
 * <p>
 * Each animal object will have their respective player faction, rank, species name, position, and state within the game, 
 * and will be stored within the <code>Tile</code> and <code>Player</code> classes.
 */
public class Animal implements Comparable<Animal> {

    /* Class Variables */
    private final Player PLAYER_FACTION;
    private final int RANK;
    private final String SPECIES;
    private Position pos;
    private boolean bCaptured = false;

    /* Constructor */
    /**
     * Constructor for Animal object
     * @param Faction - player it belongs to
     * @param Rank - rank of animal
     * @param strSpecies - name of animal
     * @param position - initial position
     */
    public Animal(Player Faction, int Rank, String strSpecies, Position position){
        this.PLAYER_FACTION = Faction;
        this.RANK = Rank;
        this.SPECIES = strSpecies;
        this.pos = position;
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
    public String getSpecies(){
        return this.SPECIES;
    }

    /**
     * Gets animal's position.
     * @return pos field
     */
    public Position getPosition(){
        return this.pos;
    }

    /**
     * Gets animal's state (captured or not).
     * @return bCaptured field
     */
    public boolean isCaptured(){
        return this.bCaptured;
    }

    /**Setters*/

    /**
     * Sets the state of the animal (captured or not).
     * @param bool - value to update with
     */
    public void setCapture(boolean bool){
        this.bCaptured = bool;
    }

    /**
     * Sets a new position for the animal
     * @param newPos - new position to update pos with
     */
    public void setPosition(Position newPos){
        this.pos = newPos;
    }

    /**Methods*/

    /**
     * check if animal's rank is higher than opponent's
     * @param opponentAnimal - opponent to compare rank to
     * @return boolean value
     * 
     * @author Carlo
     */
    public boolean isHigherOrEqualRank(Animal opponentAnimal){
        return (this.RANK >= opponentAnimal.RANK);
    }

    /**
     * check if animal and other are in opposing teams
     * @param otherAnimal - other animal to compare factions with
     * @return foolean value
     */
    public boolean isOpposingFaction(Animal otherAnimal){
        return (this.PLAYER_FACTION != otherAnimal.PLAYER_FACTION);
    }

    /**
     * check if rank is 1(mouse)
     * @return boolean value
     */
    public boolean isMouse(){
        return (this.RANK == 1);
    }

    /**
     * check if rank is 8(elephant)
     * @return boolean value
     */
    public boolean isElephant(){
        return (this.RANK == 8);
    }

    /**
     * check if rank is 6(tiger) or 7(lion)
     * @param animal - animal to check
     * @return boolean value
     */
    public static boolean canJump(Animal animal){
        return (animal.RANK == 6 || animal.RANK == 7);
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
