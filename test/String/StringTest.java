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
public class StringTest {

    /**
	 * Test of size method, of class String.
	 */
	@Test
	public void testsize() throws IllegalAccessException
	{
		String instance = new String();
		assertEquals(0, instance.size());

		instance = new String("tratata");
		assertEquals(7, instance.size());

		instance = instance.concat(new String("bum-bum"));
		assertEquals(14, instance.size());

		System.out.println(instance.toString());
		instance.replaceFirst(new String("tabum"), new String("-bum"));
		assertEquals(13, instance.size());

		char[] arr = new char[3];
		instance = new String(arr);
		assertEquals(3, instance.size());
	}

	/**
	 * Test of constuctor, of class String.
	 */
	@Test
	public void testConstr()
	{
		String instance = new String("tratata");
		assertEquals(7, instance.size());
		assertEquals("tratata", instance.toString());
	}

	/**
	 * Test of concat method, of class String.
	 */
	@Test
	public void testConcat()
	{
		String instance = new String("tratata");
		instance = instance.concat(new String("bum-bum"));
		assertEquals("tratatabum-bum", instance.toString());
	}
	@Test
	public void testConcat1()
	{
		char[] arr = new char[3];
		String instance = new String(arr);
		instance = instance.concat(new String("bum-bum"));
		assertEquals(""+arr[0]+arr[1]+arr[2]+"bum-bum", instance.toString());
	}
	@Test(expected = NullPointerException.class)
	public void testConcatCrash()
	{
		String instance = new String();
		instance.concat(null);
	}

	/**
	 * Test of indexOf method, of class String.
	 */
	@Test
	public void testIndexOf() throws IllegalAccessException
	{
		String instance = new String();
		//assertEquals(-1, instance.indexOf(new String()));
		instance = new String("tratata");
		//assertEquals(-1, instance.indexOf(new String()));
		//assertEquals(-1, instance.indexOf(new String("bum")));
		//assertEquals(-1, instance.indexOf(new String("b")));
		assertEquals(3, instance.indexOf(new String("ta")));
		instance = instance.concat(new String("bum-bum"));
		assertEquals(7, instance.indexOf(new String("bum")));
	}
	@Test(expected = NullPointerException.class)
	public void testIndexOfCrash() throws IllegalAccessException
	{
		String instance = new String();
		instance.indexOf(null);
	}

	/**
	 * Test of replaceFirst method, of class String.
	 */
	@Test
	public void testReplaceFirst() throws IllegalAccessException
	{
		String instance = new String("tratatabum-bum");

		instance.replaceFirst(new String("tabum"), new String("-bum"));
		assertEquals("trata-bum-bum", instance.toString());
	}
	@Test(expected = NullPointerException.class)
	public void testReplaceFirstCrash() throws IllegalAccessException
	{
		String instance = new String();
		instance.replaceFirst(null, null);
	}

	/**
	 * Test of toString method, of class String.
	 */
	@Test
	public void testToString()
	{
		String instance = new String();
		assertEquals("", instance.toString());

		instance = new String("tratatabum-bum");
		assertEquals("tratatabum-bum", instance.toString());
	}

	/**
	 * Test of equals method, of class String.
	 */
	@Test
	public void testEquals()
	{
		String instance = new String();
		//assertEquals(new String(), instance);

		java.lang.String testString = "tratatabum-bum";
		instance = new String("tratatabum-bum");
		assertEquals(new String(testString), instance);
	}
	/**
	 * Test of contains method, of class String.
	 */
	@Test
	public void testContains()
	{
		String instance = new String();
		instance = new String("tratata");
		assertTrue(instance.contains(new String("tratata")));
		assertTrue(instance.contains(new String("tra")));
		assertTrue(instance.contains(new String("tata")));
		assertFalse(instance.contains(new String("bu")));
	}
}


