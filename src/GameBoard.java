package src;

public class GameBoard {
    public static final int ROW = 9;
    public static final int COL = 7;
    
    private int nTurn;
    private boolean bGameWin = true;
    private boolean bForceExit = false;
    private Tile playBoard[][] = new Tile[ROW][COL];
    private Player players[] = new Player[2];


    /**
     * Initializes all tiles and pieces onto the board, 
     * and provides pieces ownership to their respective player
     * @version initial test
     * @author Carlo
     */
    public void initPlayBoard(){

        //set dens
        playBoard[0][3] = new Tile(new Position(0,3) , Terrain.DEN1);
        playBoard[ROW-1][3] = new Tile(new Position(ROW-1,3) , Terrain.DEN2);

        //set traps
        playBoard[0][2] = new Tile(new Position(0,2) , Terrain.TRAP);
        playBoard[0][4] = new Tile(new Position(0,4) , Terrain.TRAP);
        playBoard[1][3] = new Tile(new Position(1,3) , Terrain.TRAP);
        playBoard[8][2] = new Tile(new Position(8,2) , Terrain.TRAP);
        playBoard[8][4] = new Tile(new Position(8,4) , Terrain.TRAP);
        playBoard[7][3] = new Tile(new Position(7,3) , Terrain.TRAP);

        //set other tiles
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                //set rivers
                if ( (i >= 3 && i <= 5) 
                && ((j >= 1 && j <= 2) || (j >= 4 && j <= 5)) )
                    playBoard[i][j] = new Tile(new Position(i,j) , Terrain.RIVER);
                else if (playBoard[i][j] == null)
                    playBoard[i][j] = new Tile(new Position(i,j));
            }
        }
    }

    /**Constructor*/
    public GameBoard(){
        initPlayBoard();
    }

    /**Getters*/
    public Tile[][] getPlayBoard(){
        return playBoard;
    }

    public Tile getTile(int X, int Y){
        return playBoard[X][Y];
    }

    public int getTurn(){
        return nTurn;
    }

    public boolean checkGameState(){
        return (bGameWin && bForceExit);
    }

    /**Methods*/

    /**
     * sets new position of animal if new position is valid.
     * If there is an opposing animal in the new position, 
     * checks if the animal can be captured. Otherwise, retain position.
     * @param currAnimal current animal to move
     * @param newPos new position to check
     * 
     * @return true if successful, false if unsuccesful
     * @author Carlo
     */
    public boolean moveAnimal(Animal currAnimal, Position newPos){
        Tile newTile = searchTile(newPos);
        if ( isValidPosition(currAnimal, newPos)
        && captureAnimal( currAnimal, newTile.getAnimal()) ){
            //remove animal from old tile
            Tile oldTile = searchTile( currAnimal.getPosition() );
            oldTile.setAnimal(null); 

            newTile.setAnimal(currAnimal);
            currAnimal.setPosition(newPos);
            return true;
        }
        return false;
    }

    /**
     * 
     * 
     * @param other
     * @return
    */
    public boolean captureAnimal(Animal currAnimal, Animal otherAnimal){
        //if other animal exists in tile
        if(otherAnimal != null){
            if(currAnimal.isOpposingFaction(otherAnimal) 
            && currAnimal.isHigherOrEqualRank(otherAnimal)){
                otherAnimal.setCapture(true);

                //update player stores
                currAnimal.getFaction().setCaptured(otherAnimal);
                currAnimal.getFaction().addNumCaptured();
                //update other player stores
                otherAnimal.getFaction().removePiece(otherAnimal);
                otherAnimal.getFaction().reduceNumPieces();
                return true;
            }
            return false;
        }
        // if null/default
        return true;
    }

    /**
     * Searches for tile with matching position
     * @param pos position of tile to search
     * @return
     */
    public Tile searchTile(Position pos){
        if (Position.isWithinBounds(pos))
            for(int i = 0; i < ROW; i++)
                for(int j = 0; j < COL; j++)
                    if (playBoard[i][j].getLocation().equals(pos) )
                        return playBoard[i][j];
        
        return null;
    }

    public boolean isValidPosition(Animal currAnimal, Position pos){
        if (Position.isWithinBounds(pos) ){
            if( !(Animal.isMouse(currAnimal) || Animal.canJump(currAnimal)) 
            && searchTile(pos).isRiver()){
                return false;
            }
            else if (searchTile(pos).getTerrain() == currAnimal.getFaction().getDen())
                return false;

            return true;
        }
        return false;
    }

    /**
     * checks traps if there is an opponent animal close to a den.
     * @return Tile of animal that triggered check
     * 
     * @version initial test
     * @author Carlo
     * */
    public Tile checkTrap(){
        //traps near DEN1
        for(int i = 0; i <= 1; i++){
            for(int j = 2; j <= 4; j++){
                if(playBoard[i][j].hasAnimal()
                && playBoard[i][j].getAnimal().getFaction().getDen() != Terrain.DEN1)
                    return playBoard[i][j];
            }
        }

        //traps near DEN2
        for(int i = 7; i <= 8; i++){
            for(int j = 2; j <= 4; j++){
                if(playBoard[i][j].hasAnimal()
                && playBoard[i][j].getAnimal().getFaction().getDen() != Terrain.DEN2)
                    return playBoard[i][j];
            }
        }

        //no opponent animals close to den
        return null;
    }

}
