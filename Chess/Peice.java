package	Chess;

public abstract class LinearPeice
{
	//coordinates of the peice on the board, number of directions the pecie can move
	public int xPos, yPos, rayNumber;

	//type of peice
	public String type;

	//color of Peice
	public String color;

	//constructor
	public LinearPeice(int xPos, int yPos, int rayNumber, String type, String color)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.rayNumber = rayNumber;
		this.type = type;
		this.color = color;
	}

	//Move
	public void move(int xEnd, int yEnd)
	{
		//var to hold direction
		String direction = "";
		//determine direction of move to select proper ray
		if ((xEnd > xPos) && (yEnd > yPos))
		{
			direction = "NE";
		}
		else if ((xEnd == xPos) && (yEnd > yPos)) 
		{
			direction = "N";
		}
		else if ((xEnd < xPos) && (yEnd > yPos))
		{
			direction = "NW";
		}
		else if ((xEnd < xPos) && (yEnd == yPos)) 
		{
			direction = "W";
		}
		else if ((xEnd < xPos) && (yEnd < yPos)) 
		{
			direction = "SW";
		}
		else if ((xEnd == xPos) && (yEnd < yPos)) 
		{
			direction = "S";
		}
		else if ((xEnd > xPos) && (yEnd < yPos)) 
		{
			direction = "SE";
		}
		else if ((xEnd > xPos) && (yEnd == yPos)) 
		{
			direction = "E";
		}

	}
	//Take
	public void take(int xTake, int yTake)
	{

	}

	//Be taken

	//determines if a peice can move to a given place
	public boolean canMove(int xEnd, int yEnd, String direction)
	{
		
	}

	//Cast ray (to determine move legality, if a pecie can be taken, and for checkmate rules)
	//Returns a list of coordiantes representing where the peice can theoretically move from its current position
	public 
	//Trace ray (deterines if the paths represented by the cast rays are occupied or not)
}