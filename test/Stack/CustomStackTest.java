/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Stack;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author uko
 */
public class CustomStackTest
{
	/**
	 * Test of size method, of class CustomStack.
	 */
	@Test
	public void testSize()
	{
		CustomStack instance = new CustomStack();
		for (int i=0; i<10; i++)
		{
			assertEquals(i, instance.size());
			instance.push(5);
		}
	}
	/**
	 * Test of isEmpty method, of class CustomStack.
	 */
	@Test
	public void testIsEmpty()
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
	@Test(expected=RuntimeException.class)
	public void testTop_exception()
	{
		CustomStack instance = new CustomStack();
		Object result = instance.top();
		fail("Exception has not occured");
	}
	/**
	 * Test of top & push method, of class CustomStack.
	 */
	@Test
	public void testTopAndPush()
	{
		CustomStack instance = new CustomStack();
		int i=0;
		for (; i<10; i++)
		{
			instance.push(i);
			assertEquals(i, instance.top());
		}
		while (!instance.isEmpty())
		{
			i--;
			assertEquals(i, instance.top());
			instance.pop();
		}
	}
	/**
	 * Test of pop method exception throw, of class CustomStack.
	 */
	@Test(expected=RuntimeException.class)
	public void testPop_exception()
	{
		CustomStack instance = new CustomStack();
		instance.pop();
		fail("Exception has not occured.");
	}
}
