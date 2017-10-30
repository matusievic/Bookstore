# Use Cases Descriptions



## Visitor Use Cases

### View catalog
1. Go to the bookstore website.
2. Click Catalog link in the top of the website

### View book information

.. [View catalog](#view-catalog)  

3. Select a desired book  
4. Click more... link 

### Search book

.. [View catalog](#view-catalog)  

3. Enter a request into search  
4. Click Search button  

### Sign up
1. Go to the bookstore website
2. Click Sign up button at the top of the page
3. Enter correct information into fields
4. Accept Terms of Service
5. Click Sign up
6. If an error has occurred, go to Step 3



## Customer Use Cases
In addition to Visitor use cases it also includes:

### Log in
1. Go to the bookstore website
2. Click Log in link in the top of the page
3. Enter valid email and password
4. Click log
5. If an error has occurred, go to Step 3

### Rate book

.. [Log in](#log-in)  
.. [View book information](#view-book-information)

10. Click into desired star

### Order book

.. [Log in](#log-in)  
.. [View book information](#view-book-information)

10. Click to chart link on the page
11. Click Chart link on the top of the page
12. Check a chart list
13. If it necessary, remove some book from the list by Clicking remove link
14. If it necessary, add a new book by Step 4 repeating
15. Click Order button
16. Provide information for delivering
17. Click Accept button

### View account information

.. [Log in](#log-in)

6. Click Account link in the top of the page

### Edit account information

.. [View account information](#view-account-information)

7. Change information
8. Click Save
9. If an error has occurred, go to Step 7

### Deactivate account

.. [View account information](#view-account-information)

7. Click Deactivate account button at the top of the page
8. Confirm it

### Log out

.. [Log in](#log-in)

6. Click Log out button at the top of the page


## Administrator Use Cases
In addition to Visitor use cases and some Customer use cases it also includes:

### Accept order

.. [Log in](#log-in)

6. Click Orders link in the top of the page
7. Select an order
8. Click Accept
9. Enter required information
10. Click OK

### CRUD book

.. [Log in](#log-in)  
.. [View catalog](#view_catalog)

- Event flow for creating
8. Click Create button
9. Enter information into fields
10. Click OK button
11. If an error has occurred, go to Step 9
- Event flow for reading

.. [View book information](#view-book-information)  

- Event flow for updating
8. Find a desired book
9. Click Edit button
10. Edit information
11. Click OK
12. If an error has occurred, go to Step 10
- Event flow for deleting
8. Find a desired book
9. Click Delete button
10. Confirm it

### CRUD catalog category

.. [Log in](#log-in)  
.. [View catalog](#view_catalog)

- Event flow for creating
8. Click Create button below category list
9. Enter information into fields
10. Click OK button
11. If an error has occurred, go to Step 9
- Event flow for reading

.. [View catalog](#view-catalog)

- Event flow for updating
8. Select a desired book category in the list
9. Click Edit button
10. Edit information
11. Click OK
12. If an error has occurred, go to Step 10
- Event flow for deleting
8. Select a desired book category in the list
9. Click Delete button below category list
10. Confirm it

### CRUD author

.. [Log in](#log-in)  
.. [View catalog](#view_catalog)

- Event flow for creating
8. Click Create button below author list
9. Enter information into fields
10. Click OK button
11. If an error has occurred, go to Step 9
- Event flow for reading
- Event flow for updating
8. Select a desired author in the list
9. Click Edit button
10. Edit information
11. Click OK
12. If an error has occurred, go to Step 10
- Event flow for deleting
8. Select a desired author in the list
9. Click Delete button below category list
10. Confirm it
