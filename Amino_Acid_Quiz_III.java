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
	private JTextField aaQuestion = new JTextField();
	private JTextField aaAnswer = new JTextField();
	private JTextField aaTime = new JTextField();
	private JTextField aaScore = new JTextField();
	private JButton aaStartButton = new JButton("Start");
	private JButton aaEndButton = new JButton("End");
	private JPanel aaScore_Time = new JPanel();
	public Amino_Acid_Quiz_III()
	{
		super("Amino Acid Quiz");
		//setting up gui
		setSize(800,200);
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
		//aaButton.addActionListener(new Toggle()); <----------------change later
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

	public static void main(String[] args)
	{
		Amino_Acid_Quiz_III aaQuiz = new Amino_Acid_Quiz_III();
	}

}