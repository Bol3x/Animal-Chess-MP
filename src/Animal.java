package src;

public class Animal {
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

    /**check if animal's rank is higher than opponent's*/
    public boolean isHigherOrEqualRank(Animal opponentAnimal){
        return (this.RANK >= opponentAnimal.RANK);
    }

    /**check if animal and other are in opposing teams*/
    public boolean isOpposingFaction(Animal otherAnimal){
        return (this.PLAYER_FACTION != otherAnimal.PLAYER_FACTION);
    }

    /**check if rank is 1(mouse)*/
    public static boolean isMouse(Animal animal){
        return (animal.RANK == 1);
    }

    /**check if rank is 8(elephant)*/
    public static boolean isElephant(Animal animal){
        return (animal.RANK == 8);
    }

    /**check if rank is 6(tiger) or 7(lion)*/
    public static boolean canJump(Animal animal){
        return (animal.RANK == 6 || animal.RANK == 7);
    }

}
