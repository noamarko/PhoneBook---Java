package restaurant_creator;

import java.util.ArrayList;
import java.util.Collections;

public class GenericMenu<T extends Product> implements MenuFunction<T> {

	private static ArrayList<Product> listOfMenu = new ArrayList<>();
	private static Double sumOfAll = 0.0;

	public GenericMenu() {

	}

	public static ArrayList<Product> getList() {
		return listOfMenu;
	}

	public static void get(int i) {
		System.out.println(listOfMenu.get(i));
	}

	public double sum(Number p1, Number p2) {
		return (p1.doubleValue() + p2.doubleValue());
	}

	@Override
	public void createProduct(ArrayList<T> menu, T prdct) {
		menu.add(prdct);
	}

	@Override
	public void deleteProduct(ArrayList<T> menu, int i) throws PriceException {
		menu.remove(i);
	}

	@Override
	public void add(T prdct) {
		listOfMenu.add(prdct);
	}

	@Override
	public void addAll(ArrayList<T> menu, ArrayList<T> menu2) {
		menu2.addAll(menu);

	}

	@Override
	public void remove(int i) {
		listOfMenu.remove(i);
	}

	@Override
	public void removeAll() {
		listOfMenu.clear();
	}

	@Override
	public void print(GenericMenu<Product> menu) {
		int i;
		if (listOfMenu.size() == 0) {
			System.out.println("Menu [count: 0, Total price: 0]");
			System.out.println("Result:");
			System.out.println("           The menu is empty!");
		} else {
			for (i = 0; i < listOfMenu.size(); i++) {
				sumOfAll += listOfMenu.get(i).getPrdctPrice().doubleValue();
			}
			System.out.println("Menu [count: " + i + ", Total price: " + sumOfAll + "]");
			sumOfAll = 0.0;
			System.out.println("Result: ");
			for (i = 0; i < listOfMenu.size(); i++) {
				System.out.println("           "+listOfMenu.get(i).getPrdctName() + ", " + listOfMenu.get(i).getPrdctPrice() + ", "
						+ ((Hamburger) listOfMenu.get(i)).getAddOns());
			}
		}
		System.out.println();
	}

	@Override
	public void sort(ArrayList<T> menu) {
		Collections.sort(menu);

	}

	@Override
	public int search(ArrayList<T> menu, String name, Number price) {
		sort(menu);
		int start = 0;
		int end = menu.size() - 1;
		int middle = -1;
		double smallestAmount;
		while (start <= end) {
			middle = (start + end) / 2;
			if (menu.get(middle).getPrdctName().compareTo(name) == 0) {
				if (price.doubleValue() == menu.get(middle).getPrdctPrice().doubleValue()) {
					return middle;
				} else
					while (menu.get(middle).getPrdctName().compareTo(name) == 0) {
						if (price.doubleValue() == menu.get(middle).getPrdctPrice().doubleValue())
							return middle;
						else
							if (price.doubleValue() - menu.get(middle).getPrdctPrice().doubleValue() < 0) {
								smallestAmount = Math.abs(price.doubleValue() - menu.get(middle).getPrdctPrice().doubleValue());
									middle--;
									if (middle < start)
										return start;
								if (smallestAmount > Math.abs(price.doubleValue() - menu.get(middle).getPrdctPrice().doubleValue()))
									smallestAmount = Math.abs(price.doubleValue() - menu.get(middle).getPrdctPrice().doubleValue());
								else
									return middle + 1;
						} else {
							smallestAmount = Math.abs(price.doubleValue() - menu.get(middle).getPrdctPrice().doubleValue());
							middle++;
							if (middle + 1 > end)
								return end;
							else 
								if (smallestAmount > Math.abs(price.doubleValue() - menu.get(middle).getPrdctPrice().doubleValue())) {
									smallestAmount = Math.abs(price.doubleValue() - menu.get(middle).getPrdctPrice().doubleValue());
							}
							else
								return middle - 1;
						}
					}

			} else if (name.compareTo(menu.get(middle).getPrdctName()) < 0) {
				end = middle - 1;
			} else
				start = middle + 1;
		}
		return middle;
	}

}
