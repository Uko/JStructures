/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Table;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * A simple table. Keys are stored in the linked list.
 * @param <keyType> a type of key
 * @param <bodyType> a type of body
 * @author uko
 */
public class SimpleTable<keyType,bodyType> implements Iterable<bodyType>
{

	private LinkedList<KeyStructure<keyType, bodyType>> storage;


	/**
	 * A simple constructor, initializes linkedList
	 */
	public SimpleTable()
	{
		storage = new LinkedList<KeyStructure<keyType, bodyType>>();
	}


	/**
	 * Copy constructor
	 * @param instance
	 */
	public SimpleTable(SimpleTable<keyType, bodyType> instance)
	{
		this();
		for(KeyStructure<keyType, bodyType> node : instance.storage)
			this.storage.add(new KeyStructure<keyType, bodyType>(node.value, node.body));
	}


	/**
	 * Add an element
	 * @param key key of element
	 * @param data body for a key
	 * @throws NullPointerException if a null key is passed
	 * @throws NoSuchElementException if such key exists
	 */
	public void add(keyType key, bodyType data) throws NullPointerException, NoSuchElementException
	{
		if(key == null)
			throw new NullPointerException("public void add(keyType key, bodyType data)\nError: key == null.");
		if(contains(key))
		{
			throw new NoSuchElementException();
		}
		storage.add(new KeyStructure<keyType, bodyType>(key, data));
	}

	/**
	 * Replace an element
	 * @param key key for which replace
	 * @param data what to replace
	 * @throws NullPointerException if a null key is passed
	 * @throws NoSuchElementException if no such key exists
	 */
	public void replace(keyType key, bodyType data) throws NullPointerException, NoSuchElementException
	{
		if(key == null)
			throw new NullPointerException("public void add(keyType key, bodyType data)\nError: key == null.");
		if(!contains(key))
		{
			throw new NoSuchElementException();
		}
		for(KeyStructure<keyType, bodyType> node : storage)
			if(node.value.equals(key))
			{
				node.body = data;
				return;
			}
	}

	/**
	 * Checks if such key exists
	 * @param key key to check
	 * @return true if exists
	 */
	public boolean contains(keyType key)
	{
		for(KeyStructure<keyType, bodyType> node : storage)
			if(node.value.equals(key))
			{
				return true;
			}
		return false;
	}

	/**
	 * Removes a key with a body
	 * @param key to remove
	 * @return corresponding body
	 * @throws NullPointerException if null key passed
	 * @throws NoSuchElementException if no such key exists
	 */
	public bodyType remove(keyType key) throws NullPointerException, NoSuchElementException
	{
		if(key == null)
			throw new NullPointerException("public bodyType remove(keyType key)\nError: key == null.");
		int i = 0;
		for(KeyStructure<keyType, bodyType> node : storage)
		{
			if(node.value.equals(key))
			{
				bodyType tmpData = node.body;
				storage.remove(i);
				return tmpData;
			}
			i++;
		}
		throw new NoSuchElementException();
	}

	/**
	 * Gets a body of passed key
	 * @param key to look for
	 * @return body of a certain key
	 * @throws NullPointerException if null key passed
	 * @throws NoSuchElementException if no such key exists
	 */
	public bodyType get(keyType key) throws NullPointerException, NoSuchElementException
	{
		if(key == null)
			throw new NullPointerException("public bodyType get(keyType key)\nError: key == null.");
		for(KeyStructure<keyType, bodyType> node : storage)
			if(node.value.equals(key))
			{
				return node.body;
			}
		throw new NoSuchElementException();
	}

	/**
	 * Amount of keys in table
	 * @return table size
	 */
	public int size()
	{
		return storage.size();
	}


	@Override
	public Iterator<bodyType> iterator()
	{
		return new SimpleTableIterator();
	}

	private class SimpleTableIterator implements Iterator<bodyType>
	{

		private Iterator<KeyStructure<keyType, bodyType>> iter;

		public SimpleTableIterator()
		{
			iter = storage.iterator();
		}

		@Override
		public boolean hasNext()
		{
			return iter.hasNext();
		}

		@Override
		public bodyType next() throws NoSuchElementException
		{
			KeyStructure<keyType, bodyType> tmpNode = iter.next();
			return tmpNode.body;
		}
		
		@Override
		public void remove() throws IllegalStateException
		{
			iter.remove();
		}
	}
}

