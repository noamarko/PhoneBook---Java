package restaurant_creator;

public interface ProductFunction extends Comparable<Product>, Cloneable {

	int compareTo(Product prdct);
	
	Object clone(Product pdt) throws PriceException;
		
}
