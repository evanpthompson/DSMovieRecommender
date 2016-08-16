package dataStructures;

/**
 *	GenericBinarySearchTree class
 *	
 *	implementation of a rooted tree in which each node of the tree can have a
 *	maximum of 2 child nodes, and the items in the tree are stored according to
 *	the following ordering rule:
 *		1)	items < a given item, X, will be located in X's left subtree
 *		2)	items > a given item, X, will be located in X's right subtree
 *		3)	duplicate items are not allowed
 *
 *	Written by Roger West, Edited by Elham Buxton, University of Illinois at Springfield
 */
public class GenericBinarySearchTree<T extends Comparable<? super T>> implements BinarySearchTree<T>
{

	/*************
	 *	attributes
	 ************/
	
	/** root node */
	protected BinarySearchNode<T> root;
	
	/** number of items in tree */
	protected int theSize;
	
	/** current number of modifications to tree */
	protected int modCount;


	/***************
	 *	constructors
	 **************/

	/**
	 *	create a new, empty GenericBinarySearchTree
	 */
	public GenericBinarySearchTree()
	{
		// empty this GenericBinarySearchTree
		clear();
	}
	
	/**
	 *	create a new GenericBinarySearchTree with the specified item in the root node
	 */
	public GenericBinarySearchTree(T obj)
	{
		// GenericBinarySearchTree
		clear();
		
		// create root node containing obj
		root = new BinarySearchNode<T>(obj, null, null);
	}


	/**********
	 *	methods
	 *********/

	/**
	 *	return the item in the root node
	 */
	public T getRootItem()
	{
		// throw exception if this GenericBinarySearchTree is empty
		if (isEmpty())
			throw new NoSuchElementException("Tree is empty");
		
		// return item in root node
		return root.theItem;
	}
	
	
	/**
	 *	return the height of the specified node
	 *	returns -1 if node is null
	 */
	protected int height(BinarySearchNode<T> node)
	{
		if (node == null)
		{
			// null nodes have no height
			return -1;
		}
		else
		{
			// height of node is height of deepest subtree + 1
			return 1 + Math.max(height(node.left), height(node.right));
		}
	}
	
	
	/**
	 *	return the minimum item in the tree
	 *
	 *	throws NoSuchElementException if tree is empty
	 */
	public T getMinItem()
	{
		// throw exception is this GenericBinarySearchTree is empty
		if (isEmpty())
			throw new NoSuchElementException("Tree is empty");
		
		// return min item, which is contained in min node
		return getMinNode(root).theItem;
	}
	
	
	@SuppressWarnings("unchecked")
	public T getSmallestLargerThanK(T k) {
		
		//  geSmallestLargerThanK(11) must return 12; 
		// because 12 is the smallest item in the tree that is larger than 11
		
		// start searching at root node
		BinarySearchNode<T> node = root;
		
		
				
		// traverse until item found, or it's determined item doesn't exist
		while (node != null)
		{
			if (k.compareTo(node.theItem) < 0)
			{
				// obj < current item; branch left
				node = node.left;
			}
			else if (k.compareTo(node.theItem) > 0)
			{
				// obj > current item; branch right
				node = node.right;
			}
			else
			{
				// item found
				node = node.right;
				
				while (node != null) {
					
					
					if (node.left == null) {
						return node.theItem;
					}
					else {
						node = node.left;
					}
				}
				
				// item is min
				return null;
				
			}
		}
		
		T seek = k; 
		
		
		while (seek.compareTo(this.getMaxItem()) < 0) {
			if (this.get(seek) == null) {
				seek = (T) addOneFrom(seek);
			}
			else {
				return get(seek);
			}
			
			
			
		}
				
		// returns null if k is maxItem, thus has nothing more than it
		return null;

	}
	
	public Integer subOneFrom(T k) {
		int temp = (int) k;
		temp = temp -1;
		
		return temp;
	}
	
