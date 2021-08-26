package view;

import javax.swing.*;
import java.awt.*;

public class GameStats extends JFrame{
    private JPanel playerPanels;

    public GameStats(){
        super("Stats");
        this.setSize(400, 300);
        this.setLayout(new GridBagLayout());

        

        this.setVisible(true);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
}
