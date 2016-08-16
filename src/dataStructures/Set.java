package dataStructures;

/**
 *	interface Set
 *
 *	Encapsulates the functionality for a set, which is defined as a type of
 *	collection that does not allow duplicate items.
 *
 *	For now, this interface simply serves as a basis for deriving any class
 *	that is-a Set.
 *
 *	Written by Roger West,Edited by Elham S.Buxton,  University of Illinois at Springfield
 */
public interface Set<T> extends TraversableCollection<T>
{
	
	/**
	 *	remove the specified item from the Set
	 *
	 *	returns true if removal was successful, false otherwise
	 */
	public boolean remove(T obj);
}