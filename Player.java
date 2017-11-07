import java.util.*;

public class Player {
    public static final int MAX_SHIPS = 5;
    public static final int DEFAULT_BOARD_SIZE = 10;
   
    // constructors
    public Player(String name, int boardSize) {
        this.name = name;
        this.currentMarkR = -1;
        this.currentMarkC = -1;
        this.shipList = new ArrayList<>();
        this.attackBoard = new int[boardSize][boardSize];
        this.selfBoard = new int[boardSize][boardSize];
        this.sunkenShips = 0;
    }
    
    public Player(String name) {
        this(name, Player.DEFAULT_BOARD_SIZE);
    }
    
    // place or rotate a ship
    public void placeShip(int r, int c) {      // r and c passed in from a mouse click

    	// this if block handles rotating existing ships
    	if(this.selfBoard[r][c] == SelfSpace.SHIP)  // if coordinate (r, c) is ON an existing ship
    	{
    		int [] coord = {r, c};   		
    		Ship shipToRotate = null;
    		
    		// Locate the index of the ship that is on (r, c)
    		int indexShip = findShipIndex(shipList, coord);
    		shipToRotate = shipList.get(indexShip);
    		
    		
    		// remove old ship front and end squares on selfBoard
    		this.selfBoard[shipToRotate.getFront()[0]][shipToRotate.getFront()[1]] = SelfSpace.EMPTY;  // old front 
        	this.selfBoard[shipToRotate.getEnd()[0]][shipToRotate.getEnd()[1]] = SelfSpace.EMPTY;      // old end
    		
        	boolean rotated = shipToRotate.rotateShip(shipList, indexShip);
        	System.out.println("Ship was able to rotate: " + rotated);  // test print statement
        	
        	// Now update new front and end squares on selfBoard
        	this.selfBoard[shipToRotate.getFront()[0]][shipToRotate.getFront()[1]] = SelfSpace.SHIP;   // new front
        	this.selfBoard[shipToRotate.getEnd()[0]][shipToRotate.getEnd()[1]] = SelfSpace.SHIP;       // new end

    	}
    	else // this block handles placing new ship
    	{
    		// check if there's already 5 ships in shipList
    		if(shipList.size() == MAX_SHIPS)  
        	{
        		return;
        	}
    		
    		Ship ship1 = this.makeShip(r, c);  
    		if (ship1 == null) {
    			System.out.println("Failed to place ship!");
    			return; // if not a valid place to make a ship
    		}
    		System.out.println("Successfully placed ship!");
    		// assign corresponding squares to SHIP
    		int[] front = ship1.getFront();
    		int[] middle = ship1.getMiddle();
    		int[] end = ship1.getEnd();
    	
    		this.selfBoard[front[0]][front[1]] = SelfSpace.SHIP;
    		print_coord(front);
    		this.selfBoard[middle[0]][middle[1]] = SelfSpace.SHIP;
    		print_coord(middle);
    		this.selfBoard[end[0]][end[1]] = SelfSpace.SHIP;
    		print_coord(end);

    		// push to the current list of ships
    		this.shipList.add(ship1);
    	}
    }
    
    private void print_coord(int[] coord) {
        System.out.println("COORD row: "+coord[0]+" col: "+coord[1]);
    }
    
    // factory function to make a ship and manage coordinates
    private Ship makeShip(int row, int col) {
    	
    	// coordinates of center of new ship
    	int[] middle = {row, col};
    	
        // MIDDLE COORD TEST 1: check if middle of a ship is positioned in a corner which would result in invalid positioning
        if ((row == 0 || row == Player.DEFAULT_BOARD_SIZE-1) && (col == 0 || col == Player.DEFAULT_BOARD_SIZE-1))
            return null;
          
        // MIDDLE COORD TEST 2: check if the middle coordinate is on an existing ship on the board
        if (this.selfBoard[middle[0]][middle[1]] != SelfSpace.EMPTY) {
            return null;
        }
        
        // Default initialize the front and end cell of the ship to orient horizontally
        int[] front = {row+1, col};
        int[] end = {row-1, col}; 
        
        // FRONT AND END TEST 1: check if the ship's front or end coordinates are out of bounds (happens if middle touching board edge)
        if (row-1 < 0 || row+1 >= Player.DEFAULT_BOARD_SIZE) {
            front[0] = row;   // revert to middle row coordinate
            front[1] = col+1; // change to horizontal orientation coordinate
            end[0] = row;     // revert to middle row coordinate
            end[1] = col-1;   // change to horizontal orientation coordinate
        }
        
        // If all tests passed so far, create a new ship objects out of these 3 
        Ship shipNew = new Ship(front, middle, end);  // new ship object
        
        
        // Check if either front or end coordinates are on an existing cell on the board
        if (this.selfBoard[front[0]][front[1]] != SelfSpace.EMPTY || this.selfBoard[end[0]][end[1]] != SelfSpace.EMPTY)
        {
        	// attempt to rotate to place ship in valid position
        	int indexShip = findShipIndex(shipList, middle);
        	boolean test = shipNew.rotateShip(shipList, indexShip);
        	System.out.println(test);
            //return null;
        }      
        return shipNew;
    }
    
