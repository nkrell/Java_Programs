package	Chess;

public abstract class Peice
{
	//coordinates of the peice on the board
	public int xPos, yPos;

	//type of peice
	public String type;

	//constructor
	public Peice(int xPos, int yPos, String type)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.type = type;
	}

	//Move
	public void move(int xEnd, int yEnd)
	{

	}
	//Take
	public void

	//Be taken

	//Special Move (for knights and pawns)

	//Cast ray (to determine move legality, if a pecie can be taken, and for checkmate rules)
}