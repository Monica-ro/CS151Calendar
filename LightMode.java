/**
 *
 */

import javax.swing.*;
import java.awt.*;


/**
 * @author Monica Orme
 *
 */



/**
Changes the color of JPanel's background
 *
 */
public class LightMode implements ColorModeStrategy {

    @Override
    public Color updateBackground() {
        return Color.lightGray;
    }
}
