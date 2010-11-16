/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Table;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author uko
 */
public class SimpleTableTest {

    public SimpleTableTest() {
    }
	
	/**
	 * Test of add method, of class SimpleTable.
	 */
	@Test(expected = NullPointerException.class)
	public void crashAdd()
	{
		Object key = null;
		Object data = null;
		SimpleTable instance = new SimpleTable();
		instance.add(key, data);
		fail("crashAdd: The test should generate NullPointerException.");
	}

	@Test
	public void testAdd_Size()
	{
		SimpleTable instance = new SimpleTable();
		int expResult = 9;
		for(int i = 0; i < expResult; i++)
			instance.add(i, i*i);
		assertEquals(expResult, instance.size());
	}

	@Test
	public void testAdd_Get()
	{
		SimpleTable<String, String> instance = new SimpleTable<String, String>();
		instance.add("name1", "Kermit the Frog");
		instance.add("name2", "Miss Piggy");
		instance.add("name3", "Beaker");
		instance.add("name4", "Gonzo");
		instance.add("name5", "Animal");
		instance.add("name6", "The Swedish Chef");
		instance.add("name7", "Statler and Waldorf");
		instance.add("name8", "Fozzie Bear");

		assertEquals(8, instance.size());

		assertEquals("Kermit the Frog", instance.get("name1"));
		assertEquals("Miss Piggy", instance.get("name2"));
		assertEquals("Beaker", instance.get("name3"));
		assertEquals("Gonzo", instance.get("name4"));
		assertEquals("Animal", instance.get("name5"));
		assertEquals("The Swedish Chef", instance.get("name6"));
		assertEquals("Statler and Waldorf", instance.get("name7"));
		assertEquals("Fozzie Bear", instance.get("name8"));

		instance.replace("name4", "A. Ligator");
		assertEquals("A. Ligator", instance.get("name4"));
		assertEquals(8, instance.size());
	}

	@Test
	public void testAdd_Copy_Remove()
	{
		SimpleTable<String, String> instance = new SimpleTable<String, String>();
		instance.add("name1", "Kermit the Frog");
		instance.add("name2", "Miss Piggy");
		instance.add("name3", "Beaker");
		instance.add("name4", "Gonzo");
		instance.add("name5", "Animal");
		instance.add("name6", "The Swedish Chef");
		instance.add("name7", "Statler and Waldorf");
		instance.add("name8", "Fozzie Bear");

		SimpleTable<String, String> instance2 = new SimpleTable<String, String>(instance);

		assertEquals(8, instance2.size());

		instance.replace("name4", "A. Ligator");
		assertEquals("A. Ligator", instance.get("name4"));
		assertEquals("Gonzo", instance2.get("name4"));

		assertEquals("Kermit the Frog", instance2.remove("name1"));
		assertEquals("Miss Piggy", instance2.remove("name2"));
		assertEquals("Statler and Waldorf", instance2.remove("name7"));
		assertEquals("Beaker", instance2.remove("name3"));
		assertEquals("Animal", instance2.remove("name5"));
		assertEquals("Gonzo", instance2.remove("name4"));
		assertEquals("The Swedish Chef", instance2.remove("name6"));
		assertEquals("Fozzie Bear", instance2.remove("name8"));
	}

	/**
	 * Test of remove method, of class SimpleTable.
	 */
	@Test(expected = NullPointerException.class)
	public void crashRemove()
	{
		Object key = null;
		SimpleTable instance = new SimpleTable();
		Object expResult = null;
		Object result = instance.remove(key);
		assertEquals(expResult, result);
		fail("crashRemove: The test should generate NullPointerException.");
	}

	/**
	 * Test of get method, of class SimpleTable.
	 */
	@Test(expected = NullPointerException.class)
	public void crashGet()
	{
		Object key = null;
		SimpleTable instance = new SimpleTable();
		Object expResult = null;
		Object result = instance.get(key);
		assertEquals(expResult, result);
		fail("crashGet: The test should generate NullPointerException.");
	}

	/**
	 * Test of size method, of class SimpleTable.
	 */
	@Test
	public void testSize()
	{
		SimpleTable instance = new SimpleTable();
		int expResult = 0;
		int result = instance.size();
		assertEquals(expResult, result);
	}

