package edu.ntu.scse.boundary;

import java.util.Scanner;

import edu.ntu.scse.control.PriceManager;
/**
 * Price UI to update price
 * 
 * @author Kailing and Zilvinas
 *
 */
public class PriceUI {
	/**
	 * Control module for price manager
	 */
	public void start() {
		PriceManager priceManager = new PriceManager();
		Scanner sc = new Scanner(System.in);
		int opt = 0;
		do {
			System.out.println("=============================");
			System.out.println("=== [Price manager menu] ===");
			System.out.println("Choose category of price type to be updated: ");
			System.out.println("[1] Age category");
			System.out.println("[2] Type of cinema");
			System.out.println("[3] Blockbuster");
			System.out.println("[4] Movie type");
			System.out.println("[0] Return");

			opt = sc.nextInt();
			switch (opt) {
			case 0:
				break;
			case 1:
				priceManager.setPriceByAgeCategory();
				break;
			case 2:
				// type menu
				priceManager.setPriceByCinemaType();
				break;
			case 3:
				priceManager.setPriceForBlockbuster();
				break;
			case 4:
				priceManager.setPriceByMovieType();
				break;
			default:
				System.out.println("No such option");
				break;
			}
		} while (opt != 0);
	}


}
