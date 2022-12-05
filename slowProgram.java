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
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class slowProgram extends JFrame
{	
	//declare gui elements
	private JTextField input = new JTextField();
	public JButton startButton = new JButton("Start");
	public JButton stopButton = new JButton("Stop");
	private JPanel start_stop = new JPanel();//remeber to add action listener <------------------------------------
	private JTextArea outputPane;
	private JScrollPane scrollableOutputPane;

	public slowProgram()
	{
		super("Slow Process Program");
		//setting up gui
		setSize(400,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//set up button sub-panel
		start_stop.setLayout(new BorderLayout());
		start_stop.add(startButton, BorderLayout.NORTH);
		start_stop.add(stopButton, BorderLayout.SOUTH);
		//set up scrolling pane
		outputPane = new JTextArea(20, 25);
		scrollableOutputPane = new JScrollPane(outputPane);
		scrollableOutputPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//set layout
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(start_stop, BorderLayout.EAST);
		getContentPane().add(scrollableOutputPane, BorderLayout.WEST);
		getContentPane().add(input, BorderLayout.SOUTH);
		//set attributes
		input.setColumns(15);
		startButton.addActionListener(new startButtonPress());
		//set visible
		setVisible(true);
		
	}

	public void textOutput(String output)
	{
		outputPane.append(output + "\n");
	}

	//the main method
	public static void main(String[] args)
	{
		slowProgram p1 = new slowProgram();
	}

	private class startButtonPress implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			textOutput("Enter a large integer: ");
		}
	}

	


}