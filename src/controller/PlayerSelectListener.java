package src.controller;

import java.awt.event.*;

import src.model.PlayerHandler;
import src.model.Animals.Animal;
import src.view.menu_panels.PlayerPanel;

/**
 * Actionlistener for the Player Panel and initializing <code>PlayerHandler</code>.
 */
public class PlayerSelectListener implements ActionListener{
    private int[] nChoices;
    private int nCurrent;
    private PlayerPanel view;
    private PlayerHandler pHandler;
    
    public PlayerSelectListener(PlayerPanel menuPanel){
        nChoices = new int[2];
        nCurrent = 0;
        view = menuPanel;
        view.setLabel2Visibility(false);
        view.setLabel3Visibility(false);
        view.setChoiceButtonListener(this);
    }

    public void actionPerformed(ActionEvent e){
         nChoices[nCurrent] = Integer.parseInt(e.getActionCommand());
       
        
        view.setLabel2Result("Player " + (nCurrent+1) + " chose number "  + nChoices[nCurrent]);
        view.setLabel2Visibility(true);
        nCurrent++;
       
        if(nCurrent == 2){
              view.disableChoiceButtons();
              view.enableNextButton();

              pHandler = new PlayerHandler(nChoices);
              Animal[] choices = pHandler.getAnimalChoices();
              view.setLabel2Result("Player 1 chose " + choices[0].getSpecies() + " Rank: " + choices[0].getRank() 
              + ", Player 2 chose " + choices[1].getSpecies() + " Rank: " + choices[1].getRank());
              
              if(pHandler.getFirstPlayerIdx() == 0){
                  view.setLabel3Result("Player 1 goes first!");
                   view.setLabel3Visibility(true);
              }
              else{
                  view.setLabel3Result("Player 2 goes first!");
                   view.setLabel3Visibility(true);
              }
        }
    }

    public PlayerHandler getPlayerHandler(){
        return pHandler;
    }
}
