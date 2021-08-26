package driver;

import java.awt.event.*;

import view.PlayerPanel;
import src.PlayerHandler;

public class StartListener implements ActionListener{
    int[] nChoices;
    int nCurrent;
    PlayerPanel view;
    int i = 1;
    
    public StartListener(PlayerPanel menuPanel){
        nChoices = new int[2];
        nCurrent = 0;
        view = menuPanel;
        view.setChoiceButtonListener(this);
    }

    public void actionPerformed(ActionEvent e){
         nChoices[nCurrent] = Integer.parseInt(e.getActionCommand());
       
        
        view.setResult("Player " + i + " chose number "  + nChoices[nCurrent]);
        i++;
        nCurrent++;
       
        if(nCurrent == 2){
              view.disableChoiceButtons();
              view.enableNextButton();

              PlayerHandler pHandler = new PlayerHandler(nChoices);
              
              if(pHandler.getFirstPlayerIdx() == 0) 
                  view.setResult("Player 1 gets to pick colors first");
              else
                  view.setResult("Player 2 gets to pick colors first");
        }
    }

    public int[] getChoices(){
        return nChoices;
    }
}
