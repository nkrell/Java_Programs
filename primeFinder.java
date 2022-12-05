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
	private JTextArea outputPane2;
	private JScrollPane scrollableOutputPane2;
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
		setSize(1366,768); //---------for large monitors
		//setSize(1200, 600); // for smaller monitors
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//set up scrolling pane
		outputPane = new JTextArea(20, 25);
		outputPane2 = new JTextArea(20, 25);
		scrollableOutputPane = new JScrollPane(outputPane);
		scrollableOutputPane2 = new JScrollPane(outputPane2);
		scrollableOutputPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollableOutputPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//set up locations of elements
		numInput.setBounds(650, 70, 300, 20);
		threadInput.setBounds(650, 100, 300, 20);
		numLabel.setBounds(400, 70, 400, 20);
		threadLabel.setBounds(400, 100, 400, 20);
		startButton.setBounds(400, 0, 483, 60);
		stopButton.setBounds(883, 0, 483, 60);
		scrollableOutputPane.setBounds(0, 0, 400, 768);
		scrollableOutputPane2.setBounds(410, 200, 400, 400);
		//add elements
		add(numInput);
		add(threadInput);
		add(numLabel);
		add(threadLabel);
		add(scrollableOutputPane);
		add(scrollableOutputPane2);
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

	//outputs to main pane
	public synchronized void output(String output)
	{
		//SwingUtilities.invokeLater(

		outputPane.append(output + "\n");
	}

	//outputs to secondary pane
	public synchronized void output2(String output)
	{
		outputPane2.append(output + "\n");
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

	public void unCancel()
	{
		cancelled = false;
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
			//reset cancel
			unCancel();
			if ((isNumeric(checkNumber())) && (isNumeric(checkThreadNumber())))
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
		//track data about the progress of the engines
		private int[] primeCount;
		private boolean[] isDone;
		//track the progress of overall process
		private boolean done = false;

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

		//updates primeCount
		public synchronized void primeCountUpdate(int position, int update)
		{
			primeCount[position] = update;
		}

		//updates isDone
		public synchronized void isDoneUpdate(int position, boolean update)
		{
			isDone[position] = update;
		}

		//get primeCount
		public synchronized int[] getPrimeCount()
		{
			return(primeCount);
		}

		//get isDone
		public synchronized boolean[] getIsDone()
		{
			return(isDone);
		}

		//calls several number engines and assigns them each a range of numbers taken from the total range
		public void run()
		{
			//keep trakc of how many primes have been found 
			int sum = 0;
			//this is used to find a number that is divisilbe by the threadNumber
			int rangeModified = range;
			//this is the size of each subrange
			int subRange;
			//this keeps track of the growing range
			int upperRange;
			//kepps track of the lower range
			int lowerRange;
			//remembers the original upperRange
			int originalUpperRange = range;
			//create the number engines
			NumberEngine[] engines = new NumberEngine[threadNumber];

			//finish setting up isDone and primeCount now that threadNumber is finalized
			//don't need to use synchronized methods yet as no threads are spawned
			isDone = new boolean[threadNumber];
			primeCount = new int[threadNumber];
			//initilize isDone and primeCount
			for (int i = 0; i < threadNumber; i++)
			{
				isDone[i] = false;
				primeCount[i] = 0;
			}

			output("Cycler thread started");
			//start the clock
			startClock();
			//divide up the number range
			while ((rangeModified % threadNumber) != 0)
			{
				rangeModified++;
			}
			//output("Range modified: " + Integer.toString(rangeModified));
			subRange = rangeModified / threadNumber;
			//output("sub range : " + Integer.toString(subRange));
			upperRange = subRange;
			lowerRange = 0;
			for (int i = 0; i < threadNumber; i++)
			{
				//output("Range: " + Integer.toString(lowerRange) + " to " + Integer.toString(upperRange));
				//set up number engines
				engines[i] = new NumberEngine(i, lowerRange, upperRange);
				//make sure the upper range doesnt exceed the original upper range
				if (upperRange > originalUpperRange)
				{
					upperRange = originalUpperRange;
				}
				else 
				{
					upperRange = upperRange + subRange;
				}
				lowerRange = lowerRange + subRange;
			}

			

			//try allows the thread to sleep
			try
			{
				//start the number engines
				for (int i = 0; i < threadNumber; i++)
				{
					new Thread(engines[i]).start();
					output("Number engine thread started");
				}

				//begin cycling through assigned tasks until done or cancelled
				while (true)
				{
					//check if cancelled
					if (isCancelled())
					{
						break;
					}

					//check if done
					//make temp copy of isDone
					boolean[] isDoneCopy = getIsDone();
					//count how many threads are done
					int threadCount = 0;
					for (int i = 0; i < threadNumber; i++)
					{
						
						if (isDoneCopy[i])
						{
							threadCount++;
						}
						if (threadCount == threadNumber)
						{
							done = true;
						}
					}
					if (done)
					{
						break;
					}

					//print total prime count
					//make temp copy of primeCount
					int[] primeCountCopy = getPrimeCount();
					for (int i = 0; i < threadNumber; i++)
					{
						sum = sum + primeCountCopy[i];
					}
					output(Integer.toString(sum) + " primes found");
					sum = 0;

					//wait 
					Thread.sleep(250);
				}
				//say if thread stopped due to cancellation or not
				if (isCancelled())
				{
					output("Operation cancelled");
				}

				//say how long operation took
				output("Operation took: " + Long.toString(currentTime()) + " seconds");

				//count up primes one last time
				int[] primeCountCopy = getPrimeCount();
				for (int i = 0; i < threadNumber; i++)
				{
					sum = sum + primeCountCopy[i];
				}
				output(Integer.toString(sum) + " primes found");
				sum = 0;
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
		private final int lowerRange, upperRange;
		//tracks the number of primes found
		private int primeTotal = 0;

		//the constructor
		public NumberEngine(int threadID, int lowerRange, int upperRange)
		{
			this.threadID = threadID;
			this.lowerRange = lowerRange;
			this.upperRange = upperRange;
		}

		public void run()
		{
			//keeps track of which number is being analysed
			int currentNumber = lowerRange;
			//skip first few 
			if (currentNumber == 0)
			{
				output2(Integer.toString(2) + " is prime");
				output2(Integer.toString(3) + " is prime");
				output2(Integer.toString(5) + " is prime");
				output2(Integer.toString(7) + " is prime");
				primeTotal = primeTotal + 4;
				currentNumber = 11;
			}
			try 
			{
				//go through assigned tasks until done or cancelled
				while (true)
				{
					//check if done
					if (currentNumber > upperRange)
					{
						break;
					}

					//check if cancelled
					if (isCancelled())
					{
						break;
					}

					//check if number is odd
					if (currentNumber % 2 != 0)
					{
						//tracks if the number is prime
						boolean isPrime = true;

						//check if prime
						//for (int i = 3; i < currentNumber; i++)
						//{
							//if ((currentNumber % i) == 0)
							//{
								//isPrime = false;
								//break;
							//}
						//}
						if ((currentNumber % 3) == 0)
						{
							isPrime = false;
						}
						if ((currentNumber % 5) == 0)
						{
							isPrime = false;
						}
						if ((currentNumber % 7) == 0)
						{
							isPrime = false;
						}

						//update count here
						if (isPrime)
						{
							//increase number of found primes
							primeTotal++;
							output2(Integer.toString(currentNumber) + " is prime"); 
						}
					}
					//increase current number
					currentNumber++;

					//report number of primes found
					cycler1.primeCountUpdate(threadID, primeTotal);


				}
				cycler1.isDoneUpdate(threadID, true);
				//output("Thread done");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

	}


}