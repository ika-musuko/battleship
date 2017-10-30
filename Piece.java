public class Piece {
    public final static VERTICAL = 0;
    public final static HORIZONTAL = 1;
    private int x;
    private int y;
    private int state;
    private int health;
    
    public Piece(int x, int y) {
        this.x = x;
        this.y = y;
        this.state = Piece.VERTICAL;
        this.health = 3;
    }
    
    public void toggle() {
        this.state = (this.state + 1) % 2;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void take_hit() {
        --this.health;
    }
    
    public int get_health() {
        return this.health;
    }
}