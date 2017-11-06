import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class StatusPanel extends JPanel {
    public StatusPanel(int ownShips, int ownShipsSunk, int enemyShipsSunk, String currentState) {
        JLabel ownShipsLabel       = new JLabel("Own Ships: "+ownShips);
        JLabel ownShipsSunkLabel   = new JLabel("Own Ships Sunk: "+ownShipsSunk);
        JLabel enemyShipsSunkLabel = new JLabel("Enemy Ships Sunk: "+enemyShipsSunk);
        JLabel currentStateLabel   = new JLabel("Current Game State: "+currentState);
        
        this.add(ownShipsLabel);      
        this.add(ownShipsSunkLabel);  
        this.add(enemyShipsSunkLabel);
        this.add(currentStateLabel);        
    }
}