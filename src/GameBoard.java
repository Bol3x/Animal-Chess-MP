package src;

import java.util.*;

public class GameBoard {
    public static final int ROW = 9;
    public static final int COL = 7;

    private boolean bGameWin = false;
    private Tile playBoard[][] = new Tile[ROW][COL];
    private Player players[] = new Player[2];


    /**
     * Initializes all tiles and pieces onto the board, 
     * and provides pieces ownership to their respective player
     * 
     * @version 1.2
     * @author Carlo
     * @author Shane
     */
    public void initPlayBoard(){
        
        //set players
        players[0] = new Player(Terrain.DEN1);
        players[1] = new Player(Terrain.DEN2);
        
        //set dens
        playBoard[0][3] = new Tile(new Position(0,3) , Terrain.DEN1);
        playBoard[8][3] = new Tile(new Position(ROW-1,3) , Terrain.DEN2);

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
        
          //set Player 1 Animals
         Animal mouse = new Animal(players[0], 1, "Mouse", new Position(2,0));
         playBoard[2][0].setAnimal(mouse);
         players[0].addPieces(mouse);
         
         Animal cat = new Animal(players[0], 2, "Cat", new Position(1,5));
         playBoard[1][5].setAnimal(cat);
         players[0].addPieces(cat);
         
         Animal wolf = new Animal(players[0], 3, "Wolf", new Position(2,4));
         playBoard[2][4].setAnimal(wolf);
         players[0].addPieces(wolf);
         
         Animal dog = new Animal(players[0], 4, "Dog", new Position(1,1));
         playBoard[1][1].setAnimal(dog);
         players[0].addPieces(dog);
         
         Animal leopard = new Animal(players[0], 5, "Leopard", new Position(2,2));
         playBoard[2][2].setAnimal(leopard);
         players[0].addPieces(leopard);
                         
         Animal tiger = new Animal(players[0], 6, "Tiger", new Position(0,6));
         playBoard[0][6].setAnimal(tiger); 
         players[0].addPieces(tiger);
         
         Animal lion = new Animal(players[0], 7, "Lion", new Position(0,0));
         playBoard[0][0].setAnimal(lion);
         players[0].addPieces(lion);
                                 
         Animal elephant = new Animal(players[0], 8, "Elephant", new Position(2,6));
         playBoard[2][6].setAnimal(elephant);
         players[0].addPieces(elephant);
         
         //set Player 2 Animals
         Animal mouse2 = new Animal(players[1], 1, "Mouse", new Position(6,6));
         playBoard[6][6].setAnimal(mouse2);
         players[1].addPieces(mouse2);
         
         Animal cat2 = new Animal(players[1], 2, "Cat", new Position(7,1));
         playBoard[7][1].setAnimal(cat2);
         players[1].addPieces(cat2);
         
         Animal wolf2 = new Animal(players[1], 3, "Wolf", new Position(6,2));
         playBoard[6][2].setAnimal(wolf2);
         players[1].addPieces(wolf2);
         
         Animal dog2 = new Animal(players[1], 4, "Dog", new Position(7,5));
         playBoard[7][5].setAnimal(dog2);
         players[1].addPieces(dog2);
         
         Animal leopard2 = new Animal(players[1], 5, "Leopard", new Position(6,4));
         playBoard[6][4].setAnimal(leopard2);
         players[1].addPieces(leopard2);
                         
         Animal tiger2 = new Animal(players[1], 6, "Tiger", new Position(8,0));
         playBoard[8][0].setAnimal(tiger2);
         players[1].addPieces(tiger2);
         
         Animal lion2 = new Animal(players[1], 7, "Lion", new Position(8,6));
         playBoard[8][6].setAnimal(lion2);
         players[1].addPieces(lion2);
                                 
         Animal elephant2 = new Animal(players[1], 8, "Elephant", new Position(6,0));
         playBoard[6][0].setAnimal(elephant2);
         players[1].addPieces(elephant2);
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

    public boolean checkGameState(){
        return (!bGameWin);
    }

    public Player getPlayer(int i){
        return players[i];
    }

    /**Methods*/

    /**
     * selects the animal to use
     * @param nPlayer - index of player to get piece from
     * @return animal to use
     * 
     * @author Carlo
     */
    public Animal selectAnimal(int nPlayer, Scanner kbIn){
        ArrayList<Animal> PieceList = players[nPlayer].getPieces();
        int nInput = -1;
        do{
            for(int i = 0; i < players[nPlayer].getPieces().size(); i++)
                System.out.println((i+1) + ": " + PieceList.get(i).getSpecies() );

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
     * @param - currAnimal current animal to move
     * @param - newPos new position to check
     * @return true if successful, false if unsuccessful
     * 
     * @author Carlo
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
        return false;
    }

    /**
     * Checks if currAnimal can capture otherAnimal if not null
     * 
     * @param currAnimal - current animal that will capture
     * @param otherAnimal - other animal to be captured
     * @return true if captured/empty, false if not
     * 
     * @version 1.0
     * @author Carlo
    */
    public boolean captureAnimal(Animal currAnimal, Animal otherAnimal){
        //if other animal exists in tile
        if(otherAnimal != null){
            //if animals are opposing factions and currAnimal is higher rank, capture otherAnimal
            if(currAnimal.isOpposingFaction(otherAnimal) 
            && currAnimal.isHigherOrEqualRank(otherAnimal)){
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
     * Searches for tile with matching position if position is valid
     * @param pos - position of tile to search
     * @return reference to specific tile if found, null if not found
     * 
     * @author Carlo
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
     * Checks if pos is valid tile for currAnimal to move to
     * @param currAnimal - current animal to check
     * @param pos - next position to check for validity
     * @return true if position is valid, false if not
     * 
     * @author Carlo
     */
    public boolean isValidPosition(Animal currAnimal, Position pos){
        //if pos is within bounds
        if (Position.isWithinBounds(pos) ){
            //if animal is not a mouse/jumper and pos is a river tile
            if( (!Animal.isMouse(currAnimal) && !Animal.canJump(currAnimal) ) 
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
            while( searchTile(newPos).isRiver() )
                newPos = new Position(newPos.getX(), newPos.getY()-1);
            
        }
        //if animal is left of river
        else if ( currPos.getY() < nextPos.getY() ){
            while( searchTile(newPos).isRiver() )
                newPos = new Position(newPos.getX(), newPos.getY()+1);
            
        }

        //if animal is below river
        else if ( currPos.getX() > nextPos.getX() ){
            while( searchTile(newPos).isRiver() )
                newPos = new Position(newPos.getX()-1, newPos.getY());
            
        }

        //if animal is above river
        else if ( currPos.getX() < nextPos.getX() ){
            while( searchTile(newPos).isRiver() )
                newPos = new Position(newPos.getX()+1, newPos.getY());
        }
        return newPos;
    }

    /**
     * checks traps if there is an opponent animal close to a den.
     * @return Tile of animal that triggered check
     * 
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
