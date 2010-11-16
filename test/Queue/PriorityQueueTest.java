/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Queue;

import Stack.MorphedStack;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author uko
 */
public class PriorityQueueTest {

    public PriorityQueueTest() {
    }

	/**
	 * Test of constructor with comparator, of class PriorityQueue.
	 */ @Test
	public void testConstructorComparator()
	{
		Comparator<Integer> customComparator = new Comparator<Integer>()
		{
			@Override
			public int compare(Integer what, Integer toWhat)
			{
				if (what+toWhat==0)
					return 0;
				if (what+toWhat>0)
					return 1;
				return -1;
			}
		};
		PriorityQueue<Integer> instance = new PriorityQueue<Integer>(customComparator);
		assertEquals(customComparator, instance.comparator());
	}
	/**
	 * Test of size method, of class PriorityQueue.
	 */ @Test
	public void testSize()
	{
		PriorityQueue<Integer> instance = new PriorityQueue<Integer>();
		for (int i=0; i<50; i++)
		{
			instance.offer(i);
			assertEquals(i+1, instance.size());
		}
	}
	/**
	 * Test of iterator, of class PriorityQueue.
	 */ @Test
	public void testIterator()
	{
		PriorityQueue<Integer> instance = new PriorityQueue<Integer>();
		for (int i=0; i<50; i++)
		{
			instance.offer(i);
		}
		int i=50;
		for(Integer iterator : instance)
		{
			i--;
			assertEquals(new Integer(i), iterator);
		}
		i=50;
		for(Iterator<Integer> iter=instance.iterator();iter.hasNext();)
		{
			
			assertEquals(i, instance.size());
			iter.next();
			iter.remove();
			i--;
		}
	}
	 /**
	 * Test of sorting, of class PriorityQueue.
	 */ @Test
			 public void testSort()
	{
		 Random generator = new Random();
		PriorityQueue<Double> instance = new PriorityQueue<Double>();
		for (int i=0; i<5000; i++)
		{
			instance.offer(generator.nextDouble());
		}
		Double prev=null;
		for(Double iterator : instance)
		{
			if (prev==null)
			{
				prev=iterator;
			}
			else
			{
				assertTrue(prev.compareTo(iterator)>=0);
				prev=iterator;
			}
		}
	}
	/**
	 * Test of clear method, of class PriorityQueue.
	 */ @Test
	public void testClear()
	{
		PriorityQueue<Integer> instance = new PriorityQueue<Integer>();
		for (int i=0; i<50; i++)
		{
			instance.offer(i);
		}
		assertEquals(50, instance.size());
		instance.clear();
		assertEquals(0, instance.size());
	}
	/**
	 * Test of peek method, of class PriorityQueue.
	 */ @Test
	public void testPeek()
	{
		PriorityQueue<Integer> instance = new PriorityQueue<Integer>();
		for (int i=0; i<50; i++)
		{
			instance.offer(i);
			assertEquals(new Integer(i), instance.peek());
		}
	}
	/**
	 * Test of poll method, of class PriorityQueue.
	 */ @Test
	public void testPoll()
	{
		Random generator = new Random();
		PriorityQueue<Double> instance = new PriorityQueue<Double>();
		for (int i=0; i<5000; i++)
		{
			instance.offer(generator.nextDouble());
		}
		Double prev=instance.poll();
		for (int i=4999; i>0; i--)
		{
			assertTrue(prev.compareTo(instance.peek())>=0);
			prev=instance.poll();
		}
	}
	 /**
	 * Testing peek on empty queue.
	 */ @Test(expected=NoSuchElementException.class)
			 public void testPeekCrash()
	{
	 PriorityQueue<Integer> instance = new PriorityQueue<Integer>();
	 instance.peek();
	 fail("Exception should have been thrown");
	 }
	  /**
	 * Testing poll on empty queue.
	 */ @Test(expected=NoSuchElementException.class)
			 public void testPollCrash()
	{
	 PriorityQueue<Integer> instance = new PriorityQueue<Integer>();
	 instance.poll();
	 fail("Exception should have been thrown");
	 }
	  /**
	 * Testing offer of a null element.
	 */ @Test(expected=NullPointerException.class)
			 public void testElementCrash()
	{
	 PriorityQueue<Integer> instance = new PriorityQueue<Integer>();
	 instance.offer(null);
	 fail("Exception should have been thrown");
	 }
	  /**
	 * Testing next via iterator on the last element.
	 */ @Test(expected=NoSuchElementException.class)
			 public void testIteratorNextCrash()
	{
	 PriorityQueue<Integer> instance = new PriorityQueue<Integer>();
	 for (int i=0; i<10; i++)
		{
			instance.offer(i);
		}
		Iterator<Integer> iter=instance.iterator();
		for(int i=0;i<50;i++)
		{
			iter.next();
		}
		fail("Exception should have been thrown");
	 }
	  /**
	 * Testing double remove via iterator.
	 */ @Test(expected=IllegalStateException.class)
			 public void testIteratorRemoveCrash()
	{
	 PriorityQueue<Integer> instance = new PriorityQueue<Integer>();
		for (int i=0; i<10; i++)
		{
			instance.offer(i);
		}
		Iterator<Integer> iter=instance.iterator();
		for(int i=0; i<3; i++)
		{

			iter.next();

		}
		iter.remove();
		iter.remove();
		fail("Exception should have been thrown");
	 }
	  /**
	 * Testing remove via iterator without executing first next().
	 */ @Test(expected=IllegalStateException.class)
			 public void testIteratorRemoveCrash2()
	{
	 PriorityQueue<Integer> instance = new PriorityQueue<Integer>();
	 for (int i=0; i<10; i++)
		{
			instance.offer(i);
		}
		Iterator<Integer> iter=instance.iterator();
		
		iter.remove();
		fail("Exception should have been thrown");
	 }
	/**
	 * Testing cast exception when adding un-comparable.
	 */ @Test(expected=ClassCastException.class)
	public void testOfferUnComparable()
	{
		MorphedStack element = new MorphedStack();
		element.add("sss");
		PriorityQueue<MorphedStack> instance = new PriorityQueue<MorphedStack>();
		boolean expResult = false;
		boolean result = instance.offer(element);
		assertEquals(expResult, result);
		fail("Exception should have been thrown");
	}

}