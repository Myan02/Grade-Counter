package org.openjfx.Shapes;

import javafx.scene.paint.Color;
import java.util.Random;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum MyColor {

    //colors
    BLACK(0, 0, 0, 255),
    WHITE(255, 255, 255, 255),
    RED(255, 0, 0, 255),
    LIME(0, 255, 0, 255),
    BLUE(0, 0, 255, 255),
    YELLOW(255, 255, 0, 255),
    CYAN(0, 255, 255, 255),
    MAGENTA(255, 0, 255, 255),
    MAROON(128, 0, 0, 255),
    OLIVE(128, 128, 0, 255),
    GREEN(0, 128, 0, 255),
    PURPLE(128, 0, 128, 255),
    TEAL(0, 128, 128, 255),
    NAVY(0, 0, 128, 255),
    TOMATO(255, 99, 71, 255),
    SALMON(250, 128, 114, 255),
    ORANGE(255, 69, 0, 255),
    TURQUOISE(0, 206, 209, 255),
    VIOLET(138, 43, 226, 255),
    PINK(255, 192, 203, 255),
    BROWN(139, 69, 19, 255),
    ALICEBLUE(240, 248, 255, 255),
    ANTIQUEWHITE(250, 235, 215, 255),
    AQUAMARINE(127, 255, 212, 255),
    BISQUE(255, 228, 196, 255),
    BLUEVIOLET(138, 43, 226, 255),
    DARKRED(165, 42, 42, 255),
    CADETBLUE(95, 158, 160, 255),
    CHARTREUSE(127, 255, 0, 255),
    CHOCOLATE(210, 105, 30, 255),
    CORNSILK(255, 248, 220, 255),
    DARKCYAN(0, 139, 139, 255),
    DARKGREEN(0, 100, 0, 255),
    DARKSALMON(233, 150, 122, 255),
    DARKSEAGREEN(143, 188, 143, 255),
    DARKSLATEGRAY(47, 79, 79, 255),
    DEEPPINK(255, 20, 147, 255),
    DEEPSKYBLUE(0, 191, 255, 255),
    TRANSPARENT(255,255,255, 0);
    
    //variables
    private int R, G, B, A, RGBA;

    //constructor
    MyColor(int R, int G, int B, int A) {
        SetR(R);
        SetG(G);
        SetB(B);
        SetA(A);
        SetRGBA(R, G, B, A);
    }   

    //set methods
    private void SetR(int R) {
        this.R = R;
    }

    private void SetG(int G) {
        this.G = G;
    }

    private void SetB(int B) {
        this.B = B;
    }

    private void SetA(int A) {
        this.A = A;
    }

    //get methods
    private final int GetR() {
        return this.R;
    }

    private final int GetG() {
        return this.G;
    }

    private final int GetB() {
        return this.B;
    }

    private final int GetA() {
        return this.A;
    }

    private final int GetRGBA() {
        return this.RGBA;
    }

    //convert opacity from an int 0 - 255 into a double 0.0 - 1.0
    private final double SetOpacityToDouble() {
        return GetA() / 255;
    }

    //packed color word
    private void SetRGBA(int R, int G, int B, int A) {
        this.RGBA = (R << 24) & 0xFF000000 | (G << 16) & 0x00FF0000 | (B << 8) & 0x0000FF00 | A;
    }

    //rgb to hex code
    public final String GetHexCode() {
        return "#" + Integer.toHexString(GetRGBA()).toUpperCase();
    }

    //return a javafx color object to use with javafx graphics context
    public final Color GetJavaFXColor() {
        return Color.rgb(GetR(), GetG(), GetB(), SetOpacityToDouble());
    }

    //returns a random color
    public static MyColor GetRandomColor() {
        List<MyColor> shuffled = Arrays.asList(MyColor.values());
        Collections.shuffle(shuffled);
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

    @Override
    public String toString() {
        return "Color picked: " + GetHexCode() + "\n";
    }
}

