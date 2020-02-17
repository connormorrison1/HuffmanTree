package treeApp.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.AbstractQueue;
import java.util.Comparator;
import java.util.PriorityQueue;

class Node {
	public int iData;           
	public char dData;      
	public Node leftChild;      
	public Node rightChild; 

	public void displayNode() {
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
}

class Tree {
	private Node root;                 
	
	public Tree() {                
		root = null;              
	}
	
	
	public Node find(int key) {    
		Node current = root;       
		while (current.iData != key) {        
			if (key < current.iData) {     
				current =  current.leftChild; 
			}
			else {                           
				current =  current.rightChild;
			}
			if(current == null)                
			{                              
				return null;              
			}			
		}
		return current;
	} 
	
	
	public void insert(int id, char dd) {
		Node newNode = new Node();   
		newNode.iData = id;
		newNode.dData = dd;
		newNode.leftChild = null;
		newNode.rightChild = null;
		if(root == null) {          
			root = newNode;
		}
		else {  
			Node current = root;
			Node parent;
			while (true) {			
				parent = current;  
				if (id < current.iData) {            
					current =  current.leftChild;
					if(current == null) {    
						parent.leftChild = newNode; 
						return;                    
					}
				}
				else {
					current =  current.rightChild;      
					if(current == null)               
					{                            
						parent.rightChild = newNode;
						return;                    
					}
				}
			}
		}
	}

	
	public boolean delete(int key) {
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;

		while (current.iData != key) {
			parent = current;
			if (key < current.iData) {
				isLeftChild = true;
				current =  current.leftChild;
			}
			else {
				isLeftChild = false;
				current =  current.rightChild;
			}
			if(current == null) {                         
				return false;
			}			
		}

		if (current.leftChild == null && current.rightChild == null) {
			if (current == root) {
				root = null;
			}
			else if (isLeftChild) {
				parent.leftChild = null;
			}
			else {
				parent.rightChild = null;
			}
		}
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

		else { 
			Node successor = getSuccessor(current);
			if (current == root) {
				root = successor;
			}
			else if (isLeftChild) {
				parent.leftChild = successor;
			}
			else {
				parent.rightChild = successor;
			}

			successor.leftChild = current.leftChild;
		} 
		return true;     
	}

	private Node getSuccessor(Node delNode) {
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild;       
		while (current != null) {            
			successorParent = successor;        
			successor = current;
			current = current.leftChild;
		}

		if (successor != delNode.rightChild) {   
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
		System.out.println(".................................................................");
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
			}
			System.out.println();
			nBlanks /= 2;
			while (localStack.isEmpty()==false) {
				globalStack.push(localStack.pop());
			}
			System.out.println(".................................................................");
		}
		
		
	} 
}

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
		
	}
	
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
}

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
