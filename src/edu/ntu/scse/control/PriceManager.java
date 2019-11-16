package edu.ntu.scse.control;

import java.util.Scanner;

import edu.ntu.scse.config.PriceConfig;
import edu.ntu.scse.factor.AgeCategory;
import edu.ntu.scse.factor.Blockbuster;
import edu.ntu.scse.factor.CinemaClass;
import edu.ntu.scse.factor.MovieType;

/**
 * Performs control logic operations related to price
 * 
 * @author Zilvinas
 *
 */
public class PriceManager {
	/**
	 * Price controler UI for different movie types
	 */
	public void setPriceByMovieType() {
		Scanner sc = new Scanner(System.in);
		int opt = 0;

		do {
			System.out.println("============================");
			System.out.println("Select movie type to set the price for: ");
			System.out.println("[1] Regular");
			System.out.println("[2] 3D");
			System.out.println("[0] Return");

			opt = sc.nextInt();
			double dif;
			Scanner pricer = new Scanner(System.in);
			switch (opt) {
			case 0:
				break;
			case 1:
				System.out.println("Current price setting is: " + PriceConfig.getPrice(MovieType.MovieType_REGULAR));
				System.out.println("Enter new price setting: ");
				dif = pricer.nextDouble();
				PriceConfig.setPrice(MovieType.MovieType_REGULAR, dif);
				break;
			case 2:
				System.out.println("Current price setting is: " + PriceConfig.getPrice(MovieType.MovieType_3D));
				System.out.println("Enter new price setting: ");
				dif = pricer.nextDouble();
				PriceConfig.setPrice(MovieType.MovieType_3D, dif);
				break;
			default:
				System.out.println("No such option");
				break;
			}
		} while (opt != 0);
	}

	/**
	 * UI control for setting price by Age category
	 */
	public void setPriceByAgeCategory() {
		Scanner sc = new Scanner(System.in);
		int opt = 0;

		do {
			System.out.println("============================");
			System.out.println("Choose age category for which you want to change the price");
			System.out.println("[1] Child");
			System.out.println("[2] Student");
			System.out.println("[3] Adult");
			System.out.println("[4] Senior");
			System.out.println("[0] Return");

			opt = sc.nextInt();
			double dif;
			Scanner pricer = new Scanner(System.in);
			switch (opt) {
			case 0:
				break;
			case 1:
				System.out.println("Current price setting is: " + PriceConfig.getPrice(AgeCategory.CHILD));
				System.out.println("Enter new price setting: ");
				dif = pricer.nextDouble();
				PriceConfig.setPrice(AgeCategory.CHILD, dif);
				break;
			case 2:
				System.out.println("Current price setting is: " + PriceConfig.getPrice(AgeCategory.STUDENT));
				System.out.println("Enter new price setting: ");
				dif = pricer.nextDouble();
				PriceConfig.setPrice(AgeCategory.STUDENT, dif);
				break;
			case 3:
				System.out.println("Current price setting is: " + PriceConfig.getPrice(AgeCategory.ADULT));
				System.out.println("Enter new price setting: ");
				dif = pricer.nextDouble();
				PriceConfig.setPrice(AgeCategory.ADULT, dif);
				break;
			case 4:
				System.out.println("Current price setting is: " + PriceConfig.getPrice(AgeCategory.SENIOR));
				System.out.println("Enter new price setting: ");
				dif = pricer.nextDouble();
				PriceConfig.setPrice(AgeCategory.SENIOR, dif);
				break;
			default:
				System.out.println("No such option");
				break;
			}
		} while (opt != 0);
	}

	/**
	 * price controler UI for cinema type
	 */
	public void setPriceByCinemaType() {
		Scanner sc = new Scanner(System.in);
		int opt = 0;

		do {
			System.out.println("============================");
			System.out.println("Choose cinema type category for which you want to change the price");
			System.out.println("[1] Standard");
			System.out.println("[2] Platinum");
			System.out.println("[0] Return");

			opt = sc.nextInt();
			double dif;
			Scanner pricer = new Scanner(System.in);
			switch (opt) {
			case 0:
				break;
			case 1:
				System.out.println("Current price setting is: " + PriceConfig.getPrice(CinemaClass.STANDARD));
				System.out.println("Enter new price setting: ");
				dif = pricer.nextDouble();
				PriceConfig.setPrice(CinemaClass.STANDARD, dif);
				break;
			case 2:
				System.out.println("Current price setting is: " + PriceConfig.getPrice(CinemaClass.PLATINUM));
				System.out.println("Enter new price setting: ");
				dif = pricer.nextDouble();
				PriceConfig.setPrice(CinemaClass.PLATINUM, dif);
				break;
			default:
				System.out.println("No such option");
				break;
			}
		} while (opt != 0);
	}

	/**
	 * price controler UI for blockbuster movies
	 */
	public void setPriceForBlockbuster() {
		Scanner sc = new Scanner(System.in);

		System.out.println("============================");
		System.out.println("Set price for blockbuster movies");
		System.out.println("============================");
		System.out.println("Current price setting is: " + PriceConfig.getPrice(Blockbuster.TRUE));

		System.out.println("Enter new price setting: ");
		double dif = sc.nextDouble();

		PriceConfig.setPrice(Blockbuster.TRUE, dif);
		System.out.println("Price setting for blockbuster set to " + dif);
	}
}
