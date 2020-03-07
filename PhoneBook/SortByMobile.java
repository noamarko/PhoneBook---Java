package PhoneBook;

import java.util.Comparator;

public class SortByMobile implements Comparator<Contact>{

	@Override
	public int compare(Contact c1, Contact c2) {
		Contact.isSortByName = false;
		Contact.isSortByPhone = false;
		return  c1.compareTo(c2);
	}

}