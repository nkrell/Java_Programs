import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.event.*; 
import javax.swing.SwingUtilities;
import java.lang.reflect.InvocationTargetException;

public class slowProgram extends JFrame
{	
	//declare gui elements
	private JTextField input = new JTextField();
	private JTextField output = new JTextField();
	public JButton startButton = new JButton("Start");
	public JButton stopButton = new JButton("Stop");
	private JPanel start_stop = new JPanel();//remeber to add action listener <------------------------------------
	private JPanel input_output = new JPanel();//remeber to add action listener <----------------------------------

	public slowProgram()
	{
		super("Slow Process Program");
		//setting up gui
		setSize(300,200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//set up button sub-panel
		start_stop.setLayout(new BorderLayout());
		start_stop.add(startButton, BorderLayout.NORTH);
		start_stop.add(stopButton, BorderLayout.SOUTH);
		//set up text field sub-panel
		input_output.setLayout(new BorderLayout());
		input_output.add(input, BorderLayout.NORTH);
		input_output.add(output, BorderLayout.SOUTH);
		//set layout
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(input_output, BorderLayout.WEST);
		getContentPane().add(start_stop, BorderLayout.EAST);
		//set attributes
		input.setColumns(20);
		output.setColumns(20);
		//set visible
		setVisible(true);
		
	}

	public static void main(String[] args)
	{
		slowProgram p1 = new slowProgram();
	}

	//thread that finds primes in a given range
	private class NumberEngine implements Runnable
	{	
		//uses to keep track of multiple threads
		private final  int threadID;
		//the sub range for assigned to this specific thread
		private int subRange;

		//constructor
		public NumberEngine(int threadID, int subRange)
		{

		}

		//calculates all the primes in a given range
		public void run()
		{

		}

	}


	//thread for assigning number ranges to the prime caculation threads
	private class Cycler implements Runnable
	{	
		//this is just for conventions sake, there won't ever be more than one of these
		private final int threadID;
		//this give the upper bound beneath which all primes must be found
		private final int range;

		//constructor
		public Cycler(int threadID, int range)
		{

		}
		
		//calls several number engines and assigned them each a range of numbers taken from the total range
		public void run()
		{

		}
	}


}