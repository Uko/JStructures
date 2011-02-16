/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Stack;

/**
 * A custom stack that has only 3 classical methods: push, pop & isEmpty
 * @param <dataType> type of a stack content
 * @author uko
 */
public class CustomStack <dataType>
{
	/**
	 * A head of a stack
	 */
	protected Node<dataType> head;
	/**
	 * Initializes stack with an empty head.
	 */
	public CustomStack()
	{
		head = null;
	}
	/**
	 * Check if the stack is empty.
	 * @return true if the stack is empty.
	 */
	public boolean isEmpty()
	{
		return head == null;
	}
	/**
	 * Returns first element from the stack.
	 * @return Object from the stack.
	 * @throws Stack.CustomStack.CustomStackException throw an Exception with a massage "Stack Empty" when the shack is empty.
	 */
	public dataType pop() throws CustomStackException
	{
		if(isEmpty())
		{
			throw new CustomStackException("Stack Empty");
		}
		dataType temp = head.data;
		head=head.next;
		return temp;
	}
	/**
	 * Adds an Object to the stack.
	 * @param param Object to add.
	 */
	public void push(dataType param)
	{
		Node temp = head;
		head = new Node(param);
		head.next = temp;
	}
	/**
	 * A Checked exception that is used to tell about the problems of the stack
	 */
	static public class CustomStackException extends Exception
	{
		/**
		 * Uses superclass constructor
		 */
		public CustomStackException()
		{
			super();
		}
		/**
		 * Uses superclass constructor
		 * @param param string to pass
		 */
		public CustomStackException(String param)
		{
			super(param);
		}
		/**
		 * Uses superclass constructor
		 * @param param Throwable to pass
		 */
		public CustomStackException(Throwable param)
		{
			super(param);
		}
		/**
		 * Uses superclass constructor
		 * @param param1 string to pass
		 * @param param2 Throwable to pass
		 */
		public CustomStackException(String param1, Throwable param2)
		{
			super(param1, param2);
		}
	}
}
