import javax.swing.*;
import java.awt.*;

/**
 * @author Monica Orme
 */
public class DarkMode implements ColorModeStrategy {

    //JPanel original;

    public DarkMode() {
        //this.original = originalPanel;
    }

    @Override
    public Color updateBackground() {
        return Color.darkGray;
    }

    @Override
    public JPanel updatePanelTheme(JPanel original) {
        JPanel newPanel = original;
        newPanel.setBackground(updateBackground());

        return newPanel;
    }


}
