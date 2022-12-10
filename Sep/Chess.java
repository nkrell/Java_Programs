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
		//
		board1.printBoardColors();
		board1.attributes(1,6);
		
		engine.resetVisible();
		engine.addCordTiles();
		engine.addCord(board1);
		engine.addPieces(board1);
		engine.addBoard(board1);
		engine.resetVisible();
		//board1.printCords();
		engine.whitesTurn();
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
					String pieceColor = gameBoard[i][j].getState().getPieceColor();

					//determine if space is empty
					if (state != "U")
					{
						line = line + pieceColor + " ";
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
					String state = gameBoard[i][j].getState().getPieceType();
					//put cords into readable format
					line = line + "(" + x + ", " + y +  " " + state + ")";
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
			Piece blackPawn = new Piece("B", "P");
			//Rook
			Piece whiteRook = new Piece("W", "R");
			Piece blackRook = new Piece("B", "R");
			//Knight
			Piece whiteKnight = new Piece("W", "H");
			Piece blackKnight = new Piece("B", "H");
			//Bishop
			Piece whiteBishop = new Piece("W", "B");
			Piece blackBishop = new Piece("B", "B");
			//Queen
			Piece whiteQueen = new Piece("W", "Q");
			Piece blackQueen = new Piece("B", "Q");
			//King
			Piece whiteKing = new Piece("W", "K");
			Piece blackKing = new Piece("B", "K");
			//set pawn rows
			for (int i = 0; i < 8; i++)
			{
				gameBoard[i][1].setState(whitePawn);
				gameBoard[i][6].setState(blackPawn);
			}
			//set rooks
			gameBoard[0][7].setState(blackRook);
			gameBoard[7][7].setState(blackRook);
			gameBoard[0][0].setState(whiteRook);
			gameBoard[7][0].setState(whiteRook);
			//set knights
			gameBoard[6][7].setState(blackKnight);
			gameBoard[1][7].setState(blackKnight);
			gameBoard[1][0].setState(whiteKnight);
			gameBoard[6][0].setState(whiteKnight);
			//set bishops
			gameBoard[2][0].setState(whiteBishop);
			gameBoard[5][0].setState(whiteBishop);
			gameBoard[2][7].setState(blackBishop);
			gameBoard[5][7].setState(blackBishop);
			//set royals
			gameBoard[3][7].setState(blackQueen);
			gameBoard[4][7].setState(blackKing);
			gameBoard[3][0].setState(whiteQueen);
			gameBoard[4][0].setState(whiteKing);

		}

		//method to get the space color at each position of the board
		public String getBoardColor(int i, int j)
		{
			return(gameBoard[i][j].getColor());
		}

		//method to get the peice at a given position on the board
		public Piece getBoardState(int i, int j)
		{
			return(gameBoard[i][j].getState());
		}

		//retruns the space at a given position
		public Space getBoardSpace(int i, int j)
		{
			return(gameBoard[i][j]);
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
		JLabel startingInputLetterLabel = new JLabel("Letter");
		JLabel startingInputNumberLabel = new JLabel("Number");
		JLabel endingInputLetterLabel = new JLabel("Letter");
		JLabel endingInputNumberLabel = new JLabel("Number");
		JLabel to = new JLabel("To");
		JTextField startingInputLetter = new JTextField();
		JTextField startingInputNumber = new JTextField();
		JTextField endingInputLetter = new JTextField();
		JTextField endingInputNumber = new JTextField();
		JButton moveButton = new JButton("Move");
		//the constructor
		public RenderingEngine()
		{
			
			super("Chess");
			//setting up basic window attributes
			setSize(1366, 768);
			setLocationRelativeTo(null);
			setLayout(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//set up gui elements
			startingInputLetter.setBounds(100, 575, 50, 22);
			startingInputNumber.setBounds(175, 575, 50, 22);
			startingInputLetterLabel.setBounds(100, 550, 50, 22);
			startingInputNumberLabel.setBounds(175, 550, 55, 22);
			endingInputLetter.setBounds(300, 575, 50, 22);
			endingInputNumber.setBounds(375, 575, 50, 22);
			endingInputLetterLabel.setBounds(300, 550, 50, 22);
			endingInputNumberLabel.setBounds(375, 550, 55, 22);
			to.setBounds(250, 575, 20, 22);
			moveButton.setBounds(425, 575, 75, 22);
			//add elements
			add(startingInputLetter);
			add(endingInputLetter);
			add(startingInputNumber);
			add(endingInputNumber);
			add(moveButton);
			add(startingInputLetterLabel);
			add(startingInputNumberLabel);
			add(endingInputLetterLabel);
			add(endingInputNumberLabel);
			add(to);
			//set attributes (action listener)

			//set visible
			setVisible(true);
		}

		//methods to show whites turn banner
		public void whitesTurn()
		{
			JLabel pic = new JLabel();
			pic.setIcon(new ImageIcon("whitesTurn.png"));
			Dimension size = pic.getPreferredSize();
			pic.setBounds(100, 25, size.width, size.height);
			add(pic);
		}

		//metjod to show blacks turn banner
		public void blacksTurn()
		{
			JLabel pic = new JLabel();
			pic.setIcon(new ImageIcon("blacksTurn.png"));
			Dimension size = pic.getPreferredSize();
			pic.setBounds(100, 25, size.width, size.height);
			add(pic);
		}

		//method for fixing visibility errors
		public void resetVisible()
		{
			//removeAll();
			revalidate();
			repaint();
		}

		//method for resetting Jframe
		public void reset()
		{
			removeAll();
		}

		//method for adding the coordinate tiles to the edge of the board
		public void addCordTiles()
		{	
			//list of letters for easy indexing
			String[] letterList = {"A", "B", "C", "D", "E", "F", "G", "H"};
			//adds verticel tiles
			for (int i = 0; i < 8; i++)
			{
				JLabel pic = new JLabel();
				JLabel number = new JLabel(Integer.toString((i + 1 - 9) * (1 - 2) ));
				pic.setIcon(new ImageIcon("cordTile.png"));
				Dimension size = pic.getPreferredSize();
				pic.setBounds(75, (i + 2) * 50, size.width, size.height);
				number.setBounds(85, (i + 2) * 50, size.width, size.height);
				add(number);
				add(pic);
			}
			//adds horizontal tiles
			for (int i = 0; i < 8; i++)
			{
				JLabel pic = new JLabel();
				JLabel letter = new JLabel(letterList[i]);
				pic.setIcon(new ImageIcon("cordTile2.png"));
				Dimension size = pic.getPreferredSize();
				pic.setBounds((i + 2) * 50, 500 , size.width, size.height);
				letter.setBounds(((i + 2) * 50) + 20, 500 , size.width, size.height);
				add(letter);
				add(pic);
			}
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
					if (gameBoard.getBoardColor(j, i) == "W")
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
					pic.setBounds((j + 2) * 50, (i + 2) * 50, size.width, size.height);
					add(pic);
				}
			}
		}

		//method for adding peices
		public void addPieces(Board gameBoard)
		{
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					JLabel pic = new JLabel();
					//check what peice is in the space
					Piece tempPiece = gameBoard.getBoardState(j, i);
					//if there is no piece
					if ((tempPiece.getPieceColor() == "U") && (tempPiece.getPieceType() == "U"))
					{
						pic.setIcon(new ImageIcon("blankSpace.png"));
					}
					//if the piece is a white pawn
					if ((tempPiece.getPieceColor() == "W") && (tempPiece.getPieceType() == "P"))
					{
						pic.setIcon(new ImageIcon("whitePawn.png"));
					}
					//if the peice is a black pawn
					if ((tempPiece.getPieceColor() == "B") && (tempPiece.getPieceType() == "P"))
					{
						pic.setIcon(new ImageIcon("blackPawn.png"));
					}
					//if the piece is a black rook
					if ((tempPiece.getPieceColor() == "B") && (tempPiece.getPieceType() == "R"))
					{
						pic.setIcon(new ImageIcon("blackRook.png"));
					}
					//if the piece is a white rook
					if ((tempPiece.getPieceColor() == "W") && (tempPiece.getPieceType() == "R"))
					{
						pic.setIcon(new ImageIcon("whiteRook.png"));
					}
					//if the piece is a white knight
					if ((tempPiece.getPieceColor() == "W") && (tempPiece.getPieceType() == "H"))
					{
						pic.setIcon(new ImageIcon("whiteKnight.png"));
					}
					//if the piece is a black knight
					if ((tempPiece.getPieceColor() == "B") && (tempPiece.getPieceType() == "H"))
					{
						pic.setIcon(new ImageIcon("blackKnight.png"));
					}
					//if the piece is a white bishop
					if ((tempPiece.getPieceColor() == "W") && (tempPiece.getPieceType() == "B"))
					{
						pic.setIcon(new ImageIcon("whiteBishop.png"));
					}
					//if the piece is a black bishop
					if ((tempPiece.getPieceColor() == "B") && (tempPiece.getPieceType() == "B"))
					{
						pic.setIcon(new ImageIcon("blackBishop.png"));
					}
					//if the piece is a white queen
					if ((tempPiece.getPieceColor() == "W") && (tempPiece.getPieceType() == "Q"))
					{
						pic.setIcon(new ImageIcon("whiteQueen.png"));
					}
						//if the piece is a black queen
					if ((tempPiece.getPieceColor() == "B") && (tempPiece.getPieceType() == "Q"))
					{
						pic.setIcon(new ImageIcon("blackQueen.png"));
					}
					//if the piece is a white king
					if ((tempPiece.getPieceColor() == "W") && (tempPiece.getPieceType() == "K"))
					{
						pic.setIcon(new ImageIcon("whiteKing.png"));
					}
					//if the piece is a black king
					if ((tempPiece.getPieceColor() == "B") && (tempPiece.getPieceType() == "K"))
					{
						pic.setIcon(new ImageIcon("blackKing.png"));
					}


					//find pic dimensions
					Dimension size = pic.getPreferredSize();
					//place pic in proper spot
					pic.setBounds((j + 2) * 50, (i + 2) * 50, size.width, size.height);
					add(pic);
				}
			}
		}

		//method for adding coordinate over the board for debugging
		public void addCord(Board gameBoard)
		{
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					int x = gameBoard.getBoardSpace(j, i).getX();
					int y = gameBoard.getBoardSpace(j, i).getY();
					String label = (Integer.toString(x) + ", " + Integer.toString(y));
					JLabel cord = new JLabel(label);
					cord.setBounds((j + 2) * 50, (i + 2) * 50, 50, 50);
					add(cord);
				}
			}
		}
	}
}

