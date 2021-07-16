package src;

import java.util.ArrayList;

public class Player {
    private ArrayList<Animal> Pieces;
    private ArrayList<Animal> capturedPieces;
    private int numPieces;
    private int numCaptured = 0;
    private final Terrain DEN;
    
    public Player(Terrain den){
        this.DEN = den;
    }

    public Terrain getDen(){
        return this.DEN;
    }

    public int getNumPieces(){
        return this.numPieces;
    }

    public int getNumCaptured(){
        return this.numCaptured;
    }

    /**Setters*/
    public void reduceNumPieces(){
        numPieces--;
    }

    public void addNumPieces(){
        numCaptured++;
    }

    public void setPieces(Animal animal){
        Pieces.add(animal);
    }

    public void setCaptured(Animal captured){
        capturedPieces.add(captured);
    }

    public void removePiece(Animal animal){
        if(Pieces.contains(animal))
            Pieces.remove(animal);
    }
    /**Prototype Methods*/
    public void listPieces(){
        for(Animal animal: Pieces){
            System.out.println(Pieces.indexOf(animal) + ": " + animal.getSpecies());
        }
    }

    public void listCapturedPieces(){
        for(Animal animal : capturedPieces){
            System.out.println(capturedPieces.indexOf(animal) + ": " animal.getSpecies());
        }
    }
}
