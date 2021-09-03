package view;

import javax.swing.*;

import view.menu_panels.ColorPanel;
import view.menu_panels.MainPanel;
import view.menu_panels.PlayerPanel;

import java.awt.*;
import java.awt.event.*;

/**
 * Main GUI class of program.
 * <p>
 * Connects all Panels through a CardLayout to switch between panels.
 * <p>
 * Also hosts all possible button ActionCommands for traversal through the GUI.
 */
public class GUI extends JFrame{

	public static final String MENU_PANEL = "MenuPanel";
	public static final String PLAYER_SELECT_PANEL = "PlayerSelectPanel";
	public static final String GAME_PANEL = "GamePanel";
	public static final String EXIT_FRAME = "EXIT";
    public static final String COLOR_PANEL = "ColorPanel";
    public static final String RESET_GAME = "ResetGame";

	private CardLayout cardLayout;

	private MainPanel mainPanel;
	private PlayerPanel playerPanel;
	private GamePanel gamePanel;
    private ColorPanel colorPanel;

	public GUI(){
		super("Animal Chess");
		
		setSize(610, 850);

		//creates cardLayout
		cardLayout = new CardLayout();
		setLayout(cardLayout);

		//cards in cardLayout
		mainPanel = new MainPanel();
		playerPanel = new PlayerPanel();
        colorPanel = new ColorPanel();
		gamePanel = new GamePanel();

		//add to frame
		this.add(mainPanel, MENU_PANEL);
		this.add(playerPanel, PLAYER_SELECT_PANEL);
        this.add(colorPanel, COLOR_PANEL);
		this.add(gamePanel, GAME_PANEL);

		//show menu card first
		cardLayout.show(getContentPane(), MENU_PANEL);

		//settings
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	/**
	 * sets action listeners for the menu buttons and other select buttons
	 * @param listener
	 */
	public void setActionListener(ActionListener listener){
		mainPanel.setActionListener(listener);
        colorPanel.setNextButtonListener(listener);
		playerPanel.setNextButtonListener(listener);
	}

	/**
	 * gets the menuPanel
	 * @return the menuPanel of the GUI
	 */
	public MainPanel getMainPanel(){
		return mainPanel;
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
	public PlayerPanel getPlayerSelectPanel(){
		return playerPanel;
	}
                  
	/**
	 * gets the ColorPanel (ColorPanel)
	 * @return the ColorPanel of the GUI
	 */
    public ColorPanel getColorPanel(){
    return colorPanel;
    }

	/**
	 * displays the menu panel on the GUI frame
	 */
	public void showMenuPanel(){
		cardLayout.show(getContentPane(), MENU_PANEL);
	}

	/**
	 * displays the player panel on the GUI frame
	 */
	public void showPlayerSelectPanel(){
		cardLayout.show(getContentPane(), PLAYER_SELECT_PANEL);
	}

	/**
	 * displays the game panel on the GUI frame
	 */
	public void showGamePanel(){
		cardLayout.show(getContentPane(), GAME_PANEL);
	}
    
	/**
	 * displays the color panel on the GUI frame
	 */
	public void showColorPanel(){
		cardLayout.show(getContentPane(), COLOR_PANEL);
	}

	/**
	 * resets all panels when player returns to menu.
	 */
	public void resetPanels(){
		//cards in cardLayout
		mainPanel = new MainPanel();
		playerPanel = new PlayerPanel();
		colorPanel = new ColorPanel();
		gamePanel = new GamePanel();

		//add to frame
		this.add(mainPanel, MENU_PANEL);
		this.add(playerPanel, PLAYER_SELECT_PANEL);
		this.add(colorPanel, COLOR_PANEL);
		this.add(gamePanel, GAME_PANEL);
	}
}


