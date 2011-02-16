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
public class CustomStackComfortTest
{
	public CustomStackComfortTest()
	{
	}
	/**
	 * Test of size method, of class CustomStackComfort.
	 */
	@Test
	public void testSize()
	{
		CustomStackComfort instance = new CustomStackComfort();
		for (int i = 0; i < 10; i++)
		{
			assertEquals(i, instance.size());
			instance.push(5);
		}
	}
	/**
	 * Test of isEmpty method, of class CustomStackComfort.
	 */
	@Test
	public void testIsEmpty() throws CustomStackException
	{
		CustomStackComfort instance = new CustomStackComfort();
		assertEquals(true, instance.isEmpty());
		instance.push(5);
		assertEquals(false, instance.isEmpty());
		instance.remove();
		assertEquals(true, instance.isEmpty());
	}
	/**
	 * Test of top method exception throw, of class CustomStackComfort.
	 */
	@Test(expected = CustomStack.CustomStackException.class)
	public void testPeek_exception() throws CustomStackException
	{
		CustomStackComfort instance = new CustomStackComfort();
		Object result = instance.peak();
		fail("Exception has not occured");
	}
	/**
	 * Test of top & push method, of class CustomStackComfort.
	 */
	@Test
	public void testTopAndPush() throws CustomStackException
	{
		CustomStackComfort instance = new CustomStackComfort();
		int i = 0;
		for (; i < 10; i++)
		{
			instance.push(i);
			assertEquals(i, instance.peak());
		}
		while (!instance.isEmpty())
		{
			i--;
			assertEquals(i, instance.peak());
			instance.pop();
		}
	}
	/**
	 * Test of pop method exception throw, of class CustomStackComfort.
	 */
	@Test(expected = CustomStack.CustomStackException.class)
	public void testPop_exception() throws CustomStackException
	{
		CustomStackComfort instance = new CustomStackComfort();
		instance.pop();
		fail("Exception has not occured.");
	}
	@Test(expected = CustomStack.CustomStackException.class)
	public void testTop_exception() throws CustomStackException
	{
		CustomStackComfort instance = new CustomStackComfort();
		Object result = instance.pop();
		fail("Exception has not occured");
	}
	@Test(expected = CustomStack.CustomStackException.class)
	public void testRemove_exception() throws CustomStackException
	{
		CustomStackComfort instance = new CustomStackComfort();
		instance.remove();
		fail("Exception has not occured");
	}
	/**
	 * Test of top & push method, of class CustomStack.
	 */
	@Test
	public void testPopAndPush() throws CustomStackException
	{
		CustomStackComfort instance = new CustomStackComfort();
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
