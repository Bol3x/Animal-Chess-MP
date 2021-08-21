package view;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.IOException;

public class MenuPanel extends JPanel{
    
    private GridBagConstraints gbc;
    private JButton playButton;
    private JButton exitButton;

    public MenuPanel(){
        gbc = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        JLabel logo = new JLabel(addImageIcon(300, 300));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(logo, gbc);

        playButton = new JButton("Start Game");
        playButton.setActionCommand(GUI.PLAYER_SELECT_PANEL);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(playButton, gbc);

        exitButton = new JButton("Exit Game");
        exitButton.setActionCommand(GUI.EXIT_FRAME);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(exitButton, gbc);
    }

    public void setActionListener(ActionListener listener){
        playButton.addActionListener(listener);
        exitButton.addActionListener(listener);
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
