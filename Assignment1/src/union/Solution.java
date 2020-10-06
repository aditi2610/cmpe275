package union;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solution {

	
		public ArrayList<String> popultaeNfeatures(int numFeatures, int topFeatures, List<String> possibleFeature,
				int numFeReq, List<String> featureRequest){
			//create a map of all the possible features
			// i will then iterate over the feature request and see if the string contains 
			
		ArrayList<String> result = new ArrayList<String>();	
		HashMap<String, Integer> possibleFea = new HashMap<String, Integer>();
		for(String s: possibleFeature) {
			possibleFea.put(s,0);
		}
		
		for(String t: featureRequest) {
			String[] temp = t.split(" ");
			for(int i =0; i<temp.length; i++) {
				if(possibleFea.containsKey(temp[i])) {
					possibleFea.put(temp[i], possibleFea.get(temp[i])+1);
				}
			}
		}
	  
		Map<String, Integer> hm1 = sortByValue(possibleFea); 
		
		Iterator<Map.Entry<String, Integer>> itr = hm1.entrySet().iterator(); 
		int i =0;
		while(itr.hasNext() && i<topFeatures) {
			 Map.Entry<String, Integer> entry = itr.next(); 
			 result.add(entry.getKey());
			 i++;
		}
		return result;
				
		}
		public static HashMap<String, Integer> sortByValue(Map<String, Integer> map) 
	    { 
	        // Create a list from elements of HashMap 
	        List<Map.Entry<String, Integer> > list = 
	               new LinkedList<Map.Entry<String, Integer> >(map.entrySet()); 
	  
	        // Sort the list 
	        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() { 
	            public int compare(Map.Entry<String, Integer> o1,  
	                               Map.Entry<String, Integer> o2) 
	            { 
	            	if(o1.getValue() < o2.getValue())
	            		return 1;
	            	else if(o1.getValue() > o2.getValue())
	            		return -1;
	            	else 
	            		return o1.getKey().compareTo(o2.getKey());
	      
	            } 
	        }); 
	          
	        // put data from sorted list to hashmap  
	        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>(); 
	        for (Map.Entry<String, Integer> aa : list) { 
	            temp.put(aa.getKey(), aa.getValue()); 
	        } 
	        return temp; 
	    } 
		
		
		public static void main(String args[]) {
			Solution s = new Solution();
			ArrayList<String> result = new ArrayList<String>();	
			result.add("storage");
			result.add("battery");
			result.add("hover");
			result.add("Alexa");
			ArrayList<String> fetire = new ArrayList<String>();
			fetire.add("I wish my kindle has more storage");
			fetire.add("I wish battery was better");
			List<String> rs= s.popultaeNfeatures(6, 2, result, 7, fetire);
			for(String s1: rs)
				System.out.println(s1);
		}
}
