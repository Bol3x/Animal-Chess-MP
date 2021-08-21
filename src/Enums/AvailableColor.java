package src.Enums;

/**
 * Enumeration to classify colors of players
 */
public enum AvailableColor{
    
    RED,
    BLUE;


    /**
     * Maximum number of colors available in the enumeration
     */
    public static final int MAX_COLORS = 2;

    private static final AvailableColor[] colors = values();

    /**
     * Gets an enum by its order of declaration in the class
     * @param i - number of Color's ordinal
     * @return color enum
     */
    public static AvailableColor getColor(int i){
        return colors[i];
    }
}
    