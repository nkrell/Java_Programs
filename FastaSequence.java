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
import java.io.BufferedReader;
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

	public static List<FastaSequence> readFastaFile(File file) throws Exception
	{
		List<FastaSequence> list = new ArrayList<FastaSequence>();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String nextLine = reader.readLine();
		return(list);
	}

	//public static void writeTableSummary( List<FastaSequence> list, File outputFile) throws Exception
	//{

	//}

}
//ExaMPLE
import java.io.*;
import java.util.Scanner;

public class ReadFastaFile {

    public static void main(String[] args) throws FileNotFoundException {

        boolean first = true;

        try (Scanner sc = new Scanner(new File("test.fasta"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.charAt(0) == '>') {
                    if (first)
                        first = false;
                    else
                        System.out.println();
                    System.out.printf("%s: ", line.substring(1));
                } else {
                    System.out.print(line);
                }
            }
        }
        System.out.println();
    }

}