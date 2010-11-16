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
public class LibStringTest {

    /**
	 * Test of size method, of class String.
	 */
	@Test
	public void testsize() throws IllegalAccessException
	{
		java.lang.String instance = new java.lang.String();
		assertEquals(0, instance.length());

		instance = new java.lang.String("tratata");
		assertEquals(7, instance.length());

		instance = instance.concat(new java.lang.String("bum-bum"));
		assertEquals(14, instance.length());

		instance = instance.replaceFirst(new java.lang.String("tabum"), new java.lang.String("-bum"));
		assertEquals(13, instance.length());

		char[] arr = new char[3];
		instance = new java.lang.String(arr);
		assertEquals(3, instance.length());
	}

	/**
	 * Test of constuctor, of class String.
	 */
	@Test
	public void testConstr()
	{
		java.lang.String instance = new java.lang.String("tratata");
		assertEquals(7, instance.length());
		assertEquals("tratata", instance.toString());
	}

	/**
	 * Test of concat method, of class String.
	 */
	@Test
	public void testConcat()
	{
		java.lang.String instance = new java.lang.String("tratata");
		instance = instance.concat(new java.lang.String("bum-bum"));
		assertEquals("tratatabum-bum", instance.toString());
	}
	@Test
	public void testConcat1()
	{
		char[] arr = new char[3];
		java.lang.String instance = new java.lang.String(arr);
		instance = instance.concat(new java.lang.String("bum-bum"));
		assertEquals(""+arr[0]+arr[1]+arr[2]+"bum-bum", instance.toString());
	}
	@Test(expected = NullPointerException.class)
	public void testConcatCrash()
	{
		java.lang.String instance = new java.lang.String();
		instance.concat(null);
	}

	/**
	 * Test of indexOf method, of class String.
	 */
	@Test
	public void testIndexOf() throws IllegalAccessException
	{
		java.lang.String instance = new java.lang.String();
		//assertEquals(-1, instance.indexOf(new String()));
		instance = new java.lang.String("tratata");
		//assertEquals(-1, instance.indexOf(new String()));
		//assertEquals(-1, instance.indexOf(new String("bum")));
		//assertEquals(-1, instance.indexOf(new String("b")));
		assertEquals(3, instance.indexOf(new java.lang.String("ta")));
		instance = instance.concat(new java.lang.String("bum-bum"));
		assertEquals(7, instance.indexOf(new java.lang.String("bum")));
	}
	@Test(expected = NullPointerException.class)
	public void testIndexOfCrash() throws IllegalAccessException
	{
		java.lang.String instance = new java.lang.String();
		instance.indexOf(null);
	}

	/**
	 * Test of replaceFirst method, of class String.
	 */
	@Test
	public void testReplaceFirst() throws IllegalAccessException
	{
		java.lang.String instance = new java.lang.String("tratatabum-bum");

		instance=instance.replaceFirst(new java.lang.String("tabum"), new java.lang.String("-bum"));
		assertEquals("trata-bum-bum", instance.toString());
	}
	/**
	 * Test of replaceFirst method, of class String.
	 */
	@Test
	public void testReplaceFirst1() throws IllegalAccessException
	{
		java.lang.String instance = new java.lang.String("1212");
		instance=instance.replaceFirst(new java.lang.String("1"), new java.lang.String("2"));
		assertEquals("2212", instance.toString());

		instance = new java.lang.String("1234");
		instance=instance.replaceFirst(new java.lang.String("4"), new java.lang.String("5"));
		assertEquals("1235", instance.toString());

		instance = new java.lang.String("1134");
		instance=instance.replaceFirst(new java.lang.String("1"), new java.lang.String("2"));
		assertEquals("2134", instance.toString());

		instance = new java.lang.String("1224");
		instance=instance.replaceFirst(new java.lang.String("2"), new java.lang.String("3"));
		assertEquals("1324", instance.toString());

		instance = new java.lang.String("1234");
		instance=instance.replaceFirst(new java.lang.String("23"), new java.lang.String("323"));
		assertEquals("13234", instance.toString());

		instance = new java.lang.String("1234");
		instance=instance.replaceFirst(new java.lang.String("23"), new java.lang.String("3"));
		assertEquals("134", instance.toString());

		instance = new java.lang.String("1234");
		instance=instance.replaceFirst(new java.lang.String("4"), new java.lang.String(""));
		assertEquals("123", instance.toString());

	}
	@Test(expected = NullPointerException.class)
	public void testReplaceFirstCrash() throws IllegalAccessException
	{
		java.lang.String instance = new java.lang.String();
		instance.replaceFirst(null, null);
	}

	/**
	 * Test of toString method, of class String.
	 */
	@Test
	public void testToString()
	{
		java.lang.String instance = new java.lang.String();
		assertEquals("", instance.toString());

		instance = new java.lang.String("tratatabum-bum");
		assertEquals("tratatabum-bum", instance.toString());
	}

	/**
	 * Test of equals method, of class String.
	 */
	@Test
	public void testEquals()
	{
		java.lang.String instance = new java.lang.String();
		assertTrue(instance.equals(new java.lang.String()));

		java.lang.String testString = "tratatabum-bum";
		instance = new java.lang.String("tratatabum-bum");
		assertTrue(instance.equals(new java.lang.String(testString)));
	}
	/**
	 * Test of contains method, of class String.
	 */
	@Test
	public void testContains()
	{
		java.lang.String instance = new java.lang.String();

		instance = new java.lang.String("12345");
		assertTrue(instance.contains(new java.lang.String("5")));

		instance = new java.lang.String("tratata");
		assertTrue(instance.contains(new java.lang.String("tratata")));
		assertTrue(instance.contains(new java.lang.String("tra")));
		assertTrue(instance.contains(new java.lang.String("tata")));
		assertFalse(instance.contains(new java.lang.String("bu")));
	}
}


