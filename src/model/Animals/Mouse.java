package src.model.Animals;

import java.awt.image.BufferedImage;

import src.model.Player;
import src.model.Tile;
import src.model.Enums.AnimalName;

/**
 * Mouse subclass of Animal. Specifies a capture method unique to Mouse objects.
 */
public class Mouse extends Animal{
    public Mouse(Player Faction, BufferedImage img, BufferedImage disabled_img, Tile pos){
        super(Faction, 1, AnimalName.Mouse, img, disabled_img, pos);
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

                //otherwise,
                return true;
            }
            //else, can't capture
            return false;
        }
        //if other is null / default action
        return true;
    }
}