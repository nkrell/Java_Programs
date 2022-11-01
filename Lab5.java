//package Timer;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import Timer.Timer;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.*;  



public class Lab5 extends JFrame
{
	//declaring gui elements
	private JTextField aaQuestion = new JTextField();
	private JTextField aaAnswer = new JTextField();
	private JTextField aaTime = new JTextField();
	private JButton aaButton = new JButton("Start");
	static Timer timer1 = new Timer(1);
	//declaring class variables
	

	public Lab5()
	{
		super("Amino Acid Quiz");
		//setting up gui
		setSize(400,200);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//set layout
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(aaQuestion, BorderLayout.WEST);
		getContentPane().add(aaAnswer, BorderLayout.CENTER);
		getContentPane().add(aaTime, BorderLayout.EAST);
		getContentPane().add(aaButton, BorderLayout.SOUTH);
		//set gui element attributes
		aaButton.addActionListener(new Toggle());
		aaQuestion.setColumns(20);
		aaAnswer.setColumns(20);
		aaTime.setColumns(10);
		aaQuestion.setText("Question");
		aaAnswer.setText("Answer");
		aaTime.setText("Time");
		aaQuestion.setVisible(true);
	}
	public static void main(String[] args)
	{
		new Lab5();

		
	}

	public void aaTimeUpdate(long currentTime)
	{
		aaTime.setText("Time: " + currentTime);
	}

	public void runTime()
	{

	}

	public class Toggle implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			Timer timer1 = new Timer(1);
			new Thread(timer1).start();	
			while (!(timer1.isTimeUp()))
			{
				aaTimeUpdate(timer1.currentTime());
			
			}	
		}
	}


}

