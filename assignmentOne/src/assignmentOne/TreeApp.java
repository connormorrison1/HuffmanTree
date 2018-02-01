package treeApp.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.AbstractQueue;
import java.util.Comparator;
import java.util.PriorityQueue;

//////////////////////////////////////////////////////////////
class Node {
	public int iData;           // data item (key)
	public char dData;        // data item
	public Node leftChild;      // this Node's left child
	public Node rightChild;     // this Node's right child

	public void displayNode() { // display ourself
		System.out.print('{');
		System.out.print(iData);
		System.out.print(", ");
		System.out.print(dData);
		System.out.print("} ");		
	}
	
	public int getIData()
	{
		return iData;
	}
	
	public boolean hasChild()
	{
		if(leftChild != null || rightChild != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
} // end Class Node
////////////////////////////////////////////////////////////////

class Tree {
	private Node root;                 // first Node of Tree
	
	public Tree() {                    // constructor
		root = null;                   // no nodes in tree yet
	}
	
	
	public Node find(int key) {      // find node with given key
		Node current = root;         // (assumes non-empty tree)
		while (current.iData != key) {          // while no match
			if (key < current.iData) {          // go left?
				current =  current.leftChild; 
			}
			else {                              // or go right?
				current =  current.rightChild;
			}
			if(current == null)                 // if no child
			{                                   // didn't find it
				return null;              
			}			
		}
		return current;                         // found it
	}  //end find()
	
	
	public void insert(int id, char dd) {
		Node newNode = new Node();    // make new Node
		newNode.iData = id;           // insert data
		newNode.dData = dd;
		newNode.leftChild = null;
		newNode.rightChild = null;
		if(root == null) {            // no node in root
			root = newNode;
		}
		else {                        // root occupied
			Node current = root;      // start at root  
			Node parent;
			while (true) {            // exits internally			
				parent = current;  
				if (id < current.iData) {              // go left?
					current =  current.leftChild;
					if(current == null) {             // if the end of the line        
						parent.leftChild = newNode;   // insert on left
						return;                    
					}
				} //end if go left
				else {                                // or go right?
					current =  current.rightChild;      
					if(current == null)               // if the end of the line
					{                                 // insert on right
						parent.rightChild = newNode;
						return;                    
					}
				}
			}
		}
	} // end insert()

	
	public boolean delete(int key) {             // delete node with given key
		Node current = root;		             // (assumes non-empty list)
		Node parent = root;
		boolean isLeftChild = true;

		while (current.iData != key) {           // search for Node
			parent = current;
			if (key < current.iData) {           // go left?
				isLeftChild = true;
				current =  current.leftChild;
			}
			else {                               // or go right?
				isLeftChild = false;
				current =  current.rightChild;
			}
			if(current == null) {                // end of the line,                             
				return false;                    // didn't find it
			}			
		}
		//found the node to delete

		//if no children, simply delete it
		if (current.leftChild == null && current.rightChild == null) {
			if (current == root) {              // if root,
				root = null;                    // tree is empty
			}
			else if (isLeftChild) {
				parent.leftChild = null;        // disconnect
			}                                   // from parent
			else {
				parent.rightChild = null;
			}
		}
		//if no right child, replace with left subtree
		else if (current.rightChild == null) {  
			if (current == root) {
				root = current.leftChild;
			}
			else if (isLeftChild) {
				parent.leftChild = current.leftChild;
			}			
			else {
				parent.rightChild = current.leftChild;
			}
		}

		//if no left child, replace with right subtree
		else if (current.leftChild == null) {  
			if (current == root) {
				root = current.rightChild;
			}
			else if (isLeftChild) {
				parent.leftChild = current.rightChild;
			}			
			else {
				parent.rightChild = current.rightChild;
			}
		}

		else { // two children, so replace with inorder successor
			   // get successor of node to delete (current)
			Node successor = getSuccessor(current);

			// connect parent of current to successor instead
			if (current == root) {
				root = successor;
			}
			else if (isLeftChild) {
				parent.leftChild = successor;
			}
			else {
				parent.rightChild = successor;
			}

			//connect successor to current's left child
			successor.leftChild = current.leftChild;
		} // end else two children
		// (successor cannot have a left child)
		return true;              // success
	}// end delete()

	
	//returns node with next-highest value after delNode
	//goes right child, then right child's left descendants
	private Node getSuccessor(Node delNode) {
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild;        // go to the right child
		while (current != null) {                 // until no more
			successorParent = successor;          // left children
			successor = current;
			current = current.leftChild;
		}

		if (successor != delNode.rightChild) {    // if successor not right child,
			//make connections
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}

	
	public void traverse(int traverseType) {
		switch (traverseType) {
		case 1:
			System.out.print("\nPreorder traversal: ");
			preOrder(root);
			break;
		case 2:
			System.out.print("\nInorder traversal: ");
			inOrder(root);
			break;
		case 3:
			System.out.print("\nPostorder traversal: ");
			postOrder(root);
			break;
		default:
			System.out.print("Invalid traversal type\n");
			break;
		}
		System.out.println();
	}

	
	private void preOrder(Node localRoot) {
		if (localRoot != null) {
			System.out.print(localRoot.iData + " ");	
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);	
		}
	}

	
	private void inOrder(Node localRoot) {
		if (localRoot != null) {
			inOrder(localRoot.leftChild);
			System.out.print(localRoot.iData + " ");
			inOrder(localRoot.rightChild);		
		}
	}

	
	private void postOrder(Node localRoot) {
		if (localRoot != null) {
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			System.out.print(localRoot.iData + " ");		
		}
	}
	
	public void makeTree(Node n)
	{
		root = n;
	}

	
	public void displayTree() {
		Stack<Node> globalStack = new Stack<Node>();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println(
				".................................................................");
		while (isRowEmpty==false) {
			Stack<Node> localStack = new Stack<Node>();
			isRowEmpty = true;
			
			for (int j = 0; j < nBlanks; j++) {
				System.out.print(' ');
			}

			while (globalStack.isEmpty()==false) {
				Node temp = (Node) globalStack.pop();
				if (temp != null) {
					System.out.print(temp.iData);
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);
					if (temp.leftChild != null ||
							temp.rightChild != null) {
						isRowEmpty = false;
					}
				}
				else {
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}

				for (int j = 0; j < nBlanks*2-2; j++) {
					System.out.print(' ');
				}
			} // end while globalStack not empty
			System.out.println();
			nBlanks /= 2;
			while (localStack.isEmpty()==false) {
				globalStack.push(localStack.pop());
			} // end while isRowEmpty is false
			System.out.println(
			".................................................................");
		} // end displayTree()
		
		
	} // end class Tree
}
////////////////////////////////////////////////////////////////

class TreeApp {

