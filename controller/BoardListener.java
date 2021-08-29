package controller;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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


    private int nTurns = 1;
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

        view.highlightTerrain(pHandler);
        updateBoard();
    }

    //if button is selected
    TileDisplay currentSrc = null;
    
    @Override
    public void actionPerformed(ActionEvent e){

        //get current player
        Player currPlayer = pHandler.getPlayers()[nCurrPlayer];

        //if no object is selected, 
        if(currentSrc == null){
            currentSrc = (TileDisplay) e.getSource();
            view.displayMove(model, currentSrc);
        }

        else if (currentSrc.equals(e.getSource())){
            view.undisplayMove(currentSrc.getPosition());
            currentSrc = null;
            view.enablePlayerPieces(modelBoard, pHandler.getPlayers()[nCurrPlayer]);
        }

        else{
            Position currPos = currentSrc.getPosition();
            view.undisplayMove(currPos);

            Position nextPos = ((TileDisplay) e.getSource()).getPosition();
            model.moveAnimal(model.searchTile(currPos).getAnimal(), nextPos);
            currentSrc = null;

            updateBoard();
            
            bGameWin = model.checkWinningMove(currPlayer);
        }

        if(bGameWin){
            view.disableBoard();
            System.out.println("Player " + currPlayer + " Wins!");
        }
    }

    /**
     * WIP - Updates the view's elements and enables respective player's 
     */
    public void updateBoard(){
        nTurns++;
        gameStats.updateStats(pHandler);
        
        nCurrPlayer = (nTurns - 1 + pHandler.getFirstPlayerIdx()) % 2;
        view.setTurnLabel(pHandler.getPlayers()[nCurrPlayer].getColor().toString());
        view.enablePlayerPieces(modelBoard, pHandler.getPlayers()[nCurrPlayer]);
        view.displayTiles(modelBoard);
    }
}
