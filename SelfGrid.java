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
    public SelfGrid(String name) {
        super();
    }

    @Override
    protected Cell getCell(int i, int j)
    {
        Cell cell = new Cell(i, j);
        cell.setBackground(Color.black);
        cell.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        cell.setPreferredSize(new Dimension(20, 20)); // for demo purposes only

        return cell;
    }
    
    public void click(MouseEvent e, Cell cell) {
        // handle the event, for instance
        super.click(e, cell);
        System.out.println(cell.getRow());
        System.out.println(cell.getColumn());
    }
}