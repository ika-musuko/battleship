public class Player {
    public static final int MAX_SHIPS = 5;
    public static final int DEFAULT_BOARD_SIZE = 10;
   
    // constructors
    public Player(String name, int boardSize) {
        this.name = name;
        this.currentMarkR = -1;
        this.currentMarkC = -1;
        this.totalShips = 0;
        this.attackBoard = new AttackSpace[boardSize][boardSize];
        this.selfBoard = new SelfSpace[boardSize][boardSize];
    }
    
    public Player(String name) {
        this(name, Player.DEFAULT_BOARD_SIZE);
    }
    
    // place or rotate a ship
    public void placeShip(int r, int c) {
        
    }
    
    // return the total amount of ships
    public int getTotalShips() {
        return this.totalShips;
    }
    
    // return true if there are no ships
    public boolean noShips() {
        return this.totalShips == 0;
    }
    
    // set a mark to attack on
    public Player setMark(int r, int c) {
        if (this.attackBoard[r][c] ==  AttackSpace.MARKED) 
            return null;
        
        // remove old mark if it exists
        if (this.currentMarkR >= 0 && this.currentMarkC >= 0) {
           this.attackBoard[this.currentMarkR][this.currentMarkC] = AttackSpace.UNMARKED;
        }
        
        // add the new mark
        this.currentMarkR = r;
        this.currentMarkC = c;
        this.attackBoard[this.currentMarkR][this.currentMarkC] = AttackSpace.MARKED;
        return this;
    }
    
    // attack the other player on the current mark
    public Player attack(Player other) {
        if (other.selfBoard[this.currentMarkR][this.currentMarkC] == SelfSpace.SHIP) {
            // todo: attack ship and verify if the ship has been sunk (maybe make a Ship class?)
        }
        
        // reinitialize currentMarks
        this.currentMarkR = -1;
        this.currentMarkC = -1;     
        // return the other platyer
        return other;
    }
    
    /// functions to return useful information for display
    // return the player's name
    public String toString() {
        return this.name;
    }
    
    // return the attackBoard
    public AttackSpace[][] getAttackBoard() {
        return this.attackBoard;
    }
    
    // return the selfBoard
     public SelfSpace[][] getSelfBoard() {
        return this.selfBoard;
    }
    
    private String name;
    private int currentMarkR;
    private int currentMarkC;
    private int totalShips;
    private AttackSpace[][] attackBoard; // place marks on this board (AttackSpace is just an enum)
    private SelfSpace[][] selfBoard; // place ships / monitor ship progress on this board (SelfSpace is just an enum)
}
