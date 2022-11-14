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

public class Amino_Acid_Quiz_III extends JFrame
{
	//declare gui elemetns
	private JTextField aaQuestion = new JTextField();
	private JTextField aaAnswer = new JTextField();
	private JTextField aaTime = new JTextField();
	private JTextField aaScore = new JTextField();
	public JButton aaStartButton = new JButton("Start");
	private JButton aaEndButton = new JButton("End");
	private JPanel aaScore_Time = new JPanel();
	//declare Engine so it can be referecned befroe it is started
	private Engine engine1 = new Engine(1);
	//this boolean tracks if the game has been ended
	private boolean cancelled = false;
	//this boolean tracks if a potential answer has been entered and needs scoring
	//this was orignially meant to be chages by an action listener on the answer textfeild, whihc is why it isw declared here
	private boolean answerEntered = false;

	public Amino_Acid_Quiz_III()
	{
		super("Amino Acid Quiz");
		//setting up gui
		setSize(600,200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//set up sub-panel
		aaScore_Time.setLayout(new BorderLayout());
		aaScore_Time.add(aaTime, BorderLayout.EAST);
		aaScore_Time.add(aaScore, BorderLayout.WEST);
		//set layout
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(aaQuestion, BorderLayout.WEST);
		getContentPane().add(aaAnswer, BorderLayout.CENTER);
		getContentPane().add(aaScore_Time, BorderLayout.EAST);
		getContentPane().add(aaStartButton, BorderLayout.NORTH);
		getContentPane().add(aaEndButton, BorderLayout.SOUTH);
		//set gui element attributes
		aaStartButton.addActionListener(new buttonPress()); 
		aaEndButton.addActionListener(new gameEnd());
		aaQuestion.setColumns(20);
		aaAnswer.setColumns(20);
		aaTime.setColumns(10);
		aaScore.setColumns(10);
		aaQuestion.setText("Question");
		aaAnswer.setText("Answer");
		aaTime.setText("Time");
		aaScore.setText("Score");
		setVisible(true);
	}

	// the main method
	public static void main(String[] args)
	{
		Amino_Acid_Quiz_III aaQuiz = new Amino_Acid_Quiz_III();
	}

	//governs the actions taken when the start button is pressed
	private class buttonPress implements ActionListener 
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			new Thread(engine1).start();	
		}
		
	}

	//governs the actions taken when the end button is pressed
	private class gameEnd implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			cancelled = true;
		}
		
	}

	//updates the timer
	private void timerUpdate(long currentTime)
	{
		aaTime.setText("Time: " + currentTime);
	}

	private void timerFinalUpdate(String message)
	{
		aaTime.setText(message);
	}

	//udates the score
	private void scoreUpdate(int newScore)
	{
		aaScore.setText("Score: " + newScore);
	}

	//chages the test in aaQuestion
	private void setQeustion(String newQuestion)
	{
		aaQuestion.setText(newQuestion);
	}

	//get the text in aaQuestion
	private String getQuestion()
	{
		return(aaQuestion.getText());
	}

	//get the text in aaAnswer
	private String getAnswer()
	{
		return(aaAnswer.getText());
	}

	//accesses value of answerEntered
	private boolean checkAnswerEntered()
	{
		return(answerEntered);
	}

	//changes value of answerEntered
	private void changeAnswerEntered(boolean newValue)
	{
		answerEntered = newValue;
	}

	//engine runs the game
	private class Engine implements Runnable
	{
		private final int threadID;
		private long startTime;
		//constructor
		public Engine(int threadID)
		{
			this.threadID = threadID;
		}

		//starts the clock
		public void startClock()
		{
			startTime = System.currentTimeMillis();
		}

		//using to find what time should be displayed in the gui
		public long currentTime()
		{
			return((System.currentTimeMillis() - startTime) / 1000);
		}

		// tells if time is up
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

		//controls game flow and updates time from a separate thread
		public void run()
		{
			startClock();
			try {
				//string to hold the contents of aaAnswer, so it can be checked for changes
				String answerChange = getAnswer();
				//int to hold the score be fore it is updated
				int score = 0;
				//random to generate new questions
				Random rand = new Random();
				//int to receive random numbers
				int randPick = 0;
				//boolean to keep trakc of whether a new question is needed
				boolean nextQuestion = true;
				//full names for selecting question
				String[] FULL_NAMES = 
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

				//short names for checking answer
				String[] SHORT_NAMES = 
				{ 
					"A","R", "N", "D", "C", "Q", "E", 
					"G",  "H", "I", "L", "K", "M", "F", 
					"P", "S", "T", "W", "Y", "V" 
				};

				//main game engine loop: repeatedly runs all needed procceses and check at 250ms intervals
				while (true)
				{
					//update timer
					timerUpdate(currentTime());
					//check if time is up
					if (isTimeUp())
					{
						break;
					}

					//check if game has been cancelled
					if (cancelled)
					{
						break;
					}

					//check if answer has been entered
					if (!(answerChange.equals(getAnswer())))
					{
						changeAnswerEntered(true);
						answerChange = getAnswer();
					}

					//check if new question has to be chosen
					if (nextQuestion == true)
					{
						randPick = rand.nextInt(20);
						setQeustion(FULL_NAMES[randPick]);
						nextQuestion = false;
					}

					//check if an answer has been entered needs to be scored
					if (checkAnswerEntered() == true)
					{
						String answerHolder = getAnswer();
						if (answerHolder.equals(SHORT_NAMES[randPick]))
						{
							//increment score
							score++;
							//signal that a new question is needed
							nextQuestion = true;
							//signal that a new answer must be entered before scoring
							changeAnswerEntered(false);
						}
						else
						{
							//decrement score
							score--;
							//signal that a new question is needed
							nextQuestion = true;
							//signal that a new answer must be entered before scoring
							changeAnswerEntered(false);
						}
					}

					//update score
					scoreUpdate(score);
					//wait 
					Thread.sleep(250);
				}
				timerFinalUpdate("Time is up");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}



	}

}