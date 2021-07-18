package src;

public class Animal implements Comparable<Animal> {
    private final Player PLAYER_FACTION;
    private final int RANK;
    private final String SPECIES;
    private Position pos;
    private boolean bCaptured = false;

    /**Constructor*/
    public Animal(Player Faction, int Rank, String strSpecies, Position position){
        this.PLAYER_FACTION = Faction;
        this.RANK = Rank;
        this.SPECIES = strSpecies;
        this.pos = position;
    }
    
    /**Getters*/
    public Player getFaction(){
        return this.PLAYER_FACTION;
    }
    
    public int getRank(){
        return this.RANK;
    }

    public String getSpecies(){
        return this.SPECIES;
    }

    public Position getPosition(){
        return this.pos;
    }

    public boolean isCaptured(){
        return this.bCaptured;
    }

    /**Setters*/

    public void setCapture(boolean bool){
        this.bCaptured = bool;
    }

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
     * 
     * @author Carlo
     */
    public boolean isOpposingFaction(Animal otherAnimal){
        return (this.PLAYER_FACTION != otherAnimal.PLAYER_FACTION);
    }

    /**
     * check if rank is 1(mouse)
     * @param animal - Animal to check
     * @return boolean value
     * 
     * @author Carlo
     */
    public static boolean isMouse(Animal animal){
        return (animal.RANK == 1);
    }

    /**
     * check if rank is 8(elephant)
     * @param animal - Animal to check
     * @return boolean value
     * 
     * @author Carlo
     */
    public static boolean isElephant(Animal animal){
        return (animal.RANK == 8);
    }

    /**
     * check if rank is 6(tiger) or 7(lion)
     * @param animal - animal to check
     * @return boolean value
     * 
     * @author Carlo
     */
    public static boolean canJump(Animal animal){
        return (animal.RANK == 6 || animal.RANK == 7);
    }

    /**
     * Comparable method override to sort animals by rank
     * 
     * @author Carlo
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
