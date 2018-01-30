/*
 * Authors: Connor Morrison, Chris Miller
 * Date:12/06/17
 * Overview: This program creates a Huffman Tree from a text document, encodes the document, then decodes it.
 * */
package assignmentOne;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;


class Node{
	public Node leftNode;
	public Node rightNode;
	public int freq;
	public String id;
	
}
class Tree{
	public Node root;
	public Tree(){
		root =null;
	}
	public void insert(String id, int freq){
		Node node = new Node();
		node.id = id;
		node.freq = freq;
		node.leftNode = null;
		node.rightNode = null;
		if(root ==null){
			root = node;
		}else{
			Node current = root;
			Node parent;
			while(true){
				parent = current;
				if(freq < current.freq){
					current = current.leftNode;
					if(current == null) {             // if the end of the line        
						parent.leftNode = node;   // insert on left
						return;                    
					}
				}else{
					current = current.rightNode;
					if(current == null){
						parent.rightNode = node;
						return;    
					}
				}
			}
		}
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
					System.out.print(temp.freq);
					localStack.push(temp.leftNode);
					localStack.push(temp.rightNode);
					if (temp.leftNode != null ||
							temp.rightNode != null) {
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
public class main {
	public static void main(String[] args)throws IOException{
		Tree theTree = new Tree();

		// TODO Auto-generated method stub
		File input = new File("input.txt");
		File output = new File("output.txt");
		BufferedReader br = new BufferedReader(new FileReader(input));
		FileWriter fp = new FileWriter(output);
		int[] a = {01100001,0};
		int[] b = {01100010,0};
		int[] c = {01100011,0};
		int[] d = {01100100,0};
		int[] e = {01100101,0};
		int[] f = {01100110,0};
		int[] g = {01100111,0};
		int[] h = {01101000,0};
		int[] i = {01101001,0};
		int[] j = {01101010,0};
		int[] k = {01101011,0};
		int[] l = {01101100,0};
		int[] m = {01101101,0};
		int[] n = {01101110,0};
		int[] o = {01101111,0};
		int[] p = {01110000,0};
		int[] q = {01110001,0};
		int[] r = {01110010,0};
		int[] s = {01110011,0};
		int[] t = {01110100,0};
		int[] u = {01110101,0};
		int[] v = {01110110,0};
		int[] w = {01110111,0};
		int[] x = {01111000,0};
		int[] y = {01111001,0};
		int[] z = {01111010,0};
		//capital letters
		int[] A = {01000001,0};
		int[] B = {01000010,0};
		int[] C = {01000011,0};
		int[] D = {01000100,0};
		int[] E = {01000101,0};
		int[] F = {01000110,0};
		int[] G = {01000111,0};
		int[] H = {01001000,0};
		int[] I = {01001001,0};
		int[] J = {01001010,0};
		int[] K = {01001011,0};
		int[] L = {01001100,0};
		int[] M = {01001101,0};
		int[] N = {01001110,0};
		int[] O = {01001111,0};
		int[] P = {01010000,0};
		int[] Q = {01010001,0};
		int[] R = {01010010,0};
		int[] S = {01010011,0};
		int[] T = {01010100,0};
		int[] U = {01010101,0};
		int[] V = {01010110,0};
		int[] W = {01010111,0};
		int[] X = {01011000,0};
		int[] Y = {01011001,0};
		int[] Z = {01011010,0};
		//counting the components.
		/*
		 *  x Accept a text message.
			x Construct a frequency table for the message.
			Create a Huffman tree for this message.
			Create a code table.
			Encode the message into binary
			Decode the message from binary back to text.
		 * 
		 * */
		String text = br.readLine();
		System.out.println(text);
		String[] comps = text.split("");
		for(int i2 = 0; i2 < comps.length; i2++){
			if(comps[i2].equals("a")){
				a[1] +=1;
			}if(comps[i2].equals("b")){
				b[1] +=1;
			}if(comps[i2].equals("c")){
				c[1] +=1;
			}if(comps[i2].equals("d")){
				d[1] +=1;
			}if(comps[i2].equals("e")){
				e[1] +=1;
			}if(comps[i2].equals("f")){
				f[1] +=1;
			}if(comps[i2].equals("g")){
				g[1] +=1;
			}if(comps[i2].equals("h")){
				h[1] +=1;
			}if(comps[i2].equals("i")){
				i[1] +=1;
			}if(comps[i2].equals("j")){
				j[1] +=1;
			}if(comps[i2].equals("k")){
				k[1] +=1;
			}if(comps[i2].equals("l")){
				l[1] +=1;
			}if(comps[i2].equals("m")){
				m[1] +=1;
			}if(comps[i2].equals("n")){
				n[1] +=1;
			}if(comps[i2].equals("o")){
				o[1] +=1;
			}if(comps[i2].equals("p")){
				p[1] +=1;
			}if(comps[i2].equals("q")){
				q[1] +=1;
			}if(comps[i2].equals("r")){
				r[1] +=1;
			}if(comps[i2].equals("s")){
				s[1] +=1;
			}if(comps[i2].equals("t")){
				t[1] +=1;
			}if(comps[i2].equals("u")){
				u[1] +=1;
			}if(comps[i2].equals("v")){
				v[1] +=1;
			}if(comps[i2].equals("w")){
				w[1] +=1;
			}if(comps[i2].equals("x")){
				x[1] +=1;
			}if(comps[i2].equals("y")){
				y[1] +=1;
			}if(comps[i2].equals("z")){
				z[1] +=1;
			}
			//capital letters
			if(comps[i2].equals("A")){
				A[1] +=1;
			}if(comps[i2].equals("B")){
				B[1] +=1;
			}if(comps[i2].equals("C")){
				C[1] +=1;
			}if(comps[i2].equals("D")){
				D[1] +=1;
			}if(comps[i2].equals("E")){
				E[1] +=1;
			}if(comps[i2].equals("F")){
				F[1] +=1;
			}if(comps[i2].equals("G")){
				G[1] +=1;
			}if(comps[i2].equals("H")){
				H[1] +=1;
			}if(comps[i2].equals("I")){
				I[1] +=1;
			}if(comps[i2].equals("J")){
				J[1] +=1;
			}if(comps[i2].equals("K")){
				K[1] +=1;
			}if(comps[i2].equals("L")){
				L[1] +=1;
			}if(comps[i2].equals("M")){
				M[1] +=1;
			}if(comps[i2].equals("N")){
				N[1] +=1;
			}if(comps[i2].equals("O")){
				O[1] +=1;
			}if(comps[i2].equals("P")){
				P[1] +=1;
			}if(comps[i2].equals("Q")){
				Q[1] +=1;
			}if(comps[i2].equals("R")){
				R[1] +=1;
			}if(comps[i2].equals("S")){
				S[1] +=1;
			}if(comps[i2].equals("T")){
				T[1] +=1;
			}if(comps[i2].equals("U")){
				U[1] +=1;
			}if(comps[i2].equals("V")){
				V[1] +=1;
			}if(comps[i2].equals("W")){
				W[1] +=1;
			}if(comps[i2].equals("X")){
				X[1] +=1;
			}if(comps[i2].equals("Y")){
				Y[1] +=1;
			}if(comps[i2].equals("Z")){
				Z[1] +=1;
			}
		}
		String[] lettersLow = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		int[] lettersLow2 = {a[1],b[1],c[1],d[1],e[1],f[1],g[1],h[1],i[1],j[1],k[1],l[1],m[1],n[1],o[1],p[1],q[1],r[1],s[1],t[1],u[1],v[1],w[1],x[1],y[1],z[1]};
		String[] lettersUp = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	    System.out.println("Frequency Table");
	    System.out.println("---------------");
		System.out.println("CHAR   COUNT");
		for(String x3 : lettersLow){
			int index = 0;
			for(int i4 = 0; i4 < lettersLow.length; i4++){
				if(lettersLow[i4] == x3){
					index = i4;
				}
			}
		    if(lettersLow2[index] > 0){
		    	theTree.insert(x3,lettersLow2[index]);
			    System.out.println(x3 + "      " + lettersLow2[index]);
		    }
		}
		theTree.displayTree();
	}
}
