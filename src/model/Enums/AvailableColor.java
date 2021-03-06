package src.model.Enums;

import java.awt.Color;

/**
 * Enumeration to classify colors of players
 */
public enum AvailableColor{
    
    RED(Color.red),
    BLUE(Color.blue);

    private final Color VISIBLE_COLOR;

    private static final AvailableColor[] colors = values();
    
    /**
     * Maximum number of colors available in the enumeration
     */
    public static final int MAX_COLORS = colors.length;

    /**
     * initializes each enum with its respective Color in the Color class.
     * @param color color it represents
     */
    private AvailableColor(Color color){
        this.VISIBLE_COLOR = color;
    }

    public Color getVisibleColor(){
        return VISIBLE_COLOR;
    }
}
    