# Requirements Document
## 1 Introduction
This is a simple bookstore web application. Is not a large application. It can be a suitable solution for small publishing offices, witch are specialized on some particular narrow-focused literature. For example, some educational publishing house can sell its own products through this application because of it’s to expensive to have own shop and other shops can sell products with a large commission. Using this web application, customers can buy books for lower price than in any other shop. They can buy books from any place in any time.
Customer can order a book through web application and get it directly from publisher. Also customer can specify address for shipping. Customer will not pay for book in web application, but will in time of getting.

## 2 User Requirements
### 2.1 Software Interfaces
The application must be written on Java with Play Framework. It also uses SQL databases to store data permanently.
### 2.2 User Interfaces
User interface mookups are placed inside [/mockups](/documents/mockups) folder

Main Page:

![Main Page](/documents/mockups/Main%20Page.png)

About Page:

![About Page](/documents/mockups/About%20Page.png)

Catalog Page:

![Catalog Page](/documents/mockups/Catalog%20Page.png)

Book Page:

![Book Page](/documents/mockups/Book%20Page.png)

Chart Page:

![Chart Page](/documents/mockups/Chart%20Page.png)

Account Page:

![Account Page](/documents/mockups/Account%20Page.png)

Administrator Account Page:

![Administrator Account Page](/documents/mockups/Administrator%20Account%20Page.png)
### 2.3 User Characteristics
A typical user is a person who like reading or have to read some books. A user prefer to read printed books rather eBooks. Potential users are impatient: they want to buy books immediately after release and pay cheaper. User interests depends on publishing house products. It is assumed that the level of computer skills is sufficient to order a something in web shop.
### 2.4 Assumptions and Dependencies
#### Publisher business size:
When a business became larger, the application may require new functionality, improvements and other updates.
#### The quantity of users:
When quantity is growing, the application performance should to growing too. It may require new administrators to manage with orders. Also new payment ways may appear.

## 3 System Requirements
### 3.1 Functional Requirements
#### Unregistered user can:
- Explore book catalog
- View detailed information about some particular book: title, description, price, rating
- Search through book catalog
- Sign up
#### Registered user can:
- Explore book catalog
- View detailed information about some particular book: title, description, price, rating
- Search through book catalog
- Rate book
- Order for the book (with address specifying or not)
- View account information
- Edit account information
- View the list of ordered books
- Edit account settings
- Deactivate profile
#### Administrator can:
- Edit catalog categories
- Edit authors list
- Add a book
- Edit a book
- Remove a book
- Remove an user
- Accept orders from users
### 3.2 Non-Functional Requirements
#### Security
There are three groups of users: unregistered user (visitors), registered user (customers), administrators. Each group has it’s own capabilities and access privileges. It helps to avoid data destruction and application failures. Visitors can only search and view books (read some data). Customers can search, view, request books, and edit profile informations (read and edit some data). Administrators can create, read, update, and delete any application data: books, categories, authors, customers. To protect accounts from hacking and unauthorized access each account has it’s own password. The password should be at least 8 characters long and include only Latin characters and numbers.
#### Availability
User can use this application in any time from any place where Internet and a web browser available. Order processing time is various. It depends on administrator working time.
#### Localization
The application supports only the English language. But some text (for example book titles, authors name, and book description) can be written in book language.
#### Help
The application should have help pages for customers, which contain information about book searching and ordering.
