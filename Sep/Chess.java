import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;  
import java.awt.event.*; 
import javax.swing.SwingUtilities;
import java.lang.reflect.InvocationTargetException;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.*;
import java.awt.*;




public class Chess
{


	public Chess()
	{
		//all the subclasses are initialized here
		Board board1 = new Board();
		RenderingEngine engine = new RenderingEngine();
		board1.setBoard();
		board1.printBoard();
		//board1.printCords();
		board1.printBoardColors();
		board1.attributes(1,6);
		engine.addBoard(board1);
		engine.resetVisible();
		engine.resetVisible();
	}

	// describes the behavior of peices that move in stright lines
	private abstract class LinearPeice
	{

	}

	//describes the behaviour of pawns and knights
	private abstract class NonLinearPeice
	{

	}

	//the main method
	public static void main(String[] args)
	{
		//this starts the program
		Chess newGame = new Chess();
	}

	//the board class
	private class Board 
	{
		//there is only one board, and it is made up of spaces
		public Space[][] gameBoard = new Space[8][8];

		//constructor for the board class
		public Board()
		{
			//this code sets up the board
			//this is the "peice" that represents an empty space
			//this peices are "taken" when the board is set with actual peices
			Piece emptySpace = new Piece("U", "U");
			boolean	passFlag = false;
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					if ((i % 2 == 0) && (j % 2 ==0))
					{
						gameBoard[i][j] = new Space(emptySpace, "W", i, j);
					}
					else if ((i % 2 == 0) && (j % 2 !=0)) 
					{
						gameBoard[i][j] = new Space(emptySpace, "B", i, j);
					}
					else if ((i % 2 != 0) && (j % 2 ==0))
					{
						gameBoard[i][j] = new Space(emptySpace, "B", i, j);
					}
					else if ((i % 2 != 0) && (j % 2 !=0))
					{
						gameBoard[i][j] = new Space(emptySpace, "W", i, j);
					}
				}
			}

			//more code can be put here if other board functions are required
		}

		//method for printing out a simple version of the board as it would appere in the gui
		public void printBoard()
		{
			for(int i = 0; i < 8; i++)
			{
				//temp var for holding the growing line
				String line = "";
				for(int j = 0; j < 8; j++)
				{
					//collect attributes of the board spaces
					String color = gameBoard[i][j].getColor();
					String state = gameBoard[i][j].getState().getPieceType();

					//determine if space is empty
					if (state != "U")
					{
						line = line + state + " ";
					}
					else
					{
						//remove later
						color = " ";
						line = line + color + " ";
					}
				}
				//print out grown line
				System.out.println((i) + "   " + line);
			}
			//print out bottom row of coordinates
			String line = " ";
			for (int i = 0; i < 8; i++)
			{
				line = line + i + " ";
			}
			System.out.println("  ");
			System.out.println("  " + line);
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

		//method for printing the coords of eahc space
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

		public void attributes(int x, int y)
		{
			System.out.println(gameBoard[x][y].getColor());
			System.out.println(gameBoard[x][y].getState().getPieceType());
		}

		//method to set the board when starting a new game
		public void setBoard()
		{
			//pawn
			Piece whitePawn = new Piece("W", "P");
			//set pawn rows
			for (int i = 0; i < 8; i++)
			{
				gameBoard[1][i].setState(whitePawn);
			}

		}

		//method to get the space color at each position of the board
		public String getBoardColor(int i, int j)
		{
			return(gameBoard[i][j].getColor());
		}
	}

	//the space class
	private class Space
	{
		//class variables for Space
		//keeps track of the location of the space
		private final int xCord, yCord;
		//keeps track of the color of the space
		private final String spaceColor;
		//keeps track of the state of the space, what peice is there
		private Piece spaceState;

		//constructor for space
		public Space(Piece state, String spaceColor, int xCord, int yCord)
		{
			this.spaceColor = spaceColor;
			this.spaceState = state;
			this.xCord = xCord;
			this.yCord = yCord;
		}

		//returns the space color
		public String getColor()
		{
			return(this.spaceColor);
		}

		//returns x coord
		public int getX()
		{
			return(this.xCord);
		}

		//returns y coord
		public int getY()
		{
			return(this.yCord);
		}

		//returns peice 
		public Piece getState()
		{
			return(this.spaceState);
		}

		//method to change space state post-construction
		//for moving and taking peices
		public void setState(Piece state)
		{
			this.spaceState = state;
		}


	}

	//the peice class
	private class Piece
	{
		//class variables for peice
		private String color;
		//peice type
		public String type;

		//constructor for peice
		public Piece(String newColor, String newType)
		{
			this.color = newColor;
			this.type = newType;
		}

		//returns peice color
		public String getPieceColor()
		{
			return(this.color);
		}

		//returns peice type
		public String getPieceType()
		{
			return(this.type);
		}

		//method for changing the peices type
		public void setPieceType(String newType)
		{
			this.type = newType;
		}

		//method for changing color
		public void setPieceColor(String newColor)
		{
			this.color = newColor;
		}
	}

	//this class handles setting up the gui and updating it
	private class RenderingEngine extends JFrame
	{
		//a copy of board1
		Board board = new Board();
		//gui elements for rendering engine

		//the constructor
		public RenderingEngine()
		{
			
			super("Chess");
			//setting up basic window attributes
			setSize(1920, 1080);
			setLocationRelativeTo(null);
			setLayout(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//set visible
			setVisible(true);
		}

		//method for fixing visibility errors
		public void resetVisible()
		{
			//this.setState(Frame.ICONIFIED);
			//this.setState(Frame.NORMAL);
			//setVisible(true);
			//setState(Frame.NORMAL);
			revalidate();
			repaint();
		}

		//method for adding board
		public void addBoard(Board gameBoard)
		{
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					JLabel pic = new JLabel();
					//check the color of the space
					if (gameBoard.getBoardColor(i, j) == "W")
					{
						pic.setIcon(new ImageIcon("whiteSpace.png"));
					}
					else 
					{
						pic.setIcon(new ImageIcon("blackSpace.png"));
					}
					//find pic dimensions
					Dimension size = pic.getPreferredSize();
					//place pic in proper spot
					pic.setBounds((i + 1) * 50, (j + 1) * 50, size.width, size.height);
					add(pic);
				}
			}
		}

		//method for adding peices
		public void addPieces()
		{
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					
				}
			}
		}
	}
}

