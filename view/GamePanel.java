package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.image.*;
import java.io.IOException;

import src.*;
import src.Animals.Animal;
import src.Enums.AvailableColor;


public class GamePanel extends JPanel{

	private JPanel topPanel;
	private JLabel lblTopPlayer;
	private JPanel centerPanel;
    private JPanel botPanel;
	private JLabel lblBotPlayer;

	//model gameBoard
	private GameBoard model;
	private Tile[][] boardModel;

	private TileDisplay[][] boardView = new TileDisplay[GameBoard.ROW][GameBoard.COL];

    public GamePanel(){

        //gamePanel init
		setLayout(new BorderLayout());

		//top animal list
		GridBagConstraints gbc = new GridBagConstraints();
		topPanel = new JPanel(new GridBagLayout());

		JLabel lblTop = new JLabel("Animals: ");
		gbc.insets.bottom = 25;
		gbc.insets.top = 25;
		gbc.gridx = 0;
		gbc.gridy = 0;
		topPanel.add(lblTop, gbc);
		lblTopPlayer = new JLabel("Team RED");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets.bottom = 0;
		gbc.insets.top = 0;
		topPanel.add(lblTopPlayer, gbc);

		this.add(topPanel, BorderLayout.NORTH);

		//main game board
		centerPanel = new JPanel(new GridLayout(GameBoard.ROW, GameBoard.COL));

		//initialize all TileDisplay buttons
		for(int i = 0; i < GameBoard.ROW; i++){
			for(int j = 0; j < GameBoard.COL; j++){
				boardView[i][j] = new TileDisplay();
				
				centerPanel.add(boardView[i][j]);
			}
		}
		highlightTerrain();
		this.add(centerPanel, BorderLayout.CENTER);

		//bot animal list
		gbc = new GridBagConstraints();
		botPanel = new JPanel(new GridBagLayout());

		lblBotPlayer = new JLabel("Team BLUE");
		gbc.gridx = 0;
		gbc.gridy = 0;
		botPanel.add(lblBotPlayer, gbc);
		JLabel lblBot = new JLabel("Animals: ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets.bottom = 25;
		gbc.insets.top = 25;
		botPanel.add(lblBot, gbc);

		this.add(botPanel, BorderLayout.SOUTH);
		
    }

	/**
	 * placeholder for animal images - uses letters instead
	 */
	public void displayAnimal(Animal animal, TileDisplay tile){
		switch(animal.getSpecies()){
			case Mouse 	 -> tile.setText("Mo");
			case Cat 	 -> tile.setText("Ca");
			case Wolf 	 -> tile.setText("Wo");
			case Dog 	 -> tile.setText("Do");
			case Leopard -> tile.setText("Le");
			case Tiger	 -> tile.setText("Ti");
			case Lion	 -> tile.setText("Li");
			case Elephant-> tile.setText("El");
		}
		if (animal.getFaction().getColor() == AvailableColor.BLUE)
			tile.setText(tile.getText().toLowerCase());
	}

	public void highlightTerrain(){
		//put border highlight on trap buttons
		LineBorder trapBorder = new LineBorder(Color.red, 4);
		boardView[0][2].setBorder(trapBorder);
		boardView[0][4].setBorder(trapBorder);
		boardView[1][3].setBorder(trapBorder);
		boardView[8][2].setBorder(trapBorder);
		boardView[8][4].setBorder(trapBorder);
		boardView[7][3].setBorder(trapBorder);

		//put border highlight on river buttons
		LineBorder riverBorder = new LineBorder(Color.BLUE);
		for(int i = 3; i <= 5; i++){
			for(int j = 1; j <= 5; j++){
				if (j != 3)
					boardView[i][j].setBorder(riverBorder);
			}
		}
	}

	public void highlightDens(AvailableColor color){
		Color clr = color.getVisibleColor();
		LineBorder denHighlightColor = new LineBorder(clr, 6);

		TileDisplay den;
		if (color.equals(model.getLowerDen().getColor())){
			den = getTileDisplay(new Position(8,3));
			den.setBorder(denHighlightColor);
		}
		else{
			den = getTileDisplay(new Position(0,3));
			den.setBorder(denHighlightColor);
		}
	}

	public TileDisplay getTileDisplay(Position pos){
		if (Position.isWithinBounds(pos))
			return boardView[pos.getX()][pos.getY()];
		
		return null;
	}
}
