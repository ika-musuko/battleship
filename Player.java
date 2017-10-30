import java.util.*

public class Player {
    
    private SetupBoard stb;
    private SelfBoard sb;
    private AttackBoard ab;
    private int total_pieces;
    private String name;

    public Player(String name) {
        this.stb = new SetupBoard(); // handle setting up ships
        this.sb = new SelfBoard();
        this.ab = new SetupBoard();
        this.name = name;
        this.total_pieces = 5;
    }
    
    public void begin_game(Player p2) {
        this.sb.get_setup(this.stb); // set our self board to our setup board
        this.ab.get_opponent(p2.stb); // set our attack board to the other player's setup board
    }

    public boolean already_attacked(int x, int y) {
        return this.ab.get_status(x, y);
    }
    
    public boolean has_lost() {
        return total_pieces <= 0; 
    }
    
    abstract class AttackState {
        Player p1, p2;
        AttackState(Player p1, Player p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
        abstract void handle();
    }
       
    class HitState extends AttackState{
        void handle() {
            System.out.println("nice hit "+p1.name);
        }
    }
    
    class SinkState extends AttackState {
        void handle() {
            --p2.total_pieces;
            System.out.println("nice sink "+p1.name);
            System.out.println(p2.name+" has "+p2.total_pieces+" ships left");
        }
    }   
    
    class MissState extends AttackState {
        void handle() {
            System.out.println("no hit");
        }        
    }
    public AttackState attack(Player p2, int x, int y) {
        this.ab.mark(x, y);
        Piece hit_piece = p2.sb.hit(x, y);
        if (hit_piece == null)
            return FailState(this, p2);
        hit_piece.take_hit();
        return (hit_piece.get_health() <= 0) ? SinkState(this, p2) : HitState(this, p2);
    }
       
    public void play(Player p2, int x, int y) {
        
        /*int[] ac; // attack coords
        
        // TODO: handle in view/controller class 
        // make sure this space has not been attacked yet
        do {
            ac = this.input_attack(); 
        } while(this.ab.already_attacked(ac[0], ac[1]));*/

        // attack the square
        int attack_result = this.attack(p2, x, y);
        
        // perform the appropriate result
        attack_result.handle(); 
    }
}