	public Integer addOneFrom(T k) {
		int temp = (int) k;
		temp = temp +1;
		
		return temp;
	}
	
	

	@SuppressWarnings("unchecked")
	public T getLargestSmallerThanK(T k) {
		
		// getLargestSmallerThanK(11) must return 4; 
		// because 4 is the largest element that is smaller than 11.
		// start searching at root node
		BinarySearchNode<T> node = root;
				
				
						
		// traverse until item found, or it's determined item doesn't exist
		while (node != null)
		{
			if (k.compareTo(node.theItem) < 0)
			{
				// obj < current item; branch left
				node = node.left;
			}
			else if (k.compareTo(node.theItem) > 0)
			{
				// obj > current item; branch right
				node = node.right;
			}
			else
			{
				// item found
				
				node = node.left;
				
				while (node != null) {
					
					if (node.right == null) {
						return node.theItem;
					}
					else {
						node = node.right;
					}
				}
				
				// item is min
				return null;
				
			}
		}
						
		// item does not exist in this GenericBinarySearchTree
		
		T seek = k; 
		
		
		while (seek.compareTo(this.getMinItem()) > 0) {
			if (this.get(seek) == null) {
				seek = (T) subOneFrom(seek);
			}
			else {
				return get(seek);
			}
			
			
			
		}
		
		// returns null if k is minItem, thus has nothing less than it
		return null;

	}
	/**
	 *	beginning at the specified node, return the node containing the
	 *	minimum item
	 *
	 *	returns null if there is no minimum item
	 */
	protected BinarySearchNode<T> getMinNode(BinarySearchNode<T> node)
    {
    	// find min node
		if(node != null)
		{
			// branch left as far as possible
			while(node.left != null)
				node = node.left;
		}

		// return min node
		return node;
    }
    
    
    /**
	 *	return the maximum item in the tree
	 *
	 *	throws NoSuchElementException if tree is empty
	 */
	public T getMaxItem()
	{
		// throw exception if this GenericBinarySearchTree is empty
		if (isEmpty())
			throw new NoSuchElementException("Tree is empty");
		
		// return max item, which is in max node
		return getMaxNode(root).theItem;
	}
	
	
	/**
	 *	beginning at the specified node, return the node containing the
	 *	maximum item
	 *
	 *	returns null if there is no maximum item
	 */
	protected BinarySearchNode<T> getMaxNode(BinarySearchNode<T> node)
    {
    	// find max node
		if(node != null)
		{
			// branch right as far as possible
			while(node.right != null)
				node = node.right;
		}

		// return max node
		return node;
    }
    
    
    /**
     *	internal, recursive method for adding an item to the tree
     *
     *	throws DuplicateItemException if item already exists in tree
     */
    protected BinarySearchNode<T> add(T obj, BinarySearchNode<T> node)
    {
    	// find insertion point
		if(node == null)
		{
			// insertion point found; create new node
			node = new BinarySearchNode<T>(obj);
		}
		else if(obj.compareTo(node.theItem) < 0)
		{
			// obj < current item; recursively branch left
			node.left = add(obj, node.left);
		}
		else if(obj.compareTo(node.theItem) > 0)
		{
			// obj > current item; recursively branch right
			node.right = add(obj, node.right);
		}
		else
		{
			// item already exists in this GenericBinarySearchTree
			throw new DuplicateItemException(obj.toString());
		}

		// return node (at end of recursion, will always return root node)
		return node;
    }
	
	
	/**
	 *	internal, recursive method to remove an item
	 *	search for item begins at the specified node
	 */
	protected BinarySearchNode<T> remove(T obj, BinarySearchNode<T> node)
    {
    	// search for item to remove
		if(node == null)
		{
			// throw exception if item does not exist in this GenericBinarySearchTree
			throw new NoSuchElementException("Item does not exist");
		}
		if(obj.compareTo(node.theItem) < 0)
		{
			// obj < current item; recursively branch left
			node.left = remove(obj, node.left);
		}
		else if(obj.compareTo(node.theItem) > 0)
		{
			//obj > current item; recursively branch right
			node.right = remove(obj, node.right);
		}
		else if(node.left != null && node.right != null)
		{
			// item found, but current node has 2 children
			
			// replace current item with min item in right subtree
			node.theItem = getMinNode(node.right).theItem;
			
			// remove min item in right subtree
			node.right = removeMinNode(node.right);
		}
		else
		{
			// item found, current node has 0 or 1 child nodes
			node = (node.left != null) ? node.left : node.right;
		}

		// return node (at end of recursion, will always return root node)
		return node;
    }
    
    
    /**
     *	remove the minimum item from the tree
     *
     *	returns true if removal was successful, false otherwise
	 *	throws NoSuchElementException if tree is empty
     */
    public boolean removeMinItem()
    {
    	// throw exception if this GenericBinarySearchTree is empty
    	if (isEmpty())
			throw new NoSuchElementException("Tree is empty");
		
		// remove min node
    	root = removeMinNode(root);
    	
    	// subtract 1 from size of this GenericBinarySearchTree
    	theSize--;
    	
    	// indicate this GenericBinarySearchTree has been modified
		modCount++;
    	
    	// remove sucessful
    	return true;
    }
    
    
    /**
     *	internal, recursive method for removing the minimum item
     *
     *	throws NoSuchElementException if the item does not exist
     */
    protected BinarySearchNode<T> removeMinNode(BinarySearchNode<T> node)
    {
		if(node == null)
		{
			// node does not exist
			throw new NoSuchElementException("Item does not exist");
		}
		else if(node.left != null)
		{
			// recursively branch left
			node.left = removeMinNode(node.left);
			
			// return current node
			return node;
		}
		else
		{
			// remove min node by returning reference to min node's right child
			return node.right;
		}
    }
    
    
    /**
     *	remove the maximum item from the tree
     *
     *	returns true if removal was successful, false otherwise
	 *	throws NoSuchElementException if tree is empty
     */
    public boolean removeMaxItem()
    {
    	// throw exception if this GenericBinarySearchTree is empty
    	if (isEmpty())
			throw new NoSuchElementException("Tree is empty");
		
		// remove max node
    	root = removeMaxNode(root);
    	
    	// subtract 1 from size of this GenericBinarySearchTree
    	theSize--;
    	
    	// indicate this GenericBinarySearchTree has been modified
		modCount++;
    	
    	// remove sucessful
    	return true;
    }
    
    
    /**
     *	internal, recursive method for removing the maximum item
     *
     *	throws NoSuchElementException if the item does not exist
     */
    protected BinarySearchNode<T> removeMaxNode(BinarySearchNode<T> node)
    {
		if(node == null)
		{
			// node does not exist
			throw new NoSuchElementException("Item does not exist");
		}
		else if(node.right != null)
		{
			// recursively branch left
			node.right = removeMaxNode(node.right);
			
			// return current node
			return node;
		}
		else
		{
			// remove min node by returning reference to min node's right child
			return node.left;
		}
    }
	
	
	/**************************************
	 *	methods inherited from class Object
	 *************************************/
	
