package scratch;

import java.util.Random;

class Lab1
{
	public static void main(String[] args)
	{
		//declare and initilize string to hold each base and string to hold the growing kmer
		String three_mer = "";
		String base = "";
		//triple a tracker
		int aaa = 0;
		//run 1000 times
		for(int x=0; x < 1000; x++)
		{

		
			//run three times to make a 3mer
			for(int y=0; y < 3; y++)
			{
				//declare and initilize random between 0 and 3
				Random rand = new Random();
				int f = rand.nextInt(4);
			
				if (f == 0)
				{
					base = "A";
				}
				else if (f == 1) 
				{
					base = "T";	
				}
				else if (f == 2) 
				{
					base = "C";	
				}
				else if (f == 3) 
				{
					base = "G";	
				}
				three_mer = three_mer + base;
				
			}
			System.out.println(three_mer);
			if(three_mer.equals("AAA"))
			{
				aaa++;
			}
			three_mer = "";
		}
		System.out.println("AAA count: ");
		System.out.println(aaa);

	}
}
