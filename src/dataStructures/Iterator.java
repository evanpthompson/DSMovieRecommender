package dataStructures;

/**
 *	interface Iterator
 *
 *	encapsulates the most basic functionality for traversing a collection of
 *	items
 *
 *	Written by Roger West, Edited by Elham Buxton, University of Illinois at Springfield
 */
public interface Iterator<T>
{
	
	/**
	 *	return the current item in the traversal
	 */
	public T getCurrent();


	/**
	 *	return whether there is a next item to traverse
	 *	return true if yes, false otherwise
	 */
	public boolean hasNext();


	/**
	 *	advance to the next item
	 */
	public void next();

}
