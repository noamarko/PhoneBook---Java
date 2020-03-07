package PhoneBook;

import java.util.Iterator;

public class ContactIterator implements Iterator<Contact>{

	private ContactList list;
	
	public ContactList getList() {
		return list;
	}

	public void setList(ContactList list) {
		this.list = list;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	private int index;

	public ContactIterator(ContactList clist) {
		this.list = clist;
		this.index = -1;
	}

	@Override
	public boolean hasNext() {
		return (index + 1) < list.size();
	}

	@Override
	public Contact next() {
		if (hasNext())
			return list.get(++index);
		return null;
	}
	
	public void remove() {
		if(index >= 0 && index < list.size())
			list.remove(index);
	}

}
