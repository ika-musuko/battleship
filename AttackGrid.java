import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

/**
Represents the other grid
*/
public class AttackGrid extends BattleGrid {
    public AttackGrid(String name) {
        super();
        
    }

    @Override
    protected JPanel getCell()
    {
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setBorder(BorderFactory.createLineBorder(Color.purple, 5));
        panel.setPreferredSize(new Dimension(20, 20)); // for demo purposes only

        return panel;
    }
}