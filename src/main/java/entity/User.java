package entity;

import java.time.LocalDate;
import java.util.Objects;

public class User {
	
	private String passportNumber;
	private LocalDate passportTerm;
	private String licenceNumber;
	private LocalDate licenceTerm;
	private String phoneNumber;
	private RoleEnum role;
	private int id;
	private String email;
	private String name;
	private String lastname;
	private LocalDate birthdate;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public LocalDate getPassportTerm() {
		return passportTerm;
	}

	public void setPassportTerm(LocalDate passportTerm) {
		this.passportTerm = passportTerm;
	}

	public String getLicenceNumber() {
		return licenceNumber;
	}

	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}

	public LocalDate getLicenceTerm() {
		return licenceTerm;
	}

	public void setLicenceTerm(LocalDate licenceTerm) {
		this.licenceTerm = licenceTerm;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public RoleEnum getRole() {
		return role;
	}

	public void setRole(RoleEnum role) {
		this.role = role;
	}
	
	public void setRole(String role) {
		this.role = RoleEnum.valueOf(role.toUpperCase());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + ", lastname=" + lastname + ", birthdate="
				+ birthdate + ", passportNumber=" + passportNumber + ", passportTerm=" + passportTerm
				+ ", licenceNumber=" + licenceNumber + ", licenceTerm=" + licenceTerm + ", phoneNumber=" + phoneNumber
				+ ", role=" + role + "]";
	}
	
	
	
}
