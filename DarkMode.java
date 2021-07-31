import javax.swing.*;
import java.awt.*;

/**
 * @author Monica Orme
 */
public class DarkMode implements ColorModeStrategy {
    @Override
    public Color updateBackground() {
        return Color.darkGray;
    }
}
