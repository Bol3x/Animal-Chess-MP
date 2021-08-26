package controller;

import java.awt.event.*;
import javax.swing.JButton;

import src.GameBoard;
import src.Player;
import src.PlayerHandler;
import src.Enums.AvailableColor;
import view.ColorPanel;
import view.GUI;

public class ColorListener implements ActionListener {
    private int nChoice;
    private ColorPanel view;
    private GUI gui;
    private PlayerHandler pHandler;

    
    public ColorListener(ColorPanel colorPanel, GUI gui){
        view = colorPanel;
        this.gui = gui;
        view.setColorButtonListener(this);
    }
    
    public void actionPerformed(ActionEvent e){
        nChoice = Integer.parseInt(e.getActionCommand());
        /*disable individual button
        JButton btn = (JButton)e.getSource();

        btn.setEnabled(false);
        */
        
        switch(nChoice) 
        {
            case 1: view.setResult("Player " + (pHandler.getFirstPlayerIdx()+1) + " will be Red and Player " + (pHandler.getSecondPlayerIdx()+1) + " will be Blue");
                pHandler.initFirstPlayerColor(AvailableColor.RED);
                pHandler.initSecondPlayerColor(AvailableColor.BLUE);
            break;
             
            case 2: view.setResult("Player " + (pHandler.getFirstPlayerIdx()+1) + " will be Blue and Player " + (pHandler.getSecondPlayerIdx()+1) + " will be Red");
                pHandler.initFirstPlayerColor(AvailableColor.BLUE);
                pHandler.initSecondPlayerColor(AvailableColor.RED);
            break;
        }

        gui.setGamePanel(new GameBoard(pHandler));
        view.disableColorButtons();
        view.enableNextButton();
    }

    public void setHandler(PlayerHandler pHandler){
        this.pHandler = pHandler;
    }
}
