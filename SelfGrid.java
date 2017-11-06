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
    
    public SelfGrid(BattleShipContext context, StatusPanel status) {
        super(context, status);
        this.updateGrid(context.getActive().getSelfBoard());
    }
    
    @Override
    protected Cell getCell(int i, int j) {
        Cell cell = new Cell(i, j, Color.BLACK, Color.BLUE, 5, 20);
/*         cell.setBackground(Color.BLACK);
        cell.setBorder(BorderFactory.createLineBorder(Color.blue, 10));
        cell.setPreferredSize(new Dimension(20, 20)); // for demo purposes only */

        return cell;
    }
    
    public void click(MouseEvent e, Cell cell) {
        // handle the event, for instance
        // handle the event
        this.context.gridAction(cell.getRow(), cell.getColumn());
        Player activePlayer = this.context.getActive();
        this.updateGrid(activePlayer.getSelfBoard());
        System.out.println(cell.toString());
        this.status.update(context);
    }
    
    public void updateGrid(int[][] selfBoard) {
        Color currentColor;
        for (int row = 0; row < 10; ++row) {
            for (int column = 0; column < 10; ++column) {
                int spaceStatus = selfBoard[row][column];
                // inelegant : (
                if(spaceStatus == SelfSpace.EMPTY)
                    currentColor = SelfGrid.EMPTY_COLOR;
                else if(spaceStatus == SelfSpace.SHIP)
                    currentColor = SelfGrid.SHIP_COLOR;
                else 
                    currentColor = SelfGrid.DESTROYED_COLOR; 
                this.cells[row][column].changeColor(currentColor);
            }
        }            
    }
}