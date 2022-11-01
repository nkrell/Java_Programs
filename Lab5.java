//package Timer;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import Timer.Timer;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;



public class Lab5 extends JFrame
{
	//decalsring gui elements
	//private JFrame aaQuiz = new JFrame("Amino Acid Quiz");
	private JTextField aaQuestion = new JTextField();
	private JTextField aaAnswer = new JTextField();
	private JTextField aaTime = new JTextField();

	public Lab5()
	{
		super("Amino Acid Quiz");
		//setting up gui
		setSize(200,200);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(aaQuestion, BorderLayout.WEST);
		getContentPane().add(aaAnswer, BorderLayout.CENTER);
		getContentPane().add(aaTime, BorderLayout.EAST);
		aaQuestion.setText("Question");
		aaAnswer.setText("Answer");
		aaTime.setText("Time");
		aaQuestion.setVisible(true);
	}
	public static void main(String[] args)
	{
		Timer timer1 = new Timer(1);
		new Thread(timer1).start();
		Lab5 window = new Lab5();
		while (!(timer1.isTimeUp()))
		{
			window.aaTimeUpdate(timer1.currentTime());
		}
	}

	public void aaTimeUpdate(long currentTime)
	{
		aaTime.setText("Time: " + currentTime);
	}

	public void runTime()
	{
		
	}


}

