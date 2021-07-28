/**
 * 
 */
package project;

import java.awt.Color;

import javax.swing.JPanel;

import sun.awt.www.content.image.jpeg;

/**
 * @author Monica Orme
 *
 */
public class LightMode implements ColorModeStrategy {

	@Override
	public Color updateBackground() {
		return Color.lightGray;
	}

	@Override
	public JPanel updateTopPanelTheme() {
		JPanel topPanel = new JPanel();
		topPanel.setBackground(updateBackground());
		
		return topPanel;
	}

	@Override
	public JPanel updateBottomPanelTheme(JPanel original) {
		JPanel bottomPanel = original;
		bottomPanel.setBackground(updateBackground());
		
		return bottomPanel;
	}

}
