package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PlayerSelectPanel extends JPanel{
    private JPanel topPanel;
    private JPanel midPanel;
    private JPanel botPanel;
    private JLabel lblResult;

    private JButton[] buttonSelect;
    private JButton btnNextPanel;

    int[] playerChoices;

    public PlayerSelectPanel(){
        setLayout(new BorderLayout());

        playerChoices = new int[2];

        topPanel = new JPanel(new FlowLayout());
        JLabel lblInstructions = new JLabel("Select a number below:");
        topPanel.add(lblInstructions);

        this.add(topPanel, BorderLayout.NORTH);
        midPanel = new JPanel(new GridLayout(2,4));
        
        buttonSelect = new JButton[8];

        for(int i = 0; i < 8; i++){
            buttonSelect[i] = new JButton("" + (i+1));

            midPanel.add(buttonSelect[i]);
        }

        this.add(midPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        botPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        lblResult = new JLabel("test");
        botPanel.add(lblResult, gbc);

        btnNextPanel = new JButton("Next");
        btnNextPanel.setActionCommand(GUI.GAME_PANEL);
        btnNextPanel.setEnabled(false);
        gbc.gridx = 0;
        gbc.gridy = 1;
        botPanel.add(btnNextPanel, gbc);

        this.add(botPanel, BorderLayout.SOUTH);
    }

    public void setResult(String str){
        lblResult.setText(str);
    }

    public void setChoiceButtonListener(ActionListener listener){
        for(int i = 0; i < 8; i++){
            buttonSelect[i].addActionListener(listener);
        }
    }

    public void setNextButtonListener(ActionListener listener){        
        btnNextPanel.addActionListener(listener);
    }

    public void disableChoiceButtons(){
        for(int i = 0; i < 8; i++){
            buttonSelect[i].setEnabled(false);
        }
    }

     public void disableChoiceButton(int i){
          buttonSelect[i].setEnabled(false);
    }
     
    public void enableNextButton(){
        btnNextPanel.setEnabled(true);
    }
}
