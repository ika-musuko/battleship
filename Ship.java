import java.util.*;


public class Ship {

    public static final int SHIPLENGTH = 3;
    public static final int SHIPWIDTH = 1;
    
    List<ArrayList <int []>> shipCoordinates = new ArrayList<ArrayList <int []>>();  
    // shipCoordinates is an ArrayList of ArrayLists of int arrays: The outer ArrayList will consist of 
    // either 1 or 2 inner ArrayLists which hold the possible coordinates of the ship, stored in the int arrays.
   
    // There are 3 possible scenarios for shipCoordinates:
    // 1.) The ship is placed in a corner which is impossible because the front of the ship goes off the board
    //     Therefore, a null value will be stored inside the int arrays which will be stored inside a single inner ArrayList which is then
    //     stored inside shipCoordinates (outer ArrayList).
    // 2.) The ship is placed alongside the edge of the board which is possible, but it cannot be rotated otherwise part of the ship would go off the board
    //     Therefore, the 3 coordinates making up the ship's position will be stored inside int arrays, which will be stored inside
    //     a single inner ArrayList which is then stored inside shipCoordinates.
    // 3.) The ship is placed somewhere in the middle of the board, not touching any edges.  This is possible and can be rotated.
    //     Therefore, there are 2 possible orientations for the ship (either horizontal or vertical).  Each orientation's set of 3 coordinates
    //     will be stored inside an inner ArrayList.  Both inner ArrayLists will be stored inside shipCoordinates.
    // 
    // for example (2,2) as center coordinate would result in shipCoordinates = { {(1,2), (2,2), (3,2)} , {(2,1), (2,2), (2,3)} }

    private int row;  			// x coordinate of the center of the ship 
    private int col;  			// y coordinate of the center of the ship
    private int health = 3;     // each ship requires 
    boolean vertical = false;   // true = vertical, false = horizontal
    
    // to add: currentRow, currentCol
    //
    
    
	public Ship(int row, int col)
	{
		this.row = row;
		this.col = col;
		
		/*
		 either u generate a ship (exists) or you didn't (null)
		 if there is a ship, iterate through array
		 
		 
		  (1,2) (2,2) (3,2)
		  
		  catch = set of 3 points
		  
		  (2,1) (2,2) (2,3)
		  
		  want   {{(1,2) (2,2) (3,2)} , {(2,1) (2,2) (2,3)}}
		  
		  
		  (currentShipCoordinate + 1) % size
		  possibleShipCoordinates
		  
		  getCurrentShipCoordinates()
		  
		*/ 
		
		ArrayList<int[]> possibleCoordinates = new ArrayList<int[]>(); // consists of each 
		
		int [] middle = {row, col}; // this won't change
		int [] front = {row, col}; // front and end row/col coordinates will be adjusted below
		int [] end = {row, col};
		
		
		if((row == 0 || row == 9) && (col == 0 || col == 9))
		{
			// if ship is in one of the four corners
			possibleCoordinates.add(null);
			shipCoordinates.add(possibleCoordinates);
		}
		else if(row == 0 || row == 9 || col == 0 || col == 9)
		{ 
			// if ship is touching an edge of the board (but not corner)
			// ship is unrotatable
			
			if(row == 0)  // col values change
			{
				front[1]++;
				end[1]--;
			}
			else if(col == 0) // row values change
			{
				front[0]++;
				end[0]--;
			}
			else if(row == 9) // col values change
			{
				front[1]++;
				end[1]--;
			}
			else // col == 9, row values change
			{
				front[0]++;
				end[0]--;
			}
			
			// edgeCoordinates = {front, middle, end}
			
			possibleCoordinates.add(front);
			possibleCoordinates.add(middle);
			possibleCoordinates.add(end);
			
			shipCoordinates.add(possibleCoordinates);
		}
		else
		{
			// the ship is in the middle of the board, away from all edges / corners
			// ship is rotatable 
			
			// middle[0] = row, middle[1] = col
			
			// possibleCoordinates will contain horizontal orientation coordinates
			// possiblecoordinatesVertical will contain vertical orientation coordinates
			ArrayList<int[]> possibleCoordinatesVertical = new ArrayList<int[]>();
			
			
			// HORIZONTAL ORIENTATION so col values change
			front[0]++;
			end[0]--;	
			
			possibleCoordinates.add(front);
			possibleCoordinates.add(middle);
			possibleCoordinates.add(end);
		
			// VERTICAL ORIENTATION so row values change
			front[0]--; // undo horizontal orientation changes to front row values
			end[0]++;   // undo horizontal orientation changes to end row values
				
			front[1]++;
			end[1]--;
				
			possibleCoordinatesVertical.add(front);
			possibleCoordinatesVertical.add(middle);
			possibleCoordinatesVertical.add(end);	
			
			shipCoordinates.add(possibleCoordinates);
			shipCoordinates.add(possibleCoordinatesVertical);
		}

	}
	
	public void rotateShip()
	{
		// rotate ship means to change coordinates for front / end arrays
		
		if(shipCoordinates.size() == 2) // ship is not touching an edge or in a corner, so it can be rotated
		{
			shipCoordinates.get(1);
		}
	}
	
	public int getRow()  // return x coordinate of ship's middle coordinate
	{
		return row;
	}
	
	public int getCol() // return y coordinate of ship's middle coordinate
	{
		return col;
	}
	
	public int getLength()
	{
		return SHIPLENGTH;
	}
	
	public int getWidth()
	{
		return SHIPWIDTH;
	}
	

	
}
