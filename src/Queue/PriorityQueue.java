/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Queue;

import java.util.AbstractQueue;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A priority queue based on a linked nodes structure.
 * @param <dataType> a type of elements stored in a queue
 * @author uko
 */
public class PriorityQueue<dataType> extends AbstractQueue<dataType> implements Iterable<dataType>
{
	private Node<dataType> head;
	private int size = 0;
	private Comparator<? super dataType> comparator;
	/**
	 * Default constructor. Redefines comparator to compare using "compareTo()" method
	 *
	 * @throws ClassCastException exception is thrown if type cast fails i.e elements are not comparable.
	 */
	public PriorityQueue() throws ClassCastException
	{
		super();
		comparator = new Comparator<dataType>()
		{
			@Override
			public int compare(dataType what, dataType toWhat) throws ClassCastException
			{
				Comparable<? super dataType> key = (Comparable<? super dataType>) what;
				return key.compareTo(toWhat);
			}
		};
	}

	/**
	 * Constructs a queue with a passed comparator.
	 * @param comparator used to compare elements of the queue
	 */
	public PriorityQueue(Comparator<? super dataType> comparator)
	{
		super();
		this.comparator = comparator;
	}

	/**
	 * Returns comparator that is used to compare elements of the queue
	 * @return comparator used to compare elements of the queue
	 */
	public Comparator<? super dataType> comparator()
	{
		return comparator;
	}
	/**
	 * Returns the number of elements in this priority queue.
	 * @return the number of elements in this priority queue
	 */
	@Override
	public int size()
	{
		return size;
	}
	/**
	 * Returns an iterator over the elements in this priority queue in proper sequence.
	 * @return an iterator over the elements in this priority queue in proper sequence
	 */
	@Override
	public Iterator iterator()
	{
		return new PriorityQueueIterator();
	}
	/**
	 * Removes all of the elements from this priority queue.
	 */
	@Override
	public void clear()
	{
		head = null;
		size = 0;
	}
	/**
	 * Retrieves the head of this priority queue, or throws NoSuchElementException if this queue is empty.
	 * @return the head of this queue
	 * @throws NoSuchElementException if this queue is empty
	 */
	@Override
	public dataType peek() throws NoSuchElementException
	{
		if (head == null)
		{
			throw new NoSuchElementException("peek(): Empty queue");
		}
		return head.data;
	}
	/**
	 * Retrieves and removes the head of this priority queue, or throws NoSuchElementException if this queue is empty.
	 * @return the head of this queue
	 * @throws NoSuchElementException if this queue is empty
	 */
	@Override
	public dataType poll() throws NoSuchElementException
	{
		if (head == null)
		{
			throw new NoSuchElementException("poll(): Empty queue");
		}
		dataType tmp = head.data;
		head = head.next;
		size--;
		return tmp;
	}
	/**
	 * Inserts the specified element into this priority queue.
	 * @param element - element to add to this priority queue
	 * @return true
	 * @throws NullPointerException if passed element is null
	 * @throws ClassCastException if passed element isn't comparable;
	 */
	@Override
	public boolean offer(dataType element) throws NullPointerException, ClassCastException
	{
		if (element == null)
		{
			throw new NullPointerException();
		}
		Comparable<? super dataType> brut = (Comparable<? super dataType>) element;
		if (head == null)
		{
			head = new Node<dataType>(element);
			size++;
			return true;
		}
		if(comparator.compare(element, head.data)>0)
		{
			head=new Node<dataType>(element, head);
			size++;
			return true;
		}
		Node<dataType> runner = head;
		while(runner.next!=null&&comparator.compare(element, runner.next.data)<=0)
			runner=runner.next;
		runner.next=new Node<dataType>(element, runner.next);
		size++;
		return true;
	}

	private class PriorityQueueIterator implements Iterator<dataType>
	{
		private Node<dataType> runner;
		private boolean canNext = false;
		public PriorityQueueIterator()
		{
			runner = head;
		}
		@Override
		public boolean hasNext()
		{
			return runner != null/*&&runner.previous!=null*/;
		}
		@Override
		public dataType next()
		{
			if (runner == null)
			{
				throw new NoSuchElementException();
			}
			dataType temp = runner.data;
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
			if (runner==head.next)
			{
				head=head.next;
				canNext = false;
				size--;
				return;
			}
			Node<dataType> helper = head;
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
