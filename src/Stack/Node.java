/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Stack;

/**
 *
 * @author uko
 */
public class Node<dataType>
{
	/**
	 * The data in the stack node.
	 */
	public dataType data;
	/**
	 * Next node.
	 */
	public Node next;
	/**
	 * Constructs basic node with empty data and next fields.
	 */
	public Node()
	{
		data = null;
		next = null;
	}
	/**
	 * Constructs a node with the passed object and null pointer.
	 * @param param Object to be stored in a node.
	 */
	public Node(dataType param)
	{
		data = param;
		next = null;
	}
}