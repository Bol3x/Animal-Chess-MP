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

	public static final String MENU_PANEL = "MenuPanel";
	public static final String PLAYER_SELECT_PANEL = "PlayerSelectPanel";
	public static final String GAME_PANEL = "GamePanel";
	public static final String EXIT_FRAME = "EXIT";

	private CardLayout cardLayout;

	private MenuPanel menuPanel;
	private PlayerSelectPanel gameStartPanel;
	private GamePanel gamePanel;

	public GUI(){
		super("Animal Chess");
		
		setSize(700, 950);

		//creates cardLayout
		cardLayout = new CardLayout();
		setLayout(cardLayout);

		//cards in cardLayout
		menuPanel = new MenuPanel();
		gameStartPanel = new PlayerSelectPanel();

		//add to frame
		this.add(menuPanel, MENU_PANEL);
		this.add(gameStartPanel, PLAYER_SELECT_PANEL);

		//show menu card first
		cardLayout.show(getContentPane(), MENU_PANEL);

		//settings
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * sets action listeners for the menu buttons and other select buttons
	 * @param listener
	 */
	public void setActionListener(ActionListener listener){
		menuPanel.setActionListener(listener);
		gameStartPanel.setNextButtonListener(listener);
	}

	/**
	 * gets the menuPanel
	 * @return the menuPanel of the GUI
	 */
	public MenuPanel getMenuPanel(){
		return menuPanel;
	}

	/**
	 * gets the gamePanel
	 * @return the gamePanel of the GUI
	 */
	public GamePanel getGamePanel(){
		return gamePanel;
	}

	/**
	 * gets the PlayerSelectPanel (gameStartPanel)
	 * @return the playerSelectPanel of the GUI
	 */
	public PlayerSelectPanel getPlayerSelectPanel(){
		return gameStartPanel;
	}

	/**
	 * initializes the gamePanel
	 */
	public void setGamePanel(GameBoard gameBoard){
		gamePanel = new GamePanel();
		this.add(gamePanel, GAME_PANEL);
	}

	public void showMenuPanel(){
		cardLayout.show(getContentPane(), MENU_PANEL);
	}

	public void showPlayerSelectPanel(){
		cardLayout.show(getContentPane(), PLAYER_SELECT_PANEL);
	}

	public void showGamePanel(){
		cardLayout.show(getContentPane(), GAME_PANEL);
	}

	private ImageIcon addImageIcon(int w, int h){
		try{
			Image img = ImageIO.read(getClass().getResource("images/mouse.jpg"));
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
