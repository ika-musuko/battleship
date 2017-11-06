import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public abstract class BattleGrid extends JPanel{
    protected BattleShipContext context;
    protected Cell[][] cells;
    protected StatusPanel status;
    
    public BattleGrid(BattleShipContext context, StatusPanel status) {
        this.context = context;
        this.status = status;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel self = new JPanel();
        self.setLayout(new GridLayout(0,10));
        this.cells = new Cell[10][10];
        this.makeCells(self);
        this.add(self);
    }
    
    protected void makeCells(JPanel self) {
        for (int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {  
                final Cell cell = this.getCell(i, j);
                this.cells[i][j] = cell; // add to array                
                self.add(cell); // add to JPanel
                cell.addMouseListener(new MouseListener() {
                    public void mouseClicked(MouseEvent e) {
                        click(e, cell);
                    }
                    // other mouse listener functions

                    @Override
                    public void mousePressed(MouseEvent e) {
                         //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                         //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                
            }
        }       
    }
    
    // abstract methods
    protected abstract Cell getCell(int i, int j);
    protected abstract void click(MouseEvent e, Cell cell);
    protected abstract void updateGrid(int[][] boardData);

}