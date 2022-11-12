import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.*; 
import javax.swing.SwingUtilities;
import java.lang.reflect.InvocationTargetException;



public class Amino_Acid_Quiz_II
{
	private final FrameSetter frame;
	public Amino_Acid_Quiz_II()
	{
		frame = new FrameSetter();
	}


	public static void main(String[] args)
	{
		Amino_Acid_Quiz_II aaQuiz = new Amino_Acid_Quiz_II();
	}

	private class Toggle implements ActionListener
	{
		//declare fullnames and shortnames to allow for questions and answers
		public String[] FULL_NAMES = 
		{
			"alanine","arginine", "asparagine", 
			"aspartic acid", "cysteine",
			"glutamine",  "glutamic acid",
			"glycine" ,"histidine","isoleucine",
			"leucine",  "lysine", "methionine", 
			"phenylalanine", "proline", 
			"serine","threonine","tryptophan", 
			"tyrosine", "valine"
		};
		public String[] SHORT_NAMES = 
		{ 
			"A","R", "N", "D", "C", "Q", "E", 
			"G",  "H", "I", "L", "K", "M", "F", 
			"P", "S", "T", "W", "Y", "V" 
		};

		private boolean start = true;
		public Timer timer1;
		public void actionPerformed(ActionEvent arg0)
		{
			
			if (start == true)
			{
				timer1 = new Timer(1);
				new Thread(timer1).start();
				//make random number generator to choose question
				Random rand = new Random();
				int randPick = 0;
				
				
				

			}
			else if (start == false) 
			{
				timer1.stop();
			}
			start = !(start);

	
		}

	}

	private class FrameSetter extends JFrame
	{
		//declaring gui elements
		private JTextField aaQuestion = new JTextField();
		private JTextField aaAnswer = new JTextField();
		private JTextField aaTime = new JTextField();
		private JButton aaButton = new JButton("Start");

		public FrameSetter()
		{
			super("Amino Acid Quiz");
			//setting up gui
			setSize(400,200);
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
			setVisible(true);
		}

		public void timeUpdate(long currentTime)
		{
			aaTime.setText("Time: " + currentTime);
		}

		public void timeEnd(String message)
		{
			aaTime.setText(message);
		}

		public void questionUpdate(String question)
		{
			aaQuestion.setText(question);
		}

		public String getAnswer()
		{
			return(aaAnswer.getText());
		}

		public String getQuestion()
		{
			return(aaQuestion.getText());
		}


	}

	private class Timer implements Runnable
	{
		private final int threadID;
		private long startTime;
		private boolean stop = false;

		public Timer(int threadID)
		{
			this.threadID = threadID;
			this.startTime = System.currentTimeMillis();
		}

		public long currentTime()
		{
			return((System.currentTimeMillis() - startTime) / 1000);
		}

		public boolean isTimeUp()
		{
			if ((System.currentTimeMillis() - startTime) >= 30000)
			{
				return(true);
			}
			else
			{
				return(false);
			}
		}

		public void run() 
		{
			while (!(isTimeUp()) && !(stop)) 
			{
				
				try 
				{
					SwingUtilities.invokeAndWait(new Runnable()
					{
						public void run()
						{
							frame.timeUpdate(currentTime());
						}
					
					
						
					
					});
				}
				catch (InvocationTargetException e)
				{
					e.printStackTrace();
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				
			}
			frame.timeEnd("Time is Up");
		}

		public void stop()
		{
			stop = true;
		}
	}
}