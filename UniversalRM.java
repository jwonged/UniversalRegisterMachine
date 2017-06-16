package uk.ac.cam.dsjw2.RegisterMachine;

import java.util.ArrayList;

public class UniversalRM {
	private static class Pair {
		public int x, y;
		public Pair(int x, int y) {this.x=x; this.y=y;}
	}
	ArrayList<Integer> regs = new ArrayList<Integer>();
	ArrayList<Integer> prog = new ArrayList<Integer>();
	int pc = 0;
	
	//takes in encoded program e and encoded args a
	public UniversalRM(int e, int a) {
		regs.add(0); //R0 = 0
		getlist(e,prog);
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
	private Pair decodePair(int N, int brackets) {
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
	private void decode(int N) {
		if (N==0) halt();//halt
		else if (N>0) { 
			
			//decode as <<x,y>>
			Pair pair = decodePair(N,2);
			
			if ((pair.x%2)==0) {
				add(pair.x/2,N);//add
			} else {
				//decode as <<2i+1, <j,k> >>
				Pair innerPair = decodePair(pair.y,1);
				pair.x--;
				System.out.println(pair.x);
				System.out.println(innerPair.x);
				System.out.println(innerPair.y);
				
				minus(pair.x/2, innerPair.x, innerPair.y);
				
			}
		}
	}
	private void add(int i, int j) {
		//add 1 to reg i and run instruction j
		System.out.println("L"+pc+" : R"+i+"+ --> L"+j);
		pc++;
		pad(i);
		regs.set(i,regs.get(i)+1);
		if (j>=prog.size()) halt(1);
		else decode(prog.get(j));
	}
	private void minus(int i, int j, int k) {
		//if greater then 0, subtract and goto j, else k
		System.out.println("L"+pc+" : R"+i+"- --> L"+j+",L"+k);
		pc++;
		pad(i); 
		if (regs.get(i)>0) {
			regs.set(i,regs.get(i)-1);
			if (j>=prog.size()) halt(1);
			else decode(prog.get(j));
		} else {
			if (k>=prog.size()) halt(1);
			else decode(prog.get(k));
		}
	}
	private void pad(int n) {
		while (regs.size()<n) regs.add(0);
	}
	private void halt() {
		pc++;
		System.out.println("HALT");
		System.out.println(regs);
	}
	private void halt(int x) {
		System.out.println("ERRONEOUS HALT");
		System.out.println(regs);
	}
	public void compute() {
		if (prog.isEmpty()) decode(0);
		else decode(prog.get(0));
	}

}
