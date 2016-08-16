
package dataStructures;
/**
 *	List interface
 *	Encapsulates the basic functionality for a list: a data structure in which
 *	each item is associated with a particular position, or index, in the list
 *
 *	Written by Roger West, Edited by Elham Buxton, University of Illinois at Springfield
 *	Adapted from code written by Mark Weiss in Data Structures and Problem
 *	Solving Using Java, 2nd edition, 2002
 */
public interface List<T> extends TraversableCollection<T>
{

	/**
	 *	return the item at the specified index
	 */
	public T get(int index);


	/**
	 *	insert the specified item at the specified index
	 */
	public boolean add(int index, T obj);


	/**
	 *	replace the item at the specified index with the specified item
	 */
	public T set(int index, T obj);


	/**
	 *	remove the item at the specified index
	 */
	public boolean remove(int index);


	/**
	 *	return an iterator specialized for traversing a List
	 *	traversal begins at the specified index
	 */
	public ListIterator listIterator(int index);


	/**
	 *	return the index of the specified item
	 */
	public int indexOf(T obj);
	
	
	/**
	 *	remove the specified item from the List
	 */
	public boolean remove(T obj);

}