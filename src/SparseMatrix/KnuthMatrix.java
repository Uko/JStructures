/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SparseMatrix;

/**
 *
 * @author uko
 */
public class KnuthMatrix <dataType extends Number>
{
	/**
	 * anchor node which points to sub-anchor nodes down and to the left. Sub-anchor nodes point to the non-zero elements in the matrix;
	 */
	protected KnuthNode<dataType> anchor;
	/**
	 * Constructs 0x0 matrix by default.
	 */
	public KnuthMatrix()
	{
		anchor = new KnuthNode<dataType>(null, 0, 0, anchor, anchor);
	}
	/**
	 * Constructs matrix which size is based on the Position class parameter passed.
	 * @param size A Position class object (row,column), specifies amount of rows and columns in a matrix.
	 */
	public KnuthMatrix(Position size)
	{
		this(size.row, size.column);
	}
	/**
	 * Constructs matrix which size is based on the rows and columns parameters.
	 * @param rows Specifies amount of rows in a matrix.
	 * @param columns Specifies amount of columns in a matrix.
	 */
	public KnuthMatrix(int rows, int columns)
	{
		this();
		setSize(rows, columns);
	}
	/**
	 * Copying constructor.
	 * @param param Matrix to copy.
	 */
	public KnuthMatrix(KnuthMatrix param)
	{
		this(param.size().row, param.size().column);
		KnuthNode temp = param.anchor;
		for (int i = 0; i < param.anchor.row; i++)
		{
			temp = temp.up;
			temp = temp.left;
			{
				while (temp.column != 0)
				{
					insert((dataType)temp.data, temp.row, temp.column);
					temp = temp.left;
				}
			}
		}
	}
	/**
	 * Constructor from 2d array.
	 * @param param 2D array to copy.
	 */
	public KnuthMatrix(double[][] param)
	{
		this();
		for (int i = 0; i < param.length; i++)
		{
			if (param[i].length > anchor.column)
			{
				setSize(param.length, param[i].length);
			}
			for (int j = 0; j < param[i].length; j++)
			{
				if (param[i][j] != 0.0)
				{
					insert((dataType)new Double(param[i][j]), i + 1, j + 1);
				}
			}
		}
	}
	private boolean isBeforeBounds(int row, int column)
	{
		return row < 1 || column < 1;
	}
	/**
	 * Returns size of the matrix.
	 * @return size of the matrix in Position object.
	 */
	public Position size()
	{
		return (new Position(anchor.row, anchor.column));
	}
	/**
	 * Changes a size of matrix. Primary used to set size of 0x0 matrix constructed by default.
	 * @param rows Specifies new amount of rows in a matrix.
	 * @param columns Specifies new amount of columns in a matrix.
	 * @throws IndexOutOfBoundsException An exception is thrown when the size of matrix is less then 0.
	 */
	public final void setSize(int rows, int columns) throws IndexOutOfBoundsException
	{
		if (isBeforeBounds(rows, columns))
		{
			throw new IndexOutOfBoundsException("Error while trying: setSize(" + rows + ", " + columns + ")");
		}

		KnuthNode temp;
		if (columns > anchor.column)
		{
			for (int j = anchor.column; j < columns; j++)
			{
				temp = new KnuthNode(0, 0, j, null, anchor.left);
				temp.up = temp;
				anchor.left = temp;
			}
		}
		else
		{
			if (columns < anchor.column)
			{
				for (int j = anchor.column; j > columns; j--)
				{
					temp = anchor.left;
					anchor.left = anchor.left.left;
					temp = null;
				}
			}
		}
		if (rows > anchor.row)
		{
			for (int i = anchor.row; i < rows; i++)
			{
				temp = new KnuthNode(0, i, 0, anchor.up, null);
				temp.left = temp;
				anchor.up = temp;
			}
		}
		else
		{
			if (rows < anchor.row)
			{
				for (int i = anchor.row; i > rows; i--)
				{
					temp = anchor.up;
					anchor.up = anchor.up.up;
					temp = null;
				}
			}
		}
		anchor.row = rows;
		anchor.column = columns;
	}
	private boolean isOutOfBounds(int row, int column)
	{
		return row < 1 || column < 1 || row > anchor.row || column > anchor.column;
	}
	/**
	 * Adds a new non-zero element to the storage grid.
	 * @param data A value of the element.
	 * @param row i index. A row in which the element should be positioned.
	 * @param column j index. A column in which the element should be positioned.
	 * @throws IndexOutOfBoundsException An exception is thrown when the element is out of the matrix's bounds.
	 */
	public final void insert(dataType data, int row, int column) throws IndexOutOfBoundsException
	{
		if (isOutOfBounds(row, column))
		{
			throw new IndexOutOfBoundsException("Error while trying: add(" + row + ", " + column + ", " + data + ");"
					+ "\nmatrix size: rows = " + anchor.row + ", columns = " + anchor.column);
		}
		if (data.doubleValue() == 0)
		{
			remove(row, column);
			return;
		}
		KnuthNode CW = anchor;
		KnuthNode CCW = anchor;
		int rowDist = anchor.row - row;
		int colDist = anchor.column - column;
		for (int i = anchor.column; i >= column; i--)
		{
			CW = CW.left;
		}
		while (CW.up.row >= row)
		{
			CW = CW.up;
		}
		if (CW.row == row)
		{
			CW.data = data;
			return;
		}
		for (int i = anchor.row; i >= row; i--)
		{
			CCW = CCW.up;
		}
		while (CCW.left.column > column)
		{
			CCW = CCW.left;
		}
		CW.up = new KnuthNode(data, row, column, CW.up, CCW.left);
		CCW.left = CW.up;
	}
	/**
	 * Removes the matrix element form the storage grid i.e that element is changed to zero.
	 * @param row i index. A row in which the element is positioned.
	 * @param column j index. A column in which the element is positioned.
	 * @throws IndexOutOfBoundsException An exception is thrown when the element is out of the matrix's bounds.
	 */
	public void remove(int row, int column) throws IndexOutOfBoundsException
	{
		if (isOutOfBounds(row, column))
		{
			throw new IndexOutOfBoundsException("Error while trying: get(" + row + ", " + column + ")");
		}
		int rowDist = anchor.row - row;
		int colDist = anchor.column - column;
		KnuthNode temp = anchor;
		if (rowDist < colDist)
		{
			for (int i = anchor.row; i >= row; i--)
			{
				temp = temp.up;
			}
			while (temp.left.column > column)
			{
				temp = temp.left;
			}
			if (temp.left.column < column)
			{
				return;
			}
			temp.left = temp.left.left;
			temp = anchor;
			for (int i = anchor.column; i >= column; i--)
			{
				temp = temp.left;
			}
			while (temp.up.row > row)
			{
				temp = temp.up;
			}
			temp.up = temp.up.up;
		}
		else
		{
			for (int i = anchor.column; i >= column; i--)
			{
				temp = temp.left;
			}
			while (temp.up.row > row)
			{
				temp = temp.up;
			}
			if (temp.up.row < row)
			{
				return;
			}
			temp.up = temp.up.up;
			temp = anchor;
			for (int i = anchor.row; i >= row; i--)
			{
				temp = temp.up;
			}
			while (temp.left.column > column)
			{
				temp = temp.left;
			}
			temp.left = temp.left.left;
		}
	}
	/**
	 * Gets the matrix element by it's index.
	 * @param row i index. A row in which the element is positioned.
	 * @param column j index. A column in which the element is positioned.
	 * @return Value of the matrix element.
	 * @throws IndexOutOfBoundsException An exception is thrown when the requested element is out of the matrix's bounds.
	 */
	public dataType get(int row, int column) throws IndexOutOfBoundsException
	{
		if (isOutOfBounds(row, column))
		{
			throw new IndexOutOfBoundsException("Error while trying: add(" + row + ", " + column + ")");
		}
		int rowDist = anchor.row - row;
		int colDist = anchor.column - column;
		KnuthNode temp = anchor;
		if (rowDist < colDist)
		{
			for (int i = anchor.row; i >= row; i--)
			{
				temp = temp.up;
			}
			while (temp.left.column >= column)
			{
				temp = temp.left;
			}
			if (temp.column > column)
			{
				return (dataType)new Double(0);
			}
		}
		else
		{
			for (int i = anchor.column; i >= column; i--)
			{
				temp = temp.left;
			}
			while (temp.up.row >= row)
			{
				temp = temp.up;
			}
			if (temp.row > row)
			{
				return (dataType)new Double(0);
			}
		}
		return (dataType)temp.data;
	}
	/**
	 * Counts non-zero elements in the matrix.
	 * @return amount of non-zero elements.
	 */
	public int nonZero()
	{
		int counter = 0;
		KnuthNode temp = anchor;
		for (int i = 0; i < anchor.row; i++)
		{
			temp = temp.up;
			temp = temp.left;
			{
				while (temp.column != 0)
				{
					temp = temp.left;
					counter++;
				}
			}
		}
		return counter;
	}
	/**
	 * Multiplies matrix by scalar.
	 * @param factor scalar to multiply matrix.
	 * @return product of multiplication.
	 */
	public KnuthMatrix multiply(double factor)
	{
		KnuthMatrix product = new KnuthMatrix(this);
		KnuthNode temp = product.anchor;
		for (int i = 0; i < product.anchor.row; i++)
		{
			temp = temp.up;
			temp = temp.left;
			{
				while (temp.column != 0)
				{
					temp.data = new Double(temp.data.doubleValue()*factor);
					temp = temp.left;
				}
			}
		}
		return product;
	}
	/**
	 * Summation of two matrices.
	 * @param addendum matrix to add.
	 * @return Sum result.
	 * @throws SparseMatrix.KnuthMatrix.IncompatibleMatrixSizeException Exception is thrown if we cannot multiply the matrices.
	 */
	public KnuthMatrix add(KnuthMatrix addendum) throws IncompatibleMatrixSizeException
	{
		if (!size().equals(addendum.size()))
		{
			throw new IncompatibleMatrixSizeException();
		}

		KnuthMatrix summ = new KnuthMatrix(anchor.row, anchor.column);
		for (int i = 1; i <= anchor.row; i++)
		{
			for (int j = 1; j <= anchor.column; j++)
			{
				summ.insert(get(i, j).doubleValue() + addendum.get(i, j).doubleValue(), i, j);
			}
		}
		return summ;
	}
	/**
	 * Multiplies matrix by matrix.
	 * @param factor matrix to multiply by.
	 * @return Product of multiplication.
	 * @throws SparseMatrix.KnuthMatrix.IncompatibleMatrixSizeException Exception is thrown if we cannot multiply the matrices.
	 */
	public KnuthMatrix multiply(KnuthMatrix factor) throws IncompatibleMatrixSizeException
	{
		if (anchor.column != factor.anchor.row)
		{
			throw new IncompatibleMatrixSizeException();
		}

		KnuthMatrix product = new KnuthMatrix(anchor.row, factor.anchor.column);
		double summ = 0;
		for (int i = 1; i <= anchor.row; i++)
		{
			for (int j = 1; j <= factor.anchor.column; j++)
			{
				summ=0;
				for (int k = 1; k <= anchor.column; k++)
				{
					summ += get(i, k).doubleValue() * factor.get(k, j).doubleValue();
				}
				product.insert(summ, i, j);
			}
		}
		return product;
	}

}
