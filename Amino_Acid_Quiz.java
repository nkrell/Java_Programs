package scratch;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

class Amino_Acid_Quiz
{
	public static String[] FULL_NAMES = 
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

	public static String[] SHORT_NAMES = 
	{ "A","R", "N", "D", "C", "Q", "E", 
	"G",  "H", "I", "L", "K", "M", "F", 
	"P", "S", "T", "W", "Y", "V" 
	};

	
	public static void main(String[] args)
	{
		//Declare temporary variables
		String startString = " ";
		Random rand = new Random();
		int score = 0;
		//Determine is the user wants to play
		
		while(!(startString.equals("Y")))
		{

			System.out.println("Begin Amino Acid Quiz? (y/n)");
			System.out.println("\n");
			startString = System.console().readLine().toUpperCase();

		}
		//begin quiz
		//start timer
		long start = System.currentTimeMillis();
		long finish = 0;
		//track if the answer is correct
		boolean correct = true;
		boolean timeUp = false;
		//variable used to receive random integers
		int randPick = 0;
		
		while ((correct == true) && (timeUp == false))
		{
			randPick = rand.nextInt(20);
			System.out.println(FULL_NAMES[randPick]);
			//start string is reused here for simplicity
			startString = System.console().readLine().toUpperCase();
			//check if answer is right
			if (!(startString.equals(SHORT_NAMES[randPick]))) 
			{
				correct = false;
			}
			//check is time is up
			//correct is reused for this purpose
			finish = System.currentTimeMillis();
			if ((finish - start) >= 30000)
			{
				timeUp = true;
			}
			//output message if answer was right
			if (correct == true)
			{
				score++;
				System.out.println("Correct");
				System.out.println("Score: " + score);
				float timeElapsed = finish - start;
				timeElapsed = timeElapsed/1000;
				System.out.println("Time: " + timeElapsed + " seconds");
				System.out.println("\n");
			}
			else
			{
				System.out.println("Incorrect");
				System.out.println("Correct answer: " + SHORT_NAMES[randPick]);
				System.out.println("Score: " + score);
				float timeElapsed = finish - start;
				timeElapsed = timeElapsed/1000;
				System.out.println("Time: " + timeElapsed + " seconds");
				System.out.println("\n");
			}


		

		


		}
		if (timeUp == true)
			{
				System.out.println("Time is up");
				System.out.println("Correct answer: " + SHORT_NAMES[randPick]);
				System.out.println("Score: " + score);
				float timeElapsed = finish - start;
				timeElapsed = timeElapsed/1000;
				System.out.println("Time: " + timeElapsed + " seconds");
				System.out.println("\n");
			}

		finish = System.currentTimeMillis();
		float timeElapsed = finish - start;
		System.out.println("Time Elapsed:");
		System.out.println(timeElapsed/1000);
		
	}


}