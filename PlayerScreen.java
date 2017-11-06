import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class PlayerScreen extends JFrame {
    private BattleShipContext context; 
    private SelfGrid selfGrid;
    private AttackGrid attackGrid;
    private StatusPanel status;
    private JLabel activeName;
    
    public PlayerScreen(BattleShipContext context) {
        // set the global layout
        super("battleship");
        this.setLayout(new BorderLayout());
        
        // initialize the context and use the context to update the grids
        this.context = context;
        Player activePlayer = this.context.getActive();
        this.activeName = new JLabel(activePlayer.toString());
        this.status = new StatusPanel(this.context);
        this.selfGrid = new SelfGrid(this.context, this.status);
        this.attackGrid = new AttackGrid(this.context, this.status);
        
        
        // write to the GUI
        this.add(this.selfGrid, BorderLayout.EAST);
        this.add(this.attackGrid, BorderLayout.WEST);
        this.add(this.status, BorderLayout.SOUTH);
        this.add(this.activeName, BorderLayout.NORTH);
        JButton next = new JButton("next");
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextClick();
            }
        });
        this.add(next, BorderLayout.CENTER);
        this.pack();
        
        // show the window
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // update the contents of the PlayerScreen to reflect context.activePlayer
    public void nextClick() {
        // update the state
        this.context.nextAction();
        
        // write to grid
        Player activePlayer = this.context.getActive();
        this.selfGrid.updateGrid(activePlayer.getSelfBoard());  
        this.attackGrid.updateGrid(activePlayer.getAttackBoard());  
        this.status.update(context);
        this.activeName.setText(activePlayer.toString());
    }

    public void hideScreen() {
        this.setVisible(false);
    }
    
    public void showScreen() {
        this.setVisible(true);
    }
    
    public void resetScreen() {
        this.hideScreen();
        this.showScreen();
    }
}
