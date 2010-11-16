/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package String;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author uko
 */
public class MarkovTest {

    
	/**
	 * Test of ProcessString method, of class juMarkovProcessor.
	 */
	@Test
	public void testProcessString()
	{
		Markov instance = new Markov();
		instance.addProduction("a0", "0aa");
		instance.addProduction("1", "0a");
		instance.addProduction("0", "");
		instance.setworkString("101");
		for (int i = 0; i < 100500; i++)
		{
			java.lang.String result = instance.ProcessString();
			assertEquals("aaaaa", result);
		}
	}

	/**
	 * Test of ProcessString method, of class juMarkovProcessor.
	 */
	@Test
	public void testProcessMarkovString() throws IllegalAccessException
	{
		Markov instance = new Markov();
		instance.addProduction("|0", "0||");
		instance.addProduction("1", "0|");
		instance.addProduction("0", "");
		instance.setworkString("101");
		for (int i = 0; i < 100500; i++)
		{
			String result = instance.ProcessMarkovString();
			assertTrue(result.equals(new String("|||||")));
		}
	}

}