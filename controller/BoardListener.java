package controller;

import java.awt.event.*;

import src.*;
import view.GamePanel;
import view.GameStats;
import view.TileDisplay;

public class BoardListener implements ActionListener{

    private GameBoard model;
    private Tile[][] modelBoard;
    private GamePanel view;
    private GameStats gameStats;
    private PlayerHandler pHandler;


    private int nTurns = 0;
    private int nCurrPlayer;
    private boolean bGameWin = false;

    public BoardListener(GamePanel panel, GameBoard gameBoard){
        //set gameboard as model
        model = gameBoard;

        //get player handler from model for simpler player access
        pHandler = model.getPlayerHandler();

        //set initial current player index to obtain from pHandler
        nCurrPlayer = nTurns - 1 + pHandler.getFirstPlayerIdx();

        //get gameboard from model for simpler board access 
        modelBoard = model.getPlayBoard();

        //set GUI panel of game
        view = panel;

        //set ActionListener for board pieces
        view.setBoardListener(this);

        //initialize game stats frame
        gameStats = new GameStats();
        gameStats.initPlayerLabels(pHandler);

        //apply model to view
        view.setPlayerLabels(pHandler);

        updateBoard();
    }

    //if button is selected
    TileDisplay currentSrc = null;
    
    @Override
    public void actionPerformed(ActionEvent e){

        //get current player
        Player currPlayer = pHandler.getPlayers()[nCurrPlayer];

        //if no animal is selected, 
        if(currentSrc == null){
            //store in currentSrc
            currentSrc = (TileDisplay) e.getSource();
            //display available moves
            view.displayMove(model, currentSrc);
        }

        //if animal is selected and pressed again, return to pre-select state
        else if (currentSrc.equals(e.getSource())){
            //remove display for moves
            view.undisplayMove(currentSrc.getPosition());
            //remove store
            currentSrc = null;
            //redisplay options
            view.enablePlayerPieces(modelBoard, pHandler.getPlayers()[nCurrPlayer]);
            view.highlightTerrain(pHandler);
        }

        else{
            //get current position of animal selected
            Position currPos = currentSrc.getPosition();
            //remove move display
            view.undisplayMove(currPos);

            //get selected move's position
            Position nextPos = ((TileDisplay) e.getSource()).getPosition();
            //move animal in model
            model.moveAnimal(model.searchTile(currPos).getAnimal(), nextPos);
            //remove selected animal tile from storage
            currentSrc = null;

            //update board
            updateBoard();
            
            //check game win conditions
            bGameWin = model.checkWinningMove(currPlayer);
        }

        //if game is won, disable board and display new play again frame
        if(bGameWin){
            view.disableBoard();
        }
    }

    /**
     * Updates the board, gameStats frame and turn count for next player
     */
    public void updateBoard(){
        nTurns++;
        gameStats.updateStats(pHandler);
        
        nCurrPlayer = (nTurns - 1 + pHandler.getFirstPlayerIdx()) % 2;

        Player currPlayer = pHandler.getPlayers()[nCurrPlayer];
        gameStats.updateTurnDisplay(nTurns, currPlayer.getColor().toString());
        view.setTurnLabel(currPlayer.getColor().toString());

        view.enablePlayerPieces(modelBoard, pHandler.getPlayers()[nCurrPlayer]);
        view.highlightTerrain(pHandler);
        view.displayTiles(modelBoard);
    }
}
