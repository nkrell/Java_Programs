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
	public static void main(String[] args) 
	{
		System.out.println("N");
		System.out.println(dFinder(1, 1, 1, 0));
		System.out.println("NE");
		System.out.println(dFinder(1, 1, 2, 0));
		System.out.println("E");
		System.out.println(dFinder(1, 1, 2, 1));
		System.out.println("SE");
		System.out.println(dFinder(1, 1, 2, 2));
		System.out.println("S");
		System.out.println(dFinder(1, 1, 1, 2));
		System.out.println("SW");
		System.out.println(dFinder(1, 1, 0, 2));
		System.out.println("W");
		System.out.println(dFinder(1, 1, 0, 1));
		System.out.println("NW");
		System.out.println(dFinder(1, 1, 0, 0));
	}

	public static String dFinder(int startX, int startY, int endX, int endY)
	{
		String direction = "";
		//find the direction the piece is going
		
		if ((startX == endX) && (startY > endY))
		{
			direction = "N";
		}
		else if ((startX == endX) && (startY < endY)) 
		{
			direction = "S";
		}
		else if ((startY == endY) && (startX > endX)) 
		{
			direction = "W";
		}
		else if ((startY == endY) && (startX < endX)) 
		{
			direction = "E";
		}
		else if ((startX > endX) && (startY > endY)) 
		{
			direction = "NW";
		}
		else if ((startX < endX) && (startY < endY)) 
		{
			direction = "SE";
		}
		else if ((startX < endX) && (startY > endY)) 
		{
			direction = "NE";
		}
		else if ((startX > endX) && (startY < endY)) 
		{
			direction = "SW";
		}
		return(direction);
	}
}
