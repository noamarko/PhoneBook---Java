package PhoneBook;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;


public class ContactList extends LinkedList<Contact> implements Iterable<Contact> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Comparator<Contact> comparator;

	public ContactList() {
		super();
		this.comparator = new SortByName();
	}

	public boolean add(Contact c) { // adding the contact into list
		if (c == null)
			return false;
		Collections.sort(this, new SortByName());
		int index = Collections.binarySearch(this, c, new SortByName());
		if (index >= 0) // if the index exist, i.e there is already a contact with the same name
			super.set(index, c); // update the contacts info
		else
			super.add(Math.abs(index + 1), c); // if no contact with this name was found
		sort();
		return true;
	}

	public void sort() {
		Collections.sort(this, comparator);
	}

	public Comparator<Contact> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<Contact> comparator) {
		this.comparator = comparator;
	}

	public Contact remove(int index) {
		if (index >= 0 && index < size())
			return super.remove(index);
		return null;
	}

	public Contact get(int index) {
		if (index >= 0 && index<size())
			return super.get(index);
		return null;
	}

	public void saveContacts() {
		try (ObjectOutputStream oOut = 
				new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("contacts.obj")))) {
			oOut.writeInt(size());
			for (int i=0; i<size(); i++)
				oOut.writeObject(get(i));
			System.out.println(size() + " Contacts Saved!\n");
			oOut.close();
		} catch (FileNotFoundException e) {
			System.out.println("Save Exception: File contacts.obj Not Found!");
		} catch (IOException e) {
			System.out.println("Save Exception: " + e.getMessage());
		}
		
		
	}

	public void loadContacts() {
		try (ObjectInputStream oIn =
				new ObjectInputStream(	new BufferedInputStream(new FileInputStream("contacts.obj")))) {
			int size = size();
			int count = oIn.readInt();
			int added = 0;
			int updated = 0;
			int err = 0;
			boolean isLoaded = false;
			while (count > (added+updated+err) ){
				isLoaded = add((Contact) oIn.readObject());
				if(isLoaded) {
					if(size==size())
						updated++;
					else {
						added++;
						size++;	
					}
				}else
					err++;
			}
			if(added+updated>0)
				System.out.printf(
						"%d Contacts Loaded! Added: %d Updated: %d\n", (added+updated), added, updated);
			else
				System.out.println("File Is Empty!");
			oIn.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Load Exception: File contacts.obj Not Found!");
		} catch (IOException e) {
			System.out.println("Load Exception: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Load Exception: File contacts.obj Does not contain contacts data!");
		}
	}

	@Override
	public String toString() {
		if (size() > 0) {
			String str = "Contacts:\n";
			String ch = "";
			for (int i=0; i<size(); i++) {
				if (comparator instanceof SortByName && !ch.equals(get(i).getFirstName().substring(0, 1))) {
					ch = get(i).getFirstName().substring(0, 1);
					str += ch.toUpperCase() + ":\n";
				}
				str += get(i).getFirstName() + ",   " + get(i).getLastName()+",   "+get(i).getPhones() +".\n";
			}
			str += "\nNumber Of Contacts: " + size() + "\n";
			return str;
		}
		return "List Is Empty.\n";
	}

	@Override
	public Iterator<Contact> iterator() {
		return new ContactIterator(this);
	}

}