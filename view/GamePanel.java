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


public class GamePanel extends JPanel{

	private JLabel lblTurn;
	private JLabel lblTopPlayer;
	private JLabel lblBotPlayer;

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

		lblTopPlayer = new JLabel("Team Y");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets.top = 30;
		topPanel.add(lblTopPlayer, gbc);
		this.add(topPanel, BorderLayout.NORTH);

		//main game board
		GridLayout grid = new GridLayout(GameBoard.ROW, GameBoard.COL);

		ImageIcon img = addImageIcon(588, 722, "RealBoard.jpg");

		JPanel centerPanel = new JPanel(grid){
			@Override
			public void paintComponent(Graphics g){
				super.paintComponent(g);
					g.drawImage(img.getImage(), 3, 0, null);
			}
		};

		//initialize all TileDisplay buttons
		for(int i = 0; i < GameBoard.ROW; i++){
			for(int j = 0; j < GameBoard.COL; j++){
				boardView[i][j] = new TileDisplay(new Position(i,j) );
				
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
				else boardView[i][j].setIcon(null);
	}

	/**
	 * placeholder for animal images - uses letters instead for now
	 */
	public void displayAnimal(Animal animal, TileDisplay tile){
		StringBuilder filename = new StringBuilder("/view/images/animals/");
		switch (animal.getFaction().getColor()){
			case RED -> filename.append("red_");
			case BLUE ->filename.append("blue_");
		}
		
		switch(animal.getSpecies()){
			case Mouse 	 -> filename.append("mouse.png");
			case Cat 	 -> filename.append("cat.png");
			case Wolf 	 -> filename.append("wolf.png");
			case Dog 	 -> filename.append("dog.png");
			case Leopard -> filename.append("leopard.png");
			case Tiger	 -> filename.append("tiger.png");
			case Lion	 -> filename.append("lion.png");
			case Elephant-> filename.append("elephant.png");
		}

		Image img = null;
		try{
			img = ImageIO.read(getClass().getResource(filename.toString()));
		} catch(IOException e){
			System.out.println(e);
		}
		tile.setIcon(new ImageIcon(img.getScaledInstance(72, 72, Image.SCALE_SMOOTH)));
	}

	public void highlightTerrain(Tile[][] model, PlayerHandler pHandler){
		/*
		ImageIcon trap = addImageIcon(82, 82, "trapTile.png");
		ImageIcon river = addImageIcon(82, 82, "water.jpg");
		ImageIcon den = addImageIcon(82, 82, "denTile.jpg");
		ImageIcon grass = addImageIcon(82, 82, "grass.jpg");

		for(int i = 0; i < GameBoard.ROW; i++){
			for(int j = 0; j < GameBoard.COL; j++){
				switch(model[i][j].getTerrain()){
					case TRAP -> boardView[i][j].setIcon(trap);
					case RIVER -> boardView[i][j].setIcon(river);
					case DEN -> boardView[i][j].setIcon(den);
					case GRASS -> boardView[i][j].setIcon(grass);
				}
			}
		}
		*/
		
		//put border highlight on trap buttons
		LineBorder trapBorder = new LineBorder(Color.DARK_GRAY, 2);
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

		//upper den color
		LineBorder upperDenBorder = new LineBorder(pHandler.getSecondPlayer().getColor().getVisibleColor(), 6);
		boardView[0][3].setBorder(upperDenBorder);

		//lower den color
		LineBorder lowerDenBorder = new LineBorder(pHandler.getFirstPlayer().getColor().getVisibleColor(), 6);
		boardView[8][3].setBorder(lowerDenBorder);
	}

	public void enablePlayerPieces(Tile[][] boardModel, Player player){
		for(int i = 0; i < GameBoard.ROW; i++){
			for(int j = 0; j < GameBoard.COL; j++){
				Tile temp = boardModel[i][j];
				if (temp.hasAnimal() && temp.getAnimal().getFaction().equals(player)){
					enableTile(new Position(i,j));
				}
				else
					disableTile(new Position(i,j));
			}
		}
	}

	public void displayMove(GameBoard model, JButton btnTrigger){
		Position pos = null;
		for(int i = 0; i < GameBoard.ROW; i++){
			for(int j = 0; j < GameBoard.COL; j++){
				if(btnTrigger.equals(boardView[i][j])){
					pos = new Position(i, j);
				}
				else boardView[i][j].setEnabled(false);
			}
		}

		if (pos != null){
			//up
			Position posUp = new Position(pos.getX()-1, pos.getY());
			if (model.isValidPosition(model.searchTile(pos).getAnimal(), posUp)){
				getTileDisplay(posUp).setBorder(new LineBorder(Color.GREEN, 4));
				enableTile(posUp);
			}
			//down
			Position posDown = new Position(pos.getX()+1, pos.getY());
			if (model.isValidPosition(model.searchTile(pos).getAnimal(), posDown)){
				getTileDisplay(posDown).setBorder(new LineBorder(Color.GREEN, 4));
				enableTile(posDown);
			}
			//left
			Position posLeft = new Position(pos.getX(), pos.getY()-1);
			if (model.isValidPosition(model.searchTile(pos).getAnimal(), posLeft)){
				getTileDisplay(posLeft).setBorder(new LineBorder(Color.GREEN, 4));
				enableTile(posLeft);
			}
			//right
			Position posRight = new Position(pos.getX(), pos.getY()+1);
			if (model.isValidPosition(model.searchTile(pos).getAnimal(), posRight)){
				getTileDisplay(posRight).setBorder(new LineBorder(Color.GREEN, 4));
				enableTile(posRight);
			}
		}
	}
	
	public void undisplayMove(Position pos){
		if (pos != null){
			//up
			Position posUp = new Position(pos.getX()-1, pos.getY());
			if (Position.isWithinBounds(posUp)){
				getTileDisplay(posUp).setBorder(UIManager.getBorder("Button.border"));
				disableTile(posUp);
			}
			//down
			Position posDown = new Position(pos.getX()+1, pos.getY());
			if(Position.isWithinBounds(posDown)){
				getTileDisplay(posDown).setBorder(UIManager.getBorder("Button.border"));
				disableTile(posDown);
			}
			//left
			Position posLeft = new Position(pos.getX(), pos.getY()-1);
			if (Position.isWithinBounds(posLeft)){
				getTileDisplay(posLeft).setBorder(UIManager.getBorder("Button.border"));
				disableTile(posLeft);
			}
			//right
			Position posRight = new Position(pos.getX(), pos.getY()+1);
			if (Position.isWithinBounds(posRight)){
				getTileDisplay(posRight).setBorder(UIManager.getBorder("Button.border"));
				disableTile(posRight);
			}
		}
	}

	public void setTurnLabel(String playerColor){
		lblTurn.setText("Player " + playerColor + "'s turn");
	}

	public void setPlayerLabels(PlayerHandler pHandler){
		lblTopPlayer.setText("Team " + pHandler.getSecondPlayer());
		lblBotPlayer.setText("Team " +  pHandler.getFirstPlayer());
	}

	public void enableTile(Position pos){
		if (Position.isWithinBounds(pos))
			boardView[pos.getX()][pos.getY()].setEnabled(true);
	}

	public void disableTile(Position pos){
		if (Position.isWithinBounds(pos)){
			boardView[pos.getX()][pos.getY()].setEnabled(false);
		}
	}

	public void disableBoard(){
		for(int i = 0; i < GameBoard.ROW; i++)
			for(int j = 0; j < GameBoard.COL; j++)
				disableTile(new Position(i,j));
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

	private ImageIcon addImageIcon(int w, int h, String filename){
		try{
			Image img = ImageIO.read(getClass().getResource("/view/images/"+filename));
			ImageIcon icon = new ImageIcon(img);
			ImageIcon scaledImg = new ImageIcon(getScaledImage(icon.getImage(), w, h));

			return scaledImg;

		} catch(IOException e){
			System.out.println(e);
		}
		return null;
	}

	private Image getScaledImage(Image srcImg, int w, int h){
		BufferedImage scaledImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = scaledImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();
		return scaledImg;
	}
}
