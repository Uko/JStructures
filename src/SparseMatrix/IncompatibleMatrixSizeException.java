/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SparseMatrix;

/**
 *
 * @author uko
 */
public class IncompatibleMatrixSizeException extends RuntimeException
{
	public IncompatibleMatrixSizeException()
	{
		super();
	}
	public IncompatibleMatrixSizeException(String message)
	{
		super(message);
	}
}