import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

/**
Represents the other grid
*/
public class AttackGrid extends BattleGrid {
    public static final Color UNMARKED_COLOR  = Color.BLACK;
    public static final Color MARKED_COLOR    = Color.ORANGE;
    public static final Color SUCCESS_COLOR   = Color.GREEN;
    public static final Color FAILURE_COLOR   = Color.RED;
    
    public AttackGrid(BattleShipContext context, boolean withListener, boolean isSelf) {
        super(context, withListener, isSelf);
    }

    @Override
    protected Cell getCell(int i, int j){
        Cell cell = new Cell(i, j);
        cell.setBackground(Color.white);
        cell.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 5));
        cell.setPreferredSize(new Dimension(20, 20)); // for demo purposes only

        return cell;
    }
    
    @Override 
    public void click(MouseEvent e, Cell cell) {
        // handle the event, for instance
        cell.setBackground(Color.GRAY);
    }
    
    public void updateGrid(int[][] attackBoard) {
        Color currentColor;
        for (int row = 0; row < 10; ++row) {
            for (int column = 0; column < 10; ++column) {
                int spaceStatus = attackBoard[row][column];
                if(spaceStatus == AttackSpace.MARKED)
                    currentColor = AttackGrid.MARKED_COLOR;                
                else if(spaceStatus == AttackSpace.SUCCESS)
                    currentColor = AttackGrid.SUCCESS_COLOR;                
                else if(spaceStatus == AttackSpace.FAILURE)
                    currentColor = AttackGrid.FAILURE_COLOR;
                else 
                    currentColor = AttackGrid.UNMARKED_COLOR; 
                this.cells[row][column].setBackground(currentColor);
            }     
        }            
    }
    
}