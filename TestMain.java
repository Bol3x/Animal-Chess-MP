import src.*;

import java.util.*;

public class TestMain{
    public static void main(String[] args){
        
        int nTurn = 0;  //
        String Choice;
        boolean bForceExit = false;
        GameBoard board = new GameBoard();
        Scanner kbIn = new Scanner(System.in);
        Animal dummy;
        
        while (board.checkGameState() && !bForceExit) {
            displayTurn(nTurn % 2);
            displayBoard(board);
            
            dummy = board.selectAnimal(nTurn % 2, kbIn);
            
            System.out.println("What position will the piece move? : ");
            Choice = kbIn.next();
            switch (Choice) {
                        case "w" -> {
                            board.moveAnimal(dummy, new Position(dummy.getPosition().getX() - 1, dummy.getPosition().getY() ) );
                            nTurn++;
                        }
                        case "a" -> { 
                            board.moveAnimal(dummy, new Position(dummy.getPosition().getX(), dummy.getPosition().getY() - 1 ) );
                             nTurn++;
                        }     
                        case "s" -> {
                            board.moveAnimal(dummy, new Position(dummy.getPosition().getX() + 1, dummy.getPosition().getY() ) );
                            nTurn++;
                        }    
                        case "d" -> {
                            board.moveAnimal(dummy, new Position(dummy.getPosition().getX(), dummy.getPosition().getY() + 1 ) );
                            nTurn++;
                        }
                     }
        }
        kbIn.close();
    }
    
    public static void displayTurn(int nTurn) {
     System.out.println("Player " + nTurn + "  turn");
    }
    
    public static void displayAnimals(Player currPlayer){
        for(int i = 0; i < currPlayer.getPieces().size(); i++)
            System.out.println( (i+1) + ": " + currPlayer.getPieces().get(i));
    }
    
    /**
     * displays gameboard
     * @param board
     */
    public static void displayBoard(GameBoard board){
        for(int i = 0; i < GameBoard.ROW; i++){
            System.out.print("|");

            for(int j = 0; j < GameBoard.COL; j++){

                if (board.getTile(i, j).isTrap())
                    System.out.print("T");
                
                else if (board.getTile(i, j).hasAnimal()){
                    switch (board.getTile(i, j).getAnimal().getRank()) {
                        case 1 -> System.out.print("Mo");
                        case 2 -> System.out.print("Ca");
                        case 3 -> System.out.print("Wo");
                        case 4 -> System.out.print("Do");
                        case 5 -> System.out.print("Le");
                        case 6 -> System.out.print("Ti");
                        case 7 -> System.out.print("Li");
                        case 8 -> System.out.print("El");
                    }
                }
                
                else if (board.getTile(i, j).isRiver())
                    System.out.print("R");

                else if (board.getTile(i, j).isDen())
                    System.out.print("D");
                
                else
                    System.out.print(" ");

                System.out.print("|");
            }
            System.out.println("\n---------------");
        }
    }
 }