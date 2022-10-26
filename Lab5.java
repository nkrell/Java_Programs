import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import Timer.Timer;
import javax.swing.JFrame;
//import Timer;


public class Lab5
{
	public static void main(String[] args)
	{
		JFrame aaQuiz = new JFrame("Amino Acid Quiz");
		aaQuiz.setSize(200,200);

	
		String startString = "";

		while(!(startString.equals("Y")))
		{
			System.out.println("Start timer? (Y/N)");
			startString = System.console().readLine().toUpperCase();
		}
		Timer timer1 = new Timer(1);
		new Thread(timer1).start();
	}


}

