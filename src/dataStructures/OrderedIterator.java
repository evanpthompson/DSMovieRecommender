package dataStructures;

/**
 *	interface OrderedIterator
 *
 *	encapsulates the most basic functionality for traversing an ordered
 *	collection of items
 *
 *	Written by Roger West, University of Illinois at Springfield
 */
public interface OrderedIterator<T extends Comparable<? super T>>
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
