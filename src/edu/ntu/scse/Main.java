package edu.ntu.scse;

import edu.ntu.scse.entity.MOBLIMA;

/**
 * 
 * @author Kailing
 *
 */
public class Main {
	public static void main(String[] args) {
		System.out.println("Start of Application.");

		MOBLIMA moblima = new MOBLIMA();
		moblima.displayLoginMenu();
		moblima.saveData();
		
		System.out.println("End of Application.");
	}
}
