import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;


public class BattleShip { 
    public static void main(String[] args) {
        PlayerScreen player1("player1", true), player2("player2", false); 
        
        // default constructor sets the game state to SetupState for player 1
        BattleShipContext(player1.getPlayerData(), player2.getPlayerData()); 
        
        
    }

}
