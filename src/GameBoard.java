package src;

import java.util.*;
import src.Animals.*;
import src.Enums.*;

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
        //create temp storage 
        ArrayList<Animal> temp = new ArrayList<Animal>();
        
        //create temp pieces
        temp.add(new Animal(null, 1, AnimalName.Mouse   , null));
        temp.add(new Animal(null, 2, AnimalName.Cat     , null));
        temp.add(new Animal(null, 3, AnimalName.Wolf    , null));
        temp.add(new Animal(null, 4, AnimalName.Dog     , null));
        temp.add(new Animal(null, 5, AnimalName.Leopard , null));
        temp.add(new Animal(null, 6, AnimalName.Tiger   , null));
        temp.add(new Animal(null, 7, AnimalName.Lion    , null));
        temp.add(new Animal(null, 8, AnimalName.Elephant, null));

        //shuffle pieces
        Collections.shuffle(temp);

        //temp animals
        Animal[] tempAnimals = new Animal[2];
        
        //input
        int nInput;


        //player selects
        for(int i = 0; i < 2; i++){
            do{
            System.out.println("Player" + (i+1) + "\nSelect a number from 1-8: ");
            nInput = kbIn.nextInt(); kbIn.nextLine();

                if (nInput <= 0 || nInput > temp.size())
                    System.out.println("Invalid input!");
            }while (nInput <= 0 || nInput > temp.size());

            //store in temp animal and shuffle temp
            tempAnimals[i] = temp.get(nInput-1);
            Collections.shuffle(temp);
        }

        System.out.println("Player 1 Chose: " + tempAnimals[0].getSpecies() + ": Rank " + tempAnimals[0].getRank());
        System.out.println("Player 2 Chose: " + tempAnimals[1].getSpecies() + ": Rank " + tempAnimals[1].getRank());

        //determine first player turn
        boolean bHigher = tempAnimals[0].getRank() >= tempAnimals[1].getRank();
        if (bHigher){
            System.out.println("Player 1 goes first!");
            nFirstPlayer = 0;
        }
        else{ 
            System.out.println("Player 2 goes first!");
            nFirstPlayer = 1;
        } 
        
        ArrayList<Color> colorList = new ArrayList<Color>();
        for(int i = 0; i < Color.MAX_COLORS; i++){
            colorList.add(Color.getColor(i));
        }

        //assign color to each player
        for(int i = 0; i < 2; i++){
            do{
                for(int j = 0; j < colorList.size(); j++){
                    System.out.println((j+1) + ". " + colorList.get(j).toString());
                }
                System.out.println("What color will you choose?: ");
                nInput = kbIn.nextInt(); kbIn.nextLine();

                if (nInput <= 0 || nInput > colorList.size())
                    System.out.println("Invalid input!");
            }while(nInput <= 0 || nInput > colorList.size());

            Color color = colorList.get(nInput-1);
            colorList.remove(color);
            
            //assigns color
            if (nFirstPlayer == 0)
                if (i == 0)
                        players[0] = new Player(color);
                else    players[1] = new Player(color);

            else if (nFirstPlayer == 1)
                if (i == 0)
                        players[1] = new Player(color);
                else    players[0] = new Player(color);
            
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
        playBoard[0][3] = new DenTile(new Position(0,3) , Color.RED);
        playBoard[8][3] = new DenTile(new Position(8,3) , Color.BLUE);

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
        initTopAnimals(getSecondPlayer());
        initBottomAnimals(getFirstPlayer());
    }


    /**
     * initializes animals to the board and player fields
     * @param nPlayer - player owner
     * @param nRank - animal rank
     * @param strName - animal name
     * @param postion - initial tile position on board
     */
    private void initAnimal(Player player, int nRank, AnimalName species, Position position){
        switch(nRank){
            case 1: Mouse mouse = new Mouse(player, searchTile(position));
                    searchTile(position).setAnimal(mouse);
                    player.addPieces(mouse);
                    break;

            case 8: Elephant elephant = new Elephant(player, searchTile(position));
                    searchTile(position).setAnimal(elephant);
                    player.addPieces(elephant);
                    break;
            
            default:
                    Animal animal = new Animal(player, nRank, species, searchTile(position));
                    searchTile(position).setAnimal(animal);
                    player.addPieces(animal);
        }
    }

    /**
     * initializes animals in top area (top 3 rows).
     * @param nPlayer - player to assign animals to
     */
    private void initTopAnimals(int nPlayer){
        initAnimal(players[nPlayer], 1, AnimalName.Mouse    , new Position(2,0));
        initAnimal(players[nPlayer], 2, AnimalName.Cat      , new Position(1,5));
        initAnimal(players[nPlayer], 3, AnimalName.Wolf     , new Position(2,4));
        initAnimal(players[nPlayer], 4, AnimalName.Dog      , new Position(1,1));
        initAnimal(players[nPlayer], 5, AnimalName.Leopard  , new Position(2,2));
        initAnimal(players[nPlayer], 6, AnimalName.Tiger    , new Position(0,6));
        initAnimal(players[nPlayer], 7, AnimalName.Lion     , new Position(0,0));
        initAnimal(players[nPlayer], 8, AnimalName.Elephant , new Position(2,6));
    }

    /**
     * initializes animals in bottom area (bottom 3 rows).
     * @param nPlayer - player to assign animals to
     */
    private void initBottomAnimals(int nPlayer){
        initAnimal(players[nPlayer], 1, AnimalName.Mouse    , new Position(6,6));
        initAnimal(players[nPlayer], 2, AnimalName.Cat      , new Position(7,1));
        initAnimal(players[nPlayer], 3, AnimalName.Wolf     , new Position(6,2));
        initAnimal(players[nPlayer], 4, AnimalName.Dog      , new Position(7,5));
        initAnimal(players[nPlayer], 5, AnimalName.Leopard  , new Position(6,4));
        initAnimal(players[nPlayer], 6, AnimalName.Tiger    , new Position(8,0));
        initAnimal(players[nPlayer], 7, AnimalName.Lion     , new Position(8,6));
        initAnimal(players[nPlayer], 8, AnimalName.Elephant , new Position(6,0));
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
     * Gets tile specified by <code>x</code> and <code>y</code> parameters
     * @param X - row
     * @param Y - column
     * @return Tile of specified location
     */
    public Tile getTile(int X, int Y){
        return playBoard[X][Y];
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
     * Gets the index of the first player to move in <code>players[]</code>.
     * @return 1 or 0, based on result of <code>initPlayerColors()</code>
     */
    public int getFirstPlayer(){
        return nFirstPlayer;
    }

    /**
     * Gets the index of the second player to move in <code>players[]</code>.
     * @return 1 or 0, based on result of <code>initPlayerColors()</code>
     */
    public int getSecondPlayer(){
        return nFirstPlayer == 0 ? 1 : 0;
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

            //if next tile is river and animal can jump, attempt to jump across
            if ( currAnimal.canJump() && newTile.isRiver() ){
                newPos = jumpRiver(currAnimal.getTile().getLocation(), nextPos);
                newTile = searchTile(newPos);

                //if jump is unsuccessful
                if (newPos.equals(nextPos) )
                    return false;
            }
            
            //checks (if applicable) currAnimal can capture animal on new tile
            if (currAnimal.capture(newTile.getAnimal()) ){
                //remove animal from old tile
                currAnimal.getTile().setAnimal(null); 

                //place animal on new tile
                newTile.setAnimal(currAnimal);
                currAnimal.setTile(newTile);
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
            for(int i = 0; i < ROW; i++)
                for(int j = 0; j < COL; j++)
                    if (playBoard[i][j].equals(new Tile(pos)) )
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
            Tile tempTile = searchTile(pos);
            //if animal is not a mouse/jumper and pos is a river tile
            if( !(currAnimal instanceof Mouse) && !(currAnimal.canJump()) 
            && tempTile.isRiver())
                return false;
            
            //if pos is a den tile and animal is from same faction
            else if (tempTile instanceof DenTile){
                DenTile tempDen = (DenTile) tempTile;
                if (tempDen.getColor() == currAnimal.getFaction().getColor())
                    return false;
            }
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
    public void checkWinningMove(int nPlayer){
        if(playBoard[0][3].hasAnimal()  //player 1 den
        || playBoard[8][3].hasAnimal()  //player 2 den
        || players[nPlayer].getPieces().isEmpty() ) //player is out of pieces
            bGameWin = true;
    }
}
