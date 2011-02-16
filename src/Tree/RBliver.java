/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import java.util.ArrayList;
import java.util.Enumeration;

/**
 *
 * @param <keyType> data type of a key. Should be comparable
 * @param <dataType> data type of a body referring to a key&
 * @author uko
 */
public class RBliver<keyType extends Comparable<? super keyType>>
{
	public class Enumerator<keyType extends Comparable<? super keyType>> implements Enumeration<keyType>
	{
		private ArrayList<keyType> content;
		private int index;
		public Enumerator()
		{
			if (root != null)
			{
				content = new ArrayList<keyType>();
				assemble(root);
			}
			index=0;
		}
		private void assemble(Node<?> n)
		{
			if (n.left != null)
			{
				assemble(n.left);
			}
			content.add((keyType) n.key);
			if (n.right != null)
			{
				assemble(n.right);
			}
		}
		@Override
		public boolean hasMoreElements()
		{
			return index <= content.size()-1;
		}
		@Override
		public keyType nextElement()
		{
			return content.get(index++);
		}
	}

	private static class Node<keyType extends Comparable<? super keyType>>
	{
		public keyType key;
		public Node<keyType> left;
		public Node<keyType> right;
		public Node<keyType> parent;
		public Color color;
		public Node(keyType key, Color nodeColor, Node<keyType> left, Node<keyType> right)
		{
			this.key = key;
			this.color = nodeColor;
			this.left = left;
			this.right = right;
			if (left != null)
			{
				left.parent = this;
			}
			if (right != null)
			{
				right.parent = this;
			}
			this.parent = null;
		}
		public Node<keyType> grandpa()
		{
			return parent.parent;
		}
		public Node<keyType> brother()
		{
			if (this == parent.left)
			{
				return parent.right;
			}
			else
			{
				return parent.left;
			}
		}
		public Node<keyType> uncle()
		{
			return parent.brother();
		}
	}

	enum Color
	{
		RED, BLACK
	};

