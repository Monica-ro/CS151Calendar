/**
 * 
 */
package project;

import java.awt.*;
import javax.swing.*;

/**
 * @author Monica Orme
 *
 */

/**
 A strategy that governs the color appearance of the calendar application.
 */
public interface ColorModeStrategy {
	
	/**
	 @return Color - the color that will be applied to the panel background
	 */
	public Color updateBackground();
	
	/**
	 @return JPanel - a JPanel with a new theme
	 */
	public JPanel updateTopPanelTheme();
	
	/**
	 @return JPanel - a JPanel with a new theme
	 */
	public JPanel updateBottomPanelTheme(JPanel original);

	/**
	 * @return
	 */


}