	/**
	 *	return a String representation of the GenericBinarySearchTree
	 *	items will be in ascending order
	 */
	public String toString()
	{
		String s = "";
		GenericBinarySearchTreeIterator itr = inOrderIterator();
		
		while (itr.hasNext())
		{
			T o = itr.getCurrent();
			
			if (s == "")
				s += o.toString();
			else
				s += ", " + o.toString();
			
			itr.next();
		}
		
		return s;
	}


	/***********************************************
	 *	methods inherited from interface OrderedTree
	 **********************************************/
	
	/**
	 *	return the height of the tree
	 */
	public int height()
	{
		// height of tree == height of root node
		return height(root);
	}


	/**
	 *	return the specified item
	 *
	 *	returns null if specified item doesn't exist
	 */
	public T get(T obj)
	{
		// start searching at root node
		BinarySearchNode<T> node = root;
		
		// traverse until item found, or it's determined item doesn't exist
		while (node != null)
		{
			if (obj.compareTo(node.theItem) < 0)
			{
				// obj < current item; branch left
				node = node.left;
			}
			else if (obj.compareTo(node.theItem) > 0)
			{
				// obj > current item; branch right
				node = node.right;
			}
			else
			{
				// item found
				return node.theItem;
			}
		}
		
		// item does not exist in this GenericBinarySearchTree
		return null;
	}
	
	
	/**
	 *	remove the specified object from the tree
	 *
	 *	returns true if removal was successful, false otherwise
	 *	throws NoSuchElementException if tree is empty
	 */
	public boolean remove( T obj)
	{
		// throw exception if this GenericBinarySearchTree is empty
		if (isEmpty())
			throw new NoSuchElementException("Tree is empty");
		
		// recursively search for item starting at root
		root = remove(obj, root);
		
		// subtract 1 from size of this GenericBinarySearchTree
		theSize--;
		
		// indicate this GenericBinarySearchTree has been modified
		modCount++;
		
		// remove successful
		return true;
	}
	
	
	/**
	 *	return an iterator for traversing the tree in a preorder fashion
	 */
	public GenericBinarySearchTreeIterator preOrderIterator()
	{
		return new PreOrderIterator(this);
	}
	
	
	/**
	 *	return an iterator for traversing the tree in a postorder fashion
	 */
	public GenericBinarySearchTreeIterator postOrderIterator()
	{
		return new PostOrderIterator(this);
	}
	
	
	/**
	 *	return an iterator for traversing the tree in an inorder fashion
	 *	this iterator will return the items in ascending order
	 */
	public GenericBinarySearchTreeIterator inOrderIterator()
	{
		return new InOrderIterator(this);
	}
	
	
	/**
	 *	return an iterator for traversing the tree in a level-order fashion
	 */
	public GenericBinarySearchTreeIterator levelOrderIterator()
	{
		return new LevelOrderIterator(this);
	}
	
	
	/****************************************************************
	 *	methods inherited from interface TraversableOrderedCollection
	 ***************************************************************/
	
