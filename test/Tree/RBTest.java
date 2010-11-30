/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Tree;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author uko
 */
public class RBTest {

    public RBTest() {
    }
	/**
	 * Test of lookup method, of class RB.
	 */ @Test
	public void testInsertNLookup()
	{
		RB instance = new RB();
		for (int i=0; i<100; i++)
		{
			instance.insert(i, i*i);
			instance.print();
			System.out.println("———");
		}
		for (int i=0; i<100; i++)
		{
			assertEquals(i*i, instance.lookup(i));
		}
		assertTrue(instance.verifyProperties());

	}
	/**
	 * Test of delete method, of class RB.
	 */ @Test
	public void testDeleteNInsertNLoukup()
	{
		RB instance = new RB();
		assertEquals(null, instance.lookup(1));
		instance.insert(2, "abra");
		assertEquals("abra", instance.lookup(2));
		instance.insert(2, "kadabra");
		assertEquals("kadabra", instance.lookup(2));
		instance.delete(2);
		assertEquals(null, instance.lookup(2));
		assertTrue(instance.verifyProperties());

		for (int i=0; i<100; i++)
		{
			instance.insert(i, i*i);
		}
		instance.delete(5);
		instance.delete(75);
		instance.delete(3);
		instance.delete(47);
	}
	/**
	 * Test of print method, of class RB.
	 */ @Test
	public void testPrint()
	{
		RB instance = new RB();
		instance.insert(6, "oo");
		instance.print();
		System.out.println("———");
		instance.insert(4, "oo");
		instance.print();
		System.out.println("———");
		instance.insert(3, "oo");
		instance.print();
		System.out.println("———");
		instance.insert(5, "oo");
		instance.print();
		System.out.println("———");
		assertTrue(instance.verifyProperties());
	}

}