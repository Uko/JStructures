/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Stack;

import java.util.ArrayList;

/**
 * Stack data structure, extended from the ArrayList,
 * with the standard stack methods: top, push, pop
 * @author uko
 */

public class MorphedStack extends ArrayList
{
	/**
	 * Constructing the data storage (array fro ArrayList).
	 */
	public MorphedStack()
	{
		super();
	}
	/**
	 * Returns first element from the stack.
	 * @return Object from the stack.
	 */
	public Object top()
	{
		return (get(0));
	}
	/**
	 * Adds an Object to the stack.
	 * @param param Object to add.
	 */
	public void push(Object param)
	{
		add(0, param);
	}
	/**
	 * Removes the first object from the stack.
	 */
	public void pop()
	{
		remove(0);
	}
}
