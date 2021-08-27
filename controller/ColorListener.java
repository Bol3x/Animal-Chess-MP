package controller;

import java.awt.event.*;

import src.PlayerHandler;
import src.Enums.AvailableColor;
import view.ColorPanel;

public class ColorListener implements ActionListener {
    private ColorPanel view;
    private PlayerHandler pHandler;
    
    public ColorListener(ColorPanel colorPanel, PlayerHandler handler){
        view = colorPanel;
        pHandler = handler;
        view.setColorButtonListener(this);
        view.setLabel1Visibility(false);
        view.setLabel2Visibility(false);
        view.setSelector("Player " + (pHandler.getFirstPlayerIdx()+1) + ", Select a color below:");
    }
    
    public void actionPerformed(ActionEvent e){
        int nChoice = Integer.parseInt(e.getActionCommand());
        /*disable individual button
        JButton btn = (JButton)e.getSource();

        btn.setEnabled(false);
        */
        
        switch(nChoice) 
        {
            case 1: view.setLabel1Result("Player " + (pHandler.getFirstPlayerIdx()+1) + " will be Red and Player " + (pHandler.getSecondPlayerIdx()+1) + " will be Blue");
                view.setLabel2Result("Player " + (pHandler.getFirstPlayerIdx() + 1) + " chose Red");
                view.setLabel2ColorRed();
                view.setLabel1Visibility(true);
                view.setLabel2Visibility(true);
                pHandler.initFirstPlayerColor(AvailableColor.RED);
                pHandler.initSecondPlayerColor(AvailableColor.BLUE);
            break;
             
            case 2: view.setLabel1Result("Player " + (pHandler.getFirstPlayerIdx()+1) + " will be Blue and Player " + (pHandler.getSecondPlayerIdx()+1) + " will be Red");
                view.setLabel2Result("Player " + (pHandler.getFirstPlayerIdx() + 1) + " chose Blue");
                  view.setLabel2ColorBlue();
                view.setLabel1Visibility(true);
                view.setLabel2Visibility(true);   
                pHandler.initFirstPlayerColor(AvailableColor.BLUE);
                pHandler.initSecondPlayerColor(AvailableColor.RED);
            break;
        }
        
        view.disableColorButtons();
        view.enableNextButton();
    }
}
