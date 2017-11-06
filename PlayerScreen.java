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
    
    public PlayerScreen(BattleShipContext context) {
        // set the global layout
        super("battleship");
        this.setLayout(new BorderLayout());
        
        // initialize the context and use the context to update the grids
        this.context = context;
        Player activePlayer = this.context.getActive();
        this.selfGrid = new SelfGrid(this.context);
        this.attackGrid = new AttackGrid(this.context);
        this.status = new StatusPanel(this.context);
        
        // write to the GUI
        this.add(this.selfGrid, BorderLayout.EAST);
        this.add(this.attackGrid, BorderLayout.WEST);
        this.add(this.status, BorderLayout.SOUTH);
        this.add(new JLabel(this.context.toString()), BorderLayout.NORTH);
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
        
        // get the active player
        Player activePlayer = this.context.getActive();
        
        // reflect the active player's data on the grids
        this.selfGrid.updateGrid(activePlayer.getSelfBoard());
        this.selfGrid.updateGrid(activePlayer.getSelfBoard());
        
        // update the status
        this.status.update(this.context);
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
