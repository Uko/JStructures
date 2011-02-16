/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Set;

import Tree.RBliver;

/**
 *
 * @author uko
 */
public class Set<type extends Comparable<? super type>>
{
	/**
	 * Basement tree for inside elements storage.
	 */
	protected RBliver<type> content;

	/**
	 * Basic constructor. 
	 */
	public Set()
	{
		content = new RBliver<type>();
	}

	/**
	 * Copy constructor.
	 * @param param - set to copy.
	 */
	public Set(Set<type> param)
	{
		this.content = new RBliver<type>();
		for (RBliver<type>.Enumerator<type> en = param.content.enumerator(); en.hasMoreElements();)
		{
			this.content.insert((type) en.nextElement());
		}
	}

	/**
	 * Adds element to this set.
	 * @param element - element to add to this set.
	 * @return true.
	 */
	public void add(type element)
	{
		content.insert(element);
	}

	/**
	 * Deletes element from this set.
	 * @param element - element to delete from this set.
	 * @return deleted element or null if element was not found in this set.
	 */
	public void delete(type element)
	{
		content.delete(element);
	}

	/**
	 * Set union.
	 * @param param - another set to unite with this set.
	 * @return new set - the result of union.
	 */
	public Set<type> unite(Set<type> param)
	{
		Set<type> temp = new Set<type>(this);
		for (RBliver<type>.Enumerator<type> en = param.content.enumerator(); en.hasMoreElements();)
		{//run through elements of the param tree
			temp.content.insert((type) en.nextElement());
		}
		return temp;
	}

	/**
	 * Set intersection.
	 * @param param - another set to intersect with this set.
	 * @return new set - the result of interaction.
	 */
	public Set<type> intersect(Set<type> param)
	{
		Set<type> temp = new Set<type>();
		for (RBliver<type>.Enumerator<type> en = param.content.enumerator(); en.hasMoreElements();)
		{//run through elements of the param tree
			type el = (type) en.nextElement();
			if (this.content.exists(el))
			{//if element from param tree is present in this tree
				temp.add(el);
			}
		}
		return temp;
	}

	/**
	 * Set difference.
	 * @param param - another set to difference from this set.
	 * @return new set - the result of difference.
	 */
	public Set<type> difference(Set<type> param)
	{
		Set<type> result = new Set<type>();
		for (RBliver<type>.Enumerator<type> en = this.content.enumerator(); en.hasMoreElements();)
		{//run through elements of this tree
			type el = (type) en.nextElement();
			if (!param.content.exists(el))
			{//if element of this tree is absent in the tree from parameter
				result.add(el);
			}
		}
		return result;
	}
	@Override
	public String toString()
	{
		String temp = new String();
		for (RBliver<type>.Enumerator<type> en = this.content.enumerator(); en.hasMoreElements();)
		{
			temp+=(type)en.nextElement()+" ";
		}
		return temp.trim();
	}
}
