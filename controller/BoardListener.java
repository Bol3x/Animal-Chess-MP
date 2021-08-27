package controller;

import java.awt.event.*;

import src.*;
import view.GamePanel;
import view.GameStats;

public class BoardListener implements ActionListener{
    private GameBoard model;
    private Tile[][] modelBoard;
    private GamePanel view;
    private GameStats viewStats;
    private PlayerHandler pHandler;

    public BoardListener(GamePanel panel, GameBoard gameBoard){
        model = gameBoard;
        pHandler = model.getPlayerHandler();
        modelBoard = model.getPlayBoard();
        view = panel;
        //viewStats = view.getGameStats();
        view.setBoardListener(this);

        //visualize board
        view.highlightTerrain(pHandler);
        updateBoard();
    }

    @Override
    public void actionPerformed(ActionEvent e){

    }

    public void updateBoard(){
        view.enablePlayerPieces(modelBoard, pHandler.getFirstPlayer());
        view.displayTiles(modelBoard);
    }
}
