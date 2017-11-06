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
	
    public void rotateShip()
    {
    	// Horizontal to Vertical
    	if(this.front[0] == this.middle[0])  // check if ship is positioned along the row (oriented horizontally)
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
    

}
