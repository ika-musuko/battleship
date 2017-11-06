import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public abstract class BattleGrid extends JPanel{
    protected Cell[][] cells;
    protected BattleShipContext context;
    
    public BattleGrid(BattleShipContext context, boolean withListener, boolean isSelf) {
        this.cells = new Cell[10][10];
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel self = new JPanel();
        self.setLayout(new GridLayout(0,10));
        this.makeCells(self, context, isSelf, withListener);
        this.add(self);
    }
    
    protected void makeCells(JPanel self, BattleShipContext ccontext, boolean isSelf, boolean withListener) {
        for (int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {  
                final Cell cell = new Cell(i, j); 
                self.add(cell); // add to JPanel
                this.cells[i][j] = cell; // add to the cells 2d array

            }
        }      
        
        for (int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                             
                if(withListener) {
                    final Cell cello = this.cells[i][j]; 
                    cello.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                            ccontext.gridAction(cello.getRow(), cello.getColumn());

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
        this.updateGrid(isSelf ? ccontext.getActive().getSelfBoard() 
                               : ccontext.getActive().getAttackBoard());
        this.context = ccontext;    
    }
    
    protected abstract Cell getCell(int i, int j);
    
    public abstract void updateGrid(int[][] boardData);
    
    public void click(MouseEvent e, Cell cell) {
        
        
        
        // test code
        // handle the event, for instance
        //cell.setBackground(Color.GRAY);
    }

}