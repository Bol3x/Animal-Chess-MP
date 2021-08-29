package view;

import javax.swing.*;

import src.PlayerHandler;

import java.awt.*;

public class GameStats extends JFrame{
    private JPanel player1Panel;
    private JLabel player1;
    private JPanel player1Animals;
    private JLabel player1AnimalCount;

    private JPanel player2Panel;
    private JLabel player2;
    private JPanel player2Animals;
    private JLabel player2AnimalCount;

    public GameStats(){
        super("Stats");
        this.setSize(450, 250);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        player2Panel = new JPanel();

        player2Panel.setLayout(new BoxLayout(player2Panel, BoxLayout.Y_AXIS));
        player2 = new JLabel("Player B");
        player2Panel.add(player2);

        JLabel lblPlayer2Animal = new JLabel("Animals:");
        player2Panel.add(lblPlayer2Animal);

        player2Animals = new JPanel();
        player2Panel.add(player2Animals);

        player2AnimalCount = new JLabel();
        player2Animals.add(player2AnimalCount);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets.bottom = 55;
        this.add(player2Panel, gbc);

        player1Panel = new JPanel();

        player1Panel.setLayout(new BoxLayout(player1Panel, BoxLayout.Y_AXIS));
        player1 = new JLabel("Player A");
        player1Panel.add(player1);

        JLabel lblPlayer1Animal = new JLabel("Animals:");
        player1Panel.add(lblPlayer1Animal);

        player1Animals = new JPanel();
        player1Panel.add(player1Animals);

        player1AnimalCount = new JLabel();
        player1Animals.add(player1AnimalCount);
    
        gbc.gridy = 1;
        this.add(player1Panel, gbc);


        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    public void initPlayerLabels(PlayerHandler pHandler){
        player1.setText("Player " + pHandler.getFirstPlayer());
        player2.setText("Player " + pHandler.getSecondPlayer());
    }

    public void updateStats(PlayerHandler pHandler){
        player1AnimalCount.setText("" + pHandler.getFirstPlayer().getPieces().size());

        player2AnimalCount.setText("" + pHandler.getSecondPlayer().getPieces().size());
    }

}
