/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SparseMatrix;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class KnuthMatrixTest
{
	public KnuthMatrixTest()
	{
	}
	/**
	 * Test of setSize method, of class KnuthMatrix.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void crashSetSize()
	{
		KnuthMatrix instance = new KnuthMatrix();
		int rows = 0;
		int columns = 0;
		instance.setSize(rows, columns);
		fail("instance.setSize(" + rows + ", " + columns + ") should fire IndexOutOfBoundsException");
	}
	@Test
	public void testSetSize()
	{
		KnuthMatrix<Integer> instance = new KnuthMatrix<Integer>();
		instance.setSize(1, 1);
		Position result = instance.size();
		assertEquals(1, result.row);
		assertEquals(1, result.column);

		instance.setSize(3, 5);
		result = instance.size();
		assertEquals(3, result.row);
		assertEquals(5, result.column);

		instance.setSize(1, 3);
		result = instance.size();
		assertEquals(1, result.row);
		assertEquals(3, result.column);
	}
	/**
	 * Test of insert method, of class KnuthMatrix.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void crashinsert()
	{
		KnuthMatrix instance = new KnuthMatrix();
		int row = 0;
		int column = 0;
		double data = 0.0;
		instance.insert(data, row, column);
		fail("instance.insert(" + row + ", " + column + ", " + data + ") should fire IndexOutOfBoundsException");
	}
	@Test
	public void testinsert()
	{
		KnuthMatrix instance = new KnuthMatrix();
		instance.setSize(1, 1);
		instance.insert(1.1, 1, 1);
		assertEquals(1.1, instance.get(1, 1));
		instance.insert(11.11, 1, 1);
		assertEquals(11.11, instance.get(1, 1));

		instance.setSize(7, 9);
		instance.insert(5.3, 5, 3);
		assertEquals(5.3, instance.get(5, 3));
		instance.insert(5.5, 5, 5);
		assertEquals(5.5, instance.get(5, 5));
		instance.insert(5.4, 5, 4);
		assertEquals(5.4, instance.get(5, 4));
		instance.insert(3.3, 3, 3);
		assertEquals(3.3, instance.get(3, 3));
		instance.insert(4.4, 4, 4);
		assertEquals(4.4, instance.get(4, 4));
		instance.insert(0.0, 4, 4);
		assertEquals(0, instance.get(4, 4));
		assertEquals(0, instance.get(7, 9));
	}
	@Test
	public void testRemove()
	{
		KnuthMatrix instance = new KnuthMatrix();
		instance.setSize(1, 1);
		instance.insert(1.1, 1, 1);
		assertEquals(1.1, instance.get(1, 1));
		instance.insert(0, 1, 1);
		assertEquals(0, instance.get(1, 1));
	}
	/**
	 * Test of Size method, of class KnuthMatrix.
	 */
	@Test
	public void testStartSize()
	{
		KnuthMatrix instance = new KnuthMatrix();
		Position result = instance.size();
		assertEquals(0, result.row);
		assertEquals(0, result.column);
	}
	@Test
	public void testNonZero()
	{
		KnuthMatrix instance = new KnuthMatrix();
		instance.setSize(10, 10);
		instance.insert(2, 3, 4);
		instance.insert(2, 5, 4);
		instance.insert(2, 1, 4);
		instance.insert(2, 3, 6);
		instance.insert(2, 3, 7);
		assertEquals(5, instance.nonZero());
	}
	

	@Test
	public void testMultiplyScalar()
	{
		KnuthMatrix instance = new KnuthMatrix(10,10);
		instance.insert(5, 2, 3);
		instance.insert(3, 1, 3);
		instance.insert(6, 1, 8);
		KnuthMatrix instance2 = instance.multiply(10);
		assertEquals(50.0, instance2.get(2, 3));
		assertEquals(30.0, instance2.get(1, 3));
		assertEquals(60.0, instance2.get(1, 8));
	}

	@Test
	public void testAdd()
	{
		KnuthMatrix<Double> instance = new KnuthMatrix<Double>(10,10);
		instance.insert(5.0, 2, 3);
		instance.insert(3.0, 1, 3);
		instance.insert(6.0, 1, 8);
		KnuthMatrix<Double> instance2 = new KnuthMatrix<Double>(10,10);
		instance2.insert(24.0, 10, 3);
		instance2.insert(8.0, 1, 3);
		instance2.insert(-1.0, 1, 8);
		KnuthMatrix instance3 = instance.add(instance2);
		assertEquals(3, instance.nonZero());
		assertEquals(3, instance2.nonZero());
		assertEquals(4, instance3.nonZero());
		assertEquals(5.0, instance3.get(2, 3));
		assertEquals(24.0, instance3.get(10, 3));
		assertEquals(11.0, instance3.get(1, 3));
		assertEquals(5.0, instance3.get(1, 8));
	}
	@Test(expected = RuntimeException.class)
	public void testAddException()
	{
		KnuthMatrix instance = new KnuthMatrix(10,5);
		KnuthMatrix instance2 = new KnuthMatrix(2,3);
		KnuthMatrix instance3 = instance.add(instance2);
		fail("Exception has not occured!");
	}

	@Test
	public void testMultiplyMatrix()
	{
		double[][] instance1 = {{2,1,3},
							   {-2,2,1}};
		KnuthMatrix instance11 = new KnuthMatrix(instance1);
		double[][] instance2 = {{2,1},
								{3,2},
							   {-2,2}};
		KnuthMatrix instance21 = new KnuthMatrix(instance2);
		KnuthMatrix instance3 = instance11.multiply(instance21);
		assertEquals(2.0, instance11.get(1, 1));
		assertEquals(1.0, instance11.get(1, 2));
		assertEquals(3.0, instance11.get(1, 3));
		assertEquals(1.0, instance3.get(1, 1));
		assertEquals(10.0, instance3.get(1, 2));
		assertEquals(4.0, instance3.get(2, 2));
		assertEquals(0.0, instance3.get(2, 1));
		assertEquals(3, instance3.nonZero());
	}
	@Test(expected = RuntimeException.class)
	public void testMultiplyException()
	{
		KnuthMatrix instance = new KnuthMatrix(10,5);
		KnuthMatrix instance2 = new KnuthMatrix(2,3);
		KnuthMatrix instance3 = instance./*sdfsad*/multiply(instance2);
		fail("Exception has not occured!");
	}
}


