package com.dins.hw.addressbook.addressbookrestservice.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 */
public class Contact implements Serializable {

    //Auto-generated UID
    private static final long serialVersionUID = -3002652152373656212L;

    private String              id;             //contact's id
    private String              firstName;      //contact's  first name
    private String              lastName;       //contact's last name
    private List<String>        phoneNumbers;   //list of contact's phone number


    /**
     * default empty constructor
     */
    public Contact(){}

    /**
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param phoneNumbers
     */
    public Contact(String id, String firstName, String lastName, List<String> phoneNumbers) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumbers = phoneNumbers;
    }

    /**
     *
     * @return a String as contact's id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id set a String as contact's id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return a String as contact's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName set a String as contact's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return a String as contact's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName set a String as contact's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return a list of String as contact's phone number
     */
    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    /**
     *
     * @param phoneNumbers set a list of String as contact's phone number
     */
    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    /**
     * specific implementation of method Object.equals(Object o)
     * @param o
     * @return true if two objects are equal by value
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;
        Contact contact = (Contact) o;
        return Objects.equals(this.firstName , contact.firstName)
                &&
                Objects.equals(this.lastName , contact.lastName)
                &&
                this.phoneNumbers.containsAll(contact.getPhoneNumbers())
                &&
                contact.getPhoneNumbers().containsAll(this.phoneNumbers);
    }

    /**
     * specific implementation of Object.hashCode()
     * @return
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(firstName , lastName , phoneNumbers );
    }

}
