import src.*;
import java.util.*;

public class TestMain{
    public static void main(String[] args){

        int nTurn = 0, nPlayer = nTurn;
        boolean bForceExit = false;
        GameBoard board = new GameBoard();
        Scanner kbIn = new Scanner(System.in);

        displayBoard(board);

        while (board.checkGameState() && !bForceExit) {
            nPlayer = nTurn % 2;
            boolean bMoved = false;
            displayTurn(nPlayer);

            Animal dummy = board.selectAnimal(nPlayer, kbIn);

            System.out.println("What position will the piece move? \n(W: up)\n(S: down)\n(A: left)\n(D: right): ");
            String Choice = kbIn.next();
            switch (Choice) {
                        case "w", "W" -> bMoved = board.moveAnimal(dummy, new Position(dummy.getPosition().getX() - 1, dummy.getPosition().getY() ) );
                        case "a", "A" -> bMoved = board.moveAnimal(dummy, new Position(dummy.getPosition().getX(), dummy.getPosition().getY() - 1 ) );
                        case "s", "S" -> bMoved = board.moveAnimal(dummy, new Position(dummy.getPosition().getX() + 1, dummy.getPosition().getY() ) );
                        case "d", "D" -> bMoved = board.moveAnimal(dummy, new Position(dummy.getPosition().getX(), dummy.getPosition().getY() + 1 ) );
                     }
            if (!bMoved)
                System.out.println("Invalid Move!");

         displayBoard(board);
         board.checkWinningMove(nPlayer);
            if (bMoved && board.checkGameState())
                nTurn++;
        }

        if(nPlayer != 0)
            System.out.println("Player 1 Won!");
        else
           System.out.println("Player 2 Won!");

        kbIn.close();
    }

    /**
     * displays current player's turn.
     * @param nTurn - turn count
     */
    public static void displayTurn(int nTurn) {
     System.out.println("Player " + (nTurn+1) + "'s  turn");
    }

    /**
     * displays gameboard for all tiles,
     * including the objects within each tile (animal, terrain).
     * @param board
     */
    public static void displayBoard(GameBoard board){
        System.out.println("\n----------------------");
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
    }
 }