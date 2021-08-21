package driver;

import java.awt.event.*;

import src.GameBoard;
import view.GUI;

public class Controller implements ActionListener{
    GUI view;
    GameBoard model;
    StartListener startListener;

    public Controller(){
        view = new GUI();
        startListener = new StartListener(view.getPlayerSelectPanel());
        view.setActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        switch(e.getActionCommand()){
            case GUI.EXIT_FRAME -> System.exit(0);
            case GUI.PLAYER_SELECT_PANEL -> view.showPlayerSelectPanel();
            case GUI.GAME_PANEL -> view.showGamePanel();
        }
    }

    public static void main(String[] args){
        new Controller();
    }
}
