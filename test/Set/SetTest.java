/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Set;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author uko
 */
public class SetTest {

   
	/**
	 * Test of add method, of class Set.
	 */ @Test
	public void testAdd()
	{
		Set<String> instance = new Set();
		instance.add("A");
		instance.add("B");
		instance.add("C");
		instance.add("B");
		assertEquals("A B C", instance.toString());
	}
	/**
	 * Test of delete method, of class Set.
	 */ @Test
	public void testDelete()
	{
		Set<String> instance = new Set();
		instance.add("A");
		instance.add("B");
		instance.add("C");
		instance.delete("B");
		assertEquals("A C", instance.toString());
	}
	/**
	 * Test of unite method, of class Set.
	 */ @Test
	public void testUnite()
	{
		Set<String> instance = new Set();
		Set<String> instance2 = new Set();
		instance.add("A");
		instance.add("B");
		instance.add("C");
		instance2.add("B");
		instance2.add("C");
		instance2.add("D");
		assertEquals("A B C D", instance.unite(instance2).toString());
	}
	/**
	 * Test of intersect method, of class Set.
	 */ @Test
	public void testIntersect()
	{
		Set<String> instance = new Set();
		Set<String> instance2 = new Set();
		instance.add("A");
		instance.add("B");
		instance.add("C");
		instance2.add("B");
		instance2.add("C");
		instance2.add("D");
		assertEquals("B C", instance.intersect(instance2).toString());
	}
	/**
	 * Test of difference method, of class Set.
	 */ @Test
	public void testDifference()
	{
		Set<String> instance = new Set();
		Set<String> instance2 = new Set();
		instance.add("A");
		instance.add("B");
		instance.add("C");
		instance2.add("B");
		instance2.add("C");
		instance2.add("D");
		assertEquals("A", instance.difference(instance2).toString());
	}
	 /**
	 * Test of difference method, of class Set.
	 */ @Test
	public void testDifference2()
	{
		Set<String> instance = new Set();
		Set<String> instance2 = new Set();
		instance.add("A");
		instance.add("B");
		instance.add("C");
		instance.add("D");
		instance.add("E");
		instance2.add("B");
		instance2.add("C");
		instance2.add("D");
		assertEquals("A E", instance.difference(instance2).toString());
	}
}