package restaurant_creator;

import java.util.Scanner;

public class Owner extends Manager {
	public final int BASE = 20000;// base income for owner

	protected Owner(String name, int salary, int bonus) throws BonusException {//Constructor for owner
		super(name, salary, bonus);
		if(bonus > 10000)
			throw new BonusException("!! BONUS CAN'T BE HIGHER THAN 10,000 !!");

	}

	
	@Override
	public boolean equals(Object x) {
		if (super.equals(x))
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return "Owner: " + name + ", " + salary + ", " + bonus + ", " + BASE + ".";
	}

	public static Owner setOwner(Scanner scn) throws BonusException {
		Manager o = setManager(scn);
		o = new Owner(o.getName(), o.getSalary(), o.getBonus());
		return (Owner)o;

	}

}
