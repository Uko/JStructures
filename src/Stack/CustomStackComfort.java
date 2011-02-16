/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Stack;

import Stack.CustomStack.CustomStackException;

/**
 * A more sophisticated stack with size, ability to peek a head node without removing it, and to remove without peeking
 * @param <dataType> type of a stack content
 * @author uko
 */
public class CustomStackComfort<dataType> extends CustomStack<dataType>
{
	private int size;
	/**
	 * Initializes stack with a 0 size, and empty head.
	 */
	public CustomStackComfort()
	{
		super();
		size = 0;
	}
	/**
	 * Returns amount of a stored elements
	 * @return
	 */
	public int size()
	{
		return size;
	}
	/**
	 * Peeks a head node without removing it
	 * @return data stored in a head node
	 * @throws Stack.CustomStack.CustomStackException
	 */
	public dataType peak() throws CustomStackException
	{
		if (isEmpty())
		{
			throw new CustomStackException("Stack Empty");
		}
		return head.data;
	}
	@Override
	public void push(dataType param)
	{
		super.push(param);
		size++;
	}
	@Override
	public dataType pop() throws CustomStackException
	{
		dataType temp = super.pop();
		size--;
		return temp;
	}
	/**
	 * Removes the first object from the stack.
	 *
	 * @throws Stack.CustomStack.CustomStackException throw an Exception with a massage "Stack Empty" when the shack is empty.
	 */
	public void remove() throws CustomStackException
	{
		if (isEmpty())
		{
			throw new CustomStackException("Stack Empty");
		}
		head = head.next;
		size--;
	}
}
