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


public class primeFinder extends JFrame
{
	//declare cycler thread
	private Cycler cycler1;
	//declare gui elements
	private JTextArea outputPane;
	private JScrollPane scrollableOutputPane;
	private JButton startButton = new JButton("Start");
	private JButton stopButton = new JButton("Cancel");
	private JLabel threadLabel = new JLabel("Enter the number of threads : ");
	private JLabel numLabel = new JLabel("Enter a large integer: ");
	private JTextField numInput = new JTextField();
	private JTextField threadInput = new JTextField();
	private boolean cancelled = false;
	//program supports up to 16 threads
	//private numberEngine engine1, engine2, engine3, engine4, engine5, engine6, engine7, engine8, engine9, engine10, engine11,
						//engine12, engine13, engine14, engine15, engine16;

	public primeFinder()
	{
		super("Prime Finder");
		//setting up gui
		//setSize(1366,768); ---------for large monitors
		setSize(1200, 600); // for smaller monitors
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//set up scrolling pane
		outputPane = new JTextArea(20, 25);
		scrollableOutputPane = new JScrollPane(outputPane);
		scrollableOutputPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//set up locations of elements
		numInput.setBounds(650, 70, 300, 20);
		threadInput.setBounds(650, 100, 300, 20);
		numLabel.setBounds(400, 70, 400, 20);
		threadLabel.setBounds(400, 100, 400, 20);
		startButton.setBounds(400, 0, 483, 60);
		stopButton.setBounds(883, 0, 483, 60);
		scrollableOutputPane.setBounds(0, 0, 400, 768);
		//add elements
		add(numInput);
		add(threadInput);
		add(numLabel);
		add(threadLabel);
		add(scrollableOutputPane);
		add(startButton);
		add(stopButton);
		//set attributes
		startButton.addActionListener(new startButtonPress());
		stopButton.addActionListener(new stopButtonPress());
		//set visible
		setVisible(true);
		


	}

	//getter and setter methods for main class
	public int getNumber()
	{
		int i = Integer.parseInt(numInput.getText());  
		return(i);
	}

	public int getThreadNumber()
	{
		int i = Integer.parseInt(threadInput.getText());  
		return(i);
	}

	public void output(String output)
	{
		outputPane.append(output + "\n");
	}

	public String checkNumber()
	{
		return(numInput.getText());
	}

	public String checkThreadNumber()
	{
		return(threadInput.getText());
	}

	public boolean isCancelled()
	{
		return(cancelled);
	}

	public void cancel()
	{
		cancelled = true;
	}

	//Utility methods
	public static boolean isNumeric(String strNum) 
	{
    	if (strNum == null) 
    	{
        	return false;
    	}
    	try 
    	{
        	double d = Double.parseDouble(strNum);
    	} 
    	catch (NumberFormatException nfe) 
    	{
        	return false;
    	}

    	return true;
	}


	public static void main(String[] args)
	{
		primeFinder p1 = new primeFinder();
	}

	private class startButtonPress implements ActionListener
	{
		int number, threadNumber = 0;
		public void actionPerformed(ActionEvent arg0)
		{
			//my computer has 16 threads, and its rare that a computer has more
			if ((isNumeric(checkNumber())) && (isNumeric(checkThreadNumber())) && (checkThreadNumber <= 16))
			{
				//retreive number and thread number
				number = getNumber();
				threadNumber = getThreadNumber();
				//Start cycler thread
				cycler1 = new Cycler(0, threadNumber, number);
				new Thread(cycler1).start();
			}
			else 
			{
				output("Invalid input");
			}
			
			
		}
	}

	private class stopButtonPress implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			cancel();
		}
	}

	//thread for assigning number ranges to the prime caculation threads
	private class Cycler implements Runnable
	{
		//this is just for conventions sake, there won't ever be more than one of these
		private final int threadID;
		//this give the upper bound beneath which all primes must be found
		private final int range;
		//tells cycler how many number engines to spawn
		private final int threadNumber;
		//keeps track of time
		private long startTime;

		//constructor
		public Cycler(int threadID, int threadNumber, int range)
		{
			this.threadID = threadID;
			this.threadNumber = threadNumber;
			this.range = range;
		}

		//starts timer
		public void startClock()
		{
			startTime = System.currentTimeMillis();
		}

		//tells current time
		public long currentTime()
		{
			return((System.currentTimeMillis() - startTime) / 1000);
		}

		//calls several number engines and assigns them each a range of numbers taken from the total range
		public void run()
		{

			//this is used to find a number that is divisilbe by the threadNumber
			int rangeModified = range;
			//this is the size each subrange
			int subRange;
			//this keeps track of the growing range
			int growingRange;
			//create the number engines
			NumberEngine[] engines = new NumberEngine[threadNumber];

			output("Thread started");
			//start the clock
			startClock();
			//divide up the number range
			while ((rangeModified % threadNumber) != 0)
			{
				rangeModified++;
			}
			output("Range modified: " + Integer.toString(rangeModified));
			subRange = rangeModified / threadNumber;
			output("sub range : " + Integer.toString(subRange));
			growingRange = subRange;
			for (int i = 0; i < threadNumber; i++)
			{
				output("Range: " + Integer.toString(growingRange));
				growingRange = growingRange + subRange;
				//set up number engines
				engines[i] = new NumberEngine(i, growingRange);
			}

			

			//try allows the thread to sleep
			try
			{

			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	//this class actually performs the calculations
	private class NumberEngine implements Runnable
	{
		//to kepp track of the threads
		private final int threadID;
		//the range within which all primes must be found
		private final int range;

		//the constructor
		public NumberEngine(int threadID, int range)
		{
			this.threadID = threadID;
			this.range = range;
		}

		public void run()
		{
			//try allows the thread to sleep
			try
			{
				while (true)
				{
					
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

	}


}