    public int findShipIndex(ArrayList <Ship> shipList, int [] coord)
    {
    	int indexShip = 0;  // initialize to the first ship in shipList
    	for (int i = 0; i < shipList.size(); ++i)    // traverse through the ArrayList of ships
        {
			System.out.println("ship # 0: " + shipList.get(i));
			//System.out.println("getFront: " + shipList.get(i).getFront());
			//System.out.println("getMiddle: " + shipList.get(i).getMiddle());
			//System.out.println("getEnd: " + shipList.get(i).getEnd());
			
			if(checkCoords(shipList.get(i).getFront(),(coord)) || checkCoords(shipList.get(i).getMiddle(),(coord)) 
					|| checkCoords(shipList.get(i).getEnd(),(coord)))  // if(coord matches a ship in the shipList)
			{
				indexShip = i;
			}
        }
    	return indexShip;
    }
    
    // return the total amount of ships
    public int getTotalShips() {
        return this.shipList.size();
    }
    
    // return true if there are no ships
    public boolean noShips() {
        return this.getTotalShips() == 0;
    }
    
    // set a mark to attack on
    public Player setMark(int r, int c) {
    	// Check if mark coordinates are pointing to a cell that has already been attacked
        if (this.attackBoard[r][c] != AttackSpace.UNMARKED) 
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
    
    private boolean checkCoords(int[] coord1, int[] coord2) {
        return (coord1[0] == coord2[0] && coord1[1] == coord2[1]);
    }
    
    // attack the other player on the current mark
    public Player attack(Player other) {
        // SUCCESSFUL ATTACK CASE
        if (other.selfBoard[this.currentMarkR][this.currentMarkC] == SelfSpace.SHIP) {
            int[] attacked = {this.currentMarkR, this.currentMarkC};
            Ship damagedShip = null;
            // Check every possible ship's coordinate to see if there was a hit
            // If found, set damagedShip to that specific ship
            int i = 0;
            for (; i < this.shipList.size(); ++i) {
                int[] front =  this.shipList.get(i).getFront();
                if(this.checkCoords(front, attacked)){
                    damagedShip = this.shipList.get(i);
                    break;
                }
                int[] middle =  this.shipList.get(i).getMiddle();
                if(this.checkCoords(middle, attacked)){
                    damagedShip =  this.shipList.get(i);
                    break;
                }
                int[] end =  this.shipList.get(i).getEnd();
                if(this.checkCoords(end, attacked)){
                    damagedShip =  this.shipList.get(i);
                    break;
                }
            }
            
            // If attack was successful, damagedShip will have been set to an existing Ship
            if (damagedShip != null) {
                damagedShip.reduceHealth();
                if(damagedShip.isDead())
                    this.shipList.remove(i);

                this.attackBoard[this.currentMarkR][this.currentMarkC] = AttackSpace.SUCCESS; // this code will confirm that the attack was a success!
                
                other.selfBoard[this.currentMarkR][this.currentMarkC] = SelfSpace.DESTROYED;
                other.sinkShip();
            }
            // failsafe
            else {
                this.attackBoard[this.currentMarkR][this.currentMarkC] = AttackSpace.FAILURE; 
            }
        }
       
       // FAILURE ATTACK CASE
        else {
            this.attackBoard[this.currentMarkR][this.currentMarkC] = AttackSpace.FAILURE; 
        }
        
        // reinitialize currentMarks
        this.currentMarkR = -1;
        this.currentMarkC = -1;     
        // return the other player
        return other;
    }
    
    
    /// functions to return useful information for display
    // return the player's name
    public String toString() {
        return this.name;
    }
    
    // return the attackBoard
    public int[][] getAttackBoard() {
        return this.attackBoard;
    }
    
    // return the selfBoard
    public int[][] getSelfBoard() {
        return this.selfBoard;
    }
    
    // return sunk ships
    public int getSunkenShips() {
        return this.sunkenShips;
    }
    
    // sink a ship
    private void sinkShip() {
        ++this.sunkenShips;
    }
    
    private String name;
    private int sunkenShips;
    private int currentMarkR;
    private int currentMarkC;
    private ArrayList<Ship> shipList;
    private int[][] attackBoard; // place marks on this board (AttackSpace is just an enum)
    private int[][] selfBoard; // place ships / monitor ship progress on this board (SelfSpace is just an enum)
}
