package src.controller;

import java.awt.event.*;

import src.model.*;
import src.view.*;

/**
 * ActionListener for GameBoard and GamePanel.
 * <p>
 * Acts as the auxillary controller for all game mechanics
 * between the model and view.
 */
public class BoardListener implements ActionListener{

    private GameBoard model;
    private Tile[][] modelBoard;
    private GamePanel view;
    private PlayerHandler pHandler;
    private WinDialog winDialog;

    private MainController pController;

    private int nTurns = 0;
    private int nCurrPlayer;
    private boolean bGameWin = false;

    public BoardListener(GamePanel panel, PlayerHandler playerHandler, MainController pCont){
        //get PlayerHandler reference
        pHandler = playerHandler;

        //initialize new gameboard
        model = new GameBoard(pHandler);

        //set initial current player index to obtain from pHandler
        nCurrPlayer = nTurns - 1 + pHandler.getFirstPlayerIdx();

        //get gameboard from model for simpler board access 
        modelBoard = model.getPlayBoard();

        //set GUI panel of game
        view = panel;

        //store parent controller for winFrame use
        pController = pCont;

        //set ActionListener for board pieces
        view.setBoardListener(this);

        //apply model to view
        view.setPlayerLabels(pHandler);

        updateBoard();
    }

    //if button is selected
    private TileDisplay currentSrc = null;
    
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
            bGameWin = model.checkWinningMove(pHandler.getPlayers()[nCurrPlayer]);
        }

        //if game is won, disable board and display new play again frame
        if(bGameWin){
            currPlayer.increaseScore();
            view.disableBoard();

            winDialog = new WinDialog();
            winDialog.setActionListeners(pController);
            winDialog.setWinner(currPlayer.getColor());
            winDialog.setScores(pHandler);
        }
    }

    /**
     * Updates the board, gameStats frame and turn count for next player
     */
    public void updateBoard(){
        nTurns++;
        
        nCurrPlayer = (nTurns - 1 + pHandler.getFirstPlayerIdx()) % 2;

        Player currPlayer = pHandler.getPlayers()[nCurrPlayer];
        view.setTurnLabel(nTurns, currPlayer.getColor().toString());

        view.enablePlayerPieces(modelBoard, pHandler.getPlayers()[nCurrPlayer]);
        view.highlightTerrain(pHandler);
        view.displayTiles(modelBoard);
    }

    public void disposeWinFrame(){
        winDialog.dispose();
    }

    public void resetGame(){
        winDialog.dispose();
        model.resetBoard();
        bGameWin = false;
        nTurns = 0;
        updateBoard();
    }
}
