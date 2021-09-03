package src.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import src.model.*;
import src.model.Animals.Animal;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * Main game GUI panel.
 * <p>
 * Displays all GameBoard elements on this panel, as well as game states and turn counts
 */
public class GamePanel extends JPanel{

	private JLabel lblTurn;
	private JLabel lblTopPlayer;
	private JLabel lblBotPlayer;

	private TileDisplay[][] boardView = new TileDisplay[GameBoard.ROW][GameBoard.COL];

    public GamePanel(){
		initPanel();
    }

	/**
	 * initializes the gamePanel 
	 */
	private void initPanel(){
		//gamePanel init
		setLayout(new BorderLayout());

		//top animal list
		JPanel topPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		lblTurn = new JLabel("Player x's Turn");
		lblTurn.setFont(lblTurn.getFont().deriveFont(15f));
		gbc.gridx = 0;
		gbc.gridy = 0;
		topPanel.add(lblTurn, gbc);

		lblTopPlayer = new JLabel("Team Y");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets.top = 20;
		topPanel.add(lblTopPlayer, gbc);
		this.add(topPanel, BorderLayout.NORTH);

		//main game board
		GridLayout grid = new GridLayout(GameBoard.ROW, GameBoard.COL);

		ImageIcon img = addImageIcon(588, 730, "RealBoard.jpg");

		JPanel centerPanel = new JPanel(grid){
			@Override
			public void paintComponent(Graphics g){
				super.paintComponent(g);
					g.drawImage(img.getImage(), 4, 1, null);
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

	/**
	 * gets the TileDisplay button on specified location (if valid)
	 * @param pos position of button to get
	 * @return TileDisplay button reference, null if invalid position
	 */
	public TileDisplay getTileDisplay(Position pos){
		if (Position.isWithinBounds(pos))
			return boardView[pos.getX()][pos.getY()];
		
		return null;
	}

	/**
	 * displays all tiles with animals
	 * @param boardModel board to check animals from
	 */
	public void displayTiles(Tile[][] boardModel){
		for(int i = 0; i < GameBoard.ROW; i++)
			for(int j = 0; j < GameBoard.COL; j++)
				if (boardModel[i][j].hasAnimal())
					displayAnimal(boardModel[i][j].getAnimal(), boardView[i][j]);
				else{ 
					boardView[i][j].setIcon(null);
					boardView[i][j].setDisabledIcon(null);
				}
	}

	/**
	 * sets icon for animal on tile passed
	 * @param animal animal to get image from
	 * @param tile tile to set image to
	 */
	public void displayAnimal(Animal animal, TileDisplay tile){
		tile.setIcon(new ImageIcon(animal.getImage()) );
		tile.setDisabledIcon(new ImageIcon(animal.getDisabledImage()) );
	}

	public void setBoardListener(ActionListener listener){
		for(int i = 0; i < GameBoard.ROW; i++)
			for(int j = 0; j < GameBoard.COL; j++)
				boardView[i][j].addActionListener(listener);
	}

	/**
	 * highlights button border of all terrain tiles
	 * @param pHandler player handler for color of den tiles
	 */
	public void highlightTerrain(PlayerHandler pHandler){

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

	/**
	 * enables animal tiles available to move for the turn
	 * @param boardModel model of gameboard to look for animals
	 * @param player current player about to select a move
	 */
	public void enablePlayerPieces(Tile[][] boardModel, Player player){
		for(int i = 0; i < GameBoard.ROW; i++){
			for(int j = 0; j < GameBoard.COL; j++){
				Tile temp = boardModel[i][j];
				//if animal exists and animal is of same faction as player
				if (temp.hasAnimal() && temp.getAnimal().getFaction().equals(player)){

					enableTile(new Position(i,j));
				}
				else
					disableTile(new Position(i,j));
			}
		}
	}

	/**
	 * displays available moves for a selected animal
	 * @param model gameboard reference
	 * @param btnTrigger animal selected
	 */
	public void displayMove(GameBoard model, TileDisplay btnTrigger){
		Position pos = btnTrigger.getPosition();

		for(int i = 0; i < GameBoard.ROW; i++)
			for(int j = 0; j < GameBoard.COL; j++)
				disableTile(new Position(i, j));

		enableTile(pos);

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
	
	/**
	 * undisplay possible moves of selected animal,
	 * used when player selects the animal itself 
	 * to cancel their select.
	 * @param pos position of animal selected
	 */
	public void undisplayMove(Position pos){
		if (pos != null){
			//up
			Position posUp = new Position(pos.getX()-1, pos.getY());
			disableTile(posUp);
			//down
			Position posDown = new Position(pos.getX()+1, pos.getY());
			disableTile(posDown);
			//left
			Position posLeft = new Position(pos.getX(), pos.getY()-1);
			disableTile(posLeft);
			//right
			Position posRight = new Position(pos.getX(), pos.getY()+1);
			disableTile(posRight);
		}
	}

	/**
	 * sets the current turn and player to move
	 * @param nTurn number of turns passed
	 * @param playerColor color of current player to move
	 */
	public void setTurnLabel(int nTurn, String playerColor){
		lblTurn.setText("Turn " + nTurn + ": Player " + playerColor + "'s turn");
	}

	/**
	 * sets the player labels surrounding the game board
	 * @param pHandler player handler to get first and second player's colors
	 */
	public void setPlayerLabels(PlayerHandler pHandler){
		lblTopPlayer.setText("Team " + pHandler.getSecondPlayer());
		lblBotPlayer.setText("Team " +  pHandler.getFirstPlayer());
	}

	/**
	 * enables tile on specified position (if valid)
	 * @param pos position of tile to enable
	 */
	public void enableTile(Position pos){
		if (Position.isWithinBounds(pos)){
			getTileDisplay(pos).setBorder(new LineBorder(Color.GREEN, 4));
			getTileDisplay(pos).setEnabled(true);
		}
	}

	/**
	 * disables tile on specified position (if valid)
	 * @param pos position of tile to disable
	 */
	public void disableTile(Position pos){
		if (Position.isWithinBounds(pos)){
			getTileDisplay(pos).setBorder(UIManager.getBorder("Button.border"));
			getTileDisplay(pos).setEnabled(false);
		}
	}

	/**
	 * disables the entire board
	 * used when the game is over
	 */
	public void disableBoard(){
		for(int i = 0; i < GameBoard.ROW; i++)
			for(int j = 0; j < GameBoard.COL; j++)
				disableTile(new Position(i,j));
	}

	/* 
		Source of sample code for image scaling:
		Oracle (n.d.). How to Use Icons. Retrieved from
		https://docs.oracle.com/javase/tutorial/uiswing/components/icon.html
	*/

	/**
	 * creates an ImageIcon object with specified width and height
	 * @param w width of image
	 * @param h height of image
	 * @param filename filename of image to add
	 * @return ImageIcon object of imported image, null if image is not found.
	 * 
	 * @author Oracle
	 */
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

	/**
	 * Scales the image to specified dimensions
	 * @param srcImg image to scale
	 * @param w width of output image
	 * @param h height of output image
	 * @return scaled image of passed dimensions.
	 * 
	 * @author Oracle
	 */
	private Image getScaledImage(Image srcImg, int w, int h){
		BufferedImage scaledImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = scaledImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();
		return scaledImg;
	}
}
