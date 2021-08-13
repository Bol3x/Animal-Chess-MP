package src.Animals;

import src.Player;
import src.Tile;

public class Elephant extends Animal{
    public Elephant(Player Faction, int Rank, String strSpecies, Tile pos){
        super(Faction, Rank, strSpecies, pos);
    }

    @Override
    public boolean captureAnimal(Animal other){
        if (other != null){
            if (this.isOpposingFaction(other)
            && this.isHigherOrEqualRank(other)
            && (!(other instanceof Mouse) || (other instanceof Mouse && other.getTile().isTrap()) )
            ){
                other.switchCapture();
                this.getFaction().addCapturedPieces(other);
                other.getFaction().removePiece(other);
                return true;
            }
            return false;
        }
        return true;
    }
}
