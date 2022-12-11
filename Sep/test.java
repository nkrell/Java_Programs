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
import javax.swing.*;
import java.awt.*;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class test extends JFrame
{
	JPanel panel = new JPanel();

	public test()
	{
		super("test");
		setSize(1920, 1080);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel.setBounds(0, 0, 400, 400);
		add(panel);

		JLabel pic = new JLabel();
		pic.setIcon(new ImageIcon("whiteSpace.png"));
		Dimension size = pic.getPreferredSize();
		pic.setBounds(0, 0, size.width, size.height);
		panel.setLayout(null);
		panel.add(pic);

		setVisible(true);
	}

	public static void main(String[] args) 
	{
		test test1 = new test();
	}
}

