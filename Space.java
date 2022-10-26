package Chess;


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