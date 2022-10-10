package classExamples;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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










	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();
        StringBuffer buffer = new StringBuffer();	
        for( int x=0; x < 10000; x++)
        	buffer.append(x);
        String s = buffer.toString();
		float numSeconds = (System.currentTimeMillis() - startTime) / 1000f;
        System.out.println( numSeconds + " seconds"  );
        //System.out.println(s);
        float fArray[] = {1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f};
        reverseArray(fArray);
	}












}