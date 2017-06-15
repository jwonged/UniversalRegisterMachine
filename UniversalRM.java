package uk.ac.cam.dsjw2.RegisterMachine;

import java.util.ArrayList;

public class UniversalRM {
	private static class Pair {
		public int x, y;
		public Pair(int x, int y) {this.x=x; this.y=y;}
	}
	ArrayList<Integer> regs = new ArrayList<Integer>();
	ArrayList<Integer> args = new ArrayList<Integer>();
	ArrayList<Integer> prog = new ArrayList<Integer>();
	int pc = 0;
	
	//takes in encoded program e and encoded args a
	public UniversalRM(int e, int a) {
		regs.add(0); //R0 = 0
		getlist(e,prog);
		getlist(a,args);
		getlist(a,regs);
	}
	
	//decode number as list
	private void getlist(int dec, ArrayList<Integer> list) {
		int n = 0;
		while (dec>0) {
			if ((dec&1)==0) n++;
			else {
				//add item and reset n
				list.add(n);
				n=0;
			}
			dec=dec>>1;
		}
	}
	public Pair decodePair(int N, int brackets) {
		//brackets refers to <x,y> or <<x,y>> : 1 or 2 
		int check;
		if (brackets != 1 && brackets != 2) System.out.println("bracket decode error");
		if (brackets==1) check = 1;
		else check = 0;
		
		int x=0;
		while (N>0) {
			if ((N&1)==check) x++;
			else break;
			N>>=1;
		}
		N>>=1;
		
		return new Pair(x,N);
	}
	public void decode(int N) {
		if (N==0) halt();//halt
		else if (N>0) { 
			
			//decode <<x,y>>
			Pair pair = decodePair(N,2);
			
			if ((pair.x%2)==0) {
				add(pair.x/2,N);//add
			}
			else {
				Pair innerPair = decodePair(pair.y,1);
				minus(pair.x, innerPair.x, innerPair.y);
				
				System.out.println(pair.x);
				System.out.println(pair.y);
				System.out.println(innerPair.x);
				System.out.println(innerPair.y);
			}
			
			
		}
	}
	private void add(int i, int j) {
		
	}
	private void minus(int i, int j, int k) {
		
	}
	private void halt() {
		System.out.println(args);
	}
	public void compute() {
		int N = prog.get(0);
		
	}

}
