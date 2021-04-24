package restaurant_creator;

import java.util.Scanner;

public class Manager extends Worker {

	protected int bonus;// managers bonus

	protected Manager(String name, int salary, int bonus) {//Constructor for manager
		super(name, salary);
		setBonus(bonus);
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	


	@Override
	public boolean equals(Object x) {//equals method for type manager
		boolean flag = true;
		if (((Manager)x).getClass() == this.getClass())
			if ((((Manager)x).getSalary()+((Manager)x).getBonus() == this.getSalary() + this.bonus))
					return flag;
				else
					return flag = false;
			else
				return flag = false;

	}

	@Override
	public String toString() {
		return "Manager: " + name + ", " + salary + ", " + bonus + ".";
	}

	
	public static Manager setManager(Scanner scn) {
		Worker m = setWorker(scn);
		System.out.print("Please enter bonus: ");
		int bonus = scn.nextInt();
		m = new Manager(m.getName(), m.getSalary(), bonus);
		scn.nextLine();
		return (Manager) m;
	}

	


}