	private Node<keyType> root;
	/**
	 * casual constructor
	 */
	public RBliver()
	{
		root = null;
	}
	private Color nodeColor(Node<?> param)
	{
		if (param == null)
		{
			return Color.BLACK;
		}
		return param.color;
	}
	public boolean exists(keyType key)
	{
		return lookupNode(key) != null;
	}
	private Node<keyType> lookupNode(keyType key)
	{
		Node<keyType> n = root;
		while (n != null)
		{
			int compResult = key.compareTo(n.key);
			if (compResult == 0)
			{
				return n;
			}
			else
			{
				if (compResult < 0)
				{
					n = n.left;
				}
				else
				{
					n = n.right;
				}
			}
		}
		return n;
	}
	private void rotateLeft(Node<keyType> n)
	{
		Node<keyType> r = n.right;
		replaceNode(n, r);
		n.right = r.left;
		if (r.left != null)
		{
			r.left.parent = n;
		}
		r.left = n;
		n.parent = r;
	}
	private void rotateRight(Node<keyType> n)
	{
		Node<keyType> l = n.left;
		replaceNode(n, l);
		n.left = l.right;
		if (l.right != null)
		{
			l.right.parent = n;
		}
		l.right = n;
		n.parent = l;
	}
	private void replaceNode(Node<keyType> oldn, Node<keyType> newn)
	{
		if (oldn.parent == null)
		{
			root = newn;
		}
		else
		{
			if (oldn == oldn.parent.left)
			{
				oldn.parent.left = newn;
			}
			else
			{
				oldn.parent.right = newn;
			}
		}
		if (newn != null)
		{
			newn.parent = oldn.parent;
		}
	}
	/**
	 * Inserts a node
	 * @param key
	 * @param value
	 */
	public void insert(keyType key)
	{
		Node<keyType> insertedNode = new Node<keyType>(key, Color.RED, null, null);
		if (root == null)
		{
			root = insertedNode;
		}
		else
		{
			Node<keyType> runner = root;
			while (true)
			{
				int compResult = key.compareTo(runner.key);
				if (compResult == 0)
				{
					return;
				}
				else
				{
					if (compResult < 0)
					{
						if (runner.left == null)
						{
							runner.left = insertedNode;
							break;
						}
						else
						{
							runner = runner.left;
						}
					}
					else
					{
						if (runner.right == null)
						{
							runner.right = insertedNode;
							break;
						}
						else
						{
							runner = runner.right;
						}
					}
				}
			}
			insertedNode.parent = runner;
		}
		insertCase1(insertedNode);
	}
	/**
	 * The new node N is at the root of the tree. In this case, it is repainted
	 * black to satisfy Property 2 (The root is black).
	 * Since this adds one black node to every path at once, Property 5
	 * (All paths from any given node to its leaf nodes contain the same number of black nodes) is not violated.
	 * @param n added node
	 */
	private void insertCase1(Node<keyType> n)
	{
		if (n.parent == null)
		{
			n.color = Color.BLACK;
		}
		else
		{
			insertCase2(n);
		}
	}
	/**
	 * The new node's parent P is black, so Property 4 (Both children of every red node are black) is not invalidated.
	 * In this case, the tree is still valid.
	 * Property 5 (All paths from any given node to its leaf nodes contain the same number of black nodes) is not threatened,
	 * because the new node N has two black leaf children, but because N is red, the paths through each of its children
	 * have the same number of black nodes as the path through the leaf it replaced,
	 * which was black, and so this property remains satisfied.
	 * @param n
	 */
	private void insertCase2(Node<keyType> n)
	{
		if (nodeColor(n.parent) == Color.BLACK)
		{
			return;
		}
		else
		{
			insertCase3(n);
		}
	}
	/**
	 * If both the parent P and the uncle U are red, then both of them can be repainted black
	 * and the grandparent G becomes red (to maintain Property 5
	 * (All paths from any given node to its leaf nodes contain the same number of black nodes)).
	 * Now, the new red node N has a black parent. Since any path through the parent or uncle must pass through the grandparent,
	 * the number of black nodes on these paths has not changed.
	 * However, the grandparent G may now violate properties 2 (The root is black)
	 * or 4 (Both children of every red node are black) (property 4 possibly being violated since G may have a red parent).
	 * To fix this, the entire procedure is recursively performed on G from case 1.
	 * Note that this is a tail-recursive call, so it could be rewritten as a loop;
	 * since this is the only loop, and any rotations occur after this loop, this proves that a constant number of rotations occur.
	 * @param n
	 */
	private void insertCase3(Node<keyType> n)
	{
		if (nodeColor(n.uncle()) == Color.RED)
		{
			n.parent.color = Color.BLACK;
			n.uncle().color = Color.BLACK;
			n.grandpa().color = Color.RED;
			insertCase1(n.grandpa());
		}
		else
		{
			insertCase4(n);
		}
	}
	/**
	 * The parent P is red but the uncle U is black; also, the new node N is the right child of P,
	 * and P in turn is the left child of its parent G. In this case,
	 * a left rotation that switches the roles of the new node N and its parent P can be performed;
	 * then, the former parent node P is dealt with using Case 5 (relabeling N and P)
	 * because property 4 (Both children of every red node are black) is still violated.
	 * The rotation causes some paths (those in the sub-tree labelled "1") to pass through the new node where they did not before,
	 * but both these nodes are red, so Property 5
	 * (All paths from any given node to its leaf nodes contain the same number of black nodes)is not violated by the rotation.
	 * @param n
	 */
	private void insertCase4(Node<keyType> n)
	{
		if (n == n.parent.right && n.parent == n.grandpa().left)
		{
			rotateLeft(n.parent);
			n = n.left;
		}
		else
		{
			if (n == n.parent.left && n.parent == n.grandpa().right)
			{
				rotateRight(n.parent);
				n = n.right;
			}
		}
		insertCase5(n);
	}
	/**
	 * Case 5: The parent P is red but the uncle U is black, the new node N is the left child of P,
	 * and P is the left child of its parent G. In this case, a right rotation on G is performed;
	 * the result is a tree where the former parent P is now the parent of both the new node N and the former grandparent G.
	 * G is known to be black, since its former child P could not have been red otherwise (without violating Property 4).
	 * Then, the colors of P and G are switched, and the resulting tree satisfies Property 4 (Both children of every red node are black).
	 * Property 5 (All paths from any given node to its leaf nodes contain the same number of black nodes) also remains satisfied,
	 * since all paths that went through any of these three nodes went through G before, and now they all go through P.
	 * In each case, this is the only black node of the three.
	 * @param n
	 */
	private void insertCase5(Node<keyType> n)
	{
		n.parent.color = Color.BLACK;
		n.grandpa().color = Color.RED;
		if (n == n.parent.left && n.parent == n.grandpa().left)
		{
			rotateRight(n.grandpa());
		}
		else
		{
			rotateLeft(n.grandpa());
		}
	}
	/**
	 * remove a node of a specific key
	 * @param key to remove
	 */
	public void delete(keyType key)
	{
		Node<keyType> n = lookupNode(key);
		if (n == null)
		{
			return;
		}
		if (n.left != null && n.right != null)
		{
			Node<keyType> pred = maximumNode(n.left);
			n.key = pred.key;
			n = pred;
		}

		Node<keyType> child;
		if (n.right == null)
		{
			child = n.left;
		}
		else
		{
			child = n.right;
		}
		if (nodeColor(n) == Color.BLACK)
		{
			n.color = nodeColor(child);
			deleteCase1(n);
		}
		replaceNode(n, child);

		if (nodeColor(root) == Color.RED)
		{
			root.color = Color.BLACK;
		}
	}
	private <K extends Comparable<? super K>> Node<K> maximumNode(Node<K> n)
	{
		while (n.right != null)
		{
			n = n.right;
		}
		return n;
	}
	/**
	 * N is the new root. In this case, we are done. We removed one black node from every path,
	 * and the new root is black, so the properties are preserved.
	 * @param n
	 */
	private void deleteCase1(Node<keyType> n)
	{
		if (n.parent == null)
		{
			return;
		}
		else
		{
			deleteCase2(n);
		}
	}
	/**
	 * S is red. In this case we reverse the colors of P and S, and then rotate left at P, turning S into N's grandparent.
	 * Note that P has to be black as it had a red child. Although all paths still have the same number of black nodes,
	 * now N has a black sibling and a red parent, so we can proceed to step 4, 5, or 6.
	 * (Its new sibling is black because it was once the child of the red S.) In later cases, we will relabel N's new sibling as S.
	 * @param n
	 */
	private void deleteCase2(Node<keyType> n)
	{
		if (nodeColor(n.brother()) == Color.RED)
		{
			n.parent.color = Color.RED;
			n.brother().color = Color.BLACK;
			if (n == n.parent.left)
			{
				rotateLeft(n.parent);
			}
			else
			{
				rotateRight(n.parent);
			}
		}
		deleteCase3(n);
	}
	/**
	 * P, S, and S's children are black. In this case, we simply repaint S red.
	 * The result is that all paths passing through S, which are precisely those paths not passing through N,
	 * have one less black node. Because deleting N's original parent made all paths passing through N have one less black node,
	 * this evens things up. However, all paths through P now have one fewer black node than paths that do not pass through P,
	 * so Property 5 (All paths from any given node to its leaf nodes contain the same number of black nodes) is still violated.
	 * To correct this, we perform the rebalancing procedure on P, starting at case 1.
	 * @param n
	 */
	private void deleteCase3(Node<keyType> n)
	{
		if (nodeColor(n.parent) == Color.BLACK
				&& nodeColor(n.brother()) == Color.BLACK
				&& nodeColor(n.brother().left) == Color.BLACK
				&& nodeColor(n.brother().right) == Color.BLACK)
		{
			n.brother().color = Color.RED;
			deleteCase1(n.parent);
		}
		else
		{
			deleteCase4(n);
		}
	}
	/**
	 * S and S's children are black, but P is red. In this case, we simply exchange the colors of S and P.
	 * This does not affect the number of black nodes on paths going through S,
	 * but it does add one to the number of black nodes on paths going through N,
	 * making up for the deleted black node on those paths.
	 * @param n
	 */
	private void deleteCase4(Node<keyType> n)
	{
		if (nodeColor(n.parent) == Color.RED
				&& nodeColor(n.brother()) == Color.BLACK
				&& nodeColor(n.brother().left) == Color.BLACK
				&& nodeColor(n.brother().right) == Color.BLACK)
		{
			n.brother().color = Color.RED;
			n.parent.color = Color.BLACK;
		}
		else
		{
			deleteCase5(n);
		}
	}
	/**
	 * S is black, S's left child is red, S's right child is black, and N is the left child of its parent.
	 * In this case we rotate right at S, so that S's left child becomes S's parent and N's new sibling.
	 * We then exchange the colors of S and its new parent. All paths still have the same number of black nodes,
	 * but now N has a black sibling whose right child is red, so we fall into case 6.
	 * Neither N nor its parent are affected by this transformation. (Again, for case 6, we relabel N's new sibling as S.)
	 * @param n
	 */
	private void deleteCase5(Node<keyType> n)
	{
		if (n == n.parent.left
				&& nodeColor(n.brother()) == Color.BLACK
				&& nodeColor(n.brother().left) == Color.RED
				&& nodeColor(n.brother().right) == Color.BLACK)
		{
			n.brother().color = Color.RED;
			n.brother().left.color = Color.BLACK;
			rotateRight(n.brother());
		}
		else
		{
			if (n == n.parent.right
					&& nodeColor(n.brother()) == Color.BLACK
					&& nodeColor(n.brother().right) == Color.RED
					&& nodeColor(n.brother().left) == Color.BLACK)
			{
				n.brother().color = Color.RED;
				n.brother().right.color = Color.BLACK;
				rotateLeft(n.brother());
			}
		}
		deleteCase6(n);
	}
	/**
	 * S is black, S's right child is red, and N is the left child of its parent P.
	 * In this case we rotate left at P, so that S becomes the parent of P and S's right child.
	 * We then exchange the colors of P and S, and make S's right child black.
	 * The subtree still has the same color at its root, so Properties 4 (Both children of every red node are black)
	 * and 5 (All paths from any given node to its leaf nodes contain the same number of black nodes) are not violated.
	 * However, N now has one additional black ancestor: either P has become black,
	 * or it was black and S was added as a black grandparent. Thus, the paths passing through N pass through one additional black node.
	 * @param n
	 */
	private void deleteCase6(Node<keyType> n)
	{
		n.brother().color = nodeColor(n.parent);
		n.parent.color = Color.BLACK;
		if (n == n.parent.left)
		{
			n.brother().right.color = Color.BLACK;
			rotateLeft(n.parent);
		}
		else
		{
			n.brother().left.color = Color.BLACK;
			rotateRight(n.parent);
		}
	}
	/**
	 * Print a tree into a console
	 */
	public void print()
	{
		printHelper(root, 0);
	}
	private void printHelper(Node<?> n, int indent)
	{
		if (n == null)
		{
			System.out.print("<empty tree>");
			return;
		}
		if (n.right != null)
		{
			printHelper(n.right, indent + 2);
		}
		for (int i = 0; i < indent; i++)
		{
			System.out.print(" ");
		}
		if (n.color == Color.BLACK)
		{
			System.out.println(n.key);
		}
		else
		{
			System.out.println(/*"<" + */n.key + "!");
		}
		if (n.left != null)
		{
			printHelper(n.left, indent + 2);
		}
	}
	private boolean verifyProperty1(Node<?> root)
	{
		return nodeColor(root) == Color.BLACK;
	}
	private boolean verifyProperty2(Node<?> n)
	{
		if (n == null)
		{
			return true;
		}
		if (nodeColor(n) == Color.RED)
		{
			return (nodeColor(n.left) == Color.BLACK)
					&& (nodeColor(n.right) == Color.BLACK)
					&& (nodeColor(n.parent) == Color.BLACK)
					&& verifyProperty2(n.left)
					&& verifyProperty2(n.right);
		}
		else
		{
			return verifyProperty2(n.left)
					&& verifyProperty2(n.right);
		}
	}
	private boolean verifyProperty3(Node<?> root)
	{
		try
		{
			verifyProperty3Helper(root, 0, -1);
		}
		catch (Exception ex)
		{
			return false;
		}
		return true;
	}
	private int verifyProperty3Helper(Node<?> n, int blackCount, int pathBlackCount) throws Exception
	{
		if (nodeColor(n) == Color.BLACK)
		{
			blackCount++;
		}
		if (n == null)
		{
			if (pathBlackCount == -1)
			{
				pathBlackCount = blackCount;
			}
			else
			{
				if (blackCount != pathBlackCount)
				{
					throw new Exception();
				}
			}
			return pathBlackCount;
		}
		pathBlackCount = verifyProperty3Helper(n.left, blackCount, pathBlackCount);
		pathBlackCount = verifyProperty3Helper(n.right, blackCount, pathBlackCount);
		return pathBlackCount;
	}
	/**
	 * Verifies if tree is correct.
	 * @return
	 */
	public boolean verifyProperties()
	{
		return verifyProperty1(root) && verifyProperty2(root) && verifyProperty3(root);
	}
	public Enumerator<keyType> enumerator()
	{
		return new Enumerator<keyType>();
	}
}
