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

public class FastaSequence
{
	private String header;
	private String sequence;

	public FastaSequence(String header, String sequence)
	{
		this.header = header;
		this.sequence = sequence;
	}
	public String getHeader()
	{
		return this.header;
	}

	public String getSequence()
	{
		return this.sequence;
	}

	public float getGCRation()
	{
		int counter;

		for (char base : sequence.toCharArray())
		{
			if ((base == 'G')||(base == 'C'))
			{
				counter++;
			}
		}
		return(Float(counter/sequence.length()));
	}

	public static List<FastaSequence> readFastaFile(String filepath) throws Exception
	{

	}

	//public static void writeTableSummary( List<FastaSequence> list, File outputFile) throws Exception
	//{

	//}

}