package com.dins.hw.addressbook.addressbookrestservice.controller;

import com.dins.hw.addressbook.addressbookrestservice.AddressBookRestServiceApplication;
import com.dins.hw.addressbook.addressbookrestservice.model.Contact;
import com.dins.hw.addressbook.addressbookrestservice.model.User;
import com.dins.hw.addressbook.addressbookrestservice.repository.UserRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import org.hamcrest.Matchers;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddressBookRestServiceApplication.class,
                webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AddressBookControllerTest {
    //
    @LocalServerPort
    private int serverPort;

    //
    @Autowired
    private UserRepository _userRepository;

    //Create Rest Template
    TestRestTemplate _testRestTemplate = new TestRestTemplate();

    //
    //sample data persistence to work with test unit
    //
    private List<String> _phoneNumberList = new ArrayList<>(Arrays.asList("+497722210193","+498551563300","+496628448066"));
    private Contact _sampleContact = new Contact("050" , "Sina" , "Berberich", _phoneNumberList);
    private List<Contact> _contactList = new ArrayList<>(Arrays.asList(_sampleContact));
    private User _sampleUser = new User("001" , "Erhard" , "Stickel" , _contactList);
    //
    //


    @Test
    public void createNewUserTest()
    {
        HttpEntity<User> httpEntity = new HttpEntity<>(_sampleUser);
        ResponseEntity<User> response = _testRestTemplate.exchange(
                "http://localhost:" + serverPort + "/dins/users/addNewUser/",
                HttpMethod.POST, httpEntity, User.class);

        Assert.assertTrue(response.getStatusCode().value() == 200);
    }

    @Test
    public void getAllUserTest()
    {
        this.createNewUserTest();
        HttpEntity<String> httpEntity  = new HttpEntity<String>(null,null);
        ResponseEntity<String> responseEntity = _testRestTemplate.exchange(
                "http://localhost:" + serverPort + "/dins/users/", HttpMethod.GET, httpEntity, String.class);
        Assert.assertEquals(responseEntity.getStatusCodeValue() , 200);
    }

    @Test
    public void getUserByIdTest()
    {
        this.createNewUserTest();
        ResponseEntity<User> responseEntity = _testRestTemplate.getForEntity(
                "http://localhost:" + serverPort +"/dins/users/searchById/001/" , User.class);
        Assert.assertThat(responseEntity.getStatusCode() , Matchers.equalTo(HttpStatus.OK));
        Assert.assertEquals("001" , responseEntity.getBody().getId());
        Assert.assertEquals("Erhard", responseEntity.getBody().getFirstName());
        Assert.assertEquals("Stickel" , responseEntity.getBody().getLstName());
        Assert.assertThat(_contactList,  Matchers.samePropertyValuesAs(responseEntity.getBody().getContactList()));
    }

    @Test
    public void getUserByFirstNameOrPartOfItTest()
    {
        this.createNewUserTest();
        ResponseEntity<User[]> responseEntityList = _testRestTemplate.getForEntity(
                "http://localhost:" + serverPort +"/dins/users/searchByFirstName/rha/" , User[].class);
        User[] userList = responseEntityList.getBody();
        Assert.assertThat(responseEntityList.getStatusCode() , Matchers.equalTo(HttpStatus.OK));
        Assert.assertEquals("001" , userList[0].getId());
        Assert.assertEquals("Erhard", userList[0].getFirstName());
        Assert.assertEquals("Stickel" , userList[0].getLstName());
        Assert.assertThat(_contactList,  Matchers.samePropertyValuesAs(userList[0].getContactList()));
    }

    @Test
    public void getUserByLastNameOrPartOfItTest()
    {
        this.createNewUserTest();
        ResponseEntity<User[]> responseEntityList = _testRestTemplate.getForEntity(
                "http://localhost:" + serverPort +"/dins/users/searchByLastName/ickel/" , User[].class);
        User[] userList = responseEntityList.getBody();
        Assert.assertThat(responseEntityList.getStatusCode() , Matchers.equalTo(HttpStatus.OK));
        Assert.assertEquals("001" , userList[0].getId());
        Assert.assertEquals("Erhard", userList[0].getFirstName());
        Assert.assertEquals("Stickel" , userList[0].getLstName());
        Assert.assertThat(_contactList,  Matchers.samePropertyValuesAs(userList[0].getContactList()));
    }

    @Test
    public void removeUserByIdTest()
    {
        this.createNewUserTest();
        HttpEntity<String> httpEntity  = new HttpEntity<String>(null,null);
        ResponseEntity<User> responseEntityToDelete = _testRestTemplate.exchange(
                "http://localhost:" + serverPort + "/dins/users/deleteUserById/001" ,
                HttpMethod.DELETE , httpEntity, User.class);
        Assert.assertThat(responseEntityToDelete.getStatusCode() , Matchers.equalTo(HttpStatus.OK));
        Assert.assertEquals("001" , responseEntityToDelete.getBody().getId());
    }


    //<------------------------------------------------------------------------------------------------>
    //Test for Users'contact
    //<------------------------------------------------------------------------------------------------>

    @Test
    public void getAllContactOfSelectedUser()
    {
        this.createNewUserTest();
        ResponseEntity<Contact[]> contactListResponseEntity = _testRestTemplate.getForEntity(
                "http://localhost:" + serverPort + "/dins/contacts/findByUser/001/", Contact[].class);
        Contact[] contactList = contactListResponseEntity.getBody();
        Assert.assertThat(contactListResponseEntity.getStatusCode() , Matchers.equalTo(HttpStatus.OK));
        Assert.assertEquals("050" , contactList[0].getId());
        Assert.assertEquals("Sina" , contactList[0].getFirstName());
        Assert.assertEquals("Berberich" , contactList[0].getLastName());
        Assert.assertThat(_phoneNumberList ,
                Matchers.samePropertyValuesAs(contactList[0].getPhoneNumbers()));
    }

    @Test
    public void getContactByIdOfSelectedUser()
    {
        this.createNewUserTest();
        ResponseEntity<Contact> contactResponseEntity = _testRestTemplate.getForEntity(
                "http://localhost:" + serverPort +"/dins/contacts/findById/001/050/", Contact.class);
        Assert.assertThat(contactResponseEntity.getStatusCode() , Matchers.equalTo(HttpStatus.OK));
        Assert.assertEquals("050" , contactResponseEntity.getBody().getId());
        Assert.assertEquals("Sina" , contactResponseEntity.getBody().getFirstName());
        Assert.assertEquals("Berberich" , contactResponseEntity.getBody().getLastName());
        Assert.assertThat(_phoneNumberList ,
                Matchers.samePropertyValuesAs(contactResponseEntity.getBody().getPhoneNumbers()));
    }


    @Test
    public void newContactForSelectedUser()
    {
        this.createNewUserTest();
        List<String> phoneNumberList = new ArrayList<>(Arrays.asList("+498951532361"));
        Contact _newContact = new Contact(
                "090" ,
                "Jürgen" ,
                "Klinsmann" ,
                phoneNumberList);
        HttpEntity<Contact> httpEntity = new HttpEntity<>(_newContact);
        ResponseEntity<Contact> contactResponseEntity = _testRestTemplate.exchange(
                "http://localhost:" + serverPort +"/dins/contacts/001/new/",
                HttpMethod.POST,
                httpEntity,
                Contact.class
        );
        Assert.assertThat(contactResponseEntity.getStatusCode() , Matchers.equalTo(HttpStatus.OK));
    }

    @Test
    public void deleteContactOfSelectedUserById()
    {
        this.createNewUserTest();
        HttpEntity httpEntity = new HttpEntity(null,null);
        ResponseEntity<Contact> contResponseEntity = _testRestTemplate.exchange(
                "http://localhost:" + serverPort + "/dins/contacts/delete/001/050/",
                HttpMethod.DELETE,
                httpEntity,
                Contact.class);
        Assert.assertThat(contResponseEntity.getStatusCode() , Matchers.equalTo(HttpStatus.OK));
    }

    @Test
    public void updateContactOfSelectedUserById()
    {
        this.createNewUserTest();
        this._sampleContact.setFirstName("Jürgen");
        HttpEntity<Contact> httpEntity = new HttpEntity<>(_sampleContact);
        ResponseEntity<Contact> contactResponseEntity = _testRestTemplate.exchange(
                "http://localhost:" + serverPort + "/dins/contacts/update/001/050/",
                HttpMethod.PUT,
                httpEntity,
                Contact.class);
        Assert.assertThat(contactResponseEntity.getStatusCode() , Matchers.equalTo(HttpStatus.OK));
        Assert.assertEquals("Jürgen" , contactResponseEntity.getBody().getFirstName());
    }

    @Test
    public void getContactByPhoneNumber()
    {
        ResponseEntity<Contact> contactResponseEntity = _testRestTemplate.getForEntity(
                "http://localhost:" + serverPort +"/dins/contacts/findByNumber/+496628448066/" , Contact.class);
        Assert.assertThat(contactResponseEntity.getStatusCode() , Matchers.equalTo(HttpStatus.OK));
        Assert.assertEquals("050" , contactResponseEntity.getBody().getId());
        Assert.assertEquals("Sina" , contactResponseEntity.getBody().getFirstName());
        Assert.assertEquals("Berberich" , contactResponseEntity.getBody().getLastName());
        Assert.assertThat(_phoneNumberList ,
                Matchers.samePropertyValuesAs(contactResponseEntity.getBody().getPhoneNumbers()));
    }
}
