package restaurant_creator;

import java.util.Scanner;

public class Hamburger extends Product{
	private String addOns;
	enum eType{RARE,MEDIUM,MEDIUMWELL,WELLDONE};
	
	public Hamburger(String type, String name, Number price, String whatElse) throws PriceException {
		
		setPrdctName(name);
		setPrdctPrice(price);
		type = type.toUpperCase();
		eType.valueOf(type);
		setAddOns(whatElse);
	}

	public String getAddOns() {
		return addOns;
	}

	public void setAddOns(String addOns) {
		this.addOns = addOns;
	}
	
	public static Product setHamburger(Scanner scn) throws PriceException {
		double price = 0;
		int token;
		String name;
		scn.nextLine();
		while(true) {
			System.out.println("\nPlease enter the name: ");
			name = scn.nextLine();
			System.out.println("\nIs this the correct name?(1 - Y/ 0 - N)");
			token = scn.nextInt();
			if(token == 1)
				break;
		}
		System.out.print("Please enter price: ");
		if (scn.hasNextDouble()) {
			price = scn.nextDouble();
		}
		else {
			throw new PriceException("Illegal price input exception!");
		}
		System.out.print("Please enter ingredients: ");
		String whatElse = scn.nextLine()+scn.nextLine();
		System.out.print("Please enter Degree of Doneness(RARE|MEDIUM|MEDIUMWELL|WELLDONE): ");
		String howMuch = scn.next();
		Product h = new Hamburger(howMuch, name, price, whatElse);
		System.out.println("\nHamburger created successfully!");
		return h;
	}
}
