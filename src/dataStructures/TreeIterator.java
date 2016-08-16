package dataStructures;

/**
 *	interface TreeIterator
 *
 *	encapsulates the most basic functionality for traversing a tree
 *
 *	Written by Roger West, edited by Elham Buxton, University of Illinois at Springfield
 */
public interface TreeIterator<T> extends Iterator<T>
{
	/**
	 *	add the specified item at the current position of the traversal
	 */
	public boolean add(T obj);


	/**
	 *	remove the current item in the traversal
	 */
	public boolean remove();
}





