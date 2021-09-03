package src.model;

import javax.imageio.ImageIO;

import src.model.Animals.*;
import src.model.Enums.*;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Stores the main game board composed of <code>Tile</code> objects and controls movement of pieces within the board.
 * <p>
 * Handles initialization of gameplay features; Board initialization, animal movement, and win conditions.
 */
public class GameBoard {
    /**
     * Row size of playBoard
     */
    public static final int ROW = 9;
    /**
     * Col size of playBoard
     */
    public static final int COL = 7;

    /**
     * filepath for images
     */
    private final String IMAGE_PATH = "/view/images/animals/";

    private Tile playBoard[][] = new Tile[ROW][COL];
    private PlayerHandler playerHandler;

    /* Constructor */
    /**
     * Constructs a playBoard and initializes players and animals to designated locations.
     */
    public GameBoard(PlayerHandler pHandler){
        playerHandler = pHandler;
        initializeBoard();
    }

    /**
     * creates all tiles and pieces onto the board, 
     * and provides pieces' ownership to a respective player.
     */
    public void initializeBoard(){
        //set dens
        playBoard[0][3] = new DenTile(new Position(0,3) , playerHandler.getSecondPlayer().getColor());
        playBoard[8][3] = new DenTile(new Position(8,3) , playerHandler.getFirstPlayer().getColor());

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
                && (j == 1 || j == 2 || j == 4 || j == 5) )
                    playBoard[i][j] = new Tile(new Position(i,j) , Terrain.RIVER);
                else if (playBoard[i][j] == null)
                    playBoard[i][j] = new Tile(new Position(i,j));
            }
        }

        //initialize animals
        initTopAnimals(playerHandler.getSecondPlayer());
        initBottomAnimals(playerHandler.getFirstPlayer());
    }


    /**
     * initializes animals to the board and player fields
     * @param nPlayer - player owner
     * @param nRank - animal rank
     * @param strName - animal name
     * @param postion - initial tile position on board
     */
    private void initAnimal(Player player, int nRank, AnimalName species, String imgFilename, Position position){
        BufferedImage img = null, disabledImg = null;
        StringBuilder ImgFilepath = new StringBuilder(IMAGE_PATH);

        ImgFilepath.append(imgFilename);

        try{
            img = ImageIO.read(getClass().getResource(ImgFilepath.toString()+".png"));
            disabledImg = ImageIO.read(getClass().getResource(ImgFilepath.toString()+"_disabled.png"));

        } catch(IOException e){
            System.out.println(e);
        }

        switch(nRank){
            case 1: Mouse mouse = new Mouse(player, img, disabledImg, searchTile(position));
                    searchTile(position).setAnimal(mouse);
                    player.addPieces(mouse);
                    break;

            case 8: Elephant elephant = new Elephant(player, img, disabledImg, searchTile(position));
                    searchTile(position).setAnimal(elephant);
                    player.addPieces(elephant);
                    break;
            
            default:
                    Animal animal = new Animal(player, nRank, species, img, disabledImg, searchTile(position));
                    searchTile(position).setAnimal(animal);
                    player.addPieces(animal);
        }
    }

    /**
     * initializes animals in top area (top 3 rows).
     * @param nPlayer - player to assign animals to
     */
    private void initTopAnimals(Player player){
        StringBuilder filename = new StringBuilder(""+player.getColor().toString().toLowerCase()+"_");
        initAnimal(player, 1, AnimalName.Mouse    , filename.toString()+"mouse"   , new Position(2,0));
        initAnimal(player, 2, AnimalName.Cat      , filename.toString()+"cat"     , new Position(1,5));
        initAnimal(player, 3, AnimalName.Wolf     , filename.toString()+"wolf"    , new Position(2,4));
        initAnimal(player, 4, AnimalName.Dog      , filename.toString()+"dog"     , new Position(1,1));
        initAnimal(player, 5, AnimalName.Leopard  , filename.toString()+"leopard" , new Position(2,2));
        initAnimal(player, 6, AnimalName.Tiger    , filename.toString()+"tiger"   , new Position(0,6));
        initAnimal(player, 7, AnimalName.Lion     , filename.toString()+"lion"    , new Position(0,0));
        initAnimal(player, 8, AnimalName.Elephant , filename.toString()+"elephant", new Position(2,6));
    }

    /**
     * initializes animals in bottom area (bottom 3 rows).
     * @param nPlayer - player to assign animals to
     */
    private void initBottomAnimals(Player player){
        StringBuilder filename = new StringBuilder(player.getColor().toString().toLowerCase()+"_");
        initAnimal(player, 1, AnimalName.Mouse    , filename.toString()+"mouse"   , new Position(6,6));
        initAnimal(player, 2, AnimalName.Cat      , filename.toString()+"cat"     , new Position(7,1));
        initAnimal(player, 3, AnimalName.Wolf     , filename.toString()+"wolf"    , new Position(6,2));
        initAnimal(player, 4, AnimalName.Dog      , filename.toString()+"dog"     , new Position(7,5));
        initAnimal(player, 5, AnimalName.Leopard  , filename.toString()+"leopard" , new Position(6,4));
        initAnimal(player, 6, AnimalName.Tiger    , filename.toString()+"tiger"   , new Position(8,0));
        initAnimal(player, 7, AnimalName.Lion     , filename.toString()+"lion"    , new Position(8,6));
        initAnimal(player, 8, AnimalName.Elephant , filename.toString()+"elephant", new Position(6,0));
    }

    /* Getters */
    /**
     * Gets entire Tile grid playBoard.
     * @return playBoard field
     */
    public Tile[][] getPlayBoard(){
        return playBoard;
    }

    /** 
     * Gets upper den tile.
     * @return upper Den tile
     */
    public DenTile getUpperDen(){
        return (DenTile) playBoard[0][3];
    }

    /**
     * Gets lower den tile.
     * @return lower Den tile
     */
    public DenTile getLowerDen(){
        return (DenTile) playBoard[8][3];
    }

    /**
     * Gets the player handler.
     * @return player handler reference
     */
    public PlayerHandler getPlayerHandler(){
        return playerHandler;
    }

    /* Methods */

    /**
     * sets new position of animal if new position is valid.
     * If there is an opposing animal in the new position, 
     * checks if the animal can be captured. Otherwise, retain position.
     * @param currAnimal - current animal to move
     * @param nextPos - position to check
     * @return true if successful, false if unsuccessful
     */
    public boolean moveAnimal(Animal currAnimal, Position nextPos){
        Tile newTile = searchTile(nextPos);
        Position newPos = nextPos;

        //if nextPos is a valid positon for currAnimal
        if ( isValidPosition(currAnimal, nextPos)){

            //if next tile is river and animal can jump, attempt to jump across
            if ( currAnimal.canJump() && newTile.isRiver() ){
                newPos = jumpRiver(currAnimal.getTile().getLocation(), nextPos);
                newTile = searchTile(newPos);

                //if jump is unsuccessful
                if (newPos.equals(nextPos) )
                    return false;
            }
            
            //if new tile is free
            if (newTile.getAnimal() == null){
                //remove animal from old tile
                currAnimal.getTile().setAnimal(null); 

                //place animal on new tile
                newTile.setAnimal(currAnimal);

                //place new tile on animal
                currAnimal.setTile(newTile);

                return true;
            }
            //checks if currAnimal can capture animal on new tile
            else if (newTile.getAnimal() != null &&
                currAnimal.capture(newTile.getAnimal()) ){
                    //remove animal from old tile
                    currAnimal.getTile().setAnimal(null); 

                    //get other animal to update player lists
                    Animal other = newTile.getAnimal();

                    //place animal on new tile
                    newTile.setAnimal(currAnimal);

                    //place new tile on animal
                    currAnimal.setTile(newTile);

                    //modify player lists
                    currAnimal.getFaction().addCapturedPieces(other);
                    other.getFaction().removePiece(other);
                    return true;
            }
        }
        //if move is unsuccessful
        return false;
    }

    /**
     * Searches for tile with matching position if position is valid.
     * @param pos - position of tile to search
     * @return reference to specific tile if found, null if not found
     */
    public Tile searchTile(Position pos){
        //if position exists in playBoard
        if (Position.isWithinBounds(pos))
            return playBoard[pos.getX()][pos.getY()];
        
        return null;
    }

    /**
     * Checks if pos is valid tile for currAnimal to move to.
     * @param currAnimal - current animal to check
     * @param pos - next position to check for validity
     * @return true if position is valid, false if not
     */
    public boolean isValidPosition(Animal currAnimal, Position nextPos){
        //if pos is within bounds,check for conditions that make move false
        if (Position.isWithinBounds(nextPos) ){
            Tile tempTile = searchTile(nextPos);
            //if animal is not a mouse/jumper and pos is a river tile
            if( !(currAnimal instanceof Mouse) && !(currAnimal.canJump()) 
            && tempTile.isRiver())
                return false;
            
            //if animal can jump and next tile is a river
            if (currAnimal.canJump() && tempTile.isRiver()){
                //check across river with newpos
                Position newPos = jumpRiver(currAnimal.getTile().getLocation(), nextPos);
                //if newpos == nextpos (jump can't occur), return false
                if (nextPos.equals(newPos))
                    return false;
                
                //if jump succeeded but animal on other tile cannot be captured, return false
                else if (!currAnimal.capture(searchTile(newPos).getAnimal()))
                    return false;
            }

            //if next tile can be claimed by currAnimal
            if(!currAnimal.capture(tempTile.getAnimal()))
                return false;

            //if pos is a den tile and animal is from same faction
            else if (tempTile instanceof DenTile){
                DenTile tempDen = (DenTile) tempTile;
                if (tempDen.getColor().equals(currAnimal.getFaction().getColor()) )
                    return false;
            }
            //if all checks are passed, return true
            return true;
        }
        //if nextPos is not within bounds, return false
        return false;
    }

    /**
     * performs jumping over a river, returning the new position to move to.
     * @param currPos - current animal's position
     * @param nextPos - next positon to move to
     * @return new position across the river tiles, next tile directly if not a river
     */
    public Position jumpRiver(Position currPos, Position nextPos){
        Position newPos = nextPos;

        //if animal is right of river
        if ( currPos.getY() > nextPos.getY() ){
            while(searchTile(newPos).isRiver() ){
                //if animal (mouse) is in river, exit
                if (searchTile(newPos).hasAnimal() || !Position.isWithinBounds(newPos))
                    return nextPos;

                newPos = new Position(newPos.getX(), newPos.getY()-1);
            }
        }
        //if animal is left of river
        else if ( currPos.getY() < nextPos.getY() ){
            while( searchTile(newPos).isRiver() ){
                if (searchTile(newPos).hasAnimal() || !Position.isWithinBounds(newPos))
                    return nextPos;

                newPos = new Position(newPos.getX(), newPos.getY()+1);
            }
            
        }

        //if animal is below river
        else if ( currPos.getX() > nextPos.getX() ){
            while( searchTile(newPos).isRiver() ){
                if (searchTile(newPos).hasAnimal() || !Position.isWithinBounds(newPos))
                    return nextPos;

                newPos = new Position(newPos.getX()-1, newPos.getY());
            }
            
        }

        //if animal is above river
        else if ( currPos.getX() < nextPos.getX() ){
            while( searchTile(newPos).isRiver() ){
                if (searchTile(newPos).hasAnimal() || !Position.isWithinBounds(newPos))
                    return nextPos;

                newPos = new Position(newPos.getX()+1, newPos.getY());
            }
        }
        return newPos;
    }

    /**
     * UNUSED IN GAME
     * checks traps if there is an opponent animal close to a den.
     * @return Tile of animal that triggered check
     * */
    public Tile checkTrap(){
        //traps near upper Den
        for(int i = 0; i <= 1; i++){
            for(int j = 2; j <= 4; j++){
                if(playBoard[i][j].hasAnimal()
                && playBoard[i][j].getAnimal().getFaction().getColor() != getUpperDen().getColor())
                    return playBoard[i][j];
            }
        }

        //traps near lower Den
        for(int i = 7; i <= 8; i++){
            for(int j = 2; j <= 4; j++){
                if(playBoard[i][j].hasAnimal()
                && playBoard[i][j].getAnimal().getFaction().getColor() != getLowerDen().getColor())
                    return playBoard[i][j];
            }
        }

        //no opponent animals close to den
        return null;
    }

    /**
     * Checks for winning moves/conditions.
     * @param nPlayer - Player to check for available pieces.
     */
    public boolean checkWinningMove(Player player){
        if(getUpperDen().hasAnimal()    //upper den has animal
        || getLowerDen().hasAnimal()    //lower den has animal
        || player.getPieces().isEmpty() ) //player is out of pieces
            return true;
        
        return false;
    }

    /**
     * resets the game
     */
    public void resetBoard(){
        //clear all tiles of animals
        for(int i = 0; i < GameBoard.ROW; i++)
            for(int j = 0; j < GameBoard.COL; j++)
                searchTile(new Position(i,j)).setAnimal(null);
        
        //reset players
        playerHandler.resetPlayers();

        //initialize animals
        initTopAnimals(playerHandler.getSecondPlayer());
        initBottomAnimals(playerHandler.getFirstPlayer());
    }
}
