package src;

public class Position {
    
    private final int X; 
    private final int Y;
    
    public Position(int x, int y) {
        this.X = x;
        this.Y = y;
    }
            
    //** Getters */ 
    public int getX() {
        return this.X;
    }
    
    public int getY() {
        return this.Y;
    }
 
    public static boolean isWithinBounds (Position pos) {
        return pos.X < GameBoard.ROW && pos.Y < GameBoard.COL;
    }
     
    @Override
    public boolean equals (Object obj) {
        if(obj != null) {
            Position otherPosition = (Position) obj;
            return this.X == otherPosition.X && this.Y == otherPosition.Y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.X;
        hash = 97 * hash + this.Y;
        return hash;
    }
}
