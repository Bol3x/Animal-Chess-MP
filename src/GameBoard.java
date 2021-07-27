package src;

import java.util.*;

/**
 * Stores the main game board composed of <code>Tile</code> objects and controls movement of pieces within the board.
 * <p>
 * Handles initialization of gameplay features; Player initialization, animal move controls, and win conditions.
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

    private boolean bGameWin = false;
    private Tile playBoard[][] = new Tile[ROW][COL];
    private Player players[] = new Player[2];
    private int nFirstPlayer;

    /* Constructor */
    /**
     * Constructs a playBoard and initializes players and animals to designated locations.
     * @param kbIn - Keyboard user input for initializing players and turn order.
     */
    public GameBoard(Scanner kbIn){
        initPlayBoard(kbIn);
    }

    /**
     * Initializes order of turns and player colors
     * @param kbIn - keyboard input
     */
    private void initPlayerColors(Scanner kbIn){
        Player tempPlayer = new Player(null);
        
        tempPlayer.addPieces(new Animal(tempPlayer, 1, "Mouse", null));
        tempPlayer.addPieces(new Animal(tempPlayer, 2, "Cat", null));
        tempPlayer.addPieces(new Animal(tempPlayer, 3, "Wolf", null));
        tempPlayer.addPieces(new Animal(tempPlayer, 4, "Dog", null));
        tempPlayer.addPieces(new Animal(tempPlayer, 5, "Leopard", null));
        tempPlayer.addPieces(new Animal(tempPlayer, 6, "Tiger", null));
        tempPlayer.addPieces(new Animal(tempPlayer, 7, "Lion", null));
        tempPlayer.addPieces(new Animal(tempPlayer, 8, "Elephant", null));

        tempPlayer.shufflePieces();

        Animal tempAnimal1, tempAnimal2;
        int nInput;

        do{
        System.out.println("Player 1\nSelect a number from 1-8: ");
        nInput = kbIn.nextInt(); kbIn.nextLine();

            if (nInput <= 0 || nInput > tempPlayer.getPieces().size())
                System.out.println("Invalid input!");
        }while (nInput <= 0 || nInput > tempPlayer.getPieces().size());

        tempAnimal1 = tempPlayer.getPieces().get(nInput-1);
        tempPlayer.shufflePieces();
        do{
        System.out.println("Player 2\nSelect a number from 1-8: ");
        nInput = kbIn.nextInt(); kbIn.nextLine();

            if (nInput <= 0 || nInput > tempPlayer.getPieces().size())
                System.out.println("Invalid input!");
        }while (nInput <= 0 || nInput > tempPlayer.getPieces().size());

        tempAnimal2 = tempPlayer.getPieces().get(nInput-1);

        System.out.println("Player 1 Chose: " + tempAnimal1.getSpecies() + ": Rank " + tempAnimal1.getRank());
        System.out.println("Player 2 Chose: " + tempAnimal2.getSpecies() + ": Rank " + tempAnimal2.getRank());

        boolean bHigher = tempAnimal1.getRank() >= tempAnimal2.getRank();
        if (bHigher){
            System.out.println("Player 1 goes first!");
            nFirstPlayer = 0;
        }
        else{ 
            System.out.println("Player 2 goes first!");
            nFirstPlayer = 1;
        } 
        
        do{
            System.out.println("1. (Red: above)\n2. (Blue: Below)\nWhat color will you choose?: ");
            nInput = kbIn.nextInt(); kbIn.nextLine();

            if (nInput != 1 && nInput != 2)
                System.out.println("Invalid input!");
        }while(nInput != 1 && nInput != 2);

        if ( (nInput == 1 && bHigher) || (nInput == 2 && !bHigher) ){
            System.out.println("Player 1 will be RED\nPlayer 2 will be BLUE");
            players[0] = new Player(Terrain.DEN_RED);
            players[1] = new Player(Terrain.DEN_BLUE);
        }
        else{
            System.out.println("Player 1 will be BLUE\nPlayer 2 will be RED");
            players[0] = new Player(Terrain.DEN_BLUE);
            players[1] = new Player(Terrain.DEN_RED);
        }
    }

    /**
     * Initializes all tiles and pieces onto the board, 
     * and provides pieces ownership to their respective player
     * 
     * @version 1.3 - moved animal instantiates to seperate function
     */
    private void initPlayBoard(Scanner kbIn){
        
        //set players
        initPlayerColors(kbIn);
        
        //set dens
        playBoard[0][3] = new Tile(new Position(0,3) , Terrain.DEN_RED);
        playBoard[8][3] = new Tile(new Position(8,3) , Terrain.DEN_BLUE);

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
        if (players[0].getDen() == Terrain.DEN_RED){
            initRedAnimals(0);
            initBlueAnimals(1);
        }
        else if (players[0].getDen() == Terrain.DEN_BLUE){
            initBlueAnimals(0);
            initRedAnimals(1);
        }
    }


    /**
     * initializes animals to the board and player fields
     * @param nPlayer - player owner
     * @param nRank - animal rank
     * @param strName - animal name
     * @param postion - initial position on board
     */
    private void initAnimal(Player player, int nRank, String strName, Position position){
        Animal animal = new Animal(player, nRank, strName, position);
        searchTile(position).setAnimal(animal);
        player.addPieces(animal);
    }

    /**
     * initializes animals in blue area (bottom 3 rows).
     * @param nPlayer - player to assign animals to
     */
    private void initBlueAnimals(int nPlayer){
        initAnimal(players[nPlayer], 1, "Mouse"     , new Position(6,6));
        initAnimal(players[nPlayer], 2, "Cat"       , new Position(7,1));
        initAnimal(players[nPlayer], 3, "Wolf"      , new Position(6,2));
        initAnimal(players[nPlayer], 4, "Dog"       , new Position(7,5));
        initAnimal(players[nPlayer], 5, "Leopard"   , new Position(6,4));
        initAnimal(players[nPlayer], 6, "Tiger"     , new Position(8,0));
        initAnimal(players[nPlayer], 7, "Lion"      , new Position(8,6));
        initAnimal(players[nPlayer], 8, "Elephant"  , new Position(6,0));
    }

    /**
     * initializes animals in red area (top 3 rows).
     * @param nPlayer - player to assign animals to
     */
    private void initRedAnimals(int nPlayer){
        initAnimal(players[nPlayer], 1, "Mouse"     , new Position(2,0));
        initAnimal(players[nPlayer], 2, "Cat"       , new Position(1,5));
        initAnimal(players[nPlayer], 3, "Wolf"      , new Position(2,4));
        initAnimal(players[nPlayer], 4, "Dog"       , new Position(1,1));
        initAnimal(players[nPlayer], 5, "Leopard"   , new Position(2,2));
        initAnimal(players[nPlayer], 6, "Tiger"     , new Position(0,6));
        initAnimal(players[nPlayer], 7, "Lion"      , new Position(0,0));
        initAnimal(players[nPlayer], 8, "Elephant"  , new Position(2,6));
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
     * Gets specific tile by row-column index.
     * @param X - row
     * @param Y - column
     * @return Tile in playBoard
     */
    public Tile getTile(int X, int Y){
        return playBoard[X][Y];
    }

    /**
     * Checks if game is still running (No win condition is met)
     * @return false if win condition is met, true if game is still running
     */
    public boolean checkGameState(){
        return (!bGameWin);
    }

    /**
     * Gets player object by index.
     * @param i - index of specific player to access
     * @return Player object
     */
    public Player getPlayer(int i){
        return players[i];
    }

    /**
     * Gets the index of the first player to move.
     * @return 1 or 0, based on result of initPlayerColors
     */
    public int getFirstPlayer(){
        return nFirstPlayer;
    }

    /* Methods */

    /**
     * Selects the animal to use.
     * @param nPlayer - index of player to get piece from
     * @param kbIn - keyboard input scanner
     * @return animal object to use
     */
    public Animal selectAnimal(int nPlayer, Scanner kbIn){
        ArrayList<Animal> PieceList = players[nPlayer].getPieces();
        int nInput = -1;
        do{
            System.out.println("Available Pieces:");
            players[nPlayer].listPieces();

            System.out.print("Select an animal: "); 
            
            if (kbIn.hasNextInt()){
                nInput = kbIn.nextInt();
                kbIn.nextLine();
            }

            if (nInput <= 0 || nInput > players[nPlayer].getPieces().size() )
                System.out.println("Input does not exist!");
            
        } while(nInput <= 0 || nInput > players[nPlayer].getPieces().size() );

        return PieceList.get(nInput-1);

    }


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

            //jumps across river if animal can jump and next tile is river
            if ( Animal.canJump(currAnimal) && newTile.isRiver() ){
                newPos = jumpRiver(currAnimal.getPosition(), nextPos);
                newTile = searchTile(newPos);

                //if jump is unsuccessful
                if (newPos.equals(nextPos) )
                    return false;
            }
            
            //checks (if applicable) currAnimal can capture animal on new tile
            if (captureAnimal( currAnimal, newTile.getAnimal()) ){
                //remove animal from old tile
                Tile oldTile = searchTile( currAnimal.getPosition() );
                oldTile.setAnimal(null); 

                newTile.setAnimal(currAnimal);
                currAnimal.setPosition(newPos);
                return true;
            }
        }
        //if move is unsuccessful
        return false;
    }

    /**
     * Checks if currAnimal can capture otherAnimal if not null.
     * @param currAnimal - current animal that will capture
     * @param otherAnimal - other animal to be captured
     * @return true if captured/empty, false if not
     * 
     * @version 1.2 - added conditions for river-based captures (mice) and mice-elephant interaction
    */
    public boolean captureAnimal(Animal currAnimal, Animal otherAnimal){
        //if other animal exists in tile
        if(otherAnimal != null){
            /*
            if animals are opposing factions AND
            currAnimal is not an elephant while otherAnimal is a mouse OR
            currAnimal is higher rank OR 
            otherAnimal is in a trap OR
            currAnimal is a mouse and otherAnimal is an elephant And mouse is not trapped
            */
            if(currAnimal.isOpposingFaction(otherAnimal)
            && !(currAnimal.isElephant() && otherAnimal.isMouse())
            && (currAnimal.isHigherOrEqualRank(otherAnimal) 
                || searchTile(otherAnimal.getPosition()).isTrap() 
                || (currAnimal.isMouse() && !searchTile(currAnimal.getPosition()).isTrap() && otherAnimal.isElephant())))
            {
                //if one animal is in a river and the other is not(specially for mice)
                if( (searchTile(currAnimal.getPosition()).isRiver() && !searchTile(otherAnimal.getPosition()).isRiver())
                ||  (!searchTile(currAnimal.getPosition()).isRiver() && searchTile(otherAnimal.getPosition()).isRiver())
                )       return false;


                otherAnimal.setCapture(true);

                //update player stores
                currAnimal.getFaction().addCapturedPieces(otherAnimal);
                
                //update other player stores
                otherAnimal.getFaction().removePiece(otherAnimal);
                return true;
            }
            //else
            return false;
        }
        // if other is null/default
        return true;
    }

    /**
     * Searches for tile with matching position if position is valid.
     * @param pos - position of tile to search
     * @return reference to specific tile if found, null if not found
     */
    public Tile searchTile(Position pos){
        //if position exists in playBoard
        if (Position.isWithinBounds(pos))
            for(int i = 0; i < ROW; i++)
                for(int j = 0; j < COL; j++)
                    if (playBoard[i][j].getLocation().equals(pos) )
                        return playBoard[i][j];
        
        return null;
    }

    /**
     * Checks if pos is valid tile for currAnimal to move to.
     * @param currAnimal - current animal to check
     * @param pos - next position to check for validity
     * @return true if position is valid, false if not
     */
    public boolean isValidPosition(Animal currAnimal, Position pos){
        //if pos is within bounds
        if (Position.isWithinBounds(pos) ){
            //if animal is not a mouse/jumper and pos is a river tile
            if( !currAnimal.isMouse() && !Animal.canJump(currAnimal) 
            && searchTile(pos).isRiver())
                return false;
            
            //if pos is a den tile and animal is from same faction
            else if (searchTile(pos).getTerrain() == currAnimal.getFaction().getDen())
                return false;

            return true;
        }
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
                if (searchTile(newPos).hasAnimal() )
                    return nextPos;

                newPos = new Position(newPos.getX(), newPos.getY()-1);
            }
        }
        //if animal is left of river
        else if ( currPos.getY() < nextPos.getY() ){
            while( searchTile(newPos).isRiver() ){
                if (searchTile(newPos).hasAnimal() )
                    return nextPos;

                newPos = new Position(newPos.getX(), newPos.getY()+1);
            }
            
        }

        //if animal is below river
        else if ( currPos.getX() > nextPos.getX() ){
            while( searchTile(newPos).isRiver() ){
                if (searchTile(newPos).hasAnimal() )
                    return nextPos;

                newPos = new Position(newPos.getX()-1, newPos.getY());
            }
            
        }

        //if animal is above river
        else if ( currPos.getX() < nextPos.getX() ){
            while( searchTile(newPos).isRiver() ){
                if (searchTile(newPos).hasAnimal() )
                    return nextPos;

                newPos = new Position(newPos.getX()+1, newPos.getY());
            }
        }
        return newPos;
    }

    /**
     * checks traps if there is an opponent animal close to a den.
     * @return Tile of animal that triggered check
     * */
    public Tile checkTrap(){
        //traps near DEN1
        for(int i = 0; i <= 1; i++){
            for(int j = 2; j <= 4; j++){
                if(playBoard[i][j].hasAnimal()
                && playBoard[i][j].getAnimal().getFaction().getDen() != Terrain.DEN_RED)
                    return playBoard[i][j];
            }
        }

        //traps near DEN2
        for(int i = 7; i <= 8; i++){
            for(int j = 2; j <= 4; j++){
                if(playBoard[i][j].hasAnimal()
                && playBoard[i][j].getAnimal().getFaction().getDen() != Terrain.DEN_BLUE)
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
    public void checkWinningMove(int nPlayer){
        if(playBoard[0][3].hasAnimal()  //player 1 den
        || playBoard[8][3].hasAnimal()  //player 2 den
        || players[nPlayer].getPieces().isEmpty() ) //player is out of pieces
            bGameWin = true;
    }
}
