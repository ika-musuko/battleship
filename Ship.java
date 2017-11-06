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
