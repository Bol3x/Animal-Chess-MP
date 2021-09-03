package src;

import java.util.ArrayList;
import java.util.Collections;

import src.Animals.Animal;
import src.Enums.AvailableColor;

/**
 * The Player object lists down all animal pieces held by a player, both owned and captured pieces.
 * <p>
 * It also contains the particular color of the player as a den terrain enumeration.
 */
public class Player{

    /* Class Variables */
    private ArrayList<Animal> Pieces = new ArrayList<Animal>();
    private ArrayList<Animal> capturedPieces = new ArrayList<Animal>();
    private final AvailableColor COLOR;
    private int SCORE;
    
    /* Constructor */
    /**
     * Player constructor.
     * @param color - Player's chosen color
     */
    public Player(AvailableColor color){
        this.COLOR = color;
        SCORE = 0;
    }

    /* Getters */
    /**
     * Gets color belonging to player.
     * @return COLOR
     */
    public AvailableColor getColor(){
        return this.COLOR;
    }
    
    /**
     * Gets ArrayList of animals belonging to player.
     * @return Pieces field
     */
    public ArrayList<Animal> getPieces(){
        return Pieces;
    }
    
    /**
     * Gets Arraylist of animals captured by player.
     * @return capturedPieces field
     */
    public ArrayList<Animal> getCapturedPieces(){
        return capturedPieces;
    }

    /**
     * Gets score of player
     * @return score value
     */
    public int getScore(){
        return SCORE;
    }

    /* Setters */
    /**
     * Adds pieces belonging to player.
     * @param animal - Animal piece to add
     */
    public void addPieces(Animal animal){
        Pieces.add(animal);
    }

    /**
     * Adds pieces captured by player.
     * @param captured - Captured animal piece to add
     */
    public void addCapturedPieces(Animal captured){
        capturedPieces.add(captured);
    }

    /**
     * Removes piece from player (captured by other player).
     * @param animal - Animal to remove
     */
    public void removePiece(Animal animal){
        Pieces.remove(animal);
    }

    /**
     * increases score by 1
     */
    public void increaseScore(){
        SCORE++;
    }

    /* Methods */

    /**
     * Sorts pieces according to rank.
     */
    public void sortPieces(){
        Collections.sort(Pieces);
    }

    /**
     * Lists down pieces of player.
     */
    public void listPieces(){
        for(Animal animal: Pieces){
            System.out.println
            ((Pieces.indexOf(animal)+1) + ": " + animal.getSpecies() );
        }
    }

    /**
     * Lists down captured pieces by player.
     */
    public void listCapturedPieces(){
        for(Animal animal : capturedPieces){
            System.out.println
            ((capturedPieces.indexOf(animal)+1) + ": " + animal.getSpecies() );
        }
    }

    /**
     * resets player storages for new game
     */
    public void resetPlayer(){
        Pieces = new ArrayList<Animal>();
        capturedPieces = new ArrayList<Animal>();
    }

    /**
     * gets the color of the player as a string
     * @return color of player as string
     */
    @Override
    public String toString(){
        return this.COLOR.toString();
    }
}
