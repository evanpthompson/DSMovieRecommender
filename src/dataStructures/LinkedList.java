package dataStructures;

/**
 *	LinkedList class
 *
 *	implementation of a doubly linked list
 *
 *	Written by Roger West, Editted by Elham Buxton University of Illinois at Springfield
 *	Adapted from code written by Mark Weiss in Data Structures and Problem
 *	Solving Using Java, 2nd edition, 2002
 */
public class LinkedList<T> implements List<T>
{
	/************
	 *	constants
	 ***********/
	
	/** return value for unsuccessful ai.searches */
	
	
	
	
	/*************
	 *	attributes
	 ************/
	
	/** current number of items in list */
	private int theSize;
	
	/** reference to list header node */
	private ListNode<T> head;
	
	/** reference to list tail node */
	private ListNode<T> tail;
	
	
	
	/** current number of modifications to list */
	private int modCount;


	/***************
	 *	constructors
	 **************/

	/**
	 *	return a new, empty LinkedList
	 */
	public LinkedList()
	{
		// empty this LinkedList
		clear();
	}


	/**********
	 *	methods
	 *********/

	/**
	 *	return a reference to the node at the position of the specified item
	 */
	private ListNode<T> findPos(T obj)
	{
		// traverse from beginning of this LinkedList
		for(ListNode<T> p = head.next; p != tail; p = p.next)
			if(obj == null)
			{
				// null items must be compared with '==' operator
				if(p.theItem == null)
					return p;
			}
			// non-null items must be compared with equals method
			else if(obj.equals(p.theItem))
				return p;

		// item not in this LinkedList
		return null;
	}
	

	/**
	 *	place the specified item in the first position of the list (position 0)
	 */
	public boolean addFirst(T obj)
	{
		// invoke add method for adding at a specific position
		add(0, obj);
		
		// add successful
		return true;
	}


	/**
	 *	place the specified item in the last position of the list (position theSize - 1)
	 */
	public boolean addLast(T obj)
	{
		
			
		// invoke add method for adding at a specific position
		add(theSize, obj);
		
		// add successful
		return true;
	}


	/**
	 *	return the first item in the list
	 *
	 *	throws NoSuchElementException if list is empty
	 */
	public T getFirst()
	{
		// throw exception if this LinkedList is empty
		if(isEmpty())
			throw new NoSuchElementException();

		// return item at beginning of this LinkedList
		return getNode(0).theItem;
	}


	/**
	 *	return the last item in the list
	 *
	 *	throws NoSuchElementException if list is empty
	 */
	public T getLast()
	{
		// throw exception if this LinkedList is empty
		if(isEmpty())
			throw new NoSuchElementException();
		
		// return item at beginning of this LinkedList
		return getNode(theSize - 1).theItem;
	}


	/**
	 *	remove and return the first item in the list
	 *
	 *	throws NoSuchElementException if list is empty
	 */
	public T removeFirst()
	{
		// throw exception if this LinkedList is empty
		if(isEmpty())
			throw new NoSuchElementException();

		// remove item at beginning of this LinkedList
		return remove(getNode(0));
	}


	/**
	 *	remove and return the last item in the list
	 *
	 *	throws NoSuchElementException if list is empty
	 */
	public T removeLast()
	{
		// throw exception if this LinkedList is empty
		if(isEmpty())
			throw new NoSuchElementException();

		// remove item at end of this LinkedList
		return remove(getNode(theSize - 1));
	}


	/**
	 *	return a reference to the node at the specified position
	 *
	 *	throws IndexOutOfBoundsException if position is invalid
	 */
	private ListNode<T> getNode(int index)
	{
		// stores current node
		ListNode<T> p;

		// throw exception if invalid index
		if(index < 0 || index > size())
			throw new IndexOutOfBoundsException("getNode index: " + index + "; size: " + theSize);

		// determine which end of this LinkedList to start traversal
		if(index < theSize / 2)
		{
			// start traversal at beginning of this LinkedList
			p = head.next;
			
			// traverse to specified index
			for(int i = 0; i < index; i++)
				p = p.next;            
		}
		else
		{
			// start traversal at end of this LinkedList
			p = tail;
			
			// traverse to specified index
			for(int i = theSize; i > index; i--)
				p = p.previous;
		}

		// return node at specified index
		return p;
	}


	/**
	 *	remove the specified node, and return the item in that node
	 */
	private T remove(ListNode<T> node)
	{
		// reconnect next node and previous node references to bypass node to be removed
		ListNode<T> previousNeighbor = node.previous;
		ListNode<T> nextNeighbor = node.next;
		nextNeighbor.previous = previousNeighbor;
		previousNeighbor.next = nextNeighbor;
		
		// subtract 1 from size of this LinkedList
		theSize--;
		
		// indicate a modification has been made
		modCount++;

		// return item in node that was removed
		return node.theItem;
	}

	
	/**************************************
	 *	methods inherited from class Object
	 *************************************/

