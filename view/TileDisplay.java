package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import src.Animals.Animal;


public class TileDisplay extends JButton{
    private int thickness = 4;
    private Color highlightColor = Color.green;
    private BufferedImage image;

    public TileDisplay(){
        super();
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
