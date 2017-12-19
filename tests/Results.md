
# Results


## Use-case testing


Id  | Title | Scenario | Expected Result | Actual Result | Pass/Fail indication
--|--|--|--|--|--
Functional requirements
#1U | View books in the catalog checking | Catalog link clicked. | Books are displaying. | Books are displaying | Pass
#2U | View book possibility checking | Catalog link clicked. After it, Learn more link clicked. | Detailed book information is displaying. | Book information is displaying | Pass
#3U | Sign up possibility checking | Sign up link clicked. Correct registration information was provided. Sign up button was clicked. | Main page is displaying. Some additional menu items for a registered user is displaying. | Cart and Account link are dispalying | Pass
#4U | Order book checking | Shopping cart was filled by clicking Add to Cart button in the catalog. Cart link clicked. Order link clicked. Shipping information was provided. OK button was clicked. | A new order in Orders table (Account page) was added | A new order is dispalying on Orders table | Pass
#5U | View account information checking | Account link was clicked. | Account information is displaying. | Account information is displaying | Order
#6U | Edit account information checking | Account link was clicked. Account information was changed. OK button was clicked | Account page is displaying. New information is displaying. | Information was updated | Pass
#7U | Deactivate account checking | Account link was clicked. Deactivate button was clicked | Main page for unregistered user is displaying. There's no possibility to log in with deactivated account email. | Cannot to log in after deactivation | Pass
#8U | Edit catalog categories checking | Catalog link was clicked. Edit categories button was clicked. Categories were edited | New categories information is displaying in the catalog. | Category inforamtion was updated | Pass
#9U | Add book checking | Catalog link was clicked. Add book button was clicked. Book information was provided. Save button was clicked. | New book information is displaying in the catalog. | Book was added | Pass
#10U | Edit book checking | Catalog link was clicked. A book was selected. Edit book button was clicked. Book information was edited. Save button was clicked. | New book information is displaying in the catalog. | Information was updated | Pass
#11U | Remove book checking | Catalog link was clicked. A book was selected. Remove book button was clicked. | Book isn't displaying in the catalog. | Book isn't dispalying | Pass
#12U | Remove user checking | Users link was clicked. A user was selected. Deactivate book button was clicked. | User isn't displaying in the table. | User isn't dispalying in the table. | Pass
#13U | Order acceptance checking | Orders link was clicked. An order was selected. Order id was clicked. Order information was edited. Ok button was clicked. | New order information is displaying in the table. | Updated information is displaying in the table. | Pass
Non functional requirements
#14U | Security testing | Trying to CRUD books, categories, authors using non-administrator account | Cannot perform operations | Buttons for CRUD opeations in's displaying during non-administrator account session | Pass
#15U | Availability testing | Trying open web application in different browsers in different time | Web application is opening successfully | Web application is working correctly with all actuals browsers | Pass


## Presentation layer testing

Id  | Title | Scenario | Expected Result | Actual Result | Pass/Fail indication
--|--|--|--|--|--
#1P | Broken links checking | Browse website. Click links. | All links point to valid pages | The is no broken links | Pass
#2P | Paginations checking | Browse website. Select different pages using pagination. | Valid pages are loading | Pagination is working | Pass
#3P | Accordion ckecking at help page | Go to help page. Open and close each accordion help item | Each accordion item corresponds to its description | Cannot to hide first tip in accordion | Fail
#4P | Main page slider checking | Go to main page. Try to flipping through slider items | Flipping is working | Slider is working | Pass



## Business layer testing

Id  | Title | Scenario | Expected Result | Actual Result | Pass/Fail indication
--|--|--|--|--|--
#1B | Catalog item and its book page linking testing | Go to catalog page. Select a book. Click "Lean more.." link. | Corresponding book page is displaying | Expected book information is displaying | Pass
#2B | Cart addition checking | Go to book catalog. Select a book. Click "Add to cart" button. | The book has successfully added to the cart | Added book is displaying in the cart | Pass
#3B | Cart calculation checking | Go to catalog. Add few books to the cart. Go to cart page. | "Total price" field value corresponds to the sum of all books in the cart | Calculated by application sum equals to hand-caculated sum | Pass
#4B | Catalog filters checking | Go to catalog. Select an author or a category. Click on corresponding link on the right panel. | Only books by selected author (category) are displaying. | Corresponding books are displaying | Pass
#5B | The cheapest and the latest book finding checking | Go to catalog page. Find the latest book and the cheapest book in the catalog. Go to main page | Books are displaying on corresponding fields. | Expected book are displaying | Pass
#6B | Incorrect user input checking | Browse website. Try to enter an incorrect data to fields. | Error messages are displaying. | Fields checking works correctly on Log in and Sing up pages, but it isn't on other pages | Fail



# Conclusion



Developer should improve view for help page. And add validation for some pages. Missing validation don't to leads to application failure, but it allows to enter user some incorrect data to some fields. For example, administartor can provide numbers as author name.
