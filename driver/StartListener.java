package driver;

import java.awt.event.*;

import view.PlayerSelectPanel;

public class StartListener implements ActionListener{
    int[] nChoices;
    int nCurrent;
    PlayerSelectPanel view;

    public StartListener(PlayerSelectPanel menuPanel){
        nChoices = new int[2];
        nCurrent = 0;
        view = menuPanel;
        view.setChoiceButtonListener(this);
    }

    public void actionPerformed(ActionEvent e){
        
        nChoices[nCurrent] = Integer.parseInt(e.getActionCommand());
        view.setResult("" + nChoices[nCurrent]);
        nCurrent++;

        if(nCurrent == 2){
            view.disableChoiceButtons();
            view.enableNextButton();
        }
    }
}
