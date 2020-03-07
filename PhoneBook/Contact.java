package PhoneBook;

import java.io.Serializable;


public class Contact implements Serializable, Comparable<Contact> {

	private static final long serialVersionUID = 1L;
	public static boolean isSortByName = true; // indicator to know by which field to sort the contact list
	public static boolean isSortByPhone = true;
	public static boolean isSortByMobile = true;
	private String fName;
	private String lName;
	private String homePhone;
	private String mobile;
	
	
	
	public Contact(String firstName, String lastName, String homeNumber, String mobile) throws Exception {
		setFName(firstName);
		setLName(lastName);
		setHomePhone(homeNumber);
		setMobile(mobile);
	}
	
	public String getFirstName() {
		return (fName);
	}
	
	public String getLastName() {
		return (lName);
	}
	
	public String getFullName() {
		return fName+" "+lName;
	}
	
	public void setHomePhone(String number) {
		if(number.isEmpty()) {
			this.homePhone = ""; // contact must have 1 valid number, if the mobile will also be invalid, an exception will be thrown
	
		}
		else
			if(number.charAt(2) == '-') {
				if(number.substring(3, 10).length() == 7)
					this.homePhone = number;
			}
			else
				if(number.length() == 9)
					this.homePhone = number;
		
		}
		
		
	public void setMobile(String mobile) throws Exception {/*checking if the number that the user has entered is valid
	 														(has 10 numbers in a row or 3#+'-'+7#*/
		
		if(mobile.isEmpty()) {
			if(homePhone.isEmpty()) throw new Exception(
					"ADD/UPDATE Contact EXCEPTION: Contact must have at least ONE VALID NUMBER!");
			else
				this.mobile = mobile;
		}
		else
			if(mobile.charAt(3) == '-') {
				if(mobile.substring(4, 11).length() == 7)
					this.mobile = mobile;	
			
			}
		else
			if(mobile.length() == 10)
				this.mobile = mobile;	
			else
				if(homePhone.isEmpty())throw new Exception(
						"ADD/UPDATE Contact EXCEPTION: Contact must have at least ONE VALID NUMBER!");
		
	}

	public void setFName(String name) throws Exception {
		if(name.length() <= 2) throw new Exception(
				"ADD/UPDATE Contact EXCEPTION: First name Can Not Be less than 3 letters!");
		this.fName = name;
	}
			
	public void setLName(String name) throws Exception {
		if(name.length() <= 2) throw new Exception(
				"ADD/UPDATE Contact EXCEPTION: Last name Can Not Be less than 3 letters!");
		this.lName = name;
	}
	
	public String getPhone() {
		return homePhone;
	}
	
	public String getMobile() {
		return mobile;
	}
		
	public String getPhones() {
		return ("Home Phone: "+homePhone+", Mobile: "+mobile);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (!fName.equals(other.fName))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fName == null) ? 0 : fName.hashCode());
		result = prime * result + ((homePhone == null) ? 0 : homePhone.hashCode());
		result = prime * result + ((lName == null) ? 0 : lName.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		return result;
	}
	
	@Override
	public int compareTo(Contact c) {//comparing the contact to the other contacts in the list by the field the user has picked.
		if(isSortByName)
			return getFullName().compareTo(c.getFullName());
		else
			if(isSortByPhone)
				return getPhone().compareTo(c.getPhone());
			else
				if(isSortByMobile)
					return getMobile().compareTo(c.getMobile());
		return -1;
	}
	

}
