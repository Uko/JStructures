/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Stack;

import Stack.CustomStack.CustomStackException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author uko
 */
public class CustomStackTest
{
	/**
	 * Test of isEmpty method, of class CustomStack.
	 */
	@Test
	public void testIsEmpty() throws CustomStackException
	{
		CustomStack instance = new CustomStack();
		assertEquals(true, instance.isEmpty());
		instance.push(5);
		assertEquals(false, instance.isEmpty());
		instance.pop();
		assertEquals(true, instance.isEmpty());
	}
	/**
	 * Test of top method exception throw, of class CustomStack.
	 */
	@Test(expected = CustomStack.CustomStackException.class)
	public void testTop_exception() throws CustomStackException
	{
		CustomStack instance = new CustomStack();
		Object result = instance.pop();
		fail("Exception has not occured");
	}
	/**
	 * Test of top & push method, of class CustomStack.
	 */
	@Test
	public void testPopAndPush() throws CustomStackException
	{
		CustomStack instance = new CustomStack();
		int i = 0;
		for (; i < 10; i++)
		{
			instance.push(i);
		}
		while (!instance.isEmpty())
		{
			i--;
			assertEquals(i, instance.pop());
		}
	}
}
