
import java.awt.*;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jeffreyhu
 */
public class Cell extends JPanel{
    public static final int CELL_SIZE = 1;
    private int rPos;
    private int cPos;
    private Color color;

    public Cell (int r, int c, Color color, Color borderColor, int borderWidth, int size) {
        super();
        this.rPos = r;
        this.cPos = c;
        this.setAttributes(color, borderColor, borderWidth, size);
    }
    
    public void setAttributes(Color color, Color borderColor, int borderWidth, int size) {
        this.changeColor(color);
        this.setBorder(BorderFactory.createLineBorder(borderColor, borderWidth));
        this.setPreferredSize(new Dimension(size, size));
    }
    
    public void changeColor(Color color) {
        this.color = color;
        this.setBackground(this.color);
    }
    
    public int getRow(){
        return this.rPos;
    }
    
    public int getColumn(){
        return this.cPos;
    }
}
