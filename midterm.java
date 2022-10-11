package classExamples;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.HashSet;
import java.util.Set;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeMap;
import java.util.Map;
public class midterm
{
	public static int numGCs(String parameter)
	{
		int counter = 0;

		for (int i = 0; i < parameter.length(); i++)
		{
			if ((parameter.charAt(i) == 'G') || (parameter.charAt(i) == 'C'))
			{
				counter++;
			}
		}
		return(counter);
	}


	public static int bothPositive(int a, int b)
	{
		if ((a > 0) && (b > 0))
		{
			return(1);
		}
		return(0);
	}

	public static void skipEveryOther(String s)
	{
		for (int i = 0; i < s.length(); i = i + 2)
		{
			System.out.println(s.charAt(i));
		}
	}

	public static void reverseArray(float[] a)
 	{
       	float b[] = new float[a.length];
       	int countUp = 0;
       	for (int i = a.length -1; i >= 0; i--)
       	{
       		b[countUp] = a[i];
       		countUp++;
       	}
       	for (int i = 0; i < b.length; i++)
       	{
       		System.out.println(b[i]);
       	}
 	}

 	/*Returns a List containing all the Integers in inList that are even.  The order of Integers in the return List is the same as in inList*/
	public static List<Integer> getEvenNumbers( List<Integer> inList )
	{
		List<Integer> evenNumbers = new ArrayList<Integer>();
		for (int i = 0; i < inList.size(); i++)
		{
			if (inList.get(i) % 2 == 0)
			{
				evenNumbers.add(inList.get(i));
			}
		}
		return(evenNumbers);
	}
    	
	public int aMethod(int anInt)
	{
		return anInt;
	}









	public static void main(String[] args) throws Exception
	{
		String s = "Here is a string";
		Map<Character, Integer> map = new TreeMap<Character, Integer>();
		for (char c : s.toCharArray())
		{
			//int val = map.get(c);
			val++;
			map.put(c, val);
		}

		for (Character c : map.keySet())
		{
			System.out.println(c + " " + map.get(c));
		}
	}












}