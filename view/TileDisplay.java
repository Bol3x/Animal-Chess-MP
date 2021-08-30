package view;

import javax.swing.JButton;

import src.Position;


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
}
