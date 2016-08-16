package dataStructures;

/**
 *	Tree interface
 *	Encapsulates the basic functionality for a generic tree data structure
 *Written by Roger West, Edited by Elham S. Buxton, University of Illinois at Springfield
 */
public interface Tree<T> extends TraversableCollection<T>
{

	/**
	 *	return the height of the tree
	 *	the height of the tree is defined as the length of the path from the
	 *	root to the deepest leaf
	 */
	public int height();


	/**
	 *	return the first occurrence of the specified item
	 *
	 *	returns null if specified item doesn't exist
	 */
	public T get(T obj);

}