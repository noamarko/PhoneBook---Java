package restaurant_creator;

import java.util.ArrayList;

public interface MenuFunction<T> {
	
	void createProduct(ArrayList<T>  menu, T prdct);
	
	void deleteProduct(ArrayList<T> menu, int i) throws PriceException;
	
	void add(T prdct);
	
	void addAll(ArrayList<T> menu, ArrayList<T>  menu2);
	
	void remove(int i);
	
	void removeAll();
	
	void print(GenericMenu<Product> menu);
	
	void sort(ArrayList<T>  menu);
	
	int search(ArrayList<T> menu, String name, Number price);
}
