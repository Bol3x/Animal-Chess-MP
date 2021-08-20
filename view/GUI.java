package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import src.GameBoard;
import src.Animals.*;
import src.Enums.AnimalName;


public class GUI extends JFrame{
	private JPanel botPanel;
	private JPanel centerPanel;
	private JPanel topPanel;
	ArrayList<Animal> testAnimal = new ArrayList<Animal>();

	private JButton[][] board;

	public GUI(){
		super("Animal Chess");
		
		setSize(800, 1000);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//top animal list
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		JLabel lblTop = new JLabel("Animals: ");
		topPanel.add(lblTop);
		testAnimal.add(new Mouse(null, null));

		add(topPanel, BorderLayout.NORTH);

		//main game board
		board = new JButton[GameBoard.ROW][GameBoard.COL];
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(GameBoard.ROW, GameBoard.COL));
		for(int i = 0; i < GameBoard.ROW; i++){
			for(int j = 0; j < GameBoard.COL; j++){
				board[i][j] = new JButton("");

				addImage(i, j);

				centerPanel.add(board[i][j]);
			}
		}
		add(centerPanel, BorderLayout.CENTER);

		//bot animal list
		botPanel = new JPanel();
		botPanel.setLayout(new FlowLayout());
		JLabel lblBot = new JLabel("Animals: ");
		botPanel.add(lblBot);

		add(botPanel, BorderLayout.SOUTH);

		setVisible(true);
	}

	private void addImage(int x, int y){
		try{
			Image img = ImageIO.read(getClass().getResource("images/mouse.jpg"));
			ImageIcon icon = new ImageIcon(img);
			ImageIcon scaledImg = new ImageIcon(getScaledImage(icon.getImage(), 64, 64));

			board[x][y].setIcon(scaledImg);
		} catch(IOException e){
			System.out.println(e);
		}

	}

	private Image getScaledImage(Image srcImg, int w, int h){
		BufferedImage scaledImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = scaledImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();
		return scaledImg;
	}

	public static void main(String[] args){
		GUI a = new GUI();
	}
}
