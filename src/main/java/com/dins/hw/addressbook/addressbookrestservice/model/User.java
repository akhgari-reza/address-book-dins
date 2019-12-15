package com.dins.hw.addressbook.addressbookrestservice.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 */
public class User implements Serializable {

    //Auto-generated UID
    private static final long serialVersionUID = -1138413249353598078L;

    private String                          id;             //user's id
    private String                          firstName;      //user's first name
    private String                          lastName;       //user's last name
    private List<Contact>                   contactList;    //a list of user's contact

    /**
     * Default constructor
     */
    public User() {}

    /**
     *
     * @param id
     * @param firstName
     * @param lstName
     * @param contactList
     */
    public User(String id, String firstName, String lstName, List<Contact> contactList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lstName;
        this.contactList = contactList;
    }

    /**
     *
     * @return a String as user's id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id set a String as user's id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return a String as user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName set a String as user's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return a String as user's last name
     */
    public String getLstName() {
        return lastName;
    }

    /**
     *
     * @param lstName set a String as user's last name
     */
    public void setLstName(String lstName) {
        this.lastName = lstName;
    }

    /**
     *
     * @return a list of @{@link Contact} as user's contacts
     */
    public List<Contact> getContactList() {
        return contactList;
    }

    /**
     *
     * @param contactList set a list of @{@link Contact} as user's contacts
     */
    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    /**
     * Specific implementation of method Object.equals(Object o)
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;
        User user = (User) o;
        return Objects.equals(this.firstName , user.firstName)
                &&
                Objects.equals(this.lastName , user.lastName)
                &&
                this.contactList.containsAll(user.contactList)
                &&
                user.getContactList().containsAll(this.contactList);
    }

    /**
     * Specific implementation of Object.hashCode()
     * @return
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(firstName , lastName , contactList);
    }

}
