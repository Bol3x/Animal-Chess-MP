package driver;

import java.awt.event.*;

import view.ColorPanel;

public class ColorListener implements ActionListener {
     int nChoice;
     int nCount;
     ColorPanel view;
    
    public ColorListener(ColorPanel menuPanel){
        nCount = 0;
        nChoice = 0;
        view = menuPanel;
        view.setColorButtonListener(this);
    }
    
    public void actionPerformed(ActionEvent e){
         nChoice = Integer.parseInt(e.getActionCommand());
         nCount++;
        
         switch(nChoice) 
         {
             case 1:  view.setResult("Player X will be Red and Player Y will be Blue");
                           break;
             
             case 2:  view.setResult("Player X will be Blue and Player Y will be Red");
                           break;
         }
      
        if(nCount == 1){
              view.disableColorButtons();
              view.enableNextButton();      
        }
    }
}