	/**
	 *	return default iterator for this GenericBinarySearchTree
	 *	default iterator uses an inorder traversal
	 */
	public OrderedIterator<T> iterator()
	{
		return new InOrderIterator(this);
	}
	
	
	/**
	 *	return whether the specified object exists in the tree
	 */
	public boolean contains(T obj)
	{
		return (get(obj) != null);
	}


	/**********************************************
	 *	methods inherited from interface Collection
	 *********************************************/
	
	/**
	 *	add the specified item to the tree
	 *	the item will be added such that the ordering of the tree is preserved
	 */
	public boolean add(T obj)
	{
		// recursively search for insertion point starting at root
		root = add(obj, root);
		
		// add 1 to size of this GenericBinarySearchTree
		theSize++;
		
		// indicate this GenericBinarySearchTree has been modified
		modCount++;
		
		// add successful
		return true;
	}


	/**
	 *	remove the first item in the GenericBinarySearchTree
	 *	by default, the first item is considered to be the root item
	 */
	public boolean remove()
	{	
		// throw exception if this GenericBinarySearchTree is empty
		if (isEmpty())
			throw new NoSuchElementException("Tree is empty");
		
		// remove root item
		return remove(root.theItem);
	}


	/**
	 *	make the tree empty
	 */
	public void clear()
	{
		// set root to null to indicate no items
		root = null;
		
		// set size to 0
		theSize = 0;
		
		// emptying tree counts as a modification
		modCount++;
	}


	/**
	 *	return the current size of the tree
	 */
	public int size()
	{
		return theSize;
	}


	/**
	 *	return true if the tree is empty
	 */
	public boolean isEmpty()
	{
		return theSize == 0;
	}


