import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import java.util.List;
import java.util.Random;
import java.util.HashSet;
import java.util.Set;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;

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
		String tempString = sequence.toString();
		return(tempString);
	}

	@Override
	public String toString()
	{
		return(this.header + "\n" + this.sequence + "\n");
	}

	public float getGCRatio()
	{
		int counter = 0;

		for (char base : sequence.toCharArray())
		{
			if ((base == 'G')||(base == 'C'))
			{
				counter++;
			}
		}
		float counterF = counter;
		float lengthF = sequence.toString().length();
		return(counterF/lengthF);
	}

	public int countBase(char queryBase)
	{
		int counter = 0;
		for (char base : sequence.toCharArray())
		{
			if (base == queryBase)
			{
				counter++;
			}
		}
		return(counter);
	}

	public static List<FastaSequence> readFastaFile(String filename) throws Exception
	{
		//Set up variables needed for parsing
		BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
		List<FastaSequence> list = new ArrayList<FastaSequence>();
		String nextLine = reader.readLine();
		//temp var to speed up string concatenation
		StringBuffer buffer = new StringBuffer();
		while (nextLine != null)
		{
			FastaSequence fs = new FastaSequence("", "");
			list.add(fs);
			fs.header = nextLine;
			nextLine = reader.readLine();

			while ((nextLine != null) && (!nextLine.startsWith(">")))
			{
				//fs.sequence.append(nextLine);
				buffer.append(nextLine);
				nextLine = reader.readLine();
			}
			fs.sequence = buffer.toString();
			buffer.setLength(0);
		}
		reader.close();
		return(list);
	}

	public static void writeTableSummary( List<FastaSequence> list, File outputFile) throws Exception
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
		for (FastaSequence fs : list)
		{
			writer.write(fs.getHeader() + );
		}
	}

	public static void main(String[] args) throws Exception
	{
		List<FastaSequence> fastaList = FastaSequence.readFastaFile("Testing.fasta");
		for( FastaSequence fs : fastaList)
		{
			System.out.println(fs.getHeader());
        	System.out.println(fs.getSequence());
        	System.out.println(fs.getGCRatio());
		}
		File myFile = new File("Testing_Output.fasta");
		//writeTableSummary( fastaList,  myFile);
	}


}
