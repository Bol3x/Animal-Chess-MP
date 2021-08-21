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

		cardLayout = new CardLayout();
		setLayout(cardLayout);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuPanel = new MenuPanel();
		gameStartPanel = new PlayerSelectPanel();
		gamePanel = new GamePanel();

		add(menuPanel, MENU_PANEL);
		add(gameStartPanel, PLAYER_SELECT_PANEL);
		add(gamePanel, GAME_PANEL);

		cardLayout.show(getContentPane(), MENU_PANEL);

		setVisible(true);
	}

	public void setActionListener(ActionListener listener){
		menuPanel.setActionListener(listener);
		gameStartPanel.setNextButtonListener(listener);
	}

	public MenuPanel getMenuPanel(){
		return menuPanel;
	}

	public GamePanel getGamePanel(){
		return gamePanel;
	}

	public PlayerSelectPanel getPlayerSelectPanel(){
		return gameStartPanel;
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

	public static void main(String[] args){
		GUI a = new GUI();
	}
}