	/**
	 *	by default, return the item in the root node
	 *
	 *	throws NoSuchElementException if tree is empty
	 */
	public T get()
	{
		// throw exception if this GenericBinarySearchTree is empty
		if (isEmpty())
			throw new NoSuchElementException("Empty Tree");
		
		// return item in root node
		return getRootItem();
	}
	
	
	/****************
	 *	inner classes
	 ***************/

	/**
	 *	inner abstract class GenericBinarySearchTreeIterator
	 *	encapsulates the basic functionality of an iterator for a GenericBinarySearchTree
	 */
	protected abstract class GenericBinarySearchTreeIterator implements OrderedIterator<T>
	{
		
		/*************
		 *	attributes
		 ************/
		
		/** the GenericBinarySearchTree that is being iterated */
		protected GenericBinarySearchTree<T> theTree;
		
		/** number of tree modifications the iterator is aware of */
		protected int expectedModCount;
		
		
		/***************
		 *	constructors
		 **************/
		
		/**
		 *	create a new GenericBinarySearchTreeIterator to traverse the specified GenericBinarySearchTree
		 */
		public GenericBinarySearchTreeIterator(GenericBinarySearchTree<T> tree)
		{
			// set reference to GenericBinarySearchTree being traversed
 			theTree = tree;
 			
 			// sync mod counts of GenericBinarySearchTree and this GenericBinarySearchTreeIterator
 			expectedModCount = modCount; 			
 		}
		
		
		/**********
		 *	methods
		 *********/
 		
 		/**
 		 *	return the current BinarySearchNode object
 		 *	only accessible within GenericBinarySearchTreeIterator and its subclasses
 		 */
 		protected abstract BinarySearchNode<T> getCurrentNode();
 		
 		
 		/***************************************************
 		 *	methods inherited from interface OrderedIterator
 		 **************************************************/
 		
 		/**
 		 *	return the item in the current node
 		 *	throws a NoSuchElementException if current node is null
 		 */
 		public T getCurrent()
 		{
 			// retrieve current node in traversal
 			BinarySearchNode<T> node = getCurrentNode();
 			
 			if (node == null)
 			{
 				// current node in traversal is invalid
 				throw new NoSuchElementException("invalid node");
 			}
 			else
 			{
 				// return item in current node in traversal
 				return node.theItem;
 			}
 		}

		
		/**
		 *	return true if there are more nodes to traverse
		 */
		public abstract boolean hasNext();


		/**
 		 *	advance to the next node in the traversal
 		 */
		public abstract void next();
		
	}
	
	
	/**
	 *	inner class PreOrderIterator
	 *
	 *	traverses a GenericBinarySearchTree in preorder fashion:
	 *		parent --> left child --> right child
	 */
	protected class PreOrderIterator extends GenericBinarySearchTreeIterator
	{
		
		/*************
		 *	attributes
		 ************/
		
		/** stores nodes yet to be traversed */
		protected Stack<BinarySearchNode<T>> s;

		
		/***************
		 *	constructors
		 **************/
		
		/**
		 *	create a new PreOrderIterator for the specified GenericBinarySearchTree
		 *	iteration begins with the root node
		 */
		public PreOrderIterator(GenericBinarySearchTree<T> tree)
		{
			// invoke constructor of superclass, BinarySearchTree
 			super(tree);
 			
 			// initialize stack
 			s = new ArrayStack<BinarySearchNode<T>>();
 			
 			// if root exists, add root node to stack
 			if (theTree.root != null)
				s.push(theTree.root);
 		}
 		
 		
 		/***************************************************************
 		 *	methods inherited from class GenericBinarySearchTreeIterator
 		 **************************************************************/
 		
 		/**
 		 *	return the current BinarySearchNode in the iteration
 		 *	return null if the stack is empty
 		 */
 		protected BinarySearchNode<T> getCurrentNode()
 		{
 			// if no items left to traverse, return null
 			if (s.isEmpty())
 				return null;
 			
 			// return current BinarySearchNode
 			return s.peek();
 		}
 		
 		
 		/********************************************
 		 *	methods inherited from interface Iterator
 		 *******************************************/
 		
