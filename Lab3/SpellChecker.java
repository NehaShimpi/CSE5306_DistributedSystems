/*Name:-Neha Anil Shimpi
Student id:-1001827779
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/*Java file for checking the Spelling */

public class SpellChecker {
	
	@SuppressWarnings("resource")
	public static void loadWordsFromFile() throws IOException
	{/*words are loaded from the lexicon file when program loads*/
		String file = "D:/Neha_Documents/Study/Spring2021/lexicon.txt";
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String curLine;
        while ((curLine = bufferedReader.readLine()) != null){/*file input read*/
            set.add(curLine);
        } 
        bufferedReader.close();	
	}
	/*words are added to set . Set is used to prevent duplicate entries to go in file*/
	public static void addWordstoSet(String str)
	{
		if(!set.contains(str)){
			set.add(str);/*words added to set*/
			addWordstoFile();
		}    
	}
	/*words are added to lexicon file*/
	public static void addWordstoFile()
	{
		try {
			FileWriter myWriter = new FileWriter("D:/Neha_Documents/Study/Spring2021/lexicon.txt");
			for(String h:set){
				myWriter.write(h+"\n");
			}	
		     myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	}
	
	/*text in file is checked against the lexicon file words*/
	public static String checkSpelling(String str)
	{
		String strarr[] = str.split(" ");
		StringBuilder sb = new StringBuilder();
		
		for(String h : strarr){
			if(set.contains(h))/*if the file contains word just append*/{
				sb.append(h+" ");
			}
			else{
				sb.append("["+h+"]");/*if the file does not contain the word then append [] around the word*/
			}
		}
		
	  return sb.toString();
	}
	static Set<String> set = new HashSet<>();	
}
