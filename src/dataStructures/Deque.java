package dataStructures;

/**
 *	Deque (double-ended queue) interface
 *
 *	Encapsulates the basic functionality for a deque
 *	items may only be added/removed to the front/rear of the deque
 *
 *	Written by Roger West, edited by Elham Buxton, University of Illinois at Springfield
 */
public interface Deque<T> extends Collection<T>
{

	/**
	 *	add the specified item to the front of the deque
	 */
	public void addFront(T obj);

	/**
	 *	add the specified item to the rear of the deque
	 */
	public void addBack(T obj);

	/**
	 *	remove and return the item at the front of the deque
	 */
	public T removeFront();


	/**
	 *	remove and return the item at the rear of the deque
	 */
	public T removeBack();


	/**
	 *	return the item at the front of the deque; the item is not removed
	 */
	public T getFront();


	/**
	 *	return the item at the rear of the deque; the item is not removed
	 */
	public T getBack();

}





