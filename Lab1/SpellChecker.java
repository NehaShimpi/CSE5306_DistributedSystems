import java.util.Arrays;
import java.util.List;

public class SpellChecker {
	
	public static String checkSpelling(String str)
	{
		String strarr[] = str.split(" ");
		StringBuilder sb = new StringBuilder();
		
		for(String h : strarr)
		{
			if(list.contains(h.toLowerCase()))
			{
				sb.append(h+" ");
				
			}
			else
			{
				sb.append("["+h+"]");
			}
		}
		
	  return sb.toString();
	}

	static List<String> list = Arrays.asList("the","quick","brown","fox","jumps","over","the","lazy","dog","dont","cry",
			                          "over","spilled","milk","he","has","got","a","chip","on","his","shoulder");
	
}
