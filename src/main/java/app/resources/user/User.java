package app.resources.user;

import com.opencsv.bean.BeanVerifier;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import com.opencsv.exceptions.CsvConstraintViolationException;

import app.resources.utils.Utils;

public class User extends CsvToBean<User> implements BeanVerifier {

	@CsvBindByName(column = "first_name", required = true)
	private String firstName;
	@CsvBindByName(column = "last_name", required = true)
	private String lastName;
	@CsvBindByName(column = "company_name", required = false)
	private String companyName;
	@CsvBindByName(column = "address", required = false)
	private String address;
	@CsvBindByName(column = "city", required = false)
	private String city;
	@CsvBindByName(column = "province", required = false)
	private String province;
	@CsvBindByName(column = "postal", required = false)
	private String postal;
	@CsvBindByName(column = "phone1", required = false)
	private String phone1;
	@CsvBindByName(column = "phone2", required = false)
	private String phone2;
	@CsvBindByName(column = "email", required = true)
	private String email;
	@CsvBindByName(column = "web", required = false)
	private String web;

	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User(String lastName, String companyName, String address, String city, String province, String postal,
			String phone1, String phone2, String email, String web, String firstName) {
		super();
		this.lastName = lastName;
		this.companyName = companyName;
		this.address = address;
		this.city = city;
		this.province = province;
		this.postal = postal;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.email = email;
		this.web = web;
		this.firstName = firstName;
	}

	public User() {

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	// This method identifies any similarities between two users
	public boolean isRelated(User user) {
		// if first name contains -, split it to verify partially
		if (user.getFirstName().contains("-")) {
			String[] firstNameArr = user.getLastName().split("-");
			if ((firstNameArr[0].contains(this.lastName)) || (this.lastName.contains(firstNameArr[0]))
					|| (firstNameArr[1].contains(this.lastName)) || (this.lastName.contains(firstNameArr[1]))) {
				return true;
			} else {
				return false;
			}
			// if last name contains -, split it to verify partially
		} else if (user.getLastName().contains("-")) {
			String[] lastNameArr = user.getLastName().split("-");
			if ((lastNameArr[0].contains(this.lastName)) || (this.lastName.contains(lastNameArr[0]))
					|| (lastNameArr[1].contains(this.lastName)) || (this.lastName.contains(lastNameArr[1]))) {
				return true;
			} else {
				return false;
			}
		} else {
			if ((user.getLastName().equals(this.lastName)) || (user.getLastName().contains(this.lastName))
					|| (this.lastName.contains(user.getLastName())) || (user.getFirstName().contains(this.firstName))
					|| (this.firstName.contains(user.getFirstName()))) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public String toString() {
		return " User = [ First Name=\"" + this.firstName + "\", Last name\"" + this.lastName + "\", company name=\""
				+ this.companyName + "\", address=\"" + this.address + "\", city=\"" + this.city + "\", province=\""
				+ this.province + "\", postal=\"" + this.postal + "\", phone1=\"" + this.phone1 + "\", phone2=\""
				+ this.phone2 + "\", email=\"" + this.email + "\", web=\"" + this.web + "\"]";
	}

	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	// to determine if user is valid
	public boolean isInvalidUser() {
		Utils util = new Utils();
		boolean isInvalid = util.verifyValueIsIsNotTooLong(this.firstName)
				&& util.verifyValueIsIsNotTooLong(this.lastName) && util.verifyValueIsIsNotTooLong(this.companyName)
				&& util.verifyValueIsIsNotTooLong(this.address) && util.verifyValueIsIsNotTooLong(this.city)
				&& util.verifyValueIsIsNotTooLong(this.phone1) && util.verifyValueIsIsNotTooLong(this.phone2)
				&&util.verifyValueIsIsNotTooLong(this.email)&& util.verifyValueIsIsNotTooLong(this.postal)
				&&util.verifyValueIsIsNotTooLong(this.web);

		return !isInvalid;
	}

	@Override
	public boolean equals(Object that) {
		if (this == that)
			return true;// if both of them points the same address in memory

		if (!(that instanceof User))
			return false;

		User u = (User) that; // than we can cast it to User safely
		// verify if users have same exact data
		return this.firstName.equals(u.getFirstName()) && this.lastName.equals(u.getLastName())
				&& this.companyName.equals(u.getCompanyName()) && this.address.equals(u.getAddress())
				&& this.city.equals(u.getCity()) && this.province.equals(u.getProvince())
				&& this.postal.equals(u.getPostal()) && this.phone1.equals(u.getPhone1())
				&& this.phone2.equals(u.getPhone2()) && this.web.equals(u.getWeb());
	}

	@Override
	public boolean verifyBean(Object bean) throws CsvConstraintViolationException {
		// TODO Auto-generated method stub
		return false;
	}
}
