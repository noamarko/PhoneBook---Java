package PhoneBook;

import java.util.Comparator;

public class SortByHomePhone implements Comparator<Contact>  {
	@Override
	public int compare(Contact c1, Contact c2) {
		Contact.isSortByName = false;
		Contact.isSortByPhone = true;
		return  c1.compareTo(c2);
	}

}