	/**
	 * Test of iterator method, of class SimpleTable.
	 */
	@Test(expected = NoSuchElementException.class)
	public void crashIterator()
	{
		SimpleTable<String, String> instance = new SimpleTable<String, String>();

		Iterator runner = instance.iterator();

		runner.next();
	}
	@Test(expected = IllegalStateException.class)
	public void crashIterator2()
	{
		SimpleTable<String, String> instance = new SimpleTable<String, String>();

		Iterator runner = instance.iterator();

		runner.remove();
	}
	@Test(expected = IllegalStateException.class)
	public void crashIterator3()
	{
		SimpleTable<String, String> instance = new SimpleTable<String, String>();

		instance.add("name1", "Kermit the Frog");
		instance.add("name2", "Miss Piggy");
		instance.add("name3", "Beaker");
		instance.add("name4", "Gonzo");
		instance.add("name5", "Animal");
		instance.add("name6", "The Swedish Chef");
		instance.add("name7", "Statler and Waldorf");
		instance.add("name8", "Fozzie Bear");

		Iterator runner = instance.iterator();

		runner.remove();
	}
	@Test
	public void testIterator()
	{
		SimpleTable<String, String> instance = new SimpleTable<String, String>();

		Iterator runner = instance.iterator();

		assertTrue(!runner.hasNext());

		instance.add("name1", "Kermit the Frog");
		instance.add("name2", "Miss Piggy");
		instance.add("name3", "Beaker");
		instance.add("name4", "Gonzo");
		instance.add("name5", "Animal");
		instance.add("name6", "The Swedish Chef");
		instance.add("name7", "Statler and Waldorf");
		instance.add("name8", "Fozzie Bear");

		assertTrue(runner.hasNext());

		runner = instance.iterator();

		assertEquals("Kermit the Frog", runner.next());
		assertEquals("Miss Piggy", runner.next());
		assertEquals("Beaker", runner.next());
		assertEquals("Gonzo", runner.next());
		assertEquals("Animal", runner.next());
		assertEquals("The Swedish Chef", runner.next());
		assertEquals("Statler and Waldorf", runner.next());
		assertEquals("Fozzie Bear", runner.next());
	}

	@Test
	public void testIterator2()
	{
		SimpleTable<String, String> instance = new SimpleTable<String, String>();

		instance.add("name1", "Kermit the Frog");
		instance.add("name2", "Miss Piggy");
		instance.add("name3", "Beaker");
		instance.add("name4", "Gonzo");
		instance.add("name5", "Animal");
		instance.add("name6", "The Swedish Chef");
		instance.add("name7", "Statler and Waldorf");
		instance.add("name8", "Fozzie Bear");

		Iterator runner = instance.iterator();

		assertTrue(runner.hasNext());
		assertEquals("Kermit the Frog", runner.next());
		runner.remove();
		assertEquals("Miss Piggy", runner.next());
		runner.remove();
		assertEquals("Beaker", runner.next());
		runner.remove();
		assertEquals("Gonzo", runner.next());
		runner.remove();
		assertEquals("Animal", runner.next());
		runner.remove();
		assertEquals("The Swedish Chef", runner.next());
		runner.remove();
		assertEquals("Statler and Waldorf", runner.next());
		runner.remove();
		assertEquals("Fozzie Bear", runner.next());
		runner.remove();
		assertEquals(0, instance.size());
	}

	@Test(expected=NoSuchElementException.class)
	public void testTwiceAdd()
	{
		SimpleTable<String, String> instance = new SimpleTable<String, String>();
		instance.add("name1", "Kermit the Frog");
		instance.add("name1", "Miss Piggy");
		fail("Exception should have happened");
	}

	@Test(expected=NoSuchElementException.class)
	public void testNoReplace()
	{
		SimpleTable<String, String> instance = new SimpleTable<String, String>();
		instance.replace("name1", "Miss Piggy");
		fail("Exception should have happened");
	}

	@Test(expected=NoSuchElementException.class)
	public void testNoRemove()
	{
		SimpleTable<String, String> instance = new SimpleTable<String, String>();
		instance.replace("name1", "Miss Piggy");
		fail("Exception should have happened");
	}
}
