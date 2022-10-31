package Chess;
import Chess.Peice;

public class Board
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