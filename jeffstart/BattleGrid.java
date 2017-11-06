import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public abstract class BattleGrid extends JPanel{



    
    public BattleGrid() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel self = new JPanel();
        self.setLayout(new GridLayout(0,10));
        for (int i = 0; i < 10; i++)
            for(int j =0; j < 10; j++) {
                final Cell cell = getCell(i, j);
                self.add(cell);
                
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
        
        this.add(self);
    }
    
    protected abstract Cell getCell(int i, int j);
    
    public void click(MouseEvent e, Cell cell) {
        // handle the event, for instance
        cell.setBackground(Color.GRAY);
    }
}