	public static void main(String[] args) throws IOException {
		Node test1 = new Node();
		Node test2 = new Node();
		Node test3 = new Node();
		Node test4 = new Node();
		Node test5 = new Node();
		Node test6 = new Node();
		Node test7 = new Node();
		Node test8 = new Node();
		Node test9 = new Node();
		Tree theTree = new Tree();
		test1.iData = 1;
		test1.dData = 'h';
		test2.dData = 'k';
		test3.dData = 'l';
		test4.dData = 'w';
		test2.iData = 1;
		test3.iData = 2;
		test4.iData = 4;
		test5.iData = 2;
		test6.iData = 2;
		test7.iData = 6;
		test8.iData = 3;
		test9.iData = 1;
		
		Comparator<Node> comparator = new NodeComparator();
		
		PriorityQueue<Node> theQueue = new PriorityQueue<Node>(10, comparator);
		theQueue.add(test1);
		theQueue.add(test2);
		theQueue.add(test3);
		theQueue.add(test4);
		theQueue.add(test5);
		theQueue.add(test6);
		theQueue.add(test7);
		theQueue.add(test8);
		theQueue.add(test9);
		
		Node iterator1 = new Node();
		Node iterator2 = new Node();
		while(!(theQueue.isEmpty()))
		{
			if(theQueue.size() == 1)
			{
				break;
			}
			iterator1 = theQueue.poll();
			iterator2 = theQueue.poll();
			Node iterator3 = new Node(); 
			iterator3.iData = iterator1.iData + iterator2.iData;
			iterator3.leftChild = iterator1;
			iterator3.rightChild = iterator2;
			theQueue.add(iterator3);
		}
		theTree.makeTree(theQueue.poll());
		theTree.displayTree();
		
	} // end main()

	
	private static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
	
	private static int getChar() throws IOException {
		String s = getString();
		return s.charAt(0);
	}
	
	private static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}	
}  // end TreeApp class
////////////////////////////////////////////////////////////////

class NodeComparator implements Comparator<Node>
{
	public int compare(Node x, Node y)
	{
		if(x.getIData() < y.getIData())
		{
			return -1;
		}
		if(x.getIData() > y.getIData())
		{
			return 1;
		}
		if(x.getIData() == y.getIData()&& x.hasChild())
		{
			return -1;
		}
		if(x.getIData() == y.getIData()&& y.hasChild())
		{
			return 1;
		}
		else 
		{
			return 0;
		}
	}
}
