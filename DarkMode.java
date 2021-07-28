/**
 * 
 */
package project;

import java.awt.Color;
import javax.swing.JPanel;

/**
 * @author Monica Orme
 *
 */
public class DarkMode implements ColorModeStrategy {

	@Override
	public Color updateBackground() {
		return Color.black;
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
