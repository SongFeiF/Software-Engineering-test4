package test4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class SortResult 
{
	ArrayList<String> Eword = new ArrayList<String>() {{add("the");add("an");add("A");add("About");
    add("is");add("Being");add("Be");add("You");add("they");add("here");add("your");add("are");add("t");add("then");
    add("had");add("there");add("would");add("but");add("But");add("so");add("been");add("s");
    add("and");add("The");add("it");add("It");add("its");add("Its");add("itself");add("he");
    add("He");add("him");add("Him");add("himself");add("she");
	add("her");add("have");add("Then");add("this");add("do");add("did");add("his");add("be");
	add("were");add("was");add("we");add("you");add("We");add("herself");add("I");add("me");
	add("my");add("myself");add("We");add("us");
	add("our");add("or");add("not");add("no");add("a");add("Our");add("ourselves");add("off");
	add("of");add("for");add("from");add("about");add("in");
	add("on");add("in");add("In");add("by");add("as");add("at");add("with");add("over");add("into");add("to");
	add("up");add("down");add("If");add("Since");add("since");add("Though");add("Although");add("that");
	add("where");add("what");add("who");add("when");add("whom");add("which");add("because");add("after");add("before");}};  

	//按字典输出
	public Map Sort(Map<String, Integer> Map,int linenumber,int charnumber)
	{  
		
	    Set<Entry<String,Integer>> map = Map.entrySet(); 
	    int i;
		Map<String, Integer> map1=new LinkedHashMap<String, Integer>();
        for(Entry<String,Integer> m: map) 
        {  
        	for(i=0;i<Eword.size();i++)
        	{
        		if(Eword.get(i).equals(m.getKey()))
        		{
        			break;
        		}
        	}
        	if(i>=Eword.size())
        	{
        		map1.put(m.getKey(), m.getValue());  
        	}
        }  
        Set<Entry<String,Integer>> Map1=map1.entrySet();  
        LinkedList<Entry<String, Integer>> List = new LinkedList<Entry<String,Integer>>(Map1);
        //排序
        Collections.sort(List, new Comparator<Entry<String,Integer>>() {  
            @Override  
            public int compare(Entry<String, Integer> wk1,  Entry<String, Integer> wk2) {
                return wk1.getKey().compareTo(wk2.getKey());  
            }  
        }); 
        
        File file = new File("src/result.txt");
        try {
        	if(file.exists()) {
        		file.createNewFile();
        	}
        	FileWriter ref = new FileWriter(file.getAbsoluteFile());
        	ref.write("总行数："+linenumber+"\t字符数："+charnumber+"\n");
        	for(Entry<String,Integer> entry : map1.entrySet()) {
        		ref.write(entry.getKey()+":"+entry.getValue()+"\n");
        	}
        	ref.close();
        }catch(IOException e) {
        	e.printStackTrace();
        }
       return map1;
       
	    /*ArrayList<Entry<String, Integer>> List = new ArrayList<Entry<String,Integer>>(map);
	    int i,j;
	    for(j=0;j<List.size();j++) 
	    {  
	    	for(i=0;i<Eword.size();i++)
	    	{
	    		if(Eword.get(i).equals(List.get(j).getKey()))
	            {
	    			List.remove(j);
	            	break;            		
	            }
	    	}
	    }        
        	//排序
        Collections.sort(List, new Comparator<Entry<String,Integer>>() 
        {  
            @Override  
            public int compare(Entry<String, Integer> wk1,  Entry<String, Integer> wk2) 
            {
                return wk1.getKey().compareTo(wk2.getKey());  
            }  
        }); 
        
        
        Map<String,Integer> MapNew = new LinkedHashMap<String, Integer>();  
        for(Entry<String,Integer> entry: List) 
        {  
            MapNew.put(entry.getKey(), entry.getValue());  
        } 
        File file = new File("src/result.txt");
        try 
        {
        	if(file.exists()) 
        	{
        		file.createNewFile();
        	}
        	FileWriter ref = new FileWriter(file.getAbsoluteFile());
        	ref.write("总行数："+linenumber+"\t字符数："+charnumber+"\n");
        	for(Entry<String,Integer> entry : MapNew.entrySet()) 
        	{
        		ref.write(entry.getKey()+":"+entry.getValue()+"\n");
        	}
        	ref.close();
        }
        catch(IOException e) 
        {
        	e.printStackTrace();
        }
        
        return MapNew;*/
	} 
}
