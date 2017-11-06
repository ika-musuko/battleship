import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

/**
Represents the player's own grid
*/
public class SelfGrid extends BattleGrid {
    public static final Color EMPTY_COLOR = Color.BLACK;
    public static final Color SHIP_COLOR = Color.GRAY;
    public static final Color DESTROYED_COLOR = Color.RED;
    
    public SelfGrid(BattleShipContext context, boolean withListener, boolean isSelf) {
        super(context, withListener, isSelf);
    }
    
    @Override
    protected Cell getCell(int i, int j) {
        Cell cell = new Cell(i, j);
        cell.setBackground(Color.black);
        cell.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
        cell.setPreferredSize(new Dimension(20, 20)); // for demo purposes only

        return cell;
    }
    
    public void click(MouseEvent e, Cell cell) {
        // handle the event, for instance
        super.click(e, cell);
        System.out.println(cell.getRow());
        System.out.println(cell.getColumn());
    }
    
    // enum types having strict type checking in java makes me write the same method in two related classes twice : ( 
    public void updateGrid(int[][] selfBoard) {
        Color currentColor;
        for (int row = 0; row < 10; ++row) {
            for (int column = 0; column < 10; ++column) {
                int spaceStatus = selfBoard[row][column];
                if(spaceStatus == SelfSpace.EMPTY)
                    currentColor = SelfGrid.EMPTY_COLOR;
                else if(spaceStatus == SelfSpace.SHIP)
                    currentColor = SelfGrid.SHIP_COLOR;
                else 
                    currentColor = SelfGrid.DESTROYED_COLOR; 
                System.out.println("row: "+row+" column: "+column);
                this.cells[row][column].setBackground(currentColor);
            }
        }            
    }
}