/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Table;

import java.util.Enumeration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author uko
 */
public class HashTableTest {

    public HashTableTest() {
    }
	@BeforeClass
	public static void setUpClass() throws Exception
	{
	}
	@AfterClass
	public static void tearDownClass() throws Exception
	{
	}
	/**
	 * Test of size method, of class HashTable.
	 */ @Test
	public void testSize()
	{
		HashTable<Integer,Integer> instance = new HashTable();
		for (int i=0; i<500; i++)
		{
			instance.put(new Integer(i), new Integer(i^3));
			assertEquals(i+1, instance.size());
		}
	}
	/**
	 * Test of isEmpty method, of class HashTable.
	 */ @Test
	public void testIsEmpty()
	{
		HashTable instance = new HashTable();
		assertEquals(true, instance.isEmpty());
		instance.put("a", "beta");
		assertEquals(false, instance.isEmpty());
		instance.remove("a");
		assertEquals(true, instance.isEmpty());
	}
	
	/**
	 * Test of elements method, of class HashTable.
	 */ @Test
	public void testElements()
	{
		HashTable<Integer,Integer> instance = new HashTable();
		for (int i=0; i<500; i++)
		{
			instance.put(new Integer(i), new Integer(i^3));
		}
		
	}
	/**
	 * Test of keys method, of class HashTable.
	 */ @Test
	public void testKeysElements()
	{
		HashTable<Integer,Integer> instance = new HashTable();
		for (int i=0; i<500; i++)
		{
			instance.put(new Integer(i), new Integer(i^3));
		}
		Enumeration<Integer> k = instance.keys();
		Enumeration<Integer> e = instance.elements();
		int i=499;
		while (k.hasMoreElements()&&e.hasMoreElements())
		{
			assertEquals(new Integer(i), k.nextElement());
			assertEquals(new Integer(i^3), e.nextElement());
			 i--;
	}
	}
	/**
	 * Test of containsKey method, of class HashTable.
	 */ @Test
	public void testContainsKey()
	{
		HashTable<Integer,Integer> instance = new HashTable();
		for (int i=0; i<500; i++)
		{
			instance.put(new Integer(i), new Integer(i^3));
		}
		for (int i=0; i<500; i++)
		{
			assertEquals(true, instance.containsValue(new Integer(i)));
		}
	}
	@Test
	public void testContainsKey1()
	{
		HashTable<Integer, Integer> instance = new HashTable();
		Integer keyRunner;
		for (int i = 0; i < 500; i++)
		{
			keyRunner = new Integer(i);
			instance.put(keyRunner, new Integer(i ^ 3));
			assertEquals(true, instance.containsKey(keyRunner));
		}
	}
	@Test
	public void testContainsValue()
	{
		HashTable<Integer, Integer> instance = new HashTable();
		for (int i = 0; i < 500; i++)
		{
			instance.put(new Integer(i), new Integer(i ^ 3));
			assertEquals(true, instance.containsValue(new Integer(i ^ 3)));
		}
	}
	/**
	 * Test of get method, of class HashTable.
	 */ @Test
	public void testGet()
	{
		HashTable<Integer,Integer> instance = new HashTable();
		for (int i=0; i<500; i++)
		{
			instance.put(new Integer(i), new Integer(i^3));
			assertEquals(new Integer(i^3), instance.get(new Integer(i)));
		}
	}
	/**
	 * Test of rehash method, of class HashTable.
	 */ @Test
	public void testRehash()
	{
		HashTable<Integer,Integer> instance = new HashTable();
		int	capacity=instance.capasity();
		for (int i=0; i<500; i++)
		{
			instance.put(new Integer(i), new Integer(i^3));
			assertTrue(capacity<=instance.capasity());
			capacity=instance.capasity();
		}
	}
	/**
	 * Test of remove method, of class HashTable.
	 */ @Test
	public void testRemove()
	{
		HashTable<Integer,Integer> instance = new HashTable();
		for (int i=0; i<500; i++)
		{
			instance.put(new Integer(i), new Integer(i^3));
		}
		for (int i=500; i>0; i--)
		{
			assertEquals(i, instance.size());
			instance.remove(i-1);
		}
		assertTrue(instance.isEmpty());
	}
	/**
	 * Test of clear method, of class HashTable.
	 */ @Test
	public void testClear()
	{
		HashTable<Integer,Integer> instance = new HashTable();
		for (int i=0; i<500; i++)
		{
			instance.put(new Integer(i), new Integer(i^3));
		}
		instance.clear();
		assertTrue(instance.isEmpty());
		for (int i=0; i<1000; i++)
		{
			instance.put(new Integer(i), new Integer(i^3));
		}
		instance.clear();
		assertTrue(instance.isEmpty());
		for (int i=0; i<5000; i++)
		{
			instance.put(new Integer(i), new Integer(i^3));
		}
		instance.clear();
		assertTrue(instance.isEmpty());
	}
	@Test
	public void testPut()
	{
		HashTable<String, Integer> instance = new HashTable(1, 3);

		instance.put("first", new Integer(1));
		instance.put("first", new Integer(11));
		instance.put("second", new Integer(2));
		instance.put("third", new Integer(3));


		assertTrue((new Integer(3)).equals(instance.remove("third")));
		assertTrue((new Integer(2)).equals(instance.remove("second")));
		assertTrue((new Integer(11)).equals(instance.remove("first")));

	}
}