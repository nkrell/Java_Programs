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
import javax.swing.*;
import java.awt.*;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



public class Chess
{


	public Chess()
	{
		//all the subclasses are initialized here
		
		GameEngine gameEngine = new GameEngine();
		new Thread(gameEngine).start();

	}

	//the main method
	public static void main(String[] args)
	{
		//this starts the program
		Chess newGame = new Chess();
	}

	private class GameEngine implements Runnable
	{
		Board board;
		RenderingEngine renderingEngine;
		//the constructor
		public GameEngine()
		{
			board = new Board();
			renderingEngine = new RenderingEngine();
			board.setBoard();
			board.printBoard();
			renderingEngine.rePaintPanel(board);
			renderingEngine.boardUpdate(board);
		}

		public void run()
		{	

			//allows thread to sleep
			try 
			{
				while(true)
				{
					//check if game over
					if (renderingEngine.gameOver())
					{
						break;
					}

					//check if move was made
					if (renderingEngine.moveMade())
					{
						//update board
						board = renderingEngine.returnBoard();
						renderingEngine.boardUpdate(board);
						board.printBoard();
						renderingEngine.rePaintPanel(board);
						//reset turn
						renderingEngine.moveMadeReset();
					}

					//make sure board is properly rendered
					renderingEngine.resetVisible();

					//wait
					Thread.sleep(250);
				}
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
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

		//method for moving peices on the board
		//movement will already have been validated by the time this is called
		public void move(int startX, int startY, int endX, int endY)
		{
			//get the peice that is being moved
			Piece movingPiece = gameBoard[startX][startY].getState();
			//empty the space it is moving from
			Piece emptySpace = new Piece("U", "U");
			gameBoard[startX][startY].setState(emptySpace);
			//move the piece to its new space
			gameBoard[endX][endY].setState(movingPiece);

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
		//tracks if the game is over or if it has been canceld
		boolean gameOver = false;
		//trakcs if a move was made
		boolean moveMade = false;
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
		JButton concedeButton = new JButton("Concede");
		JButton resetButton = new JButton("Reset");
		JScrollPane scrollPane;
		JTextArea outputPane;
		JPanel panel = new JPanel();
		//variable to keep track of whos turn it is
		String whosTurn = "White";
		//the constructor
		public RenderingEngine()
		{
			
			super("Chess");
			//setting up basic window attributes
			setSize(1920, 1080);
			setLocationRelativeTo(null);
			setLayout(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//set up gui elements
			outputPane = new JTextArea(10, 10);
			scrollPane = new JScrollPane(outputPane);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setBounds(525, 150, 200, 375);
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
			concedeButton.setBounds(525, 100, 100, 22);
			resetButton.setBounds(627, 100, 100, 22);
			panel.setBounds(0, 0, 500, 550); //--------------------------------------------------------Panel
			panel.setLayout(null);
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
			add(concedeButton);
			add(resetButton);
			add(scrollPane);
			add(panel);
			//set attributes (action listener)
			moveButton.addActionListener(new moveButtonPress());
			//set visible
			setVisible(true);
		}

		//defines the behavior of the move button
		private class moveButtonPress implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				//keeps trakc of whether input is good, makes the logic structure simpler
				boolean goodInput = true;
				//set input letter to be uppercase
				String startingLetter = startingInputLetter.getText().toUpperCase();
				String endingLetter = endingInputLetter.getText().toUpperCase();
				//get input numbers
				String startingNumberString = startingInputNumber.getText();
				String endingNumberString = endingInputNumber.getText();
				int startingNumber = 0;
				int endingNumber = 0;
				//check that letters are correct
				goodInput = checkLetter(startingLetter);
				goodInput = checkLetter(endingLetter);
				//give message that letters are wrong
				if (checkLetter(startingLetter) == false)
				{
					output("Invalid input in for letter in starting coordinates");
				}
				if (checkLetter(endingLetter) == false)
				{
					output("Invalid input in for letter in ending coordinates");
				}
				//check that numbers are correcnt then convert them to integers
				goodInput = checkNumber(startingNumberString);
				goodInput = checkNumber(endingNumberString);
				if (checkNumber(startingNumberString) && checkNumber(endingNumberString))
				{
					startingNumber = Integer.parseInt(startingNumberString);
					endingNumber = Integer.parseInt(endingNumberString);
				}
				//send error message if numbers are wrong
				if (checkNumber(startingNumberString) == false)
				{
					output("Invalid input in for number in starting coordinates");
				}
				if (checkNumber(endingNumberString) == false)
				{
					output("Invalid input in for number in ending coordinates");
				}
				//check turn
				//convert into my coordinate system
				int startX = hConvert(startingLetter);
				int startY = vConvert(startingNumber);
				int endX = hConvert(endingLetter);
				int endY = vConvert(endingNumber);
				output(Integer.toString(startX));
				output(Integer.toString(startY));
				output(Integer.toString(endX));
				output(Integer.toString(endY));
				if (board.getBoardState(startX, startY).getPieceColor().equals("W"))
				{
					if(whosTurn.equals("Black"))
					{
						goodInput = false;
						output("You are trying to move a white piece during black's turn");
					}
				}
				else if (board.getBoardState(startX, startY).getPieceColor().equals("B"))
				{
					if(whosTurn.equals("White"))
					{
						goodInput = false;
						output("You are trying to move a black piece during white's turn");
					}
				}
				//check if move is legal (coming soon)------------------------------------------------------------------------

				//modify board
				//this wont happen if the prerequisites above werent met
				//in the higher method, the renderingengines copy of board will always be passed, wether a move has been made or not
				if (goodInput)
				{
					board.move(startX, startY, endX, endY);
					moveMade = true;
					output("Move Made");
					//change turn
					if (whosTurn.equals("White"))
					{
						whosTurn = "Black";
						output("Black's Turn");
					}
					else  
					{
						whosTurn = "White";
						output("White's Turn");
					}
				}


			}

			//method to check letter input
			public boolean checkLetter(String letter)
			{
				String[] chessCordList = {"A", "B", "C", "D", "E", "F", "G", "H"};
				for (int i = 0; i < 8; i++)
				{
					if (letter.equals(chessCordList[i]))
					{
						return(true);
					}
				}
				return(false);
			}

			//method to check number input
			public boolean checkNumber(String number)
			{
				String[] numberList = {"1", "2", "3", "4", "5", "6", "7", "8"};
				for (int i = 0; i < 8; i++)
				{
					if (number.equals(numberList[i]))
					{
						return(true);
					}
				}
				return(false);
			}
		}

		//method to paint JPanel over the board
		public void rePaintPanel(Board newBoard)
		{
			//repaint whole panel using updated board
			panel.removeAll();
			addPieces(newBoard);
			addBoard(newBoard);
			addCordTiles();
			revalidate();
			repaint();
		}

		//method to check is a move has been made by the user
		public boolean moveMade()
		{
			return(moveMade);
		}

		//method to check if game is over or canceled
		public boolean gameOver()
		{
			return(gameOver);
		}

		//method to reset moveMade
		public void moveMadeReset()
		{
			this.moveMade = false;
			output("MoveMade reset");
		}

		//method to update RenderingEngine's copy of the board
		public void boardUpdate(Board newBoard)
		{
			this.board = newBoard;
			output("boardUpdate run");
		}

		//method to return RenderingEngines copy of the board
		public Board returnBoard()
		{
			output("returnBoard run");
			return(this.board);
		}

		
		//method to output messages to scrollpane
		public void output(String output)
		{
			outputPane.append(output + "\n");
		}

		//method to convert vertical coordinate system
		public int vConvert(int chessCord)
		{	
			int myCord = ((8 - chessCord));
			return(myCord);
		}

		//method to convert horizontal alphabetical coordinate system
		public int hConvert(String chessCord)
		{						  //   0    1    2    3    4    5    6    7
			String[] chessCordList = {"A", "B", "C", "D", "E", "F", "G", "H"};
			int myCord = 0;
			for (int i = 0; i < 8; i++)
			{
				if (chessCordList[i].equals(chessCord))
				{
					myCord = i;
				}
			}
			return(myCord);
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
			//output("reset run");
			//getContentPane().removeAll();
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
				panel.add(number);
				panel.add(pic);
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
				panel.add(letter);
				panel.add(pic);
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
					if (gameBoard.getBoardColor(j, i).equals("W"))
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
					panel.add(pic);
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
					if ((tempPiece.getPieceColor().equals("U")) && (tempPiece.getPieceType().equals("U")))
					{
						pic.setIcon(new ImageIcon("blankSpace.png"));
					}
					//if the piece is a white pawn
					if ((tempPiece.getPieceColor().equals("W")) && (tempPiece.getPieceType().equals("P")))
					{
						pic.setIcon(new ImageIcon("whitePawn.png"));
					}
					//if the peice is a black pawn
					if ((tempPiece.getPieceColor().equals("B")) && (tempPiece.getPieceType().equals("P")))
					{
						pic.setIcon(new ImageIcon("blackPawn.png"));
					}
					//if the piece is a black rook
					if ((tempPiece.getPieceColor().equals("B")) && (tempPiece.getPieceType().equals("R")))
					{
						pic.setIcon(new ImageIcon("blackRook.png"));
					}
					//if the piece is a white rook
					if ((tempPiece.getPieceColor().equals("W")) && (tempPiece.getPieceType().equals("R")))
					{
						pic.setIcon(new ImageIcon("whiteRook.png"));
					}
					//if the piece is a white knight
					if ((tempPiece.getPieceColor().equals("W")) && (tempPiece.getPieceType().equals("H")))
					{
						pic.setIcon(new ImageIcon("whiteKnight.png"));
					}
					//if the piece is a black knight
					if ((tempPiece.getPieceColor().equals("B")) && (tempPiece.getPieceType().equals("H")))
					{
						pic.setIcon(new ImageIcon("blackKnight.png"));
					}
					//if the piece is a white bishop
					if ((tempPiece.getPieceColor().equals("W")) && (tempPiece.getPieceType().equals("B")))
					{
						pic.setIcon(new ImageIcon("whiteBishop.png"));
					}
					//if the piece is a black bishop
					if ((tempPiece.getPieceColor().equals("B")) && (tempPiece.getPieceType().equals("B")))
					{
						pic.setIcon(new ImageIcon("blackBishop.png"));
					}
					//if the piece is a white queen
					if ((tempPiece.getPieceColor().equals("W")) && (tempPiece.getPieceType().equals("Q")))
					{
						pic.setIcon(new ImageIcon("whiteQueen.png"));
					}
						//if the piece is a black queen
					if ((tempPiece.getPieceColor().equals("B")) && (tempPiece.getPieceType().equals("Q")))
					{
						pic.setIcon(new ImageIcon("blackQueen.png"));
					}
					//if the piece is a white king
					if ((tempPiece.getPieceColor().equals("W")) && (tempPiece.getPieceType().equals("K")))
					{
						pic.setIcon(new ImageIcon("whiteKing.png"));
					}
					//if the piece is a black king
					if ((tempPiece.getPieceColor().equals("B")) && (tempPiece.getPieceType().equals("K")))
					{
						pic.setIcon(new ImageIcon("blackKing.png"));
					}


					//find pic dimensions
					Dimension size = pic.getPreferredSize();
					//place pic in proper spot
					pic.setBounds((j + 2) * 50, (i + 2) * 50, size.width, size.height);
					panel.add(pic);
				}
			}
		}

		//method for adding coordinates over the board for debugging
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

