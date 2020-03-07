package PhoneBook;

import java.util.Comparator;

public class SortByName implements Comparator<Contact> {

	@Override
	public int compare(Contact c1, Contact c2) {
		Contact.isSortByName = true;
		return  c1.compareTo(c2);
	}

}