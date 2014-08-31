package twitterJ.twiterJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

public class UniqueLineReader extends BufferedReader {
    Set<String> lines = new HashSet<String>();
public static int count;
    public UniqueLineReader(Reader arg0) {
        super(arg0);
    }

    @Override
    public String readLine() throws IOException {
        String uniqueLine;
        while (lines.add(uniqueLine = super.readLine()) == false); //read until encountering a unique line
            return uniqueLine;
    }

    public static void main(String args[]) throws IOException {
        
    	
    	
    	
    	String filename="C:/Users/krishna/Desktop/sample1.txt";
		BufferedReader reader = new BufferedReader(new FileReader(filename));
        Set<String> lines = new HashSet<String>(10000); // maybe should be bigger
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (String unique : lines) {
            writer.write(unique);
            writer.newLine();
        }
        writer.close();
    	
    	
    	/*try {
            // Open the file that is the first
            // command line parameter
        	
        	@SuppressWarnings("resource")
			PrintWriter output = new PrintWriter(new FileWriter("C:/Users/krishna/Desktop/tweets.txt",true));
        	
        	
        	 FileInputStream fstreamforoutputfile = new FileInputStream(
                     "C:/Users/krishna/Desktop/tweets.txt");
        	
        	 UniqueLineReader brout = new UniqueLineReader(new InputStreamReader(fstreamforoutputfile));
        	 
            FileInputStream fstream = new FileInputStream(
                    "C:/Users/krishna/Desktop/sample1.txt");
            UniqueLineReader br = new UniqueLineReader(new InputStreamReader(fstream));
            String strLine;
            
            String cheakLine;
            // Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
            	
            	
            	while((cheakLine=brout.readLine())!=null)
            	{
            		if(strLine!=cheakLine)
            		{
            			try
                		{
                        	output.printf("%s\r\n",strLine);
                        	count++;

                		} 
                		catch (Exception e) {}
                    	
            		}
            		
            	}
            	
            	
            	
                    System.out.println(strLine);
            }
            // Close the input stream
           
            System.out.println("count="+count);
            fstream.close();
            output.close();
            fstreamforoutputfile.close();
            
        } catch (Exception e) {// Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
*/
    }
}