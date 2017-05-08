package Challenge;

import java.util.Scanner;

public class FridayLunchRaffle {
	public static void main(String[] args) {
		SpinningCage spinCage = new SpinningCage();
		Scanner scan = new Scanner(System.in);
		while(true) {
		try {
			System.out.println("Month-End Friday Lunch Raffle - ");
			System.out.println("1. Add New Members, enter 1");
			System.out.println("2. Lunchtime!!, enter 2");		
			
			int input = scan.nextInt();
			switch(input) {
			case 1:
				System.out.println("Enter new members name");
				String name = scan.next();
				spinCage.addNewMembers(name);
				break;
			case 2:
				spinCage.fridayLunch();
				break;
			default:
				System.out.println("Not a valid input, please try again");
			}
		} catch (Exception e) {
			scan.next();
			System.out.println("Invalid Input, please try again!");
			continue;
		}
		}
	}
}
