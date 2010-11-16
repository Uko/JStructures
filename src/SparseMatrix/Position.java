/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SparseMatrix;

/**
 *
 * @author uko
 */

public class Position
{
	/**
	 * Matrix element i index i.e the row it which element is located.
	 */
	public int row;
	/**
	 * Matrix element j index i.e the column it which element is located.
	 */
	public int column;
	/**
	 * Basic constructor that sets row and column to 0.
	 */
	public Position()
	{
		this(0,0);
	}
	/**
	 * Sets row and column to those passed to the constructor.
	 * @param row i index i.e the row it which element is located.
	 * @param column j index i.e the column it which element is located.
	 */
	public Position(int row, int column)
	{
		this.row=row;
		this.column=column;
	}
	/**
	 * Compares Position objects.
	 * @param param Position object to compare.
	 * @return true if equal, false if not.
	 */
	public boolean equals(Position param)
	{
		return column==param.column&&row==param.row;
	}
}