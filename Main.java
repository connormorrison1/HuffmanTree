/*
 * Authors: Connor Morrison, Christopher Miller
 * Date:12/06/17
 * Overview: This program creates a Huffman Tree from a text document, encodes the document, then decodes it.
 * Some code for the Tree class attributed by Robert Lafore. 2002 Data Structures and Algorithms in Java (2 ed.). Sams, Indianapolis, IN, USA
 * */
package assignmentOne;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

class Node{
	public Node leftNode;
	public Node rightNode;
	public int freq;
	public String id;
	public boolean hasChild()
	{
		if(leftNode != null || rightNode != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}

class Tree{
	public Node root;
	public Tree(){
		root =null;
	}
	public void makeTree(Node n)
	{
		root = n;
	}
	ArrayList<String> codes = new ArrayList<String>();
	ArrayList<String> id = new ArrayList<String>();
	public void codeTable(Node localRoot,String encodedStr) {
		if (localRoot!=null) {
			//checking if leftNode exists, if so then go left and add a 0
		        if (localRoot.leftNode!=null) {
				codeTable(localRoot.leftNode, encodedStr+"0");
		    	}
			//checking if rightNode exists, if so then go right and add a 1
		    	if (localRoot.rightNode!=null) {   
				codeTable(localRoot.rightNode,encodedStr+"1");
		   	}
		    	else {
				codes.add(encodedStr);
				id.add(localRoot.id);
		    	}
		}       
    	}
	//printing code table
	String[][] codeT;
	public void constructCodeTable(int size) {
		codeT = new String[size][2];
		System.out.println("Code Table");
		System.out.println("----------");
		//getting the id and code for each element.
		for(int i =0; i < codes.size(); i++) {
			codeT[i][0] = id.get(i);
			codeT[i][1] = codes.get(i);
			System.out.print(codeT[i][0] + "    " + codeT[i][1]);
			System.out.println("");
		}
	}
	//displaying the encoded message
	public String displayEncode(String origText) {
		String encoded = "";
		String brokenEncoded = "";
		String[] brokenUp = origText.split("");
		//loop through each arrayList
		//find where id = codes index and push code to encoded string.
		for(int i = 0; i < brokenUp.length; i++) {
			for(int x = 0; x < id.size(); x++) {
				if(brokenUp[i].equals(id.get(x))) {
					encoded+=codes.get(x);
					brokenEncoded+= codes.get(x)+" ";
				}
			}
		}
		System.out.println(encoded);
		return brokenEncoded;
	}
	//decode the string method
	public String decode(String encoded) {
		String decoded = "";
		String brokenDecoded = "";
		String[] brokenUp = encoded.split(" ");
		//find where id = code index and grab id and push to decoded string
		for(int i = 0; i < brokenUp.length; i++) {
			for(int x = 0; x < codes.size(); x++) {
				if(brokenUp[i].equals(codes.get(x))) {
					decoded+=id.get(x);
					brokenDecoded+= id.get(x);
				}
			}
		}
		System.out.println(brokenDecoded);
		return brokenDecoded;
	}
}

public class Main {
	public static void main(String[] args)throws IOException{
		//create the tree
		Tree theTree = new Tree();
		Comparator<Node> comparator = new NodeComparator();
		//create the files to read from
		File input = new File("input.txt");
		File output = new File("output.txt");
		//create BufferedReader to read from files and FileWriter to write to.
		BufferedReader br = new BufferedReader(new FileReader(input));
		FileWriter fp = new FileWriter(output);
		//[rows][columns]
		//create multi-dimensional array containing 
		//all letters and their counts of frequency.
		String[][] multi = new String[][]{
			  { " ", "0"},
			  { "a", "0"},
			  { "b", "0"},
			  { "c", "0"},
			  { "d", "0"},
			  { "e", "0"},
			  { "f", "0"},
			  { "g", "0"},
			  { "h", "0"},
			  { "i", "0"},
			  { "j", "0"},
			  { "k", "0"},
			  { "l", "0"},
			  { "m", "0"},
			  { "n", "0"},
			  { "o", "0"},
			  { "p", "0"},
			  { "q", "0"},
			  { "r", "0"},
			  { "s", "0"},
			  { "t", "0"},
			  { "u", "0"},
			  { "v", "0"},
			  { "w", "0"},
			  { "x", "0"},
			  { "y", "0"},
			  { "z", "0"},
			  { "A", "0"},
			  { "B", "0"},
			  { "C", "0"},
			  { "D", "0"},
			  { "E", "0"},
			  { "F", "0"},
			  { "G", "0"},
			  { "H", "0"},
			  { "I", "0"},
			  { "J", "0"},
			  { "K", "0"},
			  { "L", "0"},
			  { "M", "0"},
			  { "N", "0"},
			  { "O", "0"},
			  { "P", "0"},
			  { "Q", "0"},
			  { "R", "0"},
			  { "S", "0"},
			  { "T", "0"},
			  { "U", "0"},
			  { "V", "0"},
			  { "W", "0"},
			  { "X", "0"},
			  { "Y", "0"},
			  { "Z", "0"},
		};
		//read from the file using a BufferedReader
		String text = br.readLine();
		System.out.println(text);
		String[] comps = text.split("");
		String[] compon = {" ", "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		String[] compon2 = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		//checking to see what element is contained within each portion of the message.
		for(int i2 = 0; i2 < comps.length; i2++) {
			//checking lowercase
			for(int w2 = 0; w2<compon.length; w2++) {
				if(comps[i2].equals(compon[w2])) {
					//increment the count of of the specified letter.
					int val = Integer.parseInt(multi[w2][1]);
					val+=1;
					multi[w2][1] = String.valueOf(val);
				}
			}
			//checking uppercase
			for(int x2 = 0; x2<compon2.length; x2++){
				if(comps[i2].equals(compon2[x2])){
					//increment the count of of the specified letter.
					int val = Integer.parseInt(multi[x2+27][1]);
					val+=1;
					multi[x2+27][1] = String.valueOf(val);
				}
			}
		}
		//counting number of elements in the array
		String[] id = new String[30];
		int[] freqCount = new int[30];
		int counter =0;
		for(int w3 = 0; w3 < multi.length; w3++){
			if(Integer.parseInt(multi[w3][1])>0){
				id[counter] = multi[w3][0];
				freqCount[counter] = Integer.parseInt(multi[w3][1]);
				counter++;
			}
		}
		//printing the frequency table
		String[][] finTable = new String[id.length][2];
		int totalLetters = 0;
		System.out.println("");
	    	System.out.println("Frequency Table");
	    	System.out.println("---------------");
		System.out.println("CHAR   COUNT");
		for(int t4 = 0; t4 < id.length; t4++){
			if(id[t4] != null){
				//adding the elements to an array
				finTable[t4][0] = id[t4];
				finTable[t4][1] = String.valueOf(freqCount[t4]);
				totalLetters++;
				//grabbing and printing from an array
				System.out.println(finTable[t4][0] + "      " + finTable[t4][1]);
				
			}
		}
		//priority Queue(total number of letters, comparator)
		//using a priority queue to populate the huffman tree
		PriorityQueue<Node> theQueue = new PriorityQueue<Node>(totalLetters, comparator);
		for(int t5 = 0; t5 < id.length; t5++) {
			if(id[t5] != null){
				//adding the nodes with id and freq to the queue
				Node queueAdder = new Node();
				queueAdder.id = finTable[t5][0];
				queueAdder.freq = Integer.parseInt(finTable[t5][1]);
				theQueue.add(queueAdder);
			}
		}
		//two nodes to get the values for each layer of the huffman tree
		Node iterator1 = new Node();
		Node iterator2 = new Node();
		while(!(theQueue.isEmpty()))
		{
			if(theQueue.size() == 1)
			{
				break;
			}
			//removing two nodes from the queue to compare
			iterator1 = theQueue.poll();
			iterator2 = theQueue.poll();
			Node iterator3 = new Node(); 
			//creates a new node with the freq of the sum of the two compared nodes
			iterator3.freq = iterator1.freq + iterator2.freq;
			//sets that node as the parent
			iterator3.leftNode = iterator1;
			iterator3.rightNode = iterator2;
			theQueue.add(iterator3);
		}
		//assigning the root
		theTree.makeTree(theQueue.poll());
		System.out.println("");
		//display everything
		//theTree.displayTree();
		theTree.codeTable(theTree.root, "");
		theTree.constructCodeTable(theTree.codes.size());
		System.out.println("");
		String encoded = theTree.displayEncode(text);
		String decoded = theTree.decode(encoded);
		fp.write(decoded);
		fp.close();
		br.close();
	}
	

}

class NodeComparator implements Comparator<Node>
{
	//compare the two nodes
	public int compare(Node x, Node y)
	{
		//ordering the nodes in accordance to their frequencies.
		if(x.freq < y.freq)
		{
			return -1;
		}
		if(x.freq > y.freq)
		{
			return 1;
		}
		if(x.freq == y.freq&& x.hasChild())
		{
			return -1;
		}
		if(x.freq == y.freq&& y.hasChild())
		{
			return 1;
		}
		else 
		{
			return 0;
		}
	}
	
}
