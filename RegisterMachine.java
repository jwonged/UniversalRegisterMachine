package uk.ac.cam.dsjw2.RegisterMachine;

import java.util.ArrayList;

public class RegisterMachine {

	public static void main(String[] args) {
		/*Scanner in = new Scanner(System.in);
		System.out.println("Enter encoded program: ");
		e = in.nextLine();
		System.out.println("Enter encoded arguments list: ");
		a = in.nextLine();*/
		
		UniversalRM rm = new UniversalRM(0,0);
		rm.decode(18);
		
		

	}

}
