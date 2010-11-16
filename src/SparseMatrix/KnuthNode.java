/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SparseMatrix;

/**
 *
 * @author uko
 */
public class KnuthNode <dataType extends Number>
{
	/**
	 * A vale that is held in the matrix cell.
	 */
	public dataType data;
	/**
	 * An i index i.e the row it which element is located.
	 */
	public int row;
	/**
	 * An j index i.e the column it which element is located.
	 */
	public int column;
	/**
	 * Next non-zero element above.
	 */
	public KnuthNode<dataType> up;
	/**
	 * Next non-zero element to the left.
	 */
	public KnuthNode<dataType> left;
	/**
	 * Creates a basic node with all fields set to 0 and pointers set to null.
	 */
	public KnuthNode()
	{
		this(null);
	}
	/**
	 * Creates a basic node with a value of an element but 0-indexes and pointers set to null.
	 * @param data Value of the element.
	 */
	public KnuthNode(dataType data)
	{
		this(data, 0, 0);
	}
	/**
	 * Creates a node with a value of an element specified indexes and pointers set to null.
	 * @param data Value of the element.
	 * @param row An i index i.e the row it which element is located.
	 * @param column An j index i.e the column it which element is located.
	 */
	public KnuthNode(dataType data, int row, int column)
	{
		this(data, row, column, null, null);
	}
	/**
	 * Creates a node with a value of an element specified indexes and specified pointers.
	 * @param data Value of the element.
	 * @param row An i index i.e the row it which element is located.
	 * @param column An j index i.e the column it which element is located.
	 * @param up Next non-zero element above.
	 * @param left Next non-zero element to the left.
	 */
	public KnuthNode(dataType data, int row, int column, KnuthNode<dataType> up, KnuthNode<dataType> left)
	{
		this.data = data;
		this.row = row;
		this.column = column;
		this.up = up;
		this.left = left;
	}
}
