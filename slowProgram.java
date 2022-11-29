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
	private JPanel start_stop = new JPanel();
	private JPanel input_output = new JPanel();

	public slowProgram()
	{
		super("Slow Process Program");
		//setting up gui
		setSize(600,200);
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
		//set visible
		setVisible(true);
		
	}

	public static void main(String[] args)
	{
		slowProgram p1 = new slowProgram();
	}
}