 		/**
 		 *	return true if there are more nodes to traverse
 		 */
 		public boolean hasNext()
 		{
 			// throw exception if BinaryTree is modified outside this PreOrderIterator
 			if(expectedModCount != modCount)
				throw new ConcurrentModificationException();
			
			// if there are items left to traverse, stack will not be empty
 			return !s.isEmpty();
 		}
 		
 		
 		/**
 		 *	advance to the next node in the traversal
 		 */
		public void next()
		{
			// set current node to the node on top of the stack
			BinarySearchNode<T> current = s.pop();
		
			// push right child onto stack
			if (current.right != null)
				s.push(current.right);
		
			// push left child onto stack
			if (current.left != null)
				s.push(current.left);
		}
	}
	
	
	/**
	 *	inner class PostOrderIterator
	 *
	 *	traverses a GenericBinarySearchTree in postorder fashion:
	 *		left child --> right child --> parent
	 */
	protected class PostOrderIterator extends GenericBinarySearchTreeIterator
	{
		
		/*************
		 *	attributes
		 ************/
		
		/** stores nodes yet to be traversed */
		protected Stack<ItrNode> s;
		
		/** stores current BinarySearchNode */
		protected BinarySearchNode<T> current;
		
		
		/***************
		 *	constructors
		 **************/
		
		/**
		 *	create a new PostOrderIterator for the specified GenericBinarySearchTree
		 *	iteration begins with the root node
		 */
		public PostOrderIterator(GenericBinarySearchTree<T> tree)
		{
			// invoke constructor of superclass, BinaryTreeIterator
 			super(tree);
 			
 			// initialize stack
 			s = new ArrayStack<ItrNode>();
 			
 			// if root exists, add root node to stack
 			if (theTree.root != null)
 			{
				s.push(new ItrNode(theTree.root));
				
				// need to prime stack
				next();
			}
 		}
 		
 		
 		/***************************************************************
 		 *	methods inherited from class GenericBinarySearchTreeIterator
 		 **************************************************************/
 		
 		/**
 		 *	return the current BinarySearchNode
 		 */
 		protected BinarySearchNode<T> getCurrentNode()
 		{
 			return current;
 		}
 		
 		
 		/********************************************
 		 *	methods inherited from interface Iterator
 		 *******************************************/
 		
 		/**
 		 *	return true if there are more nodes to traverse
 		 */
 		public boolean hasNext()
 		{
 			// throw exception if GenericBinarySearchTree modified outside this
 			// PostOrderIterator
 			if(expectedModCount != modCount)
				throw new ConcurrentModificationException();
			
			// if next item exists, current will be non-null
 			return current != null;
 		}
 		
 		
 		/**
 		 *	advance to the next node in the traversal
 		 *	throws NoSuchElementException if attempt is made to traverse to a
 		 *	non-existent node
 		 */
		public void next()
		{
			// stores current ItrNode
			ItrNode cnode;
		
			// empty stack and null current node means traversal has gone past last item
			if (s.isEmpty())
			{
				// throw exception if current node is null
				if (current == null)
					throw new NoSuchElementException();
				
				// set current node to null to mark end of traversal
				current = null;
				
				// exit method
				return;
			}
		
			// process the stack
			for ( ; ; )
			{
				// get the top node on the stack
				cnode = (ItrNode)s.pop();
			
				// if this is the 3rd time cnode has been popped, ready to process cnode
				if (++cnode.timesPopped == 3)
				{
					// set current node
					current = cnode.node;
					
					// exit method
					return;
				}
			
				// not ready to process cnode yet, so push it back onto the stack
				s.push(cnode);
			
				if (cnode.timesPopped == 1)
				{
					// first time cnode is popped; proceed to left child
					if (cnode.node.left != null)
						s.push(new ItrNode(cnode.node.left));
				}
				else
				{
					// 2nd time cnode is popped; proceed to right child
					if (cnode.node.right != null)
						s.push(new ItrNode(cnode.node.right));
				}
			}
		}
		
		
		/**
	 	 * inner class ItrNode
	 	 * represents a node that maintains the number of times it has been popped
	 	 * off the stack
	 	 */
		protected class ItrNode
		{
		
