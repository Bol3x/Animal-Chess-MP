package controller;

import java.awt.event.*;

import src.*;

import view.GUI;

public class Controller implements ActionListener{
    private GUI view;
    private GameBoard model;
    private PlayerHandler pHandler;
    private PlayerSelectListener startListener;
    private ColorListener colorListener;
    private BoardListener boardListener;

    public Controller(){
        view = new GUI();
        startListener = new PlayerSelectListener(view.getPlayerSelectPanel());
        
        view.setActionListener(this);
    }

    /**
     * main game "portal" to access other panels in the GUI.
     */
    public void actionPerformed(ActionEvent e){
        switch(e.getActionCommand()){

            case GUI.PLAYER_SELECT_PANEL : view.showPlayerSelectPanel();
            break;

            case GUI.COLOR_PANEL : pHandler = startListener.getPlayerHandler();
                    colorListener = new ColorListener(view.getColorPanel(), pHandler);
                    view.showColorPanel();
            break;

            case GUI.GAME_PANEL : model = new GameBoard(pHandler);
                    boardListener = new BoardListener(view.getGamePanel(), model);
                    view.showGamePanel();
            break;

            case GUI.MENU_PANEL : view.showMenuPanel();
            break;

            case GUI.EXIT_FRAME : System.exit(0);
        }
    }

    public static void main(String[] args){
        new Controller();
    }
}
