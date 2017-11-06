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
        // initialize the window
        super("battleship");
        
        // initialize the current context
        this.context = context;
        
        // get the current players from the context
        Player active = context.getActive();
        Player waiting = context.getWaiting();
        this.setTitle(active.toString());
               
        /*  Add a status region to the SOUTH of PlayerScreen to show the following. Use the hint in #3 to drive this display.
        Number of own ships:
        Number of own ships sunk:
        Number of enemy ships sunk:
        Current state (Can be one of player1 setup, player2 setup, player1 attack, player2 attack, Game over (result) ) */
        int ownShips = active.getTotalShips();
        int ownShipsSunk = active.getSunkenShips();
        int enemyShipsSunk = waiting.getSunkenShips();
        String currentState = context.toString();
        // StatusPanel is a custom class extending JPanel which will store all the status data
        this.status = new StatusPanel(ownShips, ownShipsSunk, enemyShipsSunk, currentState);
        
        // actually draw the UI
        // get the board data from the active player and send the board data to the corresponding Grids
        this.selfGrid = new SelfGrid(this.context, this.context.selfGridListener(), true);
        this.attackGrid = new AttackGrid(this.context, this.context.attackGridListener(), false);
        // make the layout
        this.setLayout(new BorderLayout());
        this.add(this.selfGrid, BorderLayout.EAST);
        this.add(this.attackGrid, BorderLayout.WEST);
        //this.add(new JLabel(name), BorderLayout.NORTH);
        this.add(this.status, BorderLayout.SOUTH);
        JButton next = new JButton("next");
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                context.nextAction();
            }
        });
        this.add(next, BorderLayout.CENTER);
        this.pack();
        
        // pop up on the screen
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public SelfGrid getSelfGrid() {
        return this.selfGrid;
    }

    public AttackGrid getAttackGrid() {
        return this.attackGrid;
    }    
 
    public void hideScreen() {
        this.setVisible(false);
    }
}
