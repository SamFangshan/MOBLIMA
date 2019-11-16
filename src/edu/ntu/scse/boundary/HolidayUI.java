package edu.ntu.scse.boundary;

import java.util.ArrayList;
import java.util.Scanner;

import edu.ntu.scse.control.HolidayManager;
import edu.ntu.scse.entity.Holiday;

/**
 * Holiday UI to add, update, and delete holiday
 * 
 * @author Kailing & Zilvinas
 *
 */
public class HolidayUI {
	/**
	 * List of holidays the application maintains
	 */
	private ArrayList<Holiday> holidays;

	/**
	 * Constructor of HolidayUI
	 * 
	 * @param holidays
	 */
	public HolidayUI(ArrayList<Holiday> holidays) {
		this.holidays = holidays;
	}

	/**
	 * UI interface for handling holiday manager module
	 */
	public void start() {
		HolidayManager holidayManager = new HolidayManager();
		Scanner sc = new Scanner(System.in);
		int opt = 0;

		do {
			System.out.println("\n===============================");
			System.out.println("=== [Holiday manager menu] ===");
			System.out.println("[1] Add new holiday");
			System.out.println("[2] Remove holiday");
			System.out.println("[3] List holidays");
			System.out.println("[4] Update holiday");
			System.out.println("[0] Return back to admin menu");
			opt = sc.nextInt();
			Scanner holsc = new Scanner(System.in);
			switch (opt) {
			case 0:
				System.out.println("returning to admin menu");
				break;
			case 1:
				System.out.println("Enter holiday name:");
				String name = holsc.nextLine();
				System.out.println("Enter holiday date in yyyy-MM-dd HH:mm format");
				String date = holsc.nextLine();
				holidayManager.addNewHoliday(holidays,
						new Holiday(holidays.size() + 1, name, holidayManager.StringToCalendar(date)));
				break;
			case 2:
				System.out.println("Choose holiday to be removed by: \n1) id\n2) name");
				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					System.out.println("Enter holiday id to be removed");
					int id = holsc.nextInt();
					holidayManager.removeHoliday(holidays, id);
					break;
				case 2:
					System.out.println("Enter exact holiday name to be removed");
					String holidayName = holsc.nextLine();
					holidayManager.removeHoliday(holidays, holidayName);
					break;
				}
				break;
			case 3:
				System.out.println("Listing all holidays\n=============================");
				for (Holiday hol : holidays) {
					if (hol != null) {
						System.out.println(hol.toString());
					}
				}
				break;
			case 4:
				Scanner holInp = new Scanner(System.in);
				System.out.println("Enter ID of the holiday to be updated: ");
				int id = holInp.nextInt();
				holInp.nextLine(); //read new line
				System.out.println("Enter new name for the holiday: ");
				String newName = holInp.nextLine();
				System.out.println("Enter new date for the holiday: ");
				String newDate = holInp.nextLine();
				holidayManager.updateHolidays(holidays, new Holiday(id, newName, holidayManager.StringToCalendar(newDate)));
			default:
				System.out.println("Please select valid option");
			}
		} while (opt != 0);
	}
}
