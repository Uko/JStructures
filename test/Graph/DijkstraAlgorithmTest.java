/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author uko
 */
public class DijkstraAlgorithmTest
{
	public DijkstraAlgorithmTest()
	{
	}
	/**
	 * Test of solve method, of class DijkstraAlgorithm.
	 */
	@Test
	public void testSolve()
	{
		Graph instance = new Graph(6);
		DijkstraAlgorithm helper = new DijkstraAlgorithm(instance, 0, 4);
		instance.addConnection(7, 0, 1);
		instance.addConnection(9, 0, 2);
		instance.addConnection(14, 0, 5);
		instance.addConnection(10, 1, 2);
		instance.addConnection(11, 2, 3);
		instance.addConnection(2, 2, 5);
		instance.addConnection(6, 3, 4);
		instance.addConnection(9, 4, 5);
		assertEquals(20.0, helper.solve(), 0.0);
	}
}