	/**
	 *	return a String representation of the LinkedList
	 *
	 *	items are listed in order from beginning to end in comma-delimited fashion
	 */
	public String toString()
	{
		String s = "";
		
		for (int i = 0; i < theSize; i++)
		{
			if (i == theSize - 1)
				s += getNode(i).theItem.toString();
			else
				s += getNode(i).theItem.toString() + ", ";
		}
		
		return s;
	}


	/**********************************************
	 *	methods inherited from interface Collection
	 *********************************************/
	
	/**
	 *	add the specified item to the end of the LinkedList
	 */
	public boolean add(T obj)
	{
		addLast(obj);
		
		// add successful
		return true;
	}


	/**
	 *	remove the last item in the LinkedList
	 */
	public boolean remove()
	{
		removeLast();
		
		// remove sucessful
		return true;
	}


	/**
	 *	empty the LinkedList
	 *
	 *	size will be set to zero
	 *	clearing the LinkedList counts as a modification of the list
	 */
	public void clear()
	{
		// reset header node
		head = new ListNode(null,null ,null);
		// reset tail node
        tail = new ListNode(head,null,null);
        head.next=tail;
        tail.previous=head;
        // reset size to 0
		theSize = 0;
		
		// emptying list counts as a modification
		modCount++;
	}


	/**
	 *	return the number of items in the ArrayList
	 */
	public int size()
	{
		return theSize;
	}


	/**
	 *	return true if the LinkedList is empty
	 */
	public boolean isEmpty()
	{
		return theSize == 0;
	}


	/**
	 *	return the item at the end of the LinkedList
	 */
	public T get()
	{
		return getLast();
	}
	
	
	/*********************************************************
	 *	methods inherited from interface TraversableCollection
	 ********************************************************/
	
	/**
	 *	return an Iterator beginning at position 0
	 */
	public Iterator<T> iterator()
	{
		return new LinkedListIterator(0);
	}
	
	
	/**
	 *	return true if the specified item is in the list
	 */
	public boolean contains(T obj)
	{
		// if item is in this LinkedList, findPos will return valid index
		return findPos(obj) != null;
	}
	
	
	/****************************************
	 *	methods inherited from interface List
	 ***************************************/
	
	/**
	 *	return the item at the specified position
	 *
	 *	throws IndexOutOfBoundsException if position is invalid
	 */
	public T get(int index)
	{
		return getNode(index).theItem;
	}
	

	/**
	 *	add the specified item to the LinkedList at the specified position
	 *
	 *	throws IndexOutOfBoundsException if position is invalid
	 */
	public boolean add(int index, T obj)
	{
		// find insertion point
		ListNode<T> p = getNode(index);
		
		// create new node
		ListNode<T> newNode = new ListNode<T>(obj, p.previous, p);
		
	
		// connect preceding node to new node
		newNode.previous.next = newNode;
		
		// connect succeeding node to new node
		p.previous = newNode;
		
		// add 1 to size of this LinkedList
		theSize++;
		
		// indicate a modification has been made
		modCount++;
		
		// add successful
		return true;
	}


	/**
	 *	replace the item at the specified position
	 *
	 *	throws IndexOutOfBoundsException if position is invalid
	 */
	public T set(int index, T obj)
	{
		// get node at specified index
		ListNode<T> p = getNode(index);
		
		// store item currently in node
		T oldVal = p.theItem;

		// replace old item with new item
		p.theItem = obj;
		
		// return old item
		return oldVal;
	}


	/**
	 *	remove the item at the specified position
	 *
	 *	throws IndexOutOfBoundsException if position is invalid
	 */
	public boolean remove(int index)
	{
		// remove item
		remove(getNode(index));
		
		// remove successful
		return true;
	}


	/**
	 *	return a ListIterator that begins at the specified position
	 */
	public ListIterator<T> listIterator(int index)
	{
		return new LinkedListIterator(index);
	}


	/**
	 *	return the position of the specified item, if it is in the list
	 *
	 *	returns -1 if item is not in list
	 */
	public int indexOf(T obj)
	{
		// stores index of item
		int index = 0;
		
		// search for item
		for(ListNode<T> p = head.next; p != tail; p = p.next)
		{
			if(obj == null)
			{
				// null items must be compared by '==' operator
				if(p.theItem == null)
					return index;
			}
			// non-null items must be compared by equals method
			else if(obj.equals(p.theItem))
				return index;
			
			index++;
		}

		// item not in this LinkedList
		return -1;
	}
	
	
	/**
	 *	remove the first occurrence of the specified item
	 *
	 *	returns true if removal was successful
	 *	returns false if item is not in list
	 */
	public boolean remove(T obj)
	{
		// find position of first occurrence of item
		ListNode<T> pos = findPos(obj);

		if(pos == null)
		{
			// item not in this LinkedList
			return false;
		}		
		else
		{
			// item found; remove it
			remove(pos);
			
			// remove successful
			return true;
		}     
    }


	/****************
	 *	inner classes
	 ***************/
	