			/*************
			 *	attributes
			 ************/
			
			/** stores the BinarySearchNode */
			BinarySearchNode<T> node;
			
			/** # of times popped off the Stack */
			int timesPopped;
		
		
			/***************
			 *	constructors
			 **************/
		
			/**
		 	 * create a new ItrNode that contains the specified BinarySearchNode
		 	 * timesPopped = 0
		 	 */
			ItrNode(BinarySearchNode<T> theNode)
			{
				// store BinarySearchNode
				node = theNode;
				
				// initialize times popped of stack
				timesPopped = 0;
			}
			
			
			/**********
			 *	methods
			 *********/
			
			/**
			 *	return a String representation of the ItrNode
		 	 *	by default, this method simply returns the String representation of
		 	 *	the data item in the BinarySearchNode
		 	 */
		 	public String toString()
		 	{
		 		return node.theItem.toString();
		 	}
		}
	}
	
	
	/**
	 *	inner class InOrderIterator
	 *
	 *	traverses a GenericBinarySearchTree in inorder fashion:
	 *		left child --> parent --> right child
	 */
	protected class InOrderIterator extends PostOrderIterator
	{
		
		/***************
		 *	constructors
		 **************/
		
		/**
		 *	create a new InOrderIterator for the specified GenericBinarySearchTree
		 *	iteration begins with the root node
		 */
		public InOrderIterator(GenericBinarySearchTree<T> tree)
		{
			// invoke constructor of superclass, PostOrderIterator
 			super(tree);
 			
 			// initialize stack
 			s = new ArrayStack<ItrNode>();
 			
 			// if root exists, add root node to stack
 			if (theTree.root != null)
 			{
				s.push(new ItrNode(theTree.root));
				
				// need to prime stack
				next();
			}
 		}
 		
 		
 		/***************************************************************
 		 *	methods inherited from class GenericBinarySearchTreeIterator
 		 **************************************************************/
 		
 		/**
 		 *	return the current BinarySearchNode
 		 */
 		protected BinarySearchNode<T> getCurrentNode()
 		{
 			return current;
 		}
 		
 		
 		/********************************************
 		 *	methods inherited from interface Iterator
 		 *******************************************/
 		
 		/**
 		 *	return true if there are more nodes to traverse
 		 */
 		public boolean hasNext()
 		{
 			// throw exception if GenericBinarySearchTree modified outside this InOrderIterator
 			if(expectedModCount != modCount)
				throw new ConcurrentModificationException();
			
			// if next item exists, current will be non-null
 			return current != null;
 		}
 		
 		
 		/**
 		 *	advance to the next node in the traversal
 		 *	throws NoSuchElementException if attempt is made to traverse to a
 		 *	non-existent node
 		 */
		public void next()
		{
			// stores current ItrNode
			ItrNode cnode;
		
			// empty stack and null current node means traversal has gone past last item
			if (s.isEmpty())
			{
				if (current == null)
					throw new NoSuchElementException();
				
				// set current node to null to mark end of traversal
				current = null;
				
				// exit method
				return;
			}
		
			// process the stack
			for ( ; ; )
			{
				// get the top node on the stack
				cnode = (ItrNode)s.pop();
			
				// 2nd time cnode has been popped; ready to process cnode; push right child onto stack
				if (++cnode.timesPopped == 2)
				{
					// set current node
					current = cnode.node;
					
					// push current node's right child onto stack, if it exists
					if (cnode.node.right != null)
						s.push(new ItrNode(cnode.node.right));
					
					// exit method
					return;
				}
			
				// 1st time cnode has been popped; push it back onto the stack
				s.push(cnode);
				
				// push cnode's left child onto stack, if it exists
				if (cnode.node.left != null)
					s.push(new ItrNode(cnode.node.left));
			}
		}
	}
	
	
	/**
	 *	inner class LevelOrderIterator
	 *
	 *	traverses a GenericBinarySearchTree in level-order fashion:
	 *		one level at a time, beginning with root and proceeding down the tree
	 *		at each level, traverse from left to right
	 */
	protected class LevelOrderIterator extends GenericBinarySearchTreeIterator
	{
		
