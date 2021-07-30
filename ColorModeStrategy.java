import javax.swing.*;
import java.awt.*;

/**
 * @author Monica Orme
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
     Updates the color mode of the panel theme

     @param original - the original JPanel
     @return JPanel - a Jpanel with an updated background color
     */
    public JPanel updatePanelTheme(JPanel original);


}
