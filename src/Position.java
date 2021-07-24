package src;

/**
 * An object denoting the location of another object in the GameBoard.
 */
public class Position {
    
    /* Class Variables */
    private final int X; 
    private final int Y;
    
    /**
     * Constructor for Position object
     * @param x - row
     * @param y - column
     */
    public Position(int x, int y) {
        this.X = x;
        this.Y = y;
    }
            
    /* Getters */ 

    /**
     * Gets x of position
     * @return X field
     */
    public int getX() {
        return this.X;
    }
    
    /**
     * Gets Y of position
     * @return Y field
     */
    public int getY() {
        return this.Y;
    }
 
    /**
     * checks if position is within bounds of gameBoard.
     * @param pos - position to check
     * @return true if within bounds, false if not
     */
    public static boolean isWithinBounds (Position pos) {
        return (pos.X >= 0 && pos.X < GameBoard.ROW) 
        && (pos.Y >= 0 && pos.Y < GameBoard.COL);
    }
     
    /* Method overrides */
    /**
     * checks if the X and Y of position is equal to obj.
     * @param obj - object to compare to
     */
    @Override
    public boolean equals (Object obj) {
        if(obj instanceof Position) {
            Position otherPosition = (Position) obj;
            return this.X == otherPosition.X && this.Y == otherPosition.Y;
        }
        return false;
    }
}
