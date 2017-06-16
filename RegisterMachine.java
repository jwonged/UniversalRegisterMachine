package uk.ac.cam.dsjw2.RegisterMachine;

public class RegisterMachine {

	public static void main(String[] args) {
		int e = Integer.parseInt(args[0]);
		int a = Integer.parseInt(args[1]);
		
		UniversalRM rm = new UniversalRM(e,a);
		rm.compute();
		
	}

}
