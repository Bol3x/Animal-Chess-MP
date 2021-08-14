package src.Animals;

import src.Player;
import src.Tile;

/**
 * Mouse subclass of Animal. Specifies a capture method unique to Mouse objects.
 */
public class Mouse extends Animal{
    public Mouse(Player Faction, int Rank, String strSpecies, Tile pos){
        super(Faction, Rank, strSpecies, pos);
    }

    @Override
    public boolean capture(Animal other){
        if (other != null){
            /*
             if other animal is opposing faction AND
             other animal is an elephant OR is equal rank OR other is trapped 
             */
            if(this.isOpposingFaction(other) 
            && (other instanceof Elephant || isHigherOrEqualRank(other) || other.getTile().isTrap())){

                //if mouse is in river and other is not, or vice versa
                if( (this.getTile().isRiver() && !other.getTile().isRiver() )
                || (!this.getTile().isRiver() && other.getTile().isRiver()) )
                    return false;

                other.switchCapture();
                this.getFaction().addCapturedPieces(other);
                other.getFaction().removePiece(other);
                return true;
            }
            //else, can't capture
            return false;
        }
        //if other is null / default action
        return true;
    }
}