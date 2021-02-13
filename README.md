# Test
1. Write a program that passes specified requirements;
2. Write unit tests for the written code;
3. The following technologies should be used: Java (8 versions or higher), Spring Boot, JUnit. Databases should not be used
4. Must be provided: the source code of the program, unit tests, instructions for starting the program, examples of calling REST methods of the program.
5. It is good to pay special attention to the REST specification and organization of REST methods.
 
## Basic requirements:
It is necessary to write the server-side application (without UI) for working with users and their adress book.
The program should provide a REST API for:
* receiving a list of all users (phone book owners)
* create, receive (by id), delete, edit user
* create, receive (by id), delete, edit entries in the phone book
* receiving a list of all entries in the user's phone book
* search for users by name (or part of the name)
* search for contacts by phone number

--------------------------------------------------

Build :   `mvn clean package`

---------------------



# REST Results:
* Get the list of all users: 

`(GET) http://localhost:8080/dins/users/`

* Find user by id:

`(GET) http://localhost:8080/dins/users/searchById/{id}/`

* Find user by the first name or part of it:

`(GET) http://localhost:8080/dins/users/searchByFirstName/{firstName}/`

* Find user by the last name or part of it:

`(GET) http://localhost:8080/dins/users/searchByLastName/{lastName}/`

* Add a new user to the list:

`(POST) http://localhost:8080/dins/users/addNewUser/`

* Remove a user from the list:

`(DELETE) http://localhost:8080/dins/users/deleteUserById/{id}`

* Get all contacts in the address book of a selected user by id:

`(GET) http://localhost:8080/dins/contacts/findByUser/{userId}/`

* Find a specific contact in the address book of a selected user by id:

`(GET) http://localhost:8080/dins/contacts/findById/{userId}/{contactId}/`

* Add a new contact to the address book of a selected user by id:

`(POST) http://localhost:8080/dins/contacts/{userId}/new/`

* Remove a contact from the address book of a selected user by id:

`(DELETE) http://localhost:8080/dins/contacts/delete/{userId}/{contactId}/`

* Update a contact in the address book of a selected user by id:

`(UPDATE) http://localhost:8080/dins/contacts/update/{userId}/{contactId}/`

* Find a specific contact by phone number:

`(GET) http://localhost:8080/dins/contacts/findByNumber/{phoneNumber}/`

