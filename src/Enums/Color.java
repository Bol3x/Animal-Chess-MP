package src.Enums;

public enum Color{
    
    RED,
    BLUE;

    public static final int MAX_COLORS = 2;

    private static final Color[] colors = values();

    public static Color chooseColor(int nChoice){
        switch(nChoice){
            case 1: return RED;
            case 2: return BLUE;
            default: return null;
        }
    }

    public static void displayColors(){

        for(int i = 0; i < colors.length; i++)
            System.out.println( (i+1) + ". " + colors[i]);
    }
}
