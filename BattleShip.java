import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class BattleShip { 
    public static void main(String[] args) {
        // only one screen will be shown at a time for each player
        BattleShipContext battleContext = new BattleShipContext(new Player("player 1"), new Player("player 2"));
        PlayerScreen game(battleContext);
    }
}
