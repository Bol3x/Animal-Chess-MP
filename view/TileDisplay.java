package view;

import javax.swing.JButton;

import src.Position;

/**
 * Transparent JButton with <code>Position</code> implemented.
 * <p>
 * Used to represent a game board <code>Tile</code> class in the GUI.
 */
public class TileDisplay extends JButton{
    private final Position POS;

    public TileDisplay(Position loc){
        super();
        this.setContentAreaFilled(false);
        POS = loc;
    }

    /**
     * gets position of tile (relative to its position in the TileDisplay grid)
     * @return position of tile
     */
    public Position getPosition(){
        return POS;
    }
}
