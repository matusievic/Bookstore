
# Results

## Use-case testing

Id  | Title | Scenario | Expected result
--|--|--|--
Functional requirements
#1U | View books in the catalog checking | Catalog link clicked | Books are displaying
#2U | View book possibility checking | Catalog link clicked. After it, Learn more link clicked | Detailed book information is displaying
#3U | Sign up possibility checking | Sign up link clicked. Correct registration information was provided. Sign up button was clicked | Main page is displaying. Some additional menu items for a registered user is displaying.
#4U | Order book checking | Shopping cart was filled by clicking Add to Cart button in the catalog. Cart link clicked. Order link clicked. Shipping information was provided. OK button was clicked | A new order in Orders table (Account page) was added
#5U | View account information checking | Account link was clicked | Account information is displaying
#6U | Edit account information checking | Account link was clicked. Account information was changed. OK button was clicked | Account page is displaying. New information is displaying.
#7U | Deactivate account checking | Account link was clicked. Deactivate button was clicked | Main page for unregistered user is displaying. There's no possibility to log in with deactivated account email
#8U | Edit catalog categories checking | Catalog link was clicked. Edit categories button was clicked. Categories were edited | New categories information is displaying in the catalog
#9U | Add book checking | Catalog link was clicked. Add book button was clicked. Book information was provided. Save button was clicked. | New book information is displaying in the cataloged.
#10U | Edit book checking | Catalog link was clicked. A book was selected. Edit book button was clicked. Book information was edited. Save button was clicked. | New book information is displaying in the cataloged.
#11U | Remove book checking | Catalog link was clicked. A book was selected. Remove book button was clicked. | Book isn't displaying in the catalog.
#12U | Remove user checking | Users link was clicked. A user was selected. Deactivate book button was clicked. | User isn't displaying in the table.
#13U | Order acceptance checking | Orders link was clicked. An order was selected. Order id was clicked. Order information was edited. Ok button was clicked. | New order information is displaying in the table.
Non functional requirements | | | 
#14U | Security testing | Trying to CRUD books, categories, authors using non-administrator account | Cannot perform operations
#15U | Availability testing | Trying open web application in different browsers in different time | Web application is opening successfully

## Presentation layer testing

Id  | Title | Scenario | Expected Result | Actual Result | Pass/Fail indication
 --|--|--|--|--|--
#1P | Broken links checking | Browse website. Click links. | All links point to valid pages
#2P | Paginations checking | Browse website. Select different pages using pagination. | Valid pages are loading
#3P | Sign up possibility checking | Sign up link clicked. Correct registration information was provided. Sign up button was clicked | Main page is displaying. Some additional menu items for a registered user is displaying.
#4P | Accordion ckecking at help page | Go to help page. Open and close each accordion help item | Each accordion item corresponds to its description
#5P | Main page slider checking | Go to main page. Try to flipping through slider items | Flipping is working

## Business layer testing


Id  | Title | Scenario | Expected Result | Actual Result | Pass/Fail indication
 --|--|--|--|--|--
#1B | Catalog item and its book page linking testing | Go to catalog page. Select a book. Click "Lean more.." link. | Corresponding book page is displaying
#2B | Cart addition checking | Go to book catalog. Select a book. Click "Add to cart" button. | The book has successfully added to the cart
#3B | Cart calculation checking | Go to catalog. Add few books to the cart. Go to cart page. | "Total price" field value corresponds to the sum of all books in the cart
#4B | Catalog filters checking | Go to catalog. Select an author or a category. Click on corresponding link on the right panel. | Only books by selected author (category) are displaying.
#5B | The cheapest and the latest book finding checking | Go to catalog page. Find the latest book and the cheapest book in the catalog. Go to main page | Books are displaying on corresponding fields.
#6B | Incorrect user input checking | Browse website. Try to enter an incorrect data to fields. | Error messages are displaying.

# Conclusion
