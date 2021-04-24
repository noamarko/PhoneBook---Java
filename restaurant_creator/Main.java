package restaurant_creator;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static ArrayList<Worker> listOfAll = new ArrayList<>();

	public static void main(String[] args) throws BonusException, PriceException {
		Scanner scn = new Scanner(System.in);
		try {
			System.out.println();
			boolean flag = true;
			int token = 0;
			while (flag) {
				System.out.println("\nPlease choose your preffered action:\n");
				System.out.print("1. Add employee \n"
								+ "2. Get cost by type of emplpyee \n"
								+ "3. Search for duplicate emplyee in the list\n"
								+ "4. Search employee\n"
								+ "5. Update employee\n"
								+ "6. Print employee list\n"
								+ "7. Create Menu\n"
								+ "8. Delete Employee\n"
								+ "*Note: Most of the functions cannot be done without adding AT LEAST one emplyee to the system*\n"
								+ "\n"
								+ "Your choice: ");
				token = scn.nextInt();
				switch(token) {
					case 1:{
						listOfAll = insertIntoList(scn);
						break;
					}
					case 2:{
						int sum = costByType(scn);
						if (sum == -1 || sum == 0)
							flag = false;
						else
							System.out.println("\nCost = " + sum);
						break;
					}
					case 3:{
						while (true) {
							if(listOfAll.size() > 0) {
								String ans = compareBetweenWorker(scn);
								System.out.println(ans);
								if (ans.contains("ends"))
									break;
							}
							else {
								System.out.println("\nThe list contains 0 employees. Cannot compare.\n");
								break;
							}
								
						}
					}
					case 4:{
						int index = SearchWorker(scn);
						if(index != -1)
							System.out.println("\nFound "+listOfAll.get(index).getClass().getSimpleName()+": Name - "+listOfAll.get(index).getName()+", "
									+ "Salary - "+listOfAll.get(index).getSalary()+", UserName -"+listOfAll.get(index).getUsrName()+"\n");
						
						else
							System.out.println("Employee not found.\n");
						break;
					}
					case 5:{
						updateWorker(scn);
						break;
					}
					case 6:{
						printList();
						break;
					}
					case 7:{
						if(listOfAll.size() > 0) 
							logIn(scn);
						else
							System.out.println("\nThe DataBase has 0 emplyees. Cannot enter Menu.\n");
						break;
					}
					case 8:{
						deleteEmployee(scn);
						break;
					}
				}
			}
		} catch (BonusException ex) {
			System.out.println(ex);
			System.out.println("\nProgram ends now...");
			return;
		}	
		scn.close();
	}

	private static void deleteEmployee(Scanner scn) {
		int index=0;
		String answer;
		while(index != -1) {
			index = SearchWorker(scn);
			System.out.println("Are you sure you want to delete user(Y to delete, N not to): "+listOfAll.get(index).getName()+", UserName:"
					+ ""+listOfAll.get(index).getUsrName());
			answer = scn.next();
			if(answer.contains("Y")||answer.contains("y")) {
				listOfAll.remove(index);
				System.out.println("Employee deleted sucessfully.\n");
				break;
			}
		}
	}

	private static void updateWorker(Scanner scn) throws BonusException {
		scn.nextLine();
		boolean flag = true;
		int index;
		String role ="";
		int bonus = 0;
		int salary=0;
		while(flag) {
			index = SearchWorker(scn);
			if(index != -1) {
				System.out.println("What role would you wish to put him/her in?\n"
						+ "1. W for Worker\n"
						+ "2. M for Manager\n"
						+ "3. O for Owner\n"
						+ "f/F to finish.\nS");
				role = scn.next();
				System.out.println("Please enter amount of new salary: ");
				if(scn.hasNextInt())
					salary = scn.nextInt();
				else
					System.out.println("Bad input, salary changed to 0.");
				System.out.println("Enter amount of bonus you'd wish to add: ");
				if(scn.hasNextInt())
					bonus = scn.nextInt();
				else
					System.out.println("Bad input, bonus changed to 0.");
				if(role == "W") {
					Worker w = new Worker(listOfAll.get(index).getName(), salary);
					listOfAll.add(w);
					listOfAll.remove(listOfAll.get(index));
				}
				else
					if(role == "M") {
						Manager m = new Manager(listOfAll.get(index).getName(), salary, bonus);
						listOfAll.add(m);
						listOfAll.remove(listOfAll.get(index));
					}
					else
						if(role == "O"){
							Owner o = new Owner(listOfAll.get(index).getName(), salary, bonus);
							listOfAll.add(o);
							listOfAll.remove(listOfAll.get(index));
						}
						else
							flag = false;
				
			}
		}
		
		
	}

	private static void logIn(Scanner scn) throws PriceException {
		System.out.println("*** System Log-in option ***\n");
		scn.nextLine();
		System.out.print("Please enter UserName: ");
		String usrName = scn.nextLine();
		System.out.print("Please enter password: ");
		String pswd = scn.next();
		for (int i = 0; i < listOfAll.size(); i++) {
			if (usrName.equals(listOfAll.get(i).getUsrName()) && pswd.equals(listOfAll.get(i).getPswd())) {
				createMenu(scn);
			}
		}

	}

	public static void createMenu(Scanner scn) throws PriceException {
		String choice;
		String name;
		double price = 0;
		int index;
		String str;
		boolean value = true;
		GenericMenu<Product> productList2 = new GenericMenu<>();
		ArrayList<Product> p = new ArrayList<>();
		Product p2 = new Product();

		while (value != false) {
			choice = printMsg(scn);
			switch (choice) {
			case "1":
				try {
					System.out.println("Your choice: " + choice + " - Create product");
					p.add((Hamburger.setHamburger(scn)));
					System.out.println();
					break;
				} catch (PriceException ex) {
					System.out.println(ex + "\n Program ends now....");
					value = false;
					break;
				}
			case "2":
				System.out.println("Your choice: " + choice + " - Delete product");
				index = findProduct(p, productList2, scn);
				if (index == -1) {
					break;
				} else {
					productList2.deleteProduct(p, index);
					System.out.println("Hamburger deleted successfully!");
					System.out.println();
					break;
				}
			case "3":
				System.out.println("Your choice: " + choice + " - Add product to the menu");
				index = findProduct(p, productList2, scn);
				if (index == -1)
					break;
				else {
					productList2.add(p.get(index));
					System.out.println("Hamburger added successfully!");
					System.out.println();
					break;
				}
			case "4":
				System.out.println("Your choice: " + choice + " - Remove product from the menu");
				index = findProduct(GenericMenu.getList(), productList2, scn);
				if (index == -1) {
					break;
				} else {
					productList2.remove(index);
					System.out.println("Hamburger removed successfully!");
					System.out.println();
					break;
				}

			case "5":
				System.out.println("Your choice: " + choice + " - Remove all products from the menu");
				System.out.println("Are you sure? (Y/N)");
				str = scn.next();
				if (str.contains("Y")||str.contains("y")) {
					productList2.removeAll();
					System.out.println("All products removed from the menu!");
				}
				System.out.println();
				break;
			case "6":
				System.out.println("\nYour choice: " + choice + " - Search hamburger by name and price\n");
				System.out.print("Please enter name: ");
				name = scn.nextLine() + scn.next();
				System.out.print("Please enter price: ");
				if(scn.hasNextDouble()) {
					price = scn.nextDouble();
				}
				else
					price = 0;
				System.out.println("Result:");
				index = productList2.search(p, name, price);
				System.out.println(p.get(index).getPrdctName() + ", " + p.get(index).getPrdctPrice() + ", "
						+ ((Hamburger) p.get(index)).getAddOns());
				System.out.println();
				break;
			case "7":
				System.out.println("\nYour choice: " + choice + " - Print all products\n");
				p2.printList(p);
				break;
			case "8":
				System.out.println("\nYour choice: " + choice + " - Print menu\n");
				productList2.print(productList2);
				break;
			default:
				value = false;
				System.out.println("Program ends now....");
				break;
			}
		}
	}


	public static String printMsg(Scanner scn) {
		System.out.println("-----------------------------------");
		System.out.printf("Welcome to " + "%s" + " Menu Creator%n", "One-Click Hamburger");
		System.out.println("-----------------------------------");
		System.out.println("-----------------------------------");
		System.out.printf("%d. Create New Hamburger%n%d. Delete Hamburger%n%d. Add Hamburger to menu"
				+ "%n%d. Remove Hamburger from menu%n%d. Remove all Hamburgers from menu%n%d. Search Hamburger by name and price"
				+ "%n%d. Print all products%n%d. Print menu\n", 1, 2, 3, 4, 5, 6, 7, 8);
		System.out.print("Choose your option or any other key to EXIT: ");
		String choice = scn.next();
		return choice;
	}

	public static int findProduct(ArrayList<Product> p, GenericMenu<Product> pr, Scanner scn) throws PriceException {
		double price;
		int token;
		String name;
		scn.nextLine();
		while(true) {
			System.out.println("\nPlease enter the name: ");
			name = scn.nextLine();
			System.out.println("\nIs this the correct name?(1 - Y / 0 - N)");
			token = scn.nextInt();
			if(token == 1)
				break;
		}
		System.out.print("Please enter price: ");
		if(scn.hasNextDouble()) {
			price = scn.nextDouble();
			if(price<0)
				price = 0;
		}
		else
			price = 0;
		System.out.println("Result: ");
		int index = pr.search(p, name, price);
		System.out.println(p.get(index).getPrdctName() + ", " + p.get(index).getPrdctPrice() + ", "
				+ ((Hamburger) p.get(index)).getAddOns());
		System.out.print("Are you sure? (Y/N)");
		String str = scn.next();
		if (str.contains("Y") || str.contains("y")) {
			return index;
		} else
			return -1;
	}
	

	public static ArrayList<Worker> insertIntoList(Scanner scn) throws BonusException {
		ArrayList<Worker> newList = new ArrayList<>();
		boolean flag = true;
		String ans;
		while (flag) {// using switch case to know what type of person the user wants to create
			System.out.print("\nEnter [W/w], [M/m], [O/o]. [F/f] to finish: ");
			ans = scn.next();
			scn.nextLine();
			switch (ans) {
			case "M":
			case "m":
				newList.add((Manager) Manager.setManager(scn));
				break;
			case "O":
			case "o":
				newList.add((Owner) Owner.setOwner(scn));
				break;
			case "W":
			case "w":

				newList.add((Worker) Worker.setWorker(scn));
				break;
			case "f":
			case "F":
				flag = false;
				break;
			}
		}
		return newList;

	}
	

	public static void printList() {// printing the list
		System.out.println();
		for (int i = 0; i < listOfAll.size(); i++) {
			System.out.println("\n"+listOfAll.get(i));
		}
	}
	

	public static int SearchWorker(Scanner scn) {
		String search = "";
		scn.nextLine();
		System.out.print("\nPlease enter the employee name: ");
		search = scn.nextLine();
		for (int i = 0; i < listOfAll.size(); i++) {
			if (search.equals(listOfAll.get(i).getName()))
				return i;
		}
		return -1;
		

	}
	

	public static int costByType(Scanner scn) { // returning the payment of a specific character
		int sum = 0;
		boolean flag = true;
		String ans;

		while (flag) {
			System.out.print(
					"Please type a character to get total cost by type(\n" + "[W/w], [M/m], [O/o]. [F/f] to finish): ");
			ans = scn.next();
			switch (ans) {
			case "M":
			case "m":
				sum = sumUpM();
				return sum;
			case "O":
			case "o":
				sum = sumUoO();
				return sum;

			case "W":
			case "w":
				sum = sumUpW();
				return sum;

			case "f":
			case "F":
				return sum = -1;

			}
		}
		return sum;
	}
	

	private static int sumUoO() {
		int sum = 0;

		for (int i = 0; i < listOfAll.size(); i++) {
			if (listOfAll.get(i) instanceof Owner) {// summing up the owners total payment
				sum += listOfAll.get(i).getSalary() + ((Owner) listOfAll.get(i)).getBonus()
						+ ((Owner) listOfAll.get(i)).BASE;
			}
		}

		return sum;

	}
	

	private static int sumUpM() {

		int sum = 0;

		for (int i = 0; i < listOfAll.size(); i++) {
			if (listOfAll.get(i) instanceof Manager && !(listOfAll.get(i) instanceof Owner)) {// checking if the index
																								// is from type manager
				sum += listOfAll.get(i).getSalary() + ((Manager) listOfAll.get(i)).getBonus();
			}
		}

		return sum;
	}
	

	private static int sumUpW() {
		int sum = 0;
		for (int i = 0; i < listOfAll.size(); i++) {
			if (listOfAll.get(i) instanceof Worker && !(listOfAll.get(i) instanceof Manager)
					&& !(listOfAll.get(i) instanceof Owner)) {// checking if the index from type worker, if it is sum up
																// it's salary
				sum += listOfAll.get(i).getSalary();
			}
		}

		return sum;
	}
	

	private static String compareBetweenWorker(Scanner scn) {

		System.out.print("Please enter an index in the list(-1 to finish): ");
		int index = scn.nextInt();
		if (index == -1) {
			return "Program ends now...";
		} else {
			System.out.print("Please enter another index: ");
			int index2 = scn.nextInt();
			if (listOfAll.get(index).equals(listOfAll.get(index2))) {// checking if the first index equals to the second
																		// index
				return "Both are equal!";
			} else
				return "Not equal";
		}
	}

}
