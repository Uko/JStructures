/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Stack;

import Stack.RPN.WrongFormulaException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author uko
 */
public class RPNTest
{
	/**
	 * Test of getStringResult method, of class RPN.
	 */
	@Test
	public void testParse1() throws WrongFormulaException
	{
		RPN instance = new RPN("3*(4+5)-(6-2)*4");
		String expResult = "3 4 5 + * 6 2 - 4 * -";
		String result = instance.getStringResult();
		assertEquals(expResult, result);
	}

	@Test
	public void testResult1() throws WrongFormulaException
	{
		RPN instance = new RPN("3*(4+5)-(6-2)*4");
		String result = instance.getStringResult();
		assertEquals(11, instance.getResult(),0.0);
	}

	@Test
	public void testParse2() throws WrongFormulaException
	{
		RPN instance = new RPN("34+9/3-2^9*24");
		String expResult = "34 9 3 / + 2 9 ^ 24 * -";
		String result = instance.getStringResult();
		assertEquals(expResult, result);
	}

	@Test
	public void testResult2() throws WrongFormulaException
	{
		RPN instance = new RPN("34+9/3-2^9*24");
		String result = instance.getStringResult();
		assertEquals(-12251, instance.getResult(),0.0);
	}

	@Test
	public void testParse3() throws WrongFormulaException
	{
		RPN instance = new RPN("(118/(9+((2*(3+4))-4)*5)-4)^10");
		String expResult = "118 9 2 3 4 + * 4 - 5 * + / 4 - 10 ^";
		String result = instance.getStringResult();
		assertEquals(expResult, result);
	}

	@Test
	public void testResult3() throws WrongFormulaException
	{
		RPN instance = new RPN("(118/(9+((2*(3+4))-4)*5)-4)^10");
		String result = instance.getStringResult();
		assertEquals(1024, instance.getResult(),0.0);
	}
}
