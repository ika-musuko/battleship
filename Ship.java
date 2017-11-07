import java.util.*;


public class Ship 
{
	
	private int [] front = new int[2];
	private int [] middle = new int[2];
	private int [] end = new int[2];
	private int health = 3;

    public Ship(int [] front, int [] middle, int [] end)
    {
    	this.front[0] = front[0];
    	this.front[1] = front[1];
    	this.middle[0] = middle[0];
    	this.middle[1] = middle[1];
    	this.end[0] = end[0];
    	this.end[1] = end[1];
    }
	
    public boolean rotateShip(ArrayList <Ship> shipList, int index)
    {
    	// check if ship is positioned along the row (oriented horizontally)
    	boolean horizontal = this.front[0] == this.middle[0]; 
    	
    	// these are all the potential coordinates of the ship AFTER a rotation
    	int [] frontAfterVertRotation = {this.front[0] + 1, this.middle[1]};
    	int [] endAfterVertRotation = {this.end[0] - 1, this.middle[1]};
    	
    	int [] frontAfterHorizRotation = {this.middle[0], this.front[1] + 1};
    	int [] endAfterHorizRotation = {this.middle[0], this.end[1] - 1};
    	    	
        for (int i = 0; i < shipList.size(); ++i)    // traverse through the ArrayList of ships
        {
        	// for each ship in the ArrayList's front, middle, and end coordinate, compare the 
        	// front and end coordinates after rotation
            int[] frontTest =  shipList.get(i).getFront();
            int[] middleTest = shipList.get(i).getMiddle();
            int[] endTest = shipList.get(i).getEnd();
           
            if(i == index) // this means we found the correct ship in the shipList
            { 
            	;
            }
            else
            {
            	if(horizontal)
            	{
            		if(this.checkCoords(frontTest, frontAfterVertRotation) || this.checkCoords(frontTest, endAfterVertRotation))
    	            {
    	            	return false;
    	            }
    	            if(this.checkCoords(middleTest, frontAfterVertRotation) || this.checkCoords(middleTest, endAfterVertRotation))
    	            {
    	            	return false;
    	            }
    	            if(this.checkCoords(endTest, frontAfterVertRotation) || this.checkCoords(endTest, endAfterVertRotation))
    	            {
    	            	return false;
    	            }
            	}
            	else
            	{
		            if((this.checkCoords(frontTest, frontAfterHorizRotation)) || (this.checkCoords(frontTest, endAfterHorizRotation)))
		            {
		            	return false;
		            }
		            if((this.checkCoords(middleTest, frontAfterHorizRotation)) || (this.checkCoords(middleTest, endAfterHorizRotation)))
		            {
		            	return false;
		            }
		            if((this.checkCoords(endTest, frontAfterHorizRotation)) || (this.checkCoords(endTest, endAfterHorizRotation)))
		            {
		            	return false;
		            }
            	}
	        }
        }
        
     // Horizontal to Vertical
    	if(horizontal) 
    	{
    		this.front[0]++;  				 // increment to vertical row coordinate
    		this.front[1] = this.middle[1];  // revert to middle col coordinate
    		this.end[0]--;    				 // decrement to vertical row coordinate
    		this.end[1] = this.middle[1];    // revert to middle col coordinate
    	}
    	else // Vertical to Horizontal
    	{
    		this.front[0] = this.middle[0];  // increment to vertical row coordinate
    		this.front[1]++; 				 // revert to middle col coordinate
    		this.end[0] = this.middle[0];	 // decrement to vertical row coordinate
    		this.end[1]--;				     // revert to middle col coordinate
    	}

    	return true; 	
    }
    
    
    private void print_coord(int[] coord) {
        System.out.println("COORD row: "+coord[0]+" col: "+coord[1]);
    }
    
    
    private boolean checkCoords(int[] coord1, int[] coord2) {
        return (coord1[0] == coord2[0] && coord1[1] == coord2[1]);
    }
    
    public int [] getFront()
    {
    	return front;
    }
    
    public int [] getMiddle()
    {
    	return middle;
    }

    public int [] getEnd()
    {
    	return end;
    }
    
    public boolean isDead()
    {
    	return health == 0;
    }
    
    public void reduceHealth()
    {
    	health--;
    }
    
    public String toString()
    {
    	return "Ship with coordinates: (" + front[0] + ", " + front [1] + ") , ("
    			+ middle[0] + " , " + middle[1] + ") , (" + end[0] + ", " + end[1] + ")";
    }

}
