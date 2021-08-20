package src.Animals;

import src.Player;
import src.Tile;
import src.Enums.AnimalName;

/**
 * Elephant subclass of Animal. Specifies a capture method unique to Elephant objects.
 */
public class Elephant extends Animal{
    public Elephant(Player Faction, Tile pos){
        super(Faction, 8, AnimalName.Elephant, pos);
    }

    @Override
    public boolean capture(Animal other){
        if (other != null){
            /*
            if other animal is opposing faction AND
            is higher or equal rank AND
            other animal is not a mouse OR is trapped,
            capture will occur
            */
            if (this.isOpposingFaction(other)
            && this.isHigherOrEqualRank(other)
            && (!(other instanceof Mouse) || other.getTile().isTrap() )
            ){
                other.switchCapture();
                this.getFaction().addCapturedPieces(other);
                other.getFaction().removePiece(other);
                //return true
                return true;
            }
            //else, can't capture
            return false;
        }
        //if other is null / default action
        return true;
    }
}