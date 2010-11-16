/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Stack;

/**
 * A custom stack
 * @author uko
 */
public class CustomStack
{
	private int size;
	private Node head;
	/**
	 * Initializes stack with a 0 size, and null head.
	 */
	public CustomStack()
	{
		size = 0;
		head = null;
	}
	/**
	 * Shows size of stack.
	 * @return amount of stack nodes.
	 */
	public int size()
	{
		return size;
	}
	/**
	 * Check if the stack is empty.
	 * @return true if the stack is empty.
	 */
	public boolean isEmpty()
	{
		return size == 0;
	}
	/**
	 * Returns first element from the stack.
	 * @return Object from the stack.
	 */
	public Object top()
	{
		if(isEmpty())
		{
			throw new RuntimeException("Stack Empty");
		}
		return head.data;
	}
	/**
	 * Adds an Object to the stack.
	 * @param param Object to add.
	 */
	public void push(Object param)
	{
		Node temp = head;
		head = new Node(param);
		head.next = temp;
		size++;
	}
	/**
	 * Removes the first object from the stack.
	 */
	public void pop()
	{
		if(isEmpty())
		{
			throw new RuntimeException("Stack Empty");
		}
		head=head.next;
		size--;
	}

}
