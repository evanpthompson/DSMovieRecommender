package dataStructures;

public class BinarySearchTreeClient {

	public static void main(String[] args) {
		GenericBinarySearchTree<Integer> intTree = new GenericBinarySearchTree<Integer>();
		
		intTree.add(18);
		intTree.add(12);
		intTree.add(4);
		intTree.add(1);
		intTree.add(3);
		intTree.add(15);
		intTree.add(13);
		intTree.add(14);
		intTree.add(17);
		intTree.add(25);
		intTree.add(24);
		intTree.add(30);
		intTree.add(28);
		intTree.add(29);
		
		OrderedIterator<Integer> iterator = intTree.levelOrderIterator();
		
		while (iterator.hasNext()) {
			System.out.print(iterator.getCurrent() + " ");
			iterator.next();
		}
		
		System.out.println();
		
		//System.out.println(intTree.getMinItem());
		
		System.out.println(intTree.getLargestSmallerThanK(1));
		
		System.out.println(intTree.getSmallestLargerThanK(1));
		
	}

}
