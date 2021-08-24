package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.IOException;

import src.*;


public class GamePanel extends JPanel{

    private JPanel botPanel;
	private JPanel centerPanel;
	private JPanel topPanel;

	//model gameBoard
	private Tile[][] boardModel;

	private TileDisplay[][] board = new TileDisplay[GameBoard.ROW][GameBoard.COL];

    public GamePanel(){

        //gamePanel init
		setLayout(new BorderLayout());

		//top animal list
		topPanel = new JPanel(new FlowLayout());

		JLabel lblTop = new JLabel("Animals: ");
		topPanel.add(lblTop);

		add(topPanel, BorderLayout.NORTH);

		//main game board
		centerPanel = new JPanel(new GridLayout(GameBoard.ROW, GameBoard.COL));

		//initialize all TileDisplay buttons
		for(int i = 0; i < GameBoard.ROW; i++){
			for(int j = 0; j < GameBoard.COL; j++){
				board[i][j] = new TileDisplay();
				
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
