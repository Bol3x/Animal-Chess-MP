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
    private GameStats viewStats;
    private PlayerHandler pHandler;


    private int nTurns = 0;
    private int nCurrPlayer;
    private boolean bGameWin = false;

    public BoardListener(GamePanel panel, GameBoard gameBoard){
        model = gameBoard;
        pHandler = model.getPlayerHandler();
        nCurrPlayer = nTurns - 1 + pHandler.getFirstPlayerIdx();
        modelBoard = model.getPlayBoard();
        view = panel;
        //viewStats = view.getGameStats();
        view.setBoardListener(this);

        //visualize board
        view.setPlayerLabels(pHandler);

        view.highlightTerrain(pHandler);
        updateBoard();
    }


    //TODO FIND SOLN FOR MOVEMENT

    //if button is selected
    TileDisplay currentSrc = null;
    
    @Override
    public void actionPerformed(ActionEvent e){
        Player currPlayer = pHandler.getPlayers()[nCurrPlayer];
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
            Position selectPos = currentSrc.getPosition();
            Position nextPos = ((TileDisplay) e.getSource()).getPosition();
            view.undisplayMove(selectPos);
            model.moveAnimal(model.searchTile(selectPos).getAnimal(), nextPos);
            currentSrc = null;

            bGameWin = model.checkWinningMove(currPlayer);
            if (!bGameWin)
                updateBoard();
        }

        if(bGameWin){
            view.disableBoard();
            System.out.println("Player " + currPlayer.getColor() + " Wins!");
        }
    }

    /**
     * WIP - Updates the view's elements and enables respective player's 
     */
    public void updateBoard(){
        nTurns++;
        nCurrPlayer = (nTurns - 1 + pHandler.getFirstPlayerIdx()) % 2;
        view.setTurnLabel(pHandler.getPlayers()[nCurrPlayer].getColor().toString());
        view.enablePlayerPieces(modelBoard, pHandler.getPlayers()[nCurrPlayer]);
        view.displayTiles(modelBoard);
    }


    public void revertTurn(){
        nTurns--;
        nCurrPlayer = (nTurns - 1 + pHandler.getFirstPlayerIdx()) % 2;
    }
}
