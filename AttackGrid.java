import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

/**
Represents the other grid
*/
public class AttackGrid extends BattleGrid {
    public AttackGrid(String name) {
        super();
        
    }

    @Override
    protected Cell getCell(int i, int j)
    {
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
}