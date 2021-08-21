package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.IOException;

import src.GameBoard;
import src.Animals.*;
import src.Enums.AnimalName;


public class GamePanel extends JPanel{

    private JPanel botPanel;
	private JPanel centerPanel;
	private JPanel topPanel;

	private JButton[][] board;

    public GamePanel(){
        //gamePanel init
		setLayout(new BorderLayout());

		//top animal list
		topPanel = new JPanel(new FlowLayout());
		
		JLabel lblTop = new JLabel("Animals: ");
		topPanel.add(lblTop);

		add(topPanel, BorderLayout.NORTH);

		//main game board
		board = new JButton[GameBoard.ROW][GameBoard.COL];
		centerPanel = new JPanel(new GridLayout(GameBoard.ROW, GameBoard.COL));

		for(int i = 0; i < GameBoard.ROW; i++){
			for(int j = 0; j < GameBoard.COL; j++){
				board[i][j] = new JButton("");
				
				centerPanel.add(board[i][j]);
			}
		}
		add(centerPanel, BorderLayout.CENTER);

		//bot animal list
		botPanel = new JPanel(new FlowLayout());

		JLabel lblBot = new JLabel("Animals: ");
		botPanel.add(lblBot);

		add(botPanel, BorderLayout.SOUTH);
    }

}
