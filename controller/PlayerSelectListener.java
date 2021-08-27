package controller;

import java.awt.event.*;

import view.PlayerPanel;
import src.PlayerHandler;
import src.Animals.Animal;

public class PlayerSelectListener implements ActionListener{
    private int[] nChoices;
    private int nCurrent;
    private PlayerPanel view;
    private PlayerHandler pHandler;
    
    public PlayerSelectListener(PlayerPanel menuPanel){
        nChoices = new int[2];
        nCurrent = 0;
        view = menuPanel;
        view.setChoiceButtonListener(this);
    }

    public void actionPerformed(ActionEvent e){
         nChoices[nCurrent] = Integer.parseInt(e.getActionCommand());
       
        
        view.setResult("Player " + (nCurrent+1) + " chose number "  + nChoices[nCurrent]);
        nCurrent++;
       
        if(nCurrent == 2){
              view.disableChoiceButtons();
              view.enableNextButton();

              pHandler = new PlayerHandler(nChoices);
              Animal[] choices = pHandler.getAnimalChoices();
              view.setResult("Player 1 chose " + choices[0].getSpecies() + " Rank: " + choices[0].getRank() 
              + ", Player 2 chose " + choices[1].getSpecies() + " Rank: " + choices[1].getRank());
              
              if(pHandler.getFirstPlayerIdx() == 0){
                  view.setResult("Player 1 goes first!");
              }
              else{
                  view.setResult("Player 2 goes first!");
              }
        }
    }

    public PlayerHandler getPlayerHandler(){
        return pHandler;
    }
}
