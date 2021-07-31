/**
 *
 */

import javax.swing.*;
import java.awt.*;


/**
 * @author Monica Orme
 *
 */
public class LightMode implements ColorModeStrategy {

    @Override
    public Color updateBackground() {
        return Color.lightGray;
    }

    /*
    @Override
    public JPanel updatePanelTheme(JPanel original) {
        JPanel newPanel = original;
        newPanel.setBackground(updateBackground());
        return newPanel;
    }
    */

}
