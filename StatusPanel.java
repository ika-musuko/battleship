import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class StatusPanel extends JPanel {
        private JLabel ownShipsLabel;      
        private JLabel ownShipsSunkLabel; 
        private JLabel enemyShipsSunkLabel;
        private JLabel currentStateLabel; 

    public StatusPanel(BattleShipContext context) {
        
        
        Player activePlayer = context.getActive();
        Player waitingPlayer = context.getWaiting();
        
        this.ownShipsLabel       = new JLabel("Own Ships: "+activePlayer.getTotalShips());
        this.ownShipsSunkLabel   = new JLabel("Own Ships Sunk: "+activePlayer.getSunkenShips());
        this.enemyShipsSunkLabel = new JLabel("Enemy Ships Sunk: "+waitingPlayer.getSunkenShips());
        this.currentStateLabel   = new JLabel("Current Game State: \n"+context.toString());
        
        this.ownShipsLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);   
        this.ownShipsSunkLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);     
        this.enemyShipsSunkLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);   
        this.currentStateLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);     
        
        Box box = Box.createVerticalBox();
        box.add(ownShipsLabel);      
        box.add(ownShipsSunkLabel);  
        box.add(enemyShipsSunkLabel);
        box.add(currentStateLabel);  

        this.add(box);
    }
    
    public void update(BattleShipContext context) {
        Player activePlayer = context.getActive();
        Player waitingPlayer = context.getWaiting();
        this.ownShipsLabel.setText("Own Ships: "+activePlayer.getTotalShips());
        this.ownShipsSunkLabel.setText("Own Ships Sunk: "+activePlayer.getSunkenShips());
        this.enemyShipsSunkLabel.setText("Enemy Ships Sunk: "+waitingPlayer.getSunkenShips());
        this.currentStateLabel.setText("Current Game State: "+context.toString());
    }
}