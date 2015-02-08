package com.mycompany.scrambled_eggs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author colin
 *
 */
public class DefragmentText 
{
	/**
	 * 
	 * @param args
	 */
    public static void main( String[] args )
    {
    	
        try(BufferedReader reader = new BufferedReader(new FileReader(args[0]))){
        	String fragmentProblem;
        	while((fragmentProblem = reader.readLine()) != null){
        		System.out.println(reassemble(fragmentProblem));
        	}
        } 
        catch(Exception e){
        	e.printStackTrace();
        }
        
    }
    
 
    /**
     * Reassembles the fragmented text
     * 
     * @param fragmentProblem the fragmented text
     * @return the reassembled defragmented text
     */
    public static String reassemble(String fragmentProblem){
    	
    	String[] fragments = fragmentProblem.split(";");
    	
    	List<String> fragmentList = new ArrayList<String>(Arrays.asList(fragments));
	    
    	Collections.sort(fragmentList, new Comparator<String>() {
	     
	    	/*
	    	 * (non-Javadoc)
	    	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	    	 */
	    	@Override
		    public int compare(String o1, String o2) {
		          return o2.length() - o1.length();
		    }
		});
    	
    	
    	String defragment = fragmentList.remove(0);
    	
    	for(int i = fragmentList.size() - 1; i >= 0; i--){
    		
    		int match = 0, max = 0, index = 0, length = 0, j = 0;
			boolean prefix = false;
    		
			for(int k = fragmentList.size() - 1; k >= 0; k--){
    			
    			String s1 = defragment;
    			String s2 = fragmentList.get(k);
    			
    			int distanceToCompare = (s1.length() < s2.length()) ? s1.length() : s2.length();
    			
    			//prefix
    			prefix:
	    			for(int l = distanceToCompare-1; l >= 0; l--){
	    				
	    				if(s2.charAt(l) == s1.charAt(j)){
	    					
	    					for(int m = j+1, n = l+1; n < (distanceToCompare); m++, n++){
	    						
	    						if(s1.charAt(m) != s2.charAt(n)){
	    							continue prefix;
	    						}
	    						
	    					}
	 
	    					length = distanceToCompare - l;
	    					if(max < length){
	    						max = length;
	    						index = l;
	    						prefix = true;
	    						match = k;
	    					}
	    				}
	    				else{
	    					continue;
	    				}
	    				
	    			}
    			
    			//suffix	
    			suffix:
	    			for(int p = s1.length()-1; p >= (s1.length() - distanceToCompare); p--){
	    				
	    				if(s1.charAt(p) == s2.charAt(j)){
	    					
	    					for(int x = j+1, y = p+1; y < s1.length()-1; x++, y++){
	    						
	    						if(s2.charAt(x) != s1.charAt(y)){
	    							continue suffix;
	    						}
	    						
	    					}
	    					length = s1.length() - p;
	    					if(max < length){
	    						max = length;
	    						index = p;
	    						match = k;
	    						prefix = false;
	    					}
	    				}
	    				else{
	    					continue;
	    				}
	    			}
    			
    			
    		}
    		// finished comparing in this iteration and found a match so now merge accordingly
			if(max >= 1){
				
				String matched = fragmentList.get(match);
				
				if(prefix){
					defragment = matched.substring(0, index).concat(defragment);
				}
				else{
					defragment = defragment.substring(0, index).concat(matched);
				}
				
				fragmentList.remove(matched);
				
			}
			
    	}
    	
    	return defragment;
    }
    
 
    
}
