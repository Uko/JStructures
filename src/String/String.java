/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package String;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author uko
 */
public class String implements StringInterface, Iterable<java.lang.Character>
{
	private Character paragraph;
	private int size;
	public String()
	{
		paragraph = null;
		size = 0;
	}
	public String(int size, char param)
	{
		this.size = size;
		for (int i = 0; i < size; i++)
		{
			paragraph = new Character(param, paragraph);
		}
	}
	public String(String param)
	{
		size = param.size;
		paragraph = param.paragraph;
	}
	public String(java.lang.String param)
	{
		size = param.length();
		if (size == 0)
		{
			return;
		}
		paragraph = new Character(param.charAt(0));
		Character folover = paragraph;
		for (int i = 1; i < size; i++)
		{
			folover.next = new Character(param.charAt(i));
			folover = folover.next;
		}
	}
	public String(char[] param)
	{
		size = param.length;
		if (size == 0)
		{
			return;
		}
		paragraph = new Character(param[0]);
		Character folover = paragraph;
		for (int i = 1; i < size; i++)
		{
			folover.next = new Character(param[i]);
			folover = folover.next;
		}
	}
	@Override
	public String concat(String param)
	{
		if (size == 0)
		{
			String temp = new String();
			temp.size = param.size();
			temp.paragraph = param.paragraph;
			return temp;
		}
		String temp = new String(this);
		temp.size += param.size;
		Character runner = temp.paragraph;
		while (runner.next != null)
		{
			runner = runner.next;
		}
		runner.next = param.paragraph;
		return temp;
	}
	@Override
	public boolean equals(String param)
	{
		if (size != param.size)
		{
			return false;
		}
		Iterator<java.lang.Character> comparable1 = iterator();
		for (java.lang.Character comparable2 : param)
		{
			if (comparable1.next() != comparable2)
			{
				return false;
			}
		}
		return true;
	}
	@Override
	public int size()
	{
		return size;
	}
	@Override
	public void replaceFirst(String insteadOf, String what) throws IllegalAccessException
	{
		if (what.isEmpty())
		{
			return;
		}
		if (insteadOf.isEmpty())
		{
			throw new IllegalAccessException("Replacing an empty string");
		}
		if (size < insteadOf.size)
		{
			throw new IllegalAccessException("Searched string is biger than an original one");
		}
		boolean atBegin = true;
		Character startpoint = paragraph;
		for (java.lang.Character i : insteadOf)
		{
			if (i != startpoint.value)
			{
				atBegin = false;
				break;
			}
			startpoint = startpoint.next;
		}
		Character endpoint = what.paragraph;
		while (endpoint.next != null)
		{
			endpoint = endpoint.next;
		}
		if (atBegin)
		{
			endpoint.next = startpoint.next;
			paragraph = what.paragraph;
			size += what.size - insteadOf.size;
			return;
		}
		Character cutBegin = null;
		for (Character i = paragraph; i.next != null; i = i.next)
		{
			if (i.next.value == insteadOf.paragraph.value)
			{
				cutBegin = i;
				boolean contains = true;
				Character runner = i.next;
				for (char comparator : insteadOf)
				{
					if (runner == null || runner.value != comparator)
					{
						contains = false;
						break;
					}
					runner = runner.next;
				}
				if (contains)
				{
					endpoint.next = runner;
					cutBegin.next = what.paragraph;
					size += what.size - insteadOf.size;
					return;
				}
				
			}
		}
		throw new IllegalAccessException("No such substring");
	}
	@Override
	public void insert(int after, String param) throws IllegalAccessException
	{
		if (after > size)
		{
			throw new IllegalAccessException("Requested notexisting index");
		}
		if (param.isEmpty())
		{
			return;
		}
		size += param.size;
		if (after == 0)
		{
			paragraph = param.paragraph;
			return;
		}
		Character endpoint = param.paragraph;
		while (endpoint.next != null)
		{
			endpoint = endpoint.next;
		}
		Character startpoint = param.paragraph;
		for (int i = 1; i < after; i++)
		{
			startpoint = startpoint.next;
		}
		endpoint = startpoint.next;
		startpoint.next = param.paragraph;
	}
	@Override
	public int indexOf(String param) throws IllegalAccessException
	{
		if (param.isEmpty())
		{
			throw new IllegalAccessException("Searching for an empty string");
		}
		if (size < param.size)
		{
			throw new IllegalAccessException("Searched string is biger than an original one");
		}
		int index = 0;
		for (Character i = paragraph; i.next != null; i = i.next)
		{
			if (i.value == param.paragraph.value)
			{
				boolean contains = true;
				Character runner = i;
				for (char comparator : param)
				{
					if (runner == null || runner.value != comparator)
					{
						contains = false;
						break;
					}
					runner = runner.next;
				}
				if (contains)
				{
					return index;
				}
			}
			index++;
		}
		throw new IllegalAccessException("No such substring");
	}
	@Override
	public int indexOf(char param) throws IllegalAccessException
	{
		int index = 0;
		for (char comparable : this)
		{
			if (param == comparable)
			{
				return index;
			}
			index++;
		}
		throw new IllegalAccessException("No such character");
	}
	@Override
	public boolean contains(String param)
	{
		if (param.isEmpty())
		{
			return false;
		}
		for (Character i = paragraph; i.next != null; i = i.next)
		{
			if (i.value == param.paragraph.value)
			{
				boolean contains = true;
				Character runner = i;
				for (char comparator : param)
				{
					if (runner == null || runner.value != comparator)
					{
						contains = false;
						break;
					}
					runner = runner.next;
				}
				if (contains)
				{
					return true;
				}
			}

		}
		return false;
	}
	@Override
	public java.lang.String toString()
	{
		java.lang.String temp = new java.lang.String();
		for (char runner : this)
		{
			temp += runner;
		}
		return temp;
	}
	@Override
	public Iterator<java.lang.Character> iterator()
	{
		return new StringIterator();
	}
	@Override
	public String clone()
	{
		String temp = new String();
		temp.size = size;
		if (size == 0)
		{
			return temp;
		}
		Iterator<java.lang.Character> runner = iterator();
		temp.paragraph = new Character(runner.next());
		Character folover = temp.paragraph;
		while (runner.hasNext())
		{
			folover.next = new Character(runner.next());
			folover = folover.next;
		}
		return temp;
	}
	@Override
	public boolean isEmpty()
	{
		return paragraph == null;
	}

	private class StringIterator implements Iterator<java.lang.Character>
	{
		private Character runner;
		private boolean canNext = false;
		public StringIterator()
		{
			runner = paragraph;
		}
		@Override
		public boolean hasNext()
		{
			return runner != null;
		}
		@Override
		public java.lang.Character next()
		{
			if (runner == null)
			{
				throw new NoSuchElementException();
			}
			char temp = runner.value;
			runner = runner.next;
			canNext = true;
			return temp;
		}
		@Override
		public void remove()
		{
			if (!canNext)
			{
				throw new IllegalStateException();
			}
			if (runner == paragraph.next)
			{
				paragraph = paragraph.next;
				canNext = false;
				size--;
				return;
			}
			Character helper = paragraph;
			while (helper.next.next != runner)
			{
				helper = helper.next;
			}
			helper.next = helper.next.next;
			canNext = false;
			size--;
		}
	}

}
