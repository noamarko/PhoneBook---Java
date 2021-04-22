package restaurant_creator;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Worker extends Person {
	protected int salary; // workers salary
	private static String pswd;
	private static String usrName;

	protected Worker(String name, int salary) {//Constructor for worker
		super();
		setName(name);
		setSalary(salary);
		setUsrName(getUsrName());
		setPswd(getPswd());
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public String getPswd() {
		
		return pswd;
	}

	public void setPswd(String pswd) {
		Worker.pswd = pswd;
	}

	public String getUsrName() {
		
		return usrName;
	}

	public void setUsrName(String usrName) {
		Worker.usrName = usrName;
	}

	@Override
	public boolean equals(Object x) {//equals method (Override) for worker
		boolean flag = true;
		if (x.getClass() == this.getClass())
			if (((Worker)x).getSalary() == this.getSalary())
				return flag;
			else
				return flag = false;
		else
			return flag = false;

	}

	@Override
	public String toString() {
		return "Worker: " + name + ", " + salary + ".";
	}

	public static Worker setWorker(Scanner scn) {
		
		System.out.print("Please enter full name: ");
		String fullName = scn.nextLine();
		
		int salary = 0;
		boolean validInput = false;
		
		while (!validInput) {
			
			System.out.print("Please enter salary amount: ");
			try {//trying to catch exceptions in the users input
				if (scn.hasNextInt()) {
					salary = scn.nextInt();
					if (salary >= 0) {//salary can't be smaller than zero
						validInput = true;
					} else
						throw new IllegalArgumentException("Salary must be at least 0!");
				} else
					throw new InputMismatchException("Salary must be an integer!");

			} catch (IllegalArgumentException e) {
				System.out.println(e);
				
			} catch (InputMismatchException ex) {
				System.out.println(ex);
				scn.next();
			}
			System.out.print("Please enter UserName: ");
			usrName = scn.next();
			System.out.print("Please enter Password: ");
			pswd =scn.next();
		}
		Worker w = new Worker(fullName, salary);
		scn.nextLine();
		return w;
	}

}