		/*************
		 *	attributes
		 ************/
		
		/** stores nodes yet to be traversed */
		private ArrayQueue<BinarySearchNode<T>> q;
		
		
		/***************
		 *	constructors
		 **************/
		
		/**
		 *	create a new LevelOrderIterator for the specified GenericBinarySearchTree
		 *	iteration begins with the root node
		 */
		public LevelOrderIterator(GenericBinarySearchTree<T> tree)
		{
			// invoke constructor of superclass, BinarySearchTreeIterator
 			super(tree);

			// initialize queue
 			q = new ArrayQueue<BinarySearchNode<T>>();
 			
 			// if root exists, add root node to queue
 			if (theTree.root != null)
				q.enqueue(theTree.root);
 		}
 		
 		
 		/***************************************************************
 		 *	methods inherited from class GenericBinarySearchTreeIterator
 		 **************************************************************/
 		
 		/**
 		 *	return the current BinarySearchNode in the iteration
 		 *	return null if the queue is empty
 		 */		
 		protected BinarySearchNode<T> getCurrentNode()
 		{
 			// no current node if queue is empty
 			if (q.isEmpty())
 				return null;
 			
 			// return node at front of queue
 			return q.getFront();
 		}
 		
 		
 		/********************************************
 		 *	methods inherited from interface Iterator
 		 *******************************************/
 		
 		/**
 		 *	return true if there are more nodes to traverse
 		 */
 		public boolean hasNext()
 		{
 			// throw exception if GenericBinarySearchTree modified outside this LevelOrderIterator
 			if(expectedModCount != modCount)
				throw new ConcurrentModificationException();
			
			// if next item exists, queue will not be empty
 			return !q.isEmpty();
 		}
 		
 		
 		/**
 		 *	advance to the next node in the traversal
 		 */
		public void next()
		{
			// set current node to first node in queue
			BinarySearchNode<T> current = q.dequeue();

			// enqueue current node's left child, if it exists
			if (current.left != null)
				q.enqueue(current.left);
			
			// enqueue current node's right child, if it exists
			if (current.right != null)
				q.enqueue(current.right);
		}
	}


	/**
	 *	nested class BinarySearchNode
	 *	stores a data item and references to left and right child nodes
	 */
	private static class BinarySearchNode <E extends Comparable<? super E>>
	{

		/*************
		 *	attributes
		 ************/
		
		/** data item */
		E theItem;
		
		/** left child */
		BinarySearchNode<E> left;
		
		/** right child */
		BinarySearchNode<E> right;


		/***************
		 *	constructors
		 **************/

		/**
		 *	create a new BinarySearchNode containing the specified item
		 *	left and right child nodes will be null
		 */
		public BinarySearchNode(E obj)
		{
			theItem = obj;
			left = null;
			right = null;
		}

		
		/**
		 *	create a new BinarySearchNode containing the specified item, with the
		 *	specified left and right child nodes
		 */
		public BinarySearchNode(E obj, BinarySearchNode<E> leftChild, BinarySearchNode<E> rightChild)
		{
			theItem = obj;
			left = leftChild;
			right = rightChild;
		}
		
		
		/**********
		 *	methods
		 *********/
		
		/**
		 *	return a String representation of a BinarySearchNode
		 *	by default, this method simply returns the String representation of
		 *	the data item in the node
		 */
		public String toString()
		{
			return theItem.toString();
		}

	}

}