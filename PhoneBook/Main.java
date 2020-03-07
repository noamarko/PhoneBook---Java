package PhoneBook;

import java.util.Collections;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		ContactList list = new ContactList();
		Scanner s = new Scanner(System.in);

		try {
			boolean isFinished = false;
			
			while (!isFinished) {
				System.out.println("\nCONTACT CREATOR:");
				System.out.println("1. Add/Update Contact");
				System.out.println("2. Remove Contact");
				System.out.println("3. Search Contact");
				System.out.println("4. Sort Contacts");
				System.out.println("5. Save Contacts To File");
				System.out.println("6. Load Contacts From File");
				System.out.println("7. Print All Contacts");
				System.out.println("Choose Your Option Or Press Any Key To EXIT");
				System.out.print("Your Option: ");
				char op = s.nextLine().charAt(0);
				try {
					switch (op) {
					case '1':
						add(list, s);
						break;
					case '2':
						remove(list, s);

						break;
					case '3':
						search(list, s);
						break;
					case '4':
						sort(list, s);
						break;
					case '5':
						list.saveContacts();
						break;
					case '6':
						list.loadContacts();
						break;
					case '7':
						System.out.println(list);
						break;
					default:
						isFinished = true;
						break;
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	
	
	private static void add(ContactList list, Scanner s) throws Exception {
		System.out.println("ADD/UPDATE Contact:");
		int size = list.size();
		boolean isLoaded = list.add(
				new Contact(getFirstNameFromUser(s), getLastNameFromUser(s), getPhoneFromUser(s), getMobileFromUser(s)));
		if (!isLoaded)
			System.out.println("CONTACT CAN NOT BE NULL!\n");
		else if (size != list.size())
			System.out.println("Contact Added!\n");
		else
			System.out.println("Contact Updated!\n");
	}

	
	private static void remove(ContactList list, Scanner s) throws Exception {
		System.out.println("REMOVE CONTACT:");
		System.out.println("1. By Name");
		System.out.println("2. By Home Phone");
		System.out.println("3. By Mobile");
		System.out.println("Choose Your Option Or Press Any Key To EXIT");
		System.out.print("Your Option: ");
		char op = s.nextLine().charAt(0);
		Contact c = null;
		int index = -1;
		switch (op) {
		case '1':
			c = new Contact(getFirstNameFromUser(s), getLastNameFromUser(s), "123456789", "1234567890");/* given a meaningless number so the program
			 																								can search for the contact by name.*/
			Collections.sort(list, new SortByName());
			index = Collections.binarySearch(list, c, new SortByName());
			c = list.get(index);
			break;
		case '2':
			c = new Contact("   ", "   ", getPhoneFromUser(s), ""); /* first name and last name are empty so that the contact can be
																		created and searched for by phone */
			Collections.sort(list, new SortByHomePhone());
			index = Collections.binarySearch(list, c, new SortByHomePhone());
			c = list.get(index);
			break;
		case '3':
			c = new Contact("   ", "   ", "", getMobileFromUser(s)); // same reason as explained above
			Collections.sort(list, new SortByMobile());
			index = Collections.binarySearch(list, c, new SortByMobile());
			c = list.get(index);
			break;
		default:
			break;
		}

		if (c != null) {
			System.out.println(c.getFullName()+", "+c.getPhones());
			System.out.println("Are You Sure? y/n");
			op = s.nextLine().charAt(0);
			if (op == 'y') {
				list.remove(c);
				System.out.println("Contact removed!");
			}
			else
				System.out.println("Operation Canceled!");
		} else
			System.out.println("Contact Not Found!");
		list.sort();

	}
	

	private static void search(ContactList list, Scanner s) throws Exception {
		System.out.println("SEARCH CONTACT BY NAME:");
		System.out.println("1. By Name");
		System.out.println("2. By Home Phone");
		System.out.println("3. By Mobile Phone");
		System.out.println("Choose Your Option Or Press Any Key To EXIT");
		System.out.print("Your Option: ");
		char op = s.nextLine().charAt(0);
		Contact c = null;
		int index = -1;
		switch (op) {
		case '1':
			c = new Contact(getFirstNameFromUser(s), getLastNameFromUser(s), "123456789", "1234567890"); /* given a meaningless number so the program 
																											can search for the contact by name.*/
			Collections.sort(list, new SortByName());
			index = Collections.binarySearch(list, c, new SortByName());
			c = list.get(index);
			break;
		case '2':
			c = new Contact("   ", "   ", getPhoneFromUser(s), ""); // same reason like in the REMOVE option.
			Collections.sort(list, new SortByHomePhone());
			index = Collections.binarySearch(list, c, new SortByHomePhone());
			c = list.get(index);
			break;
		case '3':
			c = new Contact("   ", "   ", "", getMobileFromUser(s)); // same reason like in the REMOVE option.
			Collections.sort(list, new SortByMobile());
			index = Collections.binarySearch(list, c, new SortByMobile());
			c = list.get(index);
			break;
		default:
			break;
		}
		if (c!=null)
			System.out.println(c.getFullName() +", "+c.getPhones());
		else System.out.println("Contact Not Found!");

	}

	private static void sort(ContactList list, Scanner s) { /*sorting the contact list by one of three parameters:
																full name, home phone number or mobile number.*/
		System.out.println("SORT CONTACTS:");
		System.out.println("1. By Name");
		System.out.println("2. By Home Phone");
		System.out.println("3. By Mobile Phone");
		System.out.println("Choose Your Option Or Press Any Key To EXIT");
		System.out.print("Your Option: ");
		char op = s.nextLine().charAt(0);
		switch (op) {
		case '1':
			list.setComparator(new SortByName());
			break;
		case '2':
			list.setComparator(new SortByHomePhone());
			break;
		case '3':
			list.setComparator(new SortByMobile());
			break;
		
		default:
			break;
		}
		list.sort();
	}
	

	private static String getFirstNameFromUser(Scanner s) {
		System.out.print("Enter Contacts First Name: ");
		return s.nextLine();
	}
	
	private static String getLastNameFromUser(Scanner s) {
		System.out.print("Enter Contacts Last Name: ");
		return s.nextLine();
	}
	
	private static String getPhoneFromUser(Scanner s) {
		System.out.print("Enter Contacts Home Phone: ");
		return s.nextLine();
	}
	
	private static String getMobileFromUser(Scanner s) {
		System.out.print("Enter Contacts Mobile Phone: ");
		return s.nextLine();
	}

	
	


}
