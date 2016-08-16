package dataStructures;

/**
 *	LinkedQueue class
 *
 *	implementation of a linked queue
 *
 *	Written by Roger West, edited by Elham Buxton, University of Illinois at Springfield
 *	Adapted from code written by Mark Weiss in Data Structures and Problem
 *	Solving Using Java, 2nd edition, 2002
 */
public class LinkedQueue<T> implements Queue<T>
{

	/*************
	 *	attributes
	 ************/
	
	/** node containing item at front of queue */
	private QueueNode<T> front;
	
	/** node containing item at rear of queue */
	private QueueNode<T> back;
	
	/** current number of items in queue */
	private int theSize;


	/***************
	 *	constructors
	 **************/
	
	/**
	 *	return a new, empty LinkedQueue
	 */
	public LinkedQueue()
	{
		// empty this LinkedQueue
		clear();
	}
	
	
	/**************************************
	 *	methods inherited from class Object
	 *************************************/

	/**
	 *	return a String representation of the LinkedQueue
	 *
	 *	items are listed from left to right, in comma-delimited fashion,
	 *	with the leftmost item being the item at the front of the queue, and the
	 *	rightmost item being the item at the rear of the queue
	 */
	public String toString()
	{
		String s = "";
		QueueNode<T> node = front;
		
		while (node != null)
		{
			if (node == back)
				s += node.theItem.toString();
			else
				s += node.theItem.toString() + ", ";
			
			node = node.next;
		}
		
		return s;
	}


	/*****************************************
	 *	methods inherited from interface Queue
	 ****************************************/
	
	/**
	 *	return the item at the front of the queue; item is not removed
	 *
	 *	throws UnderflowException if queue is empty
	 */
	public T getFront()
	{
		// throw exception if this LinkedQueue is empty
		if(isEmpty())
			throw new UnderflowException("LinkedQueue getFront");

		// return the item at the front of this LinkedQueue
		return front.theItem;
	}


	/**
	 *	remove and return the item at the front of the queue
	 *
	 *	throws UnderflowException if queue is empty
	 */
	public T dequeue()
	{
		// throw exception if empty queue
		if(isEmpty())
			throw new UnderflowException("LinkedQueue dequeue");

		// store object being removed 
		T returnValue = front.theItem;
		
		// make next item new front item
		front = front.next;
		
		// subtract 1 from size of this LinkedQueue
		theSize--;

		// return item that was removed
		return returnValue;
	}


	/**
	 *	add the specified item to the rear of the queue
	 */
	public boolean enqueue(T obj)
	{
		if(isEmpty())
		{
			// if this LinkedQueue was empty, need to make both front and back
			// references link to new item
			back = front = new QueueNode<T>(obj);
		}
		else
		{
			// add new item to rear of this LinkedQueue
			back.next = new QueueNode<T>(obj);
			back = back.next;
		}
		
		// add 1 to size of this LinkedQueue
		theSize++;
		
		// enqueue successful
		return true;
	}
	
	
	/**********************************************
	 *	methods inherited from interface Collection
	 *********************************************/
	
	/**
	 *	add the specified item to the rear of the queue
	 */
	public boolean add(T obj)
	{
		// invoke enqueue alias method
		return enqueue(obj);
	}


	/**
	 *	remove the item at the front of the queue
	 *	return true if operation is successful
	 *
	 *	throws UnderflowException if queue is empty
	 */
	public boolean remove()
	{
		// invoke dequeue alias method
		dequeue();
		
		// remove successful
		return true;
	}


	/**
	 *	empty the LinkedQueue
	 *
	 *	size will be set to zero
	 */
	public void clear()
	{
		// reset links to front and rear
		front = back = null;
		
		// reset size to 0
		theSize = 0;
	}


	/**
	 *	return the number of items in the ArrayQueue
	 */
	public int size()
	{
		return theSize;
	}


	/**
	 *	return true if the ArrayQueue is empty
	 */
	public boolean isEmpty()
	{
		return theSize == 0;
	}


	/**
	 *	return the item at the front of the queue; item is not removed
	 *
	 *	throws UnderflowException if queue is empty
	 */
	public T get()
	{
		return getFront();
	}
	

	/****************
	 *	inner classes
	 ***************/
	
	/**
	 *	nested class QueueNode
	 *	encapsulates the fundamental building block of a LinkedQueue
	 *	contains a data item, and a reference to the next node in the queue
	 */
	private class QueueNode<E>
	{

		/*************
		 *	attributes
		 ************/
		
		/** the data item */
		E theItem;
		
		/** reference to the next node in the list */
		QueueNode<E> next;


		/***************
		 *	constructors
		 **************/
		
		/**
		 *	create a new QueueNode containing the specified item
		 *
		 *	reference to the new node's next node is null
		 */
		public QueueNode(E obj)
		{
			this(obj, null);
		}
		

		/**
		 *	create a new QueueNode containing the specified item, and reference
		 *	to the new node's next node
		 */
		public QueueNode(E obj, QueueNode<E> node)
		{
			theItem = obj;
			next = node;
		}

	}

}