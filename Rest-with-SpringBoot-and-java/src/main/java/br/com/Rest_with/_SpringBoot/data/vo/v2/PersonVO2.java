package br.com.Rest_with._SpringBoot.data.vo.v2;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class PersonVO2 implements Serializable {


    
	private static final long serialVersionUID = 1L;
	private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private Date birthDay;


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public PersonVO2() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PersonVO2 personVO2 = (PersonVO2) object;
        return Objects.equals(id, personVO2.id) && Objects.equals(firstName, personVO2.firstName) && Objects.equals(lastName, personVO2.lastName) && Objects.equals(address, personVO2.address) && Objects.equals(gender, personVO2.gender) && Objects.equals(birthDay, personVO2.birthDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, gender, birthDay);
    }
}
