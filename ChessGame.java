

public class ChessGame
{

//------------------------------------------------------------Linear Peice Class-------------------------------------------------
	private abstract class LinearPeice
	{
		//coordinates of the peice on the board, number of directions the pecie can move
		public int xPos, yPos, rayNumber;

		//type of peice
		public String type;

		//color of Peice
		public String color;

		//Constructor method-----------------------------------------------------------------------------------------------------
		public LinearPeice(int xPos, int yPos, int rayNumber, String type, String color)
		{
			this.xPos = xPos;
			this.yPos = yPos;
			this.rayNumber = rayNumber;
			this.type = type;
			this.color = color;
		}

		//Move Method------------------------------------------------------------------------------------------------------------
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
		//Take Method-----------------------------------------------------------------------------------------------------------------------
		public void take(int xTake, int yTake)
		{

		}

		//Be taken-------------------------------------------------------------------------------------------------------------------------

		//Can Move Method----------------------------------------------------------------------------------------------------------------
		public boolean canMove(int xEnd, int yEnd, String direction)
		{
		
		}
	}
//-------------------------------------------------------------------Board Class----------------------------------------------------------
	private class Board
	{
		public Peice[][] gameBoard = new Peice[8][8];

		public Board()
		{

			for (int i = 0; i < 7; i++)
			{
				for (int j = 0; j < 7; j++)
				{
					if (i % 2 == 0)
					{
						gameBoard[i][j] = new Peice(i,j, "Black", "U");
					}
					else
					{
						gameBoard[i][j] = new Peice(i,j, "White", "U");
					}
				
				}
			}
		}
	}
//-------------------------------------------------------------------Space Class---------------------------------------------------
	public class Space
	{
		public final int xCord, yCord;
		public final String spaceColor;
		public String spaceState;

		public Space(int x, int y, String color, String state)
		{
			this.xCord = x;
			this.yCord = y;
			this.spaceColor = color;
			this.spaceState = state;
		}
	

	}
}
