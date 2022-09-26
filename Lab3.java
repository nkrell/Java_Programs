package classExamples;
//check to see that alphabet and weights have the same length
//check to see that length >= 0
//check to see that the sum of weights is within round-off error of 1
//Return a random string of characters sampled with replacement from alphabet[] 
//with relative frequencies set by weights[]
//If your code fails any of the pre-conditions raise an error with throw new Exception(“error message”);



public class Lab3
{
	public static String generateRandomSequence(char[] alphabet, float[] weights, int length) 
	throws Exception
	{
		//check to see that alphabet and weights have the same length
		if (alphabet.size() != weights.size())
		{
			throw new Exception("Error: Alphabet list and Weights list must have the same length");
		}

		//check to see that the length >= 0
		// only need to check alphabet since we know they are equal at this point
		if (alphabet.size == 0)
		{
			throw new Exception("Error: Lists cannot have a length of zero");
		}

		//check to see that the sum of weights is within round-off error of 1
		// make variable to hold total
		float sum = 0;
		for (int i = 0; i < weights.size(); i++ ) 
		{
			sum = sum + weights[i];
		}
		if (sum > 1)
		{
			throw new Exception("Error: Weights cannot sum up to greater than 1")
		}

		//Return a random string of characters sampled with replacement from alphabet[]
		

		return null;
	}
	
	
	public static void main(String[] args) throws Exception
	{
		float[] dnaWeights = { .3f, .3f, .2f, .2f };
		char[] dnaChars = { 'A', 'C', 'G', 'T'  };
		
		// a random DNA 30 mer
		System.out.println(generateRandomSequence(dnaChars, dnaWeights,30));
		
		// background rate of residues from https://www.science.org/doi/abs/10.1126/science.286.5438.295
		float proteinBackground[] =
			{0.072658f, 0.024692f, 0.050007f, 0.061087f,
		        0.041774f, 0.071589f, 0.023392f, 0.052691f, 0.063923f,
		        0.089093f, 0.023150f, 0.042931f, 0.052228f, 0.039871f,
		        0.052012f, 0.073087f, 0.055606f, 0.063321f, 0.012720f,
		        0.032955f}; 
			

		char[] proteinResidues = 
				new char[] { 'A', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T',
							 'V', 'W', 'Y' };
		
		// a random protein with 30 residues
		System.out.println(generateRandomSequence(proteinResidues, proteinBackground, 30));
		
	}
}