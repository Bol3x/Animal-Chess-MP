package src.view;

import javax.swing.*;

import src.model.Player;
import src.model.PlayerHandler;
import src.model.Enums.AvailableColor;

import java.awt.*;
import java.awt.event.*;

/**
 * Dialog box of player win.
 * <p>
 * Displays score of players and options post-game.
 */
public class WinDialog extends JDialog{

    JLabel lblWinner;
    JLabel lblScore;
    JButton playAgainBtn;
    JButton exitBtn;

    public WinDialog(){
        super();
        setSize(400, 200);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //winner label
        lblWinner = new JLabel("Player x won!");
        lblWinner.setFont(lblWinner.getFont().deriveFont(20f));
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.insets.top = 15;
        gbc.insets.bottom = 40;
        gbc.gridwidth = 3;
        this.add(lblWinner, gbc);

        //score label
        lblScore = new JLabel("RED : 0    BLUE: 0");
        gbc.gridy = 1;
        gbc.insets.top = 0;
        gbc.insets.bottom = 10;
        gbc.insets.left = 10;
        gbc.insets.right = 20;
        this.add(lblScore, gbc);

        //play again button
        playAgainBtn = new JButton("Play Again.");
        playAgainBtn.setActionCommand(GUI.RESET_GAME);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipadx = 20;
        gbc.gridwidth = 1;
        gbc.insets.bottom = 0;
        gbc.insets.left = 20;
        gbc.insets.right = 30;
        this.add(playAgainBtn, gbc);

        //exit to menu button
        exitBtn = new JButton("Exit to Menu.");
        exitBtn.setActionCommand(GUI.MENU_PANEL);
        gbc.gridx = 2;
        gbc.insets.left = 30;
        gbc.insets.right = 20;
        this.add(exitBtn, gbc);

        //settings
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }

    public void setActionListeners(ActionListener listener){
        playAgainBtn.addActionListener(listener);
        exitBtn.addActionListener(listener);
    }

    public void setWinner(AvailableColor color){
        lblWinner.setText("Player " + color + " Won!");
    }

    public void setScores(PlayerHandler pHandler){
        Player firstPlayer = pHandler.getFirstPlayer();
        Player secondPlayer = pHandler.getSecondPlayer();
        
        lblScore.setText(firstPlayer + ": " + firstPlayer.getScore() + 
        "    " + secondPlayer.getColor() + ": " + secondPlayer.getScore());
    }
}