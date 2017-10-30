import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Space extends JButton {
    private int x;
    private int y;
    private Color color;
    
    public Space(int x, int y, Color color) {
        this.color = color
        this.setBackground(this.color);
        this.setOpaque(true);
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}