package src;

import java.util.ArrayList;
import java.util.Collections;

public class Player{
    private ArrayList<Animal> Pieces = new ArrayList<Animal>();
    private ArrayList<Animal> capturedPieces = new ArrayList<Animal>();
    private final Terrain DEN;
    
    public Player(Terrain den){
        this.DEN = den;
    }

    public Terrain getDen(){
        return this.DEN;
    }


    public ArrayList<Animal> getPieces(){
        return Pieces;
    }
    
    public ArrayList<Animal> getCapturedPieces(){
        return capturedPieces;
    }

    /**Setters*/

    public void addPieces(Animal animal){
        Pieces.add(animal);
    }

    public void addCapturedPieces(Animal captured){
        capturedPieces.add(captured);
    }

    public void removePiece(Animal animal){
        Pieces.remove(animal);
    }

    /**Methods*/
    
    public void shufflePieces(){
        Collections.shuffle(Pieces);
    }

    public void sortPieces(){
        Collections.sort(Pieces);
    }

    /**Prototype Methods*/
    public void listPieces(){
        for(Animal animal: Pieces){
            System.out.println
            (Pieces.indexOf(animal) + ": " + animal.getSpecies() );
        }
    }

    public void listCapturedPieces(){
        for(Animal animal : capturedPieces){
            System.out.println
            (capturedPieces.indexOf(animal) + ": " + animal.getSpecies() );
        }
    }
}
