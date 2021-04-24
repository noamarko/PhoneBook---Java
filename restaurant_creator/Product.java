package restaurant_creator;

import java.util.ArrayList;

public class Product implements ProductFunction{
	private  String prdctName;
	private  Number prdctPrice;
	static ArrayList<Product> prdctMenu = new ArrayList<>();
	
	
	protected Product(String name, Number number) throws PriceException {
		setPrdctName(name);
		setPrdctPrice(number);
	}

	protected Product() {
		
	}
	
	public static void addPrdct(Product p) {
		prdctMenu.add(p);
	}
	
	public ArrayList<Product> getPrdctList() {
		return prdctMenu;
	}
	
	protected String getPrdctName() {
		return prdctName;
	}
	
	protected void setPrdctName(String prdctName) {
		this.prdctName = prdctName;
	}

	protected void setPrdctPrice(Number prdctPrice) {
		this.prdctPrice = prdctPrice;
	}

	public static ArrayList<Product> getList(ArrayList<Product> p) {
		return p;
	}

	protected Number getPrdctPrice() {
		return prdctPrice;
	}
	
	@Override
	public int compareTo(Product prdct) {
		if(this.getPrdctName().compareTo(prdct.prdctName)>0) {
			return 1;
		}
		else
			if(this.prdctName.compareTo(prdct.prdctName)<0) {
			return -1;
			}
		else
			if(this.prdctPrice.doubleValue()>prdct.prdctPrice.doubleValue()) {
				return 1;
			}
			else if(this.prdctPrice.doubleValue()<prdct.prdctPrice.doubleValue()) {
				return -1;
			}
			else 
				return 0;
		
	}


	@Override
	public Object clone(Product pdt) throws PriceException {
		Product p = new Product(pdt.getPrdctName(), pdt.getPrdctPrice());
		return p;
	}
	
	
	public void printList(ArrayList<Product> p) {
		if(p.size()==0) {
			System.out.println("No products to show!");
		}
		else {
			for (int i = 0; i < p.size(); i++) {
				System.out.println(p.get(i).getPrdctName()+", "+p.get(i).getPrdctPrice()+", "+((Hamburger) p.get(i)).getAddOns());
			}
		}
		System.out.println();
	}
	
}

