package driver;

import src.*;
import src.Animals.Animal;
import src.Enums.Color;
import java.util.*;

/**
 * Test driver for testing the implementation of Classes in the <code>src</code> package.
 */
public class TestMain{
    public static void main(String[] args){

        boolean bForceExit = false;
        Scanner kbIn = new Scanner(System.in);
        GameBoard board = new GameBoard(kbIn);
        int nTurn = 0, nPlayer = nTurn;
     
        displayBoard(board);

        while (board.checkGameState() && !bForceExit) {
            nPlayer = (nTurn + board.getFirstPlayer()) % 2;
            boolean bMoved = false;
            displayTurn(board.getPlayer(nPlayer).getColor());

            Animal dummy = board.selectAnimal(nPlayer, kbIn);

            System.out.println("What position will the piece move? \n(W: up)\n(S: down)\n(A: left)\n(D: right): ");
            String Choice = kbIn.next();
            switch (Choice) {
                        case "w", "W" -> bMoved = board.moveAnimal(dummy, new Position(dummy.getTile().getLocation().getX() - 1, dummy.getTile().getLocation().getY() ) );
                        case "a", "A" -> bMoved = board.moveAnimal(dummy, new Position(dummy.getTile().getLocation().getX(), dummy.getTile().getLocation().getY() - 1 ) );
                        case "s", "S" -> bMoved = board.moveAnimal(dummy, new Position(dummy.getTile().getLocation().getX() + 1, dummy.getTile().getLocation().getY() ) );
                        case "d", "D" -> bMoved = board.moveAnimal(dummy, new Position(dummy.getTile().getLocation().getX(), dummy.getTile().getLocation().getY() + 1 ) );
                     }
            if (!bMoved)
                System.out.println("Invalid Move!");

         displayBoard(board);
         board.checkWinningMove((nPlayer+1) % 2);
            if (bMoved && board.checkGameState())
                nTurn++;
        }
        System.out.println("Player " + board.getPlayer(nPlayer).getColor().toString() + " Won!");

        kbIn.close();
    }

    /**
     * displays current player's turn.
     * @param color - current player's color
     */
    public static void displayTurn(Color color) {
     System.out.println(color.toString() + " Player's  turn");
    }

    /**
     * displays gameboard for all tiles,
     * including the objects within each tile (animal, terrain).
     * @param board - gameBoard to display
     */
    public static void displayBoard(GameBoard board){
        System.out.println("        " + board.getPlayer(board.getSecondPlayer()).getColor().toString());
        System.out.println("----------------------");
        for(int i = 0; i < GameBoard.ROW; i++){
            System.out.print("|");

            for(int j = 0; j < GameBoard.COL; j++){


                if (board.getTile(i, j).hasAnimal()){
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

                else if (board.getTile(i, j).isTrap())
                System.out.print("ww");

                else if (board.getTile(i, j).isRiver())
                    System.out.print("~~");

                else if (board.getTile(i, j).isDen())
                    System.out.print("D ");

                else
                    System.out.print("  ");

                System.out.print("|");
            }
            System.out.println("\n----------------------");
        }
        System.out.println("        " + board.getPlayer(board.getFirstPlayer()).getColor().toString());
    }
 }