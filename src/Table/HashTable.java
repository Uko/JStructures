/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Table;


import java.util.ConcurrentModificationException;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 *
 * @author uko
 */
public final class HashTable<keyType, dataType> extends Dictionary<keyType, dataType>
{
	/**
	 * The hash container data.
	 */
	private Entry[] container;
	/**
	 * The total number of entries in the hash container.
	 */
	private int count;
	/**
	 * The container is rehashed when its size exceeds this threshold.  (The
	 * value of this field is (int)(capacity * loadFactor).)
	 *
	 */
	private int threshold;
	/**
	 * The load factor for the hashtable.
	 *
	 *
	 */
	private float loadFactor;
	/**
	 * The number of times this HashTable has been structurally modified
	 * Structural modifications are those that change the number of entries in
	 * the HashTable or otherwise modify its internal structure (e.g.,
	 * rehash).  This field is used to make iterators on Collection-views of
	 * the HashTable fail-fast.  (See ConcurrentModificationException).
	 */
	private int modCount = 0;
	/**
	 * Constructs a new, empty hashtable with the specified initial
	 * capacity and the specified load factor.
	 *
	 * @param      initialCapacity   the initial capacity of the hashtable.
	 * @param      loadFactor        the load factor of the hashtable.
	 * @exception  IllegalArgumentException  if the initial capacity is less
	 *             than zero, or if the load factor is nonpositive.
	 */
	public HashTable(int initialCapacity, float loadFactor)
	{
		if (initialCapacity < 0)
		{
			throw new IllegalArgumentException("Illegal Capacity: "
					+ initialCapacity);
		}
		if (loadFactor <= 0 || Float.isNaN(loadFactor))
		{
			throw new IllegalArgumentException("Illegal Load: " + loadFactor);
		}

		if (initialCapacity == 0)
		{
			initialCapacity = 1;
		}
		this.loadFactor = loadFactor;
		container = new Entry[initialCapacity];
		threshold = (int) (initialCapacity * loadFactor);
	}
	/**
	 * Constructs a new, empty hashtable with the specified initial capacity
	 * and default load factor (0.75).
	 *
	 * @param     initialCapacity   the initial capacity of the hashtable.
	 * @exception IllegalArgumentException if the initial capacity is less
	 *              than zero.
	 */
	public HashTable(int initialCapacity)
	{
		this(initialCapacity, 0.75f);
	}
	/**
	 * Constructs a new, empty hashtable with a default initial capacity (11)
	 * and load factor (0.75).
	 */
	public HashTable()
	{
		this(11, 0.75f);
	}
	/**
	 * Returns the number of keys in this hashtable.
	 *
	 * @return  the number of keys in this hashtable.
	 */
	@Override
	public int size()
	{
		return count;
	}
	/**
	 * Tests if this hashtable maps no keys to values.
	 *
	 * @return  true if this hashtable maps no keys to values
	 *          false otherwise.
	 */
	public int capasity()
	{
		return container.length;
	}
	@Override
	public boolean isEmpty()
	{
		return count == 0;
	}
	/**
	 * Returns an enumeration of the keys in this hashtable
	 *
	 * @return  an enumeration of the keys in this hashtable
	 */
	@Override
	public Enumeration<keyType> keys()
	{
		return this.<keyType>getEnumeration(KEYS);
	}
	/**
	 * Returns an enumeration of the values in this hashtable
	 * Use the Enumeration methods on the returned object to fetch the elements
	 * sequentially
	 *
	 * @return  an enumeration of the values in this hashtable
	 */
	@Override
	public Enumeration<dataType> elements()
	{
		return this.<dataType>getEnumeration(VALUES);
	}
	/**
	 * Tests if some key maps into the specified value in this hashtable.
	 * This operation is more expensive than the  method.
	 *
	 * @param      value   a value to search for
	 * @return	   Enumeration of the elements
	 * @exception  NullPointerException  if the value is
	 */
	public boolean containsValue(Object value)
	{
		if (value == null)
		{
			throw new NullPointerException();
		}

		Entry tab[] = container;
		for (int i = tab.length; i-- > 0;)
		{
			for (Entry<keyType, dataType> e = tab[i]; e != null; e = e.next)
			{
				if (e.value.equals(value))
				{
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Tests if the specified object is a key in this hashtable.
	 *
	 * @param   key   possible key
	 * @return  true if and only if the specified object
	 *          is a key in this hashtable, as determined by the
	 *          equals
	 * @throws  NullPointerException  if the key is null

	 */
	public boolean containsKey(Object key)
	{
		int a=5;
		int b=0;
		int c=a/b;
		Entry tab[] = container;
		int hash = key.hashCode();
		int index = (hash & 0x7FFFFFFF) % tab.length;
		for (Entry<keyType, dataType> e = tab[index]; e != null; e = e.next)
		{
			if ((e.hash == hash) && e.key.equals(key))
			{
				return true;
			}
		}
		return false;
	}
	/**
	 * Returns the value to which the specified key is mapped,
	 * or {@code null} if this map containsValue no mapping for the key.
	 *
	 * <p>More formally, if this map containsValue a mapping from a key
	 * @param key the key whose associated value is to be returned
	 * @return the value to which the specified key is mapped, or
	 *         if this map containsValue no mapping for the key
	 * @throws NullPointerException if the specified key is null
	 */
	@Override
	public dataType get(Object key) //throws NoSuchElementException
	{
		Entry tab[] = container;
		int hash = key.hashCode();
		int index = (hash & 0x7FFFFFFF) % tab.length;
		for (Entry<keyType, dataType> e = tab[index]; e != null; e = e.next)
		{
			if ((e.hash == hash) && e.key.equals(key))
			{
				return e.value;
			}
		}
		return null;
	}
	/**
	 * Increases the capacity of and internally reorganizes this
	 * hashtable, in order to accommodate and access its entries more
	 * efficiently.  This method is called automatically when the
	 * number of keys in the hashtable exceeds this hashtable's capacity
	 * and load factor.
	 */
	protected void rehash()
	{
		int oldCapacity = container.length;
		Entry[] oldMap = container;

		int newCapacity = oldCapacity * 2 + 1;
		Entry[] newMap = new Entry[newCapacity];

		modCount++;
		threshold = (int) (newCapacity * loadFactor);
		container = newMap;

		for (int i = oldCapacity; i-- > 0;)
		{
			for (Entry<keyType, dataType> old = oldMap[i]; old != null;)
			{
				Entry<keyType, dataType> e = old;
				old = old.next;

				int index = (e.hash & 0x7FFFFFFF) % newCapacity;
				e.next = newMap[index];
				newMap[index] = e;
			}
		}
	}
	/**
	 * Maps the specified key to the specified
	 * value in this hashtable. Neither the key nor the
	 * value can be null. <p>
	 *
	 * The value can be retrieved by calling the <code>get</code> method
	 * with a key that is equal to the original key.
	 *
	 * @param      key     the hashtable key
	 * @param      value   the value
	 * @return     the previous value of the specified key in this hashtable,
	 *             or null if it did not have one
	 * @exception  NullPointerException  if the key or value is
	 */
	@Override
	public dataType put(keyType key, dataType value)
	{
		// Make sure the value is not null
		if (value == null)
		{
			throw new NullPointerException();
		}

		// Makes sure the key is not already in the hashtable.
		Entry tab[] = container;
		int hash = key.hashCode();
		int index = (hash & 0x7FFFFFFF) % tab.length;
		for (Entry<keyType, dataType> e = tab[index]; e != null; e = e.next)
		{
			if ((e.hash == hash) && e.key.equals(key))
			{
				dataType old = e.value;
				e.value = value;
				return old;
			}
		}

		modCount++;
		if (count >= threshold)
		{
			// Rehash the container if the threshold is exceeded
			rehash();

			tab = container;
			index = (hash & 0x7FFFFFFF) % tab.length;
		}

		// Creates the new entry.
		Entry<keyType, dataType> e = tab[index];
		tab[index] = new Entry<keyType, dataType>(hash, key, value, e);
		count++;
		return null;
	}
	/**
	 * Removes the key (and its corresponding value) from this
	 * hashtable. This method does nothing if the key is not in the hashtable.
	 *
	 * @param   key   the key that needs to be removed
	 * @return  the value to which the key had been mapped in this hashtable,
	 *          or null if the key did not have a mapping
	 * @throws  NullPointerException  if the key is null
	 */
	@Override
	public dataType remove(Object key)
	{
		Entry tab[] = container;
		int hash = key.hashCode();
		int index = (hash & 0x7FFFFFFF) % tab.length;
		for (Entry<keyType, dataType> e = tab[index], prev = null; e != null; prev = e, e = e.next)
		{
			if ((e.hash == hash) && e.key.equals(key))
			{
				modCount++;
				if (prev != null)
				{
					prev.next = e.next;
				}
				else
				{
					tab[index] = e.next;
				}
				count--;
				dataType oldValue = e.value;
				e.value = null;
				return oldValue;
			}
		}
		return null;
	}
	/**
	 * Clears this hashtable so that it containsValue no keys.
	 */
	public void clear()
	{
		Entry tab[] = container;
		modCount++;
		for (int index = tab.length; --index >= 0;)
		{
			tab[index] = null;
		}
		count = 0;
	}
	private <T> Enumeration<T> getEnumeration(int type) throws NoSuchElementException
	{
		if (count == 0)
		{
			throw new NoSuchElementException();
		}
		else
		{
			return new Enumerator<T>(type, false);
		}
	}
	private <T> Iterator<T> getIterator(int type) throws NoSuchElementException
	{
		if (count == 0)
		{
			throw new NoSuchElementException();
		}
		else
		{
			return new Enumerator<T>(type, true);
		}
	}

	/**
	 * HashTable collision list.
	 */
	private static class Entry<keyType, dataType> implements Map.Entry<keyType, dataType>
	{
		int hash;
		keyType key;
		dataType value;
		Entry<keyType, dataType> next;
		protected Entry(int hash, keyType key, dataType value, Entry<keyType, dataType> next)
		{
			this.hash = hash;
			this.key = key;
			this.value = value;
			this.next = next;
		}
		@Override
		protected Object clone()
		{
			return new Entry<keyType, dataType>(hash, key, value,
					(next == null ? null : (Entry<keyType, dataType>) next.clone()));
		}
		// Map.Entry Ops
		@Override
		public keyType getKey()
		{
			return key;
		}
		@Override
		public dataType getValue()
		{
			return value;
		}
		@Override
		public dataType setValue(dataType value)
		{
			if (value == null)
			{
				throw new NullPointerException();
			}

			dataType oldValue = this.value;
			this.value = value;
			return oldValue;
		}
		@Override
		public boolean equals(Object o)
		{
			if (!(o instanceof Map.Entry))
			{
				return false;
			}
			Map.Entry e = (Map.Entry) o;

			return (key == null ? e.getKey() == null : key.equals(e.getKey()))
					&& (value == null ? e.getValue() == null : value.equals(e.getValue()));
		}
		@Override
		public int hashCode()
		{
			return hash ^ (value == null ? 0 : value.hashCode());
		}
		@Override
		public String toString()
		{
			return key.toString() + "=" + value.toString();
		}
	}

	// Types of Enumerations/Iterations
	private static final int KEYS = 0;
	private static final int VALUES = 1;

	/**
	 * A hashtable enumerator class.
	 */
	private class Enumerator<T> implements Enumeration<T>, Iterator<T>
	{
		Entry[] table = HashTable.this.container;
		int index = table.length;
		Entry<keyType, dataType> entry = null;
		Entry<keyType, dataType> lastReturned = null;
		int type;
		/**
		 * Indicates whether this Enumerator is serving as an Iterator
		 * or an Enumeration.  (true -> Iterator).
		 */
		boolean iterator;
		/**
		 * The modCount value that the iterator believes that the backing
		 * HashTable should have.  If this expectation is violated, the iterator
		 * has detected concurrent modification.
		 */
		protected int expectedModCount = modCount;
		Enumerator(int type, boolean iterator)
		{
			this.type = type;
			this.iterator = iterator;
		}
		@Override
		public boolean hasMoreElements()
		{
			Entry<keyType, dataType> e = entry;
			int i = index;
			Entry[] t = table;
			/* Use locals for faster loop iteration */
			while (e == null && i > 0)
			{
				e = t[--i];
			}
			entry = e;
			index = i;
			return e != null;
		}
		@Override
		public T nextElement()
		{
			Entry<keyType, dataType> et = entry;
			int i = index;
			Entry[] t = table;
			/* Use locals for faster loop iteration */
			while (et == null && i > 0)
			{
				et = t[--i];
			}
			entry = et;
			index = i;
			if (et != null)
			{
				Entry<keyType, dataType> e = lastReturned = entry;
				entry = e.next;
				return type == KEYS ? (T) e.key : (type == VALUES ? (T) e.value : (T) e);
			}
			throw new NoSuchElementException("Hashtable Enumerator");
		}
		// Iterator methods
		@Override
		public boolean hasNext()
		{
			return hasMoreElements();
		}
		@Override
		public T next()
		{
			if (modCount != expectedModCount)
			{
				throw new ConcurrentModificationException();
			}
			return nextElement();
		}
		@Override
		public void remove()
		{
			if (!iterator)
			{
				throw new UnsupportedOperationException();
			}
			if (lastReturned == null)
			{
				throw new IllegalStateException("Hashtable Enumerator");
			}
			if (modCount != expectedModCount)
			{
				throw new ConcurrentModificationException();
			}

			synchronized (HashTable.this)
			{
				Entry[] tab = HashTable.this.container;
				int index = (lastReturned.hash & 0x7FFFFFFF) % tab.length;

				for (Entry<keyType, dataType> e = tab[index], prev = null; e != null;
						prev = e, e = e.next)
				{
					if (e == lastReturned)
					{
						modCount++;
						expectedModCount++;
						if (prev == null)
						{
							tab[index] = e.next;
						}
						else
						{
							prev.next = e.next;
						}
						count--;
						lastReturned = null;
						return;
					}
				}
				throw new ConcurrentModificationException();
			}
		}
	}

}
