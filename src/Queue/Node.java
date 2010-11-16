/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Queue;

/**
 * A priority queue node featuring data stored in a node and a pointer to a next node.
 * @param <dataType> a type of data stored in a node
 * @author uko
 */
public class Node<dataType>
{
	/**
	 * Field to store data.
	 */
	dataType data;
	/**
	 * Pointer to the next node.
	 */
	Node<dataType> next;

	/**
	 * Default constructor with no parameters: assign null to all fields.
	 */
	public Node()
	{
		this(null,null);
	}

	/**
	 * Constructor from some data: assigns it to this node data field.
	 * @param data	- data to store in this node
	 */
	Node(dataType data)
	{
		this(data,null);
	}

	/**
	 * Constructor from two elements: data to store and next element node to point at.
	 * @param data	- data to store in this node
	 * @param next	- next node
	 */
	public Node(dataType data, Node<dataType> next)
	{
		this.data = data;
		this.next = next;
	}
}
