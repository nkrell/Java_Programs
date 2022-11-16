

public class JavaChess
{
	//color codes for terminal
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	//color codes fro terminal background
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	//constructor for entire program
	public JavaChess()
	{
		Board board1 = new Board();
		board1.printBoardColors();
		board1.printCords();
		System.out.println(ANSI_RED + "This text is red!" + ANSI_RESET);
		String characterCode = "\033[30;47;1m";
		char character = 'A';
		System.out.println(characterCode + character);
		System.out.println(ANSI_RESET);
	}
	private abstract class LinearPiece
	{
		//
	}

	private abstract class NonLinearPiece
	{
		//
	}

	public static void main(String[] args)
	{
		JavaChess game1 = new JavaChess();
	}

	private class Board 
	{
		public Space[][] gameBoard = new Space[8][8];

		public Board()
		{
			boolean	passFlag = false;
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					if ((i % 2 == 0) && (j % 2 ==0))
					{
						gameBoard[i][j] = new Space("U", "W", i, j);
					}
					else if ((i % 2 == 0) && (j % 2 !=0)) 
					{
						gameBoard[i][j] = new Space("U", "B", i, j);
					}
					else if ((i % 2 != 0) && (j % 2 ==0))
					{
						gameBoard[i][j] = new Space("U", "B", i, j);
					}
					else if ((i % 2 != 0) && (j % 2 !=0))
					{
						gameBoard[i][j] = new Space("U", "W", i, j);
					}
				}
			} 
		}

		//method to show the colors of the board for debugging
		public void printBoardColors()
		{
			for (int i = 0; i < 8; i++)
			{
				//var to hold growing list of colors
				String line = "";
				for (int j = 0; j < 8; j++)
				{
					//get color of space
					line = line + gameBoard[i][j].getColor() + " ";
				}
				//print horizontal slice of spaces plus x coordiante for that row
				System.out.println((i) + " " + line);
			}
			//prints the bottem row of coordinates
			String line = "  ";
			for (int i = 0; i < 8; i++)
			{
				line = line + i + " ";
			}
			System.out.println(line);
		}

		//method to show the coords of the board for debugging
		public void printCords()
		{
			for (int i = 0; i < 8; i++)
			{
				String line = "";
				for (int j = 0; j < 8; j++)
				{
					//get cords
					int x = gameBoard[i][j].getX();
					int y = gameBoard[i][j].getY();
					//put cords into readable format
					line = line + "(" + x + ", " + y + ")";
				}
				System.out.println(line);
			}
		}

		//method to set board when starting a new game
		public void setBoard()
		{

		}

		//method to show a simplified version of the board as it would appere in the gui
		public void printBoard()
		{
			//
		}
	}

	private class Space
	{
		//class variables for Space
		public final int xCord, yCord;
		public final String spaceColor;
		public String spaceState;

		//constructor for peice class
		public Space(String state, String color, int x, int y)
		{
			this.spaceColor = color;
			this.spaceState = state;
			this.xCord = x;
			this.yCord = y;
		}

		//retruns color
		public String getColor()
		{
			return(this.spaceColor);
		}

		//returns x cord
		public int getX()
		{
			return(this.xCord);
		}

		//returns y cord
		public int getY()
		{
			return(this.yCord);
		}

		//return board state
		public String getState()
		{
			return(this.spaceState);
		}
	}
}