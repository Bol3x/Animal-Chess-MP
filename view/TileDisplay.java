package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import src.Position;
import src.Animals.Animal;


public class TileDisplay extends JButton{
    private final Position pos;

    public TileDisplay(Position loc){
        super();
        this.setContentAreaFilled(false);
        pos = loc;
    }

    public Position getPosition(){
        return pos;
    }

    /**
     * displays the animal that is on the position of the current button
     * @param animal animal object in the tile representation
     */
    public void displayAnimal(Animal animal){
        //if the animal 
            //image = animal.getIcon();
            highlightColor = animal.getFaction().getColor().getVisibleColor();
    }

    public void removeAnimal(){
        image = null;
        highlightColor = Color.GREEN;
    }
}
