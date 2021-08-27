package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.IOException;

import src.*;
import src.Animals.Animal;
import src.Enums.AvailableColor;


public class GamePanel extends JPanel{

	private JLabel lblTurn;
	JLabel lblTopPlayer;
	private JPanel centerPanel;
	JLabel lblBotPlayer;

	private GameStats gameInfoFrame;

	private TileDisplay[][] boardView = new TileDisplay[GameBoard.ROW][GameBoard.COL];

    public GamePanel(){
		initPanel();
    }

	private void initPanel(){
		//gamePanel init
		setLayout(new BorderLayout());

		//top animal list
		JPanel topPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		lblTurn = new JLabel("Player x's Turn");
		gbc.gridx = 0;
		gbc.gridy = 0;
		topPanel.add(lblTurn, gbc);

		JLabel lblTopPlayer = new JLabel("Team Y");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets.top = 30;
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
		//display model board to view board

		this.add(centerPanel, BorderLayout.CENTER);

		//bot animal list
		JPanel botPanel = new JPanel(new FlowLayout());

		lblBotPlayer = new JLabel("Team X");
		botPanel.add(lblBotPlayer);
		this.add(botPanel, BorderLayout.SOUTH);
	}

	public void displayTiles(Tile[][] boardModel){
		for(int i = 0; i < GameBoard.ROW; i++)
			for(int j = 0; j < GameBoard.COL; j++)
				if (boardModel[i][j].hasAnimal())
					displayAnimal(boardModel[i][j].getAnimal(), boardView[i][j]);
	}

	/**
	 * placeholder for animal images - uses letters instead for now
	 */
	public void displayAnimal(Animal animal, TileDisplay tile){
		switch(animal.getSpecies()){
			case Mouse 	 -> tile.setText("MO");
			case Cat 	 -> tile.setText("CA");
			case Wolf 	 -> tile.setText("WO");
			case Dog 	 -> tile.setText("DO");
			case Leopard -> tile.setText("LE");
			case Tiger	 -> tile.setText("TI");
			case Lion	 -> tile.setText("LI");
			case Elephant-> tile.setText("EL");
		}
		if (animal.getFaction().getColor().equals(AvailableColor.BLUE) )
			tile.setText(tile.getText().toLowerCase());
	}

	public void highlightTerrain(PlayerHandler pHandler){
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
				if (j != 3){
					boardView[i][j].setBorder(riverBorder);
					boardView[i][j].setBackground(Color.CYAN);
				}
			}
		}

		LineBorder upperDenBorder = new LineBorder(pHandler.getFirstPlayer().getColor().getVisibleColor());
		boardView[0][3].setBorder(upperDenBorder);

		LineBorder lowerDenBorder = new LineBorder(pHandler.getSecondPlayer().getColor().getVisibleColor());
		boardView[8][3].setBorder(lowerDenBorder);
	}

	public void enablePlayerPieces(Tile[][] boardModel, Player player){
		for(int i = 0; i < GameBoard.ROW; i++){
			for(int j = 0; j < GameBoard.COL; j++){
				Tile temp = boardModel[i][j];
				if (temp.hasAnimal() && temp.getAnimal().getFaction().equals(player))
					boardView[i][j].setEnabled(true);
				else
					boardView[i][j].setEnabled(false);
			}
		}
	}

	public TileDisplay getTileDisplay(Position pos){
		if (Position.isWithinBounds(pos))
			return boardView[pos.getX()][pos.getY()];
		
		return null;
	}

	public GameStats getGameStats(){
		return gameInfoFrame;
	}

	public void setBoardListener(ActionListener listener){
		for(int i = 0; i < GameBoard.ROW; i++)
			for(int j = 0; j < GameBoard.COL; j++)
				boardView[i][j].addActionListener(listener);
			
		
	}

	public void setPlayerLabels(PlayerHandler pHandler){
		lblTopPlayer.setText("Team " + pHandler.getSecondPlayer().getColor());
		lblBotPlayer.setText("Team " +  pHandler.getFirstPlayer().getColor());
	}

}
