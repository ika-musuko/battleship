import java.awt.*;
import javax.swing.*;

/**
 *
 * @author jeffreyhu
 */
public class Cell extends JPanel{
    public static final int CELL_SIZE = 1;
    private int rPos;
    private int cPos;

    public Cell (int r, int c) {
        rPos = r;
        cPos = c;
    }
    
    public int getRow(){
        return this.rPos;
    }
    
    public int getColumn(){
        return this.cPos;
    }
}
