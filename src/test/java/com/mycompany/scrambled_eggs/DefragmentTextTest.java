package com.mycompany.scrambled_eggs;

import org.junit.Test;
import junit.framework.TestCase;


    /**
     * Unit Test
     *
     * @author colin
     *
     */
    public static class DefragmentTextTest extends TestCase {

    	
    	private List<String> fragmentedTexts = null;
    	private List<String> defragmentedTexts = null;
    	
    	
    	/**
    	 * @param name
    	 */
    	public DefragmentTextTest(String name) {
    		super(name);
    	}

    	/* (non-Javadoc)
    	 * @see junit.framework.TestCase#setUp()
    	 */
    	protected void setUp() throws Exception {
    		super.setUp();
    		
    		// inputs
    		String input1 = "O draconia;conian devil! Oh la;h lame sa;saint!";
    		String input2 = "m quaerat voluptatem.;pora incidunt ut labore et d;, consectetur, adipisci velit;olore magnam aliqua;idunt ut "
    				+ "labore et dolore magn;uptatem.;i dolorem ipsum qu;iquam quaerat vol;psum quia dolor sit amet, consectetur, a;"
    				+ "ia dolor sit amet, conse;squam est, qui do;Neque porro quisquam est, qu;aerat voluptatem.;m eius modi tem;Neque porro qui;"
    				+ ", sed quia non numquam ei;lorem ipsum quia dolor sit amet;ctetur, adipisci velit, sed quia non numq;unt ut labore et dolore magnam "
    				+ "aliquam qu;dipisci velit, sed quia non numqua;us modi tempora incid;Neque porro quisquam est, qui dolorem i;uam eius modi tem;"
    				+ "pora inc;am al";
    		
    		String input3 = "ABCDEF;DEFG";
    		String input4 = "ABCDEF;XYZABC";
    		String input5 = "ABCDEF;BCDE";
    		String input6 = "ABCDEF;XCDEZ";
    		
    		//outputs
    		String output1 = "O draconian devil! Oh lame saint!";
    		String output2 = "Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, "
    				+ "sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.";
    		
    		String output3 = "ABCDEFG";
    		String output4 = "XYZABCDEF";
    		String output5 = "ABCDEF"; // no overlap so original text stay same
    		String output6 = "ABCDEF"; // no overlap so original text stay same
    		
    		
    		fragmentedTexts = new ArrayList<String>(Arrays.asList(new String[]{input1, input2, input3, input4, input5, input6}));
    		defragmentedTexts = new ArrayList<String>(Arrays.asList(new String[]{output1, output2, output3, output4, output5, output6}));
    	}

    	/* (non-Javadoc)
    	 * @see junit.framework.TestCase#tearDown()
    	 */
    	protected void tearDown() throws Exception {
    		super.tearDown();
    		
    		fragmentedTexts = null;
    		defragmentedTexts = null;
    	}
    	
    	/**
    	 * Test the reassemble method
    	 */
    	public void testReassemble(){
    		
    		for(int i = 0; i < fragmentedTexts.size(); i++){
    			
    			String expected = defragmentedTexts.get(i);
    			String actual = DefragmentText.reassemble(fragmentedTexts.get(i));
    			
    			assertEquals(expected, actual);
    		}
    		
    		
    	}

    }
    
