package src;

import java.util.*;

import src.Animals.*;
import src.Enums.*;

/**
 * handles the player initialization and player order.
 */
public class PlayerHandler {

    private Player[] players = new Player[2];
    private Animal[] animalChoices = new Animal[2];
    private final int nFirstPlayer;


    /**
     * constructor for PlayerHandler, initializes first player immediately.
     */
    public PlayerHandler(int[] nChoices){
        nFirstPlayer = initFirstPlayer(nChoices);
    }

    /**
     * Initializes order of turns and player colors
     */
    private int initFirstPlayer(int[] nChoices){

        //create temp storage 
        ArrayList<Animal> temp = new ArrayList<Animal>();
        
        //create temp pieces
        temp.add(new Animal(null, 1, AnimalName.Mouse   , null, null, null));
        temp.add(new Animal(null, 2, AnimalName.Cat     , null, null, null));
        temp.add(new Animal(null, 3, AnimalName.Wolf    , null, null, null));
        temp.add(new Animal(null, 4, AnimalName.Dog     , null, null, null));
        temp.add(new Animal(null, 5, AnimalName.Leopard , null, null, null));
        temp.add(new Animal(null, 6, AnimalName.Tiger   , null, null, null));
        temp.add(new Animal(null, 7, AnimalName.Lion    , null, null, null));
        temp.add(new Animal(null, 8, AnimalName.Elephant, null, null, null));

        //shuffle pieces
        Collections.shuffle(temp);

        //player selects
        for(int i = 0; i < 2; i++){
            //store choice in temp animal and shuffle temp
            animalChoices[i] = temp.get(nChoices[i]-1);
            Collections.shuffle(temp);
        }

        //determine first player turn
        boolean bHigher = animalChoices[0].getRank() >= animalChoices[1].getRank();
        if (bHigher){
            //return 0th index as first player
            return 0;
        }
        else{ 
            //return 1st index as first player
            return 1;
        } 
    }

    public Animal[] getAnimalChoices(){
        return animalChoices;
    }

    /**
     * Gets the index of the first player to move in <code>players[]</code>.
     * @return 1 or 0, based on result of <code>initPlayerColors()</code>
     */
    public int getFirstPlayerIdx(){
        return nFirstPlayer;
    }

    /**
     * Gets the index of the second player to move in <code>players[]</code>.
     * @return 1 or 0, based on result of <code>initPlayerColors()</code>
     */
    public int getSecondPlayerIdx(){
        return nFirstPlayer == 1 ? 0 : 1;
    }
    
    /**
     * gets the first player.
     * @return first player reference
     */
    public Player getFirstPlayer(){
        return players[getFirstPlayerIdx()];
    }

    /**
     * gets the second player.
     * @return second player reference
     */
    public Player getSecondPlayer(){
        return players[getSecondPlayerIdx()];
    }

    /**
     * gets players
     * @return players array
     */
    public Player[] getPlayers(){
        return players;
    }

    /**
     * initializes first player with assigned color
     * @param color color to assign to first player
     */
    public void initFirstPlayerColor(AvailableColor color){
        players[getFirstPlayerIdx()] = new Player(color);
    }

    /**
     * initializes second player with assigned color
     * @param color color to assign to second player
     */
    public void initSecondPlayerColor(AvailableColor color){
        players[getSecondPlayerIdx()] = new Player(color);
    }

    /**
     * resets players
     */
    public void resetPlayers(){
        getFirstPlayer().resetPlayer();
        getSecondPlayer().resetPlayer();
    }
}
