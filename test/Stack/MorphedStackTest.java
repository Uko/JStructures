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
public class MorphedStackTest
{
	/**
	 * Test of size method, of class MorphedStack.
	 */
	@Test
	public void testSize()
	{
		MorphedStack instance = new MorphedStack();
		for (int i=0; i<10; i++)
		{
			assertEquals(i, instance.size());
			instance.push(5);
		}
	}
	/**
	 * Test of isEmpty method, of class MorphedStack.
	 */
	@Test
	public void testIsEmpty()
	{
		MorphedStack instance = new MorphedStack();
		assertEquals(true, instance.isEmpty());
		instance.push(5);
		assertEquals(false, instance.isEmpty());
		instance.pop();
		assertEquals(true, instance.isEmpty());
	}
	/**
	 * Test of top method exception throw, of class MorphedStack.
	 */
	@Test(expected=RuntimeException.class)
	public void testTop_exception()
	{
		MorphedStack instance = new MorphedStack();
		Object result = instance.top();
		fail("Exception has not occured");
	}
	/**
	 * Test of top & push method, of class MorphedStack.
	 */
	@Test
	public void testTopAndPush()
	{
		MorphedStack instance = new MorphedStack();
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
	 * Test of pop method exception throw, of class MorphedStack.
	 */
	@Test(expected=RuntimeException.class)
	public void testPop_exception()
	{
		MorphedStack instance = new MorphedStack();
		instance.pop();
		fail("Exception has not occured.");
	}
}
