package src;

import java.util.*;

import src.Animals.*;
import src.Enums.*;

public class PlayerHandler {

    private Player players[] = new Player[2];
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
        temp.add(new Animal(null, 1, AnimalName.Mouse   , null));
        temp.add(new Animal(null, 2, AnimalName.Cat     , null));
        temp.add(new Animal(null, 3, AnimalName.Wolf    , null));
        temp.add(new Animal(null, 4, AnimalName.Dog     , null));
        temp.add(new Animal(null, 5, AnimalName.Leopard , null));
        temp.add(new Animal(null, 6, AnimalName.Tiger   , null));
        temp.add(new Animal(null, 7, AnimalName.Lion    , null));
        temp.add(new Animal(null, 8, AnimalName.Elephant, null));

        //shuffle pieces
        Collections.shuffle(temp);

        //temp animals
        Animal[] tempAnimals = new Animal[2];


        //player selects
        for(int i = 0; i < 2; i++){
            //store choice in temp animal and shuffle temp
            tempAnimals[i] = temp.get(nChoices[i]-1);
            Collections.shuffle(temp);
        }

        System.out.println("Player 1 Chose: " + tempAnimals[0].getSpecies() + ": Rank " + tempAnimals[0].getRank());
        System.out.println("Player 2 Chose: " + tempAnimals[1].getSpecies() + ": Rank " + tempAnimals[1].getRank());

        //determine first player turn
        boolean bHigher = tempAnimals[0].getRank() >= tempAnimals[1].getRank();
        if (bHigher){
            System.out.println("Player 1 goes first!");
            //return 0th index as first player
            return 0;
        }
        else{ 
            System.out.println("Player 2 goes first!");
            //return 1st index as first player
            return 1;
        } 
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
        return nFirstPlayer == 1 ? 1 : 0;
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

    public void initFirstPlayerColor(AvailableColor color){
        players[getFirstPlayerIdx()] = new Player(color);
    }

    public void initSecondPlayerColor(AvailableColor color){
        players[getSecondPlayerIdx()] = new Player(color);
    }
}
