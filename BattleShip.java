import javax.swing.*;

public class BattleShip {
    public static final int P1WIN = 0;
    public static final int P2WIN = 1;
    public static void main(String[] args) {
        PlayerScreen player1 = new PlayerScreen("Player1 (attack on red, setup on blue)", true);
        PlayerScreen player2 = new PlayerScreen("Player2 (attack on red, setup on blue)", true);
        
        player1.setup();
        player2.setup();
        
        int whowon = BattleShip.P2WIN;
        while(!player1.lost() && !player2.lost()) {
            player1.play();
            if(player2.lost()) {
                whowon = BattleShip.P1WIN;
                break;
            }
            player2.play();
        }
        String winner = whowon == BattleShip.P1WIN ? "P1 WINS" : "P2 WINS";
        JOptionPane.showMessageDialog(null, winner);
    }
}