	/**
	 *	inner class LinkedListIterator
	 *
	 *	traverses a LinkedList in both forward and backward directions
	 *	maintains the current position in the traversal
	 *
	 *	instances of LinkedListIterator will be invalidated when LinkedList methods
	 *	are used to modify the list
	 */
	private class LinkedListIterator implements ListIterator<T>
	{

		/*************
		 *	attributes
		 ************/
		
		/** current position */
		private int current;
		
		/** number of list modifications the iterator is aware of */
		private int expectedModCount;


		/***************
		 *	constructors
		 **************/

		/**
		 *	return a LinkedListIterator that begins at the specified position
		 *
		 *	throws IndexOutOfBoundsException if position is invalid
		 */
		public LinkedListIterator(int index)
		{
			// throw exception if invalid index
			if(index < 0 || index >= theSize)
				throw new IndexOutOfBoundsException();
			
			// set current index to specified index
			current = index;
			
			// sync mod counts of this LinkedListIterator and LinkedList
			expectedModCount = modCount;
		}
		
		
		/********************************************
		 *	methods inherited from interface Iterator
		 *******************************************/
		
		/**
		 *	return the item at the iterator's current position
		 *
		 *	throws IndexOutOfBoundsException if there is no current item
		 */
		public T getCurrent()
		{
			// throw exception if invalid index
			if(current < 0 || current >= theSize)
				throw new IndexOutOfBoundsException();
			
			// return item at current position in traversal
			return getNode(current).theItem;
		}


		/**
		 *	return true if there is a next item
		 *	there is a next item as long as the iterator's current position < theSize
		 *
		 *	throws ConcurrentModificationException if iterator has been invalidated
		 */
		public boolean hasNext()
		{
			// throw exception if LinkedList modified outside this LinkedListIterator
			if(expectedModCount != modCount)
				throw new ConcurrentModificationException();

			// next item in traversal if current index hasn't gone past end
			return current < theSize;
		}


		/**
		 *	advance forward one position
		 *
		 *	throws NoSuchElementException if there is no next item
		 */
		public void next()
		{
			// throw exception if no next item in traversal
			if(!hasNext())
				throw new NoSuchElementException();

			// set current to next position
			current++;
		}

		
		/**
		 *	add the specified T to the LinkedList
		 *	T is inserted at the iterator's current position
		 *
		 *	throws ConcurrentModificationException if iterator has been invalidated
		 */
		public boolean add(T obj)
		{
			// throw exception if Linkedlist modified outside this LinkedListIterator
			if(expectedModCount != modCount)
				throw new ConcurrentModificationException();
			
			// add item by invoking LinkedList add method
			LinkedList.this.add(current, obj);
			
			// sync mod count of this LinkedListIterator with the LinkedList
			expectedModCount++;
			
			// add successful
			return true;
		}


		/**
		 *	remove the item at the iterator's current position
		 *
		 *	throws ConcurrentModificationException if iterator has been invalidated
		 */
		public boolean remove()
		{
			// throw exception if LinkedList modified outside this LinkedListIterator
			if(expectedModCount != modCount)
				throw new ConcurrentModificationException();

			// remove item by invoking LinkedList remove method
			LinkedList.this.remove(current);

			// sync mod count of this LinkedListIterator with the LinkedList
			expectedModCount++;
			
			// remove successful
			return true;
		}
		
		
		/************************************************
		 *	methods inherited from interface ListIterator
		 ***********************************************/
		
		/**
		 *	return true if there is a previous item
		 *	there is a previous item as long as the iterator's current position >= 0
		 *
		 *	throws ConcurrentModificationException if iterator has been invalidated
		 */
		public boolean hasPrevious()
		{
			// throw exception if LinkedList modified outside this LinkedListIterator
			if(expectedModCount != modCount)
				throw new ConcurrentModificationException();

			// if previous item exists, current >= 0 will be true
			return current >= 0;
		}


		/**
		 *	move backward one position
		 *
		 *	throws NoSuchElementException if there is no previous item
		 */
		public void previous()
		{
			// throw exception if no previous item in traversal
			if(!hasPrevious())
				throw new NoSuchElementException();
			
			// set current to previous position
			current--;
		}

	}


	/**
	 *	nested class ListNode
	 *
	 *	encapsulates the fundamental building block of a LinkedList
	 *	contains a data item, and references to both the next and previous nodes
	 *	in the list
	 */
	private static class ListNode<E>
	{

		/*************
		 *	attributes
		 ************/
		
		/** the data item */
		E theItem;
		
		/** reference to the next node in the list */
		ListNode<E> next;
		
		/** reference to the previous node in the list */
		ListNode<E> previous;


		/**************
		 *	constructor
		 *************/
		
		/**
		 *	create a new ListNode containing the specified item, and references
		 *	to the new node's next and previous nodes
		 */
		public ListNode(E item, ListNode<E> previousNode, ListNode<E> nextNode)
		{
			theItem = item;
			previous = previousNode;
			next = nextNode;
		}

	}

}



