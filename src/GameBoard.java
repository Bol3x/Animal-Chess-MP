package src;

public class GameBoard {
    private static final int ROW = 9;
    private static final int COL = 7;
    
    private int nTurn;
    private boolean bGameRunning = true;
    private boolean bForceExit = false;
    private Tile playBoard[][] = new Tile[ROW][COL];
    private Player players[] = new Player[2];

}
