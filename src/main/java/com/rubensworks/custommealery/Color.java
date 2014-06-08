package com.rubensworks.custommealery;

/**
 * Color holder class.
 * @author rubensworks
 *
 */
public class Color {

    private int red;
    private int green;
    private int blue;
    
    /**
     * Make a new instance.
     * @param red
     * @param green
     * @param blue
     */
    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    
    /**
     * @return the red
     */
    public int getRed() {
        return red;
    }
    /**
     * @return the green
     */
    public int getGreen() {
        return green;
    }
    /**
     * @return the blue
     */
    public int getBlue() {
        return blue;
    }
    
    /**
     * Convert this color to an integer representation.
     * @return The integer representation of this color.
     */
    public int toInt() {
        return (int)getRed() << 16 | (int)getGreen() << 8 | (int)getBlue();
    }
    
}
