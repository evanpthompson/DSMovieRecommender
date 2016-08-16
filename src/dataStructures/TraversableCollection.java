package dataStructures;

/**
 *	TraversableCollection interface
 *	Encapsulates the functionality for a Collection that can be traversed
 *	using an iterator
 *
 *	Written by Roger West, Edited by Elham Buxton, University of Illinois at Springfield
 */
public interface TraversableCollection<T> extends Collection<T>
{

	/**
	 *	return a generic Iterator for the Collection
	 */
	public Iterator iterator();
	
	
	/**
	 *	return whether the Collection contains the specified item
	 */
	public boolean contains(T obj);

}